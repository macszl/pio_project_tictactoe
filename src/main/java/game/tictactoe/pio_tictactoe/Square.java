package game.tictactoe.pio_tictactoe;

import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class Square extends StackPane{

    Circle circle = new Circle(GameInfo.placedSize/2,GameInfo.placedSize/2,GameInfo.placedSize/2,Color.TRANSPARENT);
    Cross cross = new Cross();
    boolean empty=true;

    Grid belongsTo;

    Square() {
        this.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                if(empty) {
                    if(GameInfo.currentPlayer == PlayerType.O){
                        setO();
                        empty = false;
                        GameInfo.currentPlayer = PlayerType.X;
                        //GridController.changeCursor();
                    }
                    else{
                        setX();
                        empty = false;
                        GameInfo.currentPlayer = PlayerType.O;
                        //GridController.changeCursor();
                    }
                }
            }
        });
    }

    public void setO(){
        circle.setStroke(Color.BLUE);
        circle.setStrokeWidth(7);
        this.getChildren().add(circle);
    }
    public void setX(){
        this.getChildren().add(cross);
    }

}
