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

    ImagePattern nextGrid;
    Circle circle = new Circle(GameInfo.placedSize/2,GameInfo.placedSize/2,GameInfo.placedSize/2,Color.TRANSPARENT);
    Cross cross = new Cross(8,20,20);


    Cross crossCursor = new Cross(4,10,10);
    Circle circleCursor = new Circle(GameInfo.placedSize,GameInfo.placedSize,GameInfo.placedSize/4,Color.TRANSPARENT);

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
        if(boardGrid != null)
            this.nextGrid = new ImagePattern(new Image(new File("color.png").toURI().toString()));
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
        circle.setStroke(Color.BLUE);
        circle.setStrokeWidth(7);
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
        if(GameInfo.currentPlayer == PlayerType.Circle){
            circleCursor.setStroke(Color.BLUE);
            circleCursor.setStrokeWidth(4);
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
            parent.y * 3 + parent.x == GameInfo.getCurrentSector() && !parent.getWinner())
        {
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
        if(empty && isAllowedToPlace())
        {
            if(GameInfo.currentPlayer == PlayerType.Circle)
            {
                setCircle();
                parent.checkWinCondition();
                empty = false;
                GameInfo.currentPlayer = PlayerType.Cross;
                if(this.parent.parent.cursorMode == Board.CursorMode.SHAPE_CURSOR )
                    changeCursor();

            }
            else
            {
                setCross();
                parent.checkWinCondition();
                empty = false;
                GameInfo.currentPlayer = PlayerType.Circle;
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
