package ui;

import dto.BookingDTO;
import dto.UserDTO;
import dto.BookingWithCustomerDTO;
import service.BookingService;
import service.UserService;

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
        List<BookingWithCustomerDTO> bookings = bookingService.getAllBookingsWithCustomerName();

        String[] columns = {"ID", "User ID", "Customer Name", "Flight ID", "Seat Number", "Booking Date", "Service Class"};
        DefaultTableModel model = new DefaultTableModel(columns, 0);
        if (bookings != null) {
            for (BookingWithCustomerDTO b : bookings) {
                if ("CUSTOMER".equals(user.getRole()) && b.getUserId() != user.getId()) continue;
                model.addRow(new Object[]{
                        b.getId(),
                        b.getUserId(),
                        b.getCustomerName(),
                        b.getFlightId(),
                        b.getSeatNumber(),
                        b.getBookingDate(),
                        b.getServiceClass()
                });
            }
        }
        JTable table = new JTable(model);
        add(new JScrollPane(table), BorderLayout.CENTER);

        JButton closeBtn = new JButton("Close");
        closeBtn.addActionListener(e -> dispose());
        JPanel btnPanel = new JPanel();
        btnPanel.add(closeBtn);
        add(btnPanel, BorderLayout.SOUTH);
    }
} 