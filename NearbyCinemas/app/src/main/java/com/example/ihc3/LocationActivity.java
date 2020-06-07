package com.example.ihc3;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

public class LocationActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener, AdapterView.OnItemSelectedListener {

    private BottomNavigationView navigationView;
    private Spinner spinner;
    private String location;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location2);
        setTitle("Location");

        //Display back button
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        navigationView = (BottomNavigationView) findViewById(R.id.bottom_navigation);
        navigationView.setOnNavigationItemSelectedListener(this);

        spinner = (Spinner) findViewById(R.id.spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.locations_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);

    }

    @Override
    public boolean onSupportNavigateUp(){
        onBackPressed();
        return true;
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

        /*switch (menuItem.getItemId()) {
            case R.id.nav_apply: {

                RadioGroup radioGroup = (RadioGroup) findViewById(R.id.radio_group);
                int selected_location = radioGroup.getCheckedRadioButtonId();

                if(selected_location != -1) {
                    RadioButton radioButton = (RadioButton) findViewById(selected_location);
                    String location = radioButton.getText().toString();

                    Intent intent = new Intent(this, MainActivity.class);
                    intent.putExtra("getLocation", location);
                    startActivity(intent);
                }
                break;
            }
        }*/

        Toast.makeText(this, location, Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra("getLocation", location);
        startActivity(intent);

        return true;
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        location = parent.getItemAtPosition(position).toString();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
