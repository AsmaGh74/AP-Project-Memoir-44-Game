/**
 * Color class is used for giving different colors to strings.
 */
enum Color {
    //Color reset
    RESET("\033[0m"),

    // Bold
    BLACK_BOLD("\033[1;30m"),   // BLACK
    RED_BOLD("\033[1;31m"),     // RED
    GREEN_BOLD("\033[1;32m"),   // GREEN
    YELLOW_BOLD("\033[1;33m"),  // YELLOW
    BLUE_BOLD("\033[1;34m"),    // BLUE
    MAGENTA_BOLD("\033[1;35m"), // MAGENTA
    CYAN_BOLD("\033[1;36m"),    // CYAN
    WHITE_BOLD("\033[1;37m"),   // WHITE

    // Background
    BLACK_BACKGROUND("\033[40m"),   // BLACK
    RED_BACKGROUND("\033[41m"),     // RED
    GREEN_BACKGROUND("\033[42m"),   // GREEN
    YELLOW_BACKGROUND("\033[43m"),  // YELLOW
    BLUE_BACKGROUND("\033[44m"),    // BLUE
    MAGENTA_BACKGROUND("\033[45m"), // MAGENTA
    CYAN_BACKGROUND("\033[46m"),    // CYAN
    WHITE_BACKGROUND("\033[47m");   // WHITE

    private final String code;
    Color(String code) {
        this.code = code;
    }
    @Override
    public String toString() {
        return code;
    }
}

/**
 * HexagonalType class defines all kinds of hexagons that can be on the game map.
 */
enum HexagonalType{
    GROUND, HILL, FOREST, RIVER, BRIDGE, CITY, SHELTER;
}

/**
 * MovementDirections class defines all available movements for an arbitrary hexagon.
 */
enum MovementDirections{
    L, R, UL, UR, DL, DR;
    public static boolean check(String move){
        if (move.equals("L") || move.equals("R") || move.equals("UL") ||
                move.equals("DL") || move.equals("UR") || move.equals("DR")) return true;
        return false;
    }
}

/**
 * EnumClasses class is useless.
 * This class is just a blank page for enum classes.
 * @author Asma
 * @version 1.0
 */
public class EnumClasses {
}
