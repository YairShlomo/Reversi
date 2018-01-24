package controllers;/*
Name:Yair Shlomo
ID: 308536150
Name:Gal Eini
ID: 305216962
*/

import gamePlay.Game;
import gamePlay.Player;
import gamePlay.Point;
import gamePlay.Tile;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import java.util.List;

public class ReversiBoard extends GridPane {
    private Game game;
    private Tile[][] tiles;
    private List<Point> avaiableMoves;
    private double heightCell;
    private double widthCell;
    private int size;


    public ReversiBoard(Game game, double width, double height) {
        this.game = game;
        this.setPrefWidth(width);
        this.setPrefHeight(height);
        size = this.game.getBoard().getSizeX();
        heightCell = this.getPrefHeight()/size;
        widthCell = this.getPrefWidth()/size;
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
    }
    /**
     * update tiles as game board
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
    /**
     * lightTiles light/unlight available moves tiles
     * @param bool - light/unlight.
     */
    public void lightTiles(boolean bool) {
        Player currPlayer=game.getCurPlayer();
        avaiableMoves = game.getLogic().optionalTurns(currPlayer.getSign());
        System.out.println("gamePlay.Player: " + game.getCurPlayer().getSign());
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
    /**
     * getHeightCell returns the height of cell
     * @return heightCell -  the height of cell
     */
    public double getHeightCell() {
        return heightCell;
    }
    /**
     * getWidthCell returns the width of cell
     * @return widthCell -  the width of cell
     */
    public double getWidthCell() {
        return widthCell;
    }
}

