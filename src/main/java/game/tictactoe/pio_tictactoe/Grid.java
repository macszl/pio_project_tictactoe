package game.tictactoe.pio_tictactoe;

import javafx.scene.layout.AnchorPane;

import java.util.Vector;

public class Grid  extends AnchorPane {

    Vector<Square> squares = new Vector<>();
    int x, y;
    public Board parent;
}
