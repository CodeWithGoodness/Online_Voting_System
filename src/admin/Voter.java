package admin;

import java.sql.*;
import java.util.Scanner;

public class Voter {
    private String name;
    boolean registered = false;
    private int age;
    private String gender;
    private String state;
    private String password;
    private String logInName;
    private String logInPassword;

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    public void Register(){
        Voter reg = new Voter();
        Scanner input = new Scanner(System.in);
        System.out.println("Name: " );
        setName(input.next());
        System.out.println("Gender: ");
        setGender(input.next());
        System.out.println("State of residence: ");
        setState(input.next());
        System.out.println("Age: ");
        setAge(input.nextInt());
        System.out.println("Password: ");
        setPassword(input.next());
        try{
            Connection connection = DriverManager.getConnection("jdbc:mysql://DESKTOP-9M33U7D/mydb",
                    "root", "Cecilia2002");
            Statement statement = connection.createStatement();
            int register = statement.executeUpdate("insert into Registered_Voters (Name,Gender, State," +
                    "Age , password) values('"+name+"', '"+gender+"', '"+state+"','"+age+"'," +
                    " '"+password+"') ");
        }catch (SQLException e){
            e.printStackTrace();
        }registered = true;
    }
    public boolean checkPassword(){
        Voter reg = new Voter();
        try{
            Connection connection = DriverManager.getConnection("jdbc:mysql://DESKTOP-9M33U7D/mydb",
                    "root", "Cecilia2002");
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from account where   ");
            while(resultSet.next()){
                if(reg.logInPassword.equals(resultSet.getString("password"))&&
                        reg.logInName.equalsIgnoreCase(resultSet.getString("Name"))){
                    return true;
                }else
                    System.out.println("Incorrect password");
            }
        }catch (SQLException e){
            e.printStackTrace();
        }return false;
    }
    public static void LogIn(){
        Voter reg = new Voter();
        Scanner input = new Scanner(System.in);
        System.out.println("Login as? \n 1. Admin \n 2.User");
        String log = input.next();
        switch (log){
            case "1":
                if(reg.registered = true){
                    System.out.println("Enter your Name: ");
                    reg.setLogInName(input.next());
                    System.out.println("Enter your password: ");
                    reg.setLogInPassword(input.next());
                   reg.checkPassword();
                }

        }
    }
}
