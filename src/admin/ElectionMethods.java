package admin;

import java.text.ParseException;

public class ElectionMethods {
    public static void votingDay() throws ParseException {
        Election election = new Election();
//        election.setElectionDate("05/07/2022");
        System.out.println(election.getCurrentDate());
//        System.out.println(election.getVotingDate());
//        if (election.getCurrentDate().compareTo(election.getVotingDate()) == 0){
//            System.out.println("foo");
//        }
//        else
//            System.out.println("foo2");
    }
}
