//Emilio Guglielmelli
//Penn ID 18027147
//Collaborator: Cynthia Chavez
//We are NOT submitting the same code
package battleship;

/**
 * This class serves as the parent class for all ships, it has methods to shoot, indicate if a ship is sunk,
 * whether it is ok to place ship, and actually placing the ship.
 */
abstract class Ship {

    //instance vars

    /**
     * represents row of the front of the ship
     */
    private int bowRow;

    /**
     * represents column of the front of the ship
     */
    private int bowColumn;

    /**
     * length of the given ship
     */
    private int length;

    /**
     * whether horizontal or not
     */
    private boolean horizontal;

    /**
     * array of booleans to indicate if a ship is hit or not
     */
    private boolean[] hit;

    //constructor

    /**
     * This constructor creates a new hit array for each ship and also sets the length of the ship
     * implemented in each subclass
     * @param length of ship
     */
    public Ship(int length) {
        hit = new boolean[length]; //initialize array of booleans with length
        this.length = length; //initialize length of ship
    }

    //getters and setters

    /**
     * returns the length of a given ship
     * @return length of ship
     */
    public int getLength() {
        return this.length;
    }

    /**
     * gets the row of the bow of the ship
     * @return row of bow
     */
    public int getBowRow() {
        return this.bowRow;
    }

    /**
     * gets the column of the bow of the ship
     * @return column of bow
     */
    public int getBowColumn() {
        return this.bowColumn;
    }

    /**
     * returns the boolean hit array of the ship
     * @return hit array of ship
     */
    public boolean[] getHit() {
        return this.hit;
    }

    /**
     * returns the given horizontal value for ship
     * @return "true" or "false"
     */
    public boolean isHorizontal() {
        return horizontal;
    }

    /**
     * sets the bow row for a given ship
     * @param row of bow of ship
     */
    public void setBowRow(int row) {
        this.bowRow = row;
    }

    /**
     * sets the bow column for a given ship
     * @param column of bow of ship
     */
    public void setBowColumn(int column) {
        this.bowColumn = column;
    }

    /**
     * sets the horizontal boolean for a given ship
     * @param horizontal "true" or "false"
     */
    public void setHorizontal(boolean horizontal) {
        this.horizontal = horizontal;
    }

    //constructor

    /**
     * this constructor is empty and is to be implemented in subclasses
     * it returns a particular string for each ship type
     * @return string type of ship
     */
    public abstract String getShipType();

    //methods

    /**
     * This method decides whether a given ship can placed in a spot
     * @param row of bow of ship
     * @param column of bow of ship
     * @param horizontal indicator whether horizontal or not
     * @param ocean given ocean
     * @return "true" or "false"
     */
    boolean okToPlaceShipAt(int row, int column, boolean horizontal, Ocean ocean) {

        if(!horizontal) {
            //if row + length is too big or row - length too small, return false
            if (row - getLength() < 0) {
                return false;
            }
            //if ship is vertical, iterate over the row starting at given row-length and go up to row + 1
            //covering each spot to next to the given row and column
            for (int nRow = row - getLength(); nRow <= row + 1; nRow++) {
                if (ocean.isOccupied(nRow, column - 1) || ocean.isOccupied(nRow, column + 1) || ocean.isOccupied(nRow, column) || ocean.isOccupied(row, column)) {
                    return false;
                }
            }
        }
        if(horizontal) {
            //if column - length too small, return false
            if(column - getLength() < 0) {
                return false;
            }
            //if ship is horizontal, this starts iterating at the column-length and goes up to the given column + 1
            for(int nCol = column - getLength(); nCol <= column + 1; nCol++) {
                if(ocean.isOccupied(row-1,nCol) || ocean.isOccupied(row+1,nCol) || ocean.isOccupied(row,nCol) || ocean.isOccupied(row,column)) {
                    return false;
                }
            }
        }
        //if neither of the two prior constraints are hit, then it will return true
        return true;
    }

