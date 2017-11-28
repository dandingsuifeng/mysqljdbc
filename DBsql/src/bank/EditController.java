package bank;

import bank.controller.MysqlManager;
import bank.model.Employee;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;


public class EditController {
    @FXML
    private TextField Tablename;
    @FXML
    private TextField EEMPNO;
    @FXML
    private TextField EFIRSTNAME;
    @FXML
    private TextField EDININIT;
    @FXML
    private TextField ELASTNAME;
    @FXML
    private Button ok;
    @FXML
    private Button cancel;

    private Stage dialogStage;
    private Employee employee;
    private boolean okClicked = false;
    MysqlManager manager;
    ErrorPageController Message;
    private MainApp mainApp;

    @FXML
    private void initialize() {
    manager = new MysqlManager();
    }

    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }

    public void SetEmployee(Employee oldone){
        employee = oldone;
        EEMPNO.setText(oldone.getEMPNO());
        EFIRSTNAME.setText(oldone.getFIRSTNME());
        EDININIT.setText(oldone.getDININIT());
        ELASTNAME.setText(oldone.getLASTNAME());
    }

    public boolean isOkClicked(){
        return okClicked;
    }

    @FXML
    private void handleOk() {
        if (isInputValid()) {
            employee.setEMPNO(EEMPNO.getText());
            employee.setFIRSTNME(EFIRSTNAME.getText());
            employee.setDININIT(EDININIT.getText());
            employee.setLASTNAME(ELASTNAME.getText());
            String message = new String();
            message = manager.insert(employee,Tablename.getText());
            if (message.length()!=0){
                mainApp.showError(message);
                dialogStage.close();
            }
            else {
                mainApp.showError("插入成功");
                okClicked = true;
                dialogStage.close();
            }
        }
    }

    @FXML
    private void handleCancel() {
        dialogStage.close();
    }

    public boolean isInputValid() {
        boolean flag = false;
        String error = "";

       while (!flag){
           if (Tablename.getText() == null || EEMPNO.getText().length() == 0)
           {
               Tablename.setText("templ");
           }
           if (EEMPNO.getText() == null || EEMPNO.getText().length() == 0)
           {
               error = "请输入正确的EMPNO！";
           }
           if (false == EEMPNO.getText().matches("[0-9]+")){
               error = "请输入正确的EMPNO！";
           }
           if (EDININIT.getText() == null || EDININIT.getText().length() == 0)
           {
               EDININIT.setText("");
           }
           if (EFIRSTNAME.getText() == null || EFIRSTNAME.getText().length() == 0)
           {
               EFIRSTNAME.setText("");
           }
           if (ELASTNAME.getText() == null || ELASTNAME.getText().length() == 0)
           {
               ELASTNAME.setText("");
           }
           if (error.length()==0){
               flag = true;
           }
           if (error.length()!=0){
               mainApp.showError(error);
               flag = true;
           }
       }
       if (error.length()!=0&&flag==true){
           flag = false;
       }
        return flag;
    }
}
