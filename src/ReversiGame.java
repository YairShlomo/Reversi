import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Field;
import java.net.URL;
import java.util.ResourceBundle;

public class ReversiGame implements Initializable {

    private Board board;
    private char gameBoard[][];
    private int rowNum = 8;
    private int colNum = 8;
    private Color playerAColor = Color.BLACK;
    private Color playerBColor = Color.WHITE;
    private boolean blackTurn = true;

    @FXML
    Button setting;
    @FXML
    BorderPane root;

    public ReversiGame() {

        openFIle();
        board = new Board(rowNum, colNum);
        gameBoard = board.getBoard();
        /*
        gameBoard = new char[rowCount][colCount];
        for (int i = 0; i < colCount; ++i) {
            gameBoard[i] = new char[colCount];
        }
        int mdlle = (rowCount / 2) - 1;
        gameBoard[mdlle][mdlle] = 'O';
        gameBoard[mdlle][mdlle + 1] = 'X';
        gameBoard[mdlle + 1][mdlle] = 'X';
        gameBoard[mdlle + 1][mdlle + 1] = 'O';
        System.out.println("helo");
        */
    }

    @Override
    public void initialize(URL location, ResourceBundle
            resources) {
        ReversiBoard reversiBoard = new ReversiBoard(gameBoard, playerAColor, playerAColor);
        reversiBoard.setPrefWidth(400);
        reversiBoard.setPrefHeight(400);
        root.getChildren().add(0, reversiBoard);
        reversiBoard.draw();
        root.widthProperty().addListener((observable, oldValue, newValue) -> {
            double boardNewWidth = newValue.doubleValue() - 120;
            reversiBoard.setPrefWidth(boardNewWidth);
            reversiBoard.draw();
        });
        root.heightProperty().addListener((observable, oldValue, newValue) -> {
            reversiBoard.setPrefHeight(newValue.doubleValue());
            reversiBoard.draw();
        });
        root.setOnKeyPressed(reversiBoard.getOnKeyPressed());
    }

    public void FileButtonClicked(ActionEvent event) {
        setting.setVisible(true);
        event.consume();
        System.out.print("clicked file");
    }

    public void SettingButtonClicked(ActionEvent event) {
        event.consume();
        System.out.print("clicked setting");
    }

    public void openFIle() {
        String openingPlayer = "OpeningPlayer:";
        String colors = "Colors:";
        String boardSize = "BoardSize:";
        BufferedReader br = null;
        FileReader fr = null;
        String line;


        try {
            fr = new FileReader("settingFile");
            br = new BufferedReader(fr);
            line = br.readLine();
            while (line != null) {

                if (line.startsWith(boardSize)) {
                    line = line.replace(boardSize, "");
                    colNum = Integer.parseInt(line);
                    rowNum = Integer.parseInt(line);
                } else if (line.startsWith(colors)) {
                    line = line.replace(colors, "");
                    String[] parts = line.split(",");
                    String part1 = parts[0]; // 004
                    String part2 = parts[1];
                    Field field1 = Class.forName("java.awt.Color").getField(part1);
                    playerAColor = (Color) field1.get(null);
                    Field field2 = Class.forName("java.awt.Color").getField(part2);
                    playerBColor = (Color) field2.get(null);
                } else if (line.startsWith(openingPlayer)) {
                    line = line.replace(openingPlayer, "");
                    blackTurn = Boolean.parseBoolean(line);
                }
                System.out.println(line);
                line = br.readLine();
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {

            try {

                if (br != null)
                    br.close();

                if (fr != null)
                    fr.close();

            } catch (IOException ex) {

                ex.printStackTrace();

            }

        }

    }


}
