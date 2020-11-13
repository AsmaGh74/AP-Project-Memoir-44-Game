import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

/**
 * Cards class stores the whole cards that is in the game and acts on them.
 * @author Asma
 * @version 1.0
 */
public class Cards {
    // stores an instance from this class
    private static Cards cards = null;
    // stores number of every kind of card in the game
    private HashMap<Card, Integer> numberOfEveryCard;
    // stores all the cards that are in the game
    private ArrayList<Card> gameCards;
    // stores the cards which have been used by players
    private ArrayList<Card> usedCards;

    /**
     * Create a new set of cards.
     */
    private Cards(){
        numberOfEveryCard = new HashMap<>();
        gameCards = new ArrayList<>();
        usedCards = new ArrayList<>();
        // set all game cards
        setNumberOfEveryCard();
        setGameCards();
        // now reorder them
        putCardsInAnRandomOrder(gameCards);
    }

    /**
     * We can only have one instance from this class.
     * @return  instance
     */
    public static Cards getInstance(){
        if (cards == null) cards = new Cards();
        return cards;
    }

    /**
     * Set number of every kind of card based on game description.
     */
    private void setNumberOfEveryCard(){
        Card card1 = new Card(1,true);
        numberOfEveryCard.put(card1,6);
        Card card2 = new Card(2,true);
        numberOfEveryCard.put(card2,13);
        Card card3 = new Card(3,true);
        numberOfEveryCard.put(card3,10);
        Card card4 = new Card(4,true);
        numberOfEveryCard.put(card4,6);
        Card card5 = new Card(3,false);
        numberOfEveryCard.put(card5,5);
    }

    /**
     * Set all the cards that are in the game.
     */
    private void setGameCards(){
        for (Card ele:numberOfEveryCard.keySet()) {
            for (int i = 0; i < numberOfEveryCard.get(ele); i++){
                gameCards.add(ele);
            }
        }
    }

    /**
     * Reorder cards in a random way.
     * @param cards  cards to be reordered
     * @return  reordered cards
     */
    private ArrayList<Card> putCardsInAnRandomOrder(ArrayList<Card> cards){
        int bound = cards.size();
        Random random = new Random();
        for (int i = 0; i < bound; i++){
            int randomOrder = random.nextInt(bound);
            Card card = cards.get(i);
            cards.set(i, cards.get(randomOrder));
            cards.set(randomOrder, card);
        }
        return cards;
    }

    /**
     * Every player should have number of cards to play the game.
     * This method returns cards that every player needs.
     * @param numberOfCards  number of player's cards
     * @return  player's cards
     */
    public ArrayList<Card> getPlayerCards(int numberOfCards){
        ArrayList<Card> playerCards = new ArrayList<>();
        for (int i = 0; i < numberOfCards; i++){
            playerCards.add(gameCards.get(i));
            usedCards.add(gameCards.get(i));
            gameCards.remove(i);
        }
        return playerCards;
    }

    /**
     * After any round of game, one of player's cards has been used and should be replaced.
     * @return  one new card from game cards
     */
    public Card getOneCardForPlayer(){
        if (gameCards.size() != 0){
            Card card = gameCards.get(0);
            usedCards.add(card);
            gameCards.remove(card);
            return card;
        }
        else {
            for (Card ele:usedCards) {
                gameCards.add(ele);
            }
            putCardsInAnRandomOrder(gameCards);
            usedCards.removeAll(usedCards);
            return gameCards.get(0);
        }
    }

}
