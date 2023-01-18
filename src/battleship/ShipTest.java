//Emilio Guglielmelli
//Penn ID 18027147
//Collaborator: Cynthia Chavez
//We are NOT submitting the same code
package battleship;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.swing.*;

class ShipTest {

    Ocean ocean;
    Ship ship;

    @BeforeEach
    void setUp() throws Exception {
        ocean = new Ocean();
    }

    @Test
    void testGetLength() {
        ship = new Battleship();
        assertEquals(4, ship.getLength());

        //TODO

        //create new submarine and test length (should be 1)
        Ship submarine = new Submarine();
        assertEquals(1,submarine.getLength());

        //create new cruiser and test length (should be 3)
        Ship cruiser = new Cruiser();
        assertEquals(3,cruiser.getLength());
    }

    @Test
    void testGetBowRow() {
        Ship battleship = new Battleship();
        int row = 0;
        int column = 4;
        boolean horizontal = true;
        battleship.placeShipAt(row, column, horizontal, ocean);
        assertEquals(row, battleship.getBowRow());

        //TODO

        //initialize cruiser, set bowrow,bowcolumn, & horizontal
        Ship cruiser = new Cruiser();
        row = 5;
        column = 5;
        horizontal = false;
        cruiser.placeShipAt(row,column,horizontal,ocean); //place ship
        assertEquals(row, cruiser.getBowRow()); //row and bowrow should be equal


        //initialize destroyer, set bowcolumn bowrow & horizontal
        Ship destroyer = new Destroyer();
        row = 8;
        column = 9;
        horizontal = true;
        destroyer.placeShipAt(row,column,horizontal,ocean); //place ship
        assertEquals(row, destroyer.getBowRow()); //row and bowrow should be equal
    }

    @Test
    void testGetBowColumn() {
        Ship battleship = new Battleship();
        int row = 0;
        int column = 4;
        boolean horizontal = true;
        battleship.placeShipAt(row, column, horizontal, ocean);
        battleship.setBowColumn(column);
        assertEquals(column, battleship.getBowColumn());

        //TODO
        //initialize new submarine, set the row column & horizontal
        Ship submarine = new Submarine();
        row = 3;
        column = 9;
        horizontal = false;
        submarine.placeShipAt(row,column,horizontal,ocean); //place ship
        submarine.setBowColumn(column); //set bowcolumn
        assertEquals(column,submarine.getBowColumn()); //should equal column

        //initialize new cruiser, set row column & horizontal
        Ship cruiser = new Cruiser();
        row = 7;
        column = 4;
        horizontal = true;
        cruiser.placeShipAt(row,column,horizontal,ocean); //place ship
        cruiser.setBowColumn(column); //set bowcolumn
        assertEquals(column,cruiser.getBowColumn()); //should equal column

    }

    @Test
    void testGetHit() {
        ship = new Battleship();
        boolean[] hits = new boolean[4];
        assertArrayEquals(hits, ship.getHit());
        assertFalse(ship.getHit()[0]);
        assertFalse(ship.getHit()[1]);

        //TODO
        //initialize new cruiser and hit array
        Ship cruiser = new Cruiser();
        boolean[] hit = new boolean[3];
        assertArrayEquals(hit,cruiser.getHit());
        int row = 5; //set row, column, and horizontal
        int column = 4;
        boolean horizontal = true;
        cruiser.placeShipAt(row,column,horizontal,ocean); //place ship
        cruiser.shootAt(5,2); //shoot at ship to change boolean array
        assertFalse(cruiser.getHit()[0]);
        assertFalse(cruiser.getHit()[1]);
        assertTrue(cruiser.getHit()[2]); //this should be true because it was hit


    }
    @Test
    void testGetShipType() {
        ship = new Battleship();
        assertEquals("battleship", ship.getShipType());

        //TODO

        // new cruiser that should show as type "cruiser"
        Ship cruiser = new Cruiser();
        assertEquals("cruiser",cruiser.getShipType());

        //new submarine that should show as "submarine"
        Ship submarine = new Submarine();
        assertEquals("submarine",submarine.getShipType());

    }

