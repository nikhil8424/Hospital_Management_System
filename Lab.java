package hospital;
import java.util.Scanner;

// Interface defining the contract for a lab
interface LabService {
    void new_feci();
    void feci_list();
}

// Lab class implementing the LabService interface
public class Lab implements LabService {
    public String fecility;
    public int lab_cost;

    @Override
    public void new_feci() {
        Scanner input = new Scanner(System.in);
        try {
            System.out.print("Facility: ");
            fecility = input.nextLine();
            System.out.print("Cost: ");
            lab_cost = input.nextInt();
        } catch (java.util.InputMismatchException e) {
            System.out.println("Invalid input. Please enter a valid integer for cost.");
            input.nextLine(); // Consume the invalid input to avoid an infinite loop
            new_feci(); // Recursive call to retry input
        }
    }

    @Override
    public void feci_list() {
        System.out.println(fecility + "\t\t" + lab_cost);
    }
}
