package game.tictactoe.pio_tictactoe;

import javafx.event.EventHandler;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;

public class Square extends StackPane {

    ImageView placed = new ImageView();
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
