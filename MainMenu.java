import javax.swing.*;
import java.awt.*;

public class MainMenu extends JFrame {

    public MainMenu() {

        setTitle("Hostel Room Allocation System");
        setSize(600, 450);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Color darkBrown = new Color(92, 64, 51);
        Color cream = new Color(245, 222, 179);

        getContentPane().setBackground(cream);
        setLayout(new BorderLayout());

        // 🔹 Big Heading
        JLabel heading = new JLabel("HOSTEL ROOM ALLOCATION SYSTEM", JLabel.CENTER);
        heading.setFont(new Font("Arial", Font.BOLD, 18));
        heading.setForeground(darkBrown);
        heading.setBorder(BorderFactory.createEmptyBorder(20, 10, 20, 10));
        add(heading, BorderLayout.NORTH);

        // 🔹 Button Panel
        JPanel panel = new JPanel();
        panel.setBackground(cream);
        panel.setLayout(new GridLayout(5, 1, 15, 15));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 80, 40, 80));

        JButton addButton = createButton("Add Student", darkBrown, cream);
        JButton viewButton = createButton("View Students", darkBrown, cream);
        JButton updateButton = createButton("Update Student", darkBrown, cream);
        JButton vacateButton = createButton("Vacate Room", darkBrown, cream);
        JButton exitButton = createButton("Exit", darkBrown, cream);

        panel.add(addButton);
        panel.add(viewButton);
        panel.add(updateButton);
        panel.add(vacateButton);
        panel.add(exitButton);

        add(panel, BorderLayout.CENTER);

        addButton.addActionListener(e -> new AddStudent());
        viewButton.addActionListener(e -> new ViewStudent());
        updateButton.addActionListener(e -> new UpdateStudent());
        vacateButton.addActionListener(e -> new VacateRoom());
        exitButton.addActionListener(e -> System.exit(0));

        setVisible(true);
    }

    private JButton createButton(String text, Color bg, Color fg) {
        JButton button = new JButton(text);
        button.setBackground(bg);
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setFont(new Font("Arial", Font.BOLD, 14));
        return button;
    }

    public static void main(String[] args) {
        new MainMenu();
    }
}
