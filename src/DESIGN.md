
Airline Ticket Booking System – Project Documentation
1. System Design Overview
Architecture
Layered Architecture: The system is organized into clear layers:
UI Layer (ui/): Java Swing-based graphical user interface for all user interactions.
Service Layer (service/): Contains business logic and validation.
DAO Layer (dao/): Handles all database operations (CRUD) using JDBC.
DTO Layer (dto/): Data Transfer Objects for moving data between layers.
Model Layer (model/): Core domain entities representing database tables.
Main Components
User Management: Registration, login, and role-based access (Customer, Operator, Admin).
Flight Management: Scheduling, listing, and managing flights.
Booking System: Book, view, and manage flight bookings.
Database: MySQL, with schema and sample data in src/main/resources/db/db.sql.
Entry Point
The application starts from the ui.Main class (or ui.LoginFrame if Main is deleted), which launches the login window.
2. Test Coverage & Example Test Cases
Manual Test Cases
User Registration & Login
TC1: Register a new customer with valid details → Success message, user can log in.
TC2: Register with an existing username → Error message.
TC3: Login with correct credentials → Dashboard appears.
TC4: Login with incorrect credentials → Error message.
Booking Flights
TC5: Book a flight as a customer, select available seat → Booking successful.
TC6: Attempt to book an already booked seat → Error message.
TC7: View all bookings as a customer → List displays only user’s bookings.
Admin/Operator Functions
TC8: Admin schedules a new flight → Flight appears in flight list.
TC9: Operator views all bookings → All bookings are listed.
Security
TC10: Passwords are not stored in plain text in the database.
TC11: SQL injection attempts in login/registration fields are handled safely.
Automated Testing
The system is primarily GUI-based and uses JDBC, so automated unit tests are limited.
For future improvement, consider using JUnit for service and DAO layer testing.
3. Security & Password Handling
Password Storage:
Passwords are hashed using SHA-256 before being stored in the database.
See service/PasswordUtil.java for the hashing implementation.
Database Credentials:
Stored in dao/DBConnection.java. For demo purposes, the username is root and the password is empty.
Important: Update these credentials for production use.
SQL Injection Protection:
All database queries use PreparedStatement to prevent SQL injection.
Role-Based Access:
The system restricts access to certain features based on user roles (Customer, Operator, Admin).
4. Other Important Factors
Dependencies:
Java 1.8
MySQL Server
Maven (for build and dependency management)
MySQL Connector/J (included via Maven)
Build & Packaging:
The project uses Maven Shade plugin to create a runnable JAR with all dependencies.
The package structure is preserved in the JAR.
Database Scripts:
All schema and sample data are in src/main/resources/db/db.sql.
Known Limitations:
No automated test suite for GUI.
No password reset functionality.
Database credentials are hardcoded for demo.
5. Recommendations for Future Improvements
Add automated unit and integration tests (using JUnit and H2 for in-memory DB).
Implement password reset and email verification.
Externalize database configuration (use a properties file).
Improve error handling and user feedback in the UI.
Add logging for audit and debugging.
Prepared by:
GSCOMP269_Priyamal G.A.N.
