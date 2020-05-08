package com.thesis.apphealth;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
/*    TextView logo,slogan;*/
    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle mToggle;
    private NavigationView navigationView;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        /*-------------------------------------------------*/

        setContentView(R.layout.activity_main);



        setSupportActionBar(toolbar);
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);
        toolbar = findViewById(R.id.toolbar);
        /*-------------------------Tool Bar---------------------*/
        setSupportActionBar(toolbar);
        /*-------------------------*/
        mToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.Open, R.string.Close);
        drawerLayout.addDrawerListener(mToggle);
        mToggle.syncState();
        Menu menu = navigationView.getMenu();
        /*menu.findItem(R.id.login).setVisible(false);*/
        navigationView.bringToFront();
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.setCheckedItem(R.id.mainScreen);
    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer((GravityCompat.START));
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case R.id.mainScreen:
                break;
            case R.id.profile:
                Intent intent = new Intent(MainActivity.this, diagnosisActivity.class);
                startActivity(intent);
                break;
            case R.id.logout:
                Intent intent1= new Intent(MainActivity.this,activity_login.class);
                startActivity(intent1);
                break;
        }
        return true;
    }
}
