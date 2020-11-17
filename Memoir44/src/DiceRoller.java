import java.util.ArrayList;
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

    /**
     * This methods implements dice rolling rules and finally returns the dice numbers.
     * @param numberOfDiceRolling  initial number for dice rolling
     * @param hexagonalTypeForAttacker  type of the hexagon which the attacker located there
     * @param hexagonalTypeForTarget  type of the hexagon which the target located there
     * @param attacker  type of attacker force
     * @return  dice numbers after dice rolling
     */
    protected ArrayList<Integer> rollTheDice(int numberOfDiceRolling, String hexagonalTypeForAttacker, String hexagonalTypeForTarget,
                                         Force attacker){
        Random random = new Random();
        ArrayList<Integer> diceNumbers = new ArrayList<>();
        int initialNumberOfDiceRolling = numberOfDiceRolling;
        switch (hexagonalTypeForTarget){
            case "H":
                if (attacker.returnForceType().equals("T") || attacker.returnForceType().equals("I")) initialNumberOfDiceRolling--;
                break;
            case "F":
            case "C":
                if (attacker.returnForceType().equals("T")) initialNumberOfDiceRolling-=2;
                if (attacker.returnForceType().equals("I")) initialNumberOfDiceRolling--;
                break;
        }
        if (hexagonalTypeForAttacker.equals("C")){
            if (attacker.returnForceType().equals("T")) initialNumberOfDiceRolling-=2;
        }
        if (initialNumberOfDiceRolling <= 0) System.out.println(" Dice rolling can not be done based on the game rules!");
        for (int i = 0; i < initialNumberOfDiceRolling; i++){
            diceNumbers.add(random.nextInt(6) + 1);
        }
        return diceNumbers;
    }
}
