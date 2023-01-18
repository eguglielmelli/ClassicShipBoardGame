//Emilio Guglielmelli
//Penn ID 18027147
//Collaborator: Cynthia Chavez
//We are NOT submitting the same code
package battleship;

/**
 * Describes a battleship, a ship of length 4 from type Ship class
 */
public class Battleship extends Ship {

    static final int LENGTH = 4; //initialize ship length
    static final String TYPE = "battleship"; //initialize type of ship

    /**
     * sets length of ship and initializes array from Ship class
     */
    public Battleship() {
        super(Battleship.LENGTH); // call Ship constructor from ship class to make hit array and set length

    }
    /**
     * returns the type of ship from Ship class
     * @return TYPE
     */
    @Override
    public String getShipType() {
        return Battleship.TYPE;
    }

}
