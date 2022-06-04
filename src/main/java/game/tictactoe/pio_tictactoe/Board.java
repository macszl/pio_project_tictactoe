package game.tictactoe.pio_tictactoe;

import javafx.fxml.FXMLLoader;
import javafx.scene.ImageCursor;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.SnapshotParameters;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.util.Vector;

enum EndGameStatus
{
    CIRCLE_WIN,
    CROSS_WIN,
    TIE
}

public class Board {


    public Vector<Grid> grids = new Vector<>();
    CustomCircle circleCursor = new CustomCircle(GameInfo.CIRCLE_CURSOR_CENTER,GameInfo.CIRCLE_CURSOR_CENTER,GameInfo.CIRCLE_CURSOR_RADIUS,GameInfo.CIRCLE_CURSOR_WIDTH);

    enum CursorMode {
        NO_CURSOR,
        SHAPE_CURSOR
    }

    private EndGameStatus winStatus = null;
    
    void paintSquares()
    {
        if(GameInfo.getCurrentSector()==GameInfo.SECTOR_UNRESTRICTED)
        {
            for(int i = 0; i< grids.size(); i++)
            {
                if(!grids.get(i).getWinner())
                    grids.get(i).paintSquares();
            }
        }
        else
            grids.get(GameInfo.getCurrentSector()).paintSquares();

    }

    void unpaintSquares()
    {
        for(int j = 0; j< grids.size(); j++)
        {
            grids.get(j).unpaintSquares();
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
                grids.add(grid);
                if(BoardGrid != null)BoardGrid.getChildren().add(grid);

                GameInfo.setCurrentPlayer(PlayerType.Circle);
                SnapshotParameters snapShotparams = new SnapshotParameters();
                snapShotparams.setFill(Color.TRANSPARENT);

                WritableImage image = null;
                if(cursorMode == CursorMode.SHAPE_CURSOR )
                    image = circleCursor.snapshot(snapShotparams, null);
                if(BoardGrid != null )
                    BoardGrid.setCursor(new ImageCursor(image, GameInfo.placedSize, GameInfo.placedSize));
            }
        }
        GameInfo.gameBoard=this;
    }

    public Grid getGrid(int gridNum)
    {
        return grids.get(gridNum);
    }

    public void checkWinStatus()
    {
        for(int i = 0; i < 3; i++)
            this.checkColumn(i);
        for(int i = 0; i < 3; i++)
            this.checkRow(i);
        this.checkLeftUpDiagonal();
        this.checkRightUpDiagonal();

        this.checkTie();

        if(winStatus != null)
        {
            GridController.switchToEndGameWindow(winStatus);
        }
    }

    void checkRow(int row)
    {
        if(winStatus != null)
            return;

        if( (grids.get(row * 3).checkCross()  && grids.get(row * 3 + 1).checkCross()  && grids.get(row * 3 + 2).checkCross() ))
        {
            this.winStatus = EndGameStatus.CROSS_WIN;
        }
        else if( (grids.get(row * 3).checkCircle() && grids.get(row * 3 + 1).checkCircle() && grids.get(row * 3 + 2).checkCircle() ))
        {
            this.winStatus = EndGameStatus.CIRCLE_WIN;
        }

    }

    void checkColumn(int col)
    {
        if(winStatus != null)
            return;

        if( (grids.get(col).checkCross()  && grids.get(col + 3).checkCross()  && grids.get(col + 6).checkCross() ))
        {
            this.winStatus = EndGameStatus.CROSS_WIN;
        }
        else if( (grids.get(col).checkCircle() && grids.get(col + 3).checkCircle() && grids.get(col + 6).checkCircle() ))
        {
            this.winStatus = EndGameStatus.CIRCLE_WIN;
        }
    }

    void checkLeftUpDiagonal()
    {
        if(winStatus != null)
            return;

        if( (grids.get(0).checkCross()  && grids.get(4).checkCross()  && grids.get(8).checkCross() ))
        {
            this.winStatus = EndGameStatus.CROSS_WIN;
        }
        else if( (grids.get(0).checkCircle() && grids.get(4).checkCircle() && grids.get(8).checkCircle() ))
        {
            this.winStatus = EndGameStatus.CIRCLE_WIN;
        }
    }
    void checkRightUpDiagonal()
    {
        if(winStatus != null)
            return;

        if( (grids.get(2).checkCross()  && grids.get(4).checkCross()  && grids.get(6).checkCross() ))
        {
            this.winStatus = EndGameStatus.CROSS_WIN;
        }
        else if((grids.get(2).checkCircle() && grids.get(4).checkCircle() && grids.get(6).checkCircle() ))
        {
            this.winStatus = EndGameStatus.CIRCLE_WIN;
        }
    }

    boolean checkTie ()
    {
        if(winStatus != null)
            return false;

        for(int i = 0; i < 9; i++)
        {
            if( !grids.get(i).isFull())
                return false;
        }
        
        return true;
    }
}
