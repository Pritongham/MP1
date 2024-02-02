
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class AddRecordPanel {
    private static final JPanel panel = new JPanel();
    private static final JFrame frame = new JFrame();
    
    public static void showAddRecordPanel(){
        frame.setSize(350, 250);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.add(panel);
        frame.setTitle("Add Record");
        
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
        gbc.gridy = 2;
        gbc.gridwidth = 1;
        gbc.weightx = 0;
        gbc.fill = GridBagConstraints.NONE;
        JLabel roleLabel = new JLabel("Role:");
        panel.add(roleLabel, gbc);
        
        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        gbc.weightx = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        JComboBox roleChoice = new JComboBox();
        roleChoice.addItem("Guest");
        roleChoice.addItem("Admin");
        panel.add(roleChoice, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 3;
        gbc.weightx = 0;
        gbc.fill = GridBagConstraints.NONE;
        JButton addRecord = new JButton("Add Record");
        addRecord.addActionListener(e -> {
            if(userText.getText().isEmpty() || passwordText.getText().isEmpty())
            {
                JOptionPane.showMessageDialog(null, "Please enter a username/password");
            }
            else
            {
                MP1.passwords.put(userText.getText(), passwordText.getText());
                MP1.roles.put(userText.getText(), String.valueOf(roleChoice.getSelectedItem()));
                
                //DATABASE PROCESS
                //DAO.addRecord(email, password, role);
                
                JOptionPane.showMessageDialog(null, "Success!");
                panel.removeAll();
                frame.dispose();
                AdminPanel.showAdminPanel();
            }
        });
        
        panel.add(addRecord, gbc);
        
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 3;
        gbc.weightx = 0;
        gbc.fill = GridBagConstraints.NONE;
        JButton goBack = new JButton("Go Back");
        
        goBack.addActionListener(e -> {
            panel.removeAll();
            frame.dispose();
            AdminPanel.showAdminPanel();
        });
        
        panel.add(goBack, gbc);
        
        frame.setVisible(true);
    }
}
