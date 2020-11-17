import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * GameMap class implements and presents the map of the game and players.
 * @author Asma
 * @version 1.0
 */
public class GameMap {
    // this HashMap stores type of every hexagonal in the game map
    private static HashMap<Location, HexagonalType> hexagons;
    // stores Axis player on the map
    private static Axis axisPlayer;
    // stores Allied player on the map
    private static Allied alliedPlayer;
    // stores dice roller for attack part of the game
    private DiceRoller diceRoller;

    /**
     * Create a new map for the game.
     */
    public GameMap(String axisPlayerName, String alliedPlayerName) {
        hexagons = new HashMap<Location, HexagonalType>();
        // Set type of the hexagons based on project definition.
        setHexagons();
        // initialize the only Axis player
        axisPlayer = Axis.getInstance(axisPlayerName);
        // initialize the only Allied player
        alliedPlayer = Allied.getInstance(alliedPlayerName);
        diceRoller = new DiceRoller();
    }

    /********************************************************** getter methods *****************************************/
    public HashMap<Location, HexagonalType> getHexagons() {
        return hexagons;
    }
    /*******************************************************************************************************************/

    /***************************************************** Map visualization part **************************************/

    /**
     * Set type of the hexagons based on project definition.
     */
    private void setHexagons() {
        // define locations for rivers, bridges, hills, forests, shelters, and cities.
        Location[] rivers = {new Location(2, 3), new Location(2, 4),
                new Location(2, 5), new Location(3, 2), new Location(5, 1)};
        Location[] bridges = {new Location(1, 5), new Location(4, 2)};
        Location[] hills = {new Location(1, 1), new Location(1, 2),
                new Location(2, 8), new Location(3, 7),
                new Location(5, 6), new Location(5, 12),
                new Location(6, 6), new Location(6, 12)};
        Location[] forests = {new Location(3, 10), new Location(3, 13), new Location(4, 3),
                new Location(4, 5), new Location(4, 13), new Location(5, 2),
                new Location(5, 9), new Location(5, 13), new Location(6, 5),
                new Location(6, 13), new Location(7, 8), new Location(7, 9),
                new Location(8, 5), new Location(8, 6), new Location(8, 10)};
        Location[] shelters = {new Location(2, 6)};
        Location[] cities = {new Location(1, 4), new Location(3, 1), new Location(7, 3),
                new Location(5, 7), new Location(5, 11), new Location(9, 12)};

        // first set all the hexagons to GROUND.
        for (int i = 1; i < 10; i++) {
            for (int j = 1; j < 14; j++) {
                Location location = new Location(i, j);
                hexagons.put(location, HexagonalType.GROUND);
            }
        }

        // set real types of the hexagons
        for (Location location : rivers) {
            for (Location ele : hexagons.keySet()) {
                if (ele.equals(location)) hexagons.put(ele, HexagonalType.RIVER);
            }
        }
        for (Location location : hills) {
            for (Location ele : hexagons.keySet()) {
                if (ele.equals(location)) hexagons.put(ele, HexagonalType.HILL);
            }
        }
        for (Location location : forests) {
            for (Location ele : hexagons.keySet()) {
                if (ele.equals(location)) hexagons.put(ele, HexagonalType.FOREST);
            }
        }
        for (Location location : bridges) {
            for (Location ele : hexagons.keySet()) {
                if (ele.equals(location)) hexagons.put(ele, HexagonalType.BRIDGE);
            }
        }
        for (Location location : shelters) {
            for (Location ele : hexagons.keySet()) {
                if (ele.equals(location)) hexagons.put(ele, HexagonalType.SHELTER);
            }
        }
        for (Location location : cities) {
            for (Location ele : hexagons.keySet()) {
                if (ele.equals(location)) hexagons.put(ele, HexagonalType.CITY);
            }
        }
    }

