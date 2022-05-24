package game.tictactoe.pio_tictactoe;

import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;

public class Cross extends StackPane {

    private Line line1 = new Line();
    private Line line2 = new Line();

    public Line getLine1() {
        return line1;
    }

    public Line getLine2() {
        return line2;
    }
    public Cross(){
        line1.setStartX(GameInfo.placedSize-20);
        line1.setStartY(GameInfo.placedSize-20);
        line1.setEndX(GameInfo.placedSize+20);
        line1.setEndY(GameInfo.placedSize+20);
        line1.setStroke(Color.RED);
        line1.setStrokeWidth(8);
        line2.setStartX(GameInfo.placedSize-20);
        line2.setStartY(GameInfo.placedSize+20);
        line2.setEndX(GameInfo.placedSize+20);
        line2.setEndY(GameInfo.placedSize-20);
        line2.setStroke(Color.RED);
        line2.setStrokeWidth(8);
        this.getChildren().add(line1);
        this.getChildren().add(line2);
    }
}
