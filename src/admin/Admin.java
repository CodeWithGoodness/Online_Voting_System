package admin;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Admin extends Voter {
    public static void database() {
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://DESKTOP-9M33U7D/mydb",
                    "root", "Cecilia2002");
            System.out.println(connection);
            Statement statement = connection.createStatement();
            //  int result = statement.executeUpdate("update account set bal = 10000 where lastname = 'okoro' ");
            int voters = statement.executeUpdate("create table Registered_Voters (ID " +
                    "int auto_increment primary key, Name varchar(50), Gender Enum('M', 'F'), State varchar(20)," +
                    "Age int, password varchar (20))");
            int candidate = statement.executeUpdate("create table Registered_Candidates (ID " +
                    "int auto_increment primary key, Name varchar(50), Gender Enum('M', 'F'), State varchar(20)," +
                    "Age int, Position varchar (20), Date Date, Party varchar(10), Votes int)");
            int Admin = statement.executeUpdate("create table Admins (ID " +
                    "int auto_increment primary key, Name varchar(50), Gender Enum('M', 'F')," +
                    " State varchar(20),Age int, Date Date)");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void AddCandidates(String name, String gender, String state, int age, String password){
        try{
        Connection connection = DriverManager.getConnection("jdbc:mysql://DESKTOP-9M33U7D/mydb",
                "root", "Cecilia2002");
        Statement statement = connection.createStatement();
        int add = statement.executeUpdate("insert into Registered_candidates (Name,Gender, State," +
                "Age , password) values('"+name+"', '"+gender+"', '"+state+"','"+age+"'," +
                " '"+password+"') ");
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
    public void RemoveCandidates(String name, int ID){
        try{
            Connection connection = DriverManager.getConnection("jdbc:mysql://DESKTOP-9M33U7D/mydb",
                    "root", "Cecilia2002");
            Statement statement = connection.createStatement();
            int remove = statement.executeUpdate("delete from Registered_candidates where ID = ID && " +
                    "Name = '"+name+"' ");
        }catch (SQLException e){
            e.printStackTrace();
        }

    }


}