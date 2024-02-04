
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
import javax.swing.border.EmptyBorder;

public class EditRecordPanel {
    private static final JPanel panel = new JPanel();
    private static final JFrame frame = new JFrame();
    
    public static void showEditRecordPanel(String email){
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
        JLabel passwordLabel = new JLabel("New Password:");
        panel.add(passwordLabel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.weightx = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        JPasswordField passwordText = new JPasswordField();
        panel.add(passwordText, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        gbc.weightx = 0;
        gbc.fill = GridBagConstraints.NONE;
        JLabel roleLabel = new JLabel("Role:");
        panel.add(roleLabel, gbc);
        
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        gbc.weightx = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        JComboBox roleChoice = new JComboBox();
        roleChoice.addItem("Guest");
        roleChoice.addItem("Admin");
        panel.add(roleChoice, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 3;
        gbc.weightx = 0;
        gbc.fill = GridBagConstraints.NONE;
        JButton updateRecord = new JButton("Edit Record");
        updateRecord.addActionListener(e -> {
            if(String.valueOf(roleChoice.getSelectedItem()).equalsIgnoreCase("Guest")
                    && email.equalsIgnoreCase(MP1.loggedInUser))
            {
                JOptionPane.showMessageDialog(null, "Cannot change your own role");
            }
            else
            {
                if(!passwordText.getText().isEmpty())
                {
                    MP1.passwords.put(email, passwordText.getText());  
                } 
                
                MP1.roles.put(email, String.valueOf(roleChoice.getSelectedItem()));

        // Update the record in the database
        DAO.updateRecord(email, passwordText.getText(), String.valueOf(roleChoice.getSelectedItem()));

                
                JOptionPane.showMessageDialog(null, "Success!");
                panel.removeAll();
                frame.dispose();
                AdminPanel.showAdminPanel();
            }
        });
        
        panel.add(updateRecord, gbc);
        
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

