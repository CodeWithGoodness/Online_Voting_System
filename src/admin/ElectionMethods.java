package admin;

import java.sql.*;
import java.text.ParseException;
import java.util.Scanner;

public class ElectionMethods {
    public static void votingDay( String date) throws ParseException {
        Election election = new Election();
        Voter voter = new Voter();
        election.setElectionDate(date);
        if (election.getCurrentDate().compareTo(election.getVotingDate2()) == 0){
            try{
                voter.connection = DriverManager.getConnection("jdbc:mysql://DESKTOP-9M33U7D/mydb", "root", "Cecilia2002");
                voter.statement = voter.connection.createStatement();
                System.out.print("First name: ");
                voter.setFirstName(new Scanner(System.in).next());
                System.out.print("Password: ");
                voter.setPassword(new Scanner(System.in).next());
                voter.resultSet = voter.statement.executeQuery
                        ("select * from voting_database where status = 'voter' && firstName = '"+voter.getFirstName()+"' && password = '"+voter.getPassword()+"'");
                while(voter.resultSet.next()){
                    if(voter.resultSet.getString("hasVoted").equalsIgnoreCase("t")){
                        System.out.println("You've voted already");
                        break;
                    }
                    else {
                        System.out.println("Vote by entering your choice of Candidate's number as displayed");
                        CandidatesMethods.displayPresCandidates();
                        voter.setVoteCount(new Scanner(System.in).next());
                        voter.statement.executeUpdate("update voting_database set votes = votes + 1 where ID = '"+voter.getVoteCount()+"'");

                        CandidatesMethods.displaySenCandidates();
                        voter.setVoteCount(new Scanner(System.in).next());
                        voter.statement.executeUpdate("update voting_database set votes = votes + 1 where ID = '"+voter.getVoteCount()+"'");
                        voter.setHasVoted(true);
                        voter.statement.executeUpdate("update voting_database set hasVoted = 'T' where firstName = '"+voter.getFirstName()+"'");
                    }
                }
            }catch (SQLException e){
                e.printStackTrace();
            }finally {
                AdminMethods.close();
                AdminMethods.closeResult();
            }
        }
        else if (election.getCurrentDate().compareTo(election.getVotingDate2()) > 0) {
            System.out.println("Voting ended " + election.getDifference() + " day(s) ago!");
        }else {
            System.out.println("Voting is in " + election.getDifference() + " day(s) time!");
        }
    }

    public static void results(){
        try{
            Connection connection = DriverManager.getConnection("jdbc:mysql://DESKTOP-9M33U7D/mydb",
                    "root", "Cecilia2002");
            Statement statement = connection.createStatement();
           ResultSet resultSet = statement.executeQuery("select * from voting_database where status = 'candidate' && position = 'Senate'");
            System.out.println("Senatorial candidates");
            while (resultSet.next()){
                System.out.println( resultSet.getString("firstname") +" "+ resultSet.getString("lastname")+
                        "\t"+resultSet.getString( "party" ).toUpperCase()+ "\t" + resultSet.getString("votes"));
            }
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            AdminMethods.close();
            AdminMethods.closeResult();
        }
    }
}
