package game.tictactoe.pio_tictactoe;

import javafx.scene.layout.AnchorPane;

import java.util.Vector;

public class Grid  extends AnchorPane {

    public Grid(int BoardColumn, int BoardRow,Board _parent,AnchorPane BoardGrid)
    {
        this.x = BoardColumn;
        this.y = BoardRow;
        this.parent = _parent;
        this.resize(GameInfo.gridSize,GameInfo.gridSize);
        this.setLayoutX(GameInfo.gridSize*BoardColumn);
        this.setLayoutY(GameInfo.gridSize*BoardRow);

        for(int GridRow=0;GridRow<3;GridRow++)
        {
            for(int GridColumn=0;GridColumn<3;GridColumn++)
            {
                Square square = new Square(BoardGrid,GridColumn,GridRow);
                this.getChildren().add(square);
                this.squares.add(square);
                square.parent = this;

            }
        }
    }
    Vector<Square> squares = new Vector<>();
    int x, y;
    public Board parent;
}
