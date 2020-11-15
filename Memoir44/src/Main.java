import java.util.Random;
import java.util.Scanner;

public class Main {
    /**
     * Return a random number between one and two.
     * @return  a random number between one and two
     */
    public static int getOneOrTwo(){
        Random random = new Random();
        return random.nextInt(2) + 1;
    }
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        Text.setTextAndBackgroundColor(Color.BLACK_BOLD,Color.WHITE_BACKGROUND);
        System.out.println(" Welcome to Memoir44");
        System.out.println(" Axis player: please enter your name: ");
        String axisPlayerName = scanner.nextLine();
        System.out.println(" Allied player: please enter your name: ");
        String alliedPlayerName = scanner.nextLine();
        GameMap gameMap = new GameMap(axisPlayerName, alliedPlayerName);
        Text.showScores(0,0);
        gameMap.drawMap();
        // start with a random player
        gameMap.playTheGame(getOneOrTwo());
    }

}
