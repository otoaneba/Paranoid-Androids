package com.example.olivia.myapplication.model;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;

import com.example.olivia.myapplication.controller.R;
import com.example.olivia.myapplication.controller.WelcomeActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;


public class RetrieveUserData extends Activity {
    public static ArrayList <User> users = new ArrayList<User>();

    private String myJSON;
    private static final String TAG_RESULTS="result";
    private static final String TAG_USERNAME = "username";
    private static final String TAG_NAME ="name";
    private static final String TAG_PASS = "password";
    private static final String TAG_EMAIL ="email";
    private static final String TAG_ADD ="address";
    private static final String TAG_TYPE = "userType";
    private User user;
    private ArrayList<HashMap<String, String>> userList;

   // ListView list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retrieve_data);
        userList = new ArrayList<HashMap<String,String>>();
        //getData("http://107.180.46.167/public_html/www/getUsers.php");
        getData("http://szhougatech.com/getUsers.php");

        //getData("http://192.168.2.5:81/android_connect/getUsers.php");
    }

    /* Getting the JSON object that has JSON array from web php,
     * create list of the report while retrieve data from the web.
     */
    protected void listUsers(){
        try {
            JSONObject jsonObj = new JSONObject(myJSON);
            JSONArray people = jsonObj.getJSONArray(TAG_RESULTS);

            if(!userList.isEmpty()) {
                userList.clear();
            }
            if(user != null) {
                user = null;
            }
            if(!users.isEmpty()) {
                users.clear();
            }
            for(int i = 0; i< people.length(); i++){
                JSONObject c = people.getJSONObject(i);
                String username = c.getString(TAG_USERNAME);
                String name = c.getString(TAG_NAME);
                String password = c.getString(TAG_PASS);
                String email = c.getString(TAG_EMAIL);
                String address = c.getString(TAG_ADD);
                String userType = c.getString(TAG_TYPE);
                HashMap<String,String> person = new HashMap<String,String>();

                person.put(TAG_USERNAME,username);
                person.put(TAG_NAME,name);
                person.put(TAG_PASS,password);
                person.put(TAG_EMAIL,email);
                person.put(TAG_ADD,address);
                person.put(TAG_TYPE,userType);
                userList.add(person);
                user = new User(username, name, password, email, address, userType);
                users.add(user);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
        startActivity(new Intent(getApplicationContext(),WelcomeActivity.class));
    }

    /* Getting the data from web php, http://szhougatech.com/getSourceReport.php,
     * Android version of String variable.
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
                listUsers();
            }
        }
        GetDataJSON g = new GetDataJSON();
        g.execute(url);
    }

}

