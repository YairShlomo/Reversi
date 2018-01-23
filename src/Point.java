public class Point {

    private int rowNum;
    private int colNum;

    public Point(int rowNumP,int colNumP) {
    rowNum=rowNumP;
    colNum=colNumP;
    }
    public void printPoint() {
        System.out.println("("+ rowNum + "," +colNum+")" );

    }
    public int getRowNum() {
        return rowNum;
    }
    public int  getColNum() {
        return colNum;
    }
    public boolean isEqual(Point other) {
        if (rowNum == other.getRowNum() && colNum == other.getColNum()) {
            return true;
        } else {
            return false;
        }
    }
}
