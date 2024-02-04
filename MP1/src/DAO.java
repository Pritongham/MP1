
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

    public static void initParams() {
        try {
            Class.forName(driver);

            try (Connection conn = DriverManager.getConnection(url, "app", "app"); Statement statement = conn.createStatement()) {
                //query user info
                String queryUserInfo = "SELECT * FROM APP.USERS";
                try (ResultSet rs = statement.executeQuery(queryUserInfo)) {
                    while (rs.next()) {
                        MP1.passwords.put(rs.getString("EMAIL").trim(), rs.getString("PASSWORD").trim());
                        MP1.roles.put(rs.getString("EMAIL").trim(), rs.getString("USERROLE").trim());
                    }

                    rs.close();
                }

                statement.close();
                conn.close();
            }
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void addRecord(String email, String password, String role) {
        try {
            Class.forName(driver);

            try (Connection conn = DriverManager.getConnection(url, "app", "app"); Statement statement = conn.createStatement()) {
                String insertQuery = "INSERT INTO APP.USERS (EMAIL, PASSWORD, USERROLE) VALUES ('"
                        + email + "', '" + password + "', '" + role + "')";
                statement.executeUpdate(insertQuery);

                statement.close();
                conn.close();
            }
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void deleteRecord(String email, String password, String role) {
        try {
            Class.forName(driver);

            try (Connection conn = DriverManager.getConnection(url, "app", "app"); Statement statement = conn.createStatement()) {
                String deleteRecordQuery = "DELETE FROM APP.USERS WHERE EMAIL = '" + email + "'";
                statement.executeUpdate(deleteRecordQuery);

                statement.close();
                conn.close();
            }
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void updateRecord(String email, String newPassword, String newRole) {
        try {
            Class.forName(driver);

            try (Connection conn = DriverManager.getConnection(url, "app", "app"); Statement statement = conn.createStatement()) {

                if (!newPassword.isEmpty()) {
                    String updatePasswordQuery = "UPDATE APP.USERS SET PASSWORD = '" + newPassword + "' WHERE EMAIL = '" + email + "'";
                    statement.executeUpdate(updatePasswordQuery);
                }

                String updateRoleQuery = "UPDATE APP.USERS SET USERROLE = '" + newRole + "' WHERE EMAIL = '" + email + "'";
                statement.executeUpdate(updateRoleQuery);

                statement.close();
            }
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
