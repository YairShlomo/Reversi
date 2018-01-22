import javafx.scene.paint.Color;

import java.awt.*;
import java.util.List;

/*
Name:Yair Shlomo
ID: 308536150
Name:Gal Eini
ID: 305216962
*/
public interface Player {
    char getSign();

    char oppositeSign(char sign);

    boolean checkNextTurn(GameLogic logic);

    Point yourPlay(List<Point> pointList);
    public void setColor(Color color);
    public javafx.scene.paint.Color getColor();


}

