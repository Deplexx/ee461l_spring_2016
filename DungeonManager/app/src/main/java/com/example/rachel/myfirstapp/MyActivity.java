package com.example.rachel.myfirstapp;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import dice.*;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

public class MyActivity extends AppCompatActivity {
    private ListView mDrawerList;
    private DrawerLayout mDrawerLayout;
    private ArrayAdapter<String> mAdapter;
    private ActionBarDrawerToggle mDrawerToggle;
    private String mActivityTitle;

    public final static String EXTRA_MESSAGE = "com.example.rachel.myfirstapp.MESSAGE";
    private CharacterSheet chac;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);
        //maybe delete???
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mDrawerList = (ListView) findViewById(R.id.navList);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mActivityTitle = getTitle().toString();

        addDrawerItems();
        setupDrawer();

        //adds the action bar for navigation in the right hand corner
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        SharedPreferences settings = getSharedPreferences(CharacterSheet.getPrefsName(0), 0);
        chac = CharacterSheet.loadSheet(settings);

        EditText nameText = (EditText) findViewById(R.id.name_message);
        EditText typeText = (EditText) findViewById(R.id.type_message);
        EditText classText = (EditText) findViewById(R.id.class_message);

        nameText.setText(chac.name);
        typeText.setText(chac.race);
        classText.setText(chac.cclass);

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    private void addDrawerItems() {
        String[] osArray = {"Android", "iOS", "Windows", "OS X", "Linux"};
        mAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, osArray);
        mDrawerList.setAdapter(mAdapter);

        mDrawerList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(MyActivity.this, "YESSS!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void setupDrawer() {
        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, R.string.drawer_open, R.string.drawer_close) {

            /** Called when a drawer has settled in a completely open state. */
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                getSupportActionBar().setTitle("Navigation!");
                invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
            }

            /** Called when a drawer has settled in a completely closed state. */
            public void onDrawerClosed(View view) {
                super.onDrawerClosed(view);
                getSupportActionBar().setTitle(mActivityTitle);
                invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
            }
        };

        mDrawerToggle.setDrawerIndicatorEnabled(true);
        mDrawerLayout.setDrawerListener(mDrawerToggle);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        // Sync the toggle state after onRestoreInstanceState has occurred.
        mDrawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        mDrawerToggle.onConfigurationChanged(newConfig);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_my, menu);
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
        if (mDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * Called when the user clicks the Submit button
     */
    public void submitFields(View view) {

        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(MyActivity.this);

        dialogBuilder
            .setCancelable(true)
            .setTitle(R.string.submit_dialog_title)
            .setMessage(R.string.submit_fields_dialog_message)
            .setPositiveButton(
                R.string.dialog_yes,
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(MyActivity.this, DisplayMessageActivity.class);
                        //EditText editText = (EditText) findViewById(R.id.edit_message);
                        EditText nameText = (EditText) findViewById(R.id.name_message);
                        EditText typeText = (EditText) findViewById(R.id.type_message);
                        EditText classText = (EditText) findViewById(R.id.class_message);
                        EditText levelText = (EditText) findViewById(R.id.level_field);
                        EditText hpText = (EditText) findViewById(R.id.hp_field);
                        EditText acText = (EditText) findViewById(R.id.ac_field);
                        EditText spdText = (EditText) findViewById(R.id.spd_field);
                        EditText initText = (EditText) findViewById(R.id.init_field);
                        EditText strText = (EditText) findViewById(R.id.str_field);
                        EditText dexText = (EditText) findViewById(R.id.dex_field);
                        EditText conText = (EditText) findViewById(R.id.con_field);
                        EditText intText = (EditText) findViewById(R.id.int_field);
                        EditText wisText = (EditText) findViewById(R.id.wis_field);
                        EditText chaText = (EditText) findViewById(R.id.cha_field);

                        //String message = editText.getText().toString();
                        String name_message = nameText.getText().toString();
                        String type_message = typeText.getText().toString();
                        String class_message = classText.getText().toString();
                        int levelNum = Integer.getInteger(levelText.getText().toString());
                        int hpNum = Integer.getInteger(hpText.getText().toString());
                        int acNum = Integer.getInteger(acText.getText().toString());
                        int spdNum = Integer.getInteger(spdText.getText().toString());
                        int initNum = Integer.getInteger(initText.getText().toString());
                        int strNum = Integer.getInteger(strText.getText().toString());
                        int dexNum = Integer.getInteger(dexText.getText().toString());
                        int conNum = Integer.getInteger(conText.getText().toString());
                        int intNum = Integer.getInteger(intText.getText().toString());
                        int wisNum = Integer.getInteger(wisText.getText().toString());
                        int chaNum = Integer.getInteger(chaText.getText().toString());

                        chac.cclass = class_message;
                        chac.name = name_message;
                        chac.race = type_message;
                        chac.level = levelNum;
                        chac.hp = hpNum;
                        chac.ac = acNum;
                        chac.spd = spdNum;
                        chac.init = initNum;
                        chac.str = strNum;
                        chac.dex = dexNum;
                        chac.con = conNum;
                        chac.inl = intNum;
                        chac.wis = wisNum;
                        chac.cha = chaNum;
                        SharedPreferences settings = getSharedPreferences(CharacterSheet.getPrefsName(0), 0);
                        chac.saveSheet(settings);

                        //intent.putExtra(EXTRA_MESSAGE, message);
                        intent.putExtra("name", name_message);
                        intent.putExtra("type", type_message);
                        intent.putExtra("class", class_message);
                        intent.putExtra("CharacterSheet", chac);
                        startActivity(intent);

                    }
                }
            )
            .setNegativeButton(
                R.string.dialog_no,
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //do nothing - player does not want to save stats
                    }
                }
            )
            .show();
    }

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "My Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app deep link URI is correct.
                Uri.parse("android-app://com.example.rachel.myfirstapp/http/host/path")
        );
        AppIndex.AppIndexApi.start(client, viewAction);
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "My Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app deep link URI is correct.
                Uri.parse("android-app://com.example.rachel.myfirstapp/http/host/path")
        );
        AppIndex.AppIndexApi.end(client, viewAction);
        client.disconnect();
    }

    public void autoStats(View view) {
        EditText levelText = (EditText) findViewById(R.id.level_field);
        EditText hpText = (EditText) findViewById(R.id.hp_field);
        EditText acText = (EditText) findViewById(R.id.ac_field);
        EditText spdText = (EditText) findViewById(R.id.spd_field);
        EditText initText = (EditText) findViewById(R.id.init_field);
        EditText strText = (EditText) findViewById(R.id.str_field);
        EditText dexText = (EditText) findViewById(R.id.dex_field);
        EditText conText = (EditText) findViewById(R.id.con_field);
        EditText intText = (EditText) findViewById(R.id.int_field);
        EditText wisText = (EditText) findViewById(R.id.wis_field);
        EditText chaText = (EditText) findViewById(R.id.cha_field);
        int Level = Die.d12.roll();
        int hp = 0;
        for(int i = 0; i < Level; i++){
            hp += Die.d8.roll();
        }
        levelText.setText(String.valueOf(Level));
        hpText.setText(String.valueOf(hp));
        acText.setText(String.valueOf(Die.d6.roll() + 3));
        spdText.setText(String.valueOf(30));
        initText.setText(String.valueOf(Die.d6.roll()));

        strText.setText(String.valueOf((Die.d6.roll() + Die.d6.roll() + Die.d6.roll())));
        dexText.setText(String.valueOf((Die.d6.roll() + Die.d6.roll() + Die.d6.roll())));
        conText.setText(String.valueOf((Die.d6.roll() + Die.d6.roll() + Die.d6.roll())));
        intText.setText(String.valueOf((Die.d6.roll() + Die.d6.roll() + Die.d6.roll())));
        wisText.setText(String.valueOf((Die.d6.roll() + Die.d6.roll() + Die.d6.roll())));
        chaText.setText(String.valueOf((Die.d6.roll() + Die.d6.roll() + Die.d6.roll())));
    }
}
