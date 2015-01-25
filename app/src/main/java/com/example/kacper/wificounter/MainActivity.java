package com.example.kacper.wificounter;

import android.app.ListActivity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
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
        Input = (EditText) findViewById(R.id.Input);
        Text = (TextView) findViewById(R.id.Text);
        Input.setText("");

    }
    public void addButtonClicked(View view)  {
    Profile profile = new Profile(Input.getText().toString());
        ProfileHandler.addItem(profile);
        result.add(profile.get_profilname());
        displayResultList();
    }
    public void deleteButtonClicked(View view) {
        String input = Input.getText().toString();
        ProfileHandler.deleteItem(input);
        result.remove(input);
        displayResultList();

    }

    private void openAndQueryDatabase() {
       ProfileHandler ProfileHandler = new ProfileHandler(this.getApplicationContext());
        Database = ProfileHandler.getWritableDatabase();
        Cursor c = Database.rawQuery("select Profile_name from Profile",null);

        if(c !=null)
        {
            if (c.moveToFirst())
                do {
                    String Profilename = c.getString(c.getColumnIndex("Profile_name"));
                    result.add(Profilename);
                }while(c.moveToNext());
        }
    }



    private void displayResultList() {

        setListAdapter(new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, result));
        getListView().setTextFilterEnabled(true);
    }


}
