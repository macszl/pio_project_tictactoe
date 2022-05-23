package game.tictactoe.pio_tictactoe;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.File;

public class Images {

    static Image square= new Image(new File("square.png").toURI().toString());
    static Image circle= new Image(new File("circle.png").toURI().toString());
    static Image X= new Image(new File("X.png").toURI().toString());
}
