/**
 * Artillery class represents an artillery as a force which can be moved or can attack.
 * @author Asma
 * @version 1.0
 */
public class Artillery extends Force{

    /**
     * Create a new artillery at specific location.
     * @param location location in which the artillery will be located
     */
    public Artillery(Location location){
        super(location);
    }

    /**
     * Set valid movements which is defined for the artillery in the game.
     */
    protected void setValidMovements(){
        super.getValidMoves().put(1,false);
        super.getValidMoves().put(0,true);
    }
}
