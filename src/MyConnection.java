
import java.sql.Connection;
import java.sql.DriverManager;

public class MyConnection {

    public static String driver = "com.mysql.jdbc.Driver";
    public static String url = "jdbc:mysql://localhost/stu_inf_sys";
    public static String username = "root";
    public static String password = "";

    public static Connection getConnection() {
        Connection con = null;
        try {
            Class.forName(driver);
            con = DriverManager.getConnection(url, username, password);
        } catch (Exception ex) {
            System.out.println("Error: " + ex.getMessage());
        }
        return con;
    }
}
