package gamePlay;

/*
Name:Yair Shlomo
ID: 308536150
Name:Gal Eini
ID: 305216962
*/
public class Board {
    private int rowCount;
    private int colCount;
    private char gameBoard[][];

    public Board(int rowCountB, int colCountB) {
        rowCount = rowCountB;
        colCount = colCountB;
        gameBoard = new char[rowCount][colCount];
        for (int i = 0; i < colCount; ++i) {
            gameBoard[i] = new char[colCount];
        }
        for (int i = 0; i < rowCount; i++) {
            for (int j = 0; j < colCount; j++) {
                gameBoard[i][j]=' ';
            }
        }
        int mdlle = (rowCount / 2) - 1;
        gameBoard[mdlle][mdlle] = 'O';
        gameBoard[mdlle][mdlle + 1] = 'X';
        gameBoard[mdlle + 1][mdlle] = 'X';
        gameBoard[mdlle + 1][mdlle + 1] = 'O';
        System.out.println("helo");
    }

    /**
     * setSign changing the sign of cell in given it.
     * @param x - number of row.
     * @param y- number col.
     */
    public void flip(int x, int y) {
        if (gameBoard[x][y] == 'X') {
            gameBoard[x][y] = 'O';
            return;
        }
        if (gameBoard[x][y] == 'O') {
            gameBoard[x][y] = 'X';
        }
    }
    /**
     * getSizeX retuns num of row in board.
     * @return int - number of rows in board
     */
    public void setSign(int x, int y, char sign) {
        gameBoard[x][y] = sign;

    }
    /**
     * getSizeX retuns num of row in board.
     * @return int - number of rows in board
     */
    public int getSizeX() {
        return rowCount;
    }
    /**
     * getSizeY retuns num of col in board.
     * @return int - number of columns in board
     */
    public int getSizeY() {
        return colCount;
    }
    /**
     * getBoard returns the gameboard.
     * @return char** - the gameboard
     */
    public char[][] getGameBoard() {
        return gameBoard;
    }
}
