/*
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
        gameBoard = board1.getBoard();
    }

    public char oppositeSign(char sign) {
        if (sign == 'X') {
            return 'O';
        }
        if (sign == 'O') {
            return 'X';
        }
        return '0';

    }

    public boolean checkSign(int x, int y, char sign) {
        if ((x < rowCount) & (x >= 0) & (y < rowCount) & (y >= 0)) {
            if (gameBoard[x][y] == sign) {
                return true;
            }
        }
        return false;

    }

    public boolean isFull(int x, int y) {
        if ((gameBoard[x][y] == 'X') | (gameBoard[x][y] == 'O')) {
            return true;
        }
        return false;
    }

    public Point getPoint(int x, int y) {
        return new Point(x + 1, y + 1);
    }

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

    public boolean canPutInPiece(int x, int y, char sign) {
        if (isFull(x, y)) {
            return false;
        }
        if (checkFlipPieces(x, y, oppositeSign(sign), false)) {
            return true;
        }
        return false;
    }

    public boolean flipCheck(int x, int ix, int y, int iy, char sign, boolean bool) {
        if (checkSign(x + ix, y + iy, sign)) {
            if (isFlipCheck(x, ix, y, iy, sign, bool)) {
                return true;
            }
        }

        return false;
    }

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

    public boolean checkFlipPieces(int x, int y, char sign, boolean bool) {
        if (flipCheck(x, -1, y, -1, sign, bool) | (flipCheck(x, -1, y, 0, sign, bool)) |
                (flipCheck(x, -1, y, +1, sign, bool)) | (flipCheck(x, 0, y, -1, sign, bool)) |
                (flipCheck(x, 0, y, +1, sign, bool)) | (flipCheck(x, +1, y, -1, sign, bool)) |
                (flipCheck(x, +1, y, 0, sign, bool)) | (flipCheck(x, +1, y, +1, sign, bool))) {
            return true;
        }
        return false;
    }
}