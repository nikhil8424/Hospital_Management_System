import java.util.Scanner;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.text.ParseException;
import java.util.Date;

class Facility {
    public String fec_name;
    boolean isBooked = false;

    public void add_feci() {
        try (Scanner input = new Scanner(System.in)) {
            System.out.print("Facility Name: ");
            fec_name = input.nextLine();
        }
    }

    public void show_feci() {
        System.out.println(fec_name);
    }


    public void saveEmergencyInfo(String name, int age, String location, String contactNumber) {
        // Use try-with-resources to automatically close the FileWriter
        try (FileWriter writer = new FileWriter("emergency.txt", true)) {
            writer.write("Name: " + name + "\n");
            writer.write("Age: " + age + "\n");
            writer.write("Location to Reach: " + location + "\n");
            writer.write("Contact Number: " + contactNumber + "\n");
            writer.write("----------------------------------\n");
            System.out.println("Emergency information saved to 'emergency.txt'");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
