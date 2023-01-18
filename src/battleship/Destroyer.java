//Emilio Guglielmelli
//Penn ID 18027147
//Collaborator: Cynthia Chavez
//We are NOT submitting the same code
package battleship;

/**
 * Describes a ship of length 2 from Ship class
 */
public class Destroyer extends Ship {

    static int LENGTH = 2; //initialize ship length
    static final String TYPE = "destroyer"; //type of ship

    /**
     *Sets length of destroyer and initializes hit array from Ship class
     */
    public Destroyer() {
        super(Destroyer.LENGTH);
    }

    /**
     * Returns the type of ship implemented from the Ship class
     * @return the type of ship
     */
    @Override
    public String getShipType() {
        return TYPE;
    }

}
