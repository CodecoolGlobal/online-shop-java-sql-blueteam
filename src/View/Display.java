package View;

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

    public static void pressXToGoBack() {
        System.out.println("Press \"x\" key to go back to main menu.");
    }

    public static void nameDoesntExist() {
        System.out.println("Provided name doesn't exist.");
    }

    public static void loginName() {
        System.out.print("Provide name: ");
    }

    public static void loginPassword() {
        System.out.print("Provide password: ");
    }

    public static void incorrectPassword() {
        System.out.println("Wrong password provided.");
    }

    public static void whichTable() { System.out.print("Name thy table: "); }

    public static void whichId() { System.out.print("Provide Id: "); }

    public static void userChoices() {
        String[] choices = {"show all products.",
                            "show products in categories descending in amount.",
                            "show the amount of ordered products per client.",
                            "show orders per client.",
                            "show amount of product types per category.",
                            "show total cost per client.",
                            "show order status for all clients.",
                            "DOESN'T WORK.",
                            "add a new product.",
                            "remove a product."};
        for(int i = 0; i < choices.length; i++){
            System.out.println("Press " + (i+1) + " to " + choices[i]);
        }
    }

    public static void addSuccesful() {
        System.out.println("Success.");
    }

    public static void whatToAdd() {
        System.out.println("name, price, amount, is_available, category_id");
    }
}
