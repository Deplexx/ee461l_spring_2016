package com.example.rachel.myfirstapp;

import android.app.Activity;
import android.content.SharedPreferences;
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

import dice.Die;

public class CharacterSheetFragment extends Fragment {
    Button myButton;
    Button AutoBtn;
    charInfo mCallback;
    ArrayList<String> items = new ArrayList<>();
    EditText DMnameText;
    EditText DMtypeText;
    EditText DMclassText;
    EditText levelText;
    EditText hpText;
    EditText acText;
    EditText spdText;
    EditText initText;
    EditText strText;
    EditText dexText;
    EditText conText;
    EditText intText;
    EditText wisText;
    EditText chaText;
    CharacterSheet chac;
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

        AutoBtn = (Button) view.findViewById(R.id.Auto_Fill_Btn);
        myButton = (Button) view.findViewById(R.id.button);
        DMnameText = (EditText) view.findViewById(R.id.name_message);
        DMtypeText = (EditText) view.findViewById(R.id.type_message);
        DMclassText = (EditText) view.findViewById(R.id.class_message);
        levelText = (EditText) view.findViewById(R.id.level_field);
        hpText = (EditText) view.findViewById(R.id.hp_field);
        acText = (EditText) view.findViewById(R.id.ac_field);
        spdText = (EditText) view.findViewById(R.id.spd_field);
        initText = (EditText) view.findViewById(R.id.init_field);
        strText = (EditText) view.findViewById(R.id.str_field);
        dexText = (EditText) view.findViewById(R.id.dex_field);
        conText = (EditText) view.findViewById(R.id.con_field);
        intText = (EditText) view.findViewById(R.id.int_field);
        wisText = (EditText) view.findViewById(R.id.wis_field);
        chaText = (EditText) view.findViewById(R.id.cha_field);


        SharedPreferences settings = this.getContext().getSharedPreferences(CharacterSheet.getPrefsName(1), 0);
        chac = CharacterSheet.loadSheet(settings);

        EditText nameText = DMnameText;
        EditText typeText = DMtypeText;
        EditText classText = DMclassText;

        nameText.setText(chac.name);
        typeText.setText(chac.race);
        classText.setText(chac.cclass);

        levelText.setText(String.valueOf(chac.level));
        hpText.setText(String.valueOf(chac.hp));
        acText.setText(String.valueOf(chac.ac));
        spdText.setText(String.valueOf(chac.spd));
        initText.setText(String.valueOf(chac.init));
        strText.setText(String.valueOf(chac.str));
        dexText.setText(String.valueOf(chac.dex));
        conText.setText(String.valueOf(chac.con));
        intText.setText(String.valueOf(chac.inl));
        wisText.setText(String.valueOf(chac.wis));
        chaText.setText(String.valueOf(chac.cha));


        AutoBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int Level = Die.d12.roll();
                int hp = 0;
                for(int i = 0; i < Level; i++){
                    hp += Die.d8.roll();
                }
                levelText.setText(String.valueOf(Level));
                hpText.setText(String.valueOf(hp));
                acText.setText(String.valueOf(Die.d6.roll() + 3));
                spdText.setText(String.valueOf(30));
                initText.setText(String.valueOf(Die.d6.roll()));

