module game.tictactoe.pio_tictactoe {
    requires javafx.controls;
    requires javafx.fxml;


    opens game.tictactoe.pio_tictactoe to javafx.fxml;
    exports game.tictactoe.pio_tictactoe;
}