package bank;

import bank.controller.MysqlManager;
import bank.model.Employee;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.io.IOException;


public class PersonController {

    @FXML
    private TableView<Employee> Employeeview;
    @FXML
    private TableColumn<Employee, String> Employeenum;
    @FXML
    private TableColumn<Employee, String> EmployeeFname;
    @FXML
    private TableColumn<Employee, String> EmployeeDinInit;
    @FXML
    private TableColumn<Employee, String> EmployeeLname;

    @FXML
    private Button oneinsert;
    @FXML
    private Button manyinsert;
    @FXML
    private Button Subquery;
    @FXML
    private Button delete;
    @FXML
    private Button search;

    private MainApp mainApp;
    private MysqlManager Manager;


    @FXML
    public void initialize() {
        Manager = new MysqlManager();
        Employeeview.setEditable(true);
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
                Manager.delete(one.getEMPNO());
                one.setEMPNO(event.getNewValue());
                Manager.insert(one,"templ");
            }
        });
        EmployeeFname.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Employee, String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<Employee, String> event) {
                Employee one = new Employee();
                one = (Employee)event.getTableView().getItems().get(event.getTablePosition().getRow());
                Manager.delete(one.getEMPNO());
                one.setFIRSTNME(event.getNewValue());
                Manager.insert(one,"templ");
            }
        });
        EmployeeDinInit.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Employee, String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<Employee, String> event) {
                Employee one = new Employee();
                one = (Employee)event.getTableView().getItems().get(event.getTablePosition().getRow());
                Manager.delete(one.getEMPNO());
                one.setDININIT(event.getNewValue());
                Manager.insert(one,"templ");
            }
        });
        EmployeeLname.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Employee, String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<Employee, String> event) {
                Employee one = new Employee();
                one = (Employee)event.getTableView().getItems().get(event.getTablePosition().getRow());
                Manager.delete(one.getEMPNO());
                one.setLASTNAME(event.getNewValue());
                Manager.insert(one,"templ");
            }
        });
        Employeeview.getSelectionModel().setCellSelectionEnabled(true);
        Employeeview.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        TabUtils.installCopyPasteHandler(Employeeview);

    }

    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
        Employeeview.setItems(mainApp.getEmployeeData());
    }

    @FXML
    private void handleDeleteEmployee() {
        int selectedIndex = Employeeview.getSelectionModel().getSelectedIndex();
        String message = new String();
        message = Manager.delete(Employeeview.getItems().get(selectedIndex).EMPNO.get());
        if (message.length()!=0){
            mainApp.showError(message);
        }
        else {
            mainApp.showError("删除成功");
            Employeeview.getItems().remove(selectedIndex);
        }
    }
    @FXML
    private void handleNewEmployee() {
        Employee tempEmployee = new Employee();
        boolean okClicked = mainApp.showEmployeeEditDialog(tempEmployee);
        if (okClicked) {
            mainApp.getEmployeeData().add(tempEmployee);
        }
    }

    @FXML
    private void handleNewEmployees() throws IOException {
        //多行插入用
        TabUtils CutManager  =new TabUtils();
        boolean okClicked = mainApp.showPasteEditDialog();
    }

    @FXML
    private void handleEmployeeSubquery(){
       boolean okClicked = mainApp.showSearchEdit();
    }

    @FXML
    private void handleEmployeeSearch(){
        boolean okClicked = mainApp.showSearchEdit();
    }
  }
