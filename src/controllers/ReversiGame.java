package controllers;/*
Name:Yair Shlomo
ID: 308536150
Name:Gal Eini
ID: 305216962
*/

import gamePlay.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class ReversiGame implements Initializable {

    private Board board;
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
    Button newGameButton;
    @FXML
    BorderPane root;

    @Override
    public void initialize(URL location, ResourceBundle
            resources) {
        game = initialGame();
        double gameSize = root.getPrefHeight();
        reversiBoard = new ReversiBoard(game, gameSize, gameSize);
        reversiBoard.setPrefWidth(600);
        reversiBoard.setPrefHeight(400);
        root.getChildren().add(0, reversiBoard);
        avaiableMoves = game.getLogic().optionalTurns(game.getCurPlayer().getSign());
        this.reversiBoard.setOnMouseClicked(event -> {
            int x = (int) Math.ceil(event.getX() / reversiBoard.getWidthCell());
            int y = (int) Math.ceil(event.getY() / reversiBoard.getHeightCell());
            if (inGame) {
                Point p = new Point(x, y);
                playOneTurn(p);
            } else{
                this.showAlert("gamePlay.Game is off. press StartGame to start");
            }
            event.consume();
        });
        setLabels();
    }
    /**
     * playOneTurn manage flow of each 1 turn
     * @param userPlay - number of row.
     */
    public void playOneTurn(Point userPlay) {
        if (!game.getLogic().checkValidPoint(userPlay,game.getCurPlayer().getSign())) {
            this.showAlert("This move is not Possible");
            return;
        } else {
            this.reversiBoard.lightTiles(false);
            game.getBoard().setSign(userPlay.getRowNum() - 1, userPlay.getColNum() - 1,
                    game.getCurPlayer().getSign());
            game.getLogic().checkFlipPieces(userPlay.getRowNum() - 1, userPlay.getColNum()
                    - 1, game.getCurPlayer().oppositeSign(game.getCurPlayer().getSign()), true);
            reversiBoard.update();
            game.oppositeTurn();
            this.reversiBoard.lightTiles(true);
        }
        if (!game.getCurPlayer().checkNextTurn(game.getLogic())) {
            showAlert("No possible moves. play passes back to other player. press ok to countinue");
            game.setCountMoveTurn(game.getCountMoveTurn() + 1);
            game.oppositeTurn();
            this.reversiBoard.lightTiles(true);
            if (!game.getCurPlayer().checkNextTurn(game.getLogic())) {
                game.setCountMoveTurn(game.getCountMoveTurn() + 1);
                game.oppositeTurn();
            }
        }
        if (game.getCountMoveTurn() > 1) {
            inGame = false;
            winnerMessage();
            newGameButton.fire();
        }
        setLabels();
        game.setCountMoveTurn(0);
    }
    /**
     * initialGame intialize game with settings and returns it
     * @return gamePlay.Game -  returns new game with updated setting
     */
    public Game initialGame() {
        inGame = false;
        gameSetting = new GameSetting();
        try {
            gameSetting.readFromFIle();
        } catch (Exception e) {
            if (e instanceof java.io.FileNotFoundException) {
                try {
                    gameSetting.writeToSettings(8, Color.BLACK, Color.YELLOW, true);
                    gameSetting.readFromFIle();
                } catch (Exception e1) {

                    e.printStackTrace();
                }
            } else {
                e.printStackTrace();
            }
        }
        board = new Board(gameSetting.getBoardSize(), gameSetting.getBoardSize());
        GameLogic1 logic = new GameLogic1(board);
        ConsolePlayer pl1;
        ConsolePlayer pl2;
        if (gameSetting.getBlackTurn()) {
            pl1 = new ConsolePlayer('X');
            pl2 = new ConsolePlayer('O');

        } else {
            pl1 = new ConsolePlayer('O');
            pl2 = new ConsolePlayer('X');
        }
        pl1.setColor(gameSetting.getPlayerAColor());
        pl2.setColor(gameSetting.getPlayerBColor());

        Game game = new Game(pl1, pl2, board, logic, gameSetting);
        return game;
    }
    /**
     * setLabels update all labels of game
     */
    public void setLabels() {
        blackScore.setText("Black Player Score:" + Integer.toString(this.game.score(game.getPlayer1().getSign())));
        whiteScore.setText("White Player Score:" + Integer.toString(this.game.score(game.getPlayer2().getSign())));
        if (this.game.getTurn()) {
            currPlayer.setText("CurrentPlayer: Player 1");
        } else {
            currPlayer.setText("CurrentPlayer: Player 2");
        }
    }
    /**
     * showAlert responsible of showing diff alerts
     * @param message - number of row.
     */
    public void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
    /**
     * winnerMessage show winner message.
     */
    public void winnerMessage() {
        if (game.score(game.getPlayer1().getSign()) > game.score(game.getPlayer2().getSign())) {
            showAlert("gamePlay.Player '" + game.getPlayer1().getSign() + "' wins!" + "\n");
            return;
        }
        if (game.score(game.getPlayer2().getSign()) > game.score(game.getPlayer1().getSign())) {
            showAlert("gamePlay.Player '" + game.getPlayer2().getSign() + "' wins!" + "\n");
            return;
        }
        showAlert("Great gamePlay.Game ,it's a tie!");
    }
    /**
     * settingButtonClicked managing actions after pressing setting button
     * @param event - action event
     */
    @FXML
    public void settingButtonClicked(ActionEvent event) {
        event.consume();
        Stage stage = (Stage) settingButt.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxmlFiles/Setting.fxml"));
        try {
            AnchorPane root = (AnchorPane) loader.load();
            loader.setController(new SettingController());
            Scene settingsScene = new Scene(root, 600, 400);
            settingsScene.getStylesheets().add(getClass().getResource("/etc/SettingDesign.css").toExternalForm());
            stage.setScene(settingsScene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    /**
     * startButtonClicked managing actions after pressing start button
     * @param event - action event
     */
    @FXML
    public void startButtonClicked(ActionEvent event) {
        inGame = true;
    }
    /**
     * newButtonClicked managing actions after pressing new button
     * @param event - action event
     */
    @FXML
    public void newButtonClicked(ActionEvent event) {
        try {
            BorderPane root = (BorderPane) FXMLLoader.load(getClass().getResource("/fxmlFiles/ReversiGame.fxml"));
            Scene scene = new Scene(root, 700, 410);
            scene.getStylesheets().add(getClass().getResource("/etc/GameDesign.css").toExternalForm());
            Stage primaryStage = (Stage) this.root.getScene().getWindow();
            primaryStage.setTitle("Reversi game");
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    /**
     * exitButtonClicked managing actions after pressing exit button
     * @param event - action event
     */
    @FXML
    public void exitButtonClicked(ActionEvent event) {
        System.exit(0);
    }
}
