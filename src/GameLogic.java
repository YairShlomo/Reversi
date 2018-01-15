/*
Name:Yair Shlomo
ID: 308536150
Name:Gal Eini
ID: 305216962
*/
import java.util.List;

public interface GameLogic {

    char oppositeSign(char sign);

    boolean checkSign(int x,int y,char sign);

    boolean  isFull(int x, int y);
    Point getPoint(int x,int y);

    boolean checkValidPoint(Point userPoint,char sign);
    List<Point> optionalTurns(char sign);
    boolean checkFlipPieces(int x, int y, char sign, boolean bool);


    }
