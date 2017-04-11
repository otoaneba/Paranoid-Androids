package com.example.olivia.myapplication.model;

/**
 * Created by John on 2017-03-22.
 */
        import android.app.Activity;
        import android.content.Intent;
        import android.os.AsyncTask;
        import android.os.Bundle;
        import android.util.Log;
        import android.widget.ListView;

        import com.example.olivia.myapplication.controller.R;
        import com.example.olivia.myapplication.controller.ViewReportActivity;
        import com.example.olivia.myapplication.model.Report;
        import com.example.olivia.myapplication.model.User;

        import org.json.JSONArray;
        import org.json.JSONException;
        import org.json.JSONObject;
        import java.io.BufferedReader;
        import java.io.InputStreamReader;
        import java.net.HttpURLConnection;
        import java.net.URL;
        import java.util.ArrayList;
        import java.util.HashMap;

public class RetrievePurityReportData extends Activity {
    public static ArrayList <Report> reports = new ArrayList<Report>();

    private String myJSON;
    private static final String TAG_RESULTS="result";
    private static final String TAG_REPORT_NUMBER = "ReportNumber";
    private static final String TAG_TIME ="TIME";
    private static final String TAG_LOCATION = "Location";
    private static final String TAG_CREATOR ="Creator";
    private static final String TAG_QUALITY ="Quality";
    private static final String TAG_VIRUSPPM = "VirusPPM";
    private static final String TAG_CONTAMPPM = "ContaminatePPM";
    private static final String TAG_LAT = "Latitude";
    private static final String TAG_LONG = "Longitude";


    private JSONArray reportInfo = null;
    private Report _report;
    private ArrayList<HashMap<String, String>> reportList;

    ListView list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_report_layout);
        reportList = new ArrayList<HashMap<String,String>>();
        getData("http://szhougatech.com/getPurityReport.php");
        //getData("http://192.168.2.5:81/android_connect/getPurityReport.php");
    }
    /* Getting the JSON object that has JSON array from web php,
     * create list of the report while retrieve data from the web.
     */
    protected void listPurityReport(){
        try {
            JSONObject jsonObj = new JSONObject(myJSON);
            reportInfo = jsonObj.getJSONArray(TAG_RESULTS);

            if(!reportList.isEmpty()) {
                reportList.clear();
            }
            if(_report != null) {
                _report = null;
            }
            if(!reports.isEmpty()) {
                reports.clear();
            }
            for(int i = 0; i < reportInfo.length();i++){
                JSONObject c = reportInfo.getJSONObject(i);
                String rptNum = c.getString(TAG_REPORT_NUMBER);
                String time = c.getString(TAG_TIME);
                String loc = c.getString(TAG_LOCATION);
                String creator = c.getString(TAG_CREATOR);
                String quality = c.getString(TAG_QUALITY);
                String virusPPM = c.getString(TAG_VIRUSPPM);
                String contaminatePPM = c.getString(TAG_CONTAMPPM);
                String lat = c.getString(TAG_LAT);
                String longitude = c.getString(TAG_LONG);




                HashMap<String,String> report = new HashMap<String,String>();

                report.put(TAG_REPORT_NUMBER,rptNum);
                report.put(TAG_TIME,time);
                report.put(TAG_LOCATION,loc);
                report.put(TAG_CREATOR,creator);
                report.put(TAG_QUALITY,quality);
                report.put(TAG_VIRUSPPM,virusPPM);
                report.put(TAG_CONTAMPPM,contaminatePPM);
                report.put(TAG_LAT,lat);
                report.put(TAG_LONG,longitude);
                Log.d("checking",rptNum + " " + time + " " + loc + " " + creator + " " +  quality);
                reportList.add(report);
                _report = new Report(rptNum,time,loc,creator,virusPPM,contaminatePPM,quality,lat,longitude);
                reports.add(_report);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
        User user = (User) getIntent().getSerializableExtra("user"); //Obtaining data
        Intent intent = new Intent(getApplicationContext(),ViewReportActivity.class);
        intent.putExtra("user",user);
        startActivity(intent);
        finish();
    }

    /* Getting the data from web php, http://szhougatech.com/getSourceReport.php,
     * and put those data into Android version of the String variable.
     */
    public void getData(String url){
        class GetDataJSON extends AsyncTask<String, Void, String> {

            @Override
            protected String doInBackground(String... params) {

                String uri = params[0];

                BufferedReader bufferedReader = null;
                try {
                    URL url = new URL(uri);
                    HttpURLConnection con = (HttpURLConnection) url.openConnection();
                    StringBuilder sb = new StringBuilder();

                    bufferedReader = new BufferedReader(new InputStreamReader(con.getInputStream()));

                    String json = null;
                    while((json = bufferedReader.readLine())!= null){
                        sb.append(json+"\n");
                    }

                    return sb.toString().trim();

                }catch(Exception e){
                    return null;
                }
            }
            @Override
            protected void onPostExecute(String result){
                myJSON = result;
                listPurityReport();
            }
        }
        GetDataJSON g = new GetDataJSON();
        g.execute(url);
    }

}


