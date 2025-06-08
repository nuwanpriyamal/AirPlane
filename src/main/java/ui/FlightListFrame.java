package ui;

import dto.FlightDetailsDTO;
import service.FlightService;
import service.UserService;
import dto.UserDTO;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class FlightListFrame extends JFrame {
    public FlightListFrame(UserDTO currentUser) {
        super("All Flights");
        setSize(700, 350);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        FlightService flightService = new FlightService();
        List<FlightDetailsDTO> flights = flightService.getAllFlightsWithDetails();

        String[] columns = {"ID", "Airplane", "Origin", "Destination", "Departure", "Arrival"};
        DefaultTableModel model = new DefaultTableModel(columns, 0);
        if (flights != null) {
            for (FlightDetailsDTO f : flights) {
                model.addRow(new Object[]{
                        f.getId(),
                        f.getAirplaneCode(),
                        f.getOriginName(),
                        f.getDestinationName(),
                        f.getDepartureTime(),
                        f.getArrivalTime()
                });
            }
        }
        JTable table = new JTable(model);
        add(new JScrollPane(table), BorderLayout.CENTER);

        JButton closeBtn = new JButton("Close");
        JButton detailsBtn = new JButton("View Details");
        JPanel btnPanel = new JPanel();
        btnPanel.add(detailsBtn);
        if (currentUser != null && ("ADMIN".equals(currentUser.getRole()) || "OPERATOR".equals(currentUser.getRole()))) {
            JButton editBtn = new JButton("Edit");
            JButton deleteBtn = new JButton("Delete");
            btnPanel.add(editBtn);
            btnPanel.add(deleteBtn);
            editBtn.addActionListener(e -> {
                int row = table.getSelectedRow();
                if (row == -1) {
                    JOptionPane.showMessageDialog(this, "Please select a flight.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                int flightId = (int) model.getValueAt(row, 0);
                new ScheduleFlightFrame(flightId).setVisible(true);
            });
            deleteBtn.addActionListener(e -> {
                int row = table.getSelectedRow();
                if (row == -1) {
                    JOptionPane.showMessageDialog(this, "Please select a flight.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                int flightId = (int) model.getValueAt(row, 0);
                int confirm = JOptionPane.showConfirmDialog(this, "Are you sure you want to delete this flight?", "Confirm Delete", JOptionPane.YES_NO_OPTION);
                if (confirm == JOptionPane.YES_OPTION) {
                    if (flightService.deleteFlight(flightId)) {
                        JOptionPane.showMessageDialog(this, "Flight deleted.");
                        dispose();
                        new FlightListFrame(currentUser).setVisible(true);
                    } else {
                        JOptionPane.showMessageDialog(this, "Failed to delete flight.", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
            });
        }
        btnPanel.add(closeBtn);
        add(btnPanel, BorderLayout.SOUTH);

        detailsBtn.addActionListener(e -> {
            int row = table.getSelectedRow();
            if (row == -1) {
                JOptionPane.showMessageDialog(this, "Please select a flight.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            int flightId = (int) model.getValueAt(row, 0);
            new FlightDetailsFrame(flightId).setVisible(true);
        });

        closeBtn.addActionListener(e -> dispose());
    }
} 