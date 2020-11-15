import java.util.ArrayList;

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

    /**
     * Print force info.
     * Force type and group size.
     */
    protected void printInfo(){
        super.printInfo();
        System.out.print(" Tank");
    }

    /**
     * This method will print valid movements for every force.
     * Valid movements: number of moves and attack ability
     */
    protected void printValidMovements(){
        System.out.println(" Force type: Tank");
        for (Integer ele :getValidMoves().keySet()) {
            System.out.print(" " + ele + " move(s)");
            if (getValidMoves().get(ele) == true) System.out.println(" and then you can attack.");
            else System.out.println(" and then you can't attack.");
        }
    }

    /**
     * This method checks the entered movements validity for the force.
     * @param forceMovements  movements that player has selected for the force.
     * @return true if the selected movements are valid for the force
     */
    protected boolean checkMovementsValidityForForce(ArrayList<String> forceMovements){
        Location locationAfterMovements = new Location(getLocation().getRow(), getLocation().getColumn());
        int numberOfMovements = 0;
        // check for validity of movement directions
        for (String ele:forceMovements) {
            // check if player do not want to move the force
            if (String.valueOf(ele.charAt(0)).equals("0")){
                setAttackAbility(true);
                return true;
            }
            numberOfMovements += Integer.parseInt(String.valueOf(ele.charAt(0)));
            if (!MovementDirections.check(ele.substring(1))){
                System.out.println(" Invalid expression for direction!");
                return false;
            }
        }
        // check for validity of number of movements
        if (!getValidMoves().keySet().contains(numberOfMovements)){
            System.out.println(numberOfMovements); // test
            System.out.println(" Invalid number of movements for the Tank!");
            return false;
        }
        // now that both number of movements and direction strings are valid we should check the hexagons for the path
        for (String ele:forceMovements) {
            for (int i = 0; i < Integer.parseInt(String.valueOf(ele.charAt(0))); i++){
                locationAfterMovements = move(Text.convertStringToMovementDirection(ele.substring(1)), locationAfterMovements);
                if (locationAfterMovements == null){
                    System.out.println(" Destination location is out of map or full!");
                    return false;
                }
                if (GameMap.hexagonTypeInTheLocation(locationAfterMovements).equals("R") ||
                        GameMap.hexagonTypeInTheLocation(locationAfterMovements).equals("S")){
                    System.out.println(" There is a river or shelter through the path! Tank can not move through them!");
                    return false;
                }
            }
        }
        // now if every thing is ok move :)
        locationAfterMovements = new Location(getLocation().getRow(), getLocation().getColumn());
        for (String ele:forceMovements) {
            for (int i = 0; i < Integer.parseInt(String.valueOf(ele.charAt(0))); i++) {
                locationAfterMovements = move(Text.convertStringToMovementDirection(ele.substring(1)), locationAfterMovements);
                if (GameMap.hexagonTypeInTheLocation(locationAfterMovements).equals("F")){
                    System.out.println(" Now your force is tripped in forest and can't have any kind of action in this round!");
                    // now move and tripp in the forest
                    getLocation().setRow(locationAfterMovements.getRow());
                    getLocation().setColumn(locationAfterMovements.getColumn());
                    return true;
                }
                getLocation().setRow(locationAfterMovements.getRow());
                getLocation().setColumn(locationAfterMovements.getColumn());
            }
        }
        // if everything was ok and also force did not trip in the forest set force's attack ability as true
        setAttackAbility(true);
        return true;
    }

    /**
     * This method checks if the selected target location is in the appropriate distance from the attacker force.
     * @param targetForceLocation location of the selected target force
     * @return number of times that attacker can roll the dice
     */
    protected int checkForSelectedTargetValidity(Location targetForceLocation){
        int distance = 0;
        distance = getLocation().returnDistanceBetweenTwoLocations(targetForceLocation);
        if (distance > 3){
            System.out.println(distance); // test
            System.out.println(" Distance from your tank force to target is more than expected!");
            return 0;
        }
        // tank force can have 3 dice rolling generally
        System.out.println(" You can have 3 dice rollings.");
        return 3;
    }
}
