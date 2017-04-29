package com.zbw.interphone;


import android.app.Fragment;

import com.zbw.interphone.base.SingleFragmentActivity;

/**
 * Created by zbw on 2017/4/4.
 */

public class SettingsActivity extends SingleFragmentActivity {



    @Override
    protected Fragment createFragment() {
        return new SettingsFragment();
    }


}
