package com.example.olivia.myapplication.model;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;

import com.example.olivia.myapplication.controller.R;
import com.example.olivia.myapplication.controller.ReportGraphActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;

public class RetrieveGraphData extends Activity {
    //Graphs using location as key and a list of graphs to store information
    final public static HashMap<String, ArrayList<Graph>> graphs = new HashMap<>();

    private String myJSON;
    private static final String TAG_RESULTS="result";
    private static final String TAG_LOCATION = "Location";
    private static final String TAG_TIME ="TIME";
    private static final String TAG_PPM ="VirusPPM";

    private Graph _graph;
    private ArrayList<HashMap<String, String>> graphList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_history_graph_new);
        graphList = new ArrayList<>();

        getData("http://szhougatech.com/getGraphData.php");
    }
    /* Getting the JSON object that has JSON array from web php,
     * create list of the report while retrieve data from the web.
     */
    private void listGraphInfo(){
        try {
            JSONObject jsonObj = new JSONObject(myJSON);
            JSONArray graphInfo = jsonObj.getJSONArray(TAG_RESULTS);

            if(!graphList.isEmpty()) {
                graphList.clear();
            }
            if(_graph != null) {
                _graph = null;
            }
            if(!graphs.isEmpty()) {
                graphs.clear();
            }
            for(int i = 0; i < graphInfo.length(); i++){
                JSONObject c = graphInfo.getJSONObject(i);

                String loc = c.getString(TAG_LOCATION);
                String time = c.getString(TAG_TIME);
                String ppm = c.getString(TAG_PPM);

                HashMap<String,String> graph = new HashMap<>();
                graph.put(TAG_LOCATION,loc);
                graph.put(TAG_TIME,time);
                graph.put(TAG_PPM,ppm);
                graphList.add(graph);

                double vPPM = c.getDouble(TAG_PPM);
                _graph = new Graph(loc,time,vPPM);
                //Maintain a hash map with location as key, ArrayList of graphs as values
                if (graphs.containsKey(loc)){
                    graphs.get(loc).add(_graph);
                } else {
                    ArrayList<Graph> temp = new ArrayList<>();
                    temp.add(_graph);
                    graphs.put(loc, temp);
                }
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
        User user = (User) getIntent().getSerializableExtra("user"); //Obtaining data
        Intent intent = new Intent(getApplicationContext(),ReportGraphActivity.class);
        intent.putExtra("user",user);
        startActivity(intent);
        finish();
    }

    /* Getting the data from web php, http://szhougatech.com/getSourceReport.php,
     * and put those data into Android version of the String variable.
     */
    private void getData(String url){
        class GetDataJSON extends AsyncTask<String, Void, String> {

            @Override
            protected String doInBackground(String... params) {

                String uri = params[0];

                BufferedReader bufferedReader;
                try {
                    URL url = new URL(uri);
                    HttpURLConnection con = (HttpURLConnection) url.openConnection();
                    StringBuilder sb = new StringBuilder();

                    bufferedReader = new BufferedReader(new InputStreamReader(con.getInputStream()));

                    String json;
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
                listGraphInfo();
            }
        }
        GetDataJSON g = new GetDataJSON();
        g.execute(url);
    }

}


