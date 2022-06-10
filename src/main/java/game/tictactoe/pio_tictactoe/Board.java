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
	public final int UPPER_LEFT = 0;
	public final int UPPER_MID = 1;
	public final int UPPER_RIGHT = 2;
	public final int MIDDLE_LEFT = 3;
	public final int MIDDLE_MIDDLE = 4;
	public final int MIDDLE_RIGHT = 5;
	public final int LOWER_LEFT = 6;
	public final int LOWER_MIDDLE = 7;
	public final int LOWER_RIGHT = 8;
	private final WinHandler winHandler;
	public Vector<Grid> grids = new Vector<>();
	CustomCircle circleCursor = new CustomCircle(GameInfo.CIRCLE_CURSOR_CENTER, GameInfo.CIRCLE_CURSOR_CENTER,
												 GameInfo.CIRCLE_CURSOR_RADIUS, GameInfo.CIRCLE_CURSOR_WIDTH);
	CursorMode cursorMode;
	private EndGameStatus winStatus = null;

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
				if( !grid.isWinner() && !grid.isFull() )
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
			this.checkColumn(i, PlayerType.Circle, EndGameStatus.CIRCLE_WIN);
			this.checkColumn(i, PlayerType.Cross, EndGameStatus.CROSS_WIN);
		}
		for (int i = 0; i < COLUMN_LENGTH; i++)
		{
			this.checkRow(i, PlayerType.Circle, EndGameStatus.CIRCLE_WIN);
			this.checkRow(i, PlayerType.Cross, EndGameStatus.CROSS_WIN);
		}

		this.checkLeftUpDiagonal(PlayerType.Circle, EndGameStatus.CIRCLE_WIN);
		this.checkLeftUpDiagonal(PlayerType.Cross, EndGameStatus.CROSS_WIN);
		this.checkRightUpDiagonal(PlayerType.Cross, EndGameStatus.CROSS_WIN);
		this.checkRightUpDiagonal(PlayerType.Circle, EndGameStatus.CIRCLE_WIN);

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

	void checkRow (int row, PlayerType type, EndGameStatus status)
	{
		if( winStatus != null )
		{
			return;
		}

		if( (grids.get(row * ROW_LENGTH).checkPlayerType(type)
			 && grids.get(row * ROW_LENGTH + 1).checkPlayerType(type)
			 && grids.get(row * ROW_LENGTH + 2).checkPlayerType(type)) )
		{
			this.winStatus = status;
		}

	}

	void checkColumn (int col, PlayerType type, EndGameStatus status)
	{
		if( winStatus != null )
		{
			return;
		}

		if( (grids.get(col).checkPlayerType(type) && grids.get(col + ROW_LENGTH).checkPlayerType(type)
			 && grids.get(col + ROW_LENGTH * 2).checkPlayerType(type)) )
		{
			this.winStatus = status;
		}
	}

	void checkLeftUpDiagonal (PlayerType type, EndGameStatus status)
	{
		if( winStatus != null )
		{
			return;
		}

		if( (grids.get(UPPER_LEFT).checkPlayerType(type) && grids.get(MIDDLE_MIDDLE).checkPlayerType(type)
			 && grids.get(LOWER_RIGHT).checkPlayerType(type)) )
		{
			this.winStatus = status;
		}
	}

	void checkRightUpDiagonal (PlayerType type, EndGameStatus status)
	{
		if( winStatus != null )
		{
			return;
		}

		if( (grids.get(UPPER_RIGHT).checkPlayerType(type) && grids.get(MIDDLE_MIDDLE).checkPlayerType(type)
			 && grids.get(LOWER_LEFT).checkPlayerType(type)) )
		{
			this.winStatus = status;
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
			{
				return;
			}
		}

		winStatus = EndGameStatus.TIE;
	}

	enum CursorMode
	{
		NO_CURSOR,
		SHAPE_CURSOR
	}

}
