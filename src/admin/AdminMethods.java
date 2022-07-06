package admin;

import java.sql.*;
import java.util.Scanner;

public class AdminMethods extends Voter {
    Connection connection = null;
    Statement statement = null;
    ResultSet resultSet = null;
    Admin admin = new Admin();
    Voter reg = new Voter();

    public static void database() throws SQLException {
        AdminMethods adminMethods = new AdminMethods();
        try {
            adminMethods.connection = DriverManager.getConnection("jdbc:mysql://DESKTOP-9M33U7D/mydb",
                    "root", "Cecilia2002");
            adminMethods.statement = adminMethods.connection.createStatement();
            int create = adminMethods.statement.executeUpdate("create table voting_Database (ID " +
                    "int auto_increment primary key, firstName varchar(50), lastName varchar(50), Gender Enum('M', 'F'), State varchar(20)," +
                    "Age int, Position varchar (20), Date Date, Party varchar(10), Votes int,password varchar(50), Status varChar(12))");
        }catch (SQLSyntaxErrorException ex) {
            System.out.println("Table already created");
        }catch (SQLException e) {
            e.printStackTrace();
        }finally {
            close();
            adminMethods.resultSet.close();
        }
    }

    public static void close(){
        AdminMethods adminMethods = new AdminMethods();
        try {
            if(adminMethods.connection != null){
                adminMethods.connection.close();
            }
            if(adminMethods.statement != null){
                adminMethods.statement.close();
            }
        }catch (SQLException ex){
            ex.printStackTrace();
        }
    }
    public static void closeResult(){
        AdminMethods adminMethods = new AdminMethods();
        try {
            if(adminMethods.resultSet!= null){
                adminMethods.resultSet.close();
            }
        }catch (SQLException ex){
            ex.printStackTrace();
        }
    }

    public void addAdmins(String fName, String lname, String gender, String Origin, int age){
        setFirstName(fName);
        setLastName( lname);
        setGender(gender);
        setState(Origin);
        setAge( age);
        try{
            connection = DriverManager.getConnection("jdbc:mysql://DESKTOP-9M33U7D/mydb",
                    "root", "Cecilia2002");
            statement = connection.createStatement();
            statement.executeUpdate("insert into voting_database (FirstName,lastName, Gender, State," +
                    "Age , password, Status) values('"+getFirstName()+"','"+getLastName()+"', '"+getGender()+"', '"+getState()+"','"+getAge()+"'," +
                    " 'Admin@onlineVoting.com', 'Admin') ");
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            close();
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
            connection = DriverManager.getConnection("jdbc:mysql://DESKTOP-9M33U7D/mydb",
                    "root", "Cecilia2002");
            statement = connection.createStatement();
            statement.executeUpdate("delete from database where firstName = '"+removeFirstName+"' && " +
                    "lastName = '"+removeLastname+"' && position = '"+removePosition+"' && status = 'candidate'");
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            close();
        }
    }

    public void RemoveAdmin(){
        System.out.print("First name: ");
        String removeFirstName =  new Scanner(System.in).next();
        System.out.print("Last name: ");
        String removeLastname =  new Scanner(System.in).next();
        try{
            connection = DriverManager.getConnection("jdbc:mysql://DESKTOP-9M33U7D/mydb",
                    "root", "Cecilia2002");
            statement = connection.createStatement();
            statement.executeUpdate("delete from Admin where firstName = '"+removeFirstName+"' && " +
                    "lastName = '"+removeLastname+"' && status = 'Admin'");
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            close();
        }
    }
    public void editPassword(){
        try {
            connection = DriverManager.getConnection("jdbc:mysql://DESKTOP-9M33U7D/mydb",
                "root", "Cecilia2002");
            statement = connection.createStatement();
            resultSet = statement.executeQuery("select * from voting_database");
            System.out.println("Enter current password ");
            admin.setChangeCurrent(new Scanner(System.in).next());
            while(resultSet.next()){
                if(admin.getChangeCurrent().equals(resultSet.getString("password"))){
                    System.out.println("Enter new password ");
                    admin.setNewPassword(new Scanner(System.in).next());
                    System.out.println("Confirm new password ");
                    admin.setConfirmPassword(new Scanner(System.in).next());
                    if(admin.getConfirmPassword().equals(admin.getNewPassword())){
                        updateAdminPassword();
                        System.out.println("Password Successfully Changed!");
                        break;
                    }
                    else {
                        System.out.println("Password mismatch!");
                    }
                    break;
                }
                else {
                    setPassFound(false);
                }
            }
            if(!isPassFound()){
                System.out.println("Incorrect password");
            }
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            close();
            closeResult();
        }
    }
    public boolean checkAdminPassword(String firstName, String password){
        try{
            connection = DriverManager.getConnection("jdbc:mysql://DESKTOP-9M33U7D/mydb",
                    "root", "Cecilia2002");
            statement = connection.createStatement();
            resultSet = statement.executeQuery("select * from voting_database where status = 'Admin' || status = 'Voter'");
            reg.setLogInName(firstName);
            reg.setLogInPassword(password);
            while(resultSet.next()){
                if(reg.getLogInPassword().equals(resultSet.getString("password"))&&
                        reg.getLogInName().equalsIgnoreCase(resultSet.getString("firstName"))){
                    setPassFound(true);
                    System.out.println("Welcome");
                    break;
                }
                else {
                    setPassFound(false);
                }
            }
            if(!isPassFound()){
                System.out.println("Incorrect name or password");
            }
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            close();
            closeResult();
        }return false;
    }
    public void updateAdminPassword(){
        try{
            connection = DriverManager.getConnection("jdbc:mysql://DESKTOP-9M33U7D/mydb",
                    "root", "Cecilia2002");
            statement = connection.createStatement();
            statement.executeUpdate("update voting_database set password = '"+admin.getConfirmPassword()+"' where status = 'Admin'");
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            close();
        }
    }
    public void updateVotPassword(){
        try{
            connection = DriverManager.getConnection("jdbc:mysql://DESKTOP-9M33U7D/mydb",
                    "root", "Cecilia2002");
            statement = connection.createStatement();
            statement.executeUpdate("update voting_database set password = '"+admin.getConfirmPassword()+"' where status = 'Voter' &&" +
                    "password = '"+admin.getChangeCurrent()+"'");
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            AdminMethods.close();
        }
    }
}

