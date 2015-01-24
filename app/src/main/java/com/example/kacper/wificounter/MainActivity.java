package com.example.kacper.wificounter;

import android.content.Context;
import android.support.v7.app.ActionBarActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends ActionBarActivity {

    EditText Input;
    TextView Text;
    ProfilHandler ProfilHandler;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Input = (EditText) findViewById(R.id.Input);
        Text = (TextView) findViewById(R.id.Text);
        ProfilHandler = new ProfilHandler(getBaseContext());
        printDatabase();
    }
    public void addButtonClicked(View view)  {
    Profil  profil = new Profil(Input.getText().toString());
        ProfilHandler.addItem(profil);
        printDatabase();
    }
    public void deleteButtonClicked(View view) {
        String input = Input.getText().toString();
        ProfilHandler.deleteItem(input);
        printDatabase();

    }

    public void printDatabase() {
        String dbString = ProfilHandler.databaseToString();
        Text.setText(dbString);
        Input.setText("");

    }


}
