package admin;

import java.sql.*;
import java.util.InputMismatchException;
import java.util.Scanner;

public class CandidatesMethods {
    Connection connection = null;
    Statement statement = null;
    ResultSet resultSet = null;

    public static void registerCandidate() throws SQLException {
        Voter nv = new Voter();
        Candidates nc = new Candidates();
        CandidatesMethods candidatesMethods = new CandidatesMethods();
        while (true) {
            try {
                System.out.print("First Name: ");
                nv.setFirstName(new Scanner(System.in).next());

                System.out.print("Last name: ");
                nv.setLastName(new Scanner(System.in).next());

                System.out.print("Gender: ");
                nv.setGender(new Scanner(System.in).next());

                System.out.print("Origin: ");
                nv.setState(new Scanner(System.in).next());

                System.out.print("Age: ");
                nv.setAge( new Scanner(System.in).nextInt());

                System.out.print("Position(President or Senate): ");
                nc.setPosition( new Scanner(System.in).next());

                System.out.print("party: ");
                nc.setParty(new Scanner(System.in).next());

                candidatesMethods.connection = DriverManager.getConnection("jdbc:mysql://DESKTOP-9M33U7D/mydb",
                        "root", "Cecilia2002");
                candidatesMethods.statement = candidatesMethods.connection.createStatement();
                candidatesMethods.statement.executeUpdate("insert into voting_database (FirstName,lastName, Gender, State," +
                        "Age , position, party, votes, status) values('" + nv.getFirstName() + "','" + nv.getLastName() + "', '" + nv.getGender() + "', '" + nv.getState() + "','" + nv.getAge() + "'," +
                        " '" + nc.getPosition() + "', '" + nc.getParty() + "', '" + nc.getNumberOfVotes() + "', 'Candidate') ");
                System.out.print("Press 0 if there are no more candidates to add and 1 if you wish to add more: ");
                String input = new Scanner(System.in).next();
                if (input.equals("0")) {
                    AdminMethods.clearScreen();
                    break;
                } else if (input.equals("1")) {
                    AdminMethods.clearScreen();
                    continue;
                } else
                    System.out.print("Press 0 if there are no more candidates to add and 1 if you wish to add more");
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
    public static void displaySenCandidates(){
        CandidatesMethods candidatesMethods = new CandidatesMethods();
        try{
            candidatesMethods.connection = DriverManager.getConnection("jdbc:mysql://DESKTOP-9M33U7D/mydb",
                    "root", "Cecilia2002");
            Statement statement = candidatesMethods.connection.createStatement();
            candidatesMethods.resultSet = statement.executeQuery("select * from voting_database where status = 'candidate' && position = 'Senate'");
            System.out.println();
            System.out.println("Senatorial candidates");
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
