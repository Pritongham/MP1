
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class LoginPanel {
    private static int counter = 0;
    private static final JPanel panel = new JPanel();
    private static final JFrame frame = new JFrame();
    
    public static void constructLoginPanel() {
        frame.setSize(350, 200);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.add(panel);
        frame.setTitle("Login");
        
        panel.setBorder(new EmptyBorder(0, 25, 0, 25));
        panel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(0, 0, 10, 10);

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 1;
        gbc.weightx = 0;
        gbc.fill = GridBagConstraints.NONE;
        JLabel userLabel = new JLabel("User:");
        panel.add(userLabel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.weightx = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        JTextField userText = new JTextField();
        panel.add(userText, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        gbc.weightx = 0;
        gbc.fill = GridBagConstraints.NONE;
        JLabel passwordLabel = new JLabel("Password:");
        panel.add(passwordLabel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        gbc.weightx = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        JPasswordField passwordText = new JPasswordField();
        panel.add(passwordText, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 3;
        gbc.weightx = 0;
        gbc.fill = GridBagConstraints.NONE;
        JButton loginButton = new JButton("Login");

        loginButton.addActionListener(e -> {
            String email = userText.getText();
            String password = new String(passwordText.getPassword());
            
            if(isValid(email, password)) 
            {
                loginAuthentication(isAdmin(email), userText, passwordText);
            }
            else
            {
                loginFailed(userText, passwordText);
            }
        });
        
        panel.add(loginButton, gbc);
        
        frame.setVisible(true);
    }
    
    private static boolean isAdmin(String email) {
        String role = MP1.roles.get(email);
        
        if(role.equalsIgnoreCase("Guest")) return false;
        else if(role.equalsIgnoreCase("Admin")) return true;
        
        return false;
    }
    
    private static boolean isValid(String email, String password) {
        //checks if email exists within database
        if(!MP1.passwords.containsKey(email)) return false;
        
        return MP1.passwords.get(email).equals(password);
    }
    
    private static void loginAuthentication(boolean isAdmin, JTextField userText, JPasswordField passwordText){
        panel.removeAll();
        frame.dispose();
        
        MP1.loggedInUser = userText.getText();
        userText.setText(null);
        passwordText.setText(null);
        
        if(isAdmin) 
        {
            AdminPanel.showAdminPanel();
        } else 
        {
            GuestPanel.showGuestPanel();
        }
    }
    
    private static void loginFailed(JTextField userText, JPasswordField passwordText){
        counter++;
        if (counter == 3) 
        {
            JOptionPane.showMessageDialog(null, "Sorry, you have reached the limit of 3 tries, goodbye!");
            System.exit(0);
        } 
        else 
        {
            JOptionPane.showMessageDialog(null, "Incorrect Username / Password");
            userText.setText(null);
            passwordText.setText(null);
        }
    }
}
