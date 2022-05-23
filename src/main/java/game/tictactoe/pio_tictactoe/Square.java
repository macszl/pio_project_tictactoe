package game.tictactoe.pio_tictactoe;

import javafx.event.EventHandler;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;

public class Square extends StackPane {

    Circle circle = new Circle();
    Line line1 = new Line();
    Line line2 = new Line();
    boolean empty=true;
    int poz;

    Grid belongsTo;

    Square() {
        placed.setFitWidth(GameInfo.placedSize);
        placed.setFitHeight(GameInfo.placedSize);
        this.getChildren().add(placed);
        this.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                if(empty)
                {
                        setCircle();
                }

            }

        });
    }


    public void setCircle()
    {
        placed.setImage(Images.circle);
    }
    public void setX()
    {
        placed.setImage(Images.X);
    }

}
