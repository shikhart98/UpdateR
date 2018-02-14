package com.example.shikh.updater;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;

public class SignUpActivity extends AppCompatActivity implements View.OnClickListener {

    ProgressBar progressBar;
    EditText email_et_signup , pass_et_signup;
    Button btn_signup;
    TextView tv_signup;
    private FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        mAuth = FirebaseAuth.getInstance();
        email_et_signup = findViewById(R.id.email_et_signup);
        pass_et_signup = findViewById(R.id.pass_et_signup);
        findViewById(R.id.btn_signup).setOnClickListener(this);
        findViewById(R.id.tv_signup).setOnClickListener(this);
        progressBar = findViewById(R.id.progressbar);


    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.tv_signup:
                finish();
                break;
            case R.id.btn_signup:
                registerUser();
                break;
        }

    }

    private void registerUser() {
        String email = email_et_signup.getText().toString();
        String password = pass_et_signup.getText().toString();
        progressBar.setVisibility(View.VISIBLE);

        if(email.isEmpty()){
            email_et_signup.setError("Email is required");
            email_et_signup.requestFocus();
            return;
        }

//        if(Patterns.EMAIL_ADDRESS.matcher(email).matches()){
//            email_et_signup.setError("Not valid Email");
//            email_et_signup.requestFocus();
//            return;
//        }

        if(password.isEmpty()){
            pass_et_signup.setError("Password is required");
            pass_et_signup.requestFocus();
            return;
        }

        if(password.length() < 6){
            pass_et_signup.setError("Minimum lenght of password should be 6");
            pass_et_signup.requestFocus();
            return;
        }
        mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                progressBar.setVisibility(View.GONE);
                if(task.isSuccessful()){
                    Toast.makeText(getApplicationContext(),"User successfully registered",Toast.LENGTH_SHORT).show();
                    finish();
                }else{
                    if(task.getException() instanceof FirebaseAuthUserCollisionException){
                        Toast.makeText(getApplicationContext(),"You are already registered",Toast.LENGTH_SHORT).show();
                    }else{
                        Toast.makeText(getApplicationContext(),task.getException().getMessage(),Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });


    }
}
