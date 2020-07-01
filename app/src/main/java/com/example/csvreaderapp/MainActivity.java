package com.example.csvreaderapp;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        CsvReader parser = new CsvReader();
        parser.reader(getApplicationContext(), "data.csv");

        String str = String.valueOf(parser.objects.size());


        Log.d("debug",str);

        DBHelper helper = new DBHelper(MainActivity.this);
        SQLiteDatabase db = helper.getReadableDatabase();

        for(int i=1; i < parser.objects.size() ; i++){
            Log.d("debug", String.valueOf(i) + ":" + String.valueOf(parser.objects.get(i).getDenpyoNumber()));
            ContentValues values = new ContentValues();
            values.put(DBHelper.DENPYO_NUMBER, String.valueOf(parser.objects.get(i).getDenpyoNumber()));
            values.put(DBHelper.KANRI_TYPE, String.valueOf(parser.objects.get(i).getKariType()));
            values.put(DBHelper.KANRI_NUMBER, String.valueOf(parser.objects.get(i).getKanriNumber()));
            values.put(DBHelper.USER_NAME, String.valueOf(parser.objects.get(i).getUserName()));
            values.put(DBHelper.KANRI_NAME, String.valueOf(parser.objects.get(i).getKanriName()));
            values.put(DBHelper.LOCATION, String.valueOf(parser.objects.get(i).getLocation()));
            values.put(DBHelper.BUILDING_NAME, String.valueOf(parser.objects.get(i).getBuildingName()));
            values.put(DBHelper.DETAIL_LOCATION, String.valueOf(parser.objects.get(i).getDetailLocation()));
            values.put(DBHelper.REMARKS, String.valueOf(parser.objects.get(i).getRemarks()));
            values.put(DBHelper.KANRI_STATUS, String.valueOf(parser.objects.get(i).getKanriStatus()));
            values.put(DBHelper.TYOUSA_RESULT, String.valueOf(parser.objects.get(i).getTyosaResult()));
            values.put(DBHelper.TYOUSA_DATE, getNowDate());
            values.put(DBHelper.TYOUSA_DID_NAME, String.valueOf(parser.objects.get(i).getTyosaDidName()));
            values.put(DBHelper.TYOUSA_DID_NAME_CODE, String.valueOf(parser.objects.get(i).getTyosaDidNameCode()));

            db.insert(DBHelper.DB_TABLE, null, values);
        }

        ListViewAdapter listViewAdapter = new ListViewAdapter(this, 0, parser.objects);
        ListView listView = (ListView)findViewById(R.id.list);
        listView.setAdapter(listViewAdapter);

        System.out.println("DATE:" + getNowDate());

//        db.delete(DBHelper.DB_TABLE, null, null);

    }

    public static String getNowDate(){
        final DateFormat df = new SimpleDateFormat("yyyy/MM/dd");
        final Date date = new Date(System.currentTimeMillis());
        return df.format(date);
    }

}
