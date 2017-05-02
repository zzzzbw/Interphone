package com.zbw.interphone.model;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by ZBW on 2017/4/5.
 */

public class ScanList {
    private final int scanCount = 16;
    private int[] scanCodeList;
    private int scanNum;

    private static final String JSON_SCANNUM = "scanNum";
    private static final String JSON_SCANCODELIST = "scanCodeList";

    public ScanList() {
        scanCodeList = new int[scanCount];
    }

    public ScanList(JSONObject json) throws JSONException {
        scanNum = json.getInt(JSON_SCANNUM);
        JSONArray array = json.getJSONArray(JSON_SCANCODELIST);
        scanCodeList = new int[array.length()];
        for (int i = 0; i < array.length(); i++) {
            scanCodeList[i] = array.getInt(i);
        }

    }

    public JSONObject toJSON() throws JSONException {
        JSONObject json = new JSONObject();
        json.put(JSON_SCANNUM, scanNum);
        JSONArray arrayCode = new JSONArray();
        for (int i = 0; i < scanCodeList.length; i++) {
            arrayCode.put(scanCodeList[i]);
        }
        json.put(JSON_SCANCODELIST, arrayCode);
        return json;
    }


    public int[] getScanCodeList() {
        return scanCodeList;
    }

    public void setScanCodeList(int[] scanCodeList) {
        this.scanCodeList = scanCodeList;
    }

    public ArrayList<Integer> getScanCodeArrayList() {
        ArrayList<Integer> arrayList = new ArrayList<>();
        for (int i = 0; i < scanCodeList.length; i++) {
            Integer ig = new Integer(scanCodeList[i]);
            arrayList.add(ig);
        }
        return arrayList;
    }

    public int getScanCode(int index) {
        return scanCodeList[index];
    }

    public void setScanCode(int index, int value) {
        scanCodeList[index] = value;
    }

    public int getScanNum() {
        return scanNum;
    }

    public void setScanNum(int scanNum) {
        this.scanNum = scanNum;
    }
}
