import java.util.TreeMap;

public class MP1 {
    public static final TreeMap<String, String> passwords = new TreeMap();
    public static final TreeMap<String, String> roles = new TreeMap();
    
    public static String loggedInUser = "";

    public static void main(String[] args) {
        DAO.initParams();
        
        //TO IVAN:
        //EDIT:
        //DAO.java - line 51 done
        //DAO.java - line 69 done
        //
        //AdminPanel.java - line 83 done
        //
        //AddRecordPanel.java - line 98 done
        //EditRecordPanel.java - line 83 
        //EditRecordPanel.java - line 91 

        LoginPanel.constructLoginPanel();
    }
}