//Emilio Guglielmelli
//Penn ID 18027147
//Collaborator: Cynthia Chavez
//We are NOT submitting the same code
package battleship;

import static org.junit.jupiter.api.Assertions.*;

import java.awt.image.CropImageFilter;
import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class OceanTest {

    Ocean ocean;

    static int NUM_BATTLESHIPS = 1;
    static int NUM_CRUISERS = 2;
    static int NUM_DESTROYERS = 3;
    static int NUM_SUBMARINES = 4;
    static int OCEAN_SIZE = 10;

    @BeforeEach
    void setUp() throws Exception {
        ocean = new Ocean();
    }

    @Test
    void testEmptyOcean() {

        //tests that all locations in the ocean are "empty"

        Ship[][] ships = ocean.getShipArray();

        for (int i = 0; i < ships.length; i++) {
            for (int j = 0; j < ships[i].length; j++) {
                Ship ship = ships[i][j];

                assertEquals("empty", ship.getShipType());
            }
        }

        assertEquals(0, ships[0][0].getBowRow());
        assertEquals(0, ships[0][0].getBowColumn());

        assertEquals(5, ships[5][5].getBowRow());
        assertEquals(5, ships[5][5].getBowColumn());

        assertEquals(9, ships[9][0].getBowRow());
        assertEquals(0, ships[9][0].getBowColumn());
    }

    @Test
    void testPlaceAllShipsRandomly() {

        //tests that the correct number of each ship type is placed in the ocean

        ocean.placeAllShipsRandomly();

        Ship[][] ships = ocean.getShipArray();
        ArrayList<Ship> shipsFound = new ArrayList<Ship>();

        int numBattlehips = 0;
        int numCruisers = 0;
        int numDestroyers = 0;
        int numSubmarines = 0;
        int numEmptySeas = 0;

        for (int i = 0; i < ships.length; i++) {
            for (int j = 0; j < ships[i].length; j++) {
                Ship ship = ships[i][j];
                if (!shipsFound.contains(ship)) {
                    shipsFound.add(ship);
                }
            }
        }

        for (Ship ship : shipsFound) {
            if ("battleship".equals(ship.getShipType())) {
                numBattlehips++;
            } else if ("cruiser".equals(ship.getShipType())) {
                numCruisers++;
            } else if ("destroyer".equals(ship.getShipType())) {
                numDestroyers++;
            } else if ("submarine".equals(ship.getShipType())) {
                numSubmarines++;
            } else if ("empty".equals(ship.getShipType())) {
                numEmptySeas++;
            }
        }

        assertEquals(NUM_BATTLESHIPS, numBattlehips);
        assertEquals(NUM_CRUISERS, numCruisers);
        assertEquals(NUM_DESTROYERS, numDestroyers);
        assertEquals(NUM_SUBMARINES, numSubmarines);

        //calculate total number of available spaces and occupied spaces
        int totalSpaces = OCEAN_SIZE * OCEAN_SIZE;
        int occupiedSpaces = (NUM_BATTLESHIPS * 4)
                + (NUM_CRUISERS * 3)
                + (NUM_DESTROYERS * 2)
                + (NUM_SUBMARINES * 1);

        System.out.println(totalSpaces-occupiedSpaces);

      //  ocean.printWithShips();
        //test number of empty seas, each with length of 1
        assertEquals(totalSpaces - occupiedSpaces, numEmptySeas);
        System.out.println(totalSpaces-occupiedSpaces);
    }

    @Test
    void testIsOccupied() {

        Destroyer destroyer = new Destroyer();
        int row = 1;
        int column = 5;
        boolean horizontal = false;
        destroyer.placeShipAt(row, column, false, ocean);
        assertTrue(ocean.isOccupied(1, 5));


        //TODO

        //initialize new cruiser and set row column horizontal
        Cruiser cruiser = new Cruiser();
        row = 7;
        column = 7;
        horizontal = true;
        //place ship
        cruiser.placeShipAt(row,column,horizontal,ocean);
        //check all locations of cruiser, with one being false because cruiser is only length 3
        assertTrue(ocean.isOccupied(7, 7));
        assertTrue(ocean.isOccupied(7,6));
        assertTrue(ocean.isOccupied(7, 5));
        assertFalse(ocean.isOccupied(7, 3));


        //initialize new battleship and set row column horizontal
        Battleship battleship = new Battleship();
        row = 9;
        column = 8;
        horizontal = true;
        //check if ship can be placed here
        battleship.okToPlaceShipAt(row,column,horizontal,ocean);
        //place ship
        battleship.placeShipAt(row,column,horizontal,ocean);
        //check all locations of battleship, should be 4
        assertTrue(ocean.isOccupied(9,8));
        assertTrue(ocean.isOccupied(9,7));
        assertTrue(ocean.isOccupied(9,6));
        assertTrue(ocean.isOccupied(9,5));

    }

    @Test
    void testShootAt() {

        assertFalse(ocean.shootAt(0, 1));

        Destroyer destroyer = new Destroyer();
        int row = 1;
        int column = 5;
        boolean horizontal = false;
        destroyer.placeShipAt(row, column, horizontal, ocean);

        assertTrue(ocean.shootAt(1, 5));
        assertFalse(destroyer.isSunk());
        assertTrue(ocean.shootAt(0, 5));

        //TODO

        //make sure 0,0 is not occupied
        assertFalse(ocean.isOccupied(0,0));

        //initialize new submarine to be placed at (0,0)
        Submarine submarine = new Submarine();
        row = 0;
        column = 0;
        horizontal = false;
        //place ship
        submarine.placeShipAt(row,column,horizontal,ocean);

        //this should should hit the submarine thus true
        assertTrue(ocean.shootAt(row,column));
        //since submarine is only length 1, submarine should be sunk
        assertTrue(submarine.isSunk());



        //this shot should be false since there is not a ship present
        assertFalse(ocean.shootAt(3,7));

        //initialize new cruiser, row,column, horizontal
        Cruiser cruiser = new Cruiser();
        row = 3;
        column = 7;
        horizontal = true;
        //check if cruiser can be placed there
        assertTrue(cruiser.okToPlaceShipAt(row,column,horizontal,ocean));
        //place the cruiser
        cruiser.placeShipAt(row,column,horizontal,ocean);

        //shoot two shots, two should hit
        assertTrue(ocean.shootAt(3,7));
        assertTrue(ocean.shootAt(3,6));
        assertFalse(ocean.shootAt(1,7));

        //cruiser should not be sunk since only two shots hit not three
        assertFalse(cruiser.isSunk());
    }

    @Test
    void testGetShotsFired() {

        //should be all false - no ships added yet
        assertFalse(ocean.shootAt(0, 1));
        assertFalse(ocean.shootAt(1, 0));
        assertFalse(ocean.shootAt(3, 3));
        assertFalse(ocean.shootAt(9, 9));
        assertEquals(4, ocean.getShotsFired());

        Destroyer destroyer = new Destroyer();
        int row = 1;
        int column = 5;
        boolean horizontal = false;
        destroyer.placeShipAt(row, column, horizontal, ocean);

        Ship submarine = new Submarine();
        int row1 = 0;
        int column1 = 0;
        boolean horizontal1 = false;
        submarine.placeShipAt(row1, column1, horizontal1, ocean);

        assertTrue(ocean.shootAt(1, 5));
        assertFalse(destroyer.isSunk());
        assertTrue(ocean.shootAt(0, 5));
        assertTrue(destroyer.isSunk());
        assertEquals(6, ocean.getShotsFired());

        //TODO

        //initialize new battleship and row,column,horizontal
        Battleship battleship = new Battleship();
        row = 9;
        column = 7;
        horizontal = true;

        //check that it is allowed for ship to be placed
        assertTrue(battleship.okToPlaceShipAt(row,column,horizontal,ocean));
        //place ship
        battleship.placeShipAt(row,column,horizontal,ocean);

        //shoot 5 shots, 4 hits and 1 miss
        assertFalse(ocean.shootAt(4,5));
        assertFalse(battleship.isSunk());
        assertTrue(ocean.shootAt(9,7));
        assertTrue(ocean.shootAt(9,6));
        assertTrue(ocean.shootAt(9,5));
        assertTrue(ocean.shootAt(9,4));
        //since we have 4 hits above, the battleship should be sunk
        assertTrue(battleship.isSunk());
        //since 6 shots were fired first and then another 5, we should have 11 total shots
        assertEquals(11,ocean.getShotsFired());




        //initialize new cruiser and set row,column,horizontal
        Cruiser cruiser = new Cruiser();
        row = 6;
        column = 5;
        horizontal = false;
        //check if it is ok to place the ship
        assertTrue(cruiser.okToPlaceShipAt(row,column,horizontal,ocean));
        //place the ship
        cruiser.placeShipAt(row,column,horizontal,ocean);
        //fire two shots, both hits
        assertTrue(ocean.shootAt(6,5));
        assertTrue(ocean.shootAt(5,5));
        //our hitcount should be 8
        assertEquals(8,ocean.getHitCount());
        //total shots fired should be incremented by two from the last test
        assertEquals(13,ocean.getShotsFired());

    }

    @Test
    void testGetHitCount() {

        Destroyer destroyer = new Destroyer();
        int row = 1;
        int column = 5;
        boolean horizontal = false;
        destroyer.placeShipAt(row, column, horizontal, ocean);

        assertTrue(ocean.shootAt(1, 5));
        assertFalse(destroyer.isSunk());
        assertEquals(1, ocean.getHitCount());

        //TODO
        //More tests

        //initialize cruiser and row,column,horizontal
        Cruiser cruiser = new Cruiser();
        row = 4;
        column = 3;
        horizontal = true;
        //place ship
        cruiser.placeShipAt(row,column,horizontal,ocean);
        //firing three shots, two hits
        assertFalse(ocean.shootAt(4,5));
        assertTrue(ocean.shootAt(4,3));
        assertTrue(ocean.shootAt(4,2));
        // cruiser should not be sunk and hit count should be 3 (1 from earlier)
        assertFalse(cruiser.isSunk());
        // hit count should be 3 (1 from earlier)
        assertEquals(3,ocean.getHitCount());



        //initialize new battleship
        Battleship battleship = new Battleship();
        row = 8;
        column = 6;
        horizontal = false;
        //check if ok to place battleship
        assertTrue(battleship.okToPlaceShipAt(row,column,horizontal,ocean));
        //place the battleship
        battleship.placeShipAt(row,column,horizontal,ocean);
        //shoot and hit 4 times, battleship should be sunk
        assertTrue(ocean.shootAt(8,6));
        assertTrue(ocean.shootAt(7,6));
        assertTrue(ocean.shootAt(6,6));
        assertTrue(ocean.shootAt(5,6));
        assertTrue(battleship.isSunk());
        //hit count should be 7 after the 4 new hits
        assertEquals(7,ocean.getHitCount());

    }

    @Test
    void testGetShipsSunk() {

        Destroyer destroyer = new Destroyer();
        int row = 1;
        int column = 5;
        boolean horizontal = false;
        destroyer.placeShipAt(row, column, horizontal, ocean);

        assertTrue(ocean.shootAt(1, 5));
        assertFalse(destroyer.isSunk());
        assertEquals(1, ocean.getHitCount());
        assertEquals(0, ocean.getShipsSunk());

        //TODO

        //initialize new cruiser
        Cruiser cruiser = new Cruiser();
        row = 7;
        column = 5;
        horizontal = false;
        //place ship
        cruiser.placeShipAt(row,column,horizontal,ocean);
        //shoot at 3 times, sinking ship
        assertTrue(ocean.shootAt(7,5));
        assertTrue(ocean.shootAt(6,5));
        assertTrue(ocean.shootAt(5,5));
        assertTrue(cruiser.isSunk());
        //hit count should be 4 (3 now 1 earlier), and one ship sunk
        assertEquals(4,ocean.getHitCount());
        assertEquals(1,ocean.getShipsSunk());


        //initialize new battleship
        Battleship battleship = new Battleship();
        row = 9;
        column = 6;
        horizontal = true;
        //place ship
        battleship.placeShipAt(row,column,horizontal,ocean);
        //shoot 3 times, 2 hits one miss
        assertTrue(ocean.shootAt(9,6));
        assertTrue(ocean.shootAt(9,5));
        assertFalse(ocean.shootAt(9,1));
        //7 shots total from all test cases, there are 6 hits
        assertEquals(6,ocean.getHitCount());
        assertEquals(7,ocean.getShotsFired());

    }

    @Test
    void testGetShipArray() {

        Ship[][] shipArray = ocean.getShipArray();
        assertEquals(OCEAN_SIZE, shipArray.length);
        assertEquals(OCEAN_SIZE, shipArray[0].length);

        assertEquals("empty", shipArray[0][0].getShipType());

        //TODO

        //create new array of ships
        Ship[][] arrayofShips = ocean.getShipArray();
        //new battleship that we are going to place
        Battleship battleship = new Battleship();
        int row = 7;
        int column = 6;
        boolean horizontal = true;
        //check if ok to place battleship
        assertTrue(battleship.okToPlaceShipAt(row,column,horizontal,ocean));
        //place the battleship
        battleship.placeShipAt(row,column,horizontal,ocean);
        //check the battleships positions in the array to make sure they show "battleship" type
        assertEquals("battleship",arrayofShips[7][6].getShipType());
        assertEquals("battleship",arrayofShips[7][5].getShipType());
        assertEquals("battleship",arrayofShips[7][4].getShipType());
        assertEquals("battleship",arrayofShips[7][3].getShipType());


        //create new cruiser that is going to be placed
        Cruiser cruiser = new Cruiser();
        row = 4;
        column = 3;
        horizontal = false;
        //check if ok to place cruiser
        assertTrue(cruiser.okToPlaceShipAt(row,column,horizontal,ocean));
        //place the cruiser
        cruiser.placeShipAt(row,column,horizontal,ocean);
        //shoot at cruiser
        ocean.shootAt(4,3);
        ocean.shootAt(3,3);
        //check the ship type and to string methods
        assertEquals("cruiser",arrayofShips[4][3].getShipType());
        assertEquals("cruiser",arrayofShips[3][3].getShipType());
        assertEquals("x",arrayofShips[4][3].toString());
        assertEquals("x",arrayofShips[3][3].toString());

    }

}