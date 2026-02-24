import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.PreparedStatement;

public class VacateRoom extends JFrame {

    JTextField idField;

    public VacateRoom() {

        setTitle("Vacate Room");
        setSize(450, 220);
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

        JLabel idLabel = new JLabel("Student ID:");
        idLabel.setFont(labelFont);
        idLabel.setForeground(darkBrown);

        idField = new JTextField(15);
        idField.setPreferredSize(new Dimension(180, 30));

        JButton vacateButton = new JButton("Vacate");
        vacateButton.setBackground(darkBrown);
        vacateButton.setForeground(Color.WHITE);
        vacateButton.setFont(new Font("Arial", Font.BOLD, 14));
        vacateButton.setPreferredSize(new Dimension(110, 35));

        // Row 1
        gbc.gridx = 0; gbc.gridy = 0; gbc.anchor = GridBagConstraints.EAST;
        formPanel.add(idLabel, gbc);

        gbc.gridx = 1; gbc.anchor = GridBagConstraints.WEST;
        formPanel.add(idField, gbc);

        // Row 2 Button
        gbc.gridx = 0; gbc.gridy = 1;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        formPanel.add(vacateButton, gbc);

        add(formPanel, BorderLayout.CENTER);

        vacateButton.addActionListener(e -> vacateRoom());

        setVisible(true);
    }

    private void vacateRoom() {
        try {
            Connection con = DBConnection.getConnection();
            String sql = "UPDATE hostel SET status='Vacated' WHERE id=?";
            PreparedStatement ps = con.prepareStatement(sql);

            ps.setInt(1, Integer.parseInt(idField.getText()));
            ps.executeUpdate();

            JOptionPane.showMessageDialog(this, "Room Vacated Successfully!");

            con.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
