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
            voter.setHasVoted(false);

            voter.connection = DriverManager.getConnection("jdbc:mysql://DESKTOP-9M33U7D/mydb",
                    "root", "Cecilia2002");
            voter.statement = voter.connection.createStatement();
            voter.statement.executeUpdate("insert into voting_database (firstName,lastName, Gender, State," +
                    "Age , password, status, hasVoted) values('"+voter.getFirstName()+"','"+voter.getLastName()+"', '"+voter.getGender()+"'," +
                    " '"+voter.getState()+"','"+voter.getAge()+"','"+voter.getPassword()+"', 'Voter', 'F') ");
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
    public void checkVoted(){//checks for people that have voted already

    }
    public static void votersMenu(){
        AdminMethods adminMethods = new AdminMethods();
        System.out.println("1.View all candidates \n2.Vote \n3.View results \n4. Change password");
        try {
            String input = new Scanner(System.in).next();
            switch (input){
                case "1":
                    CandidatesMethods.displayPresCandidates();
                    CandidatesMethods.displaySenCandidates();
                    break;
                case "2":
                    ElectionMethods.votingDay("09/07/2022");
                    break;
                case "3":
                    ElectionMethods.results();
                    break;
                case "4":
                    adminMethods.editVotersPassword();
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
            Voter voter = new Voter();
            voter.setHasVoted(true);
            reg.statement.executeUpdate("update voting_database set hasVoted = 'T' where firstName = '"+reg.getFirstName()+"'");
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
