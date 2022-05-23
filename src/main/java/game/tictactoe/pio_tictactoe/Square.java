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
        this.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                if(empty) {
                    if(GameInfo.currentPlayer == PlayerType.O){
                        setO();
                        empty = false;
                        GameInfo.currentPlayer = PlayerType.X;
                    }
                    else{
                        setX();
                        empty = false;
                        GameInfo.currentPlayer = PlayerType.O;
                    }
                }
            }
        });
    }

    public void setO(){
        circle.setCenterX(GameInfo.placedSize/2);
        circle.setCenterY(GameInfo.placedSize/2);
        circle.setRadius(GameInfo.placedSize/2);
        this.getChildren().add(circle);
    }
    public void setX(){
        line1.setStartX(GameInfo.placedSize-1);
        line1.setStartY(GameInfo.placedSize-1);
        line1.setEndX(GameInfo.placedSize+1);
        line1.setEndY(GameInfo.placedSize+1);
        line2.setStartX(GameInfo.placedSize+1);
        line2.setStartY(GameInfo.placedSize+1);
        line2.setEndX(GameInfo.placedSize-1);
        line2.setEndY(GameInfo.placedSize-1);
        this.getChildren().add(line1);
        this.getChildren().add(line2);
    }

}
