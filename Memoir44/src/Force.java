import java.util.ArrayList;
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
    // stores the force's attack after movement
    // true if force can attack
    private boolean attackAbility;
    /**
     * Create a new force at specific location.
     * @param location  location in which the force will be located
     */
    public Force(Location location){
        this.location = location;
        validMoves = new HashMap<>();
        setValidMovements();
        // initial attack ability to false
        attackAbility = false;
    }

    /*************************************** getter methods ************************************/
    protected Location getLocation(){return location;}
    protected HashMap<Integer, Boolean> getValidMoves(){return validMoves;}
    protected int getGroupSize(){return groupSize;}
    protected boolean getAttackAbility(){return attackAbility;}
    /*******************************************************************************************/

    /*************************************** setter methods ************************************/
    protected void setLocation(Location location){this.location = location;}
    protected void setGroupSize(int groupSize){this.groupSize = groupSize;}
    protected void setAttackAbility (boolean attackAbility){this.attackAbility = attackAbility;}
    /*******************************************************************************************/

    /**
     * This method implements movement for force.
     * @return new location after movements
     */
    public Location move(MovementDirections movementDirection, Location currentLocation){
//        Location locationAfterMovements = new Location(getLocation().getRow(), getLocation().getColumn());
        Location locationAfterMovements = currentLocation;
        switch (movementDirection){
            case L:
                locationAfterMovements.setColumn(currentLocation.getColumn() - 1);
                break;
            case R:
                locationAfterMovements.setColumn(currentLocation.getColumn() + 1);
                break;
            case DR:
                if (currentLocation.getRow() % 2 == 0){
                    locationAfterMovements.setRow(currentLocation.getRow() + 1);
                }
                else {
                    locationAfterMovements.setRow(currentLocation.getRow() + 1);
                    locationAfterMovements.setColumn(currentLocation.getColumn() + 1);
                }
                break;
            case DL:
                if (currentLocation.getRow() % 2 == 0){
                    locationAfterMovements.setRow(currentLocation.getRow() + 1);
                    locationAfterMovements.setColumn(currentLocation.getColumn() - 1);
                }
                else {
                    locationAfterMovements.setRow(currentLocation.getRow() + 1);
                }
                break;
            case UR:
                if (currentLocation.getRow() % 2 == 0){
                    locationAfterMovements.setRow(currentLocation.getRow() - 1);
                }
                else {
                    locationAfterMovements.setRow(currentLocation.getRow() - 1);
                    locationAfterMovements.setColumn(currentLocation.getColumn() + 1);
                }
                break;
            case UL:
                if (currentLocation.getRow() % 2 == 0){
                    locationAfterMovements.setRow(currentLocation.getRow() - 1);
                    locationAfterMovements.setColumn(currentLocation.getColumn() - 1);
                }
                else {
                    locationAfterMovements.setRow(currentLocation.getRow() - 1);
                }
                break;
        }
        if (Location.validLocation(locationAfterMovements) && GameMap.returnLocationStatusBasedOnForces(locationAfterMovements))
            return locationAfterMovements;
        return null;
    }

    abstract protected void setValidMovements();

    /**
     * Return force type.
     * If it's a tank return T.
     * If it's an Infantry return I.
     * If it's an Artillery return A.
     * @return  force type
     */
    abstract protected String returnForceType();

    /**
     * Print force info.
     * Force type and group size.
     */
    protected void printInfo(){
        System.out.print(groupSize);
    }

    /**
     * This method will print valid movements for every force.
     * Valid movements: number of moves and attack ability
     */
    abstract protected void printValidMovements();

    /**
     * This method checks the entered movements validity for the force.
     * @param forceMovements  movements that player has selected for the force.
     * @return true if the selected movements are valid for the force
     */
    abstract protected boolean checkMovementsValidityForForce(ArrayList<String> forceMovements);

    /**
     * This method checks if the selected target location is in the appropriate distance from the attacker force.
     * @param targetForceLocation location of the selected target force
     * @return number of times that attacker can roll the dice
     */
    abstract protected int checkForSelectedTargetValidity(Location targetForceLocation);
}
