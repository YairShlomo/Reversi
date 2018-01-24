package controllers;/*
Name:Yair Shlomo
ID: 308536150
Name:Gal Eini
ID: 305216962
*/

import gamePlay.GameSetting;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import java.net.URL;
import java.util.ResourceBundle;

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
    /**
     * doneButtonClicked managing actions after pressing done button
     * @param event - action event
     */
    @FXML
    public void doneButtonClicked(ActionEvent event){
        event.consume();
        try {
            gameSetting = new GameSetting();
            handleTextPl1Color();
            handleTextPl2Color();
            handleBoardSize();
            if(playerAColor.toString().equals(playerBColor.toString())) {
                this.showAlert("Colors must be different");
                return;
            }
            gameSetting.writeToSettings(boardSize, playerAColor,playerBColor,blackTurn);
        } catch (Exception e){
            e.printStackTrace();
        }
        try {
            Stage primaryStage = (Stage) this.done.getScene().getWindow();
            BorderPane root = (BorderPane) FXMLLoader.load(getClass().getResource("/fxmlFiles/ReversiGame.fxml"));
            Scene scene = new Scene(root,700,410);
            scene.getStylesheets().add(getClass().getResource("/etc/GameDesign.css").toExternalForm());
            primaryStage.setTitle("Reversi gamePlay.Game");
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch(Exception e) {
            e.printStackTrace();
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
     * handleBoardSize update board size.
     */
    public void handleBoardSize() {
        boardSize=(Integer.parseInt(inputBoardSize.getText()));
    }
    /**
     * handleTextPl1Color update player 1 color.
     */
    public void handleTextPl1Color() {
        playerAColor=gameSetting.getColor(inputPl1Col.getText());
    }
    /**
     * handleTextPl2Color update player 2 color
     */
    public void handleTextPl2Color() {
        playerBColor=gameSetting.getColor(inputPl2Col.getText());
    }
    /**
     * handleTextTurn update player turn
     */
    @FXML
    public void handleTextTurn() {
        boolean bool=true;
        if (!inputIsBlackStart.isSelected()) {
            bool=false;
        }
        blackTurn=bool;
    }
}
