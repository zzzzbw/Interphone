package com.zbw.interphone.model;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by ZBW on 2017/4/29.
 */

public class InterphoneScan {
    private int scanSpaceTime;
    private int scanShelveTime;
    private ScanList[] scanLists;
    private final int scanListCount = 3;

    private static final String JSON_SCANSPACETIME = "scanSpaceTime";
    private static final String JSON_SCANSHELVETIME = "scanShelveTime";
    private static final String JSON_SCANLISTS = "scanLists";

    public InterphoneScan() {
        scanLists = new ScanList[scanListCount];
        for (int i = 0; i < scanListCount; i++) {
            scanLists[i] = new ScanList();
            if (i == 0) {
                int[] a = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16};
                scanLists[i].setScanCodeList(a);
            }
        }
    }


    public InterphoneScan(JSONObject json) throws JSONException {
        scanSpaceTime = json.getInt(JSON_SCANSPACETIME);
        scanShelveTime = json.getInt(JSON_SCANSHELVETIME);
        JSONArray array = json.getJSONArray(JSON_SCANLISTS);
        scanLists = new ScanList[array.length()];
        for (int i = 0; i < array.length(); i++) {
            scanLists[i] = new ScanList(array.getJSONObject(i));
        }

    }


    public JSONObject toJSON() throws JSONException {
        JSONObject json = new JSONObject();
        json.put(JSON_SCANSPACETIME, scanSpaceTime);
        json.put(JSON_SCANSHELVETIME, scanShelveTime);
        JSONArray arrayScanList = new JSONArray();
        for (int i = 0; i < scanLists.length; i++) {
            arrayScanList.put(scanLists[i].toJSON());
        }
        json.put(JSON_SCANLISTS, arrayScanList);
        return json;
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
