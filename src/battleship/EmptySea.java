
package battleship;

/**
 * Class represents an emptysea as an extension of the Ship Parent class
 */
public class EmptySea extends Ship {

    static final int LENGTH = 1; //initialize length

    /**
     * Sets the length of emptysea implemented from Ship class and initializes array
     */
    public EmptySea() {
        super(EmptySea.LENGTH);
    }

    /**
     * Overridden method from Ship that always returns false
     * @param row
     * @param column
     * @return false
     */
    @Override
    boolean shootAt(int row, int column) {
        return false;
    }

    /**
     * Overridden isSunk method from Ship class that returns false indicating no ships sunk
     * @return false
     */
    @Override
    boolean isSunk() {
        return false;
    }

    /**
     * Overridden toString method from Ship that shows "-" to indicate if a shot has been fired but no hit
     * @return "-"
     */
    @Override
    public String toString() {
        return "-";
    }

    /**
     * Overridden method from Ship that returns "empty" for ship type
     * @return "empty"
     */
    @Override
    public String getShipType() {
        return "empty";
    }
}
