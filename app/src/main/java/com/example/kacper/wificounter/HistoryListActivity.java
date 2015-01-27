package com.example.kacper.wificounter;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;


public class HistoryListActivity extends Activity {

    HistoryHandler historyHandler;
    ProfileHandler profileHandler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history_list);
        Intent intent = getIntent();
        String intentProfileText = intent.getStringExtra("profil");

        historyHandler = new HistoryHandler(this);
        profileHandler = new ProfileHandler(this);
        ListView lv = (ListView) findViewById(R.id.listViewHistList);

        //ArrayList<History> histlist = new ArrayList<History>();
       // histlist.add(new History("aaaa","bbbbb"));
       // histlist.add(new History("ccccc","ddddd"));
        HistoryListAdapter histAdapter = new HistoryListAdapter(this,historyHandler.historyToArray(profileHandler.getProfileId(intentProfileText)));

        lv.setAdapter(histAdapter);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_history_list, menu);
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
}
