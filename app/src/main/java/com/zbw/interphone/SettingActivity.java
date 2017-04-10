package com.zbw.interphone;

import android.support.v4.app.Fragment;

/**
 * Created by zbw on 2017/4/4.
 */

public class SettingActivity extends SingleFragmentActivity {
    @Override
    protected Fragment createFragment() {
        return new SettingFragment();
    }
}
