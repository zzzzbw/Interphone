package com.zbw.interphone.model;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by ZBW on 2017/4/29.
 */

public class DTMFSetting {
    //编码设置
    private int launchSpeed;
    private int firstLaunchDelay;
    private int firstLaunchTime;
    private String callCode1;
    private String callCode2;
    private String callCode3;
    private String PTTIdCode;
    //解码设置
    private String personId;
    private String teamId;
    private String groupId;
    private String YBCode;
    private String YYCode;
    private String reviveCode;

    private static final String JSON_LAUNCHSPEED = "launchSpeed";
    private static final String JSON_FIRSTLAUNCHDELAY = "firstLaunchDelay";
    private static final String JSON_FIRSTLAUNCHTIME = "firstLaunchTime";
    private static final String JSON_CALLCODE1 = "callCode1";
    private static final String JSON_CALLCODE2 = "callCode2";
    private static final String JSON_CALLCODE3 = "callCode3";
    private static final String JSON_PTTIDCODE = "PTTIdCode";

    private static final String JSON_PERSONID = "personId";
    private static final String JSON_TEAMID = "teamId";
    private static final String JSON_GROUPID = "groupId";
    private static final String JSON_YBCODE = "YBCode";
    private static final String JSON_YYCODE = "YYCode";
    private static final String JSON_REVIVECODE = "reviveCode";


    public DTMFSetting() {
    }

    ;

    public DTMFSetting(JSONObject json) throws JSONException {
        launchSpeed = json.getInt(JSON_LAUNCHSPEED);
        firstLaunchDelay = json.getInt(JSON_FIRSTLAUNCHDELAY);
        firstLaunchTime = json.getInt(JSON_FIRSTLAUNCHTIME);
        callCode1 = json.optString(JSON_CALLCODE1, "");
        callCode2 = json.optString(JSON_CALLCODE2, "");
        callCode3 = json.optString(JSON_CALLCODE3, "");
        PTTIdCode = json.optString(JSON_PERSONID, "");
        personId = json.optString(JSON_PERSONID, "");
        teamId = json.optString(JSON_TEAMID, "");
        groupId = json.optString(JSON_GROUPID, "");
        YBCode = json.optString(JSON_YBCODE, "");
        YYCode = json.optString(JSON_YYCODE, "");
        reviveCode = json.optString(JSON_REVIVECODE, "");
    }

    public JSONObject toJSON() throws JSONException {
        JSONObject json = new JSONObject();
        json.put(JSON_LAUNCHSPEED, launchSpeed);
        json.put(JSON_FIRSTLAUNCHDELAY, firstLaunchDelay);
        json.put(JSON_FIRSTLAUNCHTIME, firstLaunchTime);
        json.put(JSON_CALLCODE1, callCode1);
        json.put(JSON_CALLCODE2, callCode2);
        json.put(JSON_CALLCODE3, callCode3);
        json.put(JSON_PTTIDCODE, PTTIdCode);
        json.put(JSON_PERSONID, personId);
        json.put(JSON_TEAMID, teamId);
        json.put(JSON_GROUPID, groupId);
        json.put(JSON_YBCODE, YBCode);
        json.put(JSON_YYCODE, YYCode);
        json.put(JSON_REVIVECODE, reviveCode);
        return json;
    }


    public int getLaunchSpeed() {
        return launchSpeed;
    }

    public void setLaunchSpeed(int launchSpeed) {
        this.launchSpeed = launchSpeed;
    }

    public int getFirstLaunchDelay() {
        return firstLaunchDelay;
    }

    public void setFirstLaunchDelay(int firstLaunchDelay) {
        this.firstLaunchDelay = firstLaunchDelay;
    }

    public int getFirstLaunchTime() {
        return firstLaunchTime;
    }

    public void setFirstLaunchTime(int firstLaunchTime) {
        this.firstLaunchTime = firstLaunchTime;
    }

    public String getCallCode1() {
        return callCode1;
    }

    public void setCallCode1(String callCode1) {
        this.callCode1 = callCode1;
    }

    public String getCallCode2() {
        return callCode2;
    }

    public void setCallCode2(String callCode2) {
        this.callCode2 = callCode2;
    }

    public String getCallCode3() {
        return callCode3;
    }

    public void setCallCode3(String callCode3) {
        this.callCode3 = callCode3;
    }

    public String getPTTIdCode() {
        return PTTIdCode;
    }

    public void setPTTIdCode(String PTTIdCode) {
        this.PTTIdCode = PTTIdCode;
    }

    public String getPersonId() {
        return personId;
    }

    public void setPersonId(String personId) {
        this.personId = personId;
    }

    public String getTeamId() {
        return teamId;
    }

    public void setTeamId(String teamId) {
        this.teamId = teamId;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public String getYBCode() {
        return YBCode;
    }

    public void setYBCode(String YBCode) {
        this.YBCode = YBCode;
    }

    public String getYYCode() {
        return YYCode;
    }

    public void setYYCode(String YYCode) {
        this.YYCode = YYCode;
    }

    public String getReviveCode() {
        return reviveCode;
    }

    public void setReviveCode(String reviveCode) {
        this.reviveCode = reviveCode;
    }
}
