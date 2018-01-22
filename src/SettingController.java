
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.Observable;
import java.util.ResourceBundle;

/**
 * Created by Eilon on 1/13/2018.
 */
public class SettingController implements Initializable {
    @FXML
    TextField inputBoardSize;
    @FXML
    Button done;
    @FXML
    CheckBox inputIsBlackStart;
    @FXML
    TextField inputPl1Col;
    @FXML
    TextField inputPl2Col;
    private GameSetting gameSetting;
    private int boardSize = 8;
    private Color playerAColor = Color.BLACK;
    private Color playerBColor = Color.YELLOW;
    private boolean blackTurn = true;
    public SettingController() {

    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
    @FXML
    public void DoneButtonClicked(ActionEvent event){
        event.consume();

        /*
        setting.setBlackTurn(Boolean.parseBoolean(inputIsBlackStart.getText()));
        setting.setRowNum(Integer.parseInt(inputRowNum.getText()));
        setting.setColNum(Integer.parseInt(inputColNum.getText()));

        Field field1 = Class.forName("java.awt.Color").getField(inputPl1Col.getText());
        setting.setPlayerAColor((Color) field1.get(null));
        Field field2 = Class.forName("java.awt.Color").getField(inputP12Col.getText());
        setting.setPlayerAColor((Color) field1.get(null));
*/
        /*
        handleTextRow();
        handleTextCol();
        handleTextPl1Color();
        handleTextPl2Color();
        */
        System.out.print("clicked Done");

        try {
            gameSetting = new GameSetting();
            handleTextPl1Color();
            handleTextPl2Color();
            handleBoardSize();
            if(playerAColor.toString().equals(playerBColor.toString())) {
                this.showAlert("Please choose different colors");
                return;
            }
            gameSetting.writeToSettings(boardSize, playerAColor,playerBColor,blackTurn);
        } catch (Exception e){
            e.printStackTrace();
        }
        try {
            Stage primaryStage = (Stage) this.done.getScene().getWindow();
            BorderPane root = (BorderPane) FXMLLoader.load(getClass().getResource("ReversiGame.fxml"));
            Scene scene = new Scene(root,600,400);
            //scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
            primaryStage.setTitle("Reversi Game");
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    public void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    public void handleBoardSize() {
        boardSize=(Integer.parseInt(inputBoardSize.getText()));

    }
/*
    public void handleTextCol() {
        gameSetting.setRowNum(Integer.parseInt(inputColNum.getText()));
        if (((Integer.parseInt(inputColNum.getText())) != game.getBoard().getSizeY()) |
                ((Integer.parseInt(inputRowNum.getText())) != game.getBoard().getSizeX())) {
            Stage stage = (Stage) this.settingButt.getScene().getWindow();
            Node node= root.getChildren().get(0);
            root.getChildren().remove(node);
            board = new Board(gameSetting.getRowNum(), gameSetting.getRowNum());
            game = initialGame();
            double gameSize = root.getPrefHeight();
            reversiBoard = new ReversiBoard(game, gameSize, gameSize);
            reversiBoard.setPrefWidth(600);
            reversiBoard.setPrefHeight(400);
            root.getChildren().add(0, reversiBoard);
            avaiableMoves = game.getLogic().optionalTurns(game.getCurPlayer().getSign());
            reversiBoard.setOnMouseClicked(event -> {
                if (inGame) {
                    Point p = new Point((int) event.getX(), (int) event.getY());
                    playOneTurn(p);
                }
            });
            setLabels();


        }
    }
*/
    public void handleTextPl1Color() {
        playerAColor=gameSetting.getColor(inputPl1Col.getText());
        //gameSetting.setPlayerAColor(c);
        //game.getPlayer1().setColor(c);
        //reversiBoard.update();


    }
    public void handleTextPl2Color() {
        playerBColor=gameSetting.getColor(inputPl2Col.getText());
        //gameSetting.setPlayerBColor(c);
       // game.getPlayer2().setColor(c);
        //reversiBoard.update();
    }
    @FXML
    public void handleTextTurn() {
        //reversiBoard.lightTiles(false);

        boolean bool=true;
        if (!inputIsBlackStart.isSelected()) {
            bool=false;
        }
        blackTurn=bool;
        //gameSetting.setBlackTurn(bool);
        //game.setTurn(bool);
        //setLabels();
        //avaiableMoves=game.getLogic().optionalTurns(game.getCurPlayer().getSign());
       // reversiBoard.lightTiles(true);


        //String text = String.valueOf(setting.getBlackTurn());
        //currPlayer.appendText(text);
    }

}
