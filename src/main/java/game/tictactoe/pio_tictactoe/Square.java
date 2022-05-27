package game.tictactoe.pio_tictactoe;

import javafx.event.EventHandler;
import javafx.scene.ImageCursor;
import javafx.scene.SnapshotParameters;
import javafx.scene.image.WritableImage;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class Square extends StackPane{

    Circle circle = new Circle(GameInfo.placedSize/2,GameInfo.placedSize/2,GameInfo.placedSize/2,Color.TRANSPARENT);
    Cross cross = new Cross();
    boolean empty=true;
    private AnchorPane BoardGrid;
    Grid belongsTo;

    Square(AnchorPane BoardGrid) {
        this.BoardGrid = BoardGrid;
        this.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                if(empty) {
                    if(GameInfo.currentPlayer == PlayerType.O){
                        setO();
                        empty = false;
                        GameInfo.currentPlayer = PlayerType.X;
                        changeCursor();
                    }
                    else{
                        setX();
                        empty = false;
                        GameInfo.currentPlayer = PlayerType.O;
                        changeCursor();
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

    public void changeCursor(){
        SnapshotParameters snapShotparams = new SnapshotParameters();
        snapShotparams.setFill(Color.TRANSPARENT);
        if(GameInfo.currentPlayer == PlayerType.O){
            circle.setStroke(Color.BLUE);
            circle.setStrokeWidth(7);
            WritableImage image = circle.snapshot(snapShotparams, null);
            BoardGrid.setCursor(new ImageCursor(image, GameInfo.placedSize, GameInfo.placedSize));
        }
        else{
            WritableImage image = cross.snapshot(snapShotparams, null);
            BoardGrid.setCursor(new ImageCursor(image, GameInfo.placedSize, GameInfo.placedSize));
        }
    }

}
