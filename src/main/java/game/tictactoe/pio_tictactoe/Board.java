package game.tictactoe.pio_tictactoe;

import javafx.scene.ImageCursor;
import javafx.scene.SnapshotParameters;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

import java.util.Vector;

public class Board {
    public Vector<Grid> Grids= new Vector<>();

    Circle circleCursor = new Circle(GameInfo.placedSize,GameInfo.placedSize/4,GameInfo.placedSize/4,Color.TRANSPARENT);

    enum CursorMode {
        NO_CURSOR,
        SHAPE_CURSOR
    }

    private Rectangle createRectangle()
    {
        Rectangle r = new Rectangle(90,90);
        r.setFill(Color.TRANSPARENT);
        r.setStroke(Color.BLACK);
        r.setStrokeWidth(5);
        return r;
    }


    CursorMode cursorMode;
    public Board(AnchorPane BoardGrid, CursorMode _cursorMode)
    {
        cursorMode = _cursorMode;
        for(int BoardRow=0;BoardRow<3;BoardRow++)
        {
            for(int BoardColumn=0;BoardColumn<3;BoardColumn++)
            {
                Grid grid=new Grid(BoardColumn,BoardRow,this);
                for(int GridRow=0;GridRow<3;GridRow++)
                {
                    for(int GridColumn=0;GridColumn<3;GridColumn++)
                    {
                        Rectangle r = createRectangle();
                        Square square = new Square(BoardGrid,GridColumn,GridRow);
                        square.getChildren().add(r);
                        grid.getChildren().add(square);
                        grid.squares.add(square);
                        square.parent = grid;
                    }
                }
                Grids.add(grid);
                if(BoardGrid != null)BoardGrid.getChildren().add(grid);
                GameInfo.currentPlayer = PlayerType.Circle;
                SnapshotParameters snapShotparams = new SnapshotParameters();
                snapShotparams.setFill(Color.TRANSPARENT);
                circleCursor.setStroke(Color.BLUE);
                circleCursor.setStrokeWidth(4);

                WritableImage image = null;
                if(cursorMode == CursorMode.SHAPE_CURSOR )
                    image = circleCursor.snapshot(snapShotparams, null);
                if(BoardGrid != null )
                    BoardGrid.setCursor(new ImageCursor(image, GameInfo.placedSize, GameInfo.placedSize));
            }
        }
        GameInfo.gameBoard=this;
    }
}
