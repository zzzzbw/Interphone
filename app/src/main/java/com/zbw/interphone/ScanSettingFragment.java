package com.zbw.interphone;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.afollestad.materialdialogs.MaterialDialog;
import com.zbw.interphone.model.InterphoneGlobal;
import com.zbw.interphone.model.InterphoneScan;
import com.zbw.interphone.util.ResourceUtil;
import com.zbw.interphone.view.LineSettingView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by ZBW on 2017/4/29.
 */

public class ScanSettingFragment extends Fragment {
    private InterphoneGlobal mGlobal;
    private InterphoneScan mScan;
    private Activity mAppActivity;

    private ResourceUtil RUtil;

    @BindView(R.id.viewScanSpaceTime)
    public LineSettingView viewScanSpaceTime;
    @BindView(R.id.viewScanShelveTime)
    public LineSettingView viewScanShelveTime;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mAppActivity = getActivity();
        mGlobal = InterphoneGlobal.get(mAppActivity);
        mScan = mGlobal.getScan();
        RUtil = new ResourceUtil(mAppActivity);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_scan_setting, container, false);
        ButterKnife.bind(this, view);
        viewScanSpaceTime.setContent(RUtil.getShowResource(R.array.scanSpaceTime_values, mScan.getScanSpaceTime()));
        viewScanShelveTime.setContent(RUtil.getShowResource(R.array.scanShelveTime_values, mScan.getScanShelveTime()));
        return view;
    }

    @OnClick({R.id.viewScanSpaceTime, R.id.viewScanShelveTime})
    public void createChoiceListDialog(final LineSettingView view) {
        switch (view.getId()) {
            case R.id.viewScanSpaceTime:
                new MaterialDialog.Builder(mAppActivity)
                        .title(R.string.scan_space_time)
                        .items(R.array.scanSpaceTime_values)
                        .itemsCallbackSingleChoice(mScan.getScanSpaceTime(), new MaterialDialog.ListCallbackSingleChoice() {
                            @Override
                            public boolean onSelection(MaterialDialog dialog, View itemView, int which, CharSequence text) {
                                if (text == null) {
                                    return false;
                                }
                                int value = RUtil.getIndex(R.array.scanSpaceTime_values, text.toString());
                                mScan.setScanSpaceTime(value);
                                InterphoneGlobal.get(mAppActivity).setScan(mScan);
                                view.setContent(text.toString());
                                return true;
                            }
                        })
                        .positiveText(R.string.global_ok)
                        .show();
                break;
            case R.id.viewScanShelveTime:
                new MaterialDialog.Builder(mAppActivity)
                        .title(R.string.scan_shelve_time)
                        .items(R.array.scanShelveTime_values)
                        .itemsCallbackSingleChoice(mScan.getScanShelveTime(), new MaterialDialog.ListCallbackSingleChoice() {
                            @Override
                            public boolean onSelection(MaterialDialog dialog, View itemView, int which, CharSequence text) {
                                if (text == null) {
                                    return false;
                                }
                                int value = RUtil.getIndex(R.array.scanShelveTime_values, text.toString());
                                mScan.setScanShelveTime(value);
                                InterphoneGlobal.get(mAppActivity).setScan(mScan);
                                view.setContent(text.toString());
                                return true;
                            }
                        })
                        .positiveText(R.string.global_ok)
                        .show();
        }
    }
}
