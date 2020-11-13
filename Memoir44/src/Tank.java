/**
 * Tank class represents a tank as a force which can move and attack.
 * @author Asma
 * @version 1.0
 */
public class Tank extends Force{

    /**
     * Create a new tank at specific location.
     * @param location location in which the tank will be located
     */
    public Tank(Location location){
        super(location);
//        setGroupSize();  // Axis and Allied has different group size for their tanks.
    }

    /**
     * Set valid movements which is defined for the infantry in the game.
     */
    protected void setValidMovements(){
        super.getValidMoves().put(1,true);
        super.getValidMoves().put(2,true);
        super.getValidMoves().put(3,true);
    }

    /**
     * Return T for tank.
     * @return T
     */
    protected String returnForceType(){
        return "T";
    }
}
