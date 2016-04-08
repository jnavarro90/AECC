package com.aecc.aecc.Controlador;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.aecc.aecc.Modelo.Patient;
import com.aecc.aecc.R;

import java.io.Serializable;

import butterknife.Bind;
import butterknife.ButterKnife;

public class Home extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private final String TAG = Login.class.getSimpleName();
    private Patient mPatient;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
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


        //paintMenu();
    }

    private void paintMenu() {
        SharedPreferences preferencesUser = getSharedPreferences("Settings", Context.MODE_PRIVATE);
        String jsonPatientSaved = preferencesUser.getString("Patient", null);
        Patient patient = new Patient();
        mPatient = new Patient();
        mPatient = patient.createUserFromJson(jsonPatientSaved);
        TextView mGreatting = (TextView) findViewById(R.id.tvGreatting);
        mGreatting.setText(mPatient.getNombre());

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
            Intent intent = new Intent(this, Home.class);
            intent.putExtra("datos_usuario", (Serializable) mPatient);
            startActivity(intent);
        } else if (id == R.id.nav_surveys) {
            setContentView(R.layout.activity_surveys);
            Intent intent = new Intent(this, Surveys.class);
            intent.putExtra("datos_usuario", (Serializable) mPatient);
            startActivity(intent);
        } else if (id == R.id.nav_calendar) {
            setContentView(R.layout.activity_calendar);
            Intent intent = new Intent(this, Calendar.class);
            intent.putExtra("datos_usuario", (Serializable) mPatient);
            startActivity(intent);
        } else if (id == R.id.nav_medication) {
            setContentView(R.layout.activity_medication);
            Intent intent = new Intent(this, Medication.class);
            intent.putExtra("datos_usuario", (Serializable) mPatient);
            startActivity(intent);
        } else if (id == R.id.nav_evolution){
            setContentView(R.layout.activity_evolution);
            Intent intent = new Intent(this, Evolution.class);
            intent.putExtra("datos_usuario", (Serializable) mPatient);
            startActivity(intent);
        } else if (id == R.id.nav_profile){
            setContentView(R.layout.activity_profile);
            Intent intent = new Intent(this, Profile.class);
            intent.putExtra("datos_usuario", (Serializable) mPatient);
            startActivity(intent);
        } else if (id == R.id.nav_settings){
            setContentView(R.layout.activity_settings);
            Intent intent = new Intent(this, Settings.class);
            intent.putExtra("datos_usuario", (Serializable) mPatient);
            startActivity(intent);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
