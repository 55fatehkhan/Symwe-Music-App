package com.example.symwe;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class ForgotPassword extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);


    }


    public void backto_loginpage(View view) {

        startActivity(new Intent(getApplicationContext(), Login.class));
        finish();

    }
}