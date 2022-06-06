package game.tictactoe.pio_tictactoe;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class SquareTest
{
	public final int UPPER_LEFT = 0;
	public final int UPPER_MID = 1;
	public final int UPPER_RIGHT = 2;
	public final int MIDDLE_LEFT = 3;
	public final int MIDDLE_MIDDLE = 4;
	public final int MIDDLE_RIGHT = 5;
	public final int LOWER_LEFT = 6;
	public final int LOWER_MIDDLE = 7;
	public final int LOWER_RIGHT = 8;

	public static final int ROW_LENGTH = 3;
	public static final int COLUMN_LENGTH = 3;

	@Test
	public
	void userTryingToPlaceSeveralPiecesOnOneSquareTest ()
	{
		Board board = new Board(null, Board.CursorMode.NO_CURSOR);
		//dobranie sie do listy sektorow, i do listy kwadratow

		//ustawiamy pole poczatkowe
		board.grids.get(MIDDLE_MIDDLE).squares.get(MIDDLE_MIDDLE).onMouseClickEvent();

		int square_obj_idx = board.grids.get(MIDDLE_MIDDLE).squares.get(MIDDLE_MIDDLE).getChildren().size() - 1;
		Assertions.assertSame(board.grids.get(MIDDLE_MIDDLE).squares.get(MIDDLE_MIDDLE).getChildren().get(square_obj_idx),
							  board.grids.get(MIDDLE_MIDDLE).squares.get(MIDDLE_MIDDLE).getCircle(),
							  "Actual object differs from expected object.");

		//wywolujemy onMouseClickEvent w jednym polu 1000 razy
		for (int i = 0; i < 1000; i++)
		{
			board.grids.get(MIDDLE_MIDDLE).squares.get(MIDDLE_MIDDLE).onMouseClickEvent();
			Assertions.assertSame(board.grids.get(MIDDLE_MIDDLE).squares.get(MIDDLE_MIDDLE).getChildren().get(square_obj_idx),
								  board.grids.get(MIDDLE_MIDDLE).squares.get(MIDDLE_MIDDLE).getCircle(),
								  "Actual object differs from expected object");
		}

	}

	@Test
	public
	void massTestUserTryingToPlaceFigureInIncorrectPlaces ()
	{

		for (int sectorFirstClickBoardLoc = 0; sectorFirstClickBoardLoc < ROW_LENGTH * COLUMN_LENGTH; sectorFirstClickBoardLoc++)
		{

			for (int gridRow = 0; gridRow < ROW_LENGTH; gridRow++)
			{
				for (int gridColumn = 0; gridColumn < COLUMN_LENGTH; gridColumn++)
				{
                    //deklaracja boarda

                    Board board = new Board(null, Board.CursorMode.NO_CURSOR);
					GameInfo.setCurrentSector(GameInfo.SECTOR_UNRESTRICTED);

					//ustawienie pierwszej figury w danym sektorze
					board.grids.get(sectorFirstClickBoardLoc)
                         .squares.get(gridRow * ROW_LENGTH + gridColumn)
  						 .onMouseClickEvent();

					//wywolanie metody testowej sprawdzajacej czy mouseeventy przejda, badz nie
					TestUserTryingToPlaceFigureInIncorrectPlaces
                    (board, gridRow * ROW_LENGTH + gridColumn, sectorFirstClickBoardLoc);

				}
			}

		}
	}

	public
	void TestUserTryingToPlaceFigureInIncorrectPlaces (Board board, int nextMoveSectorXY, int firstSquareLocBoardXY)
	{
		//figura ustawiona

		//wywolujemy onMouseClickEvent w polach ktore sa niepoprawne dla tej figury


		for (int gridRow = 0; gridRow < ROW_LENGTH; gridRow++)
		{
			for (int gridColumn = 0; gridColumn < COLUMN_LENGTH; gridColumn++)
			{
				//skipping the checks of the correct sector
				if( gridRow * ROW_LENGTH + gridColumn == nextMoveSectorXY )
				{
					continue;
				}

				for (int squareRow = 0; squareRow < ROW_LENGTH; squareRow++)
				{
					for (int squareColumn = 0; squareColumn < COLUMN_LENGTH; squareColumn++)
					{
                        //skipping the checks of the first square placed
                        if( squareRow * ROW_LENGTH + squareColumn == nextMoveSectorXY &&
                            gridRow * ROW_LENGTH + gridColumn == firstSquareLocBoardXY )
                        {
                            continue;
                        }

						board.grids.get(gridRow * ROW_LENGTH + gridColumn)
                             .squares.get(squareRow * ROW_LENGTH + squareColumn)
                             .onMouseClickEvent();

						int amountOfObjectsOnSquare = board.grids.get(gridRow * ROW_LENGTH + gridColumn)
                                                           .squares.get(squareRow * ROW_LENGTH + squareColumn)
                                                           .getChildren().size();

						Assertions.assertSame(1, amountOfObjectsOnSquare);
					}
				}
			}
		}
	}

}