package com.example.symwe;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Login extends AppCompatActivity {
    EditText email_edittext, password_edittext;
    Button login_button;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        email_edittext = (EditText) findViewById(R.id.email_edittext);
        password_edittext =(EditText)findViewById(R.id.password_edittext);
        login_button = (Button) findViewById(R.id.login_button);

        firebaseAuth = FirebaseAuth.getInstance();

        login_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String email = email_edittext.getText().toString().trim();
                String password = password_edittext.getText().toString().trim();

                if (TextUtils.isEmpty(email)){
                    Toast.makeText(Login.this, "Please Enter Email", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (TextUtils.isEmpty(password)){
                    Toast.makeText(Login.this, "Please Enter Password", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (password.length()<6){
                    Toast.makeText(Login.this, "Password is Weak", Toast.LENGTH_SHORT).show();
                    return;
                }

                firebaseAuth.signInWithEmailAndPassword(email, password)
                        .addOnCompleteListener(Login.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    // Sign in success, update UI with the signed-in user's information

                                    startActivity(new Intent(getApplicationContext(), MainActivityOnboarding.class));
                                    Toast.makeText(Login.this, "Login Successfully", Toast.LENGTH_SHORT).show();


                                } else {
                                    // If sign in fails, display a message to the user.

                                    Toast.makeText(Login.this, "Login Failed or User not found", Toast.LENGTH_SHORT).show();

                                }


                            }
                        });





            }
        });

    }








    public void goto_signupform(View view) {

        startActivity(new Intent(getApplicationContext(), MainActivity.class));

    }

    public void goto_forgetpassword(View view) {

        startActivity(new Intent(getApplicationContext(), ForgotPassword.class));
    }
}