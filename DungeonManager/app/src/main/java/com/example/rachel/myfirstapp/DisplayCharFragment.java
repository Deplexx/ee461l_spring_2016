package com.example.rachel.myfirstapp;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

import java.util.ArrayList;


public class DisplayCharFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_display_char, container, false);
        // Inflate the layout for this fragment
        if (getArguments() != null){
            ArrayList<String> strtext = getArguments().getStringArrayList("info");

            String [] DM = {
                    "Name  : " + strtext.get(0),
                    "Race  : " + strtext.get(1),
                    "Class : " + strtext.get(2),
                    "Level : " + strtext.get(3),
                    "Init  : " + strtext.get(4),
                    "Spd   : " + strtext.get(5),
                    "Str   : " + strtext.get(6),
                    "Dex   : " + strtext.get(7),
                    "Con   : " + strtext.get(8),
                    "Int   : " + strtext.get(9),
                    "Wis   : " + strtext.get(10),
                    "Cha   : " + strtext.get(11),
            };

            ListAdapter theAdapter = new ArrayAdapter<String>(getActivity(), R.layout.row_layout1, R.id.textView1, DM);
            ListView theListView  = (ListView) rootView.findViewById(R.id.DMListView);
            theListView.setAdapter(theAdapter);
        }

        return rootView;
    }
}
