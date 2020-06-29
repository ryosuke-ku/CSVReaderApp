package com.example.csvreaderapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;

import java.util.List;

public class ListViewAdapter extends ArrayAdapter<ListData> {
    private LayoutInflater layoutInflater;

    public ListViewAdapter(Context context, int resource, List<ListData> objects) {
        super(context, resource, objects);
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ListData data = (ListData)getItem(position);
        if (null == convertView) {
            convertView = layoutInflater.inflate(R.layout.list_item, null);
        }

        TextView idText;
        TextView nameText;
        TextView yomiText;
        TextView kentyoText;
        TextView yomi_kentyoText;

        idText = (TextView)convertView.findViewById(R.id.id);
        nameText = (TextView)convertView.findViewById(R.id.name);
        yomiText = (TextView)convertView.findViewById(R.id.yomi);
        kentyoText = (TextView)convertView.findViewById(R.id.kentyo);
        yomi_kentyoText = (TextView)convertView.findViewById(R.id.yomi_kentyo);

        idText.setText(data.getDenpyoNumber());
        nameText.setText(data.getKariType());
        yomiText.setText(data.getKanriNumber());
        kentyoText.setText(data.getUserName());
        yomi_kentyoText.setText(data.getKanriName());

        return convertView;
    }
}