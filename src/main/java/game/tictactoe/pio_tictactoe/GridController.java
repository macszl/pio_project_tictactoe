package game.tictactoe.pio_tictactoe;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.ResourceBundle;

public class GridController implements Initializable {

    @FXML
    private AnchorPane boardGrid;


    Board board;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        board = new Board(boardGrid, Board.CursorMode.SHAPE_CURSOR);
        board.paintSquares();
    }

    public static void switchToEndGameWindow(EndGameStatus status)
    {

    }

}
