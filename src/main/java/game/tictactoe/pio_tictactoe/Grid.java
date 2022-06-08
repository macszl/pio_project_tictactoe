package game.tictactoe.pio_tictactoe;

import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;

import java.util.Vector;

public
class Grid extends AnchorPane
{

	private final int x;
	private final int y;
	private final CustomCircle circle = new CustomCircle(GameInfo.BIG_CIRCLE_COORDINATES,
														 GameInfo.BIG_CIRCLE_COORDINATES, GameInfo.BIG_CIRCLE_RADIUS,
														 GameInfo.BIG_CIRCLE_WIDTH);
	private final Cross cross = new Cross(GameInfo.BIG_CROSS_WIDTH, GameInfo.BIG_CROSS_SIZE, GameInfo.BIG_CROSS_SIZE,
										  GameInfo.BIG_CROSS_COORDINATES, GameInfo.BIG_CROSS_COORDINATES);
	public Board parent;
	Vector<Square> squares = new Vector<>();
	private boolean winner;
	private PlayerType playerType = null;

	public final int ROW_LENGTH = 3;
	public final int COLUMN_LENGTH = 3;

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
	Grid (int boardColumn, int boardRow, Board _parent, AnchorPane boardGrid)
	{
		this.x = boardColumn;
		this.y = boardRow;
		this.parent = _parent;
		this.resize(GameInfo.getGridSize(), GameInfo.getGridSize());
		this.setLayoutX(GameInfo.getGridSize() * boardColumn);
		this.setLayoutY(GameInfo.getGridSize() * boardRow);
		this.winner = false;
		for (int gridRow = 0; gridRow < COLUMN_LENGTH; gridRow++)
		{
			for (int gridColumn = 0; gridColumn < ROW_LENGTH; gridColumn++)
			{
				Square square = new Square(boardGrid, gridColumn, gridRow);
				this.getChildren().add(square);
				this.squares.add(square);
				square.parent = this;
			}
		}
	}

	public
	int getX ()
	{
		return x;
	}

	public
	int getY ()
	{
		return y;
	}

	void paintSquares ()
	{
		for (Square square : this.squares)
		{
			square.paintSquare();
		}
	}

	void unpaintSquares ()
	{
		for (Square square : this.squares)
		{
			square.unpaintSquare();
		}
	}

	boolean isWinner ()
	{
		return this.winner;
	}

	void checkWinCondition ()
	{
        if( winner )
        {
            return;
        }

        for (int i = 0; i < ROW_LENGTH; i++)
        {
            this.checkColumn(i,PlayerType.Cross, cross);
			this.checkColumn(i, PlayerType.Circle, circle);
        }
        for (int i = 0; i < COLUMN_LENGTH; i++)
        {
            this.checkRow(i, PlayerType.Cross, cross);
			this.checkRow(i, PlayerType.Circle, circle);
        }

		this.checkLeftUpDiagonal(PlayerType.Cross, cross);
		this.checkLeftUpDiagonal(PlayerType.Circle, circle);
		this.checkRightUpDiagonal(PlayerType.Cross, cross);
		this.checkRightUpDiagonal(PlayerType.Circle, circle);

		parent.checkWinStatus();
	}

	void checkRow (int row, PlayerType type, StackPane obj)
	{
        if( winner )
        {
            return;
        }

		if(    (squares.get(row * ROW_LENGTH).checkPlayerType(type)
			 && squares.get(row * ROW_LENGTH + 1).checkPlayerType(type)
			 && squares.get(row * ROW_LENGTH + 2).checkPlayerType(type)) )
		{
			this.getChildren().add(obj);
			this.winner = true;
			this.playerType = type;
		}

	}

	void checkColumn (int col, PlayerType type, StackPane obj)
	{
        if( winner )
        {
            return;
        }

		if( (squares.get(col).checkPlayerType(type) && squares.get(col + ROW_LENGTH).checkPlayerType(type)
			 && squares.get(col + ROW_LENGTH * 2).checkPlayerType(type)) )
		{
			this.getChildren().add(obj);
			this.winner = true;
			this.playerType = type;
		}
	}

	void checkLeftUpDiagonal (PlayerType type, StackPane obj)
	{
        if( winner )
        {
            return;
        }

		if( (squares.get(UPPER_LEFT).checkPlayerType(type) &&
			 squares.get(MIDDLE_MIDDLE).checkPlayerType(type) &&
			 squares.get(LOWER_RIGHT).checkPlayerType(type)) )
		{
			this.getChildren().add(obj);
			this.winner = true;
			this.playerType = type;
		}
	}

	void checkRightUpDiagonal (PlayerType type, StackPane obj)
	{
        if( winner )
		{
			return;
		}

		if( (squares.get(UPPER_RIGHT).checkPlayerType(type) &&
			 squares.get(MIDDLE_MIDDLE).checkPlayerType(type) &&
			 squares.get(LOWER_LEFT).checkPlayerType(type)) )
		{
			this.getChildren().add(obj);
			this.winner = true;
			this.playerType = type;
		}
	}

	boolean isFull ()
	{
		if(winner)
			return true;

		for (int i = 0; i < ROW_LENGTH * COLUMN_LENGTH; i++)
		{
            if( squares.get(i).empty )
            {
                return false;
            }
		}
		return true;
	}

	boolean checkCross ()
	{
		return this.winner && this.playerType == PlayerType.Cross;
	}

	boolean checkCircle ()
	{
		return this.winner && this.playerType == PlayerType.Circle;
	}

	boolean checkPlayerType(PlayerType type)
	{
		if( type == PlayerType.Circle)
		{
			return checkCircle();
		}
		else {
			return checkCross();
		}
	}

}
