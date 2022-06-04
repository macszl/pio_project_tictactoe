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
	private EndGameStatus currentStatus;

	public WinHandler(AnchorPane _boardGrid)
	{
		boardGrid = _boardGrid;
		currentStatus = null;
	}

	public void switchToEndGameWindow(EndGameStatus status) throws IOException
	{
		Stage stage = (Stage) boardGrid.getScene().getWindow();
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("playerselect.fxml"));
		Scene scene = new Scene(fxmlLoader.load(), 270, 270);
		stage.setScene(scene);
		stage.show();
	}

	public void setCurrentStatus(EndGameStatus _currentStatus)
	{
		currentStatus = _currentStatus;
	}
}
