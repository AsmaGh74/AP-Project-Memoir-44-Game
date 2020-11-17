import java.util.ArrayList;
import java.util.Collections;

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
    protected ArrayList<Force> getForces(){return forces;}
    protected int getNumberOfCards(){return numberOfCards;}
    protected int getScores(){return scores;}
    /*******************************************************************************************/

    /*************************************** setter methods ************************************/
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
     * Remove a card from cards list by it's index.
     * @param index  index of the card
     */
    protected void removeCardFromListByIndex(int index){
       playerCards.remove(index);
       setPlayerCardsInEveryRound();
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
    private
    void addNewCardToPlayerCardsAfterEveryRound(){
        playerCards.add(gameCards.getOneCardForPlayer());
    }

    /**
     * Check if the player has used a card, add a new card to player's cards.
     */
    private void setPlayerCardsInEveryRound(){
        if (cardsListSize()) addNewCardToPlayerCardsAfterEveryRound();
    }

    /**
     * Add a score to player's scores.
     */
    protected void addToScores(){
        this.scores++;}

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

    /**
     * Return the number of units for the card which is located in the entered index.
     * @param index  index of player cards list
     * @return  number of units for the card which is located in the entered index
     */
    protected int returnCardNumberOfUnits(int index){
        return playerCards.get(index).getNumberOfUnits();
    }

    /**
     * Return the different force type for the card which is located in the entered index.
     * @param index  index of player cards list
     * @return  different force type for the card which is located in the entered index
     */
    protected boolean returnCardDifferentForceType(int index){
        return playerCards.get(index).getDifferentForceType();
    }

    /**
     * Check if the numbers of forces that has been selected by the player are valid or not.
     * Numbers must be in the range of forces list.
     * If the selected card by player is for ordering identical units and the player has been selected non-identical units return false.
     * @param differentUnits  differentUnits for the selected card by the player
     * @param numbersOfSelectedForces  numbers of forces that have been selected by the user
     * @return  true if numbers of forces are valid
     */
    protected boolean checkForNumbersOfSelectedForces(boolean differentUnits, ArrayList<Integer> numbersOfSelectedForces){
        for (Integer ele:numbersOfSelectedForces) {
            if (ele <= 0 || ele > forces.size()) {
                System.out.println(" Invalid force location!");
                return false;
            }
            if (Collections.frequency(numbersOfSelectedForces, ele) > 1){
                System.out.println(" Duplicated locations!");
                return false;
            }
            if (!differentUnits){
                String forceType = forces.get(numbersOfSelectedForces.get(0)-1).returnForceType();
                if (!forces.get(ele-1).returnForceType().equals(forceType)){
                    System.out.println(" Selected forces must be from the same type!");
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * Return the player's force number based of location of the force.
     * @param row  row of the location of the force
     * @param column  column of the location of the force
     * @return  index + 1 for matched force with the location
     */
    protected int returnPlayerForceIndexBasedOnLocation(int row, int column){
        Location location = new Location(row,column);
        for (int i = 0; i < forces.size(); i++){
            if (forces.get(i).getLocation().equals(location)) return i+1;
        }
        System.out.println(" Invalid target location!");
        return -1;
    }


    /************************************ visualization part **************************************/

    /**
     * Create an array list for texts that should printed on the cards.
     * @return an array list of strings to be printed on the cards
     */
    private ArrayList<String> makeTextsOnTheCards(){
        ArrayList<String> texts = new ArrayList<>();
        int i = 1;
        for (Card ele:playerCards) {
            if (ele.getDifferentForceType()) texts.add(Text.stringWithSpecificSize(30, i +"- order " + ele.getNumberOfUnits() + " unit(s)" ));
            else texts.add(Text.stringWithSpecificSize(30, i +"- order " + ele.getNumberOfUnits() + " identical unit(s)" ));
            i++;
        }
        return texts;
    }

    /**
     * This method shows the player's cards.
     */
    protected void showCards(){
//        setPlayerCardsInEveryRound();
        for (int j = 0; j < numberOfCards/2; j++){
            for (int i = 0; i < 7; i++){
                Text.setTextAndBackgroundColor(Color.WHITE_BOLD, Color.BLACK_BACKGROUND);
                if ( i == 3){
                    System.out.print(makeTextsOnTheCards().get(2*j));
                    System.out.print(Color.RESET);
                    System.out.print("                              ");
                    Text.setTextAndBackgroundColor(Color.WHITE_BOLD, Color.BLACK_BACKGROUND);
                    System.out.print(makeTextsOnTheCards().get(2*j+1));
                }
                else {
                    System.out.print("                              ");
                    System.out.print(Color.RESET);
                    System.out.print("                              ");
                    Text.setTextAndBackgroundColor(Color.WHITE_BOLD, Color.BLACK_BACKGROUND);
                    System.out.print("                              ");
                }
                System.out.println(Color.RESET);
            }
            System.out.println();
        }
    }

    /**
     * List player's forces.
     * List type, group size, and location of the forces.
     * @return number of forces (in group) that the player has.
     */
    protected int listLocationOfPlayerForces(){
        Text.setTextAndBackgroundColor(Color.BLACK_BOLD, Color.WHITE_BACKGROUND);
        int i = 1;
        for (Force ele:forces) {
            if (i < 10) System.out.print(" 0" + i + "- ");
            else System.out.print(" " + i + "- ");
            ele.printInfo();
            System.out.print(" ");
            ele.getLocation().printInfo();
            System.out.println();
            i ++;
        }
        return forces.size();
    }

    /**
     * Print player type and name.
     */
    protected void printName(){
        System.out.print(name);
    }

    /*******************************************************************************************/

}
