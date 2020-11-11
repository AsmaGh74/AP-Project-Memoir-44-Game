import java.util.HashMap;

/**
 * Force class represents a force that player can use to attack the enemy.
 * @author Asma
 * @version 1.0
 */
public abstract class Force {
    // stores force's location on the 9*13 game map
    private Location location;
    // defines and stores valid movements and attacks for the infantry
    // Integer variable is for the number of hexagons that infantry can move
    // Boolean variable is for attack ability. if true the infantry  can attack.
    private HashMap<Integer, Boolean> validMoves = null;
    // stores the number of forces that are in a group
    private int groupSize;

    /**
     * Create a new for at specific location.
     * @param location  location in which the force will be located
     */
    public Force(Location location){
        this.location = location;
        validMoves = new HashMap<>();
        setValidMovements();
    }

    /*************************************** getter methods ************************************/
    protected Location getLocation(){return location;}
    protected HashMap<Integer, Boolean> getValidMoves(){return validMoves;}
    /*******************************************************************************************/

    /*************************************** setter methods ************************************/
    protected void setLocation(Location location){this.location = location;}
    protected void setGroupSize(int groupSize){this.groupSize = groupSize;}
    /*******************************************************************************************/

    /**
     * This method implements move and attack rules for forces.
     */
    public void moveAndAttack(){}

    abstract protected void setValidMovements();
}
