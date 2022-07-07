package admin;
import  java.util.Scanner;
public class General {
    public static void main(String[]args) {
        VoterMethods voterMethods = new VoterMethods();
        AdminMethods adminMethods = new AdminMethods();
        Scanner input = new Scanner(System.in);
        while (true){
                System.out.println("Welcome to the online voting System. Are you a user or an admin? \n" +
                        "1. User \n 2.Admin");
                String userInput1=  input.next();
                switch (userInput1){
                    case "1":
                        System.out.println(" 1. Register  \n 2.Login (if you have an account already)");
                        String userInput2=  input.next();
                        switch (userInput2){
                            case "1":
                                System.out.print("First name: ");
                                String firstName = input.next();
                                System.out.print("Last name: ");
                                String lastName = input.next();
                                System.out.print("Gender: ");
                                String gender = input.next();
                                System.out.print("State of Residence: ");
                                String origin = input.next();
                                System.out.print("Age: ");
                                int age = input.nextInt();
                                System.out.print("Password: ");
                                String password = input.next();

                                voterMethods.Register(firstName, lastName, gender, origin, age, password);
                                break;
                            case "2":
                                System.out.print("First name: ");
                                String firstName2 = input.next();
                                System.out.print("Password: ");
                                String password2 = input.next();
                                voterMethods.logIn(firstName2, password2 );
                                break;
                            default:
                                System.out.println("Press 1 to register or 2 to login");
                        }
                        break;
                    case "2":
                        System.out.println(" 1. Register  \n 2.Login (if you have an account already)");
                        String userInput3=  input.next();
                        switch (userInput3) {
                            case "1":
                                System.out.print("First name: ");
                                String firstName = input.next();
                                System.out.print("Last name: ");
                                String lastName = input.next();
                                System.out.print("Gender: ");
                                String gender = input.next();
                                System.out.print("State of Origin: ");
                                String origin = input.next();
                                System.out.print("Age: ");
                                int age = input.nextInt();
                                System.out.print("Password: ");
                                String password = input.next();

                                adminMethods.addAdmins(firstName, lastName, gender, origin, age, password);
                                break;
                            case "2":
                                System.out.print("Enter your First Name: ");
                                String firstName2 = input.next();
                                System.out.print("Enter your password: ");
                                String password2 = input.next();

                                adminMethods.checkAdminPassword(firstName2, password2);
                                break;
                            default:
                                System.out.println("Press 1 to register or 2 to login");
                        }
                        break;
                    default:
                        System.out.println("Press 1 or 2 to continue");
                }
            }
    }
}

