package es.drodriguez.com.Utils;

import java.util.Scanner;

public class Console {
    public static String getString(String message){
        System.out.println(message);
        return new Scanner(System.in).nextLine().trim();
    }
}
