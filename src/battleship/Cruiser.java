//Emilio Guglielmelli
//Penn ID 18027147
//Collaborator: Cynthia Chavez
//We are NOT submitting the same code
package battleship;

/**
 * Describes a ship of length 3 from Ship class
 */
public class Cruiser extends Ship {

    static final int LENGTH = 3; //set length of 3
    static final String TYPE = "cruiser";

    /**
     * Sets the length of the cruiser implemented from the ship class
     */
    public Cruiser() {
        super(Cruiser.LENGTH);

    }
    /**
     * Returns the ship type as a string "type" from Ship Class
     * @return type of ship
     */
    @Override
    public String getShipType() {
        return Cruiser.TYPE;
    }


}




