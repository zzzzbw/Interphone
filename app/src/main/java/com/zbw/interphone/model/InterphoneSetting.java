package com.zbw.interphone.model;



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
