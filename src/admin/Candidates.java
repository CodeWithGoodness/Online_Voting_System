package admin;

import java.sql.*;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Candidates {
    private String position, party;
    private int numberOfVotes = 0;

    public int getNumberOfVotes() {
        return numberOfVotes;
    }
    public String getPosition() {
        return position;
    }
    public String getParty() {
        return party;
    }
    public void setNumberOfVotes(int numberOfVotes) {
        this.numberOfVotes = numberOfVotes;
    }

    public void setParty(String party) {
        this.party = party;
    }

    public void setPosition(String position) {
        this.position = position;
    }
    Connection connection = null;
    Statement statement = null;
    ResultSet resultSet = null;

    public static void registerCandidate() throws SQLException {
        Voter nv = new Voter();
        Admin nad = new Admin();
        Candidates nc = new Candidates();
        while (true) {
            try {
                System.out.println("Enter Candidates First name: ");
                nv.setFirstName(new Scanner(System.in).next());

                System.out.println("Enter Candidates last name: ");
                nv.setLastName(new Scanner(System.in).next());

                System.out.println("Gender(M for male, F for female): ");
                nv.setGender(new Scanner(System.in).next());

                System.out.println("State of origin: ");
                nv.setState(new Scanner(System.in).next());

                System.out.println("Age: ");
                nv.setAge(new Scanner(System.in).nextInt());

                System.out.println("Position: ");
                nc.setPosition(new Scanner(System.in).next());

                System.out.println("Party: ");
                nc.setParty(new Scanner(System.in).next());
                nc.connection = DriverManager.getConnection("jdbc:mysql://DESKTOP-9M33U7D/mydb",
                        "root", "Cecilia2002");
                nc.statement = nc.connection.createStatement();
                nc.statement.executeUpdate("insert into voting_database (FirstName,lastName, Gender, State," +
                        "Age , position, party, votes, status) values('" + nv.getFirstName() + "','" + nv.getLastName() + "', '" + nv.getGender() + "', '" + nv.getState() + "','" + nv.getAge() + "'," +
                        " '" + nc.getPosition() + "', '" + nc.getParty() + "', '" + nc.getNumberOfVotes() + "', 'Candidate') ");
                System.out.print("Press 0 if there are no more candidates to add and 1 if you wish to add more: ");
                String input = new Scanner(System.in).next();
                if (input.equals("0")) {
                    General.Display();
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
                Admin.close();
            }
        }
    }
    public static void displayPresCandidates(){
        Voter nv = new Voter();
        Admin nad = new Admin();
        Candidates nc = new Candidates();
        try{
             nc.connection = DriverManager.getConnection("jdbc:mysql://DESKTOP-9M33U7D/mydb",
                    "root", "Cecilia2002");
            nc.statement = nc.connection.createStatement();
            nc.resultSet = nc.statement.executeQuery("select * from voting_database where status = 'candidate' && position = 'president'");
            System.out.println("Presidential candidates");
            while(nc.resultSet.next()){
                System.out.println(nc.resultSet.getString("ID" )+"\t" + nc.resultSet.getString("firstname") +" " +
                        nc.resultSet.getString("lastname")+ "\t"+ nc.resultSet.getString( "party" ).toUpperCase());
            }
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            Admin.close();
            Admin.closeResult();
        }
    }
    public static void displayGovCandidates(){
        Voter nv = new Voter();
        Admin nad = new Admin();
        Candidates nc = new Candidates();
        try{
             nc.connection = DriverManager.getConnection("jdbc:mysql://DESKTOP-9M33U7D/mydb",
                    "root", "Cecilia2002");
            Statement statement = nc.connection.createStatement();
            nc.resultSet = statement.executeQuery("select * from voting_database where status = 'candidate' && position = 'governor'");
            System.out.println("Governorship candidates");
            while (nc.resultSet.next()){
                System.out.println(nc.resultSet.getString("ID" )+"\t" + nc.resultSet.getString("firstname") +" " +
                        nc.resultSet.getString("lastname")+"\t"+nc.resultSet.getString( "party" ).toUpperCase());
            }
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            Admin.close();
            Admin.closeResult();

        }
    }
}
