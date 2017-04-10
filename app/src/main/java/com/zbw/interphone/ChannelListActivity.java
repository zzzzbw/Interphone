package com.zbw.interphone;

import android.support.v4.app.Fragment;

/**
 * Created by ZBW on 2017/4/5.
 */

public class ChannelListActivity extends SingleFragmentActivity {
    @Override
    protected Fragment createFragment() {
        return new ChannelListFragment();
    }
}
