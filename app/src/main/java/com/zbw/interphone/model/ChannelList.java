package com.zbw.interphone.model;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.UUID;

/**
 * Created by ZBW on 2017/4/5.
 */

public class ChannelList {
    private ArrayList<InterphoneChannel> channels;

    private static final String JSON_CHANNELS = "channels";

    public ChannelList(){

        channels = new ArrayList<>();
        for (int i = 0; i < 255; i++) {
            InterphoneChannel channel = new InterphoneChannel();
            channel.setNickname("#channel:" + i);
            channel.setValid(i % 2 == 0);
            channel.setChannelSpacing(1);
            channels.add(channel);
        }

    }

    public ChannelList(JSONObject json) throws JSONException {
        JSONArray array=json.getJSONArray(JSON_CHANNELS);
        channels = new ArrayList<>();
        for(int i=0;i<array.length();i++){
            InterphoneChannel channel=new InterphoneChannel(array.getJSONObject(i));
            channels.add(channel);
        }

    }

    public JSONObject toJSON() throws JSONException {
        JSONObject json = new JSONObject();
        JSONArray jsonArray = new JSONArray();
        for (int i = 0; i < channels.size(); i++) {
            jsonArray.put(channels.get(i).toJSON());
        }
        json.put(JSON_CHANNELS, jsonArray);
        return json;
    }

    public ArrayList<InterphoneChannel> getChannels() {
        return channels;
    }

    public InterphoneChannel getChannel(String id) {
        for (InterphoneChannel channel : channels) {
            if (channel.getId().equals(id)) {
                return channel;
            }
        }
        return null;
    }

    public boolean updateChannel(InterphoneChannel channel) {
        if (channel != null) {
            for (int i = 0; i < channels.size(); i++) {
                if (channels.get(i).getId().equals(channel.getId())) {
                    channels.set(i, channel);
                    return true;
                }
            }
        }
        return false;
    }


}
