import java.util.HashMap;

/**
 * Infantry class represents an individual infantry that can move and attack.
 * @author Asma
 * @version 1.0
 */
public class Infantry extends Force{

    /**
     * Create a new infantry at specific location.
     * @param location location in which the infantry will be located
     */
    public Infantry(Location location){
        super(location);
    }

    /**
     * Set valid movements which is defined for the infantry in the game.
     */
    protected void setValidMovements(){
        // infantry can move one unit and attack
        super.getValidMoves().put(1,true);
        // infantry can move two units but can not attack
        super.getValidMoves().put(2,false);
    }
}
