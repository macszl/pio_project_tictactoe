package game.tictactoe.pio_tictactoe;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.ImageCursor;
import javafx.scene.SnapshotParameters;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.Vector;

public class GridController implements Initializable {

    @FXML
    private AnchorPane BoardGrid;


    Board board;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        board = new Board(BoardGrid, Board.CursorMode.SHAPE_CURSOR);
    }


}
