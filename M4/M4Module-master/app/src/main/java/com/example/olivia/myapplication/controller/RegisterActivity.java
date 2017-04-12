package com.example.olivia.myapplication.controller;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
import com.example.olivia.myapplication.model.RetrieveUserData;
import com.example.olivia.myapplication.model.userType;
import com.kosalgeek.asynctask.AsyncResponse;
import com.kosalgeek.asynctask.PostResponseAsyncTask;
import java.util.HashMap;
import java.util.regex.Pattern;

/**
 * A register page that lets new users register for a new account with a username and a password
 * Cancel button will take you back to the welcome page.
 * @author Kyung Jun Lee
 * @version 1.0
 *
 */
public class RegisterActivity extends AppCompatActivity {

    /**
     * Written by Rayna
     * Check if the password is valid: The password has to
     * 1, length is from 8 to 14 characters
     * 2, at least 1 digit
     * 3, at least 1 Uppercase Letter
     * 4, at least 1 Lowercase Letter
     * @param password the password String that we wanna check
     * @return boolean boolean value of if the password is valid
     */
    public boolean isPasswordValid(String password) {
        String regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z]).{8,14}$";
        return Pattern.matches(regexp,password);
    }
    /**
     * Written by John Lee
     * Check if the password is valid: The password has to
     * 1, at least 1 any number, lower letter, or uppercase letter before @
     * 2, must contain @
     * 3, at least 1 any number, lower letter, or uppercase letter before .(dot)
     * 4, must contain . (dot)
     * 5, after . (dot) length of the string should be from 2 to 4 characters
     * @param email the email String that we wanna check
     * @return boolean boolean value of if the email is valid
     */
    public boolean isEmailValid (String email) {
        String regexp = "^[A-Za-z0-9._%+\\-]+@[A-Za-z0-9.\\-]+\\.[A-Za-z]{2,4}$";
        return Pattern.matches(regexp, email);
    }

    /**
     * Written by Naoto Abe
     * checks for validity for user ID. Conditions to meet are:
     *  1: ID is 8-20 characters long
     *  2: no _ or . at the beginning
     *  3: no __ or _. or ._ or .. inside
     *  4: allowed characters are [A-Za-z0-9]
     *  5: no _ or . at the end
     * @param id instance of user ID
     * @return returns boolean value if the user name checks out
     */
    private boolean isUserIdValid(String id) {
        String regexp = "^(?=.{8,20}$)(?![_.])(?!.*[_.]{2})[a-zA-Z0-9._]+(?<![_.])$";
        return id.matches(regexp);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        //a spinner that lists the user types in the user type enum
        final Spinner etSpinner = (Spinner) findViewById(R.id.userTypeSpinner);
        @SuppressWarnings("unchecked") final ArrayAdapter<String> adapter2 = new ArrayAdapter(this, android.R.layout.simple_spinner_item, userType.values());
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

                if (etId.getText().toString().isEmpty() || etUsername.getText().toString().isEmpty()
                        || etPassword.getText().toString().isEmpty() ||
                        etEmail.getText().toString().isEmpty() ||
                        etAddress.getText().toString().isEmpty() ||
                        etSpinner.getSelectedItem().toString().isEmpty()) {
                    Toast.makeText(RegisterActivity.this, "Please enter all blanked fields."
                            , Toast.LENGTH_LONG).show();
                }
                else if (!isEmailValid(email)) {
                    Toast.makeText(RegisterActivity.this, "Make sure your email meet the requirement"
                            , Toast.LENGTH_LONG).show();
                }
                else if (!isPasswordValid(password)) {
                    Toast.makeText(RegisterActivity.this, "Make sure your password meet the requirement"
                            , Toast.LENGTH_LONG).show();
                } else if (!isUserIdValid(id)) {
                    Toast.makeText(RegisterActivity.this, "Make sure your user ID meets the requirement"
                            , Toast.LENGTH_LONG).show();
                }
                else {
                    HashMap<String, String> postData = new HashMap<>();
                    postData.put("txtUsername", id);
                    postData.put("txtName", name);
                    postData.put("txtEmailAddress", email);
                    postData.put("txtPassword", password);
                    postData.put("txtAddress", address);
                    postData.put("txtUserType", userType);

                    AsyncResponse asyncResponse = new AsyncResponse() {
                        @Override
                        public void processFinish(String output) {
                            if (output.contains("success")) {
                                Toast.makeText(RegisterActivity.this, output, Toast.LENGTH_LONG).show();
                                Intent intent = new Intent(getApplicationContext(), RetrieveUserData.class);
                                startActivity(intent);
                                finish();
                            } else {
                                Toast.makeText(RegisterActivity.this, output, Toast.LENGTH_LONG).show();
                            }
                        }
                    };
                    PostResponseAsyncTask task = new PostResponseAsyncTask(RegisterActivity.this, postData, asyncResponse);
                    //task.execute("http://192.168.2.5:81/android_connect/addUser.php");
                    task.execute("http://szhougatech.com/addUser.php");
                }
            }
        });

        //cancel button that takes a user back to the welcome screen
        final Button cancelButton = (Button) findViewById(R.id.cancelButton);
        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),RetrieveUserData.class));
            }
        });
    }
}
