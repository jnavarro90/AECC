package com.aecc.aecc.Controlador;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.aecc.aecc.Modelo.Patient;
import com.aecc.aecc.R;

import java.io.Serializable;

import butterknife.Bind;
import butterknife.ButterKnife;

public class Surveys extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private final String TAG = Login.class.getSimpleName();

    @Bind(R.id.b_fs)
    TextView m_b_fs;
    @Bind(R.id.b_ss)
    TextView m_b_ss;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_surveys);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        ButterKnife.bind(this);
        m_b_fs.setVisibility(View.INVISIBLE);
        m_b_ss.setVisibility(View.INVISIBLE);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_home) {
            setContentView(R.layout.activity_home);
            startActivity(new Intent(this, Home.class));
        } else if (id == R.id.nav_surveys) {
            setContentView(R.layout.activity_surveys);
            startActivity(new Intent(this, Surveys.class));
        } else if (id == R.id.nav_calendar) {
            setContentView(R.layout.activity_calendar);
            startActivity(new Intent(this, Calendar.class));
        } else if (id == R.id.nav_medication) {
            setContentView(R.layout.activity_medication);
            startActivity(new Intent(this, Medication.class));
        } else if (id == R.id.nav_evolution){
            setContentView(R.layout.activity_evolution);
            startActivity(new Intent(this, Evolution.class));
        } else if (id == R.id.nav_profile){
            setContentView(R.layout.activity_profile);
            startActivity(new Intent(this, Profile.class));
        } else if (id == R.id.nav_settings){
            setContentView(R.layout.activity_settings);
            startActivity(new Intent(this, Settings.class));
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
