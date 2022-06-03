package game.tictactoe.pio_tictactoe;

import javafx.scene.layout.AnchorPane;

import java.util.Vector;

public class Grid  extends AnchorPane {

    Vector<Square> squares = new Vector<>();
    int x, y;
    public Board parent;

    private boolean winner;

    public Grid(int boardColumn, int boardRow,Board _parent,AnchorPane boardGrid)
    {
        this.x = boardColumn;
        this.y = boardRow;
        this.parent = _parent;
        this.resize(GameInfo.gridSize,GameInfo.gridSize);
        this.setLayoutX(GameInfo.gridSize*boardColumn);
        this.setLayoutY(GameInfo.gridSize*boardRow);
        this.winner = false;

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
        for (Square square : this.squares)
        {
            square.paintSquare();
        }
    }
    void unpaintSquares()
    {
        for (Square square : this.squares)
        {
            square.unpaintSquare();
        }
    }

    boolean getWinner()
    {
        return this.winner;
    }

    void checkWinCondition()
    {
        if(winner)
            return;

        for(int i = 0; i < 3; i++)
            checkColumn(i);
        for(int i = 0; i < 3; i++)
            checkRow(i);
        checkLeftUpDiagonal();
        checkRightUpDiagonal();
    }

    void checkRow(int row)
    {
        if(winner)
            return;

        if( (squares.get(row * 3).checkCross()  && squares.get(row * 3 + 1).checkCross()  && squares.get(row * 3 + 2).checkCross() )||
            (squares.get(row * 3).checkCircle() && squares.get(row * 3 + 1).checkCircle() && squares.get(row * 3 + 2).checkCircle() ))
        {
            this.winner = true;
        }
    }

    void checkColumn(int col)
    {
        if(winner)
            return;

        if( (squares.get(col).checkCross()  && squares.get(col + 3).checkCross()  && squares.get(col + 6).checkCross() )||
            (squares.get(col).checkCircle() && squares.get(col + 3).checkCircle() && squares.get(col + 6).checkCircle() ))
        {
            this.winner = true;
        }
    }

    void checkLeftUpDiagonal()
    {
        if(winner)
            return;

        if( (squares.get(0).checkCross()  && squares.get(4).checkCross()  && squares.get(8).checkCross() )||
            (squares.get(0).checkCircle() && squares.get(4).checkCircle() && squares.get(8).checkCircle() ))
        {
            this.winner = true;
        }
    }
    void checkRightUpDiagonal()
    {
        if(winner)
            return;

        if( (squares.get(2).checkCross()  && squares.get(4).checkCross()  && squares.get(6).checkCross() )||
            (squares.get(2).checkCircle() && squares.get(4).checkCircle() && squares.get(6).checkCircle() ))
        {
            this.winner = true;
        }
    }

    boolean isFull()
    {
        for(int i = 0; i < 9; i++)
        {
            if( squares.get(i).empty)
                return false;
        }
        return true;
    }

}
