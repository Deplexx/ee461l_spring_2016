package com.example.rachel.myfirstapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

import com.wifidirect.milan.wifidirect.activities.MainActivity;


public class PickerActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_picker);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        final Button DMbutton = (Button) findViewById(R.id.buttonDM);
        assert DMbutton != null;
        DMbutton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                DMChoice(v);
            }
        });


        final Button Pbutton = (Button) findViewById(R.id.buttonPlayer);
        assert Pbutton != null;
        Pbutton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                PlayerChoice(v);
            }
        });
    }

    public void DMChoice(View view){
        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra("DM", true);
        startActivity(intent);
    }

    public void PlayerChoice(View view){
        Intent intent = new Intent(this, MyActivity.class);
        intent.putExtra("Player", true);
        startActivity(intent);
    }
}
