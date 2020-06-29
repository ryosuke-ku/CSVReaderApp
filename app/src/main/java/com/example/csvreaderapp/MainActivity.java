package com.example.csvreaderapp;

import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        CsvReader parser = new CsvReader();
        parser.reader(getApplicationContext());

        String str = String.valueOf(parser.objects.size());
        String str1 = String.valueOf(parser.objects.get(0).getName());

        Log.d("debug",str);
        Log.d("debug",str1);

        for(int i=0; i < parser.objects.size() ; i++){
            Log.d("debug", String.valueOf(i) + ":" + String.valueOf(parser.objects.get(i).getName()));
        }

        ListViewAdapter listViewAdapter = new ListViewAdapter(this, 0, parser.objects);
        ListView listView = (ListView)findViewById(R.id.list);
        listView.setAdapter(listViewAdapter);
    }
}
