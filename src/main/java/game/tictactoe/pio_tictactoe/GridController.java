package game.tictactoe.pio_tictactoe;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Vector;

public class GridController implements Initializable {

    @FXML
    private AnchorPane BoardGrid;

    private Vector<Grid> Grids= new Vector<>();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        for(int i=0;i<3;i++)
        {
            for(int j=0;j<3;j++)
            {
                Grid temp=new Grid();
                for(int k=0;k<3;k++)
                {
                    for(int l=0;l<3;l++)
                    {
                        Square temp2 = new Square();
                        ImageView squareImage = new ImageView(Images.square);
                        temp2.getChildren().add(squareImage);
                        temp2.resize(90,90);
                        temp.getChildren().add(temp2);
                        temp.squares.add(temp2);
                        temp2.setLayoutX(90*k);
                        temp2.setLayoutY(l*90);
                        temp2.belongsTo=temp;
                    }
                }
                Grids.add(temp);
                BoardGrid.getChildren().add(temp);
                temp.resize(280,280);
                temp.setLayoutX(280*i);
                temp.setLayoutY(280*j);
            }
        }



    }
}
