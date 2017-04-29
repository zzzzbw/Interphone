package com.zbw.interphone.model;

import android.widget.ArrayAdapter;

import java.util.ArrayList;

/**
 * Created by ZBW on 2017/4/5.
 */

public class ScanList {
    private final int scanCount = 16;
    private int[] scanCodeList;

    public ScanList() {
        scanCodeList = new int[scanCount];
    }

    public int[] getScanCodeList() {
        return scanCodeList;
    }

    public void setScanCodeList(int[] scanCodeList) {
        this.scanCodeList = scanCodeList;
    }

    public ArrayList<Integer> getScanCodeArrayList() {
        ArrayList<Integer> arrayList = new ArrayList<>();
        for (int i = 0; i < scanCount; i++) {
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
}
