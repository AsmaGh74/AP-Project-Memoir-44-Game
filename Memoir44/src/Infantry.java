import java.util.ArrayList;
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
        setGroupSize(4);
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

    /**
     * Return I for infantry.
     * @return I
     */
    protected String returnForceType(){
        return "I";
    }

    /**
     * Print force info.
     * Force type and group size.
     */
    protected void printInfo(){
        super.printInfo();
        System.out.print(" Infantry");
    }

    /**
     * This method will print valid movements for every force.
     * Valid movements: number of moves and attack ability
     */
    protected void printValidMovements(){
        System.out.println(" Force type: Infantry");
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
            System.out.println(" Invalid number of movements for the Infantry!");
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
                if (GameMap.hexagonTypeInTheLocation(locationAfterMovements).equals("R")){
                    System.out.println(" There is a river through the path! No force can move through it!");
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
        return true;
    }

    /**
     * This method checks if the selected target location is in the appropriate distance from the attacker force.
     * @param targetForceLocation location of the selected target force
     * @return number of times that attacker can roll the dice
     */
    protected int checkForSelectedTargetValidity(Location targetForceLocation) {
        int distance = 0;
        distance = getLocation().returnDistanceBetweenTwoLocations(targetForceLocation);
        switch (distance){
            case 1:
                System.out.println(" You can have 3 dice rollings.");
                return 3;
            case 2:
                System.out.println(" You can have 2 dice rollings.");
                return 2;
            case 3:
                System.out.println(" You can have 1 dice rollings.");
                return 1;
        }
        System.out.println(" Distance from your infantry force to target is more than expected!");
        return 0;
    }
}
