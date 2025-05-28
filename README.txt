# Airline Ticket Booking System

## Requirements
- Java 1.8 (Tested with 1.8.0_411)
- MySQL Server (Database: airplane, User: root, Password: [empty])
- Maven
- NetBeans or IntelliJ IDEA

## Setup Instructions

1. **Clone or extract the project.**
2. **Open the project in NetBeans or IntelliJ IDEA as a Maven project.**
3. **Set up the MySQL database:**
   - Create a database named `airplane`.
   - (You can use the provided SQL scripts in `src/main/resources/db/` if available.)
   - Ensure MySQL is running on `localhost:3306`.
   - User: `root`, Password: (leave blank)
4. **Build the project using Maven.**
5. **Run the application from your IDE.**

## Notes
- All database connection settings are in `dao/DBConnection.java`.
- The project uses Java Swing for the GUI for maximum compatibility.
- For any issues, check your Java version and MySQL server status. 