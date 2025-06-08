package ui;

import dto.UserDTO;

import javax.swing.*;
import java.awt.*;

public class DashboardFrame extends JFrame {
    public DashboardFrame(UserDTO user) {
        super("Dashboard");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 200);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        JLabel welcomeLabel = new JLabel("Welcome, " + user.getUsername() + "! Role: " + user.getRole(), SwingConstants.CENTER);
        add(welcomeLabel, BorderLayout.NORTH);

        JPanel buttonPanel = new JPanel();
        JButton flightsBtn = new JButton("View Flights");
        buttonPanel.add(flightsBtn);
        flightsBtn.addActionListener(e -> new FlightListFrame(user).setVisible(true));

        if ("CUSTOMER".equals(user.getRole())) {
            JButton bookBtn = new JButton("Book Flight");
            buttonPanel.add(bookBtn);
            bookBtn.addActionListener(e -> new BookFlightFrame(user).setVisible(true));
        }

        JButton bookingsBtn = new JButton("View Bookings");
        buttonPanel.add(bookingsBtn);
        bookingsBtn.addActionListener(e -> new BookingListFrame(user).setVisible(true));

        if ("ADMIN".equals(user.getRole()) || "OPERATOR".equals(user.getRole())) {
            JButton scheduleBtn = new JButton("Schedule Flight");
            buttonPanel.add(scheduleBtn);
            scheduleBtn.addActionListener(e -> new ScheduleFlightFrame().setVisible(true));
        }

        add(buttonPanel, BorderLayout.CENTER);

        JButton logoutBtn = new JButton("Logout");
        logoutBtn.addActionListener(e -> {
            dispose();
            new LoginFrame().setVisible(true);
        });
        JPanel logoutPanel = new JPanel();
        logoutPanel.add(logoutBtn);
        add(logoutPanel, BorderLayout.SOUTH);
    }
} 