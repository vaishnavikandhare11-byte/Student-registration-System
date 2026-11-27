import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;

// Student class
class Student {
    private String id, name, rollNo, email, phone;
    private String gender, course, year, division, address;

    public Student(String id, String name, String rollNo,
                   String email, String phone, String gender,
                   String course, String year, String division,
                   String address) {

        this.id = id;
        this.name = name;
        this.rollNo = rollNo;
        this.email = email;
        this.phone = phone;
        this.gender = gender;
        this.course = course;
        this.year = year;
        this.division = division;
        this.address = address;
    }

    public String getId() { return id; }
    public String getName() { return name; }
    public String getRollNo() { return rollNo; }
    public String getCourse() { return course; }
    public String getYear() { return year; }
    public String getDivision() { return division; }
}

// Main GUI class
public class StudentRegistrationGUI extends JFrame {

    private JTextField nameField, rollField;
    private JTextField emailField, phoneField;
    private JTextArea addressArea;

    private JRadioButton maleButton, femaleButton, otherButton;
    private ButtonGroup genderGroup;

    private JComboBox<String> courseBox, yearBox, divisionBox;

    private JTable studentTable;
    private DefaultTableModel tableModel;

    private JLabel statusLabel;

    private ArrayList<Student> students = new ArrayList<>();
    private int registrationCount = 1;

    public StudentRegistrationGUI() {

        setTitle("Student Registration System");
        setSize(1050, 750);
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(15, 15));

        getContentPane().setBackground(
            new Color(245, 247, 252));

