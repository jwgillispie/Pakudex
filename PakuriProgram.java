import java.util.Scanner;
public class PakuriProgram {
    public static void main(String args[]) {
        Scanner myScanner = new Scanner(System.in);
        int maxCapacity = 0;
        boolean continues = false;
        String pakuriName;

        // print out a welcome message and ask for max capacity
        System.out.println("Welcome to Pakudex: Tracker Extraordinaire!");
        System.out.print("Enter max capacity of the Pakudex: ");

        do {
            // use scanner.hasNextInt to make sure user is inputting an integer and not another data type
            if (myScanner.hasNextInt()) {

                // make sure integer is positive
                maxCapacity = myScanner.nextInt();
                if (maxCapacity > 0)
                    continues = true;
                else {
                    continues = false;
                    System.out.println("Please enter a valid size.");
                    System.out.print("Enter max capacity of the Pakudex: ");

                    // restart loop of the user inputs a negative num
                    continue;
                }
            }
            else {
            continues = false;
            System.out.println("Please enter a valid size.");
            System.out.print("Enter max capacity of the Pakudex: ");

            // retry for user input to be int type
            myScanner.next();
            }
        }
        while (continues == false);
        // relay to user the max capacity once a correct input is inputted
        System.out.println("The Pakudex can hold " + maxCapacity + " species of Pakuri.");

        // we need to create an specific pakudex from the pakudex class for the user to customize
        // new pakudex objet should intake the capacity entered by the user to create the array of pakuri objects
        Pakudex pakudex = new Pakudex(maxCapacity);

        // set menu to something that wil enter the main while loop for the menu options
        String menu = "my favorite pokemon in Gengar";

        // the user must choose 1 - 6 from the menu
        while (!menu.equals("6")) {
            printMenu();
            System.out.print("What would you like to do? ");

            // change menu option to input from user
            menu = myScanner.next();

            // if user input is not 1 - 6, we should repeat the menu and print error message
            while (!(menu.equals("1") || menu.equals("2") || menu.equals("3") || menu.equals("4") || menu.equals("5") || menu.equals("6"))) {
                System.out.println("Unrecognized menu selection!");
                printMenu();

                System.out.print("What would you like to do? ");
                menu = myScanner.next();

            }
                // option 1 will list the available pakuri
            if (menu.equals("1")) {

                // make new array point to same memory loc as parkuri object array
                String[] array = pakudex.getSpeciesArray();

                if (array == null) {
                    System.out.println("No Pakuri in Pakudex yet!");
                }

                // traverse the array with for loop for print string name of each object by the order they were entered
                if (!(array == null)) {
                    System.out.println("Pakuri In Pakudex: ");
                    for (int i = 0; i < array.length; i++) {
                        System.out.println((i + 1) + ". " + array[i]);
                    }
                }
            }

            // option 2 should display the stats of a specific pakruri object
            if (menu.equals("2")){

                // ask user to input a name and store in string variable
                System.out.println("Enter the name of the species to display: ");
                pakuriName = myScanner.next();

                // create an array based on the getStats method that takes in name entered
                int[] pakuriStats = pakudex.getStats(pakuriName);

                // asses for empty array
                if (pakuriStats == null) {
                    System.out.println("Error: No such Pakuri!");
                }
                else {
                    System.out.println("Species: " + pakuriName);
                    System.out.println("Attack: " + pakuriStats[0]);
                    System.out.println("Defense: " + pakuriStats[1]);
                    System.out.println("Speed: " + pakuriStats[2]);
                }
            }
            // option 3 should add a new pakuri name from user to the array of pakuri objects
            if (menu.equals("3")){

                // make sure we are not over capacity limit
                if (pakudex.getCapacity() == pakudex.getSize()) {
                    System.out.println("Error: Pakudex is full!");
                }
                else {
                    System.out.println("Enter the name of the species to add: ");
                    pakuriName = myScanner.next();
                    pakudex.addPakuri(pakuriName);
                }
            }

            // option 4 should evolve the specific pakuri using evolve species method
            if (menu.equals("4")){

                // choose specific pakuri to evolve
                System.out.println("Enter the name of the species to evolve: ");
                pakuriName = myScanner.next();
                if (pakudex.evolveSpecies(pakuriName)) {
                    System.out.println(pakuriName + " has evolved!");
                }
                else {
                    System.out.println("Error: No such Pakuri!");
                }
            }
            // option 5 will sort pakuri
            if (menu.equals("5")){
                pakudex.sortPakuri();
                System.out.println("Pakuri have been sorted!");
            }
            // exit menu
            if (menu.equals("6")){
                System.out.println("Thanks for using Pakudex! Bye!");
                break;
            }
        }
    }
    public static void printMenu () {
        System.out.println("Pakudex Main Menu");
        System.out.println("-----------------");
        System.out.println("1. List Pakuri");
        System.out.println("2. Show Pakuri");
        System.out.println("3. Add Pakuri");
        System.out.println("4. Evolve Pakuri");
        System.out.println("5. Sort Pakuri");
        System.out.println("6. Exit");
    }
}