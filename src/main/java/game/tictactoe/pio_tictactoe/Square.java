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

    CustomCircle circle = new CustomCircle(GameInfo.CIRCLE_CENTER,GameInfo.CIRCLE_CENTER,GameInfo.CIRCLE_RADIUS,GameInfo.CIRCLE_WIDTH);
    Cross cross = new Cross(GameInfo.CROSS_WIDTH, GameInfo.CROSS_SIZE, GameInfo.CROSS_SIZE);
    Cross crossCursor = new Cross(GameInfo.CROSS_CURSOR_WIDTH,GameInfo.CROSS_CURSOR_SIZE,GameInfo.CROSS_CURSOR_SIZE);
    CustomCircle circleCursor = new CustomCircle(GameInfo.CIRCLE_CURSOR_CENTER,GameInfo.CIRCLE_CURSOR_CENTER,GameInfo.CIRCLE_CURSOR_RADIUS,GameInfo.CIRCLE_CURSOR_WIDTH);

    boolean empty=true;
    private AnchorPane boardGrid;

    Grid parent;
    int x, y;
    private final Rectangle rectangle;

    private Rectangle createRectangle()
    {
        Rectangle r = new Rectangle(GameInfo.getSquareSize(),GameInfo.getSquareSize());
        r.setFill(Color.TRANSPARENT);
        r.setStroke(Color.BLACK);
        r.setStrokeWidth(5);
        return r;
    }

    Square(AnchorPane BoardGrid,int x, int y) {
        this.boardGrid = BoardGrid;
        this.y = y;
        this.x = x;
        this.rectangle=createRectangle();
        this.getChildren().add(rectangle);
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

        this.getChildren().add(circle);
    }
    public void setCross(){
        this.getChildren().add(cross);
    }

    public void changeCursor(){
        SnapshotParameters snapShotparams = new SnapshotParameters();
        snapShotparams.setFill(Color.TRANSPARENT);
        if(GameInfo.getCurrentPlayer() == PlayerType.Circle){
            WritableImage image = circleCursor.snapshot(snapShotparams, null);
            boardGrid.setCursor(new ImageCursor(image, GameInfo.placedSize, GameInfo.placedSize));
        }
        else{
            WritableImage image = crossCursor.snapshot(snapShotparams, null);
            boardGrid.setCursor(new ImageCursor(image, GameInfo.placedSize, GameInfo.placedSize));
        }
    }

    public boolean isAllowedToPlace()
    {
        if( GameInfo.getCurrentSector() == GameInfo.SECTOR_UNRESTRICTED ||
            parent.y * 3 + parent.x == GameInfo.getCurrentSector())
        {
            GameInfo.setCurrentSector(y * 3 + x);
            GameInfo.gameBoard.unpaintSquares();
            GameInfo.gameBoard.paintSquares();
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
            if(GameInfo.getCurrentPlayer() == PlayerType.Circle){
                setCircle();
                empty = false;
                GameInfo.setCurrentPlayer(PlayerType.Cross);
                if(this.parent.parent.cursorMode == Board.CursorMode.SHAPE_CURSOR )
                    changeCursor();
            }
            else{
                setCross();
                empty = false;
                GameInfo.setCurrentPlayer(PlayerType.Circle);
                if(this.parent.parent.cursorMode == Board.CursorMode.SHAPE_CURSOR )
                    changeCursor();
            }
        }
    }
}
