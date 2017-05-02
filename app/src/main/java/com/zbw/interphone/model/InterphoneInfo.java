package com.zbw.interphone.model;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by zbw on 2017/4/4.
 */

public class InterphoneInfo {
    private String type;
    private String channel;
    private String version;

    private static final String JSON_TYPE = "type";
    private static final String JSON_CHANNEL = "channel";
    private static final String JSON_VERSION = "version";


    public InterphoneInfo(){};

    public InterphoneInfo(JSONObject json) throws JSONException {
        type = json.getString(JSON_TYPE);
        channel = json.getString(JSON_CHANNEL);
        version = json.getString(JSON_VERSION);
    }

    public JSONObject toJSON() throws JSONException {
        JSONObject json = new JSONObject();
        json.put(JSON_TYPE, type);
        json.put(JSON_CHANNEL, channel);
        json.put(JSON_VERSION, version);
        return json;
    }



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
