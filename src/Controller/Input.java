package Controller;

import View.Display;

import java.util.Scanner;

class Input {

    public String str() {
        Scanner scanner = new Scanner(System.in);
        String result = "";
        try {
            result = scanner.nextLine().toLowerCase();
        } catch (Exception e) {
            scanner.close();
            Display.inputMismatch();
            str();
        }
        return result;
    }

    public int in() {
        Scanner scanner = new Scanner(System.in);
        int result = 0;
        try {
            result = scanner.nextInt();
        } catch (Exception e){
            scanner.close();
            Display.inputMismatch();
            in();
        }
        return result;
    }

    public String userType() {
        Display.chooseUserType();
        return str();
    }
}