package com.example.olivia.myapplication.controller;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.olivia.myapplication.model.User;
import com.example.olivia.myapplication.model.UserManager;
import com.kosalgeek.asynctask.AsyncResponse;
import com.kosalgeek.asynctask.PostResponseAsyncTask;

import java.util.HashMap;

/**
 * Profile Screen which user can access after logged in
 * Edit Button enable you to edit your profile information
 * Cancel Button takes you back to dummyApp page
 * @author Ran Yi
 * @version 1.0
 */

public class ProfileActivity extends AppCompatActivity {
    private TextView userid;
    private EditText name;
    private EditText email;
    private EditText homeAddress;
    private EditText password;
    private static User user;
    private Button cancelButton;
    private UserManager manager = new UserManager();


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        try {
            super.onCreate(savedInstanceState);
            {
                Bundle extras = getIntent().getExtras();
                if (extras != null) {
                    User user2 = (User) getIntent().getSerializableExtra("user"); //Obtaining data
                    if (user2 != null) {
                        user = user2;
                    }
                }
            }

            setContentView(R.layout.activity_profile_screen);
            userid = (TextView) findViewById(R.id.username);
            userid.setText(user.getId());
            name = (EditText) findViewById(R.id.name);
            name.setText(user.getName(), TextView.BufferType.EDITABLE);

            email = (EditText) findViewById(R.id.EmailAddress);
            email.setText(user.getEmail(), TextView.BufferType.EDITABLE);

            homeAddress = (EditText) findViewById(R.id.HomeAddress);
            homeAddress.setText(user.getAddress(), TextView.BufferType.EDITABLE);

            password = (EditText) findViewById(R.id.password);
            password.setText(user.getPassword(), TextView.BufferType.EDITABLE);

            cancelButton = (Button) findViewById(R.id.Cancel);
            cancelButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                    intent.putExtra("user",user);
                    startActivity(intent);
                    //startActivity(new Intent(getApplicationContext(), MainActivity.class));
                    finish();
                }
            });

            final Button editButton = (Button) findViewById(R.id.edit);
            editButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    final String _userId = userid.getText().toString();
                    final String _name = name.getText().toString();
                    final String _userPassword = password.getText().toString();
                    final String _userEmail = email.getText().toString();
                    final String _userAddress = homeAddress.getText().toString();
                    HashMap postData = new HashMap();
                    postData.put("txtUsername", _userId);
                    postData.put("txtName", _name);
                    postData.put("txtEmailAddress", _userEmail);
                    postData.put("txtPassword", _userPassword);
                    postData.put("txtAddress", _userAddress);

                    AsyncResponse asyncResponse = new AsyncResponse() {
                        @Override
                        public void processFinish(String output) {
                            if(output.contains("edited")) {
                                Toast.makeText(ProfileActivity.this, output, Toast.LENGTH_LONG).show();
                                Intent intent = new Intent (getApplicationContext(), RetrieveDataActivity.class);
                                startActivity(intent);
                                finish();
                            }else {
                                Toast.makeText(ProfileActivity.this, "please try again", Toast.LENGTH_LONG).show();
                            }
                        }
                    };
                    PostResponseAsyncTask task = new PostResponseAsyncTask(ProfileActivity.this, postData, asyncResponse);
                    task.execute("http://128.61.3.143:81/android_connect/editUser.php");
                    //task.execute("http://szhougatech.com/editUser.php");
                }
            });

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}