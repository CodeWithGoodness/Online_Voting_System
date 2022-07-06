package admin;

import java.text.ParseException;

public class ElectionMethods {
    public static void votingDay( String date) throws ParseException {
        Election election = new Election();
        election.setElectionDate(date);
        if (election.getCurrentDate().compareTo(election.getVotingDate2()) == 0){
            System.out.println("");
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
}
