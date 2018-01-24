package gamePlay;/*
Name:Yair Shlomo
ID: 308536150
Name:Gal Eini
ID: 305216962
*/
import javafx.scene.paint.Color;
import java.util.List;


public interface Player {
    char getSign();
    /**
     * oppositeSign returns the opposite sign in given sign.
     * @param sign - sign to turn from
     * @return char - the turned sign.
     */
    char oppositeSign(char sign);
    /**
     * checkNextTurn checks if there is any available play to gamePlay.Player and pass turn if not.
     * @param logic -the logic of game object.
     * @return bool - if there is available play-true. if not-false.
     */
    boolean checkNextTurn(GameLogic logic);
    /**
     * yourPlay print list of available points ,get a point from user and returns it.
     * @param pointList - List of all available points.
     * @return gamePlay.Point* - the point user choose to play
     */
    Point yourPlay(List<Point> pointList);
    /**
     * setColor set color of player
     * @param color -new color of player
     */
    void setColor(Color color);
    /**
     * getColor returns the color of player
     * @return color - color of player
     */
    javafx.scene.paint.Color getColor();


}

