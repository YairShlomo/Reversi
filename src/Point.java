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
}
