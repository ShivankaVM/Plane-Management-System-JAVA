import java.util.Scanner;

public class PlaneManagement {
    private static final int NUM_ROWS = 4;
    private static final int[] NUM_SEATS_PER_ROW = {14, 12, 12, 14};
    private static final char[] ROW_LABELS = {'A', 'B', 'C', 'D'};
    private static final int[][] SEATS = new int[NUM_ROWS][];

    private static final String[][] Tickets_Row_A = new String[14][6];
    private static final String[][] Tickets_Row_B = new String[12][6];1
    private static final String[][] Tickets_Row_C = new String[12][6];
    private static final String[][] Tickets_Row_D = new String[14][6];

    public static String Name;
    public static String Surname;
    public static String Email;

    static {// Initialize the seats array
        for (int i = 0; i < NUM_ROWS; i++) {
            SEATS[i] = new int[NUM_SEATS_PER_ROW[i]];
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("\nWelcome to the Plane Management application.\n");
        int choice;
        do {
            //display the Menu
            System.out.println("\n **************************************************");
            System.out.println("\n *               MENU OPTIONS                     *");
            System.out.println("\n **************************************************");
            System.out.println("\n 1) Buy a seat ");
            System.out.println("\n 2) Cancel a seat ");
            System.out.println("\n 3) Find first available seat ");
            System.out.println("\n 4) Show seating plan ");
            System.out.println("\n 5) Print tickets information and total sales ");
            System.out.println("\n 6) Search ticket ");
            System.out.println("\n 0) Quit ");
            System.out.println("\n **************************************************");
            System.out.println("\n Please select an option:");

            choice = scanner.nextInt(); //Reading user input

            switch (choice) {
                case 1:
                    buy_seat();
                    break;
                case 2:
                    cancel_seat();
                    break;
                case 3:
                    find_first_available();
                    break;
                case 4:
                    show_seating_plan();
                    break;
                case 5:
                    print_tickets_info();
                    break;
                case 6:
                    search_ticket();
                    break;
                case 0:
                    System.out.println("Terminating the program.");
                    break;
                default:
                    System.out.println("Invalid input. Please try again.");
            }
        } while (choice != 0);
    }

    public static void buy_seat() {
        Scanner scanner = new Scanner(System.in);// Ask the user to input row letter and seat number
        System.out.print("Enter row letter: ");
        char rowLetter = scanner.next().toUpperCase().charAt(0); // Convert to uppercase for consistency
        System.out.print("Enter seat number (1-" + NUM_SEATS_PER_ROW[rowIndex(rowLetter)] + "): ");
        int seatNumber = scanner.nextInt();

        // Check if the row and seat entered are valid
        if (!isValidRow(rowLetter) || !isValidSeat(rowLetter, seatNumber)) {
            System.out.println("Invalid row or seat number. Please try again.");
            return;
        }
        // Check if the seat is available
        if (SEATS[rowIndex(rowLetter)][seatNumber - 1] == 1) {
            System.out.println("Seat already booked. Please select another seat.");
            return;
        }
        // Record the seat as booked
        SEATS[rowIndex(rowLetter)][seatNumber - 1] = 1;
        System.out.println("Seat " + rowLetter + seatNumber + " successfully booked.");
        User_info();
        Ticket ticket = new Ticket();
        ticket.setRow(String.valueOf(rowLetter));
        ticket.setSeat(seatNumber);
        ticket.Set_Price(seatNumber);
        ticket.setPerson(new Person(Name, Surname, Email));
        append_to_array(rowLetter, seatNumber, ticket);
    }

    public static void cancel_seat() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter row letter (A-D): ");
        char rowLetter = scanner.next().toUpperCase().charAt(0); // Convert to uppercase for consistency
        System.out.print("Enter seat number (1-" + NUM_SEATS_PER_ROW[rowIndex(rowLetter)] + "): ");
        int seatNumber = scanner.nextInt();

        // Check if the row and seat entered are valid
        if (!isValidRow(rowLetter) || !isValidSeat(rowLetter, seatNumber)) {
            System.out.println("Invalid row or seat number. Please try again.");
            return;
        }
        // Check if the seat is already available
        if (SEATS[rowIndex(rowLetter)][seatNumber - 1] == 0) {
            System.out.println("Seat is already available. No reservation to cancel.");
            return;
        }
        // Record the seat as available
        SEATS[rowIndex(rowLetter)][seatNumber - 1] = 0;
        // Mark the corresponding ticket as canceled
        switch (rowIndex(rowLetter)) {
            case 0:
                Tickets_Row_A[seatNumber - 1] = new String[6]; // Set the ticket information to null
                break;
            case 1:
                Tickets_Row_B[seatNumber - 1] = new String[6]; // Set the ticket information to null
                break;
            case 2:
                Tickets_Row_C[seatNumber - 1] = new String[6]; // Set the ticket information to null
                break;
            case 3:
                Tickets_Row_D[seatNumber - 1] = new String[6]; // Set the ticket information to null
                break;
        }
        System.out.println("Seat " + rowLetter + seatNumber + " reservation successfully cancelled.");
    }


