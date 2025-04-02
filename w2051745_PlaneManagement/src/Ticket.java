import java.io.FileWriter;
import java.io.IOException;

public class Ticket extends PlaneManagement {
    private String row;
    private int seat;
    private int price;
    private Person person;

    public Ticket() {
        this.person = new Person("", "", ""); // Initialize person object
    }

    // Setters
    public void setRow(String row) {
        this.row = row;
    }

    public void setSeat(int seat) {
        this.seat = seat;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    // Getters
    public String getRow() {
        return row;
    }

    public int getSeat() {
        return seat;
    }

    public int getPrice() {
        return price;
    }

    public Person getPerson() {
        return person;
    }

    // Set price based on seat index
    public void Set_Price(int seatIndex) {
        if (seatIndex <= 5) {
            price = 200;
        } else if (seatIndex <= 9) {
            price = 150;
        } else {
            price = 180;
        }
    }

    // Method to save ticket information to a file
    public void save() {
        String filename = row + seat + ".txt";
        try {
            FileWriter writer = new FileWriter(filename);
            writer.write("Row: " + row + "\n");
            writer.write("Seat: " + seat + "\n");
            writer.write("Price: $" + price + "\n");
            writer.write("Person: " + person.getName() + " " + person.getSurname() + "\n");
            writer.write("Email: " + person.getEmail() + "\n");
            writer.close();
            System.out.println("Ticket information saved to file: " + filename);
        } catch (IOException e) {
            System.out.println("An error occurred while saving ticket information: " + e.getMessage());
        }
    }
}
