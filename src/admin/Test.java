package admin;

import java.util.Scanner;

public class Test {
    public static void main(String[]args){
        Admin admin = new Admin();
        Scanner input = new Scanner(System.in);
        String firstname = input.next();
        String lastname = input.next();
        String gender = input.next();
        String origin = input.next();
        int age = input.nextInt();
        admin.addAdmins(firstname, lastname, gender, origin, age);
        System.out.println(admin);
    }
}
