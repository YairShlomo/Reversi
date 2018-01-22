import javafx.scene.paint.Color;

import java.io.*;
import java.lang.reflect.Field;


public class GameSetting {

    private int boardSize = 8;
    private Color playerAColor = Color.BLACK;
    private Color playerBColor = Color.YELLOW;
    private boolean blackTurn = true;
    private File fileSetting;
    public  GameSetting(){
        fileSetting = new File("settings.txt");

    }

    public int getBoardSize(){
        return boardSize;
    }
    public Color getPlayerAColor(){
        return playerAColor;
    }
    public Color getPlayerBColor(){
        return playerBColor;
    }
    public boolean getBlackTurn(){
        return blackTurn;
    }
    public void setBoardSize(int boardSize1){
        boardSize=boardSize1;
    }

    public void setPlayerAColor(Color color){
        playerAColor=color;
    }
    public void setPlayerBColor(Color color){
        playerBColor=color;
    }
    public void setBlackTurn(boolean bool){
        blackTurn=bool;
    }
    public void readFromFIle() throws Exception{
        //Setting setting = new Setting();
        String openingPlayerStr = "OpeningPlayer:";
        String colorsStr = "Colors:";
        String boardSizeStr = "BoardSize:";


            BufferedReader reader;
            reader = new BufferedReader(new InputStreamReader(new FileInputStream(fileSetting)));
            String line = reader.readLine();
            while (line!= null) {

                if (line.startsWith(boardSizeStr)) {
                    line = line.replace(boardSizeStr, "");
                    //setting.setRowNum(Integer.parseInt(line));
                    //setting.setColNum(Integer.parseInt(line));
                    boardSize=Integer.parseInt(line);

                } else if (line.startsWith(colorsStr)) {
                    line = line.replace(colorsStr, "");
                    String[] parts = line.split(",");
                   // String part1 =  .toString();
                   // String part2 = Color.valueOf(parts[1]).toString();
                    playerAColor=Color.valueOf(parts[0]);
                    playerBColor=Color.valueOf(parts[1]);



                } else if (line.startsWith(openingPlayerStr)) {
                    line = line.replace(openingPlayerStr, "");
                    blackTurn=Boolean.parseBoolean(line);
                }
                //System.out.println(line);
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
