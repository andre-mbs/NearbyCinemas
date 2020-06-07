package com.example.ihc3;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity  implements NavigationView.OnNavigationItemSelectedListener {
    private DrawerLayout drawer;
    NavigationView navigationView;
    private String location = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //In the first time, the user needs to choose a location
        Bundle extras = getIntent().getExtras();

        if (extras == null){
            Intent intent = new Intent(this, LocationActivity.class);
            startActivity(intent);
        }else if(extras.getString("getOrder") != null){
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new NewOrderFragment()).commit();
            setTitle("     New Order ");
            //getSupportActionBar().setIcon(R.drawable.ic_shopping_cart_black_24dp);
        }else if(extras.getString("signUp2") != null){
            //System.out.println(extras.getString("getOrder"));
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new AccountFragment2()).commit();
            //setTitle("     New Order ");
            //getSupportActionBar().setIcon(R.drawable.ic_shopping_cart_black_24dp);
        }else if(extras.getString("signUp") != null){
            //System.out.println(extras.getString("getOrder"));
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new AccountFragment()).commit();
            setTitle("     New Order ");
            //getSupportActionBar().setIcon(R.drawable.ic_shopping_cart_black_24dp);
        }else {
            location = extras.getString("getLocation");
        }

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawer = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        if (savedInstanceState == null) {
            if(extras != null && new String("getOrder").equals(extras.getString("getOrder"))){
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new NewOrderFragment()).commit();
                setTitle("     New Order ");
                getSupportActionBar().setIcon(R.drawable.ic_shopping_cart_black_24dp);
            }else if(extras != null && new String("signUp2").equals(extras.getString("signUp2"))){
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new AccountFragment2()).commit();
                setTitle("     Account ");
                getSupportActionBar().setIcon(R.drawable.ic_person_add_black_24dp);
            }else if(extras != null && new String("signUp").equals(extras.getString("signUp"))){
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new AccountFragment()).commit();
                setTitle("     Account ");
                getSupportActionBar().setIcon(R.drawable.ic_person_add_black_24dp);
            }else {
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new MoviesListFragment()).commit();
                //setTitle("     Movies in " + location);
                setTitle("     Movies in Viseu");
                getSupportActionBar().setIcon(R.drawable.ic_movie_black_24dp);
                navigationView.setCheckedItem(R.id.nav_list);
            }
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        Intent intent;

        System.out.println(menuItem.getItemId());
        switch (menuItem.getItemId()){
            case R.id.nav_list:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new MoviesListFragment()).commit();
                //setTitle("     Movies in " + location);
                setTitle("     Movies in Viseu");
                getSupportActionBar().setIcon(R.drawable.ic_movie_black_24dp);
                navigationView.setCheckedItem(R.id.nav_list);
                break;
            case R.id.nav_location:
                //getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new LocationFragment()).commit();
                //setTitle("     Location");
                //getSupportActionBar().setIcon(R.drawable.ic_public_black_24dp);
                navigationView.setCheckedItem(R.id.nav_location);
                intent = new Intent(this, LocationActivity.class);
                startActivity(intent);
                break;
            case R.id.nav_login:
                //getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new LoginFragment()).commit();
                navigationView.setCheckedItem(R.id.nav_login);
                intent = new Intent(this, LoginActivity.class);
                intent.putExtra("getLogin", "");
                startActivity(intent);
                break;

            case R.id.nav_signUp:
                navigationView.setCheckedItem(R.id.nav_signUp);
                intent = new Intent(this, SignUpActivity.class);
                intent.putExtra("getSignup", "");
                startActivity(intent);
                break;
            case R.id.nav_settings:
                Toast.makeText(this, "Settings", Toast.LENGTH_SHORT).show();
                break;
        }

        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)){
            drawer.closeDrawer(GravityCompat.START);
        } else{
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.dots_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.changeView:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new MoviesListFragment()).commit();
                break;
        }

        return true;
    }
}
