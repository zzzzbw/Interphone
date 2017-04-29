package com.zbw.interphone.model;

import android.content.Context;

/**
 * Created by ZBW on 2017/4/5.
 */

public class InterphoneGlobal {

    private InterphoneInfo mInfo;
    private InterphoneSetting mSetting;
    private DTMFSetting mDTMFSetting;
    private InterphoneScan mScan;

    private static InterphoneGlobal sGloabl;
    private Context mAppContext;

    private InterphoneGlobal(Context context) {
        mAppContext = context;
        //先写死初始数据

        mInfo = new InterphoneInfo();
        mInfo.setType("H328");
        mInfo.setChannel("150-174MHz");
        mInfo.setVersion("1.0");

        mSetting = new InterphoneSetting();
        mSetting.setJZLevel(4);
        mSetting.setTimer(5);
        mSetting.setSKZYLevel(3);
        mSetting.setKeyA(5);
        mSetting.setKeyB(0);
        mSetting.setKeyC(0);
        mSetting.setKeyD(0);
        mSetting.setPowerSaving(false);
        mSetting.setIdRecognition(false);

        mDTMFSetting = new DTMFSetting();
        mDTMFSetting.setLaunchSpeed(3);
        mDTMFSetting.setFirstLaunchDelay(4);
        mDTMFSetting.setFirstLaunchTime(1);

        mScan = new InterphoneScan();
        mScan.setScanSpaceTime(0);
        mScan.setScanShelveTime(9);
    }

    public static InterphoneGlobal get(Context context) {
        if (sGloabl == null) {
            sGloabl = new InterphoneGlobal(context);
        }
        return sGloabl;
    }

    public InterphoneInfo getInfo() {
        return mInfo;
    }

    public void setInfo(InterphoneInfo mInfo) {
        this.mInfo = mInfo;
    }

    public InterphoneSetting getSetting() {
        return mSetting;
    }

    public void setSetting(InterphoneSetting mSetting) {
        this.mSetting = mSetting;
    }

    public DTMFSetting getDTMFSetting() {
        return mDTMFSetting;
    }

    public void setDTMFSetting(DTMFSetting mDTMFSetting) {
        this.mDTMFSetting = mDTMFSetting;
    }

    public InterphoneScan getScan() {
        return mScan;
    }

    public void setScan(InterphoneScan mScan) {
        this.mScan = mScan;
    }
}
