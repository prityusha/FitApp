package com.example.android.fitapp;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Signup extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);


        EditText username=(EditText) findViewById(R.id.signupusername);
        EditText password=(EditText) findViewById(R.id.signuppassword);
        EditText confirmpassword=(EditText) findViewById(R.id.signuppassword2);
        Button register=(Button) findViewById(R.id.signupregister);
        TextView gotologinpage = (TextView) findViewById(R.id.gotologin);
        FirebaseAuth mAuth;

        mAuth = FirebaseAuth.getInstance();
        FirebaseUser currentUser = mAuth.getCurrentUser();



        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = username.getText().toString().trim();
                String pass = password.getText().toString().trim();
                String pass2 = confirmpassword.getText().toString().trim();

                if(TextUtils.isEmpty(email)){
                    username.setError("Email is required");
                    return;
                }
                if(TextUtils.isEmpty(pass)){
                    password.setError("Password is required");
                    return;
                }
                if(TextUtils.isEmpty(pass2)){
                    confirmpassword.setError("Password is required");
                    return;
                }

                if(pass.length()<6){
                    password.setError("Password must be atleast 6 characters");
                    return;
                }
                if(!pass.equals(pass2)){
                    confirmpassword.setError("Password does not match!");
                    return;
                }

                mAuth.createUserWithEmailAndPassword(email,pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull  Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(Signup.this,"User Regitered successfully!",Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(Signup.this,Homepage.class);
                            startActivity(intent);
                        }
                        else{
                            Toast.makeText(Signup.this, "Error ! "+task.getException().getMessage(),Toast.LENGTH_SHORT).show();
                            //finish();
                        }
                    }
                });
            }
        });


        gotologinpage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Signup.this,MainActivity.class);
                startActivity(intent);
            }
        });
    }






}