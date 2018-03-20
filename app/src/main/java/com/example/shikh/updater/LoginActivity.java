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

import com.example.shikh.updater.model.Session;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {


    EditText email_et_login, pass_et_login;
    TextView tv_login;
    Button btn_login;
    FirebaseAuth mAuth;
    ProgressBar progressBarlogin;
    Session session;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        session = new Session(this);
        mAuth = FirebaseAuth.getInstance();
        email_et_login = findViewById(R.id.email_et_login);
        pass_et_login = findViewById(R.id.pass_et_login);
        tv_login = findViewById(R.id.tv_login);
        progressBarlogin = findViewById(R.id.progressbarlogin);
        btn_login = findViewById(R.id.btn_login);
        if(session.loggedin()){
            startActivity(new Intent(LoginActivity.this,MainActivity.class));
            finish();
        }

        findViewById(R.id.tv_login).setOnClickListener(this);
        findViewById(R.id.btn_login).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_login:
                startActivity(new Intent(this, SignUpActivity.class));
                break;
            case R.id.btn_login:
                userLogin();
                break;
        }
    }

    private void userLogin() {
        String email = email_et_login.getText().toString();
        String password = pass_et_login.getText().toString();
        progressBarlogin.setVisibility(View.VISIBLE);

        if(email.isEmpty()){
            progressBarlogin.setVisibility(View.GONE);
            email_et_login.setError("Email is required");
            email_et_login.requestFocus();
            return;
        }

//          check why this is not working!
//        if(Patterns.EMAIL_ADDRESS.matcher(email).matches()){
//            email_et_login.setError("Not valid Email");
//            email_et_login.requestFocus();
//            return;
//        }

        if(password.isEmpty()){
            progressBarlogin.setVisibility(View.GONE);
            pass_et_login.setError("Password is required");
            pass_et_login.requestFocus();
            return;
        }

        if(password.length() < 6){
            progressBarlogin.setVisibility(View.GONE);
            pass_et_login.setError("Minimum length of password should be 6");
            pass_et_login.requestFocus();
            return;
        }
        mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                progressBarlogin.setVisibility(View.GONE);
                if(task.isSuccessful()){
                    session.setLoggedin(true);
                    Toast.makeText(getApplicationContext(),"You're successfully logged in",Toast.LENGTH_SHORT).show();
                    finish();
                    startActivity(new Intent(LoginActivity.this,MainActivity.class));
                }else{
                    Toast.makeText(getApplicationContext(), task.getException().getMessage(),Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
