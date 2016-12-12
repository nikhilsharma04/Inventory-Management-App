package com.example.saket.inventorymanagement;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.MainThread;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

/**
 * Created by saket on 12/11/16.
 */

public class LoginActivity extends AppCompatActivity {

    private EditText inputEmail_lgn, inputPassword_lgn;
    private Button btnSignIn_lgn, btnSignUp_lgn;
    private ProgressBar progressBar;
    private FirebaseAuth auth_lgn;
    private FirebaseUser user;
    private String email , pass;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        auth_lgn = FirebaseAuth.getInstance();
        inputEmail_lgn = (EditText)findViewById(R.id.email_lgn);
        inputPassword_lgn = (EditText)findViewById(R.id.pass_lgn);
        btnSignUp_lgn = (Button)findViewById(R.id.sign_up);
        btnSignIn_lgn = (Button)findViewById(R.id.sign_in);
        progressBar = (ProgressBar)findViewById(R.id.progressBar_lgn);
        btnSignUp_lgn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this , SignUpActivity.class));
            }
        });

        btnSignIn_lgn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                email = inputEmail_lgn.getText().toString().trim();
                pass = inputPassword_lgn.getText().toString().trim();

                if (TextUtils.isEmpty(email) || TextUtils.isEmpty(pass)){
                    Toast.makeText(LoginActivity.this , "Email or password field is blank" , Toast.LENGTH_LONG).show();
                }
                progressBar.setVisibility(View.VISIBLE);
                auth_lgn.signInWithEmailAndPassword(email , pass)
                        .addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {

                                progressBar.setVisibility(View.GONE);
                                if (!task.isSuccessful()){
                                    Toast.makeText(LoginActivity.this , "Authentication failed" , Toast.LENGTH_SHORT).show();
                                }

                                else {
                                    startActivity(new Intent(LoginActivity.this , MainActivity.class));
                                    finish();
                                }




                            }
                        });



            }
        });
    }


}