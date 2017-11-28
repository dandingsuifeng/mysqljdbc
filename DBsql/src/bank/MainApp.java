package bank;

import java.io.IOException;

import bank.controller.MysqlManager;
import bank.model.Employee;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class MainApp extends Application {
    private ObservableList<Employee> EmployeeData = FXCollections.observableArrayList();
    private Stage primaryStage;
    private AnchorPane Overview;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("table TEMPL");

        showEmployee();
    }
    public MainApp(){
        MysqlManager Manager = new MysqlManager();
        EmployeeData = Manager.getall();
      //  Employee test = new Employee();
       // test.setEMPNO("a");
        //EmployeeData.add(test);
         }

    public void showEmployee(){
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("PersonOverview.fxml"));
            Overview = (AnchorPane)loader.load();

            Scene scene = new Scene(Overview);
            primaryStage.setScene(scene);
            primaryStage.show();
            PersonController personController = loader.getController();
            personController.setMainApp(this);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public ObservableList<Employee> getEmployeeData() {
        return EmployeeData;
    }

    public Stage getPrimaryStage() {
        return primaryStage;
    }

    public boolean showEmployeeEditDialog(Employee employee) {
        try {
            // Load the fxml file and create a new stage for the popup dialog.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("EmployeeEditDialog.fxml"));
            AnchorPane page = (AnchorPane) loader.load();

            // Create the dialog Stage.
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Edit Person");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(primaryStage);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);

            // Set the Employee into the controller.
            EditController controller = loader.getController();
            controller.setMainApp(this);
            controller.setDialogStage(dialogStage);
            controller.SetEmployee(employee);

            // Show the dialog and wait until the user closes it
            dialogStage.showAndWait();
            return controller.isOkClicked();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean showPasteEditDialog(){
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("PasteEdit.fxml"));
            AnchorPane page = (AnchorPane) loader.load();

        // Create the dialog Stage.
            Stage dialogStage = new Stage();
            dialogStage.setTitle(" ADD Employees");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(primaryStage);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);

            PasteController controller = loader.getController();
            controller.setDialogStage(dialogStage);
            controller.setMainApp(this);


            dialogStage.showAndWait();
            return controller.isOkClicked();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean showSearchEdit(){
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("SearchEdit.fxml"));
            AnchorPane page = (AnchorPane) loader.load();

            Stage dialogStage = new Stage();
            dialogStage.setTitle(" Search Employees");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(primaryStage);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);

            SearchController controller = loader.getController();
            controller.setDialogStage(dialogStage);
            controller.setMainApp(this);

            dialogStage.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean showError(String message){
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("ErrorPage.fxml"));
            AnchorPane page = (AnchorPane) loader.load();

            Stage dialogStage = new Stage();
            dialogStage.setTitle(" Search Employees");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(primaryStage);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);

            ErrorPageController controller = loader.getController();
            controller.setDialogStage(dialogStage);
            controller.setMainApp(this);
            controller.ShowError(message);
            dialogStage.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

   /* public boolean showEmployeeSerchDialog(){
        try {
            // Load the fxml file and create a new stage for the popup dialog.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("SearchEdit.fxml"));
            AnchorPane page = (AnchorPane) loader.load();

            // Create the dialog Stage.
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Edit Person");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(primaryStage);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);

            // Set the Employee into the controller.
            SearchController controller = loader.getController();
            controller.setDialogStage(dialogStage);
            controller.SetEmployee(employee);

            // Show the dialog and wait until the user closes it
            dialogStage.showAndWait();
            return controller.isOkClicked();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }*/
}
