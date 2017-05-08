package com.zbw.interphone.model;

import android.content.Context;

import com.zbw.interphone.util.InterphoneJSONSerializerUtil;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

/**
 * Created by ZBW on 2017/4/5.
 */

public class InterphoneGlobal {

    private InterphoneInfo mInfo;
    private InterphoneSetting mSetting;
    private DTMFSetting mDTMFSetting;
    private InterphoneScan mScan;
    private ChannelList mChannelList;

    private InterphoneJSONSerializerUtil mSerializer;
    private static final String FILENAME = "interphone.json";

    private static final String JSON_INFO = "info";
    private static final String JSON_SETTING = "setting";
    private static final String JSON_DTMFSETTING = "DTMFsetting";
    private static final String JSON_SCAN = "scan";
    private static final String JSON_CHANNELLIST = "channelList";

    private static InterphoneGlobal sGloabl;
    private Context mAppContext;


    //用于在无法读取文件时使用
    private InterphoneGlobal(Context context, int i) {
        mAppContext = context;
        mSerializer = new InterphoneJSONSerializerUtil(mAppContext, FILENAME);

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

        mChannelList = new ChannelList();
    }


    private InterphoneGlobal(Context context) throws IOException, JSONException {
        mAppContext = context;
        mSerializer = new InterphoneJSONSerializerUtil(mAppContext, FILENAME);

        JSONObject json = mSerializer.loadGlobal();
        if (json == null) {
            throw new JSONException("No json");
        }

        JSONObject jsonInfo = json.getJSONObject(JSON_INFO);
        mInfo = new InterphoneInfo(jsonInfo);
        JSONObject jsonSetting = json.getJSONObject(JSON_SETTING);
        mSetting = new InterphoneSetting(jsonSetting);
        JSONObject jsonDTMFSetting = json.getJSONObject(JSON_DTMFSETTING);
        mDTMFSetting = new DTMFSetting(jsonDTMFSetting);
        JSONObject jsonScan = json.getJSONObject(JSON_SCAN);
        mScan = new InterphoneScan(jsonScan);
        JSONObject jsonChannelList = json.getJSONObject(JSON_CHANNELLIST);
        mChannelList = new ChannelList(jsonChannelList);

    }


    public static InterphoneGlobal get(Context context) {
        if (sGloabl == null) {
            try {
                sGloabl = new InterphoneGlobal(context);
            } catch (IOException e) {
                e.printStackTrace();
                sGloabl = new InterphoneGlobal(context, 1);
            } catch (JSONException e) {
                e.printStackTrace();
                sGloabl = new InterphoneGlobal(context, 1);
            }
        }
        return sGloabl;
    }


    public JSONObject toJSON() throws JSONException {
        JSONObject json = new JSONObject();
        json.put(JSON_INFO, mInfo.toJSON());
        json.put(JSON_SETTING, mSetting.toJSON());
        json.put(JSON_DTMFSETTING, mDTMFSetting.toJSON());
        json.put(JSON_SCAN, mScan.toJSON());
        json.put(JSON_CHANNELLIST, mChannelList.toJSON());
        return json;
    }

    public boolean saveInterphone() {
        try {
            mSerializer.saveInterphone(sGloabl);
            return true;
        } catch (JSONException e) {
            e.printStackTrace();
            return false;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
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

    public ChannelList getChannelList() {
        return mChannelList;
    }

    public void setChannelList(ChannelList ChannelList) {
        this.mChannelList = mChannelList;
    }
}
