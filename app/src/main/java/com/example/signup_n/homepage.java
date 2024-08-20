package com.example.signup_n;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;


import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.drawerlayout.widget.DrawerLayout;

public class homepage extends AppCompatActivity {
    TextView name;
    SharedPreferences sharedPreferences;
    ImageView arrow, phot;
    DrawerLayout drawerLayout;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.newhome);
        name = findViewById(R.id.name);
        arrow = findViewById(R.id.arrow);
        phot = findViewById(R.id.photo);
        sharedPreferences = getSharedPreferences("login", MODE_PRIVATE);
        drawerLayout=findViewById(R.id.drawerLayout);


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawerLayout, toolbar,R.string.open, R.string.close);
        drawerLayout.addDrawerListener(toggle);
        toggle.setDrawerIndicatorEnabled(true);
        toggle.getDrawerArrowDrawable().setColor(getResources().getColor(R.color.black));
        toggle.syncState();

        String nameuser = sharedPreferences.getString("username", "");
        name.setText(" " + nameuser);
        final int[] index = {0};
        int[] images = {R.drawable.ballon, R.drawable.book, R.drawable.lemon, R.drawable.zahralavender};
        arrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                phot.setImageResource(images[index[0]]);
                index[0]++;
                if (index[0] == images.length) {
                    index[0] = 0;
                }
            }
        });

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.my_menu, menu);
        return true;

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.about) {
            Intent intent = new Intent(homepage.this, Aboutas.class);
            startActivity(intent);
        } else if (item.getItemId() == R.id.contac) {
            Intent intent = new Intent(homepage.this, contactus.class);
            startActivity(intent);
        }


        return true;
    }

}