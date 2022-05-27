package game.tictactoe.pio_tictactoe;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.ImageCursor;
import javafx.scene.SnapshotParameters;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.Vector;

public class GridController implements Initializable {

    @FXML
    private AnchorPane BoardGrid;

    private Vector<Grid> Grids= new Vector<>();

    static Circle circle = new Circle(GameInfo.placedSize/2,GameInfo.placedSize/2,GameInfo.placedSize/2,Color.TRANSPARENT);

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        for(int i=0;i<3;i++)
        {
            for(int j=0;j<3;j++)
            {
                Grid temp=new Grid();
                temp.x = j;
                temp.y = i;
                for(int k=0;k<3;k++)
                {
                    for(int l=0;l<3;l++)
                    {
                        Rectangle r = new Rectangle(90,90);
                        r.setFill(Color.TRANSPARENT);
                        r.setStroke(Color.BLACK);
                        r.setStrokeWidth(5);
                        Square temp2 = new Square(BoardGrid);
                        temp2.y = k;
                        temp2.x = l;
                        temp2.getChildren().add(r);
                        temp2.resize(90,90);
                        temp.getChildren().add(temp2);
                        temp.squares.add(temp2);
                        temp2.setLayoutX(2+90*k);
                        temp2.setLayoutY(l*90+1);
                        temp2.belongsTo=temp;
                    }
                }
                Grids.add(temp);
                BoardGrid.getChildren().add(temp);
                temp.resize(280,280);
                temp.setLayoutX(280*i);
                temp.setLayoutY(280*j);
                GameInfo.currentPlayer = PlayerType.Circle;
                SnapshotParameters snapShotparams = new SnapshotParameters();
                snapShotparams.setFill(Color.TRANSPARENT);
                circle.setStroke(Color.BLUE);
                circle.setStrokeWidth(7);
                WritableImage image = circle.snapshot(snapShotparams, null);
                BoardGrid.setCursor(new ImageCursor(image, GameInfo.placedSize, GameInfo.placedSize));
            }
        }
    }


}
