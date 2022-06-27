package admin;
import java.sql.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class Admin extends Voter {
    private String changeCurrent;

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    private String confirmPassword;

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    private String newPassword;
    Connection connection = null;
    Statement statement = null;
    ResultSet resultSet = null;
    public void database() throws SQLException {
        try {
            connection = DriverManager.getConnection("jdbc:mysql://DESKTOP-9M33U7D/mydb",
                    "root", "Cecilia2002");
            statement = connection.createStatement();
            int create = statement.executeUpdate("create table voting_Database (ID " +
                    "int auto_increment primary key, firstName varchar(50), lastName varchar(50), Gender Enum('M', 'F'), State varchar(20)," +
                    "Age int, Position varchar (20), Date Date, Party varchar(10), Votes int,password varchar(50), Status varChar(12))");
        }catch (SQLSyntaxErrorException ex) {
            System.out.println("Table already created");
        }catch (SQLException e) {
            e.printStackTrace();
        }finally {
            close();
            }
    }

    public static void close(){
        Admin admin= new Admin();
        try {
            if(admin.connection != null){
                admin.connection.close();
            }
            if(admin.statement != null){
                admin.statement.close();
            }
        }catch (SQLException ex){
            ex.printStackTrace();
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

    public String getChangeCurrent() {
        return changeCurrent;
    }

    public void setChangeCurrent(String changeCurrent) {
        this.changeCurrent = changeCurrent;
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
                System.out.println("Enter current password ");
                setChangeCurrent(new Scanner(System.in).next());
                if(getPassword().equals(getChangeCurrent())){
                    System.out.println("Enter new password ");
                    setNewPassword(new Scanner(System.in).next());
                    System.out.println("Confirm new password ");
                    setConfirmPassword(new Scanner(System.in).next());
                    if(getConfirmPassword().equals(getNewPassword())){
                        System.out.println("Password Successfully Changed!");
                        updatePassword();
                    }
                    else {
                        System.out.println("Password mismatch!");
                    }
                }else {
                    System.out.println("Password incorrect!");
                }
}
    public boolean checkAdminPassword(){
        Voter reg = new Voter();
        try{
             connection = DriverManager.getConnection("jdbc:mysql://DESKTOP-9M33U7D/mydb",
                    "root", "Cecilia2002");
             statement = connection.createStatement();
             resultSet = statement.executeQuery("select * from voting_database where status = 'Admin'");
            System.out.print("Enter your password: ");
            reg.setLogInPassword( new Scanner(System.in).next());
            System.out.print("Enter your First Name: ");
            reg.setLogInName(new Scanner(System.in).next());
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
        }return false;
}
    public void updatePassword(){
        try{
            connection = DriverManager.getConnection("jdbc:mysql://DESKTOP-9M33U7D/mydb",
                    "root", "Cecilia2002");
            statement = connection.createStatement();
            statement.executeUpdate("update database set password = '"+getConfirmPassword()+"' where status = 'Admin'");
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            close();
        }
    }
    }
