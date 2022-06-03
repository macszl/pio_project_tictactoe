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
    public Cross(int strokeWidth, int size1, int size2){
        line1.setStartX(GameInfo.placedSize-size1);
        line1.setStartY(GameInfo.placedSize-size2);
        line1.setEndX(GameInfo.placedSize+size1);
        line1.setEndY(GameInfo.placedSize+size2);
        line1.setStroke(Color.RED);
        line1.setStrokeWidth(strokeWidth);
        line2.setStartX(GameInfo.placedSize-size1);
        line2.setStartY(GameInfo.placedSize+size2);
        line2.setEndX(GameInfo.placedSize+size1);
        line2.setEndY(GameInfo.placedSize-size2);
        line2.setStroke(Color.RED);
        line2.setStrokeWidth(strokeWidth);
        this.getChildren().add(line1);
        this.getChildren().add(line2);
    }
}
