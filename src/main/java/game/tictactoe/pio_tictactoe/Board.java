package game.tictactoe.pio_tictactoe;

import javafx.scene.ImageCursor;
import javafx.scene.SnapshotParameters;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;

import java.util.Vector;

enum EndGameStatus
{
	CIRCLE_WIN,
	CROSS_WIN,
	TIE
}

public
class Board
{

	public static final int ROW_LENGTH = 3;
	public static final int COLUMN_LENGTH = 3;
	private final WinHandler winHandler;
	public Vector<Grid> grids = new Vector<>();
	CustomCircle circleCursor = new CustomCircle(GameInfo.CIRCLE_CURSOR_CENTER, GameInfo.CIRCLE_CURSOR_CENTER,
												 GameInfo.CIRCLE_CURSOR_RADIUS, GameInfo.CIRCLE_CURSOR_WIDTH);
	CursorMode cursorMode;
	private EndGameStatus winStatus = null;

	public final int UPPER_LEFT = 0;
	public final int UPPER_MID = 1;
	public final int UPPER_RIGHT = 2;
	public final int MIDDLE_LEFT = 3;
	public final int MIDDLE_MIDDLE = 4;
	public final int MIDDLE_RIGHT = 5;
	public final int LOWER_LEFT = 6;
	public final int LOWER_MIDDLE = 7;
	public final int LOWER_RIGHT = 8;

	public
	Board (AnchorPane boardGrid, CursorMode _cursorMode)
	{
		cursorMode = _cursorMode;
		winHandler = new WinHandler(boardGrid);
		for (int BoardRow = 0; BoardRow < COLUMN_LENGTH; BoardRow++)
		{
			for (int BoardColumn = 0; BoardColumn < ROW_LENGTH; BoardColumn++)
			{
				Grid grid = new Grid(BoardColumn, BoardRow, this, boardGrid);
				grids.add(grid);
                if( boardGrid != null )
                {
                    boardGrid.getChildren().add(grid);
                }

				GameInfo.setCurrentPlayer(PlayerType.Circle);
				SnapshotParameters snapShotparams = new SnapshotParameters();
				snapShotparams.setFill(Color.TRANSPARENT);

				WritableImage image = null;
                if( cursorMode == CursorMode.SHAPE_CURSOR )
                {
                    image = circleCursor.snapshot(snapShotparams, null);
                }
                if( boardGrid != null )
                {
                    boardGrid.setCursor(new ImageCursor(image, GameInfo.placedSize, GameInfo.placedSize));
                }
			}
		}
		GameInfo.gameBoard = this;
	}

	void paintSquares ()
	{
        if( GameInfo.getCurrentSector() == GameInfo.SECTOR_UNRESTRICTED )
        {
            for (Grid grid : grids)
            {
                if( !grid.isWinner() && !grid.isFull())
                {
                    grid.paintSquares();
                }
            }
        }
        else
        {
            grids.get(GameInfo.getCurrentSector()).paintSquares();
        }

	}

	void unpaintSquares ()
	{
		for (Grid grid : grids)
		{
			grid.unpaintSquares();
		}
	}

	public
	Grid getGrid (int gridNum)
	{
		return grids.get(gridNum);
	}

	public
	void checkWinStatus ()
	{
        for (int i = 0; i < ROW_LENGTH; i++)
        {
            this.checkColumn(i);
        }
        for (int i = 0; i < COLUMN_LENGTH; i++)
        {
            this.checkRow(i);
        }
		this.checkLeftUpDiagonal();
		this.checkRightUpDiagonal();

		this.checkTie();

		if( winStatus != null )
		{
			try
			{
				GameInfo.setStatus(winStatus);
				winHandler.switchToEndGameWindow(winStatus);
			} catch (Exception e)
			{
				e.printStackTrace();
			}
		}
	}

	void checkRow (int row)
	{
        if( winStatus != null )
        {
            return;
        }

		if( (grids.get(row * ROW_LENGTH).checkCross()
			 && grids.get(row * ROW_LENGTH + 1).checkCross()
			 && grids.get(row * ROW_LENGTH + 2).checkCross()) )
		{
			this.winStatus = EndGameStatus.CROSS_WIN;
		}
		else if( (grids.get(row * ROW_LENGTH).checkCircle()
				  && grids.get(row * ROW_LENGTH + 1).checkCircle()
				  && grids.get(row * ROW_LENGTH + 2).checkCircle()) )
		{
			this.winStatus = EndGameStatus.CIRCLE_WIN;
		}

	}

	void checkColumn (int col)
	{
        if( winStatus != null )
        {
            return;
        }

		if( (grids.get(col).checkCross() && grids.get(col + ROW_LENGTH).checkCross()
			 && grids.get(col + ROW_LENGTH * 2).checkCross()) )
		{
			this.winStatus = EndGameStatus.CROSS_WIN;
		}
		else if( (grids.get(col).checkCircle() && grids.get(col + ROW_LENGTH).checkCircle()
				  && grids.get(col + ROW_LENGTH * 2).checkCircle()) )
		{
			this.winStatus = EndGameStatus.CIRCLE_WIN;
		}
	}

	void checkLeftUpDiagonal ()
	{
        if( winStatus != null )
        {
            return;
        }

		if( (grids.get(UPPER_LEFT).checkCross() && grids.get(MIDDLE_MIDDLE).checkCross() && grids.get(LOWER_RIGHT).checkCross()) )
		{
			this.winStatus = EndGameStatus.CROSS_WIN;
		}
		else if( (grids.get(UPPER_LEFT).checkCircle() && grids.get(MIDDLE_MIDDLE).checkCircle() && grids.get(LOWER_RIGHT).checkCircle()) )
		{
			this.winStatus = EndGameStatus.CIRCLE_WIN;
		}
	}

	void checkRightUpDiagonal ()
	{
        if( winStatus != null )
        {
            return;
        }

		if( (grids.get(UPPER_RIGHT).checkCross() && grids.get(MIDDLE_MIDDLE).checkCross() && grids.get(LOWER_LEFT).checkCross()) )
		{
			this.winStatus = EndGameStatus.CROSS_WIN;
		}
		else if( (grids.get(UPPER_RIGHT).checkCircle() && grids.get(MIDDLE_MIDDLE).checkCircle() && grids.get(LOWER_LEFT).checkCircle()) )
		{
			this.winStatus = EndGameStatus.CIRCLE_WIN;
		}
	}

	void checkTie ()
	{
        if( winStatus != null )
        {
            return;
        }

		for (int i = 0; i < ROW_LENGTH * COLUMN_LENGTH; i++)
		{
			if( !grids.get(i).isFull() )
				return;
		}

		winStatus = EndGameStatus.TIE;
	}

	enum CursorMode
	{
		NO_CURSOR,
		SHAPE_CURSOR
	}

}
