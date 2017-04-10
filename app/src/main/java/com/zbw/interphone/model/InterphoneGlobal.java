package com.zbw.interphone.model;

import android.content.Context;

/**
 * Created by ZBW on 2017/4/5.
 */

public class InterphoneGlobal {

    private InterphoneInfo mInfo;
    private InterphoneSetting mSetting;

    private static InterphoneGlobal sGloabl;
    private Context mAppContext;

    private InterphoneGlobal(Context context) {
        mAppContext = context;
        //先写死初始数据

        InterphoneInfo interphoneInfo = new InterphoneInfo();
        interphoneInfo.setType("H328");
        interphoneInfo.setChannel("150-174MHz");
        interphoneInfo.setVersion("1.0");
        mInfo = interphoneInfo;
        InterphoneSetting interphoneSetting = new InterphoneSetting();
        interphoneSetting.setJZLevel(4);
        interphoneSetting.setTimer(5);
        interphoneSetting.setSKZYLevel(3);
        interphoneSetting.setKeyA(5);
        interphoneSetting.setKeyB(0);
        interphoneSetting.setKeyC(0);
        interphoneSetting.setKeyD(0);
        interphoneSetting.setPowerSaving(false);
        interphoneSetting.setIdRecognition(false);
        mSetting = interphoneSetting;

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
}
