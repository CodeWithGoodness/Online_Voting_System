package admin;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.Scanner;

public class Test {
    public static void main(String[]args) throws SQLException, ParseException {
   // AdminMethods.database();
        Election election = new Election();
        System.out.println(election.getDifference());
    }
}
