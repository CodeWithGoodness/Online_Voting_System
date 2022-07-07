package admin;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class VoterMethods {
    Voter voter = new Voter();
    public void Register(String firstName, String lastName,String gender, String state, int age, String password){
        try{
            voter.setFirstName(firstName);
            voter.setLastName(lastName);
            voter.setGender(gender);
            voter.setState(state);
            voter.setAge(age);
            voter.setPassword(password);

            voter.connection = DriverManager.getConnection("jdbc:mysql://DESKTOP-9M33U7D/mydb",
                    "root", "Cecilia2002");
            voter.statement = voter.connection.createStatement();
            voter.statement.executeUpdate("insert into voting_database (firstName,lastName, Gender, State," +
                    "Age , password, status) values('"+voter.getFirstName()+"','"+voter.getLastName()+"', '"+voter.getGender()+"'," +
                    " '"+voter.getState()+"','"+voter.getAge()+"','"+voter.getPassword()+"', 'Voter') ");
        }catch (SQLException e){
            e.printStackTrace();
        }catch (InputMismatchException ex){
            System.out.println("Data not entered correctly");
        }finally {
            AdminMethods.close();
            System.out.println("You've been registered!");
        }
        voter.registered = true;
    }
    public boolean logIn(String firstname, String password){
        try{
            voter.connection = DriverManager.getConnection("jdbc:mysql://DESKTOP-9M33U7D/mydb",
                    "root", "Cecilia2002");
            voter.statement = voter.connection.createStatement();
            voter.resultSet = voter.statement.executeQuery("select * from voting_database");
            voter.setLogInName(firstname);
            voter.setLogInPassword(password);
            while(voter.resultSet.next()){
                if(voter.getLogInPassword().equals(voter.resultSet.getString("password"))&&
                        voter.getLogInName().equalsIgnoreCase(voter.resultSet.getString("firstName"))){
                    voter.setPassFound(true);
                    System.out.println("Welcome");
                    votersMenu();
                    break;
                }
                else {
                    voter.setPassFound(false);
                }
            }
            if(!voter.isPassFound()){
                System.out.println("Incorrect name or password");
            }
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            AdminMethods.close();
            AdminMethods.closeResult();
            return false;
        }
    }
    public static void votersMenu(){
        AdminMethods adminMethods = new AdminMethods();
        System.out.println("1.View all candidates \n 2.Vote \n 3.View results \n 4. Change password");
        try {
            String input = new Scanner(System.in).next();
            switch (input){
                case "1":
                    CandidatesMethods.displayPresCandidates();
                    CandidatesMethods.displayGovCandidates();
                    break;
                case "2":
                    ElectionMethods.votingDay("07/07/2022");
                    break;
                case "3":
                    ElectionMethods.results();
                    break;
                case "4":
                    adminMethods.editPassword("tree", "admins", "admins");
                    break;
            }
        }catch (InputMismatchException e){
            System.out.println("invalid input");
        } catch (ParseException e) {
            e.printStackTrace();
        }

    }
    public static void Vote(String vote){
        Voter reg = new Voter();
        try
        {
            reg.connection = DriverManager.getConnection("jdbc:mysql://DESKTOP-9M33U7D/mydb",
                    "root", "Cecilia2002");
            reg.statement = reg.connection.createStatement();
            reg.resultSet = reg.statement.executeQuery("Select * from voting_database");
            reg.setVoteCount(vote);
            reg.statement.executeUpdate("update voting_database set votes = votes + 1 where ID = '"+reg.getVoteCount()+"'");
        }catch (SQLException e) {
            e.printStackTrace();
        }catch (InputMismatchException e){
            System.out.println("invalid input");
        }finally {
            AdminMethods.close();
            AdminMethods.closeResult();
        }
    }
}
