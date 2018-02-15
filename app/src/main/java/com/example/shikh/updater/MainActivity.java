package com.example.shikh.updater;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.SearchEvent;
import android.view.View;
import android.widget.EditText;
import com.example.shikh.updater.model.Session;
import com.melnykov.fab.FloatingActionButton;

public class MainActivity extends AppCompatActivity {

    EditText et_main;
    Session session;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        session = new Session(this);
        if(!session.loggedin()){
            logout();
        }
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Task Added!", Snackbar.LENGTH_SHORT)
                        .setAction("Action", null).show();
            }
        });
    }

    private void logout() {
        session.setLoggedin(false);
        finish();
        startActivity(new Intent(MainActivity.this,LoginActivity.class));
    }
}
