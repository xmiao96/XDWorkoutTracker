package com.example.xdworkouttracker;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.xdworkouttracker.UserServices.User;
import com.example.xdworkouttracker.UserServices.UserServices;

public class LoginActivity extends AppCompatActivity {

    private UserServices us;
    private TextView username;
    private TextView password;
    private User user;
    private SharedPreferences sp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        username = (TextView) findViewById(R.id.login_username);
        password = (TextView) findViewById(R.id.login_password);
        us = new UserServices(this);
        Button login = (Button) findViewById(R.id.login);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                submitLogin(view);
            }
        });

    }

    public void submitLogin(View view){
        // Check username and password
        user = us.loginUser(username.getText().toString(), password.getText().toString());
        if(user == null){
            new AlertDialog.Builder(this)
                    .setTitle("Login Failed")
                    .setMessage("Please check your username and password.")
                    .setPositiveButton("Confirm", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                        }
                    }).show();
            return;
        }
        //routing to main page with preferences setup (user cookie with the information)
        sp = getSharedPreferences("UserCookie", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString("USERNAME", user.getUsername());
        editor.putString("PASSWORD", user.getPassword());
        editor.putFloat("WEIGHT", user.getWeight());
        editor.putFloat("HEIGHT", user.getHeight());
        editor.putBoolean("GENDER", user.isMale());
        editor.commit();
        new AlertDialog.Builder(this)
                .setTitle("Login Successes")
                .setPositiveButton("Confirm", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        loginRouting(view);
                    }
                }).show();
    }

    public void registerRouting(View view){
        startActivity(new Intent(this, RegisterActivity.class));
        finish();
    }

    public void loginRouting(View view){
        startActivity(new Intent(this, MainActivity.class));
        finish();
    }
}