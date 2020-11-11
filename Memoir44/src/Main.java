import java.io.Console;
import java.util.HashMap;

public class Main {
    public static void main(String[] args){
        GameMap gameMap = new GameMap();
//        gameMap.drawMap();
//        gameMap.test();

        Axis axis = Axis.getInstance();
        axis.addCardToList(new Card(3, true, 3));
        axis.addCardToList(new Card(2, false, 4));

        axis.showCard();
    }

}
