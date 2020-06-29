package com.example.csvreaderapp;

import android.content.Context;
import android.content.res.AssetManager;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class CsvReader {
    List<ListData> objects = new ArrayList<ListData>();
    public void reader(Context context, String filename) {
        AssetManager assetManager = context.getResources().getAssets();
        try {
            // CSVファイルの読み込み
            InputStream inputStream = assetManager.open(filename);
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
            BufferedReader bufferReader = new BufferedReader(inputStreamReader);
            String line;
            while ((line = bufferReader.readLine()) != null) {

                //カンマ区切りで１つづつ配列に入れる
                ListData data = new ListData();
                String[] RowData = line.split(",");

                System.out.println("列の長さ：" + RowData.length);

                //CSVの左([0]番目)から順番にセット
                data.setDenpyoNumber(RowData[0]);
                data.setKanriType(RowData[1]);
                data.setKanriNumber(RowData[2]);
                data.setUserName(RowData[3]);
                data.setKanriName(RowData[4]);
                data.setBuildingName(RowData[5]);
                data.setLocation(RowData[6]);
                data.setDetailLocation(RowData[7]);
                data.setRemarks(RowData[8]);
                data.setKanriStatus(RowData[9]);
                data.setTyosaResult(RowData[10]);
                data.setTyosaDate(RowData[11]);
                data.setTyosaDidName(RowData[12]);
                data.setTyosaDidNameCode(RowData[13]);


                objects.add(data);
            }
            bufferReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}