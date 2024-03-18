package hospital;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Patient {
    private String pid;
    private String pname;
    private String disease;
    private String sex;
    private String admit_status;
    private int age;

    public void new_patient() {
        Scanner input = new Scanner(System.in);
        System.out.print("id: ");
        pid = input.nextLine();
        System.out.print("name: ");
        pname = input.nextLine();
        System.out.print("disease: ");
        disease = input.nextLine();
        System.out.print("sex: ");
        sex = input.nextLine();
        System.out.print("admit_status: ");
        admit_status = input.nextLine();

        // Exception handling for age input
        while (true) {
            try {
                System.out.print("age: ");
                age = input.nextInt();
                break; // Exit the loop if age is successfully entered
            } catch (InputMismatchException e) {
                System.out.println("Error: Invalid input. Age must be a number.");
                input.nextLine(); // Clear the input buffer
            }
        }
    }

    // Getter and setter methods for attributes
    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public String getPname() {
        return pname;
    }

    public void setPname(String pname) {
        this.pname = pname;
    }

    public String getDisease() {
        return disease;
    }

    public void setDisease(String disease) {
        this.disease = disease;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getAdmitStatus() {
        return admit_status;
    }

    public void setAdmitStatus(String admit_status) {
        this.admit_status = admit_status;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void patient_info() {
        System.out.println(getPid() + "\t" + getPname() + "\t" + getDisease() + "\t" + getSex() + "\t" + getAdmitStatus() + "\t" + getAge());
    }
}
