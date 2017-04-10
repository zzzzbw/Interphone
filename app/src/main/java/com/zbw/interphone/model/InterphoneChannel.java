package com.zbw.interphone.model;

import java.util.UUID;

/**
 * Created by ZBW on 2017/4/5.
 */

public class InterphoneChannel {
    private UUID id;

    private String nickname;
    private int channelSpacing;
    private int powerLevel;
    private int firstScan;
    private ScanList scanList;

    private float receiveRate;
    private int receiveCTCSSType;
    private float receviceCTCSSRate;
    private float receviceCTCSSCode;

    private float launchRate;
    private int launchCTCSSType;
    private float launchCTCSSRate;
    private float launchCTCSSCode;

    private boolean valid;
    private boolean PTTID;
    private boolean busyDeny;
    private boolean choiceCall;
    private boolean interfere;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public int getChannelSpacing() {
        return channelSpacing;
    }

    public void setChannelSpacing(int channelSpacing) {
        this.channelSpacing = channelSpacing;
    }

    public int getPowerLevel() {
        return powerLevel;
    }

    public void setPowerLevel(int powerLevel) {
        this.powerLevel = powerLevel;
    }

    public int getFirstScan() {
        return firstScan;
    }

    public void setFirstScan(int firstScan) {
        this.firstScan = firstScan;
    }

    public ScanList getScanList() {
        return scanList;
    }

    public void setScanList(ScanList scanList) {
        this.scanList = scanList;
    }

    public float getReceiveRate() {
        return receiveRate;
    }

    public void setReceiveRate(float receiveRate) {
        this.receiveRate = receiveRate;
    }

    public int getReceiveCTCSSType() {
        return receiveCTCSSType;
    }

    public void setReceiveCTCSSType(int receiveCTCSSType) {
        this.receiveCTCSSType = receiveCTCSSType;
    }

    public float getReceviceCTCSSRate() {
        return receviceCTCSSRate;
    }

    public void setReceviceCTCSSRate(float receviceCTCSSRate) {
        this.receviceCTCSSRate = receviceCTCSSRate;
    }

    public float getReceviceCTCSSCode() {
        return receviceCTCSSCode;
    }

    public void setReceviceCTCSSCode(float receviceCTCSSCode) {
        this.receviceCTCSSCode = receviceCTCSSCode;
    }

    public float getLaunchRate() {
        return launchRate;
    }

    public void setLaunchRate(float launchRate) {
        this.launchRate = launchRate;
    }

    public int getLaunchCTCSSType() {
        return launchCTCSSType;
    }

    public void setLaunchCTCSSType(int launchCTCSSType) {
        this.launchCTCSSType = launchCTCSSType;
    }

    public float getLaunchCTCSSRate() {
        return launchCTCSSRate;
    }

    public void setLaunchCTCSSRate(float launchCTCSSRate) {
        this.launchCTCSSRate = launchCTCSSRate;
    }

    public float getLaunchCTCSSCode() {
        return launchCTCSSCode;
    }

    public void setLaunchCTCSSCode(float launchCTCSSCode) {
        this.launchCTCSSCode = launchCTCSSCode;
    }

    public boolean isValid() {
        return valid;
    }

    public void setValid(boolean valid) {
        this.valid = valid;
    }

    public boolean isPTTID() {
        return PTTID;
    }

    public void setPTTID(boolean PTTID) {
        this.PTTID = PTTID;
    }

    public boolean isBusyDeny() {
        return busyDeny;
    }

    public void setBusyDeny(boolean busyDeny) {
        this.busyDeny = busyDeny;
    }

    public boolean isChoiceCall() {
        return choiceCall;
    }

    public void setChoiceCall(boolean choiceCall) {
        this.choiceCall = choiceCall;
    }

    public boolean isInterfere() {
        return interfere;
    }

    public void setInterfere(boolean interfere) {
        this.interfere = interfere;
    }
}
