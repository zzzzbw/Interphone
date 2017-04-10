package com.zbw.interphone.model;

/**
 * Created by zbw on 2017/4/4.
 */

public class InterphoneInfo {
    private String type;
    private String channel;
    private String version;

    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }
}
