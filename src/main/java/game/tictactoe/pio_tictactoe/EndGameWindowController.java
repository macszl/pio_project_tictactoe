package game.tictactoe.pio_tictactoe;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.ResourceBundle;

public
class EndGameWindowController implements Initializable
{
	@FXML
	Text victory_text;

	@Override
	public void initialize(URL url, ResourceBundle resourceBundle) {
		if( GameInfo.getStatus() == null)
		{
			victory_text.setText("Nikt nie zwyciężył.");
		}
		else if( GameInfo.getStatus() == EndGameStatus.CIRCLE_WIN )
		{
			victory_text.setText("Wygrana kółka.");
		}
		else if( GameInfo.getStatus() == EndGameStatus.CROSS_WIN )
		{
			victory_text.setText("Wygrana krzyżyka.");
		}
	}
}
