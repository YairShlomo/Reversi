
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

import java.io.IOException;

public class ReversiBoard extends GridPane {
    private char[][] board;
    private Color playerACol;
    private Color playerBCol;

    //private Player player;


    public ReversiBoard(char[][] board,Color playerACol1,Color playerBCol1) {
        this.board = board;
        playerACol=playerACol1;
        playerBCol=playerBCol1;

        //player = new Player(this, 0, 0);

        FXMLLoader fxmlLoader = new
                FXMLLoader(getClass().getResource("ReversiBoard.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);
        try {
            fxmlLoader.load();
            /*
            this.setOnKeyPressed(event -> {
                switch (event.getCode()) {
                    case DOWN:
                        player.moveDown();
                        break;
                    case UP:
                        player.moveUp();
                        break;
                    case LEFT:
                        player.moveLeft();
                        break;
                    case RIGHT:
                        player.moveRight();
                        break;
                }
            });
                    fx:controller="ReversiBoard"
            */

        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }
    public void draw() {
        this.getChildren().clear();
        int height = (int)this.getPrefHeight();
        int width = (int)this.getPrefWidth();

        int cellHeight = height / board.length;
        int cellWidth = width / board.length;
        Rectangle r;
        Circle c;
        /*
        this.setFit
        iv.setFitWidth(cellWidth);
        iv.setFitHeight(cellHeight);
        //grid.getChildren().remove(iv);
        grid.add(iv, col, row);
        */
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                r=new Rectangle(cellWidth, cellHeight, Color.WHITE);
                c=new Circle(cellWidth/2, cellHeight/2,
                        cellWidth/2-cellHeight/6,Color.YELLOW);
                r.setStroke(Color.BLACK);
                this.add(r,j,i);
                if (board[i][j] != ' ') {
                    this.add(c,j,i);
                }

                /*
                if (board[i][j] == 'O')
                    this.add(new Rectangle(cellWidth, cellHeight,
                            Color.WHITE), j, i);
                else if (board[i][j] == 'X')
                    this.add(new Rectangle(cellWidth, cellHeight,
                            Color.BLACK), j, i);
                else{

                }
                 */
            }

        }
        //player.draw(cellWidth, cellHeight);

    }
}

