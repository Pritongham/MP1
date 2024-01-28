import javax.swing.*;
import java.awt.*;
import java.util.HashMap;

public class MP1 {

    private static final int max = 3;
    private static int counter = 0;
    private static HashMap<String, String> credentials;
    private static JFrame login;

    public static void main(String[] args) {
        credentials = readCredentials();
        showLogin();
    }

    private static void showLogin() {
        login = new JFrame("Login Screen");
        login.setSize(350, 200);
        login.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel panel = new JPanel();
        login.add(panel);
        LoginComp(panel);
        login.setVisible(true);
    }

    private static void LoginComp(JPanel panel) {
        panel.setLayout(null);

        JLabel userLabel = new JLabel("User:");
        userLabel.setBounds(10, 20, 80, 25);
        panel.add(userLabel);

        JTextField userText = new JTextField(20);
        userText.setBounds(80, 20, 165, 25);
        panel.add(userText);

        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setBounds(10, 50, 80, 25);
        panel.add(passwordLabel);

        JPasswordField passwordText = new JPasswordField(20);
        passwordText.setBounds(80, 50, 165, 25);
        panel.add(passwordText);

        JButton loginButton = new JButton("Login");
        loginButton.setBounds(80, 80, 80, 25);
        panel.add(loginButton);

        loginButton.addActionListener(e -> {
            String username = userText.getText();
            String password = new String(passwordText.getPassword());

            if (isValid(username, password, credentials)) {
                login.dispose();
            } else {
                counter++;
                if (counter == max) {
                    JOptionPane.showMessageDialog(null, "Sorry, you have reached the limit of 3 tries, goodbye!");
                    System.exit(0);
                } else {
                    JOptionPane.showMessageDialog(null, "Incorrect Username / Password");
                    userText.setText("");
                    passwordText.setText("");
                }
            }
        });
    }

    private static boolean isValid(String username, String password, HashMap<String, String> credentials) {
        return credentials.containsKey(username) && credentials.get(username).equals(password);
    }


    private static void displayException(String title, String message) {
        JFrame exceptionFrame = new JFrame(title);
        exceptionFrame.setSize(400, 150);
        exceptionFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel exceptionPanel = new JPanel();
        exceptionPanel.setLayout(new BorderLayout());
        exceptionFrame.add(exceptionPanel);

        JLabel exceptionLabel = new JLabel(message);
        exceptionLabel.setHorizontalAlignment(JLabel.CENTER);
        exceptionPanel.add(exceptionLabel, BorderLayout.CENTER);

        JButton ok = new JButton("OK");
        ok.addActionListener(e -> exceptionFrame.dispose());
        exceptionPanel.add(ok, BorderLayout.SOUTH);

        exceptionFrame.setVisible(true);
    }

    private static HashMap<String, String> readCredentials() {
        HashMap<String, String> credentials = new HashMap<>();

        credentials.put("user1", "password1");
        credentials.put("user2", "password2");

        return credentials;
    }
}
