package com.example.rachel.myfirstapp;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class CharacterSheetFragment extends Fragment {
    Button myButton;
    charInfo mCallback;
    ArrayList<String> items = new ArrayList<>();
    EditText DMnameText;
    EditText DMtypeText;
    EditText DMclassText;

    public interface charInfo {
        void getCharInfo(ArrayList<String> info);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        // This makes sure that the container activity has implemented
        // the callback interface. If not, it throws an exception
        try {
            mCallback = (charInfo) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement charInfo");
        }
    }

    public void onListItemClick() {
        // Send the event to the host activity
        mCallback.getCharInfo(items);
    }

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.char_sheet_frag, container, false);

        myButton = (Button) view.findViewById(R.id.DMbutton);
        DMnameText = (EditText) view.findViewById(R.id.DMname_message);
        DMtypeText = (EditText) view.findViewById(R.id.DMtype_message);
        DMclassText = (EditText) view.findViewById(R.id.DMclass_message);


        myButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!DMclassText.getText().toString().equals("") && !DMnameText.getText().toString().equals("") && !DMclassText.getText().toString().equals("")){
                    String DMname = DMnameText.getText().toString();
                    String DMtype = DMtypeText.getText().toString();
                    String DMclass = DMclassText.getText().toString();

                    items.add(DMname);
                    items.add(DMtype);
                    items.add(DMclass);

                    onListItemClick();
                }
                else{
                    Toast.makeText(getActivity(), "Please enter a complete char sheet",
                            Toast.LENGTH_LONG).show();
                }
            }
        });
        return view;

    };

}