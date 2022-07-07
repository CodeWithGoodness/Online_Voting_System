package admin;

import java.sql.*;
import java.text.ParseException;
import java.util.Scanner;

public class ElectionMethods {
    public static void votingDay( String date) throws ParseException {
        Election election = new Election();
        election.setElectionDate(date);
        if (election.getCurrentDate().compareTo(election.getVotingDate2()) == 0){
            System.out.println("1. Presidential election \n 2. Governorship election");
            String input2 = new Scanner(System.in).next();
            System.out.println("Vote by entering your choice of Candidate's number as displayed");
            if (input2.equals("1")){
                CandidatesMethods.displayPresCandidates();
                VoterMethods.Vote(new Scanner(System.in).next());
            }else  if (input2.equals("2")){
                CandidatesMethods.displayGovCandidates();
                VoterMethods.Vote("");
            }else{
                System.out.println("wrong input!");
            }

        }
        else if (election.getCurrentDate().compareTo(election.getVotingDate2()) > 0) {
            System.out.println("Voting ended " + election.getDifference() + " day(s) ago!");
        }else {
            System.out.println("Voting is in " + election.getDifference() + " day(s) time!");
        }
    }
    public void changeVotingDay(String date) throws ParseException {
        Election election = new Election();
        election.setElectionDate(date);
    }
    public static void results(){
        try{
            Connection connection = DriverManager.getConnection("jdbc:mysql://DESKTOP-9M33U7D/mydb",
                    "root", "Cecilia2002");
            Statement statement = connection.createStatement();
           ResultSet resultSet = statement.executeQuery("select * from voting_database where status = 'candidate' && position = 'governor'");
            System.out.println("Governorship candidates");
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
