package com.zbw.interphone.model;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.UUID;

/**
 * Created by ZBW on 2017/4/5.
 */

public class InterphoneChannel {
    private String id;

    private String nickname;
    private int channelSpacing;
    private int powerLevel;
    private int firstScan;
    private int scanList;

    private float receiveRate;
    private int receiveCTCSSType;
    private float receiveCTCSSRate;
    private float receiveCTCSSCode;

    private float launchRate;
    private int launchCTCSSType;
    private float launchCTCSSRate;
    private float launchCTCSSCode;

    private boolean valid;
    private boolean PTTID;
    private boolean busyDeny;
    private boolean choiceCall;
    private boolean interfere;

    private static final String JSON_ID = "id";
    private static final String JSON_NICKNAME = "nickname";
    private static final String JSON_CHANNELSPACING = "channelSpacing";
    private static final String JSON_POWERLEVEL = "powerLevel";
    private static final String JSON_FIRSTSCAN = "firstScan";
    private static final String JSON_SCANLIST = "scanList";
    private static final String JSON_RECEVIERATE = "receiveRate";
    private static final String JSON_REVEIVECTCSSTYPE = "receiveCTCSSType";
    private static final String JSON_RECEIVECTCSSRATE = "receiveCTCSSRate";
    private static final String JSON_REVEIVECTCSSCODE = "receiveCTCSSCode";
    private static final String JSON_LAUNCHRATE = "launchRate";
    private static final String JSON_LAUNCHCTCSSTYPE = "launchCTCSSType";
    private static final String JSON_LAUNCHCTCSSRATE = "launchCTCSSRate";
    private static final String JSON_LAUNCHCTCSSCODE = "launchCTCSSCode";
    private static final String JSON_VALID = "valid";
    private static final String JSON_PTTID = "PTTID";
    private static final String JSON_BUSYDENY = "busyDeny";
    private static final String JSON_CHOICECALL = "choiceCall";
    private static final String JSON_INTERFERE = "interfere";

    public InterphoneChannel() {
        id = UUID.randomUUID().toString();
    }

    public InterphoneChannel(JSONObject json) throws JSONException {

        id = json.getString(JSON_ID);
        nickname = json.getString(JSON_NICKNAME);
        channelSpacing = json.getInt(JSON_CHANNELSPACING);
        powerLevel = json.getInt(JSON_POWERLEVEL);
        firstScan = json.getInt(JSON_FIRSTSCAN);
        receiveRate = (float) json.getDouble(JSON_RECEVIERATE);
        receiveCTCSSType = json.getInt(JSON_REVEIVECTCSSTYPE);
        receiveCTCSSRate = (float) json.getDouble(JSON_RECEIVECTCSSRATE);
        receiveCTCSSCode = (float) json.getDouble(JSON_REVEIVECTCSSCODE);
        launchRate = json.getInt(JSON_LAUNCHRATE);
        launchCTCSSType = json.getInt(JSON_LAUNCHCTCSSTYPE);
        launchCTCSSRate = (float) json.getDouble(JSON_LAUNCHCTCSSRATE);
        launchCTCSSCode = (float) json.getDouble(JSON_LAUNCHCTCSSCODE);
        valid = json.getBoolean(JSON_VALID);
        PTTID = json.getBoolean(JSON_PTTID);
        busyDeny = json.getBoolean(JSON_BUSYDENY);
        choiceCall = json.getBoolean(JSON_CHOICECALL);
        interfere = json.getBoolean(JSON_INTERFERE);
    }

    public JSONObject toJSON() throws JSONException {
        JSONObject json = new JSONObject();
        json.put(JSON_ID, id);
        json.put(JSON_NICKNAME, nickname);
        json.put(JSON_CHANNELSPACING, channelSpacing);
        json.put(JSON_POWERLEVEL, powerLevel);
        json.put(JSON_FIRSTSCAN, firstScan);
        json.put(JSON_SCANLIST, scanList);
        json.put(JSON_RECEVIERATE, receiveRate);
        json.put(JSON_REVEIVECTCSSTYPE, receiveCTCSSType);
        json.put(JSON_RECEIVECTCSSRATE, receiveCTCSSRate);
        json.put(JSON_REVEIVECTCSSCODE, receiveCTCSSCode);
        json.put(JSON_LAUNCHRATE, launchRate);
        json.put(JSON_LAUNCHCTCSSTYPE, launchCTCSSType);
        json.put(JSON_LAUNCHCTCSSRATE, launchCTCSSRate);
        json.put(JSON_LAUNCHCTCSSCODE, launchCTCSSCode);
        json.put(JSON_VALID, valid);
        json.put(JSON_PTTID, PTTID);
        json.put(JSON_BUSYDENY, busyDeny);
        json.put(JSON_CHOICECALL, choiceCall);
        json.put(JSON_INTERFERE, interfere);
        return json;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
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

    public int getScanList() {
        return scanList;
    }

    public void setScanList(int scanList) {
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

    public float getReceiveCTCSSRate() {
        return receiveCTCSSRate;
    }

    public void setReceiveCTCSSRate(float receiveCTCSSRate) {
        this.receiveCTCSSRate = receiveCTCSSRate;
    }

    public float getReceiveCTCSSCode() {
        return receiveCTCSSCode;
    }

    public void setReceiveCTCSSCode(float receiveCTCSSCode) {
        this.receiveCTCSSCode = receiveCTCSSCode;
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
