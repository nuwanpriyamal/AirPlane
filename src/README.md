
GSCOMP269_Priyamal G.A.N.

# Airline Ticket Booking System

This directory contains the source code for a Java Swing-based Airline Ticket Booking System. The application allows users to register, log in, book flights, and manage bookings, with roles for customers, operators, and admins.

## Directory Structure

- `main/java/`
  - `model/`   - Core domain entities (e.g., User, Flight, Booking, Airport, Airplane)
  - `dao/`     - Data Access Objects for database operations (e.g., BookingDAO, UserDAO, DBConnection)
  - `dto/`     - Data Transfer Objects for passing data between layers
  - `service/` - Business logic and application services (e.g., BookingService, UserService)
  - `ui/`      - Java Swing GUI components (e.g., LoginFrame, DashboardFrame, BookFlightFrame)
- `main/resources/`
  - `db/`      - Database schema and sample data scripts (`db.sql`), and a README for DB scripts

## Key Features

- **User Registration & Login:** Customers can register and log in. Admins and operators have special roles.
- **Flight Management:** View, schedule, and manage flights.
- **Booking System:** Book flights, select seats, and manage bookings.
- **Role-Based Dashboard:** Different UI options for customers, operators, and admins.
- **Database Integration:** Uses MySQL for persistent storage.
- **Password Security:** Passwords are hashed using SHA-256 before storage.

## Setup Instructions

1. **Requirements:**
   - Java 1.8
   - MySQL Server (Database: `airplane`, User: `root`, Password: [empty])
   - Maven
   - NetBeans or IntelliJ IDEA

2. **Database Setup:**
   - Create a MySQL database named `airplane`.
   - Use the provided SQL script in `src/main/resources/db/db.sql` to create tables and insert sample data.
   - Ensure MySQL is running on `localhost:3306` with user `root` and no password (see `dao/DBConnection.java`).

3. **Build & Run:**
   - Open the project as a Maven project in your IDE.
   - Build the project using Maven.
   - Run the application. The main entry point is `ui.LoginFrame` (run its `main` method).

## Project Structure Overview

- **Model Layer (`model/`):** Contains POJOs representing database entities.
- **DAO Layer (`dao/`):** Handles all database CRUD operations. `DBConnection.java` manages the MySQL connection.
- **DTO Layer (`dto/`):** Used for transferring data between layers, especially for UI and service communication.
- **Service Layer (`service/`):** Contains business logic, validation, and coordination between DAOs and UI.
- **UI Layer (`ui/`):** Java Swing forms and frames for user interaction (login, registration, booking, admin dashboard, etc.).

## Security Notes
- Passwords are hashed using SHA-256 before being stored in the database (`service/PasswordUtil.java`).
- Database credentials are hardcoded for demo purposes; update `dao/DBConnection.java` for production use.

## Additional Notes
- All database scripts and a brief DB README are in `src/main/resources/db/`.
- For any issues, check your Java version and MySQL server status.

---


******
   - Open the project as a Maven project in your IDE.
   - Build the project using Maven.
   - Run the application. The main entry point is `ui.LoginFrame` (run its `main` method).

*******

------
GSCOMP269_Priyamal G.A.N.