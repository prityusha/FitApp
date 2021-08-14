package com.example.android.fitapp;

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
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EditText mEmail = (EditText) findViewById(R.id.loginemail);
        EditText mpassword=(EditText) findViewById(R.id.loginpassword);
        Button signupbutton = (Button) findViewById(R.id.signuppage);
        Button loginbutton = (Button) findViewById(R.id.loginbutton);
        FirebaseAuth fAuth;
        fAuth = FirebaseAuth.getInstance();
        FirebaseUser currentUser = fAuth.getCurrentUser();
        if(currentUser!=null){
            Intent intent = new Intent(MainActivity.this,Homepage.class);
            startActivity(intent);
            finish();
        }

        loginbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = mEmail.getText().toString().trim();
                String pass = mpassword.getText().toString().trim();

                if(TextUtils.isEmpty(email)){
                    mEmail.setError("Email is required");
                    return;
                }
                if(TextUtils.isEmpty(pass)) {
                    mpassword.setError("Password is required");
                    return;
                }

                if(pass.length()<6){
                    mpassword.setError("Password must be atleast 6 characters");
                    return;
                }

                fAuth.signInWithEmailAndPassword(email,pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(MainActivity.this, "Logged in successfully", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(MainActivity.this,Homepage.class);
                            startActivity(intent);
                        }
                        else{
                            Toast.makeText(MainActivity.this,"Error ! "+task.getException().getMessage(),Toast.LENGTH_SHORT).show();
                            //finish();
                        }
                    }
                });
            }
        });


        signupbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,Signup.class);
                startActivity(intent);
            }
        });

        /*loginbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,Homepage.class);
                startActivity(intent);
            }
        });*/
    }
}