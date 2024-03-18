package hospital;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Staff {
    public String sid, sname, desg, sex;
    public int salary;

    public void newStaff() {
        Scanner input = new Scanner(System.in);
        try {
            System.out.print("id: ");
            sid = input.nextLine();
            System.out.print("name: ");
            sname = input.nextLine();
            System.out.print("designation: ");
            desg = input.nextLine();
            System.out.print("sex: ");
            sex = input.nextLine();
            System.out.print("salary: ");
            salary = input.nextInt();
        } catch (InputMismatchException e) {
            System.out.println("Error: Invalid input. Please enter valid data.");
            input.nextLine(); // Clear the input buffer
            newStaff(); // Recursively call newStaff() to re-enter data
        }
    }

    public void staffInfo() {
        System.out.println(sid + "\t" + sname + "\t" + sex + "\t" + salary);
    }
}
