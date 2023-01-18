//Emilio Guglielmelli
//Penn ID 18027147
//Collaborator: Cynthia Chavez
//We are NOT submitting the same code
package battleship;

/**
 * Describes a ship of length 1 from Ship class
 */
public class Submarine extends Ship {

    static final int LENGTH = 1; //initialize the length
    static final String TYPE = "submarine"; //initialize type of ship

    /**
     * Implements the method from ship class to initialize boolean array and length
     */
    public Submarine() {
        super(Submarine.LENGTH);
    }
    /**
     * sets the ship type of submarine from Ship class
     * @return type of ship
     */
    @Override
    public String getShipType() {
        return Submarine.TYPE;
    }
}
