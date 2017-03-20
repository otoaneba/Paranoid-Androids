package com.example.olivia.myapplication.controller;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.app.AlertDialog;
import android.widget.Toast;

import com.example.olivia.myapplication.model.UserManager;
import com.example.olivia.myapplication.model.userType;
import com.kosalgeek.asynctask.AsyncResponse;
import com.kosalgeek.asynctask.PostResponseAsyncTask;

import java.util.HashMap;
//import com.example.olivia.myapplication.model.User;
//import com.example.olivia.myapplication.model.UserManager;
//import static com.example.olivia.myapplication.model.UserManager.*;

/**
 * A register page that lets new users register for a new account with a username and a password
 * Cancel button will take you back to the welcome page.
 * @author Kyung Jun Lee
 * @version 1.0
 *
 */
public class RegisterActivity extends AppCompatActivity {
    private UserManager manager = new UserManager();


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        //a spinner that lists the user types in the usertype enum
        final Spinner etSpinner = (Spinner) findViewById(R.id.userTypeSpinner);
        final ArrayAdapter<String> adapter2 = new ArrayAdapter(this,android.R.layout.simple_spinner_item, userType.values());
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        etSpinner.setAdapter(adapter2);

        //represents all the text field that user needs to input to register
        final EditText etId = (EditText) findViewById(R.id.etId);
        final EditText etUsername = (EditText) findViewById(R.id.etUsername);
        final EditText etPassword = (EditText) findViewById(R.id.etPassword);
        final EditText etEmail = (EditText) findViewById(R.id.etEmail);
        final EditText etAddress = (EditText) findViewById(R.id.etAddress);

        //register button that will, when pressed, create a new user with all the
        //attributes entered in the text field
        final Button registerButton = (Button) findViewById(R.id.createButton_source);
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String id = etId.getText().toString();
                final String name = etUsername.getText().toString();
                final String password = etPassword.getText().toString();
                final String email = etEmail.getText().toString();
                final String address = etAddress.getText().toString();
                final String userType = etSpinner.getSelectedItem().toString();
                HashMap postData = new HashMap();
                postData.put("txtUsername", id);
                postData.put("txtName", name);
                postData.put("txtEmailAddress", email);
                postData.put("txtPassword", password);
                postData.put("txtAddress", address);
                postData.put("txtUserType", userType);
                manager.addUser(id, name, password, email, address, userType);


                AsyncResponse asyncResponse = new AsyncResponse() {
                    @Override
                    public void processFinish(String output) {
                        if(output.contains("success")) {
                            Toast.makeText(RegisterActivity.this, output, Toast.LENGTH_LONG).show();
                            Intent intent = new Intent (getApplicationContext(), RetrieveDataActivity.class);
                            startActivity(intent);
                            finish();
                        }else {
                            Toast.makeText(RegisterActivity.this, output, Toast.LENGTH_LONG).show();
                        }
                    }
                };
                PostResponseAsyncTask task = new PostResponseAsyncTask(RegisterActivity.this, postData, asyncResponse);
                //task.execute("http://192.168.1.192:81/android_connect/addUser.php");
                task.execute("http://szhougatech.com/addUser.php");
            }
        });

        //cancel button that takes a user back to the welcome screen
        final Button cancelButton = (Button) findViewById(R.id.cancelButton);
        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),RetrieveDataActivity.class));
            }
        });
    }
}
