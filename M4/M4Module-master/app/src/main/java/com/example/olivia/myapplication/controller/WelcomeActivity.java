package com.example.olivia.myapplication.controller;
//import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
//import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

/**
 *
 * a welcome screen that has two buttons. Login button will take a user to a login page, and a
 * register button that will take users to a register activity that let users register for a
 * new account.
 * @author Naoto Abe
 *
 */
public class WelcomeActivity extends AppCompatActivity {

    private Button login;
    private Button register;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.welcome_screen);
        //login button
        login =(Button)findViewById(R.id._Login);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),LoginActivity.class));
            }
        });
        //register button
        register = (Button)findViewById(R.id._Register);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("button clicked");
                startActivity(new Intent(getApplicationContext(), RegisterActivity.class));
            }
        });
    }

}
