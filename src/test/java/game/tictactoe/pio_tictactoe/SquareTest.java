package game.tictactoe.pio_tictactoe;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class SquareTest
{

	@Test
	public
	void userTryingToPlaceSeveralPiecesOnOneSquareTest ()
	{
		Board board = new Board(null, Board.CursorMode.NO_CURSOR);
		//dobranie sie do listy sektorow, i do listy kwadratow

		//ustawiamy pole poczatkowe
		board.grids.get(4).squares.get(4).onMouseClickEvent();

		int square_obj_idx = board.grids.get(4).squares.get(4).getChildren().size() - 1;
		Assertions.assertSame(board.grids.get(4).squares.get(4).getChildren().get(square_obj_idx),
							  board.grids.get(4).squares.get(4).circle,
							  "Actual object differs from expected object.");

		//wywolujemy onMouseClickEvent w jednym polu 1000 razy
		for (int i = 0; i < 1000; i++)
		{
			board.grids.get(4).squares.get(4).onMouseClickEvent();
			Assertions.assertSame(board.grids.get(4).squares.get(4).getChildren().get(square_obj_idx),
								  board.grids.get(4).squares.get(4).circle,
								  "Actual object differs from expected object");
		}

	}

	@Test
	public
	void massTestUserTryingToPlaceFigureInIncorrectPlaces ()
	{

		for (int sectorFirstClickBoardLoc = 0; sectorFirstClickBoardLoc < 9; sectorFirstClickBoardLoc++)
		{

			for (int gridRow = 0; gridRow < 3; gridRow++)
			{
				for (int gridColumn = 0; gridColumn < 3; gridColumn++)
				{
                    //deklaracja boarda

                    Board board = new Board(null, Board.CursorMode.NO_CURSOR);
					GameInfo.setCurrentSector(GameInfo.SECTOR_UNRESTRICTED);

					//ustawienie pierwszej figury w danym sektorze
					board.grids.get(sectorFirstClickBoardLoc)
                         .squares.get(gridRow * 3 + gridColumn)
  						 .onMouseClickEvent();

					//wywolanie metody testowej sprawdzajacej czy mouseeventy przejda, badz nie
					TestUserTryingToPlaceFigureInIncorrectPlaces
                    (board, gridRow * 3 + gridColumn, sectorFirstClickBoardLoc);

				}
			}

		}
	}

	public
	void TestUserTryingToPlaceFigureInIncorrectPlaces (Board board, int nextMoveSectorXY, int firstSquareLocBoardXY)
	{
		//figura ustawiona

		//wywolujemy onMouseClickEvent w polach ktore sa niepoprawne dla tej figury


		for (int gridRow = 0; gridRow < 3; gridRow++)
		{
			for (int gridColumn = 0; gridColumn < 3; gridColumn++)
			{
				//skipping the checks of the correct sector
				if( gridRow * 3 + gridColumn == nextMoveSectorXY )
				{
					continue;
				}

				for (int squareRow = 0; squareRow < 3; squareRow++)
				{
					for (int squareColumn = 0; squareColumn < 3; squareColumn++)
					{
                        //skipping the checks of the first square placed
                        if( squareRow * 3 + squareColumn == nextMoveSectorXY &&
                            gridRow * 3 + gridColumn == firstSquareLocBoardXY )
                        {
                            continue;
                        }

						board.grids.get(gridRow * 3 + gridColumn)
                             .squares.get(squareRow * 3 + squareColumn)
                             .onMouseClickEvent();

						int amountOfObjectsOnSquare = board.grids.get(gridRow * 3 + gridColumn)
                                                           .squares.get(squareRow * 3 + squareColumn)
                                                           .getChildren().size();

						Assertions.assertSame(1, amountOfObjectsOnSquare);
					}
				}
			}
		}
	}

}