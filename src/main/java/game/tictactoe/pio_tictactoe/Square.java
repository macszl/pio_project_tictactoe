package game.tictactoe.pio_tictactoe;

import javafx.event.EventHandler;
import javafx.scene.ImageCursor;
import javafx.scene.SnapshotParameters;
import javafx.scene.image.WritableImage;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;

public
class Square extends StackPane
{

	private final CustomCircle circle = new CustomCircle(GameInfo.CIRCLE_CENTER, GameInfo.CIRCLE_CENTER,
														 GameInfo.CIRCLE_RADIUS, GameInfo.CIRCLE_WIDTH);
	private final Cross cross = new Cross(GameInfo.CROSS_WIDTH, GameInfo.CROSS_SIZE, GameInfo.CROSS_SIZE);
	private final Cross crossCursor = new Cross(GameInfo.CROSS_CURSOR_WIDTH, GameInfo.CROSS_CURSOR_SIZE,
												GameInfo.CROSS_CURSOR_SIZE);
	private final CustomCircle circleCursor = new CustomCircle(GameInfo.CIRCLE_CURSOR_CENTER,
															   GameInfo.CIRCLE_CURSOR_CENTER,
															   GameInfo.CIRCLE_CURSOR_RADIUS,
															   GameInfo.CIRCLE_CURSOR_WIDTH);
	private final AnchorPane boardGrid;
	private final Rectangle rectangle;
	boolean empty = true;
	Grid parent;
	private final int x;
    private final int y;

	Square (AnchorPane BoardGrid, int x, int y)
	{
		this.boardGrid = BoardGrid;
		this.y = y;
		this.x = x;
		this.rectangle = createRectangle();
		this.getChildren().add(rectangle);
		this.resize(GameInfo.getSquareSize(), GameInfo.getSquareSize());
		this.setLayoutX(2 + GameInfo.getSquareSize() * x);
		this.setLayoutY(y * GameInfo.getSquareSize() + 1);
		this.setOnMouseClicked(new EventHandler<>()
		{
			@Override
			public
			void handle (MouseEvent mouseEvent)
			{
				onMouseClickEvent();
			}
		});
	}

	private
	Rectangle createRectangle ()
	{
		Rectangle r = new Rectangle(GameInfo.getSquareSize(), GameInfo.getSquareSize());
		r.setFill(Color.TRANSPARENT);
		r.setStroke(Color.BLACK);
		r.setStrokeWidth(5);
		return r;
	}

	public
	void setCircle ()
	{

		this.getChildren().add(circle);
	}

	public
	void setCross ()
	{
		this.getChildren().add(cross);
	}

	public
	boolean checkCross ()
	{
		if( this.getChildren().size() == 2 )
		{
            return this.getChildren().get(1).getClass() == Cross.class;
		}
		return false;
	}

	public
	boolean checkCircle ()
	{
		if( this.getChildren().size() == 2 )
		{
            return this.getChildren().get(1).getClass() == CustomCircle.class;
		}
		return false;
	}

	public
	void changeCursor ()
	{
		SnapshotParameters snapShotparams = new SnapshotParameters();
		snapShotparams.setFill(Color.TRANSPARENT);
		if( GameInfo.getCurrentPlayer() == PlayerType.Circle )
		{
			WritableImage image = circleCursor.snapshot(snapShotparams, null);
			boardGrid.setCursor(new ImageCursor(image, GameInfo.placedSize, GameInfo.placedSize));
		}
		else
		{
			WritableImage image = crossCursor.snapshot(snapShotparams, null);
			boardGrid.setCursor(new ImageCursor(image, GameInfo.placedSize, GameInfo.placedSize));
		}
	}

	public
	boolean isAllowedToPlace ()
	{
        return (GameInfo.getCurrentSector() == GameInfo.SECTOR_UNRESTRICTED && !parent.isWinner()) ||
               (parent.getY() * 3 + parent.getX() == GameInfo.getCurrentSector() && !parent.isWinner());
	}

	public
	void paintSquare ()
	{
		this.rectangle.setFill(Paint.valueOf(GameInfo.FILL_COLOR));
	}

	public
	void unpaintSquare ()
	{
		this.rectangle.setFill(null);
	}

	public
	void onMouseClickEvent ()
	{
		if( empty && isAllowedToPlace() )
		{
			if( GameInfo.getCurrentPlayer() == PlayerType.Circle )
			{
				setCircle();
				GameInfo.setSmallCircle(GameInfo.getSmallCircle()+1);
				GameInfo.setCurrentPlayer(PlayerType.Cross);
			}
			else
			{
				setCross();
				GameInfo.setSmallX(GameInfo.getSmallX()+1);
				GameInfo.setCurrentPlayer(PlayerType.Circle);
			}

			empty = false;
			parent.checkWinCondition();
			if( this.parent.parent.cursorMode == Board.CursorMode.SHAPE_CURSOR )
			{
				changeCursor();
			}

			if( !GameInfo.gameBoard.getGrid(y * 3 + x).isWinner()
			 && !GameInfo.gameBoard.getGrid(y * 3 + x)  .isFull())
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

	public
	CustomCircle getCircle ()
	{
		return circle;
	}

	public
	Cross getCross ()
	{
		return cross;
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
