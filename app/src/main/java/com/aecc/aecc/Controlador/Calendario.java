package com.aecc.aecc.Controlador;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.aecc.aecc.R;
import com.roomorama.caldroid.CaldroidFragment;
import com.roomorama.caldroid.CaldroidListener;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import butterknife.ButterKnife;

public class Calendario extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    final SimpleDateFormat formatter = new SimpleDateFormat("dd MMM yyyy");
    private final String TAG = Login.class.getSimpleName();
    private Clock mClock;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);
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
        mClock = new Clock();

        setLocale();
            final CaldroidFragment caldroidFragment = new CaldroidFragment();
            Bundle args = new Bundle();
            Calendar cal =  mClock.getCalendar();
            args.putInt(CaldroidFragment.MONTH, cal.get(Calendar.MONTH) + 1);
            args.putInt(CaldroidFragment.YEAR, cal.get(Calendar.YEAR));
            args.putInt(CaldroidFragment.START_DAY_OF_WEEK, CaldroidFragment.MONDAY);
            caldroidFragment.setArguments(args);

            FragmentTransaction t = getSupportFragmentManager().beginTransaction();
            t.replace(R.id.calendar1, caldroidFragment);
            t.commit();

            final CaldroidListener listener = new CaldroidListener() {

                @Override
                public void onSelectDate(Date date, View view) {

                    Toast.makeText(getApplicationContext(), formatter.format(date),
                            Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onChangeMonth(int month, int year) {
                    String text = "month: " + month + " year: " + year;
                    Toast.makeText(getApplicationContext(), text,
                            Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onLongClickDate(Date date, View view) {
                    Toast.makeText(getApplicationContext(),
                            "Long click " + formatter.format(date),
                            Toast.LENGTH_SHORT).show();
                    caldroidFragment.setTextColorForDate(R.color.red, date);
                    caldroidFragment.refreshView();
                }

                @Override
                public void onCaldroidViewCreated() {
                    Toast.makeText(getApplicationContext(),
                            "Caldroid view is created",
                            Toast.LENGTH_SHORT).show();
                }

            };

            caldroidFragment.setCaldroidListener(listener);
    }

    //Para cambiar el idioma del calendario
    public void setLocale() {
        Locale locale = new Locale("es", "ES");
        Locale.setDefault(locale);
        Configuration config = new Configuration();
        config.locale = locale;
        getBaseContext().getResources().updateConfiguration(config, getBaseContext().getResources().getDisplayMetrics());
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
            startActivity(new Intent(this, Calendario.class));
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
