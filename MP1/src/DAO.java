
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DAO {
    static String driver = "org.apache.derby.jdbc.ClientDriver";
    static String url = "jdbc:derby://localhost:1527/loginDB";
    
    public static void initParams(){
       try{
           Class.forName(driver);
           
           //attempt to connect to database
           try (Connection conn = DriverManager.getConnection(url, "app", "app");
                   Statement statement = conn.createStatement()) 
           {
               //query user info
               String queryUserInfo = "SELECT * FROM APP.USERS";
               try (ResultSet rs = statement.executeQuery(queryUserInfo)) 
               {
                   while(rs.next())
                   {
                       MP1.passwords.put(rs.getString("EMAIL").trim(), rs.getString("PASSWORD").trim());
                       MP1.roles.put(rs.getString("EMAIL").trim(), rs.getString("USERROLE").trim());
                   }
                   
                   rs.close();
               }
               
               statement.close();
               conn.close();
           }
       }catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
       }
    }
    
    public static void addRecord(String email, String password, String role){
        try{
           Class.forName(driver);
           
           //attempt to connect to database
           try (Connection conn = DriverManager.getConnection(url, "app", "app");
                   Statement statement = conn.createStatement()) 
           {
               //ADD STATEMENT HERE
               
               statement.close();
               conn.close();
           }
       }catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
       }
    }
    
    public static void deleteRecord(String email, String password, String role){
        try{
           Class.forName(driver);
           
           //attempt to connect to database
           try (Connection conn = DriverManager.getConnection(url, "app", "app");
                   Statement statement = conn.createStatement()) 
           {
               //ADD STATEMENT HERE
               
               statement.close();
               conn.close();
           }
       }catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
       }
    }
}
