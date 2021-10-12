// class for creating a pakudex
public class Pakudex {

    // create ref variable for array of pakuri objects
    private Pakuri[] pakudexInfo;
    private int capacity = -1;

    // default constructor should have a capacity of 20
    public Pakudex() {
        pakudexInfo = new Pakuri[20];
    }
    // create parameterized pakudex constructor that intakes capacity
    public Pakudex(int capacity) {
        pakudexInfo = new Pakuri[capacity];
    }
    // returns the current size
    public int getSize() {
        return capacity + 1;
    }
    // return the capacity of the pakudex
    public int getCapacity() {
        return pakudexInfo.length;
    }

    // go through the pakudex to return an array of pakuri objects
    public String[] getSpeciesArray() {
        int counter;
        for (counter = 0; counter < pakudexInfo.length; counter++) {
            if(pakudexInfo[counter] == null) {
                break;
            }
        }
        // create a new array that will point to the same memory loc as the pakudex info
        String[] newPakudex = new String[counter];
        for (int i = 0; i < newPakudex.length; i++) {
            newPakudex [i] = pakudexInfo[i].getSpecies();
        }
        if (capacity == -1) {
            return null;
        }
        return newPakudex;
    }

    // return the stats of the pakuri by calling
    public int[] getStats(String species) {

        // set statistics array to have a size of 3
            // [1] = attack, [2] = defense, [3] = speed
        int[] statistics = new int[3];
        boolean check = false;

        // set condition tto continue whenever the name of pakuri is equal to the name of the pakuri in the array of objects
        for(int i = 0; i < pakudexInfo.length; i++) {
            if ((pakudexInfo[i] != null) && species.equals(pakudexInfo[i].getSpecies())) {
                check = true;
            }
        }

        // continue when the first for loop is approved
        if (check) {

            // retrieve updated statistics from the Pakuri and update statistics array point to those values
            for(int i = 0; i < pakudexInfo.length; i++) {
                if ((pakudexInfo[i] != null) && (pakudexInfo[i].getSpecies().equals(species))) {
                    statistics[0] = pakudexInfo[i].getAttack();
                    statistics[1] = pakudexInfo[i].getDefense();
                    statistics[2] = pakudexInfo[i].getSpeed();
                }
            }
            return statistics;
        }
        else {
            return null;
        }
    }

    // method for sorting the pakuri objects in aplhabetical order
    public void sortPakuri() {

        // traverse through the array of pakuri
        for (int i = 0; i < pakudexInfo.length; i++)
        {
            // while traversing through the original array, we need to traverse again and compare the following index with the previous
            int first = i;
            for (int j = ( i+ 1); j < pakudexInfo.length; j++) {
                if ((pakudexInfo[j] != null) && (pakudexInfo[j].getSpecies().compareTo(pakudexInfo[first].getSpecies()) < 0)) {

                    // update the position of j in the array
                    first = j;
                }
            }

            // final sorting of objects
            Pakuri random = pakudexInfo[i];
            pakudexInfo[i] = pakudexInfo[first];
            pakudexInfo[first] = random;
        }
    }

    // create a method for adding the pakuri objects to the array
    public boolean addPakuri(String species) {
        boolean checkCapacity = true;
        capacity++;

        // check to make sure the pakudex isnt at full capacity
        if (capacity > pakudexInfo.length - 1 ) {
            checkCapacity = false;
            capacity--;
            System.out.println("Error: Pakudex is full!");
        }

        // continue whenever the pakudex isnt full
        if (checkCapacity) {
            for (int i = 0; i < pakudexInfo.length; i++) {

                // whenever the the array takes in a new pakuri,
                // make sure index has a value
                // make sure it isnt already inputted
                if ((pakudexInfo[i] != null) && species.equals(pakudexInfo[i].getSpecies())) {
                    checkCapacity = false;
                    capacity--;
                    System.out.println("Error: Pakudex already contains this species!");
                }
            }
        }

        // continue if capacity isnt reached and no repeats

        if (checkCapacity) {

            // finally create a new pakuri object with the name given in main class
            // add object to array of objects in the pakudex
            Pakuri pakuri = new Pakuri(species);
            pakudexInfo[capacity] = pakuri;
            System.out.println("Pakuri species " + species + " successfully added!");
        }
        return checkCapacity;
    }
    // method for evolving the pakuri
    public boolean evolveSpecies(String species) {
        // set check to false, should only return true once we evolve
        boolean check = false;

        // go through array of objects and choose pakuri by name
        // evolve pakuri whenever conditions are string matches object
        for (int i = 0; i < pakudexInfo.length; i++) {
            if ((pakudexInfo[i] != null) && species.equals(pakudexInfo[i].getSpecies())) {
                pakudexInfo[i].evolve();
                check = true;
            }
        }
        return check;
    }
}