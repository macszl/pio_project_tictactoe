package game.tictactoe.pio_tictactoe;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public
class WinHandler
{
	AnchorPane boardGrid;

	public WinHandler(AnchorPane _boardGrid)
	{
		boardGrid = _boardGrid;
	}

	public void switchToEndGameWindow(EndGameStatus status) throws IOException
	{
		Stage stage = (Stage) boardGrid.getScene().getWindow();
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("endGame.fxml"));
		Scene scene = new Scene(fxmlLoader.load(), 270, 270);
		stage.setScene(scene);
		stage.show();
	}
}
