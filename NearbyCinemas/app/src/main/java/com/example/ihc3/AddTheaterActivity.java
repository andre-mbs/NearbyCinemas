package com.example.ihc3;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class AddTheaterActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener, AdapterView.OnItemSelectedListener {

    private BottomNavigationView navigationView;
    private Spinner spinner;
    private String location;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_theater);
        setTitle("Theater");

        //Display back button
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        navigationView = (BottomNavigationView) findViewById(R.id.bottom_navigation);
        navigationView.setOnNavigationItemSelectedListener(this);

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

        /*Toast.makeText(this, location, Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra("getLocation", location);
        startActivity(intent);*/

        String name = ((EditText)findViewById(R.id.txt_name)).getText().toString();
        String city = ((EditText)findViewById(R.id.txt_city)).getText().toString();
        String addr = ((EditText)findViewById(R.id.txt_addr)).getText().toString();
        String phone = ((EditText)findViewById(R.id.txt_phone)).getText().toString();

        Map<String, String> map = new HashMap<>();
        map.put("name", name);
        map.put("city", city);
        map.put("addr", addr);
        map.put("phone", phone);

        JSONObject dataJSON = new JSONObject(map);
        String data = dataJSON.toString();

        //Intent intent = new Intent();
        //intent.putExtra("data", data);
        //setResult(Activity.RESULT_OK, intent);
        //finish();

        Intent intent = new Intent(AddTheaterActivity.this, MainActivity.class);
        intent.putExtra("signUp2", "signUp2");
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
