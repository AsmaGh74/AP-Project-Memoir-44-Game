import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class Main {
    /**
     * Return a random number between one and two.
     * @return  a random number between one and two
     */
    public static int getOneOrTwo(){
        Random random = new Random();
        return random.nextInt(2) + 1;
    }

    public static void main(String[] args) throws java.lang.InterruptedException{
        Scanner scanner = new Scanner(System.in);
        Text.setTextAndBackgroundColor(Color.BLACK_BOLD,Color.BLUE_BACKGROUND);
        System.out.println(" Welcome to Memoir44");
        TimeUnit.SECONDS.sleep(1);
        Text.setTextAndBackgroundColor(Color.BLACK_BOLD,Color.WHITE_BACKGROUND);
        System.out.println(" Axis player: please enter your name: ");
        String axisPlayerName = scanner.nextLine();
        System.out.println(" Allied player: please enter your name: ");
        String alliedPlayerName = scanner.nextLine();
        GameMap gameMap = new GameMap(axisPlayerName, alliedPlayerName);
        Text.setTextAndBackgroundColor(Color.RED_BOLD, Color.BLACK_BACKGROUND);
        System.out.println();
        System.out.println(" You can enter exit at any stage to exit the game (just write exit).");
        System.out.println();
        Text.setTextAndBackgroundColor(Color.BLACK_BOLD,Color.WHITE_BACKGROUND);
        TimeUnit.SECONDS.sleep(3);
        Text.showScores(0,0);
        TimeUnit.SECONDS.sleep(1);
        gameMap.drawMap();
        gameMap.playTheGame(getOneOrTwo());   // start with a random player
//        gameMap.playTheGame(2);   // start with Allied player
    }

}
