package game.tictactoe.pio_tictactoe;

import javafx.scene.layout.AnchorPane;

import java.util.Vector;

public class Grid  extends AnchorPane {

    public Grid(int BoardRow, int BoardColumn,Board _parent)
    {
        this.x = BoardColumn;
        this.y = BoardRow;
        this.parent = _parent;
        this.resize(GameInfo.gridSize,GameInfo.gridSize);
        this.setLayoutX(GameInfo.gridSize*BoardColumn);
        this.setLayoutY(GameInfo.gridSize*BoardRow);
    }
    Vector<Square> squares = new Vector<>();
    int x, y;
    public Board parent;
}
