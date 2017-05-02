package com.zbw.interphone;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.zbw.interphone.model.InterphoneChannel;
import com.zbw.interphone.model.InterphoneGlobal;

import java.util.ArrayList;
import java.util.UUID;

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

        channels = InterphoneGlobal.get(this).getChannelList().getChannels();

        FragmentManager fm = getSupportFragmentManager();
        viewPager.setAdapter(new FragmentStatePagerAdapter(fm) {
            @Override
            public Fragment getItem(int position) {
                InterphoneChannel channel = channels.get(position);
                return ChannelFragment.newInstance(channel.getId());
            }

            @Override
            public int getCount() {
                return channels.size();
            }
        });

        String channelId = (String) getIntent().getSerializableExtra(ChannelFragment.EXTRA_CHANNEL_ID);
        for (int i = 0; i < channels.size(); i++) {
            if (channels.get(i).getId().equals(channelId)) {
                viewPager.setCurrentItem(i);
                setTitle(channels.get(i).getNickname());
                break;
            }
        }

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                InterphoneChannel channel = channels.get(position);
                if (channel.getNickname() != null) {
                    setTitle(channel.getNickname());
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }
}
