package ui;

import dto.FlightDTO;
import dto.BookingWithCustomerDTO;
import service.FlightService;
import service.BookingService;
import service.AirplaneService;
import service.AirportService;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class FlightDetailsFrame extends JFrame {
    public FlightDetailsFrame(int flightId) {
        super("Flight Details");
        setSize(600, 400);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        FlightService flightService = new FlightService();
        FlightDTO flight = flightService.getFlightById(flightId);

        AirplaneService airplaneService = new AirplaneService();
        AirportService airportService = new AirportService();
        String airplaneCode = "";
        String originName = "";
        String destName = "";
        try {
            airplaneCode = airplaneService.getAirplaneById(flight.getAirplaneId()).getCode();
            originName = airportService.getAirportById(flight.getOriginId()).getName();
            destName = airportService.getAirportById(flight.getDestinationId()).getName();
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }

        JPanel infoPanel = new JPanel(new GridLayout(0, 2));
        infoPanel.add(new JLabel("Flight ID:"));
        infoPanel.add(new JLabel(String.valueOf(flight.getId())));
        infoPanel.add(new JLabel("Airplane:"));
        infoPanel.add(new JLabel(airplaneCode));
        infoPanel.add(new JLabel("Origin Airport:"));
        infoPanel.add(new JLabel(originName));
        infoPanel.add(new JLabel("Destination Airport:"));
        infoPanel.add(new JLabel(destName));
        infoPanel.add(new JLabel("Departure Time:"));
        infoPanel.add(new JLabel(String.valueOf(flight.getDepartureTime())));
        infoPanel.add(new JLabel("Arrival Time:"));
        infoPanel.add(new JLabel(String.valueOf(flight.getArrivalTime())));
        add(infoPanel, BorderLayout.NORTH);

        BookingService bookingService = new BookingService();
        List<BookingWithCustomerDTO> bookings = bookingService.getAllBookingsWithCustomerName();
        String[] columns = {"Booking ID", "Customer Name", "Seat Number", "Service Class", "Booking Date"};
        DefaultTableModel model = new DefaultTableModel(columns, 0);
        for (BookingWithCustomerDTO b : bookings) {
            if (b.getFlightId() == flightId) {
                model.addRow(new Object[]{
                        b.getId(),
                        b.getCustomerName(),
                        b.getSeatNumber(),
                        b.getServiceClass(),
                        b.getBookingDate()
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