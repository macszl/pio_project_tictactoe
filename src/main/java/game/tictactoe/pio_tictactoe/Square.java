package game.tictactoe.pio_tictactoe;

import javafx.event.EventHandler;
import javafx.scene.ImageCursor;
import javafx.scene.SnapshotParameters;
import javafx.scene.image.Image;
import javafx.scene.image.WritableImage;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

import java.io.File;

public class Square extends StackPane{

    ImagePattern nextGrid = new ImagePattern(new Image(new File("color.png").toURI().toString()));
    Circle circle = new Circle(GameInfo.placedSize/2,GameInfo.placedSize/2,GameInfo.placedSize/2,Color.TRANSPARENT);
    Cross cross = new Cross();
    Rectangle rectangle;
    boolean empty=true;
    private AnchorPane BoardGrid;
    Grid parent;
    int x, y;

    Square(AnchorPane BoardGrid) {
        this.BoardGrid = BoardGrid;
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

    public boolean isAllowedToPlace()
    {
        if( GameInfo.getCurrentSector() == GameInfo.SECTOR_UNRESTRICTED ||
            parent.y * 3 + parent.x == GameInfo.getCurrentSector())
        {
            GameInfo.setCurrentSector(y * 3 + x);
            parent.parent.unpaintSquares();
            parent.parent.paintSquares();
            return true;
        }
        else {
            return false;
        }
    }

    public void paintSquare()
    {
        this.rectangle.setFill(nextGrid);
    }
    public void unpaintSquare()
    {
        this.rectangle.setFill(null);
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
