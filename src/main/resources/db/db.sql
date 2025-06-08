-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: localhost
-- Generation Time: Jun 08, 2025 at 06:37 AM
-- Server version: 10.4.28-MariaDB
-- PHP Version: 8.0.28

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `airplane`
--

-- --------------------------------------------------------

--
-- Table structure for table `airplanes`
--

CREATE TABLE `airplanes` (
  `id` int(11) NOT NULL,
  `code` varchar(20) NOT NULL,
  `capacity_type` enum('SMALL','MEDIUM','LARGE') NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `airplanes`
--

INSERT INTO `airplanes` (`id`, `code`, `capacity_type`) VALUES
(1, 'A320', 'SMALL'),
(2, 'B777', 'LARGE'),
(3, 'A350', 'MEDIUM');

-- --------------------------------------------------------

--
-- Table structure for table `airports`
--

CREATE TABLE `airports` (
  `id` int(11) NOT NULL,
  `code` varchar(10) NOT NULL,
  `name` varchar(100) NOT NULL,
  `location` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `airports`
--

INSERT INTO `airports` (`id`, `code`, `name`, `location`) VALUES
(1, 'CMB', 'Bandaranaike International', 'Colombo'),
(2, 'LHR', 'Heathrow', 'London'),
(3, 'JFK', 'John F. Kennedy', 'New York'),
(4, 'DXB', 'Dubai International', 'Dubai');

-- --------------------------------------------------------

--
-- Table structure for table `bookings`
--

CREATE TABLE `bookings` (
  `id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  `flight_id` int(11) NOT NULL,
  `seat_number` varchar(10) NOT NULL,
  `booking_date` datetime NOT NULL,
  `service_class` enum('FIRST','BUSINESS','ECONOMY') NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `bookings`
--

INSERT INTO `bookings` (`id`, `user_id`, `flight_id`, `seat_number`, `booking_date`, `service_class`) VALUES
(1, 3, 1, '12A', '2025-05-20 10:00:00', 'ECONOMY'),
(2, 3, 2, '1B', '2025-05-21 11:00:00', 'BUSINESS'),
(3, 4, 3, '5C', '2025-05-22 12:00:00', 'FIRST'),
(4, 4, 4, '7D', '2025-05-23 13:00:00', 'ECONOMY'),
(5, 6, 3, '1A', '2025-06-08 07:42:28', 'BUSINESS'),
(6, 6, 1, '1A', '2025-06-08 08:01:16', 'FIRST'),
(8, 6, 1, '1C', '2025-06-08 08:16:21', 'FIRST'),
(9, 6, 1, '1E', '2025-06-08 08:16:30', 'BUSINESS');

-- --------------------------------------------------------

--
-- Table structure for table `booking_print_history`
--

CREATE TABLE `booking_print_history` (
  `id` int(11) NOT NULL,
  `booking_id` int(11) NOT NULL,
  `printed_at` datetime NOT NULL,
  `printed_by` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `flights`
--

CREATE TABLE `flights` (
  `id` int(11) NOT NULL,
  `airplane_id` int(11) NOT NULL,
  `origin_id` int(11) NOT NULL,
  `destination_id` int(11) NOT NULL,
  `departure_time` datetime NOT NULL,
  `arrival_time` datetime NOT NULL,
  `seat_letters` varchar(10) DEFAULT 'ABCDEF',
  `rowCount` int(11) DEFAULT 30
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `flights`
--

INSERT INTO `flights` (`id`, `airplane_id`, `origin_id`, `destination_id`, `departure_time`, `arrival_time`, `seat_letters`, `rowCount`) VALUES
(1, 1, 1, 2, '2025-06-01 08:00:00', '2025-06-01 14:00:00', 'ABCDEF', 30),
(2, 2, 2, 3, '2025-06-01 16:00:00', '2025-06-01 22:00:00', 'ABCDEF', 30),
(3, 3, 3, 4, '2025-06-02 09:00:00', '2025-06-02 13:00:00', 'ABCDEF', 30),
(4, 1, 4, 1, '2025-06-03 10:00:00', '2025-06-03 18:00:00', 'ABCDEF', 30),
(5, 1, 1, 2, '2025-07-01 08:00:00', '2025-08-01 14:00:00', 'ABCDEF', 30);

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE `users` (
  `id` int(11) NOT NULL,
  `username` varchar(50) NOT NULL,
  `name` varchar(100) DEFAULT NULL,
  `password` varchar(100) NOT NULL,
  `role` enum('CUSTOMER','OPERATOR','ADMIN') NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`id`, `username`, `name`, `password`, `role`) VALUES
(1, 'admin', 'Alice Admin', '057ba03d6c44104863dc7361fe4578965d1887360f90a0895882e58a6248fc86', 'ADMIN'),
(2, 'operator', 'Oscar Operator', '057ba03d6c44104863dc7361fe4578965d1887360f90a0895882e58a6248fc86', 'OPERATOR'),
(3, 'customer1', 'Charlie Customer', '057ba03d6c44104863dc7361fe4578965d1887360f90a0895882e58a6248fc86', 'CUSTOMER'),
(4, 'customer2', 'Cathy Customer', '057ba03d6c44104863dc7361fe4578965d1887360f90a0895882e58a6248fc86', 'CUSTOMER'),
(5, 'nuwan', 'nuwanpriyamal', '057ba03d6c44104863dc7361fe4578965d1887360f90a0895882e58a6248fc86', 'CUSTOMER'),
(6, 'track', 'track', '057ba03d6c44104863dc7361fe4578965d1887360f90a0895882e58a6248fc86', 'CUSTOMER'),
(7, 'nwe', '2323', '057ba03d6c44104863dc7361fe4578965d1887360f90a0895882e58a6248fc86', 'CUSTOMER'),
(8, '2323', 'sdsd', '057ba03d6c44104863dc7361fe4578965d1887360f90a0895882e58a6248fc86', 'CUSTOMER');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `airplanes`
--
ALTER TABLE `airplanes`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `code` (`code`);

--
-- Indexes for table `airports`
--
ALTER TABLE `airports`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `code` (`code`);

--
-- Indexes for table `bookings`
--
ALTER TABLE `bookings`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `flight_id` (`flight_id`,`seat_number`),
  ADD KEY `user_id` (`user_id`);

--
-- Indexes for table `booking_print_history`
--
ALTER TABLE `booking_print_history`
  ADD PRIMARY KEY (`id`),
  ADD KEY `booking_id` (`booking_id`);

--
-- Indexes for table `flights`
--
ALTER TABLE `flights`
  ADD PRIMARY KEY (`id`),
  ADD KEY `airplane_id` (`airplane_id`),
  ADD KEY `origin_id` (`origin_id`),
  ADD KEY `destination_id` (`destination_id`);

--
-- Indexes for table `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `username` (`username`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `airplanes`
--
ALTER TABLE `airplanes`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `airports`
--
ALTER TABLE `airports`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT for table `bookings`
--
ALTER TABLE `bookings`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;

--
-- AUTO_INCREMENT for table `booking_print_history`
--
ALTER TABLE `booking_print_history`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `flights`
--
ALTER TABLE `flights`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT for table `users`
--
ALTER TABLE `users`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `bookings`
--
ALTER TABLE `bookings`
  ADD CONSTRAINT `bookings_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`),
  ADD CONSTRAINT `bookings_ibfk_2` FOREIGN KEY (`flight_id`) REFERENCES `flights` (`id`);

--
-- Constraints for table `booking_print_history`
--
ALTER TABLE `booking_print_history`
  ADD CONSTRAINT `booking_print_history_ibfk_1` FOREIGN KEY (`booking_id`) REFERENCES `bookings` (`id`);

--
-- Constraints for table `flights`
--
ALTER TABLE `flights`
  ADD CONSTRAINT `flights_ibfk_1` FOREIGN KEY (`airplane_id`) REFERENCES `airplanes` (`id`),
  ADD CONSTRAINT `flights_ibfk_2` FOREIGN KEY (`origin_id`) REFERENCES `airports` (`id`),
  ADD CONSTRAINT `flights_ibfk_3` FOREIGN KEY (`destination_id`) REFERENCES `airports` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
