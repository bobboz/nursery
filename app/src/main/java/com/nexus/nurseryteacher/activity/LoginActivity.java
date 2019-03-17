package com.nexus.nurseryteacher.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.nexus.nurseryteacher.R;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LoginActivity extends AppCompatActivity {
    private EditText email_editTxt;
    private EditText password_editTxt;
    private Button login_btn;
    private String [][] users;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_fragment);

        // Address the email and password field
        email_editTxt = findViewById(R.id.login_emailid);
        password_editTxt =  findViewById(R.id.login_password);
        login_btn = findViewById(R.id.login_btn);
        login_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(checkLogin()){
                    Intent myIntent = new Intent(LoginActivity.this, MainActivity.class);
                    startActivity(myIntent);
                    LoginActivity.this.finish();
                }
                else{
                    Toast.makeText(getApplicationContext(),"Invalid Email/Password, please try again ",Toast.LENGTH_LONG).show();
                    password_editTxt.setText("");
                }
            }
        });
        users = new String[2][2];
        getUsers();
    }

    public boolean checkLogin() {
        /*
        >>>>>> only for testing <<<<< remove the comment block

        final String email = email_editTxt.getText().toString();
        if (!isValidEmail(email)) {
            //Set error message for email field
            Toast.makeText(getApplicationContext(),"Login success",Toast.LENGTH_LONG);
           // emailEditText.setError("Invalid Email");
            return false;
        }

        final String pass = password_editTxt.getText().toString();
        if (!isValidPassword(pass)) {
            //Set error message for password field
            password_editTxt.setError("Password cannot be empty");
            return false;
        }

        if(!verifyAccount(email, pass)){
            return false;
        }
        */
        return true;

    }

    // validating email id
    private boolean isValidEmail(String email) {
        String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

        Pattern pattern = Pattern.compile(EMAIL_PATTERN);
        Matcher matcher = pattern.matcher(email);

        return matcher.matches();
    }

    // validating password
    private boolean isValidPassword(String pass) {
        if (pass != null && pass.length() >= 4) {
            return true;
        }
        return false;
    }

    private boolean verifyAccount(String email, String pass){

        //Check email and password
        // here where you need to send JSON obj to the webservice
        // for dummy use:
        while(users.length!= 0){
            for(int i=0, j=0; i < users.length; i++){
                if(users[i][j].equals(email) && users[i][j+1].equals(pass)){
                    return true;
                }
                else
                    j=0;
            }
            return false;
        }
        return true;
    }

    private void getUsers(){

        users[0][0]="basma@gmail.com";
        users[0][1]="1234";

        users[1][0]="adam@gmail.com";
        users[1][1]="2222";

    }
}