                strText.setText(String.valueOf((Die.d6.roll() + Die.d6.roll() + Die.d6.roll())));
                dexText.setText(String.valueOf((Die.d6.roll() + Die.d6.roll() + Die.d6.roll())));
                conText.setText(String.valueOf((Die.d6.roll() + Die.d6.roll() + Die.d6.roll())));
                intText.setText(String.valueOf((Die.d6.roll() + Die.d6.roll() + Die.d6.roll())));
                wisText.setText(String.valueOf((Die.d6.roll() + Die.d6.roll() + Die.d6.roll())));
                chaText.setText(String.valueOf((Die.d6.roll() + Die.d6.roll() + Die.d6.roll())));
            }
        });

        myButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean classs = !DMclassText.getText().toString().equals("");
                boolean name = !DMnameText.getText().toString().equals("");
                boolean type = !DMtypeText.getText().toString().equals("");
                boolean level = !levelText.getText().toString().equals("");
                boolean hp = !hpText.getText().toString().equals("");
                boolean ac = !acText.getText().toString().equals("");
                boolean spd = !spdText.getText().toString().equals("");
                boolean init = !initText.getText().toString().equals("");
                boolean str = !strText.getText().toString().equals("");
                boolean dex = !dexText.getText().toString().equals("");
                boolean con = !conText.getText().toString().equals("");
                boolean inteli = !intText.getText().toString().equals("");
                boolean wis = !wisText.getText().toString().equals("");
                boolean cha = !chaText.getText().toString().equals("");


                String name_message = DMnameText.getText().toString();
                String type_message = DMtypeText.getText().toString();
                String class_message = DMclassText.getText().toString();
                int levelNum = Integer.parseInt(levelText.getText().toString());
                int hpNum = Integer.parseInt(hpText.getText().toString());
                int acNum = Integer.parseInt(acText.getText().toString());
                int spdNum = Integer.parseInt(spdText.getText().toString());
                int initNum = Integer.parseInt(initText.getText().toString());
                int strNum = Integer.parseInt(strText.getText().toString());
                int dexNum = Integer.parseInt(dexText.getText().toString());
                int conNum = Integer.parseInt(conText.getText().toString());
                int intNum = Integer.parseInt(intText.getText().toString());
                int wisNum = Integer.parseInt(wisText.getText().toString());
                int chaNum = Integer.parseInt(chaText.getText().toString());

                chac.cclass = class_message;
                chac.name = name_message;
                chac.race = type_message;
                chac.level = levelNum;
                chac.hp = hpNum;
                chac.ac = acNum;
                chac.spd = spdNum;
                chac.init = initNum;
                chac.str = strNum;
                chac.dex = dexNum;
                chac.con = conNum;
                chac.inl = intNum;
                chac.wis = wisNum;
                chac.cha = chaNum;
                SharedPreferences settings = getContext().getSharedPreferences(CharacterSheet.getPrefsName(1), 0);
                chac.saveSheet(settings);


                if(classs && name && type && level && hp && ac && cha && wis && con && spd && init && str && dex && inteli){
                    String DMname = DMnameText.getText().toString();
                    String DMtype = DMtypeText.getText().toString();
                    String DMclass = DMclassText.getText().toString();
                    String Level_info = levelText.getText().toString();
                    String hp_info = hpText.getText().toString();
                    String ac_info = acText.getText().toString();
                    String spd_info = spdText.getText().toString();
                    String init_info = initText.getText().toString();
                    String str_info = strText.getText().toString();
                    String dex_info = dexText.getText().toString();
                    String con_info = conText.getText().toString();
                    String inteli_info = intText.getText().toString();
                    String wis_info = wisText.getText().toString();
                    String cha_info = chaText.getText().toString();

                    items.add(DMname);
                    items.add(DMtype);
                    items.add(DMclass);
                    items.add(Level_info);
                    items.add(hp_info);
                    items.add(ac_info);
                    items.add(spd_info);
                    items.add(init_info);
                    items.add(str_info);
                    items.add(dex_info);
                    items.add(con_info);
                    items.add(inteli_info);
                    items.add(wis_info);
                    items.add(cha_info);

                    onListItemClick();
                }
                else{
                    Toast.makeText(getActivity(), "Please enter a complete char sheet",
                            Toast.LENGTH_LONG).show();
                }
            }
        });
        return view;

    }

    public void DMautoStats() {

        int Level = Die.d12.roll();
        int hp = 0;
        for(int i = 0; i < Level; i++){
            hp += Die.d8.roll();
        }
        levelText.setText(String.valueOf(Level));
        hpText.setText(String.valueOf(hp));
        acText.setText(String.valueOf(Die.d6.roll() + 3));
        spdText.setText(String.valueOf(30));
        initText.setText(String.valueOf(Die.d6.roll()));

        strText.setText(String.valueOf((Die.d6.roll() + Die.d6.roll() + Die.d6.roll())));
        dexText.setText(String.valueOf((Die.d6.roll() + Die.d6.roll() + Die.d6.roll())));
        conText.setText(String.valueOf((Die.d6.roll() + Die.d6.roll() + Die.d6.roll())));
        intText.setText(String.valueOf((Die.d6.roll() + Die.d6.roll() + Die.d6.roll())));
        wisText.setText(String.valueOf((Die.d6.roll() + Die.d6.roll() + Die.d6.roll())));
        chaText.setText(String.valueOf((Die.d6.roll() + Die.d6.roll() + Die.d6.roll())));
    }

}