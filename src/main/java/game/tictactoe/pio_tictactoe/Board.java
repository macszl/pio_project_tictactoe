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




    void paintSquares()
    {
        Grid temp= Grids.get(GameInfo.getCurrentSector());
        for(int i=0;i<temp.squares.size();i++)
        {
            temp.squares.get(i).paintSquare();
        }
    }

    void unpaintSquares()
    {
        for(int j=0;j<Grids.size();j++)
        {
            Grid temp= Grids.get(j);
            for(int i=0;i<temp.squares.size();i++)
            {
                temp.squares.get(i).unpaintSquare();
            }
        }
    }


    CursorMode cursorMode;
    public Board(AnchorPane BoardGrid, CursorMode _cursorMode)
    {
        cursorMode = _cursorMode;
        for(int BoardRow=0;BoardRow<3;BoardRow++)
        {
            for(int BoardColumn=0;BoardColumn<3;BoardColumn++)
            {
                Grid grid=new Grid(BoardColumn,BoardRow,this,BoardGrid);
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
