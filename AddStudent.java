import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.PreparedStatement;

public class AddStudent extends JFrame {

    JTextField idField,nameField, deptField, roomField;

    public AddStudent() {

        setTitle("Add Student");
        setSize(600, 450);   // Slightly adjusted window size
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        Color darkBrown = new Color(92, 64, 51);
        Color cream = new Color(245, 222, 179);

        getContentPane().setBackground(cream);
        setLayout(new BorderLayout());

        // Main Panel (Center aligned form)
        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setBackground(cream);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10); // spacing

        Font labelFont = new Font("Arial", Font.BOLD, 16);  // Bigger font

        JLabel idLabel = new JLabel("Student ID");
        idLabel.setFont(labelFont);
        idLabel.setForeground(darkBrown);

        JLabel nameLabel = new JLabel("Student Name:");
        nameLabel.setFont(labelFont);
        nameLabel.setForeground(darkBrown);

        JLabel deptLabel = new JLabel("Department:");
        deptLabel.setFont(labelFont);
        deptLabel.setForeground(darkBrown);

        JLabel roomLabel = new JLabel("Room Number:");
        roomLabel.setFont(labelFont);
        roomLabel.setForeground(darkBrown);

        // Smaller text fields
        idField = new JTextField(15);
        nameField = new JTextField(15);
        deptField = new JTextField(15);
        roomField = new JTextField(15);

        idField.setPreferredSize(new Dimension(180, 30));
        nameField.setPreferredSize(new Dimension(180, 30));
        deptField.setPreferredSize(new Dimension(180, 30));
        roomField.setPreferredSize(new Dimension(180, 30));

        JButton saveButton = new JButton("Save");
        saveButton.setBackground(darkBrown);
        saveButton.setForeground(Color.WHITE);
        saveButton.setFont(new Font("Arial", Font.BOLD, 14));
        saveButton.setPreferredSize(new Dimension(100, 35));

        // Row 1
        gbc.gridx = 0; gbc.gridy = 0; gbc.anchor = GridBagConstraints.EAST;
        formPanel.add(idLabel, gbc);

        gbc.gridx = 1; gbc.anchor = GridBagConstraints.WEST;
        formPanel.add(idField, gbc);

        //row2
        gbc.gridx = 0; gbc.gridy = 1; gbc.anchor = GridBagConstraints.EAST;
        formPanel.add(nameLabel, gbc);

        gbc.gridx = 1; gbc.anchor = GridBagConstraints.WEST;
        formPanel.add(nameField, gbc);

        // Row 2
        gbc.gridx = 0; gbc.gridy = 2; gbc.anchor = GridBagConstraints.EAST;
        formPanel.add(deptLabel, gbc);

        gbc.gridx = 1; gbc.anchor = GridBagConstraints.WEST;
        formPanel.add(deptField, gbc);

        // Row 3
        gbc.gridx = 0; gbc.gridy = 3; gbc.anchor = GridBagConstraints.EAST;
        formPanel.add(roomLabel, gbc);

        gbc.gridx = 1; gbc.anchor = GridBagConstraints.WEST;
        formPanel.add(roomField, gbc);

        // Row 4 (Button Centered)
        gbc.gridx = 0; gbc.gridy = 4;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        formPanel.add(saveButton, gbc);

        add(formPanel, BorderLayout.CENTER);

        saveButton.addActionListener(e -> addStudent());

        setVisible(true);
    }

    private void addStudent() {
        try {
            Connection con = DBConnection.getConnection();
            String sql = "INSERT INTO hostel (id,student_name, department, room_number, status) VALUES (?,?, ?, ?, 'Allocated')";
            PreparedStatement ps = con.prepareStatement(sql);

            ps.setString(1, idField.getText());
            ps.setString(2, nameField.getText());
            ps.setString(3, deptField.getText());
            ps.setString(4, roomField.getText());

            ps.executeUpdate();

            JOptionPane.showMessageDialog(this, "Student Added Successfully!");
            con.close();

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}