    public static void find_first_available() {//Find the first available seat
        for (int i = 0; i < NUM_ROWS; i++) {
            char rowLetter = ROW_LABELS[i];
            int numSeatsInRow = NUM_SEATS_PER_ROW[i];

            for (int j = 0; j < numSeatsInRow; j++) {
                if (SEATS[i][j] == 0) {
                    System.out.println("First available seat found: " + rowLetter + (j + 1));
                    return; // Return as soon as the first available seat is found
                }
            }
        }
        System.out.println("No available seats found.");// If no available seat is found

    }

    public static void show_seating_plan() {//Display the seating plan
        System.out.println("Seating Plan:");

        for (int i = 0; i < NUM_ROWS; i++) {
            int numSeatsInRow = NUM_SEATS_PER_ROW[i];

            System.out.print(" ");

            for (int j = 0; j < numSeatsInRow; j++) {
                if (SEATS[i][j] == 0) {
                    System.out.print("O ");
                } else {
                    System.out.print("X ");
                }
            }
            System.out.println(); // Move to the next line after printing seats in a row
        }
    }

    public static void append_to_array(char rowLetter, int seatNumber, Ticket ticket) {
        int Index = rowIndex(rowLetter);
        switch (Index) {
            case 0:
                Tickets_Row_A[seatNumber - 1] = new String[]{ticket.getPerson().getName(), ticket.getPerson().getSurname(), ticket.getPerson().getEmail(), String.valueOf(seatNumber), String.valueOf(rowLetter), String.valueOf(ticket.getPrice())};
                break;
            case 1:
                Tickets_Row_B[seatNumber - 1] = new String[]{ticket.getPerson().getName(), ticket.getPerson().getSurname(), ticket.getPerson().getEmail(),
                        String.valueOf(seatNumber), String.valueOf(rowLetter), String.valueOf(ticket.getPrice())};
                break;
            case 2:
                Tickets_Row_C[seatNumber - 1] = new String[]{ticket.getPerson().getName(), ticket.getPerson().getSurname(), ticket.getPerson().getEmail(), String.valueOf(seatNumber), String.valueOf(rowLetter), String.valueOf(ticket.getPrice())};
                break;
            case 3:
                Tickets_Row_D[seatNumber - 1] = new String[]{ticket.getPerson().getName(), ticket.getPerson().getSurname(), ticket.getPerson().getEmail(), String.valueOf(seatNumber), String.valueOf(rowLetter), String.valueOf(ticket.getPrice())};
                break;
        }
    }

