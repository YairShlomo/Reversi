package gamePlay;

/*
Name:Yair Shlomo
ID: 308536150
Name:Gal Eini
ID: 305216962
*/
public class Point {

    private int rowNum;
    private int colNum;

    public Point(int rowNumP,int colNumP) {
    rowNum=rowNumP;
    colNum=colNumP;
    }
    /**
     * printPoint function-prints the gamePlay.Point
     */
    public void printPoint() {
        System.out.println("("+ rowNum + "," +colNum+")" );

    }
    /**
     * getRowNum returns number of rows in gameboard
     * @return rowNum -  number of rows in gameboard
     */
    public int getRowNum() {
        return rowNum;
    }
    /**
     * getRowNum returns number of columns in gameboard
     * @return colNum - number of columns in gameboard
     */
    public int  getColNum() {
        return colNum;
    }
    /**
     * isEqual returns if two points are equal or not
     * @param other- anothr point co compare with current
     * @return boolean - equal or not
     */
    public boolean isEqual(Point other) {
        if (rowNum == other.getRowNum() && colNum == other.getColNum()) {
            return true;
        } else {
            return false;
        }
    }
}
