package com.example.shikh.updater;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.shikh.updater.FragmentFolder.FragmentPage;
import com.example.shikh.updater.FragmentFolder.ViewPageAdapter;
import com.example.shikh.updater.model.Session;

import java.text.DateFormat;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    EditText et_main;
    Session session;
    ImageView img_main;
    ViewPager viewpager;
    TabLayout tabbox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        session = new Session(this);
        img_main = findViewById(R.id.img_main);
        viewpager = findViewById(R.id.viewpager);
        tabbox = findViewById(R.id.tabbox);

        if (!session.loggedin()) {
            logout();
        }
        img_main.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "You're successfully logged out!", Toast.LENGTH_SHORT).show();
                logout();
            }
        });
        ViewPageAdapter adapter = new ViewPageAdapter(getSupportFragmentManager());
        String currentDateTimeString = DateFormat.getDateTimeInstance().format(new Date());
        adapter.AddFragment(new FragmentPage(), currentDateTimeString);
        adapter.AddFragment(new FragmentPage(),"2");
        adapter.AddFragment(new FragmentPage(),"3");
        viewpager.setAdapter(adapter);
        tabbox.setupWithViewPager(viewpager);
    }

    private void logout() {
        session.setLoggedin(false);
        finish();
        startActivity(new Intent(MainActivity.this, LoginActivity.class));
    }
}
