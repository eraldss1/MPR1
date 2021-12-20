package com.example.utsmpr1;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    String[] school = {
            "Tadika Mesra",
            "Mail Primary School",
            "B Package",
            "Sky High School"
    };

    String[] year = {
            "2005",
            "2012",
            "2015",
            "2018"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);

        ListView listView = findViewById(R.id.list_view);
        ArrayList<HashMap<String, String>> arrayList = new ArrayList<>();
        for (int i = 0; i < school.length; i++) {
            HashMap<String, String> hashMap = new HashMap<>();
            hashMap.put("school", school[i]);
            hashMap.put("year", year[i] + "");
            arrayList.add(hashMap);
        }
        String[] from = {"school", "year"};
        int[] to = {R.id.school, R.id.year};
        SimpleAdapter adapter = new SimpleAdapter(
                this, arrayList,
                R.layout.listview_design,
                from, to
        );
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int i, long id) {
                Toast.makeText(getApplicationContext(), school[i],
                        Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case R.id.action_help:
                Toast.makeText(this, R.string.toast_help,Toast.LENGTH_SHORT).show();
                return true;

            case R.id.action_permission:
                Intent permissionIntent = new Intent(MainActivity.this, Permission.class);
                startActivity(permissionIntent);
                return true;

            case R.id.action_multimedia:
                Intent multimediaIntent = new Intent(MainActivity.this, Multimedia.class);
                startActivity(multimediaIntent);
                return true;

            case R.id.action_firebase:
                Intent firebaseIntent = new Intent(MainActivity.this, FirebaseAct.class);
                startActivity(firebaseIntent);
                return true;

            case R.id.action_about:
                snackBar();
                return true;

            case R.id.action_exit:
                close();
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void snackBar() {
        Snackbar snackbar = Snackbar.make(findViewById(R.id.acitivity_main),
                R.string.snackbar_about,
                Snackbar.LENGTH_SHORT);
        snackbar.show();
    }

    public void close() {
        final AlertDialog.Builder tutup = new AlertDialog.Builder(this);
        tutup.setMessage(R.string.close_dialog)
                .setNegativeButton(R.string.no, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                })
                .setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }
                }).create().show();
    }
}