package bank;

import bank.controller.MysqlManager;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class ErrorPageController {
    @FXML
    private TextField ErrorMassage;

    private MainApp mainApp;
    private MysqlManager Manager;
    private Stage dialogStage;

    @FXML
    public void initialize() {
    }

    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }

    public void ShowError(String message){
        ErrorMassage.setText(message);
    }
    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }
}
