package admin;
import  java.util.Scanner;
public class General {
    public static void main(String[]args) {

        Display();
    }
    public static void Display(){
        Voter nv = new Voter();
        Voter reg = new Voter();
        AdminMethods adminMethods = new AdminMethods();
        while (true){
            System.out.println("Welcome to the online voting System. Are you a user or an admin? \n" +
                    "1. User \n 2.Admin");
            String userInput1=  new Scanner(System.in).next();
            switch (userInput1){
                case "1":
                    System.out.println(" 1. Register  \n 2.Login (if you have an account already)");
                    String userInput2=  new Scanner(System.in).next();
                    switch (userInput2){
                        case "1":
                            nv.Register();
                            break;
                        case "2":
                            Voter.LogIn();
                            break;
                        default:
                            System.out.println("Press 1 to register or 2 to login");
                    }
                    break;
                case "2":
                    System.out.println(" 1. Register  \n 2.Login (if you have an account already)");
                    String userInput3=  new Scanner(System.in).next();
                    switch (userInput3) {
                        case "1":
                           // ad.addAdmins();
                            break;
                        case "2":
                            adminMethods.checkAdminPassword();
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

