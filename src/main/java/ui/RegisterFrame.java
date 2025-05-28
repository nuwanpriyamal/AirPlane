package ui;

import service.UserService;

import javax.swing.*;
import java.awt.*;

public class RegisterFrame extends JFrame {
    public RegisterFrame() {
        super("Register as Customer");
        setSize(400, 300);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(6, 2));

        JTextField usernameField = new JTextField();
        JTextField nameField = new JTextField();
        JPasswordField passwordField = new JPasswordField();
        JPasswordField confirmField = new JPasswordField();

        add(new JLabel("Username:"));
        add(usernameField);
        add(new JLabel("Full Name:"));
        add(nameField);
        add(new JLabel("Password:"));
        add(passwordField);
        add(new JLabel("Confirm Password:"));
        add(confirmField);

        JButton registerBtn = new JButton("Register");
        JButton cancelBtn = new JButton("Cancel");
        add(registerBtn);
        add(cancelBtn);

        UserService userService = new UserService();

        registerBtn.addActionListener(e -> {
            String username = usernameField.getText();
            String name = nameField.getText();
            String password = new String(passwordField.getPassword());
            String confirm = new String(confirmField.getPassword());
            if (username.isEmpty() || name.isEmpty() || password.isEmpty() || confirm.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please fill all fields.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            if (!password.equals(confirm)) {
                JOptionPane.showMessageDialog(this, "Passwords do not match.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            if (userService.getUserByUsername(username) != null) {
                JOptionPane.showMessageDialog(this, "Username already exists.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            boolean success = userService.registerCustomer(username, name, password);
            if (success) {
                JOptionPane.showMessageDialog(this, "Registration successful! You can now log in.");
                dispose();
                new LoginFrame().setVisible(true);
            } else {
                JOptionPane.showMessageDialog(this, "Registration failed. Username may already exist.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        cancelBtn.addActionListener(e -> {
            dispose();
            new LoginFrame().setVisible(true);
        });
    }
} 