package com.example.saket.inventorymanagement;

import android.app.Activity;
import android.content.Intent;
import android.nfc.Tag;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.FirebaseUser;

/**
 * Created by saket on 12/11/16.
 */

public class SignUpActivity extends AppCompatActivity {

    private EditText inputEmail, inputPassword , inputName , inputConfirmPassword;
    private Button btnSignIn, btnSignUp;
    private ProgressBar progressBar;
    private FirebaseAuth auth;
    private FirebaseUser user1;
    private String email , pass , person_name , confirmPass;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        inputName = (EditText)findViewById(R.id.name);
        inputEmail = (EditText)findViewById(R.id.email);
        inputPassword = (EditText)findViewById(R.id.pass);
        inputConfirmPassword = (EditText)findViewById(R.id.confirm_pass);
        btnSignIn = (Button)findViewById(R.id.sign_in_button);
        btnSignUp = (Button)findViewById(R.id.sign_up_button);
        progressBar = (ProgressBar)findViewById(R.id.progressBar);
        auth = FirebaseAuth.getInstance();
        user1 = FirebaseAuth.getInstance().getCurrentUser();
        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                person_name = inputName.getText().toString().trim();
                email = inputEmail.getText().toString().trim();
                pass = inputPassword.getText().toString().trim();
                confirmPass = inputConfirmPassword.getText().toString().trim();

                if (TextUtils.isEmpty(email)){
                    Toast.makeText(getBaseContext() , "Enter email Address" , Toast.LENGTH_LONG).show();
                    return;
                }

                if (TextUtils.isEmpty(pass)) {
                    Toast.makeText(getApplicationContext(), "Enter password!", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (pass.length() < 6) {
                    Toast.makeText(getApplicationContext(), "Password too short, enter minimum 6 characters!", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (!confirmPass.equals(pass)){
                    Toast.makeText(getApplicationContext() , "Password didn't matched" , Toast.LENGTH_LONG).show();
                    return;
                }

                progressBar.setVisibility(View.VISIBLE);
                auth.createUserWithEmailAndPassword(email , pass)
                        .addOnCompleteListener(SignUpActivity.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {

                                progressBar.setVisibility(View.GONE);
                                if (!task.isSuccessful()) {
                                    Toast.makeText(SignUpActivity.this, "Authentication failed.",
                                            Toast.LENGTH_SHORT).show();
                                }
                                else {

                                    user1.sendEmailVerification().addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {

                                            if (task.isSuccessful()){
                                                Toast.makeText(SignUpActivity.this, "We have sent you instructions to confirm your authentication!", Toast.LENGTH_SHORT).show();

                                            } else {
                                                Toast.makeText(SignUpActivity.this, "Failed to send confirgation email!", Toast.LENGTH_SHORT).show();
                                            }

                                        }
                                    });

                                    }
                                }

                        });



            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        progressBar.setVisibility(View.GONE);
    }
}
