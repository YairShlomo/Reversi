package gamePlay;/*
Name:Yair Shlomo
ID: 308536150
Name:Gal Eini
ID: 305216962
*/

import java.util.ArrayList;
import java.util.List;

public class GameLogic1  implements GameLogic {
    private int rowCount;
    private int colCount;
    private Board board;
    private char[][] gameBoard;

    public GameLogic1(Board board1) {
        rowCount = board1.getSizeX();
        colCount = board1.getSizeY();
        board = board1;
        gameBoard = board1.getGameBoard();
    }
    /**
     * oppositeSign returns the opposite sign.
     * @param sign - a sign to turn from.
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
     * checkSign checks if cell contains the given sign.
     * @param x - number of row.
     * @param y- number col.
     * @param sign- gamePlay.Player's sign.
     * @return bool - cell contains the given sign or not.
     */
    public boolean checkSign(int x, int y, char sign) {
        if ((x < rowCount) & (x >= 0) & (y < rowCount) & (y >= 0)) {
            if (gameBoard[x][y] == sign) {
                return true;
            }
        }
        return false;
    }
    /**
     * isFull checks if cell is full.
     * @param x - number of row.
     * @param y- number col.
     * @return bool - cell is full or not.
     */
    public boolean isFull(int x, int y) {
        if ((gameBoard[x][y] == 'X') | (gameBoard[x][y] == 'O')) {
            return true;
        }
        return false;
    }
    /**
     * getPoint returns the place of cell.
     * @param x - number of row.
     * @param y- number col.
     * @return gamePlay.Point - the place of cell.
     */
    public Point getPoint(int x, int y) {
        return new Point(x + 1, y + 1);
    }
    /**
     * checkValidPoint checks if user point is valid.
     * @param userPoint - point of user.
     * @param sign - a sign.
     * @return bool -  user point is valid or not.
     */
    public boolean checkValidPoint(Point userPoint, char sign) {
        if ((userPoint.getRowNum() > rowCount) | (userPoint.getRowNum() < 0)
                | (userPoint.getColNum() > colCount) | (userPoint.getColNum() < 0)) {
            System.out.println("not allowed move,please try again" + "\n");
            return false;
        }
        if (canPutInPiece(userPoint.getRowNum() - 1, userPoint.getColNum() - 1, sign)) {
            return true;
        }
        System.out.println("not allowed move,please try again" + "\n");
        return false;
    }

    /**
     * optionalTurns returns vector of optionals moves..
     * @param sign- gamePlay.Player's sign.
     * @return vector<gamePlay.Point>  - vector contains optionals moves.
     */
    public List<Point> optionalTurns(char sign) {
        List<Point> pointList=new ArrayList<>();
        for (int i = 0; i < rowCount; ++i) {
            for (int j = 0; j < colCount; ++j) {
                if (canPutInPiece(i, j, sign)) {
                    pointList.add(getPoint(i, j));
                }
            }
        }
        return pointList;
    }
    /**
     * CanPutInPiece returns if cell is available to play or not.
     * @param x - number of row.
     * @param y- number col.
     * @param sign- PLayer's sign.
     * @return bool - if cell is available to play or not.
     */
    public boolean canPutInPiece(int x, int y, char sign) {
        if (isFull(x, y)) {
            return false;
        }
        if (checkFlipPieces(x, y, oppositeSign(sign), false)) {
            return true;
        }
        return false;
    }
    /**
     * flipCheck returns if cell suppose to flip..
     * @param x - number of row.
     * @param y- number col.
     * @param ix- diff number row.
     * @param iy- diff number col.
     * @param sign- player sign
     * @param bool- to flip or not.
     * @return bool - returns if cell suppose to flip.
     */
    public boolean flipCheck(int x, int ix, int y, int iy, char sign, boolean bool) {
        if (checkSign(x + ix, y + iy, sign)) {
            if (isFlipCheck(x, ix, y, iy, sign, bool)) {
                return true;
            }
        }

        return false;
    }
    /**
     * recursive func. returns if all cells in order suppose to flip.
     * @param x - number of row.
     * @param y- number col.
     * @param ix- diff number row.
     * @param iy- diff number col.
     * @param sign- player sign
     * @param bool- to flip or not.
     * @return bool - returns if cell suppose to flip.
     */
    public boolean isFlipCheck(int x, int ix, int y, int iy, char sign, boolean bool) {
        int currX = x + ix, currY = y + iy;
        if (checkSign(currX, currY, sign)) {
            if (!isFlipCheck(currX, ix, currY, iy, sign, bool)) {
                return false;
            }
            if (bool) {
                board.flip(x + ix, y + iy);
            }
            return true;
        }
        if (checkSign(x + ix, y + iy, oppositeSign(sign))) {
            return true;
        }
        return false;
    }
    /**
     * isFlipCheck checks for all flipping options directions of cell.
     * @param x - number of row.
     * @param y- number col.
     * @param sign- player sign
     * @param bool- to flip or not.
     * @return bool - returns if cell suppose to flip.
     */
    public boolean checkFlipPieces(int x, int y, char sign, boolean bool) {
        if (flipCheck(x, -1, y, -1, sign, bool) | (flipCheck(x, -1, y, 0, sign, bool)) |
                (flipCheck(x, -1, y, +1, sign, bool)) | (flipCheck(x, 0, y, -1, sign, bool)) |
                (flipCheck(x, 0, y, +1, sign, bool)) | (flipCheck(x, +1, y, -1, sign, bool)) |
                (flipCheck(x, +1, y, 0, sign, bool)) | (flipCheck(x, +1, y, +1, sign, bool))) {
            return true;
        }
        return false;
    }
    /**
     * checkValidMove checks if user point is in optional moves and returns result
     * @param userPlay - player move
     * @param sign- player sign
     * @return bool - valid or not
     */
    public boolean checkValidMove(Point userPlay, char sign) {
        List<Point> avaiableMoves = optionalTurns(sign);
        for (Point p : avaiableMoves) {
            if (p.isEqual(userPlay)) {
                return true;
            }
        }
        return false;
    }
}