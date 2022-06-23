package admin;

import java.sql.*;
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

    public static void registerCandidate(){
        Voter nv = new Voter();
        Admin nad = new Admin();
        Candidates nc = new Candidates();
        System.out.println("Enter Candidates First name: ");
        nv.setFirstName( new Scanner(System.in).next());

        System.out.println("Enter Candidates last name: ");
        nv.setLastName( new Scanner(System.in).next());

        System.out.println("Gender(M for male, F for female): ");
        nv.setGender( new Scanner(System.in).next());

        System.out.println("State of origin: ");
       nv.setState( new Scanner(System.in).next());

        System.out.println("Age: ");
        nv.setAge( new Scanner(System.in).nextInt());

        System.out.println("Position: ");
        nc.setPosition( new Scanner(System.in).next());

        System.out.println("Party: ");
        nc.setParty( new Scanner(System.in).next());

        try{
            Connection connection = DriverManager.getConnection("jdbc:mysql://DESKTOP-9M33U7D/mydb",
                    "root", "Cecilia2002");
            Statement statement = connection.createStatement();
            int add = statement.executeUpdate("insert into Registered_candidates (FirstName,lastName, Gender, State," +
                    "Age , position, party, votes) values('"+nv.getFirstName()+"','"+nv.getLastName()+"', '"+nv.getGender()+"', '"+nv.getState()+"','"+nv.getAge()+"'," +
                    " '"+nc.getPosition()+"', '"+nc.getParty()+"', '"+nc.getNumberOfVotes()+"') ");
        }catch (SQLException e){
            e.printStackTrace();
        }

    }
    public static void displayCandidate(){
        Voter nv = new Voter();
        Admin nad = new Admin();
        Candidates nc = new Candidates();
        try{
            Connection connection = DriverManager.getConnection("jdbc:mysql://DESKTOP-9M33U7D/mydb",
                    "root", "Cecilia2002");
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from registered_candidates");
            while (resultSet.next()){
                System.out.println(resultSet.getString("ID" )+"\t" + resultSet.getString("firstname") +"\t" +
                        resultSet.getString("lastname")+ "\t"+ resultSet.getString("position") +"\t"
                        +resultSet.getString( "party" ));
            }
        }catch (SQLException e){
            e.printStackTrace();
        }

    }

}
