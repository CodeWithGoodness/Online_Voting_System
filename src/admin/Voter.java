package admin;

import java.sql.*;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Voter {
    private String voteCount;

    public String getVoteCount() {
        return voteCount;
    }

    public void setVoteCount(String voteCount) {
        this.voteCount = voteCount;
    }

    private String firstName;
    private String lastName;
    boolean registered = false;
    private boolean passFound = false;
    private int age;
    private String gender;
    private String state;
    private String password;
    private String logInName;
    private String logInPassword;

    public boolean isPassFound() {
        return passFound;
    }

    public void setPassFound(boolean passFound) {
        this.passFound = passFound;
    }

    public String getLogInName() {
        return logInName;
    }

    public void setLogInName(String logInName) {
        this.logInName = logInName;
    }

    public String getLogInPassword() {
        return logInPassword;
    }

    public void setLogInPassword(String logInPassword) {
        this.logInPassword = logInPassword;
    }
    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getPassword() {
        return password;
    }

    public  void setPassword(String password) {
        this.password = password;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName() {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void Register(){
        Admin reg = new Admin();
        System.out.println("First Name: " );
        setFirstName(new Scanner(System.in).next());

        System.out.println("Last Name: " );
        setLastName(new Scanner(System.in).next());

        System.out.println("Gender: ");
        setGender(new Scanner(System.in).next());

        System.out.println("State of residence: ");
        setState(new Scanner(System.in).next());

        System.out.println("Age: ");
        setAge(new Scanner(System.in).nextInt());

        System.out.println("Password: ");
        setPassword(new Scanner(System.in).next());
        try{
            Connection connection = DriverManager.getConnection("jdbc:mysql://DESKTOP-9M33U7D/mydb",
                    "root", "Cecilia2002");
            Statement statement = connection.createStatement();
            statement.executeUpdate("insert into voting_database (firstName,lastName, Gender, State," +
                    "Age , password, status) values('"+getFirstName()+"','"+getLastName()+"', '"+getGender()+"', '"+getState()+"','"+getAge()+"'," +
                    " '"+getPassword()+"', 'Voter') ");
        }catch (SQLException e){
            e.printStackTrace();
        }
        registered = true;
    }
    public boolean checkPassword(){
        Voter reg = new Voter();
        try{
            Connection connection = DriverManager.getConnection("jdbc:mysql://DESKTOP-9M33U7D/mydb",
                    "root", "Cecilia2002");
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from voting_database");
            System.out.println("Enter your First Name: ");
            reg.setLogInName( new Scanner(System.in).next());
            System.out.println("Enter your password: ");
            reg.setLogInPassword( new Scanner(System.in).next());
            while(resultSet.next()){
                if(reg.logInPassword.equals(resultSet.getString("password"))&&
                        reg.logInName.equalsIgnoreCase(resultSet.getString("firstName"))){
                   setPassFound(true);
                    System.out.println("Welcome");
                    break;
                }
                else {
                    setPassFound(false);
                }
            }
            if(!passFound){
                System.out.println("Incorrect password");
            }
            }catch (SQLException e){
            e.printStackTrace();
        }return false;
    }
    public static void LogIn(){
        Voter reg = new Voter();
        if(reg.registered = true){
            reg.checkPassword();
        }
    }
    public static void Vote(){
        Voter reg = new Voter();
       try
       {
           Connection connection = DriverManager.getConnection("jdbc:mysql://DESKTOP-9M33U7D/mydb",
                   "root", "Cecilia2002");
           Statement statement = connection.createStatement();
           ResultSet resultSet = statement.executeQuery("Select * from voting_database");
           System.out.println("Vote by entering your choice of Candidate's number as displayed");
           reg.setVoteCount(new Scanner(System.in).next());
           statement.executeUpdate("update voting_database set votes = votes + 1 where ID = '"+reg.getVoteCount()+"'");
          // if(resultSet.getString("ID").contains( reg.getVoteCount())){
           //}
       }catch (SQLException e) {
           e.printStackTrace();
       }catch (InputMismatchException e){
           System.out.println("invalid input");
       }
    }
}

