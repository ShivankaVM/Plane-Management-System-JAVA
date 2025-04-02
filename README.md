# Plane Seat Reservation System

## Overview
The **Plane Seat Reservation System** is a Java-based application designed to manage airplane seat bookings. It allows users to reserve and cancel seats, find available seats, search for reserved tickets, view seating arrangements, and print tickets to a text file.

## Features
- **Reserve Plane Seats**: Users can book a seat by entering the seat number.
- **Cancel Seat Reservation**: Allows users to cancel their booked tickets.
- **Find First Available Seat**: The system searches for the first unreserved seat.
- **Search for Reserved Tickets**: Users can look up specific ticket bookings.
- **View Seat Availability**: Provides a seating chart showing reserved and available seats.
- **Print Ticket**: Generates a text file with ticket details for reference.

## Technologies Used
- **Java**: Core programming language.
- **Object-Oriented Programming (OOP)**: Implements `Person` and `Ticket` classes.
- **Arrays**: Used for seat storage and management.
- **File Handling**: Exports ticket details to a text file.
- **Sorting & Searching Algorithms**: Used for ticket lookups.

## Installation & Execution
### Prerequisites
- Java Development Kit (JDK) installed (Java 8 or later)
- Any Java-compatible IDE (Eclipse, IntelliJ IDEA, or VS Code) or command-line execution

### Steps to Run
1. **Clone the Repository** (if using version control)
   ```sh
   git clone https://github.com/your-repository/plane-reservation.git
   ```
2. **Navigate to the Project Directory**
   ```sh
   cd plane-reservation
   ```
3. **Compile the Java Program**
   ```sh
   javac PlaneReservationSystem.java
   ```
4. **Run the Program**
   ```sh
   java PlaneReservationSystem
   ```

## Usage
Once the program starts, a menu-driven interface will appear. Users can:
1. **Reserve a Seat**: Enter a seat number to book it.
2. **Cancel a Seat**: Enter the seat number to remove a booking.
3. **Find First Available Seat**: Automatically assigns the first empty seat.
4. **Search for a Ticket**: Look up reservations.
5. **Display Seating Chart**: Shows occupied and available seats.
6. **Print a Ticket**: Saves a ticket confirmation as a text file.
7. **Exit**: Closes the program.

## File Output
- Tickets are saved in the format: `ticket_<seat_number>.txt`.
- The text file contains passenger details, seat number, and booking confirmation.

## Future Enhancements
- Implement a GUI for a better user experience.
- Store seat reservations in a database instead of an array.
- Enable online reservations via a web interface.

## Author
- Shivanka Maddumarachchi (University Coursework)

## License
This project is for educational purposes only and follows no specific licensing.

