
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.Map;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.Border;

public class AdminPanel {
    private static final JFrame frame = new JFrame();
    private static final JPanel panel = new JPanel();
    private static final Border border = BorderFactory.createLineBorder(Color.black, 1);
    private static int counter = 1;
    
    public static void showAdminPanel() {
        int height = MP1.passwords.size() + 1;
        
        frame.setSize(800, height * 45 + 120);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        frame.add(panel);
        frame.setTitle("Admin Panel - Logged in as " + MP1.loggedInUser);
        panel.setLayout(new GridLayout(0,5));

        JLabel emailLabel = new JLabel("Email", SwingConstants.CENTER);
        emailLabel.setBackground(Color.GRAY);
        emailLabel.setBorder(border);
        emailLabel.setFont(new Font("Times New Roman", Font.BOLD, 22));
        panel.add(emailLabel);
        
        JLabel passwordLabel = new JLabel("Password", SwingConstants.CENTER);
        passwordLabel.setBorder(border);
        passwordLabel.setFont(new Font("Times New Roman", Font.BOLD, 22));
        passwordLabel.setBackground(Color.GRAY);
        panel.add(passwordLabel);
        
        JLabel roleLabel = new JLabel("Role", SwingConstants.CENTER);
        roleLabel.setBorder(border);
        roleLabel.setFont(new Font("Times New Roman", Font.BOLD, 22));
        roleLabel.setBackground(Color.GRAY);
        panel.add(roleLabel);
        
        //filler
        JLabel filler1 = new JLabel();
        filler1.setBorder(border);
        filler1.setBackground(Color.GRAY);
        panel.add(filler1);
        
        //filler
        JLabel filler2 = new JLabel();
        filler2.setBorder(border);
        filler2.setBackground(Color.GRAY);
        panel.add(filler2);
        
       for(Map.Entry<String, String> entry : MP1.passwords.entrySet()) {
            JLabel emailEntry = new JLabel(entry.getKey(), SwingConstants.CENTER);
            emailEntry.setOpaque(true);
            emailEntry.setBorder(border);
            
            JLabel passwordEntry = new JLabel(entry.getValue(), SwingConstants.CENTER);
            passwordEntry.setOpaque(true);
            passwordEntry.setBorder(border);
            
            JLabel roleEntry = new JLabel(MP1.roles.get(entry.getKey()), SwingConstants.CENTER);
            roleEntry.setOpaque(true);
            roleEntry.setBorder(border);
            
            JButton deleteButton = new JButton("DELETE");
            deleteButton.setOpaque(true);
            deleteButton.setBorder(border);
            deleteButton.setBackground(Color.red);
            
            deleteButton.addActionListener(e -> {
                if(entry.getKey().equals(MP1.loggedInUser))
                {
                    JOptionPane.showMessageDialog(null, "Cannot delete your own account");
                    return;
                }
                
                MP1.roles.remove(entry.getKey());
                MP1.passwords.remove(entry.getKey());
                
                DAO.deleteRecord(entry.getKey(), entry.getValue(), entry.getValue());
                
                panel.removeAll();
                frame.dispose();
                showAdminPanel();
            });
            
            JButton editButton = new JButton("EDIT");
            editButton.setOpaque(true);
            editButton.setBorder(border);
            editButton.setBackground(Color.GREEN);
            
            editButton.addActionListener(e -> {
                panel.removeAll();
                frame.dispose();
                EditRecordPanel.showEditRecordPanel(entry.getKey());
            });
            
            setColor(emailEntry, passwordEntry, roleEntry);
            
            panel.add(emailEntry);
            panel.add(passwordEntry);
            panel.add(roleEntry);
            panel.add(deleteButton);
            panel.add(editButton);
        }
       
       //filler space
       panel.add(new JLabel());
       panel.add(new JLabel());
       panel.add(new JLabel());
       panel.add(new JLabel());
       panel.add(new JLabel());
       panel.add(new JLabel());
       
       JButton logoutButton = new JButton("Logout");
       panel.add(logoutButton);

       logoutButton.addActionListener(e -> {
           panel.removeAll();
           frame.dispose();
           MP1.loggedInUser = "";
           
           LoginPanel.constructLoginPanel();
       });
        
        panel.add(new JLabel());
        
        JButton addRecord = new JButton("Add Record");
        panel.add(addRecord);
        
        addRecord.addActionListener(e -> {
           panel.removeAll();
           frame.dispose();
           
           AddRecordPanel.showAddRecordPanel();
       });
        
        frame.setVisible(true);
    }
    
    private static void setColor(JLabel label1, JLabel label2, JLabel label3){
        if(counter % 2 == 0) 
        {
            counter++;
            label1.setBackground(Color.lightGray);
            label2.setBackground(Color.lightGray);
            label3.setBackground(Color.lightGray);
        }
        else 
        {
            counter++;
        }
    }
}
