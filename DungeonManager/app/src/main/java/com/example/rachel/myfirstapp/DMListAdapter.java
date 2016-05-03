package com.example.rachel.myfirstapp;

import android.content.ClipData;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import java.util.List;

public class DMListAdapter extends ArrayAdapter<ClipData.Item> {

    public DMListAdapter(Context context, int textViewResourceId) {
        super(context, textViewResourceId);
    }

    public DMListAdapter(Context context, int resource, List<ClipData.Item> items) {
        super(context, resource, items);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View v = convertView;

        if (v == null) {
            LayoutInflater vi;
            vi = LayoutInflater.from(getContext());
            v = vi.inflate(R.layout.char_sheet_frag, null);
        }

        ClipData.Item p = getItem(position);

        if (p != null) {
//            EditText tt1 = (EditText) v.findViewById(R.id.id);
//            TextView tt2 = (TextView) v.findViewById(R.id.categoryId);
//            TextView tt3 = (TextView) v.findViewById(R.id.description);

        }

        return v;
    }

}