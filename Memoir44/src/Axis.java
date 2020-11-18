/**
 * Axis class represents Axis player.
 * @author Asma
 * @version 1.0
 */
public class Axis extends Player{
    // stores an instance from this class
    private static Axis AXIS = null;

    /**
     * Create a new Axis player.
     * It can initialized just once.
     */
    private Axis(String name){
        super(name,2);
        Location location = new Location(9,12);
        setPlayerSpecialLocation(location);
    }

    /**
     * We can only have one instance from this class.
     * @param name axis player's name
     * @return  instance
     */
    public static Axis getInstance(String name){
        if (AXIS == null) AXIS = new Axis(name);
        return AXIS;
    }

    /**
     * Axis player has a set of forces.
     * Add these forces for the player.
     */
    protected void addPlayerInitialForces(){
        int numberOfTanks = 6;
        int numberOfInfantries = 7;
        Location[] locationOfTanks = {new Location(1,1), new Location(1,6), new Location(1,9),
                new Location(1,12), new Location(2,7), new Location(2,12)};
        Location[] locationOfInfantries = {new Location(1,2), new Location(1,3), new Location(1,8),
                new Location(1,11), new Location(1,13), new Location(2,6), new Location(2,10)};
        // add tanks
        for (int i = 0; i < numberOfTanks; i++){
            Tank tank = new Tank(locationOfTanks[i]);
            tank.setGroupSize(4);
            getForces().add(tank);
        }
        // add infantries
        for (int i = 0; i < numberOfInfantries; i++){
            Infantry infantry = new Infantry(locationOfInfantries[i]);
            getForces().add(infantry);
        }
    }

    /**
     * Return the type of player (Axis or Allied) as a string.
     * @return a string for player type
     */
    protected String returnPlayerType(){
        return "Ax";
    }

    /**
     * Print player type and name.
     */
    protected void printName(){
        System.out.print(" Axis player- name: ");
        super.printName();
    }
}