        add(createHeader(), BorderLayout.NORTH);
        add(createMainPanel(), BorderLayout.CENTER);
        add(createFooter(), BorderLayout.SOUTH);

        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent e) {
                exitApplication();
            }
        });
    }

    // Header
    private JPanel createHeader() {

        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(new Color(35, 55, 90));
        panel.setBorder(
            BorderFactory.createEmptyBorder(20, 25, 20, 25));

        JLabel title = new JLabel(
            "STUDENT REGISTRATION SYSTEM");

        title.setForeground(Color.WHITE);
        title.setFont(new Font("Arial", Font.BOLD, 25));

        JLabel subtitle = new JLabel(
            "Java Swing GUI Application | OOP Project");

        subtitle.setForeground(new Color(220, 230, 245));

        JPanel textPanel = new JPanel(new GridLayout(2, 1, 0, 5));
        textPanel.setOpaque(false);
        textPanel.add(title);
        textPanel.add(subtitle);

        panel.add(textPanel, BorderLayout.CENTER);

        return panel;
    }

    // Main panel
    private JPanel createMainPanel() {

        JPanel main = new JPanel(new BorderLayout(15, 15));
        main.setOpaque(false);
        main.setBorder(
            BorderFactory.createEmptyBorder(5, 20, 5, 20));

        main.add(createFormPanel(), BorderLayout.NORTH);
        main.add(createTablePanel(), BorderLayout.CENTER);

        return main;
    }

    // Registration form
    private JPanel createFormPanel() {

        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(Color.WHITE);

        panel.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(
                new Color(220, 225, 235)),
            BorderFactory.createEmptyBorder(15, 20, 15, 20)
        ));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(7, 8, 7, 8);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1;

        JLabel formTitle = new JLabel("Student Details");
        formTitle.setFont(new Font("Arial", Font.BOLD, 19));
        formTitle.setForeground(new Color(35, 55, 90));

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 4;
        panel.add(formTitle, gbc);

        gbc.gridwidth = 1;

        nameField = new JTextField(15);
        rollField = new JTextField(15);
        emailField = new JTextField(15);
        phoneField = new JTextField(15);

        addField(panel, gbc, "Full Name:", nameField, 0, 1);
        addField(panel, gbc, "Roll Number:", rollField, 2, 1);

        addField(panel, gbc, "Email:", emailField, 0, 2);
        addField(panel, gbc, "Phone Number:", phoneField, 2, 2);

        // Gender
        maleButton = new JRadioButton("Male");
        femaleButton = new JRadioButton("Female");
        otherButton = new JRadioButton("Other");

        maleButton.setBackground(Color.WHITE);
        femaleButton.setBackground(Color.WHITE);
        otherButton.setBackground(Color.WHITE);

        genderGroup = new ButtonGroup();
        genderGroup.add(maleButton);
        genderGroup.add(femaleButton);
        genderGroup.add(otherButton);

        JPanel genderPanel = new JPanel(
            new FlowLayout(FlowLayout.LEFT, 5, 0));

        genderPanel.setBackground(Color.WHITE);
        genderPanel.add(maleButton);
        genderPanel.add(femaleButton);
        genderPanel.add(otherButton);

        addField(panel, gbc, "Gender:", genderPanel, 0, 3);

        // Course
        courseBox = new JComboBox<>(new String[]{
            "B.Tech IT",
            "Computer Engineering",
            "Mechanical Engineering",
            "Civil Engineering",
            "Electronics Engineering"
        });

        addField(panel, gbc, "Course:", courseBox, 2, 3);

        // Year and division
        yearBox = new JComboBox<>(new String[]{
            "First Year", "Second Year",
            "Third Year", "Final Year"
        });

        divisionBox = new JComboBox<>(new String[]{
            "A", "B", "C", "D"
        });

        addField(panel, gbc, "Academic Year:", yearBox, 0, 4);
        addField(panel, gbc, "Division:", divisionBox, 2, 4);

        // Address
        addressArea = new JTextArea(2, 15);
        addressArea.setLineWrap(true);
        addressArea.setWrapStyleWord(true);

        JScrollPane addressScroll = new JScrollPane(addressArea);

        addField(panel, gbc, "Address:", addressScroll, 0, 5);

        // Buttons
        JButton registerButton = createButton(
            "REGISTER", new Color(40, 140, 100));

        JButton clearButton = createButton(
            "CLEAR", new Color(90, 110, 140));

        JButton exitButton = createButton(
            "EXIT", new Color(190, 65, 65));

        registerButton.addActionListener(e -> registerStudent());
        clearButton.addActionListener(e -> clearForm());
        exitButton.addActionListener(e -> exitApplication());

        JPanel buttonPanel = new JPanel(
            new FlowLayout(FlowLayout.CENTER, 15, 5));

        buttonPanel.setBackground(Color.WHITE);
        buttonPanel.add(registerButton);
        buttonPanel.add(clearButton);
        buttonPanel.add(exitButton);

        gbc.gridx = 0;
        gbc.gridy = 6;
        gbc.gridwidth = 4;

        panel.add(buttonPanel, gbc);

        return panel;
    }

    // Add form fields
    private void addField(JPanel panel, GridBagConstraints gbc,
                          String label, Component component,
                          int x, int y) {

        gbc.gridy = y;
        gbc.gridx = x;
        gbc.gridwidth = 1;
        gbc.weightx = 0.2;

        JLabel fieldLabel = new JLabel(label);
        fieldLabel.setFont(
            new Font("Arial", Font.BOLD, 13));

        panel.add(fieldLabel, gbc);

        gbc.gridx = x + 1;
        gbc.weightx = 1;

        panel.add(component, gbc);
    }

    // Create buttons
    private JButton createButton(String text, Color color) {

        JButton button = new JButton(text);

        button.setBackground(color);
        button.setForeground(Color.WHITE);
        button.setFont(new Font("Arial", Font.BOLD, 12));
        button.setFocusPainted(false);
        button.setBorderPainted(false);
        button.setCursor(
            new Cursor(Cursor.HAND_CURSOR));

        button.setPreferredSize(new Dimension(120, 35));

        return button;
    }

    // Student table
    private JPanel createTablePanel() {

        JPanel panel = new JPanel(new BorderLayout(5, 10));
        panel.setBackground(Color.WHITE);

        panel.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(
                new Color(220, 225, 235)),
            BorderFactory.createEmptyBorder(12, 15, 12, 15)
        ));

        JLabel heading = new JLabel("Registered Students");

        heading.setFont(new Font("Arial", Font.BOLD, 18));
        heading.setForeground(new Color(35, 55, 90));

        String[] columns = {
            "Reg. ID", "Name", "Roll No.",
            "Course", "Year", "Division"
        };

        tableModel = new DefaultTableModel(columns, 0) {
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        studentTable = new JTable(tableModel);
        studentTable.setRowHeight(27);

        JScrollPane scrollPane = new JScrollPane(studentTable);

        panel.add(heading, BorderLayout.NORTH);
        panel.add(scrollPane, BorderLayout.CENTER);

        return panel;
    }

    // Footer
    private JPanel createFooter() {

        JPanel panel = new JPanel(new BorderLayout());

        panel.setBackground(new Color(235, 240, 248));
        panel.setBorder(
            BorderFactory.createEmptyBorder(10, 20, 10, 20));

        statusLabel = new JLabel(
            "Ready to register students");

        JLabel footerText = new JLabel(
            "OOP GUI Application | Java Swing");

        panel.add(statusLabel, BorderLayout.WEST);
        panel.add(footerText, BorderLayout.EAST);

        return panel;
    }

    // Register student
    private void registerStudent() {

        String name = nameField.getText().trim();
        String roll = rollField.getText().trim();
        String email = emailField.getText().trim();
        String phone = phoneField.getText().trim();
        String address = addressArea.getText().trim();

        if (name.isEmpty() || roll.isEmpty() ||
            email.isEmpty() || phone.isEmpty() ||
            address.isEmpty()) {

            showError("Please fill in all the fields.");
            return;
        }

        if (!name.matches("[\\p{L} .'-]+")) {
            showError("Please enter a valid student name.");
            return;
        }

        if (!roll.matches("[A-Za-z0-9-]+")) {
            showError("Please enter a valid roll number.");
            return;
        }

        if (!email.matches(
            "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$")) {

            showError("Please enter a valid email address.");
            return;
        }

        if (!phone.matches("[6-9][0-9]{9}")) {
            showError("Enter a valid 10-digit Indian mobile number.");
            return;
        }

        if (!maleButton.isSelected() &&
            !femaleButton.isSelected() &&
            !otherButton.isSelected()) {

            showError("Please select your gender.");
            return;
        }

        for (Student s : students) {
            if (s.getRollNo().equalsIgnoreCase(roll)) {
                showError("This roll number is already registered.");
                return;
            }
        }

        String gender = maleButton.isSelected() ? "Male" :
                        femaleButton.isSelected() ? "Female" : "Other";

        String course = (String) courseBox.getSelectedItem();
        String year = (String) yearBox.getSelectedItem();
        String division = (String) divisionBox.getSelectedItem();

        String id = String.format("REG%03d", registrationCount);

        Student student = new Student(
            id, name, roll, email, phone,
            gender, course, year, division, address
        );

        students.add(student);

        tableModel.addRow(new Object[]{
            student.getId(),
            student.getName(),
            student.getRollNo(),
            student.getCourse(),
            student.getYear(),
            student.getDivision()
        });

        registrationCount++;

        statusLabel.setText(
            "Total registered students: " + students.size());

        JOptionPane.showMessageDialog(
            this,
            "Registration Successful!\n\n" +
            "Registration ID: " + id +
            "\nName: " + name +
            "\nRoll Number: " + roll +
            "\nCourse: " + course,
            "Success",
            JOptionPane.INFORMATION_MESSAGE
        );

        clearForm();
    }

    // Error message
    private void showError(String message) {

        JOptionPane.showMessageDialog(
            this,
            message,
            "Validation Error",
            JOptionPane.ERROR_MESSAGE
        );
    }

    // Clear form
    private void clearForm() {

        nameField.setText("");
        rollField.setText("");
        emailField.setText("");
        phoneField.setText("");
        addressArea.setText("");

        genderGroup.clearSelection();

        courseBox.setSelectedIndex(0);
        yearBox.setSelectedIndex(0);
        divisionBox.setSelectedIndex(0);

        nameField.requestFocus();
    }

    // Exit application
    private void exitApplication() {

        int choice = JOptionPane.showConfirmDialog(
            this,
            "Are you sure you want to exit?",
            "Exit Application",
            JOptionPane.YES_NO_OPTION
        );

        if (choice == JOptionPane.YES_OPTION) {
            dispose();
        }
    }

    // Main method
    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {

            StudentRegistrationGUI app =
                new StudentRegistrationGUI();

            app.setVisible(true);
        });
    }
}