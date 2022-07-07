package admin;

import java.sql.*;
import java.util.InputMismatchException;
import java.util.Scanner;

public class AdminMethods extends Voter {
    Connection connection = null;
    Statement statement = null;
    ResultSet resultSet = null;
    Admin admin = new Admin();
    Voter reg = new Voter();

    public static void database() throws SQLException {
        AdminMethods adminMethods = new AdminMethods();
        try {
            adminMethods.connection = DriverManager.getConnection("jdbc:mysql://DESKTOP-9M33U7D/mydb",
                    "root", "Cecilia2002");
            adminMethods.statement = adminMethods.connection.createStatement();
            adminMethods.statement.executeUpdate("create table voting_Database (ID " +
                    "int auto_increment primary key, firstName varchar(50), lastName varchar(50), Gender Enum('M', 'F'), State varchar(20)," +
                    "Age int, Position varchar (20), Date Date, Party varchar(10), Votes int,password varchar(50), Status varChar(12), hasVoted Enum('T', 'F'))");
        }catch (SQLSyntaxErrorException ex) {
            ex.printStackTrace();
            //System.out.println("Table already created");
        }catch (SQLException e) {
            e.printStackTrace();
        }finally {
            close();
        }
    }

    public static void close(){
        AdminMethods adminMethods = new AdminMethods();
        try {
            if(adminMethods.connection != null){
                adminMethods.connection.close();
            }
            if(adminMethods.statement != null){
                adminMethods.statement.close();
            }
        }catch (SQLException ex){
            ex.printStackTrace();
        }
    }
    public static void closeResult(){
        AdminMethods adminMethods = new AdminMethods();
        try {
            if(adminMethods.resultSet!= null){
                adminMethods.resultSet.close();
            }
        }catch (SQLException ex){
            ex.printStackTrace();
        }
    }

    public void addAdmins(){
        System.out.print("First name: ");
        setFirstName(new Scanner(System.in).next());

        System.out.print("Last name: ");
        setLastName(new Scanner(System.in).next());

        System.out.print("Gender: ");
        setGender(new Scanner(System.in).next());

        System.out.print("State of Origin: ");
        setState(new Scanner(System.in).next());

        System.out.print("Age: ");
        setAge(new Scanner(System.in).nextInt());

        System.out.print("Password: ");
        setPassword(new Scanner(System.in).next());
        try{
            connection = DriverManager.getConnection("jdbc:mysql://DESKTOP-9M33U7D/mydb",
                    "root", "Cecilia2002");
            statement = connection.createStatement();
            statement.executeUpdate("insert into voting_database (FirstName,lastName, Gender, State," +
                    "Age , password, Status) values('"+getFirstName()+"','"+getLastName()+"', '"+getGender()+"', '"+getState()+"','"+getAge()+"'," +
                    " '"+getPassword()+"', 'Admin') ");
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            close();
        }
    }
    public static void adminMenu() throws SQLException {
        AdminMethods adminMethods = new AdminMethods();
        System.out.println("1.Remove Admins \n2. Remove Candidate \n3. Edit password \n4. Check Results \n5. Add Candidate \n6. Add Admin");
        try {
            String input = new Scanner(System.in).next();
            switch (input){
                case "1":
                    adminMethods.RemoveAdmin();
                    break;
                case "2":
                    adminMethods.RemoveCandidates();
                    break;
                case "3":
                    System.out.println("Enter current password");
                    String currentPass = new Scanner(System.in).next();
                    System.out.println("Enter new password");
                    String newPass = new Scanner(System.in).next();
                    System.out.println("Confirm new password");
                    String confirmPass = new Scanner(System.in).next();
                    adminMethods.editAdminPassword(currentPass, newPass,confirmPass);
                    break;
                case "4":
                    ElectionMethods.results();
                    break;
                case "5":
                    CandidatesMethods.registerCandidate();
                    break;
                case "6":
                    adminMethods.addAdmins();
                default:
                    System.out.println("Wrong entry");
            }
        }catch (InputMismatchException exception){
            System.out.println("Wrong entry");
        }

    }

    public void RemoveCandidates(){
        System.out.print("First name: ");
        String removeFirstName =  new Scanner(System.in).next();
        System.out.print("Last name: ");
        String removeLastname =  new Scanner(System.in).next();
        System.out.print("Position name: ");
        String removePosition =  new Scanner(System.in).next();
        try{
            connection = DriverManager.getConnection("jdbc:mysql://DESKTOP-9M33U7D/mydb",
                    "root", "Cecilia2002");
            statement = connection.createStatement();
            statement.executeUpdate("delete from voting_database where firstName = '"+removeFirstName+"' && " +
                    "lastName = '"+removeLastname+"' && position = '"+removePosition+"' && status = 'candidate'");
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            close();
        }
    }

