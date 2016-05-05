package com.wifidirect.milan.wifidirect.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.squareup.otto.Subscribe;
import com.wifidirect.milan.wifidirect.Events;
import com.wifidirect.milan.wifidirect.R;
import com.wifidirect.milan.wifidirect.WiFiDirectConstants;
import com.wifidirect.milan.wifidirect.WifiDirectApplication;
import com.wifidirect.milan.wifidirect.activities.MainActivity;
import com.wifidirect.milan.wifidirect.adapters.ChatAdapter;
import com.wifidirect.milan.wifidirect.listeners.MessageListener;
import com.wifidirect.milan.wifidirect.utils.MessageUtils;

/**
 * Created by milan on 26.11.15..
 */
public class ChatDirect extends Fragment implements MessageListener {
    private static final String TAG = "ChatDirect ";
    RecyclerView mRecyclerView;
    EditText mEditText;
    private ChatAdapter mAdapter;
    private String chatName;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container
            , Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_chat, container, false);

        // get name
        chatName = getArguments().getString("name");

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        mRecyclerView = (RecyclerView) getActivity().findViewById(R.id.recycler_view);
        mEditText =  (EditText) getActivity().findViewById(R.id.edittextmessage);

        // action bar
        ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle("Chat");

        // set subtitle on actionbar
        if (chatName != null) {
            ((MainActivity) getActivity()).mToolbar.setSubtitle(chatName);
        }

        // register otto
        WifiDirectApplication.getBus().register(this);

        // use a linear layout manager
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(layoutManager);

        // specify an adapter
        mAdapter = new ChatAdapter(getActivity());
        mRecyclerView.setAdapter(mAdapter);

        // add listener
        if (MainActivity.mService != null) {
            MainActivity.mService.addListener(this);
            MainActivity.mService.startMessageReceiver();
        }

        ImageButton _send = (ImageButton) getView().findViewById(R.id.imagebuttonsend);

        _send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // get message from edittext
                String message = mEditText.getText().toString();

                // clear edit text
                mEditText.setText("");

                // add message to adapter
                mAdapter.addMessage(message, true);

                // create json object
                String messageEncode = MessageUtils.createMessage(chatName, message
                        , MessageUtils.TYPE_MESSAGE, message.length(), "text");

                // send message
                MainActivity.mService.sendMessage(messageEncode);
            }
        });
    }

    @Override
    public void onMessageReceived(final String response) {
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                String[] messageDecode = MessageUtils.parseMessage(response);
                mAdapter.addMessage(messageDecode[3], false);
            }
        });

        Toast.makeText(getActivity(), "Message Received", Toast.LENGTH_SHORT);
    }


    @Override
    public void onConnected(boolean isConnected) {
        // Toast.makeText(getActivity(), "Connected", Toast.LENGTH_SHORT).show();
    }


    @Subscribe
    public void answerAvailable(Events.WifiState event) {
        if (MainActivity.mService == null) {
            return;
        }

        switch (event.state) {
            // if WiFi is eneble
            case WiFiDirectConstants.BROADCAST_ACTION_WIFI_ENABLE:
                Toast.makeText(getActivity(), "Your WiFi is enabled", Toast.LENGTH_SHORT).show();
                break;

            // if WiFi is disable
            case WiFiDirectConstants.BROADCAST_ACTION_WIFI_DISABLE:
                Toast.makeText(getActivity(), "Your Wifi is disabled!", Toast.LENGTH_SHORT).show();
                getActivity().onBackPressed();
                break;

            default:
                break;
        }

    }


    // Main Menu
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v
            , ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            getActivity().onBackPressed();
        }

        return super.onOptionsItemSelected(item);
    }



    @Override
    public void onDestroyView() {
        super.onDestroyView();


        WifiDirectApplication.getBus().unregister(this);

        // stop message hendler
        if(MainActivity.mService != null) {
            MainActivity.mService.stopMessageReceiver();
        }

    }
}
