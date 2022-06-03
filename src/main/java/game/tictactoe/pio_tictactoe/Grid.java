package game.tictactoe.pio_tictactoe;

import javafx.scene.layout.AnchorPane;

import java.util.Vector;

public class Grid  extends AnchorPane {

    public Grid(int boardColumn, int boardRow,Board _parent,AnchorPane boardGrid)
    {
        this.x = boardColumn;
        this.y = boardRow;
        this.parent = _parent;
        this.resize(GameInfo.gridSize,GameInfo.gridSize);
        this.setLayoutX(GameInfo.gridSize*boardColumn);
        this.setLayoutY(GameInfo.gridSize*boardRow);

        for(int gridRow=0;gridRow<3;gridRow++)
        {
            for(int gridColumn=0;gridColumn<3;gridColumn++)
            {
                Square square = new Square(boardGrid,gridColumn,gridRow);
                this.getChildren().add(square);
                this.squares.add(square);
                square.parent = this;

            }
        }
    }

    void paintSquares()
    {
        for(int i=0;i<this.squares.size();i++)
        {
            this.squares.get(i).paintSquare();
        }
    }
    void unpaintSquares()
    {
        for(int i=0;i<this.squares.size();i++)
        {
            this.squares.get(i).unpaintSquare();
        }
    }
    Vector<Square> squares = new Vector<>();
    int x, y;
    public Board parent;
}
