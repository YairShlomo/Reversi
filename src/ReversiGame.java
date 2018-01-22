import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

import java.awt.*;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Field;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class ReversiGame implements Initializable {

    private Board board;
    private char gameBoard[][];
    private GameSetting gameSetting;
    private ReversiBoard reversiBoard;
    private List<Point> avaiableMoves;
    private Game game;
    private boolean inGame;
    @FXML
    private javafx.scene.control.Label currPlayer;
    @FXML
    private javafx.scene.control.Label whiteScore;
    @FXML
    private javafx.scene.control.Label blackScore;
    @FXML
    Button settingButt;

    @FXML
    BorderPane root;
    @FXML
    VBox menu;






    public ReversiGame() {

    }

    @Override
    public void initialize(URL location, ResourceBundle
            resources) {
        game=initialGame();

        double gameSize = root.getPrefHeight();
        reversiBoard = new ReversiBoard(game, gameSize,gameSize);
        reversiBoard.setPrefWidth(600);
        reversiBoard.setPrefHeight(400);
        root.getChildren().add(0, reversiBoard);
        avaiableMoves = game.getLogic().optionalTurns(game.getCurPlayer().getSign());
        reversiBoard.setOnMouseClicked(event -> {
            System.out.println("sd");
            System.out.println(event.getX());
            System.out.println(event.getY());
            if (inGame) {
                Point p = new Point((int) event.getX(), (int) event.getY());
                playOneTurn(p);
            }
            event.consume();

        });
        setLabels();

       // this.noTurn = false;
        /*
        root.widthProperty().addListener((observable, oldValue, newValue) -> {
            double boardNewWidth = newValue.doubleValue() - 120;
            reversiBoard.setPrefWidth(boardNewWidth);
            reversiBoard.draw();
        });
        root.heightProperty().addListener((observable, oldValue, newValue) -> {
            reversiBoard.setPrefHeight(newValue.doubleValue());
            reversiBoard.draw();
        });
        reversiBoard.draw();

        root.setOnKeyPressed(reversiBoard.getOnKeyPressed());
        //


/*



/*

        GameLogic1 logic = new GameLogic1(board);
        ConsolePlayer pl1;
        ConsolePlayer pl2;
        if (blackTurn) {
            pl1=new ConsolePlayer('X');
            pl2=new ConsolePlayer('O');

        } else{
            pl1=new ConsolePlayer('O');
            pl2=new ConsolePlayer('X');
        }

        Game game=new Game(pl1, pl2, board, logic,blackTurn);
        while ((game.score(pl1.getSign()) + game.score(pl2.getSign()) < board.getSizeY() * board.getSizeX())&(countMoveTurn<2)) {
            String text = getTurn(blackTurn);
            currPlayer.appendText(text);
            whiteScore.appendText(Integer.toString(game.score(pl1.getSign())));
            blackScore.appendText(Integer.toString(game.score(pl2.getSign())));

            if (blackTurn) {
                countMoveTurn+=game.play1Turn(pl1,countMoveTurn);
            } else {
                countMoveTurn+=game.play1Turn(pl2,countMoveTurn);
            }
        }
        game.printWinner();
*/
    }



    public void settingButtonClicked(ActionEvent event) {
        event.consume();

        System.out.print("clicked setting");
        Stage stage = (Stage) settingButt.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Setting.fxml"));
        try {
            AnchorPane root = (AnchorPane) loader.load();
            loader.setController(new SettingController());
            Scene settingsScene = new Scene(root, 600, 400);
            stage.setScene(settingsScene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public int playOneTurn(Point userPlay){
        if   (!game.getCurPlayer().checkNextTurn(game.getLogic())) {
            game.oppositeTurn();
            return 1;
        }
        ConsolePlayer currPl=new ConsolePlayer(game.getCurPlayer().getSign());
        if(!game.getLogic().checkValidPoint(userPlay,currPl.getSign())) {
            //this.showAlert("This move is not Possible");
            return  0;
        }
        if(game.getLogic().checkValidPoint(userPlay,currPl.getSign())) {
            //this.noTurn = false;
            this.reversiBoard.lightTiles(false);
            game.getLogic().checkFlipPieces(userPlay.getRowNum()-1,userPlay.getColNum()-1,currPl.oppositeSign(currPl.getSign()),true);
            reversiBoard.update();
            int score =game.score(currPl.getSign());
            game.oppositeTurn();
        }
        /*

                if(pressLoc == null) {
            return;
        }
        if(!this.nextPosMoves.contains(pressLoc)) {
            this.showAlert("You can't do that move");
            return;
        }
        if(this.nextPosMoves.contains(pressLoc)){
            this.noTurn = false;
            int score = this.game.playOneTurn(pressLoc);
            this.game.setScoreAfterMove(score);
            this.gameBoard.unlightTiles();
            this.gameBoard.flipOnBoardFX();
            this.game.changeTurn();
        }

        if(this.game.getBoard().isBoardFull()) {
            //Show end game message
            this.showAlert("Game ended");
            return;
        }
        List<Pair<Integer, Integer>> posMoves = this.game.possibleMoves(this.game.getCurPlayer());
        if(posMoves.isEmpty()){
            this.showAlert("You have no move.");
            this.game.changeTurn();
f            posMoves = this.game.possibleMoves(this.game.getCurPlayer());
            if(posMoves.isEmpty()) {
                this.showAlert("You have no move, game ended.");
                //End game .
            }
        }
        this.nextPosMoves = this.game.possibleMoves(this.game.getCurPlayer());
        this.gameBoard.lightTiles();
        this.setTextLabels();
         */


        game.setCountMoveTurn(0);
        //board.printBoard();
        avaiableMoves= game.getLogic().optionalTurns(currPl.getSign());
        if(userPlay==null) {
            return 0;
        }
        if(!game.getLogic().checkValidPoint(userPlay,currPl.getSign())) {
            return 0;
        }
        board.setSign(userPlay.getRowNum()-1,userPlay.getColNum()-1,currPl.getSign());
        game.oppositeTurn();
        return 0;
    }
    public Game initialGame() {
        inGame=false;
        gameSetting=new GameSetting();
        try {
            gameSetting.readFromFIle();
        } catch (Exception e) {
            e.printStackTrace();
        }
        board=new Board(gameSetting.getBoardSize(),gameSetting.getBoardSize());

        GameLogic1 logic= new GameLogic1(board);
        ConsolePlayer pl1;
        ConsolePlayer pl2;
        if (gameSetting.getBlackTurn()) {
            pl1=new ConsolePlayer('X');
            pl2=new ConsolePlayer('O');

        } else{
            pl1=new ConsolePlayer('O');
            pl2=new ConsolePlayer('X');
        }
        pl1.setColor(gameSetting.getPlayerAColor());
        pl2.setColor(gameSetting.getPlayerBColor());

        Game game=new Game(pl1, pl2, board, logic,gameSetting);
        return  game;
        /*
        game.play();
        */
    }


    public String getTurn(boolean bool) {
        if (bool) {
            return "Black";
        }
        return "White";
    }

    @FXML
    public void startButtonClicked(ActionEvent event) {
        inGame=true;
        System.out.print("clicked startGame");
    }


    public void setLabels(){
        blackScore.setText("Black Player Score:"+Integer.toString(this.game.score(game.getPlayer1().getSign())));
        whiteScore.setText("White Player Score:"+Integer.toString(this.game.score(game.getPlayer2().getSign())));
        if(this.game.getTurn()){
            currPlayer.setText("CurrentPlayer: Player 1");
        } else {
            currPlayer.setText("CurrentPlayer: Player 2");
        }
    }
}
