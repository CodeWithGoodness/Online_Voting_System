package admin;

import java.text.ParseException;
import java.util.Scanner;

public class Test {
    public static void main(String[]args) throws ParseException {
        Admin admin = new Admin();
        AdminMethods adminMethods = new AdminMethods();
        Election election = new Election();
        System.out.println(election.getCurrentDate());
        ElectionMethods.votingDay();
//        Scanner input = new Scanner(System.in);
//        String firstname = input.next();
//        String lastname = input.next();
//        String gender = input.next();
//        String origin = input.next();
//        int age = input.nextInt();
//        adminMethods.addAdmins(firstname, lastname, gender, origin, age);
    }
}
