package ui;

import dto.BookingDTO;
import dto.UserDTO;
import dto.BookingWithCustomerDTO;
import service.BookingService;
import service.UserService;
import service.FlightService;
import dto.FlightDTO;
import service.AirportService;
import dto.AirportDTO;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class BookingListFrame extends JFrame {
    public BookingListFrame(UserDTO user) {
        super("All Bookings");
        setSize(700, 300);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        BookingService bookingService = new BookingService();
        UserService userService = new UserService();
        FlightService flightService = new FlightService();
        AirportService airportService = new AirportService();
        List<BookingWithCustomerDTO> bookings = bookingService.getAllBookingsWithCustomerName();

        String[] columns = {"ID", "User ID", "Customer Name", "Flight", "Origin", "Destination", "Departure", "Seat Number", "Booking Date", "Service Class"};
        DefaultTableModel model = new DefaultTableModel(columns, 0);
        if (bookings != null) {
            for (BookingWithCustomerDTO b : bookings) {
                if ("CUSTOMER".equals(user.getRole()) && b.getUserId() != user.getId()) continue;
                FlightDTO flight = flightService.getFlightById(b.getFlightId());
                String origin = "";
                String dest = "";
                String dep = "";
                if (flight != null) {
                    AirportDTO originAirport = airportService.getAirportById(flight.getOriginId());
                    AirportDTO destAirport = airportService.getAirportById(flight.getDestinationId());
                    origin = originAirport != null ? originAirport.getName() : "";
                    dest = destAirport != null ? destAirport.getName() : "";
                    dep = String.valueOf(flight.getDepartureTime());
                }
                String seatClass = b.getServiceClass();
                if (seatClass != null && !seatClass.isEmpty()) {
                    seatClass = seatClass.substring(0,1).toUpperCase() + seatClass.substring(1).toLowerCase();
                }
                model.addRow(new Object[]{
                        b.getId(),
                        b.getUserId(),
                        b.getCustomerName(),
                        b.getFlightId(),
                        origin,
                        dest,
                        dep,
                        b.getSeatNumber(),
                        b.getBookingDate(),
                        seatClass
                });
            }
        }
        JTable table = new JTable(model);
        add(new JScrollPane(table), BorderLayout.CENTER);

        JButton closeBtn = new JButton("Close");
        JButton editBtn = new JButton("Edit");
        JButton deleteBtn = new JButton("Delete");
        JButton createBtn = new JButton("Create Booking");
        JPanel btnPanel = new JPanel();
        btnPanel.add(editBtn);
        btnPanel.add(deleteBtn);
        btnPanel.add(closeBtn);
        if ("ADMIN".equals(user.getRole())) {
            btnPanel.add(createBtn);
            createBtn.addActionListener(e -> {
                new CreateBookingFrame(user).setVisible(true);
                dispose();
            });
        }
        add(btnPanel, BorderLayout.SOUTH);

        editBtn.addActionListener(e -> {
            int row = table.getSelectedRow();
            if (row == -1) {
                JOptionPane.showMessageDialog(this, "Please select a booking.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            int bookingId = (int) model.getValueAt(row, 0);
            int userId = (int) model.getValueAt(row, 1);
            if ("CUSTOMER".equals(user.getRole()) && user.getId() != userId) {
                JOptionPane.showMessageDialog(this, "You can only edit your own bookings.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            int confirm = JOptionPane.showConfirmDialog(this, "Are you sure you want to edit this booking?", "Confirm Edit", JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                new EditBookingFrame(bookingId, user).setVisible(true);
                dispose();
            }
        });

        deleteBtn.addActionListener(e -> {
            int row = table.getSelectedRow();
            if (row == -1) {
                JOptionPane.showMessageDialog(this, "Please select a booking.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            int bookingId = (int) model.getValueAt(row, 0);
            int userId = (int) model.getValueAt(row, 1);
            if ("CUSTOMER".equals(user.getRole()) && user.getId() != userId) {
                JOptionPane.showMessageDialog(this, "You can only delete your own bookings.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            int confirm = JOptionPane.showConfirmDialog(this, "Are you sure you want to delete this booking?", "Confirm Delete", JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                boolean success = bookingService.deleteBooking(bookingId);
                if (success) {
                    JOptionPane.showMessageDialog(this, "Booking deleted.");
                    dispose();
                    new BookingListFrame(user).setVisible(true);
                } else {
                    JOptionPane.showMessageDialog(this, "Failed to delete booking.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        closeBtn.addActionListener(e -> dispose());
    }
} 