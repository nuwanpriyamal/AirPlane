package ui;

import dto.AirplaneDTO;
import dto.AirportDTO;
import dto.FlightDTO;
import service.AirplaneService;
import service.AirportService;
import service.FlightService;

import javax.swing.*;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class ScheduleFlightFrame extends JFrame {
    public ScheduleFlightFrame() {
        super("Schedule New Flight");
        setSize(500, 350);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(7, 2));

        AirplaneService airplaneService = new AirplaneService();
        AirportService airportService = new AirportService();
        List<AirplaneDTO> airplanes = airplaneService.getAllAirplanes();
        List<AirportDTO> airports = airportService.getAllAirports();

        JComboBox<AirplaneDTO> airplaneCombo = new JComboBox<>();
        for (AirplaneDTO a : airplanes) airplaneCombo.addItem(a);
        JComboBox<AirportDTO> originCombo = new JComboBox<>();
        JComboBox<AirportDTO> destCombo = new JComboBox<>();
        for (AirportDTO ap : airports) {
            originCombo.addItem(ap);
            destCombo.addItem(ap);
        }
        JTextField depField = new JTextField("2025-06-01 08:00:00");
        JTextField arrField = new JTextField("2025-06-01 14:00:00");

        add(new JLabel("Airplane:")); add(airplaneCombo);
        add(new JLabel("Origin Airport:")); add(originCombo);
        add(new JLabel("Destination Airport:")); add(destCombo);
        add(new JLabel("Departure (yyyy-MM-dd HH:mm:ss):")); add(depField);
        add(new JLabel("Arrival (yyyy-MM-dd HH:mm:ss):")); add(arrField);

        JButton scheduleBtn = new JButton("Schedule");
        JButton cancelBtn = new JButton("Cancel");
        add(scheduleBtn); add(cancelBtn);

        scheduleBtn.addActionListener(e -> {
            try {
                AirplaneDTO airplane = (AirplaneDTO) airplaneCombo.getSelectedItem();
                AirportDTO origin = (AirportDTO) originCombo.getSelectedItem();
                AirportDTO dest = (AirportDTO) destCombo.getSelectedItem();
                String depStr = depField.getText();
                String arrStr = arrField.getText();
                if (airplane == null || origin == null || dest == null || depStr.isEmpty() || arrStr.isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Please fill all fields.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                if (origin.getId() == dest.getId()) {
                    JOptionPane.showMessageDialog(this, "Origin and destination cannot be the same.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                Date dep = sdf.parse(depStr);
                Date arr = sdf.parse(arrStr);
                if (!arr.after(dep)) {
                    JOptionPane.showMessageDialog(this, "Arrival must be after departure.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                FlightService flightService = new FlightService();
                if (!flightService.isAirplaneAvailable(airplane.getId(), dep, arr)) {
                    JOptionPane.showMessageDialog(this, "This airplane is already scheduled for another flight during this time.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                FlightDTO flight = new FlightDTO(0, airplane.getId(), origin.getId(), dest.getId(), dep, arr);
                boolean success = flightService.addFlight(flight);
                if (success) {
                    JOptionPane.showMessageDialog(this, "Flight scheduled successfully!");
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(this, "Failed to schedule flight.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Invalid date/time format.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
        cancelBtn.addActionListener(e -> dispose());
    }

    public ScheduleFlightFrame(int flightId) {
        super("Edit Flight");
        setSize(500, 350);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(7, 2));

        AirplaneService airplaneService = new AirplaneService();
        AirportService airportService = new AirportService();
        FlightService flightService = new FlightService();
        List<AirplaneDTO> airplanes = airplaneService.getAllAirplanes();
        List<AirportDTO> airports = airportService.getAllAirports();
        FlightDTO flight = flightService.getFlightById(flightId);

        JComboBox<AirplaneDTO> airplaneCombo = new JComboBox<>();
        for (AirplaneDTO a : airplanes) airplaneCombo.addItem(a);
        JComboBox<AirportDTO> originCombo = new JComboBox<>();
        JComboBox<AirportDTO> destCombo = new JComboBox<>();
        for (AirportDTO ap : airports) {
            originCombo.addItem(ap);
            destCombo.addItem(ap);
        }
        JTextField depField = new JTextField(new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(flight.getDepartureTime()));
        JTextField arrField = new JTextField(new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(flight.getArrivalTime()));

        // Set selected items
        for (int i = 0; i < airplaneCombo.getItemCount(); i++) {
            if (airplaneCombo.getItemAt(i).getId() == flight.getAirplaneId()) {
                airplaneCombo.setSelectedIndex(i);
                break;
            }
        }
        for (int i = 0; i < originCombo.getItemCount(); i++) {
            if (originCombo.getItemAt(i).getId() == flight.getOriginId()) {
                originCombo.setSelectedIndex(i);
                break;
            }
        }
        for (int i = 0; i < destCombo.getItemCount(); i++) {
            if (destCombo.getItemAt(i).getId() == flight.getDestinationId()) {
                destCombo.setSelectedIndex(i);
                break;
            }
        }

        add(new JLabel("Airplane:")); add(airplaneCombo);
        add(new JLabel("Origin Airport:")); add(originCombo);
        add(new JLabel("Destination Airport:")); add(destCombo);
        add(new JLabel("Departure (yyyy-MM-dd HH:mm:ss):")); add(depField);
        add(new JLabel("Arrival (yyyy-MM-dd HH:mm:ss):")); add(arrField);

        JButton updateBtn = new JButton("Update");
        JButton cancelBtn = new JButton("Cancel");
        add(updateBtn); add(cancelBtn);

        updateBtn.addActionListener(e -> {
            try {
                AirplaneDTO airplane = (AirplaneDTO) airplaneCombo.getSelectedItem();
                AirportDTO origin = (AirportDTO) originCombo.getSelectedItem();
                AirportDTO dest = (AirportDTO) destCombo.getSelectedItem();
                String depStr = depField.getText();
                String arrStr = arrField.getText();
                if (airplane == null || origin == null || dest == null || depStr.isEmpty() || arrStr.isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Please fill all fields.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                if (origin.getId() == dest.getId()) {
                    JOptionPane.showMessageDialog(this, "Origin and destination cannot be the same.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                java.util.Date dep = sdf.parse(depStr);
                java.util.Date arr = sdf.parse(arrStr);
                if (!arr.after(dep)) {
                    JOptionPane.showMessageDialog(this, "Arrival must be after departure.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                if (!flightService.isAirplaneAvailable(airplane.getId(), dep, arr)) {
                    JOptionPane.showMessageDialog(this, "This airplane is already scheduled for another flight during this time.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                FlightDTO updatedFlight = new FlightDTO(flightId, airplane.getId(), origin.getId(), dest.getId(), dep, arr);
                boolean success = flightService.updateFlight(updatedFlight);
                if (success) {
                    JOptionPane.showMessageDialog(this, "Flight updated successfully!");
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(this, "Failed to update flight.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Invalid date/time format.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
        cancelBtn.addActionListener(e -> dispose());
    }
} 