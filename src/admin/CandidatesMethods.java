package admin;

import java.sql.*;
import java.util.InputMismatchException;
import java.util.Scanner;

public class CandidatesMethods {
    Connection connection = null;
    Statement statement = null;
    ResultSet resultSet = null;

    public static void registerCandidate(String firstName, String lastName,String gender, String origin, int age, String position, String party) throws SQLException {
        Voter nv = new Voter();
        Admin nad = new Admin();
        Candidates nc = new Candidates();
        CandidatesMethods candidatesMethods = new CandidatesMethods();
        while (true) {
            try {
                nv.setFirstName(firstName);
                nv.setLastName(lastName);
                nv.setGender(gender);
                nv.setState(origin);
                nv.setAge(age);
                nc.setPosition(position);
                nc.setParty(party);
                candidatesMethods.connection = DriverManager.getConnection("jdbc:mysql://DESKTOP-9M33U7D/mydb",
                        "root", "Cecilia2002");
                candidatesMethods.statement = candidatesMethods.connection.createStatement();
                candidatesMethods.statement.executeUpdate("insert into voting_database (FirstName,lastName, Gender, State," +
                        "Age , position, party, votes, status) values('" + nv.getFirstName() + "','" + nv.getLastName() + "', '" + nv.getGender() + "', '" + nv.getState() + "','" + nv.getAge() + "'," +
                        " '" + nc.getPosition() + "', '" + nc.getParty() + "', '" + nc.getNumberOfVotes() + "', 'Candidate') ");
                System.out.print("Press 0 if there are no more candidates to add and 1 if you wish to add more: ");
                String input = new Scanner(System.in).next();
                if (input.equals("0")) {
                    break;
                } else if (input.equals("1")) {
                    continue;
                } else
                    System.out.print("Press 0 if there are no more candidates to add and 1 if you wish to add more: ");
            }
            catch(SQLException e){
                e.printStackTrace();
            }catch(InputMismatchException ex){
                System.out.println("There's an error in your input");
            }finally {
                AdminMethods.close();
            }
        }
    }
    public static void displayPresCandidates(){
        Voter nv = new Voter();
        Admin nad = new Admin();
        Candidates nc = new Candidates();
        CandidatesMethods candidatesMethods = new CandidatesMethods();
        try{
            candidatesMethods.connection = DriverManager.getConnection("jdbc:mysql://DESKTOP-9M33U7D/mydb",
                    "root", "Cecilia2002");
            candidatesMethods.statement = candidatesMethods.connection.createStatement();
            candidatesMethods.resultSet = candidatesMethods.statement.executeQuery("select * from voting_database where status = 'candidate' && position = 'president'");
            System.out.println("Presidential candidates");
            while(candidatesMethods.resultSet.next()){
                System.out.println(candidatesMethods.resultSet.getString("ID" )+"\t" + candidatesMethods.resultSet.getString("firstname") +" " +
                        candidatesMethods.resultSet.getString("lastname")+ "\t"+ candidatesMethods.resultSet.getString( "party" ).toUpperCase());
            }
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            AdminMethods.close();
            AdminMethods.closeResult();
        }
    }
    public static void displayGovCandidates(){
        Voter nv = new Voter();
        Admin nad = new Admin();
        Candidates nc = new Candidates();
        CandidatesMethods candidatesMethods = new CandidatesMethods();
        try{
            candidatesMethods.connection = DriverManager.getConnection("jdbc:mysql://DESKTOP-9M33U7D/mydb",
                    "root", "Cecilia2002");
            Statement statement = candidatesMethods.connection.createStatement();
            candidatesMethods.resultSet = statement.executeQuery("select * from voting_database where status = 'candidate' && position = 'governor'");
            System.out.println("Governorship candidates");
            while (candidatesMethods.resultSet.next()){
                System.out.println(candidatesMethods.resultSet.getString("ID" )+"\t" + candidatesMethods.resultSet.getString("firstname") +" " +
                        candidatesMethods.resultSet.getString("lastname")+"\t"+candidatesMethods.resultSet.getString( "party" ).toUpperCase());
            }
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            AdminMethods.close();
            AdminMethods.closeResult();
        }
    }
}
