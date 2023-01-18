//Emilio Guglielmelli
//Penn ID 18027147
//Collaborator: Cynthia Chavez
//We are NOT submitting the same code
package battleship;

import java.util.Random;

/**
 * This class represents the ocean that the array of ships will be placed in
 * it also has methods to print the array, allow shots, and print final results
 */
public class Ocean {

    //Instance vars

    /**
     * Represents an array of type ship that will be the ocean
     */
    private Ship[][] ships = new Ship[10][10];

    /**
     * Represents the number of shots the user has taken in total
     */
    private int shotsFired;

    /**
     * represents the number of hits on a real ship in the ocean (not "emptySea")
     */
    private int hitCount;

    /**
     * The number of ships that have been sunk in the sea
     */
    private int shipsSunk;

    /**
     * An array of boolean values that are to be changed when a shot is taken
     * This is so we can update the array using the toString method ("x" or "s" or "-")
     */
    private boolean[][] booleans = new boolean[10][10];

    //Constructor

    /**
     * This constructor for the Ocean class creates an "ocean" of ships
     * and places "emptySea" ships within it before any other ship type is placed
     */
    public Ocean() {
        //initialize our variables
        shotsFired = 0;
        hitCount = 0;
        shipsSunk = 0;
        //iterate over ships,
        for (int i = 0; i < ships.length; i++) {
            for (int j = 0; j < ships.length; j++) {
                //for each ships index, create a new emptysea
                EmptySea emptySea = new EmptySea();
                //place this emptysea in the ocean
                emptySea.placeShipAt(i, j, false, this);
            }
        }
    }
    //Methods

    /**
     * this method checks places the ships randomly in the ocean by using the random module
     * and checking if the given ship can be placed through the "oktoplaceship" method
     */
    void placeAllShipsRandomly() {
        //import java random module
        Random random = new Random();
        //create a new array of type ship and create a new ship object for each ship that we are able to iterate over
        //per the instructions, we need 1 battleship, 2 cruisers, 3 destroyers, 4 submarines
        Ship[] shipstoBePlaced = {new Battleship(), new Cruiser(), new Cruiser(), new Destroyer(), new Destroyer(), new Destroyer()
                , new Submarine(), new Submarine(), new Submarine(), new Submarine()};
        //iterate over our ships
        for (Ship ships : shipstoBePlaced) {
            while (true) {
                //row assigned to the ship
                int row = random.nextInt(10);
                //column assigned to the ship
                int column = random.nextInt(10);
                //boolean assigned to the ship
                boolean horizontal = random.nextBoolean();
                //check if ship is ok to place using method with given row,column,horizontal
                if (ships.okToPlaceShipAt(row, column, horizontal, this) == true) {
                    //if this method is true, then we place the ship
                    ships.placeShipAt(row, column, horizontal, this);
                    break;
                }
            }
        }
    }

    /**
     * this method checks if a given spot in the ocean is occupied by looking for the ship type
     * "empty" since the emptysea always returns false for is occupied
     * @param row in ocean
     * @param column in ocean
     * @return "true" or "false"
     */
    boolean isOccupied(int row, int column) {

        //this checks to make sure the isOccupied is not called outside the array
        if (row < 0 || row > 9 || column < 0 || column > 9) {
            return false;
        }
        //if the type of ship is "empty", then we know it is enptySea and thus not occupied
        if (ships[row][column].getShipType().equals("empty")) {
            return false;
        }
        //if neither of the above are hit, then we know it is occupied
        return true;
    }

    /**
     * This method represents shooting at a ship in the ocean by also bringing in the ship shootAt method
     * it will print when a ship is sunk and also keep count of hitcount, shotsfired, and shipssunk
     * @param row to be shot at
     * @param column column to be shot at
     * @return boolean
     */
    boolean shootAt(int row, int column) {
        //increment our shots fired whether they hit or miss
        shotsFired++;
        //need to check if the given spot is occupied with a real ship per the instructions
        //also check if the ship shootAt method is true so the shot can be taken
        if (isOccupied(row, column) == true && ships[row][column].shootAt(row,column) == true) {
            // if these are both true, then we can change our booleans index to true indicating it hit a real ship
            booleans[row][column] = true;
            //if the ship is sunk we need to increment ships sunk and print our message of type sunk
            if (ships[row][column].isSunk() == true) {
                shipsSunk++;
                System.out.println("You sunk a " + ships[row][column].getShipType() + ".");
            }
            //if the ship is not sunk yet, we must increment hit count still and give minor feedback "hit"
            hitCount++;
            System.out.println("hit");
            return true;
        }
            //if the ship is not occupied, this will help print out the "-" that we need for the user to know they missed
            //this means we are basically shooting at an "emptysea" object, should return false
            booleans[row][column] = true;
            ships[row][column].shootAt(row, column);
            System.out.println("miss");
            return false;
    }

    /**
     * this method is a getter for the shotsFired variable
     * @return number of shots fired
     */
    int getShotsFired() {
        return this.shotsFired;
    }

    /**
     * this method is a getter for the hitcount
     * @return number of hits
     */
    int getHitCount(){
            return this.hitCount;
    }

    /**
     * this method is a getter for the ships sunk
     * @return number of ships sunk
     */
    int getShipsSunk() {
        return this.shipsSunk;
    }

    /**
     * this method checks whether the game is over by seeing if the user sunk 10 ships
     * @return boolean
     */
    boolean isGameOver() {
        if(shipsSunk == 10) {
            //print array one more time
            print();
            //print number of shots
            System.out.println(hitCount);
            System.out.println("It took you " + shotsFired + " shots to sink all the ships.");
            System.out.println("Thanks for playing!");
            return true;
        }
        return false;
    }

    /**
     * this method returns the ship object created in this ocean class
     * @return ships array
     */
    Ship[][] getShipArray() {
        return ships;
    }

    /**
     * this method prints the array and pulls the toString method to let the user know when they hit,miss, or sunk ship
     */
    void print() {
        //print our top numbers
        System.out.println("  0 1 2 3 4 5 6 7 8 9");
        //iterate twice to user for our ships object that requires two indices
        for (int i = 0; i < 10; i++) {
            System.out.print(i);
            for (int j = 0; j < 10; j++) {
                //if ships sunk or the boolean array has been set to true, we pull toString to change to
                //either "x","s", or "-"
                if (ships[i][j].isSunk() || booleans[i][j]) {
                    System.out.print(" " + getShipArray()[i][j].toString());
                }
                //if neither of those, we print our period
                else{
                    System.out.print(" .");
                }
            }
            //extra space for the array formatting
            System.out.println(" ");
        }
    }

    //debugging purposes only
    /**
     * This method is only used to show the array with the ships placed with their respective letters
     */
    void printWithShips() {
        //print our top numbers
        System.out.println("  0 1 2 3 4 5 6 7 8 9");
        //iterate twice for our two indices
        for (int i = 0; i < 10; i++) {
            System.out.print(i);
            for (int j = 0; j < ships.length; j++) {
                //if ship type is empty we print blank space
                if (ships[i][j].getShipType().equals("empty")) {
                    System.out.print("  ");
                    //if not empty print the ship with the helper method in ship class
                    //that returns single letter of ship type
                } else {
                    System.out.print(" " + ships[i][j].shipLetterforPrinting());
                }
            }
            //final space for formatting
            System.out.println(" ");
        }
    }
}
