package gamePlay;

import javafx.scene.paint.Color;
import java.io.*;

public class GameSetting {
    private int boardSize;
    private Color playerAColor;
    private Color playerBColor;
    private boolean blackTurn;
    private File fileSetting;
    public  GameSetting(){
        fileSetting = new File("setting.txt");
    }
    /**
     * getBoardSize returns board size
     * @return boardSize - board size
     */
    public int getBoardSize(){
        return boardSize;
    }
    /**
     * getPlayerAColor returns player 1 color
     * @return playerAColor - player 1 color
     */
    public Color getPlayerAColor(){
        return playerAColor;
    }
    /**
     * getPlayerBColor returns player 2 color
     * @return playerBColor - player 2 color
     */
    public Color getPlayerBColor(){
        return playerBColor;
    }
    /**
     * getBlackTurn returns curr turn
     * @return blackTurn - curr turn
     */
    public boolean getBlackTurn(){
        return blackTurn;
    }
    /**
     * readFromFIle read settings from file and save them in game setting
     * @throws  Exception - in case somthing in reading failed
     */
    public void readFromFIle() throws Exception{
        String openingPlayerStr = "OpeningPlayer:";
        String colorsStr = "Colors:";
        String boardSizeStr = "BoardSize:";
            BufferedReader reader;
            reader = new BufferedReader(new InputStreamReader(new FileInputStream(fileSetting)));
            String line = reader.readLine();
            while (line!= null) {
                if (line.startsWith(boardSizeStr)) {
                    line = line.replace(boardSizeStr, "");
                    boardSize=Integer.parseInt(line);
                } else if (line.startsWith(colorsStr)) {
                    line = line.replace(colorsStr, "");
                    String[] parts = line.split(",");
                    playerAColor=Color.valueOf(parts[0]);
                    playerBColor=Color.valueOf(parts[1]);
                } else if (line.startsWith(openingPlayerStr)) {
                    line = line.replace(openingPlayerStr, "");
                    blackTurn=Boolean.parseBoolean(line);
                }
                line = reader.readLine();
            }
    }
    public void writeToSettings(int size, Color p1Color, Color p2Color,boolean turn) throws Exception{
        BufferedWriter writer;
        writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(fileSetting)));
        String boardSize = Integer.toString(size);
        String color1 = p1Color.toString();
        String color2 = p2Color.toString();
        String blackTurn=Boolean.toString(turn);
        writer.write("OpeningPlayer:");
        writer.write(blackTurn);
        writer.newLine();
        writer.write("Colors:");
        writer.write(color1);
        writer.write(",");
        writer.write(color2);
        writer.newLine();
        writer.write("BoardSize:");
        writer.write(boardSize);
        writer.newLine();
        writer.close();
    }
    /**
     * Get the color from a string name
     *
     * @param col name of the color
     * @return White if no color is given, otherwise the Color object
     */
    public Color getColor(String col) {
        Color color = Color.BLACK;
        switch (col.toLowerCase()) {
            case "black":
                color = Color.BLACK;
                break;
            case "blue":
                color = Color.BLUE;
                break;
            case "cyan":
                color = Color.CYAN;
                break;
            case "darkgray":
                color = Color.DARKGRAY;
                break;
            case "gray":
                color = Color.GRAY;
                break;
            case "green":
                color = Color.GREEN;
                break;

            case "yellow":
                color = Color.YELLOW;
                break;
            case "lightgray":
                color = Color.LIGHTGRAY;
                break;
            case "magneta":
                color = Color.MAGENTA;
                break;
            case "orange":
                color = Color.ORANGE;
                break;
            case "pink":
                color = Color.PINK;
                break;
            case "red":
                color = Color.RED;
                break;
            case "white":
                color = Color.WHITE;
                break;
        }
        return color;
    }
}
