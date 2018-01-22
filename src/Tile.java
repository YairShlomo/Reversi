

import javafx.geometry.Pos;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.util.Pair;

/**
 * Created by Eilon on 1/13/2018.
 */
public class Tile extends StackPane{
    private Circle circle;
    private Point location;
    private boolean canBeClicked = false;
    private Color color;
    private boolean clicked;
    private boolean turn;
    private Rectangle rec;

    public Tile(Point p, double height, double width, Color color) {
        this.color = color;
        this.setPrefHeight(height);
        this.setPrefWidth(width);
        this.location = p;
        this.canBeClicked = false;
        this.turn = false;
        rec = new Rectangle(width, height);
        rec.setStrokeWidth(0.5);
        rec.setFill(null);
        rec.setStroke(Color.BLACK);
        setAlignment(Pos.CENTER);
        double smaller = Math.min(height, width);
        this.getChildren().add(rec);
        double radius = smaller/2;
        this.circle = new Circle(radius, this.color);
        this.getChildren().add(circle);
        setOnMouseClicked(event -> {
            this.clicked = true;
        });
    }
    public void changeCircleColor(Color color) {
        this.getChildren().remove(circle);
        this.color = color;
        this.circle = new Circle(this.circle.getRadius(), this.color);
        this.getChildren().add(circle);
    }

    public Point getPoint(){
        return this.location;
    }
    public boolean isClicked() {
        if(clicked){
            this.clicked = false;
            return true;
        }
        return false;
    }
    public void light(Color color) {
        this.getChildren().remove(rec);
        this.rec.setFill(color);
        this.getChildren().add(rec);
    }
    public void unlight() {
        this.getChildren().remove(rec);
        this.rec.setFill(Color.TRANSPARENT);
        this.getChildren().add(rec);
    }
}