import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

/**
 * DiceRoller class is responsible for rolling the dice.
 * @author Asma
 * @version 1.0
 */
public class DiceRoller {

    /**
     * Create a new dice roller.
     */
    public DiceRoller(){
    }

    protected ArrayList<String> rollTheDice(int numberOfDiceRolling, HexagonalType hexagonalTypeForAttacker, HexagonalType hexagonalTypeForEnemy,
                                         Force attacker){
        Random random = new Random();
        ArrayList<String> diceNumbers = new ArrayList<>();
        int initialNumberOfDiceRolling = numberOfDiceRolling;
        switch (hexagonalTypeForEnemy){
            case HILL:
                if (attacker.returnForceType().equals("T") || attacker.returnForceType().equals("I")) initialNumberOfDiceRolling--;
                break;
            case FOREST:
            case CITY:
                if (attacker.returnForceType().equals("T")) initialNumberOfDiceRolling-=2;
                if (attacker.returnForceType().equals("I")) initialNumberOfDiceRolling--;
                break;
        }
        if (hexagonalTypeForAttacker.name().equals("CITY")){
            if (attacker.returnForceType().equals("T")) initialNumberOfDiceRolling-=2;
        }
        for (int i = 0; i < initialNumberOfDiceRolling; i++){
            diceNumbers.add(Integer.toString(random.nextInt(6) + 1));
        }
        return diceNumbers;
    }
}
