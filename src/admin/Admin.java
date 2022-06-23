package admin;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class Admin extends Voter {
    public int getNumberOfVotes() {
        return numberOfVotes;
    }

    public void setNumberOfVotes(int numberOfVotes) {
        this.numberOfVotes = numberOfVotes;
    }

    private String position, party;
    private int numberOfVotes = 0;

    public void setParty(String party) {
        this.party = party;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public static void database() {
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://DESKTOP-9M33U7D/mydb",
                    "root", "Cecilia2002");
            System.out.println(connection);
            Statement statement = connection.createStatement();
            //  int result = statement.executeUpdate("update account set bal = 10000 where lastname = 'okoro' ");
            int voters = statement.executeUpdate("create table Registered_Voters (ID " +
                    "int auto_increment primary key, firstName varchar(50),lastName varchar(50), Gender Enum('M', 'F'), State varchar(20)," +
                    "Age int, password varchar (20))");
            int candidate = statement.executeUpdate("create table Registered_Candidates (ID " +
                    "int auto_increment primary key, firstName varchar(50), lastName varchar(50), Gender Enum('M', 'F'), State varchar(20)," +
                    "Age int, Position varchar (20), Date Date, Party varchar(10), Votes int)");
            int Admin = statement.executeUpdate("create table Admins (ID " +
                    "int auto_increment primary key, Name varchar(50), Gender Enum('M', 'F')," +
                    " State varchar(20),Age int, Date Date)");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void AddCandidates(){
        System.out.println("Enter Candidates First name: ");
        setFirstName( new Scanner(System.in).next());
        System.out.println("Enter Candidates last name: ");
        setLastName( new Scanner(System.in).next());
        System.out.println("Gender: ");
        setGender( new Scanner(System.in).next());
        System.out.println("State of origin: ");
        setState( new Scanner(System.in).next());
        System.out.println("Age: ");
        setAge( new Scanner(System.in).nextInt());
        System.out.println("Position: ");
        setPosition( new Scanner(System.in).next());
        System.out.println("Party: ");
        setParty( new Scanner(System.in).next());
        try{
        Connection connection = DriverManager.getConnection("jdbc:mysql://DESKTOP-9M33U7D/mydb",
                "root", "Cecilia2002");
        Statement statement = connection.createStatement();
        int add = statement.executeUpdate("insert into Registered_candidates (FirstName,lastName, Gender, State," +
                "Age , position, party, votes) values('"+getFirstName()+"','"+getLastName()+"', '"+getGender()+"', '"+getState()+"','"+getAge()+"'," +
                " '"+position+"', '"+party+"', '"+numberOfVotes+"') ");
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
    public void RemoveCandidates(){
        String removeFirstName =  new Scanner(System.in).next();
        String removeLastname =  new Scanner(System.in).next();
        String removePosition =  new Scanner(System.in).next();
        try{
            Connection connection = DriverManager.getConnection("jdbc:mysql://DESKTOP-9M33U7D/mydb",
                    "root", "Cecilia2002");
            Statement statement = connection.createStatement();
            int remove = statement.executeUpdate("delete from Registered_candidates where firstName = '"+removeFirstName+"' && " +
                    "lastName = '"+removeLastname+"' && position = '"+removePosition+"' ");
            System.out.println(remove);
        }catch (SQLException e){
            e.printStackTrace();
        }

    }
    public void editAdminPassword(){
        setPassword("Admin@onlineVoting.com");
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