import java.util.ArrayList;

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
    private Axis(){
        super(2);
    }

    public static Axis getInstance(){
        if (AXIS == null) AXIS = new Axis();
        return AXIS;
    }

    /**
     * This method shows the player's cards.
     */
    public void showCard(){
        for (int i = 0; i < 7; i++){
            setTextAndBackgroundColor(Color.WHITE_BOLD, Color.BLACK_BACKGROUND);
            if ( i == 3){
                System.out.print(makeTextsOnTheCards().get(0));
                System.out.print(Color.RESET);
                System.out.print("                              ");
                setTextAndBackgroundColor(Color.WHITE_BOLD, Color.BLACK_BACKGROUND);
                System.out.print(makeTextsOnTheCards().get(1));
            }
            else {
                System.out.print("                              ");
                System.out.print(Color.RESET);
                System.out.print("                              ");
                setTextAndBackgroundColor(Color.WHITE_BOLD, Color.BLACK_BACKGROUND);
                System.out.print("                              ");
            }
            System.out.println(Color.RESET);
        }
    }
}
