package admin;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.Scanner;

public class Test {
    public static void main(String[]args) throws ParseException, SQLException {
        Admin admin = new Admin();
        AdminMethods adminMethods = new AdminMethods();
        Election election = new Election();
        CandidatesMethods candidatesMethods = new CandidatesMethods();
        ElectionMethods electionMethods = new ElectionMethods();
//        electionMethods.changeVotingDay("07/07/2022");
//       ElectionMethods.votingDay("6/7/2022");
        VoterMethods.votersMenu();

    }
}
