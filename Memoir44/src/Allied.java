/**
 * Allied class represents Allied player.
 * @author Asma
 * @version 1.0
 */
public class Allied extends Player{
    // stores an instance from this class
    private static Allied ALLIED = null;

    /**
     * Create a new Allied player.
     * It can be initialized just once.
     */
    private Allied(String name){
        super(name,4);
        Location location = new Location(3,1);
        setPlayerSpecialLocation(location);
    }

    /**
     * We can only have one instance from this class.
     * @return  instance
     */
    public static Allied getInstance(String name){
        if (ALLIED == null) ALLIED = new Allied(name);
        return ALLIED;
    }

    /**
     * Allied player has a set of forces.
     * Add these forces for the player.
     */
    protected void addPlayerInitialForces(){
        int numberOfTanks = 3;
        int numberOfInfantries = 9;
        int numberOfArtillery = 2;
        Location[] locationOfTanks = {new Location(9,1), new Location(9,2), new Location(9,13)};
        Location[] locationOfInfantries = {new Location(5,2), new Location(5,7), new Location(5,9),
                new Location(5,12), new Location(6,5), new Location(7,8), new Location(8,2),
                new Location(8,11), new Location(9,9)};
        Location[] locationOfArtillery = {new Location(8,3), new Location(8,7)};
        // add tanks
        for (int i = 0; i < numberOfTanks; i++){
            Tank tank = new Tank(locationOfTanks[i]);
            tank.setGroupSize(3);
            getForces().add(tank);
        }
        // add infantries
        for (int i = 0; i < numberOfInfantries; i++){
            Infantry infantry = new Infantry(locationOfInfantries[i]);
            getForces().add(infantry);
        }
        // add artillery
        for (int i = 0; i < numberOfArtillery; i++){
            Artillery artillery = new Artillery(locationOfArtillery[i]);
            getForces().add(artillery);
        }
    }

    /**
     * Return the type of player (Axis or Allied) as a string.
     * @return a string for player type
     */
    protected String returnPlayerType(){
        return "Al";
    }

    /**
     * Print player type and name.
     */
    protected void printName(){
        System.out.print(" Allied player- name: ");
        super.printName();
    }
}
