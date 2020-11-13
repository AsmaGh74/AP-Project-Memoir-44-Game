import java.io.Console;

/**
 * Card class represents valid cards for the game.
 * @author Asma
 * @version 1.0
 */
public class Card {
    // stores the number of units that we can command by card
    private int numberOfUnits;
    // stores whether the groups should be from tha same type of force or not
    // if true then we can command different types of forces
    private boolean differentForceType;
//    // stores the number of cards that we have from this type
//    private int numberOfCards;

    public Card(int numberOfUnits, boolean differentForceType){
        this.numberOfUnits = numberOfUnits;
        this.differentForceType = differentForceType;
    }

    /*************************************** getter methods ************************************/
    public int getNumberOfUnits(){ return  numberOfUnits;}
    public boolean getDifferentForceType(){return differentForceType;}
    /*******************************************************************************************/


    /**
     * Check if two cards are the same.
     * @param card  card to be compared.
     * @return  true if two cards are the same
     */
    public boolean equals(Card card){
        return this.numberOfUnits == card.getNumberOfUnits() && this.differentForceType == card.getDifferentForceType();
    }

}
