import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class ViewStudent extends JFrame {

    JTable table;

    public ViewStudent() {

        setTitle("View Students");
        setSize(600, 450);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        Color cream = new Color(245, 222, 179);
        Color darkBrown = new Color(92, 64, 51);

        getContentPane().setBackground(cream);
        setLayout(new BorderLayout());

        table = new JTable();
        table.setBackground(Color.WHITE);
        table.setForeground(darkBrown);

        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);

        loadData();

        setVisible(true);
    }

    private void loadData() {
        DefaultTableModel model = new DefaultTableModel();
        model.setColumnIdentifiers(new String[]{
                "ID", "Student Name", "Department", "Room", "Status"
        });

        try {
            Connection con = DBConnection.getConnection();
            String sql = "SELECT * FROM hostel";
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                model.addRow(new Object[]{
                        rs.getInt("id"),
                        rs.getString("student_name"),
                        rs.getString("department"),
                        rs.getString("room_number"),
                        rs.getString("status")
                });
            }

            table.setModel(model);
            con.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