    @Test
    void testIsHorizontal() {
        Ship battleship = new Battleship();
        int row = 0;
        int column = 4;
        boolean horizontal = true;
        battleship.placeShipAt(row, column, horizontal, ocean);
        assertTrue(battleship.isHorizontal());

        //TODO

        //create new cruiser, place ship and then test if horizontal
        Ship cruiser = new Cruiser();
        row = 9;
        column = 9;
        horizontal = true;
        cruiser.placeShipAt(row,column,horizontal,ocean);
        assertTrue(cruiser.isHorizontal());

        //create new destroyer and initialize horizontal as false
        Ship destroyer = new Destroyer();
        row = 7;
        column = 4;
        horizontal = false;
        destroyer.placeShipAt(row,column,horizontal,ocean);
        assertFalse(destroyer.isHorizontal());

    }

    @Test
    void testSetBowRow() {
        Ship battleship = new Battleship();
        int row = 0;
        int column = 4;
        boolean horizontal = true;
        battleship.setBowRow(row);
        assertEquals(row, battleship.getBowRow());

        //TODO

        //create new destroyer, initialize row and column, and test for bowrow = row
        Ship destroyer = new Destroyer();
        row = 5;
        column = 5;
        horizontal = false;
        destroyer.setBowRow(row);
        assertEquals(row,destroyer.getBowRow());

        //create new cruiser, initialize row and column, and test for bowrow = row
        Ship cruiser = new Cruiser();
        row = 3;
        column = 7;
        horizontal = false;
        cruiser.setBowRow(row);
        assertEquals(row,cruiser.getBowRow());

    }

    @Test
    void testSetBowColumn() {
        Ship battleship = new Battleship();
        int row = 0;
        int column = 4;
        boolean horizontal = true;
        battleship.setBowColumn(column);
        assertEquals(column, battleship.getBowColumn());

        //TODO

        //initialize new destroyer and test bowcolumn = column
        Ship destroyer = new Destroyer();
        row = 5;
        column = 3;
        horizontal = false;
        destroyer.setBowColumn(column);
        assertEquals(column,destroyer.getBowColumn());

        //initialize new cruiser and test bowcolumn = column
        Ship cruiser = new Cruiser();
        row = 8;
        column = 7;
        horizontal = true;
        cruiser.setBowColumn(column);
        assertEquals(column,cruiser.getBowColumn());



    }

    @Test
    void testSetHorizontal() {
        Ship battleship = new Battleship();
        int row = 0;
        int column = 4;
        boolean horizontal = true;
        battleship.setHorizontal(horizontal);
        assertTrue(battleship.isHorizontal());

        //TODO

        //initialize new destroyer and set horizontal to false
        Ship destroyer = new Destroyer();
        row = 9;
        column = 6;
        horizontal = false;
        destroyer.setHorizontal(horizontal);
        assertFalse(destroyer.isHorizontal());

        //initialize new cruiser and set horizontal to true
        Ship cruiser = new Cruiser();
        row = 6;
        column = 5;
        horizontal = true;
        cruiser.setHorizontal(horizontal);
        assertTrue(cruiser.isHorizontal());

    }

    @Test
    void testOkToPlaceShipAt() {

//        test when other ships are not in the ocean
        Ship battleship = new Battleship();
        int row = 0;
        int column = 4;
        boolean horizontal = true;
        boolean ok = battleship.okToPlaceShipAt(row, column, horizontal, ocean);
        assertTrue(ok, "OK to place ship here.");
        battleship.placeShipAt(row,column,horizontal,ocean);

        //TODO

        //initialize new cruiser
        Ship cruiser = new Cruiser();
        int row2 = 1; //set row, column, horizontal
        int column2 = 4;
        horizontal = true;
        //this cruiser should not be allowed to be placed because of the previous battleship at (0,4)
        //it would be directly below the battleship which is an illegal placement
        boolean shipCheck = cruiser.okToPlaceShipAt(row2,column2,horizontal,ocean);
        assertFalse(shipCheck);


        //initialize new battleship
        Ship battleship2 =  new Battleship();
        row = 4;
        column = 5;
        horizontal = false;
        //this battleship should not be allowed to be placed because it would cover a spot (1,5) diagonal to original battleship
        boolean shipCheck2 = battleship.okToPlaceShipAt(row,column,horizontal,ocean);
        assertFalse(shipCheck2);


        //initialize cruiser
       Ship cruiser1 = new Cruiser();
       row = 4;
       column = 3;
       horizontal = true;
       //since this cruiser is not next to any other ships, it should be legal and will be placed
       boolean shipCheck3 = cruiser.okToPlaceShipAt(row,column,horizontal,ocean);
       assertTrue(shipCheck3);
       cruiser.placeShipAt(4,3,horizontal,ocean);

    }