    public static void search_ticket() {
        Scanner scanner = new Scanner(System.in);// Ask the user to input row letter and seat number
        System.out.print("Enter row letter: ");
        char rowLetter = scanner.next().toUpperCase().charAt(0); // Convert to uppercase for consistency
        System.out.print("Enter seat number (1-" + NUM_SEATS_PER_ROW[rowIndex(rowLetter)] + "): ");
        int seatNumber = scanner.nextInt();

        // Check if the row and seat entered are valid
        if (!isValidRow(rowLetter) || !isValidSeat(rowLetter, seatNumber)) {
            System.out.println("Invalid row or seat number. Please try again.");
            return;
        }
        // Check if the seat is available
        if (SEATS[rowIndex(rowLetter)][seatNumber - 1] == 1) {
            System.out.println("Seat already booked. Please select another seat.");
            return;
        }
        int index = rowIndex(rowLetter);
        if (index == 0) {
            if (Tickets_Row_A[seatNumber-1][0]!= null) {
                System.out.println("Name: " + Tickets_Row_A[seatNumber - 1][0] + " " + Tickets_Row_A[seatNumber - 1][1]);
                System.out.println("Email: " + Tickets_Row_A[seatNumber - 1][2]);
                System.out.println("Seat: " + Tickets_Row_A[seatNumber - 1][3] + " " + Tickets_Row_A[seatNumber - 1][4]);
                System.out.println("Price: " + Tickets_Row_A[seatNumber - 1][5]);
            }else{
                System.out.println("Seat available");
            }
        } else if (index == 1) {
            if (Tickets_Row_A[seatNumber-1][0]!= null) {
                System.out.println("Name: " + Tickets_Row_B[seatNumber - 1][0] + " " + Tickets_Row_B[seatNumber - 1][1]);
                System.out.println("Email: " + Tickets_Row_B[seatNumber - 1][2]);
                System.out.println("Seat: " + Tickets_Row_B[seatNumber - 1][3] + " " + Tickets_Row_B[seatNumber - 1][4]);
                System.out.println("Price: " + Tickets_Row_B[seatNumber - 1][5]);
            }else{
                System.out.println("Seat available");
            }
        } else if (index == 2) {
            if (Tickets_Row_A[seatNumber-1][0]!= null) {
                System.out.println("Name: " + Tickets_Row_C[seatNumber - 1][0] + " " + Tickets_Row_C[seatNumber - 1][1]);
                System.out.println("Email: " + Tickets_Row_C[seatNumber - 1][2]);
                System.out.println("Seat: " + Tickets_Row_C[seatNumber - 1][3] + " " + Tickets_Row_C[seatNumber - 1][4]);
                System.out.println("Price: " + Tickets_Row_C[seatNumber - 1][5]);
            }else{
                System.out.println("Seat available");
            }
        } else {
            if (Tickets_Row_A[seatNumber-1][0]!= null) {
                System.out.println("Name: " + Tickets_Row_D[seatNumber - 1][0] + " " + Tickets_Row_D[seatNumber - 1][1]);
                System.out.println("Email: " + Tickets_Row_D[seatNumber - 1][2]);
                System.out.println("Seat: " + Tickets_Row_D[seatNumber - 1][3] + " " + Tickets_Row_D[seatNumber - 1][4]);
                System.out.println("Price: " + Tickets_Row_D[seatNumber - 1][5]);
            }else{
                System.out.println("Seat available");
            }
        }

    }

    // Helper method to get the index of the row letter in the ROW_LABELS array
    private static int rowIndex(char rowLetter) {
        int rowIndex;
        switch (rowLetter) {
            case 'A':
                rowIndex = 0;
                break;
            case 'B':
                rowIndex = 1;
                break;
            case 'C':
                rowIndex = 2;
                break;
            default:
                rowIndex = 3;
                break;
        }
        return rowIndex;
    }

    // Helper method to check if the row letter is valid
    private static boolean isValidRow(char rowLetter) {
        return rowLetter == 'A' || rowLetter == 'B' || rowLetter == 'C' || rowLetter == 'D';
    }

    // Helper method to check if the seat number is valid
    private static boolean isValidSeat(char rowLetter, int seatNumber) {
        int rowIdx = rowIndex(rowLetter);
        return seatNumber >= 1 && seatNumber <= NUM_SEATS_PER_ROW[rowIdx];
    }

    public static void print_tickets_info() {
        System.out.println("Tickets Information:");
        printRowTickets("Row A", Tickets_Row_A);
        printRowTickets("Row B", Tickets_Row_B);
        printRowTickets("Row C", Tickets_Row_C);
        printRowTickets("Row D", Tickets_Row_D);
    }

    private static void printRowTickets(String rowName, String[][] rowTickets) {
        System.out.println(rowName + ":");
        for (String[] ticket : rowTickets) {
            if (ticket[0] != null) {
                System.out.println("Name: " + ticket[0] + " " + ticket[1]);
                System.out.println("Email: " + ticket[2]);
                System.out.println("Seat: " + ticket[3] + " " + ticket[4]);
                System.out.println("Price: " + ticket[5]);
                System.out.println();
            }
        }
    }

    public static void User_info() {
        Scanner scanner = new Scanner(System.in);
        System.out
                .print("Enter first name: ");
        Name = scanner.nextLine();
        System.out.print("Enter last name: ");
        Surname = scanner.nextLine();
        System.out.print("Enter email: ");
        Email = scanner.nextLine();
    }
}
