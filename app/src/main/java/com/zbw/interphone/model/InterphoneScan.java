package com.zbw.interphone.model;

/**
 * Created by ZBW on 2017/4/29.
 */

public class InterphoneScan {
    private int scanSpaceTime;
    private int scanShelveTime;
    private ScanList[] scanLists;
    private final int scanListCount = 3;

    public InterphoneScan() {
        scanLists = new ScanList[scanListCount];
        for (int i = 0; i < scanListCount; i++) {
            scanLists[i] = new ScanList();
        }
    }

    public int getScanSpaceTime() {
        return scanSpaceTime;
    }

    public void setScanSpaceTime(int scanSpaceTime) {
        this.scanSpaceTime = scanSpaceTime;
    }

    public int getScanShelveTime() {
        return scanShelveTime;
    }

    public void setScanShelveTime(int scanShelveTime) {
        this.scanShelveTime = scanShelveTime;
    }

    public ScanList[] getScanLists() {
        return scanLists;
    }

    public void setScanLists(ScanList[] scanLists) {
        this.scanLists = scanLists;
    }

    public ScanList getScanList(int id) {
        return scanLists[id];
    }
}
