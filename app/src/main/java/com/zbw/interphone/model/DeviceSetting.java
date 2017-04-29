package com.zbw.interphone.model;

import android.content.Context;

/**
 * Created by zbw on 2017/4/23.
 */

public class DeviceSetting {
    private static DeviceSetting sDeviceSetting;
    private Context mAppContext;

    private DeviceSetting(Context context) {
        mAppContext = context;
        device="";
        listenTime = MIDDLE_LISTEN_TIME;
    }

    public static DeviceSetting get(Context context) {
        if (sDeviceSetting == null) {
            sDeviceSetting = new DeviceSetting(context);
        }
        return sDeviceSetting;
    }

    private static final String DEVICE_PRE="/dev/";

    private static final int NON_STOP_LISTEN_TIME = -1;
    private static final int SHORT_LISTEN_TIME = 1000;
    private static final int MIDDLE_LISTEN_TIME = 1000000;
    private static final int LONG_MIDDLE_TIME = 1000000000;

    private String device;
    private int listenTime;
    private int listenTimeType;

    public String getDevice() {
        return device;
    }

    public void setDevice(String device) {
        this.device=DEVICE_PRE+device;
    }



    public int getListenTime() {
        return listenTime;
    }

    public void setListenTime(int type) {
        switch (type) {
            case 0:
                this.listenTime = NON_STOP_LISTEN_TIME;
                break;
            case 1:
                this.listenTime = SHORT_LISTEN_TIME;
                break;
            case 2:
                this.listenTime = MIDDLE_LISTEN_TIME;
                break;
            case 3:
                this.listenTime = LONG_MIDDLE_TIME;
                break;
        }
    }

    public int getListenTimeType() {
        switch (listenTime) {
            case NON_STOP_LISTEN_TIME:
                return 0;
            case SHORT_LISTEN_TIME:
                return 1;
            case MIDDLE_LISTEN_TIME:
                return 2;
            case LONG_MIDDLE_TIME:
                return 3;
        }
        return MIDDLE_LISTEN_TIME;
    }
}
