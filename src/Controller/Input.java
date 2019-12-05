package Controller;

import View.Display;

import java.util.Scanner;

public class Input {

    public String string() {
        Scanner scanner = new Scanner(System.in);
        String result = "";
        try {
            result = scanner.nextLine();
        } catch (Exception e) {
            scanner.close();
            Display.inputMismatch();
            string();
        }
        return result;
    }

    public int integer() {
        Scanner scanner = new Scanner(System.in);
        int result = 0;
        try {
            result = scanner.nextInt();
        } catch (Exception e){
            scanner.close();
            Display.inputMismatch();
            integer();
        }
        return result;
    }
}