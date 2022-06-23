package admin;
import java.sql.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class Admin extends Voter {
    public void candidatesDatabase() {
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://DESKTOP-9M33U7D/mydb",
                    "root", "Cecilia2002");
            Statement statement = connection.createStatement();
            int candidates = statement.executeUpdate("create table Registered_Candidates (ID " +
                    "int auto_increment primary key, firstName varchar(50), lastName varchar(50), Gender Enum('M', 'F'), State varchar(20)," +
                    "Age int, Position varchar (20), Date Date, Party varchar(10), Votes int)");
        }catch (SQLSyntaxErrorException ex) {
            System.out.println("Table already created");
        }catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void votersDatabase() {
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://DESKTOP-9M33U7D/mydb",
                    "root", "Cecilia2002");
            Statement statement = connection.createStatement();
            int voters = statement.executeUpdate("create table Registered_Voters (ID " +
                    "int auto_increment primary key, firstName varchar(50), lastName varchar(50), Gender varChar(10), State varchar(20),Age int," +
                    " Password varchar(20))");
        } catch (SQLSyntaxErrorException ex) {
            System.out.println("Table already created");
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void AdminDatabase() {
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://DESKTOP-9M33U7D/mydb",
                    "root", "Cecilia2002");
            Statement statement = connection.createStatement();
            int Admin = statement.executeUpdate("create table Admins (ID " +
                    "int auto_increment primary key, firstName varchar(50),lastName varchar(50), Gender Enum('M', 'F')," +
                    " State varchar(20),Age int, Date Date, password varchar(30))");
        } catch (SQLSyntaxErrorException ex) {
            System.out.println("Table already created");
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void addAdmins(){
        System.out.println("Enter First name: ");
        setFirstName( new Scanner(System.in).next());

        System.out.println("Enter last name: ");
        setLastName( new Scanner(System.in).next());

        System.out.println("Gender: ");
        setGender( new Scanner(System.in).next());

        System.out.println("State of origin: ");
        setState( new Scanner(System.in).next());

        System.out.println("Age: ");
        setAge( new Scanner(System.in).nextInt());
        try{
            Connection connection = DriverManager.getConnection("jdbc:mysql://DESKTOP-9M33U7D/mydb",
                    "root", "Cecilia2002");
            Statement statement = connection.createStatement();
            int add = statement.executeUpdate("insert into Admins (FirstName,lastName, Gender, State," +
                    "Age , password) values('"+getFirstName()+"','"+getLastName()+"', '"+getGender()+"', '"+getState()+"','"+getAge()+"'," +
                    " 'Admin@onlineVoting.com') ");
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public void RemoveCandidates(){
        System.out.print("First name: ");
        String removeFirstName =  new Scanner(System.in).next();
        System.out.print("Last name: ");
        String removeLastname =  new Scanner(System.in).next();
        System.out.print("Position name: ");
        String removePosition =  new Scanner(System.in).next();
        try{
            Connection connection = DriverManager.getConnection("jdbc:mysql://DESKTOP-9M33U7D/mydb",
                    "root", "Cecilia2002");
            Statement statement = connection.createStatement();
            int remove = statement.executeUpdate("delete from Registered_candidates where firstName = '"+removeFirstName+"' && " +
                    "lastName = '"+removeLastname+"' && position = '"+removePosition+"' ");
        }catch (SQLException e){
            e.printStackTrace();
        }

    }
    public void RemoveAdmin(){
        System.out.print("First name: ");
        String removeFirstName =  new Scanner(System.in).next();
        System.out.print("Last name: ");
        String removeLastname =  new Scanner(System.in).next();
        try{
            Connection connection = DriverManager.getConnection("jdbc:mysql://DESKTOP-9M33U7D/mydb",
                    "root", "Cecilia2002");
            Statement statement = connection.createStatement();
            int remove = statement.executeUpdate("delete from Admin where firstName = '"+removeFirstName+"' && " +
                    "lastName = '"+removeLastname+"'");
        }catch (SQLException e){
            e.printStackTrace();
        }

    }
    public void editPassword(){
        System.out.println("Change current password \n 1.yes \n 2.no");
        String edit = new Scanner(System.in).next();
        switch (edit){
            case "1":
                System.out.println("Enter current password ");
                String changeCurrent = new Scanner(System.in).next();
                if(getPassword().equals(changeCurrent)){
                    System.out.println("Enter new password ");
                    String newPassword = new Scanner(System.in).next();
                    System.out.println("Confirm new password ");
                    String confirmPassword = new Scanner(System.in).next();
                    if(confirmPassword.equals(newPassword)){
                        System.out.println("Password Successfully Changed!");
                    }
                    else {
                        System.out.println("Password mismatch!");
                    }
                }else {
                    System.out.println("Password incorrect!");
                }
        }
    }


}