/*
Name:Yair Shlomo
ID: 308536150
Name:Gal Eini
ID: 305216962
*/
import java.util.List;
import java.util.Scanner;

public class ConsolePlayer implements Player{
    private char sign;
    public ConsolePlayer(char signCp){
        sign=signCp;
    }
    public char getSign() {
        return sign;
    }


    public char oppositeSign(char sign) {
        if (sign == 'X') {
            return 'O';
        }
        if (sign == 'O') {
            return 'X';
        }
        return '0';
    }



    public Point yourPlay(List<Point> pointList) {
        int userX, userY;
        System.out.println( getSign() + ": It's your move" );
        System.out.println ( "Your possible moves: " );
        for (int i = 0; i < pointList.size(); ++i) {
            pointList.get(i).printPoint();
            if (i != pointList.size() - 1) {
                System.out.println( "," );
            }
        }
        System.out.println( "" +"\n");
        System.out.println( "Please enter your move row ,col:(enter row,col separately)" +"\n");

        Scanner in = new Scanner(System.in);

        userX = in.nextInt();
        userY = in.nextInt();
        return new Point(userX, userY);
    }

    public boolean checkNextTurn(GameLogic logic) {
        if (logic.optionalTurns((getSign())).size()==0) {
            System.out.println( "No possible moves. play passes back to other player. press any key to countinue" +"\n");
            Scanner in = new Scanner(System.in);
            String str = in.nextLine();
            return false;
        }
        return true;
    }



}
