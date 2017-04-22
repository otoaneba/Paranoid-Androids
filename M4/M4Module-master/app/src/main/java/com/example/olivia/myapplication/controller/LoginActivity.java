package com.example.olivia.myapplication.controller;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.example.olivia.myapplication.model.User;
import com.kosalgeek.asynctask.AsyncResponse;
import com.kosalgeek.asynctask.PostResponseAsyncTask;

import org.apache.tools.ant.util.regexp.Regexp;

import java.util.HashMap;

import static android.graphics.Color.WHITE;
import static com.example.olivia.myapplication.model.RetrieveUserData.users;

/**
 * @author KyungJun Lee
 * A login screen that offers login via username and password
 *
 * Modified by Kyung Jun Lee on 3/18/2017
 */
public class LoginActivity extends AppCompatActivity {
    /**
     * Keep track of the login task to ensure we can cancel it if requested.
     */
    private User currentUser;
    // UI references.
    private EditText _username, _password;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_new);
        // Set up the login form.

        _username = (EditText) findViewById(R.id.username);
        _password = (EditText) findViewById(R.id.password);
        Button _signOn = (Button) findViewById(R.id.loginButton);
        //Button _cancel = (Button) findViewById(R.id.login_cancel_button);


        _signOn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                HashMap<String, String> postData= new HashMap<>();
                postData.put("txtUsername", _username.getText().toString());
                postData.put("txtPassword", _password.getText().toString());

                AsyncResponse asyncResponse = new AsyncResponse() {
                    @Override
                    public void processFinish(String output) {
                        if (output.contains("success")) {
                            for(int i = 0; i < users.size(); i++) {
                                if (users.get(i).getId().equals(_username.getText().toString())) {
                                    currentUser = users.get(i);
                                }
                            }
                            Toast.makeText(LoginActivity.this, output, Toast.LENGTH_LONG).show();
                            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                            intent.putExtra("user", currentUser);
                            startActivity(intent);
                            finish();
                        } else {
                            Toast.makeText(LoginActivity.this, output, Toast.LENGTH_LONG).show();
                        }
                    }
                };
                PostResponseAsyncTask task = new PostResponseAsyncTask(LoginActivity.this, postData, asyncResponse);
                task.execute("http://szhougatech.com/login.php");
            }
        });

        SpannableString ss = new SpannableString("Don't Have an Account? Click Here to Create One.");
        TextView textView = (TextView) findViewById(R.id.registerText);
        ClickableSpan clickableSpan = new ClickableSpan() {
            @Override
            public void onClick(View textView) {
                startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
                finish();
            }

            @Override
            public void updateDrawState(final TextPaint textPaint) {
                textPaint.setColor(WHITE);
                textPaint.setUnderlineText(true);
            }
        };
        ss.setSpan(clickableSpan, 0, ss.length() - 1, 0);
        textView.setMovementMethod(LinkMovementMethod.getInstance());
        textView.setText(ss);

//        _cancel.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(getApplicationContext(),WelcomeActivity.class);
//                startActivity(intent);
//                finish();
//            }
//        });

    }
}

