package com.zbw.interphone;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.zbw.interphone.model.ChannelList;
import com.zbw.interphone.model.InterphoneChannel;

import java.util.ArrayList;

/**
 * Created by ZBW on 2017/4/9.
 */

public class ChannelPageActivity extends AppCompatActivity {
    private ViewPager viewPager;
    private ArrayList<InterphoneChannel> channels;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewPager = new ViewPager(this);
        viewPager.setId(R.id.viewPager);
        setContentView(viewPager);

        channels = ChannelList.get(this).getChannels();

        FragmentManager fm = getSupportFragmentManager();
        viewPager.setAdapter(new FragmentStatePagerAdapter(fm) {
            @Override
            public Fragment getItem(int position) {
                InterphoneChannel channel = channels.get(position);
                setTitle(channel.getNickname());
                return ChannelFragment.newInstance(channel.getId());
            }

            @Override
            public int getCount() {
                return channels.size();
            }
        });
    }
}
