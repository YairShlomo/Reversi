package gamePlay;
/*
Name:Yair Shlomo
ID: 308536150
Name:Gal Eini
ID: 305216962
*/
import java.util.List;

public interface GameLogic {
    /**
     * oppositeSign returns the opposite sign.
     * @param sign - a sign to turn from.
     * @return char - the turned sign.
     */
    char oppositeSign(char sign);
    /**
     * checkSign checks if cell contains the given sign.
     * @param x - number of row.
     * @param y- number col.
     * @param sign- gamePlay.Player's sign.
     * @return bool - cell contains the given sign or not.
     */
    boolean checkSign(int x, int y, char sign);
    /**
     * isFull checks if cell is full.
     * @param x - number of row.
     * @param y- number col.
     * @return bool - cell is full or not.
     */
    boolean isFull(int x, int y);
    /**
     * getPoint returns the place of cell.
     * @param x - number of row.
     * @param y- number col.
     * @return gamePlay.Point - the place of cell.
     */
    Point getPoint(int x, int y);
    /**
     * checkValidPoint checks if user point is valid.
     * @param userPoint - point of user.
     * @param sign - a sign.
     * @return bool -  user point is valid or not.
     */
    boolean checkValidPoint(Point userPoint, char sign);

    /**
     * optionalTurns returns vector of optionals moves..
     *
     * @param sign- PLayer's sign.
     * @return vector<gamePlay.Point>  - vector contains optionals moves.
     */
    List<Point> optionalTurns(char sign);
    /**
     * isFlipCheck checks for all flipping options directions of cell.
     * @param x - number of row.
     * @param y- number col.
     * @param sign- player sign
     * @param bool- to flip or not.
     * @return bool - returns if cell suppose to flip.
     */
    boolean checkFlipPieces(int x, int y, char sign, boolean bool);


}
