import java.util.ArrayList;

/**
 * Text class manages all texts and messages which should be printed and some related methods.
 * @author Asma
 * @version 1.0
 */
public class Text {

    /**
     * Set color for text and background.
     * @param textColor  color of the text
     * @param backgroundColor  color of the background
     */
    public static void setTextAndBackgroundColor(Color textColor, Color backgroundColor){
        System.out.print(textColor);
        System.out.print(backgroundColor);
    }

    /**
     * For every entered string fill it sides by space.
     * @param size return a string with this size
     * @param text entered string
     * @return a string with specific length
     */
    public static String stringWithSpecificSize(int size, String text){
        int add = (size - text.length())/2;
        String space = "";
        for ( int i = 0; i < add; space+= " ", i++);
        if ((size - text.length()) % 2 == 0) return space + text + space;
        return space + " " + text + space;
    }

    /**
     * List members of the array list by number.
     * @param arrayList  array list to be listed
     */
    public static void listAnArrayList(ArrayList<String> arrayList){
        setTextAndBackgroundColor(Color.BLACK_BOLD, Color.WHITE_BACKGROUND);
        System.out.println();
        int i = 1;
        for (String ele:arrayList) {
            if (i < 10) System.out.print(" 0" + i + "- ");
            else System.out.print("" + i + "- ");
            System.out.println(ele);
            i++;
        }
    }

    /**
     * List directions for movement on the map.
     */
    public static void listMovementDirections(){
        ArrayList<String> movementDirections = new ArrayList<>();
        movementDirections.add("UL: UpLeft");
        movementDirections.add("UR: UpRight");
        movementDirections.add("L: Left");
        movementDirections.add("R: Right");
        movementDirections.add("DL: DownLeft");
        movementDirections.add("DR: DownRight");
        System.out.println(" Movement directions:");
        listAnArrayList(movementDirections);
        System.out.println();
    }

    /**
     * Convert entered string to a member from enum MovementDirections class (match them)
     * @param movementDirection  one movement direction
     * @return  matched member from enum MovementDirections class
     */
    public static MovementDirections convertStringToMovementDirection(String movementDirection){
        switch (movementDirection){
            case "L":
                return MovementDirections.L;
            case "R":
                return MovementDirections.R;
            case "UL":
                return MovementDirections.UL;
            case "UR":
                return MovementDirections.UR;
            case "DL":
                return MovementDirections.DL;
            case "DR":
                return MovementDirections.DR;
        }
        return MovementDirections.L;
    }

    /**
     * Print and show the scores for both players.
     * @param axisScores  axis player scores
     * @param alliedScores  allied player scores
     */
    public static void showScores(int axisScores, int alliedScores){
//        setTextAndBackgroundColor(Color.YELLOW_BOLD, Color.WHITE_BACKGROUND);
        Text.setTextAndBackgroundColor(Color.BLACK_BOLD,Color.BLUE_BACKGROUND);
        System.out.println("   ***********************************************");
        System.out.println("   **                 SCORES                    **");
        System.out.println("   **                                           **");
        System.out.println("   **                AXIS:  "  + axisScores + "                   **");
        System.out.println("   **               ALLIED:  "  + alliedScores + "                  **");
        System.out.println("   **                                           **");
        System.out.println("   ***********************************************");
        System.out.println(Color.RESET);
    }

    public static void printDiceNumbersHorizontally(ArrayList<Integer> diceNumbers){
        if (diceNumbers.size() != 0){
            System.out.println();
            System.out.print(" Dice rolling result:");
            for (Integer ele:diceNumbers) {
                System.out.print(" " + ele + " ");
            }
            System.out.println();
        }
    }

    /**
     * This method will announce and print the winner player.
     * @param player  winner player
     */
    public static void showTheWinner(Player player){
        Text.setTextAndBackgroundColor(Color.BLACK_BOLD,Color.BLUE_BACKGROUND);
        System.out.println("********************************************************************");
        System.out.print("                      WINNER:  **");
        if (player.returnPlayerType().equals("Ax")) System.out.println("Axis**                   ");
        else System.out.println("Allied**                   ");
        System.out.println("********************************************************************");
        System.out.println(Color.RESET);
    }

}
