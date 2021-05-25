
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class course {

    public void insertUpdateDeleteStudent(char operation, Integer id, String label, Integer hours) {
        Connection con = MyConnection.getConnection();
        PreparedStatement ps;

        // i for insert
        if (operation == 'i') {
            try {
                ps = con.prepareStatement("INSERT INTO course(label, hours_number) VALUES (?,?)");
                ps.setString(1, label);
                ps.setInt(2, hours);
                if (ps.executeUpdate() > 0) {
                    JOptionPane.showMessageDialog(null, "New Course Added!");
                } else {
                    JOptionPane.showMessageDialog(null, "Something Wrong!");
                }
            } catch (SQLException ex) {
                System.err.println("Error: " + ex.getMessage());
            }
        }

        // u for update
        if (operation == 'u') {
            try {
                ps = con.prepareStatement("UPDATE `course` SET `label`=?,`hours_number`=? WHERE `id` = ?");
                ps.setString(1, label);
                ps.setInt(2, hours);
                ps.setInt(3, id);

                if (ps.executeUpdate() > 0) {
                    JOptionPane.showMessageDialog(null, "Course Data Updated!");
                }
            } catch (SQLException ex) {
                System.err.println("Error: " + ex.getMessage());
            }
        }

        // d for delete
        if (operation == 'd') {
            int YesOrNo = JOptionPane.showConfirmDialog(null, "The Score Will Be Also Deleted", "Delete Crouse", JOptionPane.OK_CANCEL_OPTION, 0);
            if (YesOrNo == JOptionPane.OK_OPTION) {
                try {
                    ps = con.prepareStatement("DELETE FROM `course` WHERE `id` = ?");
                    ps.setInt(1, id);
                    if (ps.executeUpdate() > 0) {
                        JOptionPane.showMessageDialog(null, "Course Deleted!");
                    }
                } catch (SQLException ex) {
                    System.err.println("Error: " + ex.getMessage());
                }
            }
        }
    }

    public void fillCourseJtable(JTable table) {
        Connection con = MyConnection.getConnection();
        PreparedStatement ps;
        try {
            ps = con.prepareStatement("SELECT * FROM `course`");

            ResultSet rs = ps.executeQuery();
            DefaultTableModel model = (DefaultTableModel) table.getModel();

            Object[] row;
            while (rs.next()) {
                row = new Object[3];
                row[0] = rs.getInt(1);
                row[1] = rs.getString(2);
                row[2] = rs.getInt(3);

                model.addRow(row);
            }
        } catch (SQLException ex) {
            System.err.println("Error: " + ex.getMessage());
        }
    }

    public boolean isCourseExist(String courseName) {
        boolean isExist = false;
        Connection con = MyConnection.getConnection();
        PreparedStatement ps;
        try {
            ps = con.prepareStatement("SELECT * FROM `course` WHERE `label` = ?");
            ps.setString(1, courseName);

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                isExist = true;
            }
        } catch (SQLException ex) {
            System.err.println("Error: " + ex.getMessage());
        }
        return isExist;
    }

    public int getCourseId(String courseLabel) {
        int courseId = 0;
        Connection con = MyConnection.getConnection();
        PreparedStatement ps;
        try {
            ps = con.prepareStatement("SELECT id FROM `course` WHERE `label` = ?");
            ps.setString(1, courseLabel);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                courseId = rs.getInt("id");
            }
        } catch (SQLException ex) {
            System.err.println("Error: " + ex.getMessage());
        }
        return courseId;
    }

    public void fillCourseCombo(JComboBox combo) {
        Connection con = MyConnection.getConnection();
        PreparedStatement ps;
        try {
            ps = con.prepareStatement("SELECT * FROM `course`");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                combo.addItem(rs.getString(2));
            }
        } catch (SQLException ex) {
            System.err.println("Error: " + ex.getMessage());
        }
    }
}
