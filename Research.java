
import java.io.*;
import java.util.*;
import java.sql.*;


//research hereditary  inheritance of doctors

public class Research extends Doctor{

//encapsulation 
private String rid,title,status,docid;
 


//polymorphism-method overloading
public Research(String rid,String title,String status,String did, String docname, String dspecialist, String dappoint, String ddoc_qual,String ddroom){
super(did, docname, dspecialist, dappoint, ddoc_qual,ddroom);
this.rid=rid;
this.title=title;
this.status=status;
this.docid=docid;
}


//polymorphism-method overloading
public Research (){
super();
this.rid="";
this.title="";
this.status="";
this.docid="";

}

public String getrid(){
return this.rid;
}

public String gettitle(){
return this.title;
}

public void setrid(String rid){
this.rid=rid;
}

public void settitle(String title){
this.title=title;
}

public void setstatus(String status){
this.status=status;
}

public String getstatus(){
return this.status;
}

public void setdocid(String id){
this.docid=id;
}

public String getdocid(){
return this.docid;
}


//to insert data in the resaerch table
public void input()
    {
        Scanner input = new Scanner(System.in);
        System.out.println("research Id:-");
        String rid= input.nextLine();
        System.out.println("title:-");
        String title = input.nextLine();
        System.out.println("Doctor Id:-");
        String docid = input.nextLine();
         System.out.println("Status Of Research(ongoing,pending,completed):-");
        String status = input.nextLine();
        
       
        this.setrid(rid);
        this.settitle(title);
        this.setdocid(docid);
        this.setstatus(status);
       
       this.databasedoc();
}


//polymorphism-method overriding
public void databasedoc(){
      // Open a connection
      try(Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
         ){
          
           String sql="insert into research (rid, title ,docid, status) values(?,?,?,?)";
           PreparedStatement stmt=conn.prepareStatement(sql);
   
          stmt.setString(1,this.getrid());
          stmt.setString(2,this.gettitle());
          stmt.setString(3,this.getdocid());
          stmt.setString(4,this.getstatus());
          stmt.execute();
          conn.close();
          System.out.print("inserted successfully in Research Table");
        } 
           catch (SQLException e) {
                      System.out.print(e);
         } 
   }



//polymorphism-method overriding
// upadte information
public void updatedoc(){
        Scanner input = new Scanner(System.in);
        System.out.println("Research id:-");
        String id= input.nextLine();
        this.setrid(id);
         System.out.println("what do you want to udate (title,status,docid)?");
        String change= input.nextLine();
         System.out.println("change to:-");
        String changeto= input.nextLine();

//open database
        try(Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);){
          
           String sql="update research set %s =? where rid=?";
           PreparedStatement stmt=conn.prepareStatement(String.format(sql,change));
           stmt.setString(1,changeto);
           stmt.setString(2,this.getrid());

            stmt.executeUpdate();
            conn.close();
            System.out.print("Updated successfully in Research Table");
            } 
                 catch (SQLException e) {
                       System.out.print(e);
      } 
}





//polymorphism-method overriding
//delete records
public void deletedoc(){
Scanner input = new Scanner(System.in);
        System.out.print("Research id:-");
        String id= input.nextLine();
        this.setrid(id);
//open database
          try(Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);){
                   String sql="delete from research where rid=?";
                   PreparedStatement stmt=conn.prepareStatement(sql);
                   stmt.setString(1,this.getId());
                   stmt.executeUpdate();
                   conn.close();
                   System.out.print("deleted  successfully from Research Table");
              } 
          catch (SQLException e) {
                  System.out.print(e);
      } 
   

     }


// displays the entire details of the research table
public void getinfoall() {
    try (Connection con = DriverManager.getConnection(DB_URL, USER, PASS);
         Statement stmt = con.createStatement();
         ResultSet rs = stmt.executeQuery("SELECT * FROM research")) {

        System.out.println("------------------------------------------------------------------------------------------------");
        System.out.println("Research ID\t| Title\t| Doctor ID\t| Status");
        System.out.println("------------------------------------------------------------------------------------------------");

        // Print rows
        while (rs.next()) {
            String rid = rs.getString("rid");
            String title = rs.getString("title");
            String docid = rs.getString("docid");
            String status = rs.getString("status");

            System.out.printf("%s\t\t| %s\t| %s\t\t| %s\n", rid, title, docid, status);
            System.out.println("------------------------------------------------------------------------------------------------");
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
}


public String displayDoctorName(String id,String col) {
Doctor doc=new Doctor();
 return doc.selectone(id,col);
       
}


//display the details doctor name and just the title of ther research
public void conductresearch(){
Scanner input = new Scanner(System.in);
        System.out.println("research Id:-");
        String rid= input.nextLine();
String id=this.selectone(rid,"docid");
this.settitle(this.selectone(rid,"title"));
Doctor d2=new Doctor();
d2.setname(this.displayDoctorName(id,"docname"));
System.out.println("Doctor " + d2.getname() + " is doing research on " + this.gettitle());
   
}




//polymorphism-method overriding
//only one column value
public String selectone(String id,String col){
try(Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);)
      {
           
           String sql="select %s from research where rid=?";
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

//to display the status of the research.
public void status(){
Scanner input = new Scanner(System.in);
        System.out.println("research Id:-");
        String rid= input.nextLine();
String id=this.selectone(rid,"docid");
this.settitle(this.selectone(rid,"title"));
this.setstatus(this.selectone(rid,"status"));
Doctor d2=new Doctor();
d2.setname(d2.selectone(id,"docname"));
System.out.println("Doctor " + d2.getname() + " is doing research on " + this.gettitle()+"and the research is "+this.getstatus());
   
}

	
}
