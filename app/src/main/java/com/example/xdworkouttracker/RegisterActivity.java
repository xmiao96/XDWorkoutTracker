package com.example.xdworkouttracker;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.xdworkouttracker.UserServices.User;
import com.example.xdworkouttracker.UserServices.UserServices;

public class RegisterActivity extends AppCompatActivity {
    private UserServices us;
    private User regUser = new User();
    private Button signUp;

    private TextView username;
    private TextView password;
    private TextView confPassword;
    private TextView height;
    private TextView weight;
    private RadioButton isFemale;
    private RadioButton isMale;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        signUp = (Button) findViewById(R.id.reg_confirmButton);

        username = (TextView) findViewById(R.id.reg_username);
        password = (TextView) findViewById(R.id.reg_password);
        confPassword = (TextView) findViewById(R.id.reg_confirmPassword);
        height = (TextView) findViewById(R.id.reg_height);
        weight = (TextView) findViewById(R.id.reg_weight);
        isFemale = (RadioButton) findViewById(R.id.reg_female);
        isMale = (RadioButton) findViewById(R.id.reg_male);

        //setup the user services and get data from services
        us = new UserServices(this);

        //register button listener
        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                submitRegistration(view);
            }
        });

    }

    public void submitRegistration(View view){
        //Check the password and confirm password
        if(!isPasswordsSame(password.getText().toString(), confPassword.getText().toString())){
            new AlertDialog.Builder(this)
                    .setTitle("The password is not the same as your confirmation")
                    .setPositiveButton("Confirm", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                        }
                    }).show();
            return;
        }

        //if any information is not filled, set to default
        if(username.getText().toString().equals("")){
            new AlertDialog.Builder(this)
                    .setTitle("Please Enter The Username!")
                    .setPositiveButton("Confirm", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                        }
                    }).show();
            return;
        }

        if ((!isMale.isChecked())&&(!isFemale.isChecked())){
            isMale.setChecked(true);
        }
        if(weight.getText().toString().equals("")){
            weight.setText("60");
        }
        if(height.getText().toString().equals("")){
            height.setText("170");
        }

        //Package the data into User Class
        setUser(username.getText().toString(),
                password.getText().toString(),
                Float.parseFloat(weight.getText().toString()),
                Float.parseFloat(height.getText().toString()),
                isMale.isChecked()
                );

        //Send the data to UserServices to store it
        if(us.isRegistered(regUser)){
            us.registerUser(regUser);
            new AlertDialog.Builder(this)
                    .setTitle("Registration Successes!")
                    .setMessage("You have been registered successfully")
                    .setPositiveButton("Confirm", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            loginRouting(view);
                        }
                    }).show();
        }
        else{
            new AlertDialog.Builder(this)
                    .setTitle("Failed")
                    .setMessage("The username already existed!")
                    .setPositiveButton("Confirm", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                        }
                    }).show();
            return;
        }
    }

    private boolean isPasswordsSame(String password, String confirmPassword){
        return password.equals(confirmPassword);
    }

    public void loginRouting(View view){
        startActivity(new Intent(this, LoginActivity.class));
        finish();
    }
    public void setUser(String username, String password, float weight, float height, boolean gender){
        regUser.setUsername(username);
        regUser.setPassword(password);
        regUser.setWeight(weight);
        regUser.setHeight(height);
        regUser.setGender(gender);
    }

}