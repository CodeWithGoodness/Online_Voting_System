package admin;

import java.sql.*;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Voter {
    Connection connection = null;
    Statement statement = null;
    ResultSet resultSet = null;

    private String firstName, lastName,gender,state, password, logInName, logInPassword, voteCount;
    boolean registered = false;
    private boolean passFound = false;
    private int age;

    public boolean isRegistered() {
        return registered;
    }

    public void setRegistered(boolean registered) {
        this.registered = registered;
    }

    public String getVoteCount() {
        return voteCount;
    }

    public void setVoteCount(String voteCount) {
        this.voteCount = voteCount;
    }
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

}

