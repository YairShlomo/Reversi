import java.util.List;

/*
Name:Yair Shlomo
ID: 308536150
Name:Gal Eini
ID: 305216962
*/
public class Game {
    private Player pl1;
    private Player pl2;
    private Board board;
    private GameLogic logic;
    private boolean blackTurn;
    private int countMoveTurn;

    public Game(Player pl1s,Player pl2s,Board boards ,GameLogic logicG) {
        pl1=pl1s;
        pl2=pl2s;
        board=boards;
        logic=logicG;
        blackTurn=true;
        countMoveTurn=0;
    }

    public void play() {
        while ((score(pl1.getSign()) + score(pl2.getSign()) < board.getSizeY() * board.getSizeX())&(countMoveTurn<2)) {
            if (blackTurn) {
                countMoveTurn+=play1Turn(pl1);
            } else {
                countMoveTurn+=play1Turn(pl2);
            }
        }
        printWinner();
    }
    public int play1Turn(Player pl) {
        if   (!pl.checkNextTurn(logic)) {
            oppositeTurn();
            return 1;
        }
        countMoveTurn=0;
        board.printBoard();
        List<Point> optionalMoves= logic.optionalTurns(pl.getSign());
        Point userPlay=pl.yourPlay(optionalMoves);
        if(userPlay==null) {
            return 0;
        }
        if(!logic.checkValidPoint(userPlay,pl.getSign())) {
            return 0;
        }
        board.setSign(userPlay.getRowNum()-1,userPlay.getColNum()-1,pl.getSign());
        logic.checkFlipPieces(userPlay.getRowNum()-1,userPlay.getColNum()-1,pl.oppositeSign(pl.getSign()),true);
        oppositeTurn();
        return 0;
    }

    public void oppositeTurn() {
        if (blackTurn) {
            blackTurn = false;
            return;
        }
        blackTurn = true;
    }

    public int score(char sign) {
        int counter = 0;
        for (int i = 0; i < board.getSizeX(); ++i) {
            for (int j = 0; j < board.getSizeY(); ++j) {
                if (board.getBoard()[i][j] == sign) {
                    counter++;
                }
            }
        }
        return counter;
    }

    public void printWinner() {
        if (score(pl1.getSign())>score(pl2.getSign())) {
            System.out.println("Player '"+pl1.getSign()+"' wins!"+"\n");
            return;
        }
        if (score(pl2.getSign())>score(pl1.getSign())) {
            System.out.println("Player '"+pl2.getSign() + "' wins!"+"\n");
            return;
        }
        System.out.println("Great Game ,it's a tie!");

    }







}
