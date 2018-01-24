package gamePlay;/*
Name:Yair Shlomo
ID: 308536150
Name:Gal Eini
ID: 305216962
*/
import javafx.scene.paint.Color;
import java.util.List;
import java.util.Scanner;

public class ConsolePlayer implements Player {
    private char sign;
    private Color color;
    public ConsolePlayer(char signCp){
        sign=signCp;
    }
    public char getSign() {
        return sign;
    }
    /**
     * oppositeSign returns the opposite sign in given sign.
     * @param sign - sign to turn from
     * @return char - the turned sign.
     */
    public char oppositeSign(char sign) {
        if (sign == 'X') {
            return 'O';
        }
        if (sign == 'O') {
            return 'X';
        }
        return '0';
    }
    /**
     * yourPlay print list of available points ,get a point from user and returns it.
     * @param pointList - List of all available points.
     * @return gamePlay.Point* - the point user choose to play
     */
    public Point yourPlay(List<Point> pointList) {
        int userX, userY;
        System.out.println( getSign() + ": It's your move" );
        System.out.println ( "Your possible moves: " );
        for (int i = 0; i < pointList.size(); ++i) {
            pointList.get(i).printPoint();
            if (i != pointList.size() - 1) {
                System.out.println( "," );
            }
        }
        System.out.println( "" +"\n");
        System.out.println( "Please enter your move row ,col:(enter row,col separately)" +"\n");
        Scanner in = new Scanner(System.in);
        userX = in.nextInt();
        userY = in.nextInt();
        return new Point(userX, userY);
    }
    /**
     * checkNextTurn checks if there is any available play to gamePlay.Player and pass turn if not.
     * @param logic -the logic of game object.
     * @return bool - if there is available play-true. if not-false.
     */
    public boolean checkNextTurn(GameLogic logic) {
        if (logic.optionalTurns((getSign())).size()==0) {
            //System.out.println( "No possible moves. play passes back to other player. press any key to countinue" +"\n");

            return false;
        }
        return true;
    }
    /**
     * setColor set color of player
     * @param color -new color of player
     */
    public void setColor(Color color) {
        this.color = color;
    }
    /**
     * getColor returns the color of player
     * @return color - color of player
     */
    public Color getColor() {
        return this.color;
    }
}
