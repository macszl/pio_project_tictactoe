package game.tictactoe.pio_tictactoe;

import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class CustomCircle extends StackPane {

    private Circle circle;

    public CustomCircle(double x, double y, double radius, double strokeWidth){
        this.circle = new Circle(x,y,radius, Color.TRANSPARENT);
        this.circle.setStroke(Color.BLUE);
        this.circle.setStrokeWidth(strokeWidth);
        this.getChildren().add(this.circle);
    }

}