    @Test
    void testOkToPlaceShipAtAgainstOtherShipsOneBattleship() {

        //test when other ships are in the ocean

        //place first ship
        Battleship battleship1 = new Battleship();
        int row = 0;
        int column = 4;
        boolean horizontal = true;
        boolean ok1 = battleship1.okToPlaceShipAt(row, column, horizontal, ocean);
        assertTrue(ok1, "OK to place ship here.");
        battleship1.placeShipAt(row, column, horizontal, ocean);

        //test second ship
        Battleship battleship2 = new Battleship();
        row = 1;
        column = 4;
        horizontal = true;
        boolean ok2 = battleship2.okToPlaceShipAt(row, column, horizontal, ocean);
        assertFalse(ok2, "Not OK to place ship vertically adjacent below.");

        //TODO
        //More tests

        //initialize new cruiser
        Cruiser cruiser = new Cruiser();
         row = 2;
        column = 5;
        horizontal = true;
        //since this cruiser is at least one row down and is not adjacent to the battleship, it is legal to be placed
        boolean shipCheck = cruiser.okToPlaceShipAt(row,column,horizontal,ocean);
        assertTrue(shipCheck);
        //place ship
        cruiser.placeShipAt(row,column,horizontal,ocean);


        //initialize new destroyer
        Destroyer destroyer = new Destroyer();
        row = 1;
        column = 0;
        horizontal = false;
        //this destroyer should not be able to be placed because it will overlap with the battleship at (0,4)
        boolean shipCheck2 = destroyer.okToPlaceShipAt(row,column,horizontal,ocean);
        assertFalse(shipCheck2);
    }

    @Test
    void testPlaceShipAt() {

        Ship battleship = new Battleship();
        int row = 0;
        int column = 4;
        boolean horizontal = true;
        battleship.placeShipAt(row, column, horizontal, ocean);
        assertEquals(row, battleship.getBowRow());
        assertEquals(column, battleship.getBowColumn());
        assertTrue(battleship.isHorizontal());

        assertEquals("empty", ocean.getShipArray()[0][0].getShipType());
        assertEquals(battleship, ocean.getShipArray()[0][1]);

        //TODO

        //initialize new cruiser, set row column and horizontal
        Ship cruiser = new Cruiser();
        row = 7;
        column = 4;
        horizontal = false;
        cruiser.placeShipAt(row,column,horizontal,ocean);
        //check bowrow, bowcolumn, horizontal
        assertEquals(row, cruiser.getBowRow());
        assertEquals(column, cruiser.getBowColumn());
        assertFalse(cruiser.isHorizontal());

        //check each type of ship at the given indices, with (4,4) being "empty" since cruiser is only length 3
        assertEquals(cruiser, ocean.getShipArray()[7][4]);
        assertEquals(cruiser,ocean.getShipArray()[6][4]);
        assertEquals(cruiser,ocean.getShipArray()[5][4]);
        assertEquals("empty",ocean.getShipArray()[4][4].getShipType());


        //initialize new submarine, set row, column, horizontal
        Ship submarine = new Submarine();
        row = 3;
        column = 4;
        horizontal = false;
        //place this ship
        submarine.placeShipAt(row,column,horizontal,ocean);
        //check the current spot that it is a submarine, check the next spot to be empty
        assertEquals(submarine, ocean.getShipArray()[3][4]);
        assertEquals("empty",ocean.getShipArray()[3][5].getShipType());

    }

