package game.tictactoe.pio_tictactoe;

import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;

public class Cross extends StackPane {

    private Line line1 = new Line();
    private Line line2 = new Line();

    public Cross(int strokeWidth, int size1, int size2){
        line1.setStartX(-size1);
        line1.setStartY(-size2);
        line1.setEndX(size1);
        line1.setEndY(size2);
        line1.setStroke(Color.RED);
        line1.setStrokeWidth(strokeWidth);
        line2.setStartX(-size1);
        line2.setStartY(size2);
        line2.setEndX(size1);
        line2.setEndY(-size2);
        line2.setStroke(Color.RED);
        line2.setStrokeWidth(strokeWidth);
        this.getChildren().add(line1);
        this.getChildren().add(line2);
    }
}
