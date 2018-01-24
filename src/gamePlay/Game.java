package gamePlay;/*
Name:Yair Shlomo
ID: 308536150
Name:Gal Eini
ID: 305216962
*/

import java.util.List;

public class Game  {
    private Player pl1;
    private Player pl2;
    private Board board;
    private GameLogic logic;
    private int countMoveTurn;
    private GameSetting gameSetting;
    private boolean blackTurn;



    public Game(Player pl1s, Player pl2s, Board boards , GameLogic logicG, GameSetting gameSetting1) {
        pl1 = pl1s;
        pl2 = pl2s;
        board = boards;
        logic = logicG;
        gameSetting = gameSetting1;
        blackTurn = gameSetting.getBlackTurn();
    }
    /*
     * play in charge of the loop game and managing turns.
     */
    public void play() {
        while ((score(pl1.getSign()) + score(pl2.getSign()) < board.getSizeY() * board.getSizeX())&(countMoveTurn<2)) {
            if (gameSetting.getBlackTurn()) {
                countMoveTurn+=play1Turn(pl1);
            } else {
                countMoveTurn+=play1Turn(pl2);
            }
        }
        printWinner();
    }
    /**
     * play1Turn is sets of action in any turn in game.
     * @param pl - current gamePlay.Player playing.
     * @return int - if turns passed=1 ,otherwise 0.
     */
    public int play1Turn(Player pl) {
        if   (!pl.checkNextTurn(logic)) {
            oppositeTurn();
            return 1;
        }
        countMoveTurn=0;
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
    /**
     * oppositeTurn turn the turn to other gamePlay.Player
     */
    public void oppositeTurn() {
        if (blackTurn) {
            blackTurn=false;
            return;
        }
        blackTurn=true;
    }
    /**
     * score returns number of cells gamePlay.Player owns.
     * @param sign - a player.
     * @return int - number of cells gamePlay.Player owns
     */
    public int score(char sign) {
        int counter = 0;
        for (int i = 0; i < board.getSizeX(); ++i) {
            for (int j = 0; j < board.getSizeY(); ++j) {
                if (board.getGameBoard()[i][j] == sign) {
                    counter++;
                }
            }
        }
        return counter;
    }
    /**
     * printWinner print the Winner when game end.
     */
    public void printWinner() {
        if (score(pl1.getSign())>score(pl2.getSign())) {
            System.out.println("gamePlay.Player '"+pl1.getSign()+"' wins!"+"\n");
            return;
        }
        if (score(pl2.getSign())>score(pl1.getSign())) {
            System.out.println("gamePlay.Player '"+pl2.getSign() + "' wins!"+"\n");
            return;
        }
        System.out.println("Great gamePlay.Game ,it's a tie!");

    }

    /**
     * getTurn returns currentTurn in game
     * @return blackTurn - currentTurn in game
     */
    public boolean getTurn(){
        return blackTurn;
    }
    /**
     * getPlayer1 returns player1
     * @return pl1 - player1
     */
    public Player getPlayer1(){
        return pl1;
    }
    /**
     * getPlayer2 returns player2
     * @return pl2 - player2
     */
    public Player getPlayer2(){
        return pl2;
    }
    /**
     * getCurPlayer returns currenPlayer in game
     * @return pl1 - currenPlayer in game
     */
    public Player getCurPlayer(){
        if(blackTurn) {
            return pl1;
        }
        return pl2;
    }
    /**
     * getBoard returns board of game
     * @return board - board of game
     */
    public Board getBoard(){
        return board;
    }
    /**
     * getLogic returns logic of game
     * @return logic - logic of game
     */
    public GameLogic getLogic() {
        return logic;
    }
    /**
     * getCountMoveTurn returns how many turn passes
     * @return countMoveTurn - number of turn passes
     */
    public int getCountMoveTurn(){
        return countMoveTurn;
    }
    /**
     * setCountMoveTurn set new number of turn passes
     * @param count - number of turn passes
     */
    public void setCountMoveTurn(int count){
         countMoveTurn=count;
    }
}
