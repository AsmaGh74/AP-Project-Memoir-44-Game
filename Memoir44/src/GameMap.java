import java.util.ArrayList;
import java.util.HashMap;

/**
 * GameMap class implements and presents the map of the game.
 * @author Asma
 * @version 1.0
 */
public class GameMap {
    // this HashMap stores type of every hexagonal in the game map
    private HashMap<Location, HexagonalType> hexagons;

    /**
     * Create a new map for the game.
     */
    public GameMap(){
        hexagons = new HashMap<Location, HexagonalType>();
        // Set type of the hexagons based on project definition.
        setHexagons();
    }

    /**
     * Set type of the hexagons based on project definition.
     */
    private void setHexagons(){
        // define locations for rivers, bridges, hills, forests, shelters, and cities.
        Location[] rivers = {new Location(2,3), new Location(2,4),
                new Location(2,5), new Location(3,2), new Location(5,1)};
        Location[] bridges = {new Location(1,5), new Location(4,2)};
        Location[] hills = {new Location(1,1), new Location(1,2),
                new Location(2,8), new Location(3,7),
                new Location(5,6), new Location(5,12),
                new Location(6,6), new Location(6,12)};
        Location[] forests = {new Location(3,10), new Location(3,13), new Location(4,3),
                new Location(4,5), new Location(4,13), new Location(5,2),
                new Location(5,9), new Location(5,13), new Location(6,5),
                new Location(6,13), new Location(7,8), new Location(7,9),
                new Location(8,5), new Location(8,6), new Location(8,10)};
        Location[] shelters = {new Location(2,6)};
        Location[] cities = {new Location(1,4), new Location(3,1), new Location(7,3),
                new Location(5,7), new Location(5,11), new Location(9,12)};

        // first set all the hexagons to GROUND.
        for (int i = 1; i < 10; i++){
            for (int j = 1; j < 14; j++){
                Location location = new Location(i, j);
                hexagons.put(location, HexagonalType.GROUND);
            }
        }

        // set real types of the hexagons
        for (Location location:rivers) {
            for (Location ele:hexagons.keySet()) {
                if (ele.equals(location)) hexagons.put(ele, HexagonalType.RIVER);
            }
        }
        for (Location location:hills) {
            for (Location ele:hexagons.keySet()) {
                if (ele.equals(location)) hexagons.put(ele, HexagonalType.HILL);
            }
        }
        for (Location location:forests) {
            for (Location ele:hexagons.keySet()) {
                if (ele.equals(location)) hexagons.put(ele, HexagonalType.FOREST);
            }
        }
        for (Location location:bridges) {
            for (Location ele:hexagons.keySet()) {
                if (ele.equals(location)) hexagons.put(ele, HexagonalType.BRIDGE);
            }
        }
        for (Location location:shelters) {
            for (Location ele:hexagons.keySet()) {
                if (ele.equals(location)) hexagons.put(ele, HexagonalType.SHELTER);
            }
        }
        for (Location location:cities) {
            for (Location ele:hexagons.keySet()) {
                if (ele.equals(location)) hexagons.put(ele, HexagonalType.CITY);
            }
        }
    }

    /**
     * Set color for text and background.
     * @param textColor  color of the text
     * @param backgroundColor  color of the background
     */
    private void setTextAndBackgroundColor(Color textColor, Color backgroundColor){
        System.out.print(textColor);
        System.out.print(backgroundColor);
    }

    /**
     * Return a string for every hexagon type.
     * This  string represents the hexagon type.
     * @param hexagonalType hexagon real type
     * @return  a string for every hexagon type
     */
    private String returnHexagonsType (HexagonalType hexagonalType){
        switch (hexagonalType){
            case GROUND:
                return "G";
            case HILL:
                return "H";
            case CITY:
                return "C";
            case RIVER:
                return "R";
            case BRIDGE:
                return "B";
            case SHELTER:
                return "S";
            case FOREST:
                return "F";
        }
        return "";
    }

    /**
     * Print guide for hexagons types.
     */
    private void guide(){
        System.out.println(" Guide:");
        System.out.println(" G: Ground, H: Hill, C: City, R: River, B: Bridge, S:Shelter, F: Forest");
    }

    /**
     * Draw map of the game.
     */
    public void drawMap(){
        setTextAndBackgroundColor(Color.BLACK_BOLD, Color.WHITE_BACKGROUND);
        guide();
        setTextAndBackgroundColor(Color.BLUE_BOLD, Color.YELLOW_BACKGROUND);
        // define row and column number variables
        int row = 0;
        int column = 0;
        Location location = new Location(row, column);
        for (int j = 0; j < 5; j++) {
            //round 1
            System.out.print("     *");
            for (int i = 0; i < 13; System.out.print("           *"), i++) ;
            System.out.println();

            System.out.print("   ");
            for (int i = 0; i < 26; System.out.print("     *"), i++) ;
            System.out.println("     *");

//            System.out.print("      ");
//            for (int i = 0; i < 14; System.out.print("     *      "), i++) ;
//            System.out.println();

            row = 2*j+1;
            location.setRow(row);
            System.out.print("      ");
            for (int i = 0; i < 14; i++) {
                System.out.print("     *     ");
                if (i != 13){
                    column = i + 1;
                    location.setColumn(column);
                    String s = "";
                    setTextAndBackgroundColor(Color.CYAN_BOLD, Color.YELLOW_BACKGROUND);
                    for (Location ele:hexagons.keySet()) {
                        if (ele.equals(location)){
                            s = returnHexagonsType(hexagons.get(ele));
                            break;
                        }
                    }
                    System.out.printf("%s", s);
                    setTextAndBackgroundColor(Color.BLUE_BOLD, Color.YELLOW_BACKGROUND);
                }
            }
            System.out.println();

            System.out.print("      ");
            for (int i = 0; i < 14; System.out.print("     *      "), i++) ;
            System.out.println();

            // round 2
            for (int i = 0; i < 14; System.out.print("           *"), i++) ;
            System.out.println();

            System.out.print("   ");
            for (int i = 0; i < 27; System.out.print("     *"), i++) ;
            System.out.println();

//            for (int i = 0; i < 14; System.out.print("     *      "), i++) ;
//            System.out.println();

            row = 2*j+2;
            location.setRow(row);
            for (int i = 0; i < 14; i++) {
                if (i != 13 && j != 4){
                    column = i + 1;
                    location.setColumn(column);
                    String s ="";
                    System.out.print("     *     ");
                    setTextAndBackgroundColor(Color.CYAN_BOLD, Color.YELLOW_BACKGROUND);
                    for (Location ele:hexagons.keySet()) {
                        if (ele.equals(location)){
                            s = returnHexagonsType(hexagons.get(ele));
                            break;
                        }
                    }
                    System.out.printf("%s",s);
                    setTextAndBackgroundColor(Color.BLUE_BOLD, Color.YELLOW_BACKGROUND);
                }
                else System.out.print("     *      ");
            }
            System.out.println();

            if (j != 4){
                for (int i = 0; i < 14; System.out.print("     *      "), i++) ;
                System.out.println();
            }
        }
        System.out.print(Color.RESET);
    }

    public void test(){
    }
}
