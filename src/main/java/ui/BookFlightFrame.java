package ui;

import dto.BookingDTO;
import dto.FlightDTO;
import dto.UserDTO;
import service.BookingService;
import service.FlightService;

import javax.swing.*;
import java.awt.*;
import java.util.Date;
import java.util.List;

public class BookFlightFrame extends JFrame {
    public BookFlightFrame(UserDTO user) {
        super("Book a Flight");
        setSize(500, 250);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(5, 2));

        FlightService flightService = new FlightService();
        List<FlightDTO> flights = flightService.getAllFlights();
        JComboBox<FlightDTO> flightCombo = new JComboBox<>();
        for (FlightDTO f : flights) {
            flightCombo.addItem(f);
        }
        add(new JLabel("Select Flight:"));
        add(flightCombo);

        JTextField seatField = new JTextField();
        add(new JLabel("Seat Number:"));
        add(seatField);

        JComboBox<String> classCombo = new JComboBox<>(new String[]{"FIRST", "BUSINESS", "ECONOMY"});
        add(new JLabel("Service Class:"));
        add(classCombo);

        JButton bookBtn = new JButton("Book");
        add(new JLabel());
        add(bookBtn);

        bookBtn.addActionListener(e -> {
            FlightDTO selectedFlight = (FlightDTO) flightCombo.getSelectedItem();
            String seat = seatField.getText();
            String serviceClass = (String) classCombo.getSelectedItem();
            if (selectedFlight == null || seat.isEmpty() || serviceClass == null) {
                JOptionPane.showMessageDialog(this, "Please fill all fields.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            BookingDTO booking = new BookingDTO(0, user.getId(), selectedFlight.getId(), seat, new Date(), serviceClass);
            BookingService bookingService = new BookingService();
            boolean success = bookingService.addBooking(booking);
            if (success) {
                JOptionPane.showMessageDialog(this, "Booking successful!");
                dispose();
            } else {
                JOptionPane.showMessageDialog(this, "Booking failed.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
    }
} 