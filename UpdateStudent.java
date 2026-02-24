import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.PreparedStatement;

public class UpdateStudent extends JFrame {

    JTextField idField, roomField;

    public UpdateStudent() {

        setTitle("Update Room");
        setSize(600, 450);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        Color darkBrown = new Color(92, 64, 51);
        Color cream = new Color(245, 222, 179);

        getContentPane().setBackground(cream);
        setLayout(new BorderLayout());

        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setBackground(cream);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(15, 10, 15, 10);

        Font labelFont = new Font("Arial", Font.BOLD, 16);

        // Labels
        JLabel idLabel = new JLabel("Student ID:");
        idLabel.setFont(labelFont);
        idLabel.setForeground(darkBrown);

        JLabel roomLabel = new JLabel("New Room No:");
        roomLabel.setFont(labelFont);
        roomLabel.setForeground(darkBrown);

        // Text Fields (Smaller)
        idField = new JTextField(15);
        roomField = new JTextField(15);

        Dimension fieldSize = new Dimension(180, 30);
        idField.setPreferredSize(fieldSize);
        roomField.setPreferredSize(fieldSize);

        // Button
        JButton updateButton = new JButton("Update Room");
        updateButton.setBackground(darkBrown);
        updateButton.setForeground(Color.WHITE);
        updateButton.setFont(new Font("Arial", Font.BOLD, 14));
        updateButton.setPreferredSize(new Dimension(130, 35));

        // Row 1 - ID
        gbc.gridx = 0; gbc.gridy = 0; gbc.anchor = GridBagConstraints.EAST;
        formPanel.add(idLabel, gbc);

        gbc.gridx = 1; gbc.anchor = GridBagConstraints.WEST;
        formPanel.add(idField, gbc);

        // Row 2 - Room
        gbc.gridx = 0; gbc.gridy = 1; gbc.anchor = GridBagConstraints.EAST;
        formPanel.add(roomLabel, gbc);

        gbc.gridx = 1; gbc.anchor = GridBagConstraints.WEST;
        formPanel.add(roomField, gbc);

        // Row 3 - Button
        gbc.gridx = 0; gbc.gridy = 2;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        formPanel.add(updateButton, gbc);

        add(formPanel, BorderLayout.CENTER);

        updateButton.addActionListener(e -> updateRoom());

        setVisible(true);
    }

    private void updateRoom() {
        try {
            Connection con = DBConnection.getConnection();
            String sql = "UPDATE hostel SET room_number=? WHERE id=?";
            PreparedStatement ps = con.prepareStatement(sql);

            ps.setString(1, roomField.getText());
            ps.setInt(2, Integer.parseInt(idField.getText()));

            ps.executeUpdate();

            JOptionPane.showMessageDialog(this, "Room Updated Successfully!");

            con.close();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error updating room!");
            e.printStackTrace();
        }
    }
}
