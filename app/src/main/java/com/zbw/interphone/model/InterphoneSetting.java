package com.zbw.interphone.model;


import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by zbw on 2017/4/4.
 */

public class InterphoneSetting {
    private int JZLevel;
    private int timer;
    private int SKZYLevel;
    private int keyA;
    private int keyB;
    private int keyC;
    private int keyD;
    private boolean powerSaving;
    private boolean idRecognition;

    private static final String JSON_JZLEVEL = "JZLevel";
    private static final String JSON_TIMER = "timer";
    private static final String JSON_SKZYLEVEL = "SKZYLevel";
    private static final String JSON_KEYA = "keyA";
    private static final String JSON_KEYB = "keyB";
    private static final String JSON_KEYC = "keyC";
    private static final String JSON_KEYD = "keyD";
    private static final String JSON_POWERSAVING = "powerSaving";
    private static final String JSON_IDRECOGNITION = "idRecognition";


    public InterphoneSetting(){};

    public InterphoneSetting(JSONObject json) throws JSONException {
        JZLevel = json.getInt(JSON_JZLEVEL);
        timer = json.getInt(JSON_TIMER);
        SKZYLevel = json.getInt(JSON_SKZYLEVEL);
        keyA = json.getInt(JSON_KEYA);
        keyB = json.getInt(JSON_KEYB);
        keyC = json.getInt(JSON_KEYC);
        keyD = json.getInt(JSON_KEYD);
        powerSaving = json.getBoolean(JSON_POWERSAVING);
        idRecognition = json.getBoolean(JSON_IDRECOGNITION);
    }

    public JSONObject toJSON() throws JSONException {
        JSONObject json = new JSONObject();
        json.put(JSON_JZLEVEL, JZLevel);
        json.put(JSON_TIMER, timer);
        json.put(JSON_SKZYLEVEL, SKZYLevel);
        json.put(JSON_KEYA, keyA);
        json.put(JSON_KEYB, keyB);
        json.put(JSON_KEYC, keyC);
        json.put(JSON_KEYD, keyD);
        json.put(JSON_POWERSAVING, powerSaving);
        json.put(JSON_IDRECOGNITION, idRecognition);
        return json;
    }



    public boolean isIdRecognition() {
        return idRecognition;
    }

    public void setIdRecognition(boolean idRecognition) {
        this.idRecognition = idRecognition;
    }

    public int getJZLevel() {
        return JZLevel;
    }

    public void setJZLevel(int JZLevel) {
        this.JZLevel = JZLevel;
    }

    public int getKeyA() {
        return keyA;
    }

    public void setKeyA(int keyA) {
        this.keyA = keyA;
    }

    public int getKeyB() {
        return keyB;
    }

    public void setKeyB(int keyB) {
        this.keyB = keyB;
    }

    public int getKeyC() {
        return keyC;
    }

    public void setKeyC(int keyC) {
        this.keyC = keyC;
    }

    public int getKeyD() {
        return keyD;
    }

    public void setKeyD(int keyD) {
        this.keyD = keyD;
    }

    public boolean isPowerSaving() {
        return powerSaving;
    }

    public void setPowerSaving(boolean powerSaving) {
        this.powerSaving = powerSaving;
    }

    public int getSKZYLevel() {
        return SKZYLevel;
    }

    public void setSKZYLevel(int SKZYLevel) {
        this.SKZYLevel = SKZYLevel;
    }

    public int getTimer() {
        return timer;
    }

    public void setTimer(int timer) {
        this.timer = timer;
    }
}
