
package battleship;

import java.util.Scanner;


/**
 * This class represents the actual battleship game where the user plays
 */
public class BattleshipGame {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in); //new scanner
        Ocean ocean = new Ocean(); //new instance of ocean

        System.out.println("Welcome to Battleship! You will play against the computer. The computer has set up 10 ships in the array and you must specify where you want to shoot \nby entering two integers separated by a comma. Once you sink all ships, you will be given your score. Good luck!");

        System.out.println("");
        ocean.placeAllShipsRandomly(); //place ships randomly
    //    ocean.printWithShips(); //method for debugging only
        while(!ocean.isGameOver()) {
            ocean.print(); //print array with ships in it
            System.out.println("Please enter the row and column you would like to shoot at:");
            String input = scanner.next();
            String[] cleanedInput = input.strip().split(","); //clean inputs by stripping and split based on comma
            int row; //initialize row
            int column; //initialize column
            try {
                row = Integer.parseInt(cleanedInput[0]); //try and cast to int
                column = Integer.parseInt(cleanedInput[1]);
            }catch (Exception e) { //if not int throw exception
                System.out.println("Please enter two integers separated by a comma.");
                continue;
            }
            if(row > 9 || row < 0 || column > 9 || column < 0) { //if number is out of bounds throw exception
                try {
                    throw new Exception();
                } catch (Exception e) {
                   System.out.println("Your input must be between 0 and 9.");
                    continue;
                }
            }
                ocean.shootAt(row,column); //shoot at with the given row and column entered by user
                System.out.println("");
            }
                scanner.close();
    }

}

