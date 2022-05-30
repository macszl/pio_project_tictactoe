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

    static Circle circle = new Circle(GameInfo.placedSize/2,GameInfo.placedSize/2,GameInfo.placedSize/2, Color.TRANSPARENT);

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
        for(int i=0;i<3;i++)
        {
            for(int j=0;j<3;j++)
            {
                Grid temp=new Grid();
                temp.x = j;
                temp.y = i;
                temp.parent = this;
                for(int k=0;k<3;k++)
                {
                    for(int l=0;l<3;l++)
                    {
                        Rectangle r = new Rectangle(90,90);
                        r.setFill(Color.TRANSPARENT);
                        r.setStroke(Color.BLACK);
                        r.setStrokeWidth(5);
                        Square temp2 = new Square(BoardGrid);
                        temp2.y = k;
                        temp2.x = l;
                        temp2.getChildren().add(r);
                        temp2.rectangle=r;
                        temp2.resize(90,90);
                        temp.getChildren().add(temp2);
                        temp.squares.add(temp2);
                        temp2.setLayoutX(2+90*k);
                        temp2.setLayoutY(l*90+1);
                        temp2.parent =temp;
                    }
                }
                Grids.add(temp);
                if(BoardGrid != null)BoardGrid.getChildren().add(temp);
                temp.resize(280,280);
                temp.setLayoutX(280*i);
                temp.setLayoutY(280*j);
                GameInfo.currentPlayer = PlayerType.Circle;
                SnapshotParameters snapShotparams = new SnapshotParameters();
                snapShotparams.setFill(Color.TRANSPARENT);
                circle.setStroke(Color.BLUE);
                circle.setStrokeWidth(7);

                WritableImage image = null;
                if(cursorMode == CursorMode.SHAPE_CURSOR )
                    image = circle.snapshot(snapShotparams, null);
                if(BoardGrid != null )
                    BoardGrid.setCursor(new ImageCursor(image, GameInfo.placedSize, GameInfo.placedSize));
            }
        }
    }
}
