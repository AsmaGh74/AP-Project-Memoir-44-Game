import java.util.ArrayList;
import java.util.HashMap;

/**
 * Player class represents a player in the game.
 * Player can be Axis or Allied.
 * @author Asma
 * @version 1.0
 */
public abstract class Player {
    // stores the number of cards which the player can have
    private int numberOfCards;
    // stores every player's cards
    private ArrayList<Card> cards;

    /**
     * Creates a new player with specific number of cards in which the player can have in every round.
     * @param numberOfCards number of cards in which the player can have in every round
     */
    public Player(int numberOfCards){
        this.numberOfCards = numberOfCards;
        cards = new ArrayList<>();
    }

    /*************************************** getter methods ************************************/
    protected ArrayList<Card> getCards(){return cards;}
    /*******************************************************************************************/

    /*************************************** setter methods ************************************/
    public void setNumberOfCards(int numberOfCards){ this.numberOfCards = numberOfCards; }
    /*******************************************************************************************/


    /**
     * Check if the cards list is full or not.
     * @return  true if there is empty place to add a new card.
     */
    protected boolean cardsListSize(){
        if (cards.size() < numberOfCards) return true;
        return false;
    }

    /**
     * Add a new card to cards list if there is an empty place.
     * @param card  card to be added to the list.
     */
    protected void addCardToList(Card card){
        if (cardsListSize()) cards.add(card);
    }

    /**
     * Check if the entered card exists in the player's card list.
     * @param card  card to be checked
     * @return  true if the card exists in the list
     */
    protected boolean checkCardExistenceInTheList(Card card){
        for (Card ele:cards) {
            if (ele.equals(card)) return true;
        }
        return false;
    }

    /**
     * Remove a card from cards list if the card is already in the list.
     * @param card  card to be removed
     */
    protected void removeCardFromList(Card card){
        for (Card ele:cards) {
            if (checkCardExistenceInTheList(card)) cards.remove(ele);
        }
    }

    /************************************ visualization part **************************************/

    /**
     * Set color for text and background.
     * @param textColor  color of the text
     * @param backgroundColor  color of the background
     */
    protected void setTextAndBackgroundColor(Color textColor, Color backgroundColor){
        System.out.print(textColor);
        System.out.print(backgroundColor);
    }

    /**
     * This method shows the player's cards.
     */
    abstract protected void showCard();

    /**
     * For every entered string fill it sides by space.
     * @param text entered string
     * @return a string with specific length
     */
    protected String stringWithSpecificSize(String text){
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
    protected ArrayList<String> makeTextsOnTheCards(){
        ArrayList<String> texts = new ArrayList<>();
        for (Card ele:cards) {
            if (ele.getDifferentForceType()) texts.add(stringWithSpecificSize( "order " + ele.getNumberOfUnits() + " units" ));
            else texts.add(stringWithSpecificSize( "order " + ele.getNumberOfUnits() + " identical units" ));
        }
        return texts;
    }

    /*******************************************************************************************/

}
