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

    public void printBoard() {
        System.out.println(" |   ");
        for (int i = 1; i <= colCount; i++) {
            System.out.println(i + "   |   ");
        }
        System.out.println("");
        for (int i = 1; i <= colCount; i++) {
            System.out.println(" - - - -");
        }
        System.out.println("" + "\n");
        for (int i = 1; i <= rowCount; i++) {
            printRow(i);
        }
    }

    public void printRow(int rowNum) {
        System.out.println(rowNum + "|  ");
        for (int x = 0; x < colCount; x++) {
            System.out.println(getStringVal(rowNum - 1, x) + "   |  ");
        }
        System.out.println("" + "\n");
        for (int x = 0; x < colCount; x++) {

            System.out.println(" - - - -");
        }
        System.out.println("" + "\n");
    }

    public String getStringVal(int rowNum, int colNum) {
        if (gameBoard[rowNum][colNum] == 'X') {
            return "X ";
        }
        if (gameBoard[rowNum][colNum] == 'O') {
            return "O ";
        }
        return "  ";
    }

    public void flip(int x, int y) {
        if (gameBoard[x][y] == 'X') {
            gameBoard[x][y] = 'O';
            return;
        }
        if (gameBoard[x][y] == 'O') {
            gameBoard[x][y] = 'X';
        }
    }

    public void setSign(int x, int y, char sign) {
        gameBoard[x][y] = sign;

    }

    public int getSizeX() {
        return rowCount;
    }

    public int getSizeY() {
        return colCount;
    }

    public char[][] getGameBoard() {
        return gameBoard;
    }
}
