package com.zbw.interphone;


import android.app.Fragment;

import com.zbw.interphone.base.SingleFragmentActivity;

public class MainActivity extends SingleFragmentActivity {

    @Override
    protected Fragment createFragment() {
        return new MainFragment();
    }
}
