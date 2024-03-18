package hospital;
import java.util.Scanner;

public class Medical {
    public String med_name, med_comp, exp_date;
    public int med_cost, count;

    public void new_medi() {
        Scanner input = new Scanner(System.in);
        try {
            System.out.print("name:-");
            med_name = input.nextLine();
            System.out.print("comp:-");
            med_comp = input.nextLine();
            System.out.print("exp_date:-");
            exp_date = input.nextLine();
            System.out.print("cost:-");
            med_cost = input.nextInt();
            System.out.print("no of unit:-");
            count = input.nextInt();
        } catch (Exception e) {
            System.out.println("Error: Invalid input. Please enter valid data.");
            // You can add more specific error handling or logging here as needed
        } finally {
            input.close(); // Close the Scanner in the finally block to prevent resource leaks
        }
    }

    public void find_medi() {
        System.out.println(med_name + "  \t" + med_comp + "    \t" + exp_date + "     \t" + med_cost);
    }
}
