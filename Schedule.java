
// take input,update,delete,display the entire schedule ,display one schdeule on patient name.
import java.io.*;
import java.util.*;
import java.sql.*;

//schedule class is a form of hereditary  inheritance from doctor
public class Schedule extends Doctor {
    

private String apid,date, time,status,patient_name,docid;

public void setapid(String id){

this.apid=id;

}

public void setpatient(String patient){
this.patient_name=patient;
}

public void setdate(String date){

this.date=date;

}


public void settime(String time){
this.time=time;
}
public void setdocid(String id){
this.docid=id;
}

public void setstatus(String status){
this.status=status;
}

public String getapid(){
return this.apid;
}

public String gettime(){
return this.time;
}

public String getdate(){
return this.date;
}

public String getstatus(){
return this.status;
}

public String getdocid(){
return this.docid;
}

public String getpatient(){
return this.patient_name;
}


public void input()
    {
        Scanner input = new Scanner(System.in);
        System.out.print("Appointment Id:-");
        String apid= input.nextLine();
        System.out.print("patient name:-");
        String patient = input.nextLine();
        System.out.print("time(HH:MM:SS):-");
        String time = input.nextLine();
        System.out.print("date(DD-MM-YYYY):-");
        String date = input.nextLine();
        System.out.print("status(confirmed,cancelled,pending):-");
        String status = input.nextLine();
        System.out.print("docid:-");
        String docid = input.nextLine();
         
       
        this.setapid(apid);
        this.setpatient(patient);
        this.setdate(date);
        this.setstatus(status);
        this.settime(time);
        this.setdocid(docid);
        this.databasedoc();
}


//polymorphism-method overriding

public void databasedoc(){
      // Open a connection
      try(Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
         ){
           
           String sql="insert into schedule (apid,patient_name,date,time,status,docid) values(?,?,?,?,?,?)";
           PreparedStatement stmt=conn.prepareStatement(sql);
   
          stmt.setString(1,this.getapid());
          stmt.setString(2,this.getpatient());
          stmt.setString(3,this.getdate());
          stmt.setString(4,this.gettime());
          stmt.setString(5,this.getstatus());
          stmt.setString(6,this.getdocid());
          stmt.execute();
          conn.close();
          System.out.print("inserted successfully");
        } 
           catch (SQLException e) {
                      System.out.print(e);
         } 
   }




//polymorphism-method overriding
// upadte information
public void updatedoc(){
        Scanner input = new Scanner(System.in);
        System.out.print("Appointment id:-");
        String id= input.nextLine();
        this.setapid(id);
         System.out.println("what do you want to udate (patient_name,date,time,status)?");
        String change= input.nextLine();
         System.out.print("change to:-");
        String changeto= input.nextLine();

//open database
        try(Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);){
           
           String sql="update schedule set %s =? where apid=?";
           PreparedStatement stmt=conn.prepareStatement(String.format(sql,change));
           stmt.setString(1,changeto);
           stmt.setString(2,this.getapid());

            stmt.executeUpdate();
            conn.close();
            System.out.println("Updated successfully");
            } 
                 catch (SQLException e) {
                       System.out.print(e);
      } 
}





//polymorphism-method overriding
//delete records
public void deletedoc(){
Scanner input = new Scanner(System.in);
        System.out.print("appointment id:-");
        String id= input.nextLine();
        this.setapid(id);
//open database
          try(Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);){
                  
                   String sql="delete from schedule where apid=?";
                   PreparedStatement stmt=conn.prepareStatement(sql);
                   stmt.setString(1,this.getapid());
                   stmt.executeUpdate();
                   conn.close();
                   System.out.println("deleted  successfully");
              } 
          catch (SQLException e) {
                  System.out.print(e);
      } 
   

     }



//display all the info with the appointment id.
 public void appointment_info()

    {   Scanner input = new Scanner(System.in);
        System.out.print("appointment id:-");
        String id= input.nextLine();
        this.setapid(id);
        try(Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);)
      {
           
           String sql="select*from schedule where apid=?";
           PreparedStatement stmt=conn.prepareStatement(sql);
           stmt.setString(1,this.getapid());

                try (ResultSet rs = stmt.executeQuery()) {
                     if (rs.next()) {
                   
                           System.out.println("Id: "+rs.getString("apid"));
                           System.out.println("Patient Name: "+rs.getString("patient_name"));
                           System.out.println("date: "+rs.getString("date"));
                           System.out.println("Time: "+rs.getString("time"));
                           System.out.println("Status: "+rs.getString("status"));
                           

                } 
                else {System.out.println("No data found for ID: " + id);}
            }

      }
             catch (SQLException e) {
                       System.out.print(e);
        } 
}


//displays  the entire details of schedule

public void getinfoall() {
    try (Connection con = DriverManager.getConnection(DB_URL, USER, PASS);
         Statement stmt = con.createStatement();
         ResultSet rs = stmt.executeQuery("SELECT * FROM schedule")) {

        System.out.println("----------------------------------------------------------------------------------------------------------");
        // Print column headers
        System.out.println("Appointment ID  | Patient Name\t\t |    Date \t|    Time \t| Status\t | Doctor ID");
        System.out.println("----------------------------------------------------------------------------------------------------------");

        // Print rows
        while (rs.next()) {
            String apid = rs.getString("apid");
            String patientName = rs.getString("patient_name");
            String date = rs.getString("date");
            String time = rs.getString("time");
            String status = rs.getString("status");
            String docid = rs.getString("docid");

            System.out.printf("%s \t\t| %s\t\t| %s \t| %s\t | %s \t| %s\n", apid, patientName, date, time, status, docid);
            System.out.println("----------------------------------------------------------------------------------------------------------");
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
}


//display the doctor based on the patient name.
public void displaydoc(){
Scanner input = new Scanner(System.in);
        System.out.print("Patient Name:-");
        String name= input.nextLine();
        this.setpatient(name);
String id=selectone(name,"docid");
Doctor d2=new Doctor();
String docname=d2.selectone(id,"docname");
System.out.println("Dr."+docname+"  is the incharge of patient "+name);
}


//polymorphism-method overriding
//select one column
public String selectone(String patient,String col){
try(Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);)
      {
           
           String sql="select %s from schedule where patient_name=?";
           PreparedStatement stmt=conn.prepareStatement(String.format(sql,col));
           stmt.setString(1,patient);

                try (ResultSet rs = stmt.executeQuery()) {
                     if (rs.next()) {
                   
                          
                           return rs.getString(col);
                      

                } 
                else {System.out.println("No data found for patient: " + patient);}
            }

      }
             catch (SQLException e) {
                       System.out.print(e);
        } 

return null;
}

//count apointments by status :=cancelled,confirmed,pending
public void countAppointmentsByStatus() {
Scanner input = new Scanner(System.in);

    System.out.print("status(confirmed,pending,cancelled):-");
    String status= input.nextLine();
    int count = 0;
    try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
         PreparedStatement stmt = conn.prepareStatement("SELECT COUNT(*) AS count FROM schedule WHERE status = ?")) {
        stmt.setString(1, status);
        try (ResultSet rs = stmt.executeQuery()) {
            if (rs.next()) {
                count = rs.getInt("count");
                System.out.println("the total appointments which are "+status+" - "+count);
            }
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
 
}




}