package game.tictactoe.pio_tictactoe;

import javafx.fxml.FXML;
import javafx.scene.ImageCursor;
import javafx.scene.SnapshotParameters;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Vector;

class SquareTest {

    @Test
    public void userTryingToPlaceSeveralPiecesOnOneSquareTest()
    {
        Board board = new Board(null, Board.CursorMode.NO_CURSOR);
        //dobranie sie do listy sektorow, i do listy kwadratow

        //ustawiamy pole poczatkowe
        board.Grids.get(4).squares.get(4).onMouseClickEvent();

        int square_obj_idx = board.Grids.get(4).squares.get(4).getChildren().size() - 1;
        Assertions.assertSame(board.Grids.get(4).squares.get(4).getChildren().get(square_obj_idx),
                              board.Grids.get(4).squares.get(4).circle,
                      "Actual object differs from expected object.");

        //wywolujemy onMouseClickEvent w jednym polu 1000 razy
        for(int i = 0; i < 1000; i++)
        {
            board.Grids.get(4).squares.get(4).onMouseClickEvent();
            Assertions.assertSame(board.Grids.get(4).squares.get(4).getChildren().get(square_obj_idx),
                                  board.Grids.get(4).squares.get(4).circle,
                          "Actual object differs from expected object");
        }

    }

    @Test
    public void massTestUserTryingToPlaceFigureInIncorrectPlaces()
    {

        for(int i = 0; i < 9; i++)
        {
            //deklaracja boarda

            Board board = new Board(null, Board.CursorMode.NO_CURSOR);

            for(int j = 0; j < 3; j++)
            {
                for(int k = 0; k < 3; k++)
                {
                    //ustawienie pierwszej figury w danym sektorze
                    board.Grids.get(j * 3 + k).squares.get(j * 3 + k).onMouseClickEvent();
                    //wywolanie metody testowej sprawdzajacej czy mouseeventy przejda, badz nie
                    TestUserTryingToPlaceFigureInIncorrectPlaces(board, j * 3 + k);
                }
            }
            //czyszczenie boarda
            board = null;

        }
    }

    @Test
    public void TestUserTryingToPlaceFigureInIncorrectPlaces(Board board, int correctSector)
    {
        //figura ustawiona

        //wywolujemy onMouseClickEvent w polach ktore sa niepoprawne dla tej figury

        for(int i = 0; i < 3; i++)
        {
            for(int j = 0; j < 3; j++)
            {
                //skipping the checks of the correct sector
                if( i * 3 + j == correctSector)
                {
                    continue;
                }

                for(int k = 0; k < 3; k++)
                {
                    for(int l = 0; l < 3; l++)
                    {
                        board.Grids.get( i * 3 + j).squares.get(k * 3 + l).onMouseClickEvent();
                        int square_obj_idx = board.Grids.get(4).squares.get(4).getChildren().size() - 1;
                        Assertions.assertNotSame(board.Grids.get(4).squares.get(4).getChildren().get(square_obj_idx),
                                board.Grids.get(4).squares.get(4).circle,
                                "Actual object differs from expected object.");
                        Assertions.assertNotSame(board.Grids.get(4).squares.get(4).getChildren().get(square_obj_idx),
                                board.Grids.get(4).squares.get(4).cross,
                                "Actual object differs from expected object.");
                    }
                }
            }
        }
    }

}