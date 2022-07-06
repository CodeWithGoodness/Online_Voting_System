package admin;

import java.text.ParseException;

public class ElectionMethods {
    public static void votingDay() throws ParseException {
        Election election = new Election();
        AdminMethods.dateOfElection();
        election.setElectionDate("06/07/2022");
        if (election.getCurrentDate().compareTo(election.getVotingDate2()) == 0){
            VoterMethods.Vote();
       }
        else if (election.getCurrentDate().compareTo(election.getVotingDate2()) > 0) {
            System.out.println("Voting ended " + election.getDifference() + " day(s) ago!");
        }else {
            System.out.println("Voting is in " + election.getDifference() + " day(s) time!");
        }
    }
}
