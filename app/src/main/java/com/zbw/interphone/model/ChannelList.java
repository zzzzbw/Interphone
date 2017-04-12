package com.zbw.interphone.model;

import android.content.Context;

import java.util.ArrayList;
import java.util.UUID;

/**
 * Created by ZBW on 2017/4/5.
 */

public class ChannelList {
    private ArrayList<InterphoneChannel> channels;

    private static ChannelList sChannelList;
    private Context mAppContext;

    private ChannelList(Context context) {
        mAppContext = context;
        channels = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            InterphoneChannel channel = new InterphoneChannel();
            channel.setNickname("#channel:" + i);
            channel.setValid(i % 2 == 0);
            channel.setChannelSpacing(1);
            channels.add(channel);
        }
    }

    public static ChannelList get(Context context) {
        if (sChannelList == null) {
            sChannelList = new ChannelList(context);
        }
        return sChannelList;
    }

    public ArrayList<InterphoneChannel> getChannels() {
        return channels;
    }

    public InterphoneChannel getChannel(UUID id){
        for(InterphoneChannel channel:channels){
            if(channel.getId().equals(id)){
                return channel;
            }
        }
        return null;
    }


}
