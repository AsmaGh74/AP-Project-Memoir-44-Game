import java.util.ArrayList;
import java.util.HashMap;

/**
 * Player class represents a player in the game.
 * Player can be Axis or Allied.
 * @author Asma
 * @version 1.0
 */
public abstract class Player {
    // stores the player's name
    private String name;
    // stores the number of cards which the player can have
    private int numberOfCards;
    // stores every player's cards
    private ArrayList<Card> playerCards;
    // stores the forces that player has
    private ArrayList<Force> forces;
    // only instance of game cards
    private static final Cards gameCards = Cards.getInstance();
    // stores player's special location
    // if the player take this location will get a score
    private Location playerSpecialLocation;
    // stores number of scores for the player
    // player will win if has six scores
    private int scores;

    /**
     * Creates a new player with specific number of cards in which the player can have in every round.
     * @param numberOfCards number of cards in which the player can have in every round
     */
    public Player(String name, int numberOfCards){
        this.name = name;
        this.numberOfCards = numberOfCards;
        playerCards = new ArrayList<>();
        // when you create an Axis or Allied player set their forces too.
        forces = new ArrayList<>();
        addPlayerInitialForces();
        setPlayerInitialRandomCards();
        // initialize number of scores to zero
        scores = 0;
    }

    /*************************************** getter methods ************************************/
    protected ArrayList<Card> getCards(){return playerCards;}
    protected ArrayList<Force> getForces(){return forces;}
    /*******************************************************************************************/

    /*************************************** setter methods ************************************/
    public void setNumberOfCards(int numberOfCards){ this.numberOfCards = numberOfCards; }
    protected void setPlayerSpecialLocation(Location location){ playerSpecialLocation = location;}
    /*******************************************************************************************/


    /**
     * Check if the cards list is full or not.
     * @return  true if there is empty place to add a new card.
     */
    protected boolean cardsListSize(){
        if (playerCards.size() < numberOfCards) return true;
        return false;
    }

    /**
     * Add a new card to cards list if there is an empty place.
     * @param card  card to be added to the list.
     */
    protected void addCardToList(Card card){
        if (cardsListSize()) playerCards.add(card);
    }

    /**
     * Check if the entered card exists in the player's card list.
     * @param card  card to be checked
     * @return  true if the card exists in the list
     */
    protected boolean checkCardExistenceInTheList(Card card){
        for (Card ele:playerCards) {
            if (ele.equals(card)) return true;
        }
        return false;
    }

    /**
     * Remove a card from cards list if the card is already in the list.
     * @param card  card to be removed
     */
    protected void removeCardFromList(Card card){
        for (Card ele:playerCards) {
            if (checkCardExistenceInTheList(card)) playerCards.remove(ele);
        }
    }

    /**
     * Player has a set of forces.
     * Add these forces for the player.
     */
    abstract protected void addPlayerInitialForces();

    /**
     * Set every player's initial cards.
     * It's 2 cards for Axis player
     * and 4 cards for Allied player.
     */
    private void setPlayerInitialRandomCards(){
        playerCards = gameCards.getPlayerCards(numberOfCards);
    }

    /**
     * After every round player has been used one of cards so the card should be replaced.
     */
    protected void addNewCardToPlayerCardsAfterEveryRound(){
        playerCards.add(gameCards.getOneCardForPlayer());
    }

    /**
     * Add a score to player's scores.
     */
    protected void addToScores(){this.scores++;}

    /**
     * Subtract a score from player's scores.
     */
    protected void subtractFromScores(){this.scores--;}

    /**
     * Check if any of player's forces are in the special location. If yes add to player's scores and return true.
     * @return true if any of player's forces are in the special location
     */
    protected boolean checkForBeingInSpacialLocation(){
        for (Force ele:forces) {
            if (ele.getLocation().equals(playerSpecialLocation)){
                this.scores++;
                return true;
            }
        }
        return false;
    }

    /**
     * Return the type of player (Axis or Allied) as a string.
     * @return a string for player type
     */
    abstract protected String returnPlayerType();

    /************************************ visualization part **************************************/

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
     * For every entered string fill it sides by space.
     * @param text entered string
     * @return a string with specific length
     */
    private String stringWithSpecificSize(String text){
        int cardSize = 30;
        int add = (cardSize - text.length())/2;
        String space = "";
        for ( int i = 0; i < add; space+= " ", i++);
        if ((cardSize - text.length()) % 2 == 0) return space + text + space;
        return space + " " + text + space;
    }

    /**
     * Create an array list for texts that should printed on the cards.
     * @return an array list of strings to be printed on the cards
     */
    private ArrayList<String> makeTextsOnTheCards(){
        ArrayList<String> texts = new ArrayList<>();
        for (Card ele:playerCards) {
            if (ele.getDifferentForceType()) texts.add(stringWithSpecificSize( "order " + ele.getNumberOfUnits() + " units" ));
            else texts.add(stringWithSpecificSize( "order " + ele.getNumberOfUnits() + " identical units" ));
        }
        return texts;
    }

    /**
     * This method shows the player's cards.
     */
    protected void showCard(){
        for (int j = 0; j < numberOfCards/2; j++){
            for (int i = 0; i < 7; i++){
                setTextAndBackgroundColor(Color.WHITE_BOLD, Color.BLACK_BACKGROUND);
                if ( i == 3){
                    System.out.print(makeTextsOnTheCards().get(2*j));
                    System.out.print(Color.RESET);
                    System.out.print("                              ");
                    setTextAndBackgroundColor(Color.WHITE_BOLD, Color.BLACK_BACKGROUND);
                    System.out.print(makeTextsOnTheCards().get(2*j+1));
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
            System.out.println();
        }
    }

    /*******************************************************************************************/

}
