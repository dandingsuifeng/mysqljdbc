package bank;

import bank.controller.MysqlManager;
import bank.model.Employee;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;


public class SearchController {
    @FXML
    private TextField TableName;
    @FXML
    private TextField Limit;
    @FXML
    private Button Ok;
    @FXML
    private Button cancel;
    @FXML
    private Button search;

    private Stage dialogStage;
    private Employee employee;
    private boolean okClicked = false;
    MysqlManager manager;
    private MainApp mainApp;


    @FXML
    private void initialize() {
        manager = new MysqlManager();
    }

    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    @FXML
    private void handleCancel() {
        dialogStage.close();
    }

    @FXML
    private void handleSearch(){
        if (isInputValid()){
            Employee one = new Employee();
            one = manager.search(TableName.getText(),Limit.getText());
            if (one!=null){
                String message = new String("EMPNO="+one.getEMPNO()+" FIRSTNAME="+one.getFIRSTNME()+" MIDINIT="+one.getFIRSTNME()+" LASTNAME="+one.getLASTNAME());
                mainApp.showError(message);
                dialogStage.close();
            }
            else {
                mainApp.showError("查无此人");
                dialogStage.close();
            }
        }
    }

    @FXML
    private void handleOk() {
        if (isInputValid()) {
            //子查询插入
            String message= new String();
            message = manager.Subquery(TableName.getText(),Limit.getText());
            if (message.length()!=0){
                mainApp.showError(message);
                dialogStage.close();
            }
           else {
                okClicked = true;
                mainApp.showError("子查询插入成功!");
                //  刷新页面未写
                dialogStage.close();
            }
        }
    }

    public boolean isInputValid() {
        boolean flag = false;
        String error = "";
            if (TableName.getText() == null || TableName.getText().length() == 0)
            {
                error+="未填写表格名字!   ";
            }
            if (Limit.getText() == null || Limit.getText().length() == 0)
            {
                error+="未填写限制条件!";
            }
            if (error.length()!=0){
                mainApp.showError(error);
            }
            if (error.length()==0){
                flag = true;
            }
        return flag;
    }

    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }
}
