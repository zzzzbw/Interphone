package com.zbw.interphone;

import android.app.Activity;
import android.app.ListFragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.TextView;

import com.zbw.interphone.model.ChannelList;
import com.zbw.interphone.model.InterphoneChannel;
import com.zbw.interphone.model.InterphoneGlobal;

import java.util.ArrayList;

/**
 * Created by ZBW on 2017/4/5.
 */

public class ChannelListFragment extends ListFragment {
    private Activity mAppActivity;
    private InterphoneGlobal mGlobal;
    private ChannelList mChannelList;
    private ArrayList<InterphoneChannel> mChannels;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mAppActivity = getActivity();
        mGlobal = InterphoneGlobal.get(mAppActivity);
        mChannelList = mGlobal.getChannelList();
        mChannels = mChannelList.getChannels();

        ArrayAdapter<InterphoneChannel> arrayAdapter = new ChannelAdapter(mChannels);
        setListAdapter(arrayAdapter);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        InterphoneChannel channel = ((ChannelAdapter) getListAdapter()).getItem(position);
        Intent intent = new Intent(getActivity(), ChannelPageActivity.class);
        intent.putExtra(ChannelFragment.EXTRA_CHANNEL_ID, channel.getId());
        startActivity(intent);
    }

    private class ChannelAdapter extends ArrayAdapter<InterphoneChannel> {
        public ChannelAdapter(ArrayList<InterphoneChannel> channels) {
            super(mAppActivity, 0, channels);
        }

        @NonNull
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if (convertView == null) {
                convertView = mAppActivity.getLayoutInflater().inflate(R.layout.list_item_channel, null);
            }
            InterphoneChannel channel = getItem(position);
            TextView titleTextView = (TextView) convertView.findViewById(R.id.channel_list_item_titleTextView);
            titleTextView.setText("信道列表" + position);
            TextView nicknameTextView = (TextView) convertView.findViewById(R.id.channel_list_item_nicknameTextView);
            nicknameTextView.setText(channel.getNickname());
            CheckBox validCheckBox = (CheckBox) convertView.findViewById(R.id.channel_list_item_validCheckBox);
            validCheckBox.setChecked(channel.isValid());
            return convertView;
        }
    }
}
