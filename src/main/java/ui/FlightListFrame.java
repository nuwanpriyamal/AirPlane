package ui;

import dto.FlightDetailsDTO;
import service.FlightService;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class FlightListFrame extends JFrame {
    public FlightListFrame() {
        super("All Flights");
        setSize(700, 300);
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
        closeBtn.addActionListener(e -> dispose());
        JPanel btnPanel = new JPanel();
        btnPanel.add(closeBtn);
        add(btnPanel, BorderLayout.SOUTH);
    }
} 