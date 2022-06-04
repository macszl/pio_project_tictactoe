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
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

import java.io.File;

public class Square extends StackPane{
    
    CustomCircle circle = new CustomCircle(GameInfo.CIRCLE_CENTER,GameInfo.CIRCLE_CENTER,GameInfo.CIRCLE_RADIUS,GameInfo.CIRCLE_WIDTH);
    Cross cross = new Cross(GameInfo.CROSS_WIDTH, GameInfo.CROSS_SIZE, GameInfo.CROSS_SIZE);
    Cross crossCursor = new Cross(GameInfo.CROSS_CURSOR_WIDTH,GameInfo.CROSS_CURSOR_SIZE,GameInfo.CROSS_CURSOR_SIZE);
    CustomCircle circleCursor = new CustomCircle(GameInfo.CIRCLE_CURSOR_CENTER,GameInfo.CIRCLE_CURSOR_CENTER,GameInfo.CIRCLE_CURSOR_RADIUS,GameInfo.CIRCLE_CURSOR_WIDTH);

    boolean empty=true;
    private final AnchorPane boardGrid;

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
        this.setOnMouseClicked(new EventHandler<>()
        {
            @Override
            public void handle (MouseEvent mouseEvent)
            {
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

    public boolean checkCross()
    {
        if ( this.getChildren().size() == 2)
        {
            if(this.getChildren().get(1).getClass() == Cross.class)
                return true;
        }
        return false;
    }

    public boolean checkCircle()
    {
        if ( this.getChildren().size() == 2)
        {
            if(this.getChildren().get(1).getClass() == Circle.class)
                return true;
        }
        return false;
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
        if(( GameInfo.getCurrentSector() == GameInfo.SECTOR_UNRESTRICTED && !parent.getWinner())||
           (parent.y * 3 + parent.x == GameInfo.getCurrentSector() && !parent.getWinner()))
        {
            return true;
        }
        else {
            return false;
        }
    }

    public void paintSquare()
    {
        this.rectangle.setFill(Paint.valueOf("cdffa6"));
    }
    public void unpaintSquare()
    {
        this.rectangle.setFill(null);
    }

    public void onMouseClickEvent() {
        if(empty && isAllowedToPlace()) {
            if(GameInfo.getCurrentPlayer() == PlayerType.Circle){
                setCircle();
                parent.checkWinCondition();
                empty = false;
                GameInfo.setCurrentPlayer(PlayerType.Cross);
                if(this.parent.parent.cursorMode == Board.CursorMode.SHAPE_CURSOR )
                    changeCursor();

            }
            else
            {
                setCross();
                parent.checkWinCondition();
                empty = false;
                GameInfo.setCurrentPlayer(PlayerType.Circle);
                if(this.parent.parent.cursorMode == Board.CursorMode.SHAPE_CURSOR )
                    changeCursor();
            }

            if( !parent.parent.getGrid(y * 3 + x).getWinner() && !parent.isFull())
            {
                GameInfo.setCurrentSector(y * 3 + x);
            }
            else
            {
                GameInfo.setCurrentSector(GameInfo.SECTOR_UNRESTRICTED);
            }

            GameInfo.gameBoard.unpaintSquares();
            GameInfo.gameBoard.paintSquares();
        }
    }
}
