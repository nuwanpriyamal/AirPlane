-- USERS
CREATE TABLE users (
    id INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(50) NOT NULL UNIQUE,
    password VARCHAR(100) NOT NULL,
    role ENUM('CUSTOMER', 'OPERATOR', 'ADMIN') NOT NULL
);

-- AIRPORTS
CREATE TABLE airports (
    id INT AUTO_INCREMENT PRIMARY KEY,
    code VARCHAR(10) NOT NULL UNIQUE,
    name VARCHAR(100) NOT NULL,
    location VARCHAR(100) NOT NULL
);

-- AIRPLANES
CREATE TABLE airplanes (
    id INT AUTO_INCREMENT PRIMARY KEY,
    code VARCHAR(20) NOT NULL UNIQUE,
    capacity_type ENUM('SMALL', 'MEDIUM', 'LARGE') NOT NULL
);

-- FLIGHTS
CREATE TABLE flights (
    id INT AUTO_INCREMENT PRIMARY KEY,
    airplane_id INT NOT NULL,
    origin_id INT NOT NULL,
    destination_id INT NOT NULL,
    departure_time DATETIME NOT NULL,
    arrival_time DATETIME NOT NULL,
    FOREIGN KEY (airplane_id) REFERENCES airplanes(id),
    FOREIGN KEY (origin_id) REFERENCES airports(id),
    FOREIGN KEY (destination_id) REFERENCES airports(id)
);

-- BOOKINGS
CREATE TABLE bookings (
    id INT AUTO_INCREMENT PRIMARY KEY,
    user_id INT NOT NULL,
    flight_id INT NOT NULL,
    seat_number VARCHAR(10) NOT NULL,
    booking_date DATETIME NOT NULL,
    service_class ENUM('FIRST', 'BUSINESS', 'ECONOMY') NOT NULL,
    FOREIGN KEY (user_id) REFERENCES users(id),
    FOREIGN KEY (flight_id) REFERENCES flights(id)
);

ALTER TABLE users ADD COLUMN name VARCHAR(100) AFTER username;