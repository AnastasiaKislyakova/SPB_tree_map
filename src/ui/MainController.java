package ui;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.*;

public class MainController implements Initializable{

    @FXML
    private Button filterButton;

    @FXML
    protected BorderPane mainPane;

    @FXML
    private AnchorPane map;

    @FXML
    private AnchorPane filterPanel;

    @FXML
    void showFilterPanel(ActionEvent event) {
        filterPanel.setVisible(true);
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
            filterButton.setOnAction(this::showFilterPanel);
    }

}
