package controller;

import com.lynden.gmapsfx.GoogleMapView;
import com.lynden.gmapsfx.MapComponentInitializedListener;
import com.lynden.gmapsfx.javascript.event.UIEventType;
import javafx.fxml.Initializable;
import com.lynden.gmapsfx.javascript.object.*;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import model.*;
import app.Main;
import javafx.collections.ObservableList;
import javafx.beans.property.StringProperty;

import java.net.URL;
import java.util.Map;
import java.util.ResourceBundle;

/**
 * Created by naoto on 3/20/2017.
 */
public class MainOverviewController implements Initializable, MapComponentInitializedListener {
    @FXML
    private Label lableField;
    @FXML
    private Button rpListButton;

    @FXML
    private Button historyButton;

    @FXML
    private GoogleMapView mapview;
    //a google map
    private GoogleMap map;


    //store user
    private model.User user;
    //store report list
    private final ObservableList<StringProperty> reports = Reports.getReportList();
    // store main app
    private Main mainapp;

    /**
     * used to connected to google map service
     *
     * @param url the url of google map
     * @param rb  resource bundle
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        mapview.addMapInializedListener(this);
    }

    /**
     * used to initialize the map
     */
    @Override
    public void mapInitialized() {

        //create map option
        MapOptions mapoption = new MapOptions();
        mapoption.center(new LatLong(47.6097, -122.3331));
        mapoption.mapType(MapTypeIdEnum.ROADMAP);
        mapoption.overviewMapControl(false);
        mapoption.panControl(false);
        mapoption.rotateControl(false);
        mapoption.scaleControl(false);
        mapoption.streetViewControl(false);
        mapoption.zoomControl(false);
        mapoption.zoom(12);

        map = mapview.createMap(mapoption);

        double latitude;
        double longitude;
        String location;
        String[] lalong;
        //create map pin
        for (StringProperty filename : reports) {
            final Map<String, String> report;
            MarkerOptions markeroptions = new MarkerOptions();
            if (filename.get().split(",")[1].equals("workerReport")) {
                if (!(user.getUserLevel().equals(UserLevel.USER))) {
                    report = ((Worker) user).viewReport(filename.get(), false);
                    System.out.println(filename.get());
                    location = report.get("location");
                    lalong = location.split(",");
                    latitude = Double.parseDouble(lalong[0]);
                    longitude = Double.parseDouble(lalong[1]);
                    LatLong loc = new LatLong(latitude, longitude);
                    markeroptions.position(loc);
                    markeroptions.visible(true);
                    markeroptions.title(report.get("title"));

                    Marker marker = new Marker(markeroptions);

                    map.addUIEventHandler(marker,
                            UIEventType.click,
                            (netscape.javascript.JSObject obj) -> {
                                InfoWindowOptions iwoptions = new InfoWindowOptions();
                                iwoptions.content(
                                        "<h4>Report ID:</h4>" + report.get("reportId") + " "
                                                + "<h4>UserName:</h4>" + report.get("user") + " "
                                                + "<h4>Title:</h4>" + report.get("title") + " "
                                                + "<h4>Date:</h4>" + report.get("date") + ""
                                                + "<h4>Location:</h4>" + report.get("location") + ""
                                                + "<h4>Water Condtion:</h4>" + report.get("watercondition") + ""
                                                + "<h4>Virus PPM:</h4>" + report.get("virusPPM") + ""
                                                + "<h4>Contaminant PPM:</h4>" + report.get("contaminantPPM") + ""
                                );
                                InfoWindow window = new InfoWindow(iwoptions);
                                window.open(map, marker);
                            });
                    map.addMarker(marker);
                }
            } else {
                report = user.viewReport(filename.get());
                System.out.println(filename.get());
                location = report.get("location");
                lalong = location.split(",");
                latitude = Double.parseDouble(lalong[0]);
                longitude = Double.parseDouble(lalong[1]);
                LatLong loc = new LatLong(latitude, longitude);
                markeroptions.position(loc);
                markeroptions.visible(true);
                markeroptions.title(report.get("title"));

                Marker marker = new Marker(markeroptions);

                map.addUIEventHandler(marker,
                        UIEventType.click,
                        (netscape.javascript.JSObject obj) -> {
                            InfoWindowOptions iwoptions = new InfoWindowOptions();
                            iwoptions.content(
                                    "<h4>Report ID:</h4>" + report.get("reportId") + " "
                                            + "<h4>UserName:</h4>" + report.get("user") + " "
                                            + "<h4>Title:</h4>" + report.get("title") + " "
                                            + "<h4>Date:</h4>" + report.get("date") + ""
                                            + "<h4>Location:</h4>" + report.get("location") + ""
                                            + "<h4>Water Type:</h4>" + report.get("watertype") + ""
                                            + "<h4>Water Condtion:</h4>" + report.get("watercondition") + ""
                            );
                            InfoWindow window = new InfoWindow(iwoptions);
                            window.open(map, marker);
                        });
                map.addMarker(marker);
            }
        }

    }

    /**
     * set the user logging in
     *
     * @param who the user
     */
    public void setUser(User who) {
        this.user = who;
        lableField.setText("Hi, " + user.getUserLevel());
        if (!user.getUserLevel().equals(UserLevel.MANAGER)) {
            rpListButton.setVisible(false);
            historyButton.setVisible(false);
        }
    }

    /**
     * set the main stage
     *
     * @param main the main app
     */
    public void setMainApp(Main main) {
        this.mainapp = main;
    }

    /**
     * handle the edit button to edit user's profile
     */
    @FXML
    private void handleEdit() {
        mainapp.showSignUp(user, false);
    }

    @FXML
    private void handleNewReport() {
        if (user.getUserLevel().equals(UserLevel.WORKER) || user.getUserLevel().equals(UserLevel.MANAGER)) {
            mainapp.showReportView(user, true);
        } else {
            mainapp.showReportView(user, false);
        }
    }

    @FXML
    private void handleHistoryReport() {
        if (user.getUserLevel().equals(UserLevel.MANAGER)) {
            mainapp.showHistoryReport(user);
        }
    }

    @FXML
    private void handleRPL() {
        mainapp.showReportOverview(this.user);
    }

}
