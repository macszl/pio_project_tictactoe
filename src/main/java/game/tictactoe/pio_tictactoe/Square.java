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
    Cross cross = new Cross(8,20,20);
    Cross crossCursor = new Cross(4,10,10);
    Circle circleCursor = new Circle(GameInfo.placedSize,GameInfo.placedSize,GameInfo.placedSize/4,Color.TRANSPARENT);
    boolean empty=true;
    private AnchorPane BoardGrid;
    Grid parent;
    int x, y;


    Square(AnchorPane BoardGrid,int x, int y) {
        this.BoardGrid = BoardGrid;
        this.y = y;
        this.x = x;
        this.resize(GameInfo.getSquareSize(),GameInfo.getSquareSize());
        this.setLayoutX(2+GameInfo.getSquareSize()*x);
        this.setLayoutY(y*GameInfo.getSquareSize()+1);
        this.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                onMouseClickEvent();
            }
        });
    }

    public void setCircle(){
        circle.setStroke(Color.BLUE);
        circle.setStrokeWidth(7);
        this.getChildren().add(circle);
    }
    public void setCross(){
        this.getChildren().add(cross);
    }

    public void changeCursor(){
        SnapshotParameters snapShotparams = new SnapshotParameters();
        snapShotparams.setFill(Color.TRANSPARENT);
        if(GameInfo.currentPlayer == PlayerType.Circle){
            circleCursor.setStroke(Color.BLUE);
            circleCursor.setStrokeWidth(4);
            WritableImage image = circleCursor.snapshot(snapShotparams, null);
            BoardGrid.setCursor(new ImageCursor(image, GameInfo.placedSize, GameInfo.placedSize));
        }
        else{
            WritableImage image = crossCursor.snapshot(snapShotparams, null);
            BoardGrid.setCursor(new ImageCursor(image, GameInfo.placedSize, GameInfo.placedSize));
        }
    }

    public boolean isAllowedToPlace()
    {
        if( GameInfo.getCurrentSector() == GameInfo.SECTOR_UNRESTRICTED ||
            parent.y * 3 + parent.x == GameInfo.getCurrentSector())
        {
            GameInfo.setCurrentSector(y * 3 + x);
            return true;
        }
        else {
            return false;
        }
    }

    public void onMouseClickEvent() {
        if(empty && isAllowedToPlace()) {
            if(GameInfo.currentPlayer == PlayerType.Circle){
                setCircle();
                empty = false;
                GameInfo.currentPlayer = PlayerType.Cross;
                if(this.parent.parent.cursorMode == Board.CursorMode.SHAPE_CURSOR )
                    changeCursor();
            }
            else{
                setCross();
                empty = false;
                GameInfo.currentPlayer = PlayerType.Circle;
                if(this.parent.parent.cursorMode == Board.CursorMode.SHAPE_CURSOR )
                    changeCursor();
            }
        }
    }
}
