//input,delete,update,showall, show one
import java.io.*;
import java.util.*;
import java.sql.*;

public class Doctor
{
    static final String DB_URL = "jdbc:mysql://localhost/gargi";
    static final String USER = "root";
    static final String PASS = "gargi*1995";


    private String did, docname, dspecialist, dappoint, ddoc_qual;
    private String dnum;

//polymorphism-method overloading
public Doctor (String did, String docname,String  dspecialist, String dappoint, String ddoc_qual,String dnum){

this.did=did;
this.docname=docname;
this.dspecialist=dspecialist;
this.dappoint=dappoint;
this.ddoc_qual=ddoc_qual;
this.dnum=dnum;

}
 
//polymorphism-method overloading
//without parameters
public Doctor (){

this.did="";
this.docname="";
this.dspecialist="";
this.dappoint="";
this.ddoc_qual="";
this.dnum="";
}
   
public String getId(){
return did;
}

 public String getname(){
return docname;
}

 public String getspecial(){
return dspecialist;
}

 public String getappoint(){
return dappoint;
}

 public String getqual(){
return ddoc_qual;
}

 public String getnum(){
return dnum;
}

public void setId(String did){

this.did=did;
}

public void setname(String docname){

this.docname=docname;
}

public void setspecial(String dspecial){

this.dspecialist=dspecial;

}

public void setnum(String dnum){

this.dnum=dnum;

}

public void setqual(String qual){

this.ddoc_qual=qual;

}

public void setappoint(String dappoint){

this.dappoint=dappoint;

}



//add new doctor information

    public  void new_doctor()
    {
        Scanner input = new Scanner(System.in);
        System.out.print("id:-");
        String a= input.nextLine();
        this.setId(a);

        System.out.print("name:-");
        String docname = input.nextLine();
        this.setname(docname);

        System.out.print("specilization:-");
        String dspecial = input.nextLine();
        this.setspecial(dspecial);

        System.out.print("work time:-");
        String dappoint = input.nextLine();
        this.setappoint(dappoint);


        System.out.print("qualification:-");
        String ddoc_qual = input.nextLine();
        this.setqual(ddoc_qual);



        System.out.print("emergency no.:-");
        String dnum = input.nextLine();
        this.setnum(dnum);



        this.databasedoc();
               
    }

//polymorphism-method overriding
//inserting values

    public void databasedoc(){
      // Open a connection
      try(Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
         ){
          
           String sql="insert into doctor (did, docname , dspecialist, dappoint, ddoc_qual,ddroom) values(?,?,?,?,?,?)";
           PreparedStatement stmt=conn.prepareStatement(sql);
   
          stmt.setString(1,this.getId());
          stmt.setString(2,this.getname());
          stmt.setString(3,this.getspecial());
          stmt.setString(4,getappoint());
          stmt.setString(5,this.getqual());
          stmt.setString(6,this.getnum());
          stmt.execute();
          conn.close();
          System.out.println("Inserted successfully");
        } 
           catch (SQLException e) {
                      System.out.print(e);
         } 
   }





//get entire doctor information of a particular id
    public void doctor_info()

    {   Scanner input = new Scanner(System.in);
        System.out.print("id:-");
        String id= input.nextLine();
        this.setId(id);
        try(Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);)
      {
          
           String sql="select*from doctor where did=?";
           PreparedStatement stmt=conn.prepareStatement(sql);
           stmt.setString(1,this.getId());

                try (ResultSet rs = stmt.executeQuery()) {
                     if (rs.next()) {
                   
                           System.out.println("Id: "+rs.getString("did"));
                           System.out.println("Name: "+rs.getString("docname"));
                           System.out.println("specialist: "+rs.getString("dspecialist"));
                           System.out.println("Work Hours: "+rs.getString("dappoint"));
                           System.out.println("Qualifications: "+rs.getString("ddoc_qual"));
                           System.out.println("Emergency Number: "+rs.getString("ddroom"));

                } 
                else {System.out.println("No data found for ID: " + id);}
            }

      }
             catch (SQLException e) {
                       System.out.print(e);
        } 
}


//returns a array of the entire details from id
public void getinfoall() {
    try (Connection con = DriverManager.getConnection(DB_URL, USER, PASS);
         Statement stmt = con.createStatement();
         ResultSet rs = stmt.executeQuery("SELECT * FROM doctor")) {

        // Print column headers
            System.out.println("---------------------------------------------------------------------------------------------------------------------------------");

        System.out.println("ID\t|\tName\t\t|\tSpecialist\t|  Work Hours   |Qualifications|\tEmergency Number");
            System.out.println("---------------------------------------------------------------------------------------------------------------------------------");
            System.out.println("---------------------------------------------------------------------------------------------------------------------------------");

        // Print rows
        while (rs.next()) {
            setId( rs.getString("did"));
            setname(rs.getString("docname"));
            setspecial(rs.getString("dspecialist"));
            setappoint(rs.getString("dappoint"));
            setqual(rs.getString("ddoc_qual"));
            setnum(rs.getString("ddroom"));

            System.out.printf("%s\t|\t%s\t|\t%s\t|\t%s\t|\t%s\t|\t%s\n", getId(), getname(), getspecial(), getappoint(), getqual(),getnum());
            System.out.println("---------------------------------------------------------------------------------------------------------------------------------");
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
}





//polymorphism-method overriding
// upadte information
public void updatedoc(){
        Scanner input = new Scanner(System.in);
        System.out.println("id:-");
        String id= input.nextLine();
        this.setId(id);
         System.out.println("what do you want to udate (Id-did,name-docname,speciality-dspecialist,work Hours-dappoint,qualifications-ddoc_qual,emergency numebr-ddroom)?");
        String change= input.nextLine();
         System.out.println("change to:-");
        String changeto= input.nextLine();

//open database
        try(Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);){
          
           String sql="update doctor set %s =? where did=?";
           PreparedStatement stmt=conn.prepareStatement(String.format(sql,change));
           stmt.setString(1,changeto);
           stmt.setString(2,this.getId() );

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
        System.out.print("id:-");
        String id= input.nextLine();
        this.setId(id);
//open database
          try(Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);){
                   String sql="delete from Doctor where did=?";
                   PreparedStatement stmt=conn.prepareStatement(sql);
                   stmt.setString(1,this.getId());
                   stmt.executeUpdate();
                   conn.close();
                   System.out.println("deleted  successfully");
              } 
          catch (SQLException e) {
                  System.out.print("Cannot delete the record as it it neccessary for information in another class");
      } 
   

     }


//select only few attributes and return them on id
//polymorphism-method overriding
public String selectone(String id,String col){
try(Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);)
      {
           
           String sql="select %s from doctor where did=?";
           PreparedStatement stmt=conn.prepareStatement(String.format(sql,col));
           stmt.setString(1,id);

                try (ResultSet rs = stmt.executeQuery()) {
                     if (rs.next()) {
                   
                          
                           return rs.getString(col);
                      

                } 
                else {System.out.println("No data found for ID: " + id);}
            }

      }
             catch (SQLException e) {
                       System.out.print(e);
        } 

return null;
}

}


      
