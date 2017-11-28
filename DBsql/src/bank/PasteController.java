package bank;

import bank.controller.MysqlManager;
import bank.model.Employee;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.stage.Stage;

import static bank.TabUtils.pasteString;


public class PasteController {
    @FXML
    private TableView<Employee> EmployeesView;
    @FXML
    private TableColumn<Employee, String> Employeenum;
    @FXML
    private TableColumn<Employee, String> EmployeeFname;
    @FXML
    private TableColumn<Employee, String> EmployeeDinInit;
    @FXML
    private TableColumn<Employee, String> EmployeeLname;
    @FXML
    private Button Ok;
    @FXML
    private Button Cancel;

    private Stage dialogStage;
    private Employee employee;
    private boolean okClicked = false;
    MysqlManager manager;
    TabUtils tabUtils;
    private MainApp mainApp;


    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    public boolean isOkClicked(){
        return okClicked;
    }
    @FXML
    private void initialize()
    {
        mainApp = new MainApp();
        manager = new MysqlManager();
        tabUtils = new TabUtils();
        Employeenum.setCellValueFactory(cellData -> cellData.getValue().EMPNOProperty());
        EmployeeFname.setCellValueFactory(cellDate -> cellDate.getValue().FIRSTNMEProperty());
        EmployeeDinInit.setCellValueFactory(cellDate -> cellDate.getValue().DININITProperty());
        EmployeeLname.setCellValueFactory(cellDate -> cellDate.getValue().LASTNAMEProperty());
        Employeenum.setCellFactory(TextFieldTableCell.forTableColumn());
        EmployeeFname.setCellFactory(TextFieldTableCell.forTableColumn());
        EmployeeDinInit.setCellFactory(TextFieldTableCell.forTableColumn());
        EmployeeLname.setCellFactory(TextFieldTableCell.forTableColumn());
        Employeenum.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Employee, String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<Employee, String> event) {
                Employee one = new Employee();
                one = (Employee)event.getTableView().getItems().get(event.getTablePosition().getRow());
                manager.delete(one.getEMPNO());
                one.setEMPNO(event.getNewValue());
                manager.insert(one,"templ");
            }
        });
        EmployeeFname.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Employee, String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<Employee, String> event) {
                Employee one = new Employee();
                one = (Employee)event.getTableView().getItems().get(event.getTablePosition().getRow());
                manager.delete(one.getEMPNO());
                one.setFIRSTNME(event.getNewValue());
                manager.insert(one,"templ");
            }
        });
        EmployeeDinInit.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Employee, String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<Employee, String> event) {
                Employee one = new Employee();
                one = (Employee)event.getTableView().getItems().get(event.getTablePosition().getRow());
                manager.delete(one.getEMPNO());
                one.setDININIT(event.getNewValue());
                manager.insert(one,"templ");
            }
        });
        EmployeeLname.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Employee, String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<Employee, String> event) {
                Employee one = new Employee();
                one = (Employee)event.getTableView().getItems().get(event.getTablePosition().getRow());
                manager.delete(one.getEMPNO());
                one.setLASTNAME(event.getNewValue());
                manager.insert(one,"templ");
            }
        });
        EmployeesView.getSelectionModel().setCellSelectionEnabled(true);
        EmployeesView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        TabUtils.installCopyPasteHandler(EmployeesView);
        ObservableList<Employee> employees = FXCollections.observableArrayList();
        int a = 0;
        while (a<50) {
            Employee one = new Employee();
            for (int i = 1; i <= 4; i++) {
                if (i == 1) {
                    one.setEMPNO("");
                }
                if (i == 2) {
                    one.setFIRSTNME("");
                }
                if (i == 3) {
                    one.setDININIT("");
                }
                if (i == 4) {
                    one.setLASTNAME("");
                }
            }
            employees.add(one);
            a++;
        }
        EmployeesView.setItems(employees);
        tabUtils.pasteClipboard(EmployeesView);
    }
    @FXML
    private void handleCancel() {
        dialogStage.close();
    }
    @FXML
    private void handleOk(){
        int i = 0,a = 1;
        String[] str = new String[pasteString.split("\t|\n|\r").length];
        str = pasteString.split("\t|\n|\r");
        str = change(str);
        boolean flag = true;
        while (i<str.length){
            Employee one = new Employee();
            for (a=1;a<=4;a++){
                if (a==1){
                    if(false ==str[i].matches("[0-9]+")){
                       String error = "请输入正确的EMPNO！";
                       mainApp.showError(error);
                       flag = false;
                    }
                    one.setEMPNO(str[i]);
                    System.out.print(str[i]);
                    i++;
                }
                if (a==2){
                    one.setFIRSTNME(str[i]);
                    i++;
                }
                if (a==3){
                    one.setDININIT(str[i]);
                    i++;
                }
                if (a==4){
                    one.setLASTNAME(str[i]);
                    i++;
                }
            }
            if (flag){
                manager.insert(one,"templ");
                mainApp.getEmployeeData().add(one);
            }
        }
        dialogStage.close();
    }

    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }

    public String[] change(String[] List){
        String[] newList = new String[List.length];
        int a= 0;
        String b ="";
        for (int i = 0; i<List.length;i++){
            if (List[i].equals("")){
            }
            else{
                newList[a] = List[i];
                a++;
            }
        }
        String[] newList2 = new String[a];
        for (int i = 0;i<newList2.length;i++){
            newList2[i] = newList[i];
        }
        return newList2;
    }
}
