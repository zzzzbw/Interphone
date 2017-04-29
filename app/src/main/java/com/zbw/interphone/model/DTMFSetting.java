package com.zbw.interphone.model;

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
