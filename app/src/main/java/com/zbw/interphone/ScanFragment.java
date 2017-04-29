package com.zbw.interphone;

import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zbw.interphone.view.LineSettingView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by ZBW on 2017/4/29.
 */

public class ScanFragment extends Fragment {
    private Activity mAppActivity;

    @BindView(R.id.viewScanSetting)
    public LineSettingView viewScanSetting;
    @BindView(R.id.viewScanList1)
    public LineSettingView viewScanList1;
    @BindView(R.id.viewScanList2)
    public LineSettingView viewScanList2;
    @BindView(R.id.viewScanList3)
    public LineSettingView viewScanList3;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mAppActivity = getActivity();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_scan, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @OnClick({R.id.viewScanSetting,
            R.id.viewScanList1,
            R.id.viewScanList2,
            R.id.viewScanList3})
    public void goActivity(final LineSettingView view) {
        Intent intent = null;
        switch (view.getId()) {
            case R.id.viewScanSetting:
                intent = new Intent(mAppActivity, ScanSettingActivity.class);
                break;
            case R.id.viewScanList1:
                intent = new Intent(mAppActivity, ScanListActivity.class);
                intent.putExtra(ScanListFragment.EXTRA_SCANLIST_ID, 1);
                break;
            case R.id.viewScanList2:
                intent = new Intent(mAppActivity, ScanListActivity.class);
                intent.putExtra(ScanListFragment.EXTRA_SCANLIST_ID, 2);
                break;
            case R.id.viewScanList3:
                intent = new Intent(mAppActivity, ScanListActivity.class);
                intent.putExtra(ScanListFragment.EXTRA_SCANLIST_ID, 3);
                break;

        }
        if (intent != null) {
            startActivity(intent);
        }

    }

}

