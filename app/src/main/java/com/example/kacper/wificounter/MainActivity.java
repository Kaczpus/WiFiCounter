package com.example.kacper.wificounter;

import android.app.AlertDialog;
import android.app.ListActivity;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends ListActivity {

    EditText Input;
    TextView Text;
    ProfileHandler ProfileHandler;
    SQLiteDatabase Database;
    private ArrayList<String> result = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        openAndQueryDatabase();
        displayResultList();
        ProfileHandler = new ProfileHandler(getBaseContext());
       /* Input = (EditText) findViewById(R.id.Input);
        Text = (TextView) findViewById(R.id.Text);
        Input.setText("");*/
        setListAdapter(new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, result));
        final ListView lv = getListView();

        lv.setTextFilterEnabled(true);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {

                    Intent myIntent = new Intent(view.getContext(), ProfileActivity.class);
                    // Profile item = (Profile) parent.getSelectedItem();

                   // Profile Item = (Profile) parent.getItemAtPosition(position);
                    //int a = getSelectedItemPosition();
                    String profileName =  lv.getItemAtPosition(position).toString();
                    myIntent.putExtra("profile",profileName);

                    startActivity(myIntent);

            }
        });


    }

    public void addButtonClicked(View view) {
       // Profile profile = new Profile(Input.getText().toString());
      //  ProfileHandler.addItem(profile);
      //  result.add(profile.get_profilname());
      //  displayResultList();
        AlertDialog.Builder alert = new AlertDialog.Builder(this);

        alert.setTitle("Adding");
        alert.setMessage("Please enter name of your profile");
        LayoutInflater inflater = MainActivity.this.getLayoutInflater();
        View layout = inflater.inflate(R.layout.addbuttonlayout,null);

        alert.setView(layout);
        final EditText input=(EditText)layout.findViewById(R.id.editText);
        final EditText ourwifi=(EditText)layout.findViewById(R.id.editText2);
        WiFi wifi = new WiFi(this);
        ourwifi.setText(wifi.getWiFiSSID());
       /* final EditText wifi = new EditText(this);

        WiFi ourwifi = new WiFi(this);
        String text = ourwifi.getWiFiSSID();
        wifi.setText(text);*/



        alert.setPositiveButton("Ok",new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                String value = input.getText().toString();
                Profile profile = new Profile(value);
                ProfileHandler.addItem(profile);
                result.add(profile.get_profilname());
                displayResultList();
            }
       });
       alert.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                // Canceled.
            }
       });

        alert.show();
        displayResultList();
}

    public void deleteButtonClicked(View view) {
        /*String input = Input.getText().toString();
        ProfileHandler.deleteItem(input);
        result.remove(input);
        displayResultList();*/
        AlertDialog.Builder alert = new AlertDialog.Builder(this);

        alert.setTitle("Deleting");
        alert.setMessage("Please enter name of your profile to delete");

        final EditText input = new EditText(this);
        alert.setView(input);

        alert.setPositiveButton("Ok",new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String value = input.getText().toString();
                ProfileHandler.deleteItem(value);
                result.remove(value);
                displayResultList();
            }
        });
        alert.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                // Canceled.
            }
        });

        alert.show();
        displayResultList();


    }

    private void openAndQueryDatabase() {
        ProfileHandler ProfileHandler = new ProfileHandler(this.getApplicationContext());
        Database = ProfileHandler.getWritableDatabase();
        Cursor c = Database.rawQuery("select Profile_name from Profile", null);

        if (c != null) {
            if (c.moveToFirst())
                do {
                    String Profilename = c.getString(c.getColumnIndex("Profile_name"));
                    result.add(Profilename);
                } while (c.moveToNext());
        }
    }


    private void displayResultList() {

        setListAdapter(new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, result));
        getListView().setTextFilterEnabled(true);
    }


}
