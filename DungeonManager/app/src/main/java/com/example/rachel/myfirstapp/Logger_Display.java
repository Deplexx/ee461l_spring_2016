package com.example.rachel.myfirstapp;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Spinner;

public class Logger_Display extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private Spinner Stat_Spinner;
    private Button Execute_Button;
    private EditText Char_Name_Field;
    private EditText Stat_Mod_Field;
    private ListView Cmd_View;

    private CommandLogger logger;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_logger__display, container, false);

        logger = CommandLogger.getInstance();

        String log[] = logger.getLog();


        Char_Name_Field = (EditText) view.findViewById(R.id.Char_Name_Field);
        Stat_Mod_Field = (EditText) view.findViewById(R.id.Stat_Mod_Magnitude_Field);
        Execute_Button = (Button) view.findViewById(R.id.Execute_Mod_Btn);
        Cmd_View = (ListView) view.findViewById(R.id.Log_View);

        Execute_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String sel = (String) Stat_Spinner.getSelectedItem();
                String name = Char_Name_Field.getText().toString();
                int mod = Integer.parseInt(Stat_Mod_Field.getText().toString());
                Command c = null;
                if(sel.equals("HP")){
                    c = new HpModCmd(name, mod);
                }else if(sel.equals("STR")){
                    c = new StrModCmd(name, mod);
                }else if(sel.equals("DEX")){
                    c = new DexModCmd(name, mod);
                }else if(sel.equals("CON")){
                    c = new ConModCmd(name, mod);
                }else if(sel.equals("INT")){
                    c = new IntModCmd(name, mod);
                }else if(sel.equals("WIS")){
                    c = new WisModCmd(name, mod);
                }else if(sel.equals("CHA")){
                    c = new ChaModCmd(name, mod);
                }

                if(c == null){
                    return;
                }

                logger.logCommand(c);

                ListAdapter theAdapter = new ArrayAdapter<String>(getActivity(), R.layout.row_layout1, R.id.textView1, logger.getLog());
                Cmd_View.setAdapter(theAdapter);

            }
        });

        Spinner spinner = (Spinner) view.findViewById(R.id.Stat_Mod_Spinner);
        Stat_Spinner = spinner;
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getActivity(),
                R.array.DM_Modify_Stats, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);


        //fragView = getView();

        if(log != null) {
            ListAdapter theAdapter = new ArrayAdapter<String>(getActivity(), R.layout.row_layout1, R.id.textView1, log);
            Cmd_View.setAdapter(theAdapter);
        }

        return view;

    }

}
