
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MyFunction {

    public static int countData(String tableName) {
        int total = 0;
        Connection con = MyConnection.getConnection();
        Statement st;
        try {
            st = con.createStatement();
            ResultSet rs = st.executeQuery("SELECT COUNT(*) as 'total' FROM " + tableName);
            while (rs.next()) {
                total = rs.getInt(1);
            }
        } catch (SQLException ex) {
            System.err.println("Error: " + ex.getMessage());
        }
        return total;
    }
}