    @Test
    void testShootAt() {

        Ship battleship = new Battleship();
        int row = 0;
        int column = 9;
        boolean horizontal = true;
        battleship.placeShipAt(row, column, horizontal, ocean);

        assertFalse(battleship.shootAt(1, 9));
        assertTrue(battleship.shootAt(0,8));
        boolean[] hitArray0 = {false, true, false, false};
        assertArrayEquals(hitArray0, battleship.getHit());

        //TODO

        //initialize new cruiser, set row column and horizontal
        Ship cruiser = new Cruiser();
        row = 7;
        column = 7;
        horizontal = false;
        //place ship
        cruiser.placeShipAt(row,column,horizontal,ocean);
        //shoot at two of the cruisers locations
        assertTrue(cruiser.shootAt(7, 7));
        assertTrue(cruiser.shootAt(6,7));
        //hit array should be adjusted by two shots
        boolean[] hitArray1 = {true, true, false};
        assertArrayEquals(hitArray1, cruiser.getHit());


        //initialize new destroyer, set row column horizontal
        Ship destroyer = new Destroyer();
        column = 5;
        row = 5;
        horizontal = true;
        //check if this destroyer can actually be placed here
        assertTrue(destroyer.okToPlaceShipAt(row,column,horizontal,ocean));
        //place ship
        destroyer.placeShipAt(5,5,true,ocean);
        //shoot twice at destroyer, one hit one miss
        assertTrue(destroyer.shootAt(5,5));
        assertFalse(destroyer.shootAt(9,9));
        //hit array should be updated
        boolean[] hitArray2 = {true,false};
        assertArrayEquals(hitArray2, destroyer.getHit());
    }

    @Test
    void testIsSunk() {


        Ship submarine = new Submarine();
        int row = 3;
        int column = 3;
        boolean horizontal = true;
        submarine.placeShipAt(row, column, horizontal, ocean);

        assertFalse(submarine.isSunk());
        assertFalse(submarine.shootAt(5, 3));
        assertFalse(submarine.isSunk());

        //TODO

        //initialize new battleship, set row, column, horizontal
        Ship battleship = new Battleship();
        row = 7;
        column = 6;
        horizontal = true;
        //place ship
        battleship.placeShipAt(row,column,horizontal,ocean);

        //shoot at the battleship 4 times, this should sink it
        assertTrue(battleship.shootAt(7,6));
        assertTrue(battleship.shootAt(7,5));
        assertTrue(battleship.shootAt(7,4));
        assertTrue(battleship.shootAt(7,3));
        //this should show as sunk, and toString method should show "s"
        assertTrue(battleship.isSunk());
        assertEquals("s",battleship.toString());


        //initialize new destroyer, set row,column, horizontal
        Ship destroyer = new Destroyer();
        row = 5;
        column = 3;
        horizontal = true;
        //place ship
        destroyer.placeShipAt(row,column,horizontal,ocean);
        //shoot twice, one hit, one miss
        assertTrue(destroyer.shootAt(5,3));
        assertFalse(destroyer.shootAt(5, 5));
        //ship should not be sunk, and toString shows "x"
        assertFalse(destroyer.isSunk());
        assertEquals("x",destroyer.toString());

    }

    @Test
    void testToString() {

        Ship battleship = new Battleship();
        assertEquals("x", battleship.toString());

        int row = 9;
        int column = 1;
        boolean horizontal = false;
        battleship.placeShipAt(row, column, horizontal, ocean);
        battleship.shootAt(9, 1);
        assertEquals("x", battleship.toString());

        //TODO

        //initialize new submarine, set row,column, horizontal
        Ship submarine = new Submarine();
        //since not sunk, toString should show "x"
        assertEquals("x", submarine.toString());
        row = 4;
        column = 5;
        horizontal = true;
        //place ship
        submarine.placeShipAt(row,column,horizontal,ocean);
        //shoot at the ship
        submarine.shootAt(4,5);
        //should be sunk after one hit
        assertEquals("s", submarine.toString());


        //initialize new destroyer, set row,column,horizontal
        Ship destroyer = new Destroyer();
        //should show as "x" since not sunk
        assertEquals("x", destroyer.toString());
        row = 0;
        column = 2;
        horizontal = true;
        //check if the destroyer can be placed here
        destroyer.setBowRow(row);
        destroyer.setBowColumn(column);
        destroyer.setHorizontal(horizontal);
        assertTrue(destroyer.okToPlaceShipAt(row,column,horizontal,ocean));
        //place ship
        destroyer.placeShipAt(row,column,horizontal,ocean);
        //shoot twice, two hits
        destroyer.shootAt(0,2);
        destroyer.shootAt(0,1);
        //should return "s" sine it is sunk
        assertEquals("s", destroyer.toString());

    }

}