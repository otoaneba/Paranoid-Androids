package app;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import controller.*;
import model.User;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Main extends Application {

    /**the java logger for main class */
    private static final Logger LOGGER = Logger.getLogger("MainApp");
    //primary stage of this application
    private Stage mainStage;
    //the pane of main screen
    private BorderPane rootLayout;

    public static void Main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception{
        this.mainStage = primaryStage;
        this.mainStage.setTitle("Welcome...");

        initRootLayout();

        showLogin();
    }

    private void initRootLayout() {
        try {
            //Load root layout from fxml file
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("../view/MainScreenView.fxml"));
            rootLayout = loader.load();
            //connect the main screen controller to the mainapp
            MainScreenController controller = loader.getController();
            controller.setMainApp(this);
            //show the sign containing the root layout
            Scene scene = new Scene(rootLayout);
            mainStage.setScene(scene);
            mainStage.show();
        } catch (IOException e) {
            //error on load
            LOGGER.log(Level.SEVERE, "Fail to find the fxml file for main screen!");
            e.printStackTrace();
        }
    }

    public void showLogin() {
        try {
            // load login
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("../view/Loginview.fxml"));
            AnchorPane Loginview = loader.load();
            //set the login in interface into the screen
            rootLayout.setCenter(Loginview);
            //link the login to the mainaoo
            LoginviewController controller = loader.getController();
            controller.setMainApp(this);

        } catch (IOException e) {
            //error on load
            LOGGER.log(Level.SEVERE, "ERROR! Cannot find Login interface");
            e.printStackTrace();
        }
    }

    /**
     * show the sign up window, based on sign up mode or edit mode
     * @param user the user to be passed in sign up/ edit page
     * @param signornot choose the mode to be signup or edit
     */
    public void showSignUp(model.User user, boolean signornot) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("../view/Signupview.fxml"));
            AnchorPane signupview = loader.load();
            //create a dialog stage
            Stage dialogstage = new Stage();
            dialogstage.setTitle("Sign Up");
            if (!signornot) {
                dialogstage.setTitle("Edit Profile");
            }
            dialogstage.initModality(Modality.WINDOW_MODAL);
            dialogstage.initOwner(mainStage);
            Scene scene = new Scene(signupview);
            dialogstage.setScene(scene);
            //set the username and passwod into the controller
            SignUpController controller = loader.getController();
            controller.setMainapp(this);
            controller.setEditBoolean(signornot);
            controller.setUserInfo(user);
            controller.setDialogStage(dialogstage);

            //shwo the dialog and wait
            dialogstage.showAndWait();
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    /**
     * open the application after login
     * @param user the user logged in
     */
    public void showMainOverview(User user) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("../view/MainScreenOverview.fxml"));
            AnchorPane mainoverview = loader.load();
            rootLayout.setCenter(mainoverview);
            //link to the controller
            MainOverviewController controller = loader.getController();
            controller.setMainApp(this);
            controller.setUser(user);
        } catch(IOException e) {
            e.printStackTrace();
        }
    }

    public void showReportView(User user, boolean isworker) {
        try {
            FXMLLoader loader = new FXMLLoader();
            Stage dialogstage = new Stage();
            if (isworker) {
                loader.setLocation(Main.class.getResource("../view/WorkerReportView.fxml"));
                AnchorPane reportview = loader.load();
                dialogstage.setTitle("New Report");
                dialogstage.initModality(Modality.WINDOW_MODAL);
                dialogstage.initOwner(mainStage);
                Scene scene = new Scene(reportview);
                dialogstage.setScene(scene);
                WorkerReportController controller = loader.getController();
                controller.setMainapp(this);
                controller.setUser(user);
                controller.setDialogStage(dialogstage);
            } else {
                loader.setLocation(Main.class.getResource("../view/ReportView.fxml"));
                AnchorPane reportview = loader.load();
                dialogstage.setTitle("New Report");
                dialogstage.initModality(Modality.WINDOW_MODAL);
                dialogstage.initOwner(mainStage);
                Scene scene = new Scene(reportview);
                dialogstage.setScene(scene);
                ReportViewController controller = loader.getController();
                controller.setMainapp(this);
                controller.setUser(user);
                controller.setDialogStage(dialogstage);
            }
            //shwo the dialog and wait
            dialogstage.showAndWait();
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    public void showHistoryReport(User user) {
        try {
            FXMLLoader loader = new FXMLLoader();
            Stage dialogstage = new Stage();

            loader.setLocation(Main.class.getResource("../view/HistoryReportView.fxml"));
            AnchorPane historytview = loader.load();
            dialogstage.setTitle("History Report");
            dialogstage.initModality(Modality.WINDOW_MODAL);
            dialogstage.initOwner(mainStage);
            Scene scene = new Scene(historytview);
            dialogstage.setScene(scene);
            HistoryReportController controller = loader.getController();
            //controller.setMainapp(this);
            controller.setUser(user);
            controller.setDialogStage(dialogstage);

            dialogstage.showAndWait();
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    public void showReportOverview(User user) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("../view/ReportOverview.fxml"));
            AnchorPane reportoverview = loader.load();
            rootLayout.setCenter(reportoverview);
            //link to the controller
            ReportOverviewController controller = loader.getController();
            controller.setMainapp(this);
            controller.setUser(user);
        } catch(IOException e) {
            e.printStackTrace();
        }
    }


    public Stage getMainStage() {
        return this.mainStage;
    }
}