    /**
     * Return a string for every hexagon type.
     * This  string represents the hexagon type.
     *
     * @param hexagonalType hexagon real type
     * @return a string for every hexagon type
     */
    private static String returnHexagonsType(HexagonalType hexagonalType) {
        switch (hexagonalType) {
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
    private void guide() throws InterruptedException {
        Text.setTextAndBackgroundColor(Color.BLACK_BOLD, Color.WHITE_BACKGROUND);
        System.out.println();
        System.out.println(" Guide:");
        System.out.println(" G: Ground, H: Hill, C: City, R: River, B: Bridge, S:Shelter, F: Forest");
        TimeUnit.MILLISECONDS.sleep(300);
        System.out.println(" Axis forces: Red color, Allied forces: Green color");
        TimeUnit.MILLISECONDS.sleep(300);
        System.out.println(" T: Tank, I: Infantry, A: Artillery");
        TimeUnit.MILLISECONDS.sleep(300);
        Text.setTextAndBackgroundColor(Color.RED_BOLD, Color.BLACK_BACKGROUND);
        System.out.print(" Axis spatial location: row:9 column:12  ");
        Text.setTextAndBackgroundColor(Color.BLACK_BOLD, Color.WHITE_BACKGROUND);
        System.out.println();
        TimeUnit.MILLISECONDS.sleep(300);
        Text.setTextAndBackgroundColor(Color.RED_BOLD, Color.BLACK_BACKGROUND);
        System.out.print(" Allied spatial location: row:3 column:1 ");
        Text.setTextAndBackgroundColor(Color.BLACK_BOLD, Color.WHITE_BACKGROUND);
        System.out.println();
        System.out.println();
        TimeUnit.MILLISECONDS.sleep(300);
    }

    /**
     * Find the type and number of forces that is in the specified location.
     *
     * @param location force location
     * @return a string of number and type of force
     */
    private String forceInTheLocation(Location location) {
        String s = "  ";
        for (Force ele : axisPlayer.getForces()) {
            if (ele.getLocation().equals(location)) {
                s = (ele.getGroupSize()) + ele.returnForceType();
                Text.setTextAndBackgroundColor(Color.RED_BOLD, Color.BLACK_BACKGROUND);
            }
        }
        for (Force ele : alliedPlayer.getForces()) {
            if (ele.getLocation().equals(location)) {
                s = (ele.getGroupSize()) + ele.returnForceType();
                Text.setTextAndBackgroundColor(Color.GREEN_BOLD, Color.BLACK_BACKGROUND);
            }
        }
        return s;
    }

    /**
     * Find the type hexagon that is in the specified location.
     *
     * @param location hexagon location
     * @return a string of type of hexagon
     */
    public static String hexagonTypeInTheLocation(Location location) {
        String s = "";
        for (Location ele : hexagons.keySet()) {
            if (ele.equals(location)) {
                s = returnHexagonsType(hexagons.get(ele));
                break;
            }
        }
        return s;
    }

    /**
     * Draw map of the game.
     */
    public void drawMap () throws InterruptedException {
        guide();
        Text.setTextAndBackgroundColor(Color.BLUE_BOLD, Color.BLACK_BACKGROUND);
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

            String s = "";
            row = 2 * j + 1;
            location.setRow(row);
            System.out.print("      ");
            for (int i = 0; i < 14; i++) {
                System.out.print("     *     ");
                if (i != 13) {
                    column = i + 1;
                    location.setColumn(column);
                    // find hexagon type in the location
                    s = hexagonTypeInTheLocation(location);
                    System.out.printf("%s", s);
                }
            }
            System.out.println();

            System.out.print("      ");
            for (int i = 0; i < 14; i++) {
                System.out.print("     *    ");
                column = i + 1;
                location.setColumn(column);
                // find the force that is in the location
                s = forceInTheLocation(location);
                System.out.printf("%s", s);
                Text.setTextAndBackgroundColor(Color.BLUE_BOLD, Color.BLACK_BACKGROUND);
            }
            System.out.println();

            TimeUnit.MILLISECONDS.sleep(500);
            // round 2
            for (int i = 0; i < 14; System.out.print("           *"), i++) ;
            System.out.println();

            System.out.print("   ");
            for (int i = 0; i < 27; System.out.print("     *"), i++) ;
            System.out.println();

            row = 2 * j + 2;
            location.setRow(row);
            for (int i = 0; i < 14; i++) {
                if (i != 13 && j != 4) {
                    column = i + 1;
                    location.setColumn(column);
                    System.out.print("     *     ");
                    // find hexagon type in the location
                    s = hexagonTypeInTheLocation(location);
                    System.out.printf("%s", s);
                } else System.out.print("     *      ");
            }
            System.out.println();

            if (j != 4) {
                for (int i = 0; i < 14; i++) {
                    System.out.print("     *    ");
                    column = i + 1;
                    location.setColumn(column);
                    // find the force that is in the location
                    s = forceInTheLocation(location);
                    System.out.printf("%s", s);
                    Text.setTextAndBackgroundColor(Color.BLUE_BOLD, Color.BLACK_BACKGROUND);
                }
                System.out.println();
            }
        }
        System.out.print(Color.RESET);
        System.out.println();
        Text.setTextAndBackgroundColor(Color.BLACK_BOLD, Color.WHITE_BACKGROUND);
    }

    /*******************************************************************************************************************/
    /**************************************************** playing part *************************************************/

    /**
     * Check if the entered location if full or empty.
     *
     * @param location location to be checked
     * @return true if the location is empty.
     */
    public static boolean returnLocationStatusBasedOnForces(Location location) {
        for (Force ele : axisPlayer.getForces()) {
            if (ele.getLocation().equals(location)) return false;
        }
        for (Force ele : alliedPlayer.getForces()) {
            if (ele.getLocation().equals(location)) return false;
        }
        return true;
    }

    /**
     * Return the starting player based on a random number.
     *
     * @param startingPlayerNumber number of the starting player
     * @return starting player
     */
    private Player returnStartingPlayer(int startingPlayerNumber) {
        if (startingPlayerNumber % 2 == 0) return alliedPlayer;
        return axisPlayer;
    }

    protected void playTheGame(int startingPlayerNumber) throws InterruptedException {
        boolean[] beingInSpecialLocationInPreviousRound = {false, false};
        Text.setTextAndBackgroundColor(Color.BLACK_BOLD, Color.WHITE_BACKGROUND);
        System.out.println();
        System.out.print(" The game will start with: ");
        int playerNumber = startingPlayerNumber;
        returnStartingPlayer(playerNumber).printName();
        int k = 0;
        while (!(returnStartingPlayer(playerNumber).getScores() == 6 || returnStartingPlayer(playerNumber + 1).getScores() == 6)) { // change the condition to  k == 0
            showPlayerCards(returnStartingPlayer(playerNumber));
            Text.setTextAndBackgroundColor(Color.BLACK_BOLD, Color.WHITE_BACKGROUND);
            // say the player to choose one card
            Scanner scanner = new Scanner(System.in);
            // generate valid numbers as string and add to an arraylist
            ArrayList<String> validCardNumbers = new ArrayList<>();
            for (int i = 0; i < returnStartingPlayer(playerNumber).getNumberOfCards();
            validCardNumbers.add(Integer.toString(i + 1)), i++);
            String cardNumber;
            do {
                System.out.println(" Now pick a card and enter it's number (please enter a valid number):");
                cardNumber = scanner.nextLine();
            } while (!(validCardNumbers.contains(cardNumber)));
            int numberOfUnits = returnStartingPlayer(playerNumber).returnCardNumberOfUnits(Integer.valueOf(cardNumber) - 1);
            boolean differentUnits = returnStartingPlayer(playerNumber).returnCardDifferentForceType(Integer.valueOf(cardNumber) - 1);
            System.out.print(" Now you can select " + numberOfUnits + " ");
            Text.setTextAndBackgroundColor(Color.RED_BOLD, Color.BLACK_BACKGROUND);
            if (differentUnits) System.out.print(" different or identical");
            else System.out.print(" identical");
            Text.setTextAndBackgroundColor(Color.BLACK_BOLD, Color.WHITE_BACKGROUND);
            System.out.println(" unit(s)\n Enter number of row (1-9) and column (1-13) with a space for every unit based on the map.\n" +
                    " Example:1 2");
            // now remove selected card from player's cards
            returnStartingPlayer(playerNumber).removeCardFromListByIndex(Integer.valueOf(cardNumber) - 1);
            // let the player to select forces
            ArrayList<Integer> numberOfSelectedForces = new ArrayList<>();
            int rowNumber = 0;
            int columnNumber = 0;
            do {
                numberOfSelectedForces.removeAll(numberOfSelectedForces);
                for (int i = 0; i < numberOfUnits; i++) {
                    String[] location;
                    do {
                        Text.setTextAndBackgroundColor(Color.RED_BOLD, Color.BLACK_BACKGROUND);
                        System.out.print(" Enter row and column number for force number " + (i + 1) + " :");
                        Text.setTextAndBackgroundColor(Color.BLACK_BOLD,Color.WHITE_BACKGROUND);
                        System.out.println();
                        location = scanner.nextLine().split(" ");
                    } while (!checkForValidRowAndColumnNumber(location));
                    rowNumber = Integer.valueOf(location[0]);
                    columnNumber = Integer.valueOf(location[1]);
                    numberOfSelectedForces.add(returnStartingPlayer(playerNumber).returnPlayerForceIndexBasedOnLocation(rowNumber, columnNumber));
                }
            } while (!returnStartingPlayer(playerNumber).checkForNumbersOfSelectedForces(differentUnits, numberOfSelectedForces));
            // draw the map
            drawMap();
            System.out.println(" Forces selected successfully.");
            Text.setTextAndBackgroundColor(Color.RED_BOLD, Color.BLACK_BACKGROUND);
            System.out.print(" Now moving part starts.");
            Text.setTextAndBackgroundColor(Color.BLACK_BOLD, Color.WHITE_BACKGROUND);
            System.out.println();
            // ask player for selected forces movements
            ArrayList<String> movements = new ArrayList<>();
            Text.listMovementDirections();
            for (Integer ele : numberOfSelectedForces) {
                String[] moves;
                do {
                    movements.removeAll(movements);
                    Text.setTextAndBackgroundColor(Color.RED_BOLD, Color.BLACK_BACKGROUND);
                    System.out.print(" Select your move for force ");
                    returnStartingPlayer(playerNumber).getForces().get(ele - 1).getLocation().printInfo();
                    System.out.print(" ");
                    Text.setTextAndBackgroundColor(Color.BLACK_BOLD, Color.WHITE_BACKGROUND);
                    System.out.println();
                    System.out.println(" Valid movements:");
                    // print valid movements for the force
                    returnStartingPlayer(playerNumber).getForces().get(ele - 1).printValidMovements();
                    System.out.println(" Enter your movement (example: 1R 2UL or just 0 to not move):");
                    moves = scanner.nextLine().split(" "); // add to array list
                    if (moves[0].equals("0")) break;
                    for (String string : moves) {
                        if (string != null) movements.add(string);
                    }
                } while (!returnStartingPlayer(playerNumber).getForces().get(ele - 1).checkMovementsValidityForForce(movements));
                if (moves[0].equals("0")) {
                    returnStartingPlayer(playerNumber).getForces().get(ele - 1).setAttackAbility(true);
                    continue;
                }
            }
            // now we should check for spacial locations for player's forces and change the scores based on the result
            if (returnStartingPlayer(playerNumber).checkForBeingInSpacialLocation()) {
                if (!beingInSpecialLocationInPreviousRound[playerNumber % 2]) {
                    returnStartingPlayer(playerNumber).addToScores();
                    beingInSpecialLocationInPreviousRound[playerNumber % 2] = true;
                    Text.setTextAndBackgroundColor(Color.RED_BOLD, Color.BLACK_BACKGROUND);
                    System.out.print(" You are in the special location. New Score ***");
                    Text.setTextAndBackgroundColor(Color.BLACK_BOLD, Color.WHITE_BACKGROUND);
                    System.out.println();
                    System.out.println();
                    TimeUnit.SECONDS.sleep(1);
                }
            } else {
                if (beingInSpecialLocationInPreviousRound[playerNumber % 2]) {
                    returnStartingPlayer(playerNumber).subtractFromScores();
                    beingInSpecialLocationInPreviousRound[playerNumber % 2] = false;
                    Text.setTextAndBackgroundColor(Color.RED_BOLD, Color.BLACK_BACKGROUND);
                    System.out.print(" You lost the special location! SO you lost a score!");
                    Text.setTextAndBackgroundColor(Color.BLACK_BOLD, Color.WHITE_BACKGROUND);
                    System.out.println();
                    System.out.println();
                    TimeUnit.SECONDS.sleep(1);
                }
            }
            // now that we have valid movements for all the forces, we should redraw the game map
            // show the scores and draw the map
            if (playerNumber % 2 == 0) Text.showScores(returnStartingPlayer(playerNumber + 1).getScores(),
                    returnStartingPlayer(playerNumber).getScores());
            else Text.showScores(returnStartingPlayer(playerNumber).getScores(),
                    returnStartingPlayer(playerNumber + 1).getScores());
            TimeUnit.SECONDS.sleep(1);
            drawMap();
            System.out.println(" The forces moved successfully.");
            Text.setTextAndBackgroundColor(Color.RED_BOLD, Color.BLACK_BACKGROUND);
            System.out.print(" Now attacking part starts.");
            Text.setTextAndBackgroundColor(Color.BLACK_BOLD, Color.WHITE_BACKGROUND);
            System.out.println();
            // now we should start attack part
            for (Integer ele : numberOfSelectedForces) {
                Force attackerForce = returnStartingPlayer(playerNumber).getForces().get(ele - 1);
                // we should check force's attack ability status
                if (attackerForce.getAttackAbility()) {
                    int numberOfTargetForceInTheList = 0;
                    int initialNumberOfDiceRolling = 0;
                    String[] location;
                    do {
                        do {
                            Text.setTextAndBackgroundColor(Color.RED_BOLD, Color.BLACK_BACKGROUND);
                            System.out.print(" Select target for force ");
                            returnStartingPlayer(playerNumber).getForces().get(ele - 1).getLocation().printInfo();
                            System.out.print(" ");
                            Text.setTextAndBackgroundColor(Color.BLACK_BOLD, Color.WHITE_BACKGROUND);
                            System.out.println();
                            System.out.println(" Enter number of row (1-9) and column (1-13) with a space based on the map " +
                                    "or just enter 0 to not attack.\n" + " Example:1 2");
                            location = scanner.nextLine().split(" ");
                            if (location[0].equals("0")) break; // break the do-while loop
                        }while (!checkForValidRowAndColumnNumber(location));
                        if (location[0].equals("0")) break; // break the do-while loop
                        rowNumber = Integer.valueOf(location[0]);
                        columnNumber = Integer.valueOf(location[1]);
                        numberOfTargetForceInTheList = returnStartingPlayer(playerNumber + 1).returnPlayerForceIndexBasedOnLocation(rowNumber, columnNumber);
                        if (numberOfTargetForceInTheList >= 1 &&
                                numberOfTargetForceInTheList <= returnStartingPlayer(playerNumber + 1).getForces().size()){
                            initialNumberOfDiceRolling = attackerForce.checkForSelectedTargetValidity(returnStartingPlayer(playerNumber + 1).getForces().get(numberOfTargetForceInTheList - 1).getLocation());
                        }
                    } while (initialNumberOfDiceRolling == 0); // check for target location validity
                    // now player has selected the target location
                    if (location[0].equals("0")) continue; // go to next selected force to attack
                    Force targetForce = returnStartingPlayer(playerNumber + 1).getForces().get(numberOfTargetForceInTheList - 1);
                    // if the attacker type is allied and axis force is in the shelter, allied tank should roll the dice twice less
                    // and allied infantry should roll the dice once less
                    if (returnStartingPlayer(playerNumber).returnPlayerType().equals("Al")) {
//                        System.out.println(" check for allied al"); // test
                        if (attackerForce.returnForceType().equals("T")) initialNumberOfDiceRolling -= 2;
                        if (attackerForce.returnForceType().equals("I")) initialNumberOfDiceRolling--;
                    }
                    // now roll the dice with this initial number for dice rolling
                    ArrayList<Integer> diceNumbers = new ArrayList<>();
                    diceNumbers = diceRoller.rollTheDice(initialNumberOfDiceRolling,
                            hexagonTypeInTheLocation(attackerForce.getLocation()),
                            hexagonTypeInTheLocation(targetForce.getLocation()),
                            attackerForce);
                    Text.printDiceNumbersHorizontally(diceNumbers);
                    // now we have the random selected dice numbers
                    // now based on target force type and dice numbers we should determine attack ability
                    if (diceNumbers.contains(3) || diceNumbers.contains(4)) {
                        Text.setTextAndBackgroundColor(Color.RED_BOLD, Color.BLACK_BACKGROUND);
                        System.out.print(" You can not attack with this force! (dice error)");
                        Text.setTextAndBackgroundColor(Color.BLACK_BOLD,Color.WHITE_BACKGROUND);
                        System.out.println();
                        System.out.println();
                        TimeUnit.SECONDS.sleep(1);
                        continue;
                    }
                    if (diceNumbers.contains(5)) {
                        targetForce.setGroupSize(targetForce.getGroupSize() - 1);
                        Text.setTextAndBackgroundColor(Color.RED_BOLD, Color.BLACK_BACKGROUND);
                        System.out.print(" Attack completed!");
                        Text.setTextAndBackgroundColor(Color.BLACK_BOLD,Color.WHITE_BACKGROUND);
                        System.out.println();
                        System.out.println();
                        TimeUnit.SECONDS.sleep(1);
                        if (targetForce.getGroupSize() == 0) {
                            returnStartingPlayer(playerNumber).addToScores();
                            // remove the force with 0 group size
                            returnStartingPlayer(playerNumber + 1).getForces().remove(numberOfTargetForceInTheList-1);
                            Text.setTextAndBackgroundColor(Color.RED_BOLD, Color.BLACK_BACKGROUND);
                            System.out.print(" You get a new score ***");
                            Text.setTextAndBackgroundColor(Color.BLACK_BOLD,Color.WHITE_BACKGROUND);
                            System.out.println();
                            System.out.println();
                            TimeUnit.SECONDS.sleep(1);
                        }
                    } else {
                        switch (targetForce.returnForceType()) {
                            case "I":
                                if (diceNumbers.contains(1) || diceNumbers.contains(6)) { // attacker force can attack
                                    targetForce.setGroupSize(targetForce.getGroupSize() - 1);
                                    Text.setTextAndBackgroundColor(Color.RED_BOLD, Color.BLACK_BACKGROUND);
                                    System.out.print(" Attack completed!");
                                    Text.setTextAndBackgroundColor(Color.BLACK_BOLD,Color.WHITE_BACKGROUND);
                                    System.out.println();
                                    System.out.println();
                                    TimeUnit.SECONDS.sleep(1);
                                    if (targetForce.getGroupSize() == 0) {
                                        returnStartingPlayer(playerNumber).addToScores();
                                        // remove the force with 0 group size
                                        returnStartingPlayer(playerNumber + 1).getForces().remove(numberOfTargetForceInTheList-1);
                                        Text.setTextAndBackgroundColor(Color.RED_BOLD, Color.BLACK_BACKGROUND);
                                        System.out.print(" You get a new score ***");
                                        Text.setTextAndBackgroundColor(Color.BLACK_BOLD,Color.WHITE_BACKGROUND);
                                        System.out.println();
                                        System.out.println();
                                        TimeUnit.SECONDS.sleep(1);
                                    }
                                }
                                break;
                            case "T":
                                if (diceNumbers.contains(2)) { // attacker force can attack
                                    targetForce.setGroupSize(targetForce.getGroupSize() - 1);
                                    Text.setTextAndBackgroundColor(Color.RED_BOLD, Color.BLACK_BACKGROUND);
                                    System.out.print(" Attack completed!");
                                    Text.setTextAndBackgroundColor(Color.BLACK_BOLD,Color.WHITE_BACKGROUND);
                                    System.out.println();
                                    System.out.println();
                                    TimeUnit.SECONDS.sleep(1);
                                    if (targetForce.getGroupSize() == 0) {
                                        returnStartingPlayer(playerNumber).addToScores();
                                        // remove the force with 0 group size
                                        returnStartingPlayer(playerNumber + 1).getForces().remove(numberOfTargetForceInTheList-1);
                                        Text.setTextAndBackgroundColor(Color.RED_BOLD, Color.BLACK_BACKGROUND);
                                        System.out.print(" You get a new score ***");
                                        Text.setTextAndBackgroundColor(Color.BLACK_BOLD,Color.WHITE_BACKGROUND);
                                        System.out.println();
                                        System.out.println();
                                        TimeUnit.SECONDS.sleep(1);
                                    }
                                }
                                break;
                            default:
                                Text.setTextAndBackgroundColor(Color.RED_BOLD, Color.BLACK_BACKGROUND);
                                System.out.print(" Force can not attack based on the rules!");
                                Text.setTextAndBackgroundColor(Color.BLACK_BOLD, Color.WHITE_BACKGROUND);
                                System.out.println();
                        }
                    }
                    attackerForce.setAttackAbility(false);
                }
                else {
                    Text.setTextAndBackgroundColor(Color.RED_BOLD, Color.BLACK_BACKGROUND);
                    System.out.print(" Force ");
                    returnStartingPlayer(playerNumber).getForces().get(ele - 1).getLocation().printInfo();
                    System.out.print(" can't attack this round!");
                    Text.setTextAndBackgroundColor(Color.BLACK_BOLD, Color.WHITE_BACKGROUND);
                    System.out.println();
                    System.out.println();
                    TimeUnit.SECONDS.sleep(1);
                }
            }
            // show the scores and draw the map
            if (playerNumber % 2 == 0) Text.showScores(returnStartingPlayer(playerNumber + 1).getScores(),
                    returnStartingPlayer(playerNumber).getScores());
            else Text.showScores(returnStartingPlayer(playerNumber).getScores(),
                    returnStartingPlayer(playerNumber + 1).getScores());
            TimeUnit.SECONDS.sleep(1);
            drawMap();
            // now change the player
            playerNumber++;
        }
    }

    /**
     * Check for the validity of entered row and column as a string.
     *
     * @param rowAndColumn numbers of entered row and column as string
     * @return true if the entered row and column are true
     */
    private boolean checkForValidRowAndColumnNumber(String[] rowAndColumn) {
        final Set<String> validRows = new HashSet<String>(Arrays.asList("1", "2", "3", "4", "5", "6", "7", "8", "9"));
        final Set<String> validColumns = new HashSet<String>(Arrays.asList("1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13"));
        if (validRows.contains(rowAndColumn[0])) {
            if (validColumns.contains(rowAndColumn[1])) return true;
        }
        System.out.println(" Invalid number for row or/and column!");
        return false;
    }

    /*******************************************************************************************************************/
    /************************************************ printing messages part *******************************************/

    /**
     * Show player's cards
     *
     * @param player player whose cards must be showed
     */
    private void showPlayerCards(Player player) {
        System.out.println();
        player.printName();
        System.out.println();
        System.out.println(" cards list:");
        System.out.println();
        System.out.println(Color.RESET);
        System.out.println();
        player.showCards();
        System.out.println();
    }


    /*******************************************************************************************************************/
}