package View;

import java.util.ArrayList;

public class Display {

    public static void chooseUserType(){
        System.out.println("Choose user type");
    }

    public static void wrongInput() {
        System.out.println("Wrong input, please try again.");
    }

    public static void inputMismatch(){
        System.out.println("Wrong input");
    }

    public static void userChoices() {
        String[] choices = {"show all products.", "show products in categories descending."};
        for(int i = 0; i < choices.length; i++){
            System.out.println("Press " + (i+1) + " to " + choices[i]);
        }
    }
}
