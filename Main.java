
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

class Student {

    private final String name;
    private final int id;
    private final double result;
    private final String password;

    public Student(String name, int id, double result, String password) {
        this.name = name;
        this.id = id;
        this.result = result;
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public double getResult() {
        return result;
    }

    public String getPassword() {
        return password;
    }

    void setName(String name) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    void setResult(double result) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}

class StudentRegistrationSystem extends JFrame {

    private final ArrayList<Student> studentList;
    private DefaultTableModel tableModel;
    private JTable studentTable;
    private JTextField nameField, idField, resultField, passwordField;

    public StudentRegistrationSystem() {
        studentList = new ArrayList<>();
        createUI();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    private void createUI() {
        setTitle("Student Registration System");
        setSize(800, 400);
        setLayout(new BorderLayout());
        JPanel inputPanel = new JPanel(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.fill = GridBagConstraints.HORIZONTAL;
        JLabel nameLabel = new JLabel("Name:");
        constraints.gridx = 0;
        constraints.gridy = 0;
        inputPanel.add(nameLabel, constraints);
        nameField = new JTextField(20);
        constraints.gridx = 1;
        constraints.gridy = 0;
        inputPanel.add(nameField, constraints);
        JLabel idLabel = new JLabel("ID:");
        constraints.gridx = 0;
        constraints.gridy = 1;
        inputPanel.add(idLabel, constraints);
        idField = new JTextField(20);
        constraints.gridx = 1;
        constraints.gridy = 1;
        inputPanel.add(idField, constraints);
        JLabel resultLabel = new JLabel("Result:");
        constraints.gridx = 0;
        constraints.gridy = 2;
        inputPanel.add(resultLabel, constraints);
        resultField = new JTextField(20);
        constraints.gridx = 1;
        constraints.gridy = 2;
        inputPanel.add(resultField, constraints);
        JLabel passwordLabel = new JLabel("Password:");
        constraints.gridx = 0;
        constraints.gridy = 3;
        inputPanel.add(passwordLabel, constraints);
        passwordField = new JPasswordField(20);
        constraints.gridx = 1;
        constraints.gridy = 3;
        inputPanel.add(passwordField, constraints);
        JButton addButton = new JButton("Add Student");
        addButton.addActionListener((ActionEvent e) -> {
            String name1 = nameField.getText();
            int id = Integer.parseInt(idField.getText());
            double result = Double.parseDouble(resultField.getText());
            String password = passwordField.getText();
            Student student = new Student(name1, id, result, password);
            studentList.add(student);
            Object[] rowData = {name1, id, result};
            tableModel.addRow(rowData);
            JOptionPane.showMessageDialog(StudentRegistrationSystem.this, "Student added Successfully!");
            clearFields();
        });
        constraints.gridx = 0;
        constraints.gridy = 4;
        constraints.gridwidth = 2;
        inputPanel.add(addButton, constraints);
        add(inputPanel, BorderLayout.NORTH);
        tableModel = new DefaultTableModel();
        studentTable = new JTable(tableModel);
        tableModel.addColumn("Name");
        tableModel.addColumn("ID");
        tableModel.addColumn("Result");
        JScrollPane scrollPane = new JScrollPane(studentTable);
        add(scrollPane, BorderLayout.CENTER);
        JPanel buttonPanel = new JPanel();
        JButton viewButton = new JButton("View");
        viewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int selectedRow = studentTable.getSelectedRow();
                if (selectedRow != -1) {
                    String password = JOptionPane.showInputDialog(StudentRegistrationSystem.this, "Enter Paeeword:");
                    Student selectedStudent = studentList.get(selectedRow);
                    if (password.equals(selectedStudent.getPassword())) {
                        JOptionPane.showMessageDialog(StudentRegistrationSystem.this, "Name: " + selectedStudent.getName()
                                + "\nID: " + selectedStudent.getId()
                                + "\nResult: " + selectedStudent.getResult());
                    } else {
                        JOptionPane.showMessageDialog(StudentRegistrationSystem.this, "Invalid Password");
                    }
                } else {
                    JOptionPane.showMessageDialog(StudentRegistrationSystem.this, "Select a Student From a Table");
                }
            }
        });
        JButton updateButton = new JButton("Update");
        updateButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int selectedRow = studentTable.getSelectedRow();
                if (selectedRow != -1) {
                    String password = JOptionPane.showInputDialog(StudentRegistrationSystem.this, "Enter Password:");
                    Student selectedStudent = studentList.get(selectedRow);
                    if (password.equals(selectedStudent.getPassword())) {
                        String name = JOptionPane.showInputDialog(StudentRegistrationSystem.this, "Enter Updated Name:");
                        double result = Double.parseDouble(JOptionPane.showInputDialog(StudentRegistrationSystem.this, "Enter Updated Result:"));
                        selectedStudent.setName(name);
                        selectedStudent.setResult(result);
                        tableModel.setValueAt(name, selectedRow, 0);
                        tableModel.setValueAt(result, selectedRow, 2);
                        JOptionPane.showMessageDialog(StudentRegistrationSystem.this, "Student Updated Successfully!");
                    } else {
                        JOptionPane.showMessageDialog(StudentRegistrationSystem.this, "Invalid Password");
                    }
                } else {
                    JOptionPane.showMessageDialog(StudentRegistrationSystem.this, "Select a Student From a Table");
                }
            }
        });
        JButton deleteButton = new JButton("Delete");
        deleteButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int selectedRow = studentTable.getSelectedRow();
                if (selectedRow != -1) {
                    String password = JOptionPane.showInputDialog(StudentRegistrationSystem.this, "Enter Password:");
                    Student selectedStudent = studentList.get(selectedRow);
                    if (password.equals(selectedStudent.getPassword())) {
                        studentList.remove(selectedStudent);
                        tableModel.removeRow(selectedRow);
                        JOptionPane.showMessageDialog(StudentRegistrationSystem.this, "Student Updated Successfully!");
                    } else {
                        JOptionPane.showMessageDialog(StudentRegistrationSystem.this, "Invalid Password");
                    }
                } else {
                    JOptionPane.showMessageDialog(StudentRegistrationSystem.this, "Select a Student From a Table");
                }
            }
        });
        buttonPanel.add(viewButton);
        buttonPanel.add(updateButton);
        buttonPanel.add(deleteButton);
        add(buttonPanel, BorderLayout.SOUTH);
    }

    private void clearFields() {
        nameField.setText("");
        idField.setText("");
        resultField.setText("");
        passwordField.setText("");
    }
}

public class Main {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new StudentRegistrationSystem();
            }
        });
    }
}