    public void RemoveAdmin(){
        System.out.print("First name: ");
        String removeFirstName =  new Scanner(System.in).next();
        System.out.print("Last name: ");
        String removeLastname =  new Scanner(System.in).next();
        try{
            connection = DriverManager.getConnection("jdbc:mysql://DESKTOP-9M33U7D/mydb",
                    "root", "Cecilia2002");
            statement = connection.createStatement();
            statement.executeUpdate("delete from voting_database where firstName = '"+removeFirstName+"' && " +
                    "lastName = '"+removeLastname+"' && status = 'Admin'");
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            close();
        }
    }
    public void editAdminPassword(String currentPass, String newPassword, String confirmNewPassword){
        try {
            connection = DriverManager.getConnection("jdbc:mysql://DESKTOP-9M33U7D/mydb",
                "root", "Cecilia2002");
            statement = connection.createStatement();
            resultSet = statement.executeQuery("select * from voting_database where status = admin");
            admin.setChangeCurrent(currentPass);
            while(resultSet.next()) {
                //check if password entered by user matches with current password
                if (admin.getChangeCurrent().equals(resultSet.getString("password"))) {
                    setPassFound(true);
                    admin.setNewPassword(newPassword);
                    admin.setConfirmPassword(confirmNewPassword);
                    //check confirm password and new password
                    if (admin.getConfirmPassword().equals(admin.getNewPassword())) {
                        updateAdminPassword();
                        System.out.println("Password Successfully Changed!");
                        break;
                    } else {
                        System.out.println("Password mismatch!");
                    }
                    break;
                } else {
                    setPassFound(false);
                }
            }if (!isPassFound()) {
                System.out.println("Incorrect password");
            }
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            close();
            closeResult();
        }
    }
    public void editVotersPassword(){
        try {
            connection = DriverManager.getConnection("jdbc:mysql://DESKTOP-9M33U7D/mydb",
                    "root", "Cecilia2002");
            statement = connection.createStatement();
            resultSet = statement.executeQuery("select * from voting_database where status = 'voter'");
            System.out.print("Current password: ");
            admin.setChangeCurrent(new Scanner(System.in).next());
            while(resultSet.next()) {
                //check if password entered by user matches with current password
                if (admin.getChangeCurrent().equals(resultSet.getString("password"))) {
                    setPassFound(true);
                    System.out.print("New password: ");
                    admin.setNewPassword(new Scanner(System.in).next());
                    System.out.print("Confirm new password: ");
                    admin.setConfirmPassword(new Scanner(System.in).next());
                    //check confirm password and new password
                    if (admin.getConfirmPassword().equals(admin.getNewPassword())) {
                        updateVotPassword();
                        System.out.println("Password Successfully Changed!");
                        break;
                    } else {
                        System.out.println("Password mismatch!");
                    }
                    break;
                } else {
                    setPassFound(false);
                }
            }if (!isPassFound()) {
                System.out.println("Incorrect password");
            }
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            close();
            closeResult();
        }
    }

    public boolean checkAdminPassword(String firstName, String password){
        try{
            connection = DriverManager.getConnection("jdbc:mysql://DESKTOP-9M33U7D/mydb",
                    "root", "Cecilia2002");
            statement = connection.createStatement();
            resultSet = statement.executeQuery("select * from voting_database where status = 'Admin'");
            reg.setLogInName(firstName);
            reg.setLogInPassword(password);
            while(resultSet.next()){
                if(reg.getLogInPassword().equals(resultSet.getString("password"))&&
                        reg.getLogInName().equalsIgnoreCase(resultSet.getString("firstName"))){
                    setPassFound(true);
                    System.out.println("Welcome");
                    AdminMethods.adminMenu();
                    break;
                }
                else {
                    setPassFound(false);
                }
            }
            if(!isPassFound()){
                System.out.println("Incorrect name or password");
            }
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            close();
            closeResult();
        }return false;
    }
    public void updateAdminPassword(){
        try{
            connection = DriverManager.getConnection("jdbc:mysql://DESKTOP-9M33U7D/mydb",
                    "root", "Cecilia2002");
            statement = connection.createStatement();
            statement.executeUpdate("update voting_database set password = '"+admin.getConfirmPassword()+"' where status = 'Admin'");
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            close();
        }
    }
    public void updateVotPassword(){
        try{
            connection = DriverManager.getConnection("jdbc:mysql://DESKTOP-9M33U7D/mydb",
                    "root", "Cecilia2002");
            statement = connection.createStatement();
            statement.executeUpdate("update voting_database set password = '"+admin.getConfirmPassword()+"' where status = 'Voter' &&" +
                    "password = '"+admin.getChangeCurrent()+"'");
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            AdminMethods.close();
        }
    }
}