    /**
     * This class represents actually placing the ship into the ocean
     * @param row of the bow of the ship
     * @param column of the bow of the ship
     * @param horizontal whether horizontal or not
     * @param ocean array
     */
    void placeShipAt(int row, int column, boolean horizontal, Ocean ocean) {
        //set the bowrow, bowcolumn, and horizontal
        this.setBowRow(row);
        this.setBowColumn(column);
        this.setHorizontal(horizontal);

        //if horizontal, places ship depending on the given type and length
        if (horizontal) {
            if (getShipType() == "empty") {
                ocean.getShipArray()[row][column] = this;
            }
            if (getShipType() == "battleship") {
                ocean.getShipArray()[row][column] = this;
                ocean.getShipArray()[row][column - 1] = this;
                ocean.getShipArray()[row][column - 2] = this;
                ocean.getShipArray()[row][column - 3] = this;
            }
            if (getShipType() == "destroyer") {
                ocean.getShipArray()[row][column] = this;
                ocean.getShipArray()[row][column - 1] = this;
            }
            if (getShipType() == "submarine") {
                ocean.getShipArray()[row][column] = this;
            }
            if (getShipType() == "cruiser") {
                ocean.getShipArray()[row][column] = this;
                ocean.getShipArray()[row][column - 1] = this;
                ocean.getShipArray()[row][column - 2] = this;
            }
        }
        //if not horizontal, places ship depending on the given type and length
        if (!horizontal) {
            if (getShipType() == "empty") {
                ocean.getShipArray()[row][column] = this;
            }
            if (getShipType() == "battleship") {
                ocean.getShipArray()[row][column] = this;
                ocean.getShipArray()[row - 1][column] = this;
                ocean.getShipArray()[row - 2][column] = this;
                ocean.getShipArray()[row - 3][column] = this;
            }
            if (getShipType() == "destroyer") {
                ocean.getShipArray()[row][column] = this;
                ocean.getShipArray()[row - 1][column] = this;
            }
            if (getShipType() == "submarine") {
                ocean.getShipArray()[row][column] = this;

            }
            if (getShipType() == "cruiser") {
                ocean.getShipArray()[row][column] = this;
                ocean.getShipArray()[row - 1][column] = this;
                ocean.getShipArray()[row - 2][column] = this;
            }
        }
    }

    /**
     * This is used to modify the hit array based on if a given shot hits a ship
     * @param row of ship
     * @param column of ship
     * @return boolean
     */
    boolean shootAt(int row, int column) {
        //if not sunk and not horizontal, modify the given array index based on row and column and return true
        if (!isSunk() && !horizontal) {
            if (column == bowColumn && row <= bowRow) {
                getHit()[bowRow - row] = true;
                return true;
            }
        }
        //if not sunk and horizontal, modify the given array index based on row and column and return true
        if (!isSunk() && horizontal) {
            if (row == bowRow && column <= bowColumn) {
                getHit()[bowColumn - column] = true;
                return true;
            }
        }
        //if neither, return false
        return false;
    }

    /**
     * This checks if booleans are true, and if so, then the ship is sunk
     * @return boolean true or false
     */
    boolean isSunk() {
        int count = 0; //initialize count
        //iterate over boolean hit array
        for (Boolean bool : getHit()) {
            if (bool == true) {
                count++; //increment count if true
            }
        }
        if (count == getHit().length) { //if all are true return true
            return true;
        } else {
            return false;
        }
    }

    /**
     * This checks if the ship is sunk, if so return "s", if not return "x"
     * @return "s" or "x"
     */
    @Override
    public String toString() {
        if(!isSunk()) {
            return "x";
        }
        return "s";
        }

    /**
     * This method is strictly for the printwithships debugging method in ocean
     * @return first letter of string type of ship
     */
      String shipLetterforPrinting() {
        if(getShipType() == "destroyer") {
            return "d";
        }
        if(getShipType() == "battleship") {
            return "b";
        }
        if(getShipType() == "cruiser") {
            return "c";
        }
        if(getShipType() == "submarine") {
            return "s";
        }
        return "";
    }
}
