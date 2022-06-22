package admin;
import  java.util.Scanner;
public class General {
    public static void main(String[]args){
    }
    public static void Display(){
        System.out.println("Welcome to the online voting System \n " +
                "1. Register 2.Login(if you have an account already)");
        Scanner input = new Scanner(System.in);
       String userInput = input.next();
       switch (userInput){
           case "1":
              // Register();
               break;
           case "2":
              // LogIn();
               break;
           default:
               System.out.println("Press 1 to register or 2 to login");
       }
    }

}
