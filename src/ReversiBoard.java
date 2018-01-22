
import com.sun.deploy.security.CPCallbackHandler;
import com.sun.javafx.property.adapter.PropertyDescriptor;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.util.List;
public class ReversiBoard extends GridPane {
    //implements MouseListener {
    private char[][] board;

    private Game game;
    private Tile[][] tiles;
    private List<Point> avaiableMoves;
    private Point p;
    //private Player player;


    public ReversiBoard(Game game, double width, double height) {
        this.game = game;
        this.board = game.getBoard().getGameBoard();
        this.setPrefWidth(width);
        this.setPrefHeight(height);
        int size = this.game.getBoard().getSizeX();
        double heightCell = this.getPrefHeight()/size;
        double widthCell = this.getPrefWidth()/size;
        this.tiles = new Tile[size][size];
        for(int i =0; i < size; i++) {
            this.tiles[i] = new Tile[size];
            for(int j =0; j < size; j++) {
                Point p = new Point(i,j);
                char sign = this.game.getBoard().getGameBoard()[i][j];
                Color tileColor;
                if(sign == 'X') {
                    tileColor = this.game.getPlayer1().getColor();
                } else if( sign == 'O') {
                    tileColor = this.game.getPlayer2().getColor();
                } else {
                    tileColor = Color.TRANSPARENT;
                }
                this.tiles[i][j] = new Tile(p, heightCell, widthCell, tileColor);

                this.add(tiles[i][j], i, j);
            }
        }
        this.lightTiles(true);
        //player = new Player(this, 0, 0);
/*
        FXMLLoader fxmlLoader = new
                FXMLLoader(getClass().getResource("ReversiBoard.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);
        try {
            fxmlLoader.load();

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


        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
        */
    }
    /*
        public void draw() {
            this.getChildren().clear();
            int height = (int) this.getPrefHeight();
            int width = (int) this.getPrefWidth();

            int cellHeight = height / board.length;
            int cellWidth = width / board.length;
            Rectangle r;
            Circle c;

            this.setFit
            iv.setFitWidth(cellWidth);
            iv.setFitHeight(cellHeight);
            //grid.getChildren().remove(iv);
            grid.add(iv, col, row);

            //System.out.println("height:"+cellHeight+"width:"+cellWidth);
            for (int i = 0; i < board.length; i++) {
                for (int j = 0; j < board[i].length; j++) {
                    r = new Rectangle(cellWidth, cellHeight, Color.WHITE);
                    r.setOnMouseClicked(e->{
                        int row=(int)Math.ceil(e.getSceneX()/cellWidth);
                        int col=(int)Math.ceil(e.getSceneY()/cellHeight);
                        System.out.println(e.getX());
                        System.out.println(e.getY());
                    });
                    r.setX(i);
                    r.setY(j);
                    final int indexI = i;
                    final int indexJ = j;


                    c = new Circle(cellWidth / 2, cellHeight / 2,
                            cellWidth / 2 - cellHeight / 6, getColor(i, j));
                    GridPane.setHalignment(c, HPos.CENTER);
                    GridPane.setValignment(c, VPos.CENTER);
                    r.setStroke(Color.BLACK);
                    this.add(r, j, i);
                    if (board[i][j] != ' ') {
                        this.add(c, j, i);
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


                }

            }
            /*
            this.setOnMouseClicked((MouseEvent e)->{
                e.getX();
                e.getY();
                //addEventHandler();
                System.out.println(e.getX());
                System.out.println(e.getY());
                notify();
                this.mouseClicked(e);

            });


        //player.draw(cellWidth, cellHeight);
        //List s = this.getChildren();
        // GridPane.getColumnIndex();




/*
    public Color getColor(int x, int y) {
        if (board[x][y] == ('X')) {
            return Color.WHITE;
        } else if (board[x][y] == ('O')) {
            return CPCallbackHandle;
        }
        return Color.WHITE;
    }
    */
    public void update() {
        int sizeX = this.game.getBoard().getSizeX();
        int sizeY = this.game.getBoard().getSizeY();

        for(int i =0 ; i< sizeX; i++) {
            for (int j = 0; j < sizeY; j++) {
                char sign = this.game.getBoard().getGameBoard()[i][j];
                if(sign == 'X'){
                    this.tiles[i][j].changeCircleColor(game.getPlayer1().getColor());
                }
                if(sign == 'O') {
                    this.tiles[i][j].changeCircleColor(game.getPlayer2().getColor());
                }
            }
        }
    }
    public void lightTiles(boolean bool) {
        Player currPlayer=game.getCurPlayer();
        avaiableMoves = game.getLogic().optionalTurns(currPlayer.getSign());
        System.out.println("Player: " + game.getCurPlayer().getSign());
        for(Point p: avaiableMoves) {
            System.out.println(p.getRowNum() +","+ p.getColNum());
            int x = p.getRowNum();
            int y = p.getColNum();
            if (bool) {
                this.tiles[x-1][y-1].light(Color.LIGHTGRAY);
            } else{
                this.tiles[x-1][y-1].unlight();
            }
        }
    }

}

