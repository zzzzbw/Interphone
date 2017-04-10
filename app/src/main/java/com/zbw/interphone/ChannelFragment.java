package com.zbw.interphone;

import android.os.Bundle;
import android.support.v4.app.Fragment;

import java.util.UUID;

/**
 * Created by ZBW on 2017/4/9.
 */

public class ChannelFragment extends Fragment {
    public static final String EXTRA_CHANNEL_ID = "com.zbw.interphont.channel_id";

    public static ChannelFragment newInstance(UUID channelId) {
        Bundle args = new Bundle();
        args.putSerializable(EXTRA_CHANNEL_ID, channelId);
        ChannelFragment fragment = new ChannelFragment();
        fragment.setArguments(args);
        return fragment;
    }
}
