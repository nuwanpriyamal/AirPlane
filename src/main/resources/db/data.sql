INSERT INTO users (username, name, password, role) VALUES
('admin', 'Alice Admin', 'changeme', 'ADMIN'),
('operator', 'Oscar Operator', 'changeme', 'OPERATOR'),
('customer1', 'Charlie Customer', 'changeme', 'CUSTOMER'),
('customer2', 'Cathy Customer', 'changeme', 'CUSTOMER');

INSERT INTO airports (code, name, location) VALUES
('CMB', 'Bandaranaike International', 'Colombo'),
('LHR', 'Heathrow', 'London'),
('JFK', 'John F. Kennedy', 'New York'),
('DXB', 'Dubai International', 'Dubai');

INSERT INTO airplanes (code, capacity_type) VALUES
('A320', 'SMALL'),
('B777', 'LARGE'),
('A350', 'MEDIUM');


INSERT INTO flights (airplane_id, origin_id, destination_id, departure_time, arrival_time) VALUES
(1, 1, 2, '2025-06-01 08:00:00', '2025-06-01 14:00:00'), -- CMB to LHR
(2, 2, 3, '2025-06-01 16:00:00', '2025-06-01 22:00:00'), -- LHR to JFK
(3, 3, 4, '2025-06-02 09:00:00', '2025-06-02 13:00:00'), -- JFK to DXB
(1, 4, 1, '2025-06-03 10:00:00', '2025-06-03 18:00:00'); -- DXB to CMB


INSERT INTO bookings (user_id, flight_id, seat_number, booking_date, service_class) VALUES
(3, 1, '12A', '2025-05-20 10:00:00', 'ECONOMY'),  -- Charlie Customer
(3, 2, '1B',  '2025-05-21 11:00:00', 'BUSINESS'), -- Charlie Customer
(4, 3, '5C',  '2025-05-22 12:00:00', 'FIRST'),    -- Cathy Customer
(4, 4, '7D',  '2025-05-23 13:00:00', 'ECONOMY');  -- Cathy Customer 