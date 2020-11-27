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

public class MainActivity extends AppCompatActivity {
    EditText edittext_signup, passwordsignup_edittext;
    Button signup_button, facebook_button;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edittext_signup = (EditText) findViewById(R.id.edittext_signup);
        passwordsignup_edittext = (EditText) findViewById(R.id.passwordsignup_edittext);
        signup_button =(Button) findViewById(R.id.signup_button);
        facebook_button= (Button) findViewById(R.id.facebook_button);

        firebaseAuth = FirebaseAuth.getInstance();



        signup_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String email = edittext_signup.getText().toString().trim();
                String password = passwordsignup_edittext.getText().toString().trim();

                if (TextUtils.isEmpty(email)){
                    Toast.makeText(MainActivity.this, "Please Enter Email", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (TextUtils.isEmpty(password)){
                    Toast.makeText(MainActivity.this, "Please Enter Password", Toast.LENGTH_SHORT).show();
                    return;
                }


                if (password.length()<6){
                    Toast.makeText(MainActivity.this, "Password is Weak", Toast.LENGTH_SHORT).show();
                    return;
                }

                // Register user to the firebase

                firebaseAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if (task.isSuccessful()){

                            Toast.makeText(MainActivity.this, "Signup Successfully", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(), Login.class));

                        }else
                        {
                            Toast.makeText(MainActivity.this, "Error!"+ task.getException().getMessage(), Toast.LENGTH_SHORT).show();

                        }

                        

                    }
                });





            }
        });

    }









    public void goto_Loginform(View view) {

        startActivity(new Intent(getApplicationContext(), Login.class));

    }
}