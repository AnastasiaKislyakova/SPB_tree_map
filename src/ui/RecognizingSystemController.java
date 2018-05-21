package ui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressIndicator;

import java.net.URL;
import java.util.ResourceBundle;

public class RecognizingSystemController implements Initializable{

    @FXML
    private ProgressIndicator indicator;

    @FXML
    private Button loadPhotoButton;

    @FXML
    private Label resultInformation;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        loadPhotoButton.setOnAction(this::load);
        resultInformation.setText("");

    }

    @FXML
    void load(ActionEvent event) {
        indicator.setVisible(true);
        this.resultInformation.setText("Система в стадии разработки");
    }



}
