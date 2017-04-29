package com.zbw.interphone.util;

import android.content.Context;

/**
 * Created by ZBW on 2017/4/5.
 */

public class ResourceUtil {
    private Context mAppContext;

    public ResourceUtil(Context context) {
        mAppContext = context;
    }

    public String getShowResource(int resource, int value) {
        CharSequence[] items = mAppContext.getResources().getStringArray(resource);
        if (items.length > 0) {
            return items[value].toString();
        }
        return "";
    }



    public int getIndex(int resource, String value){
        CharSequence[] items = mAppContext.getResources().getStringArray(resource);
        if (items.length > 0) {
            for (int i = 0; i < items.length; i++) {
                if (items[i].equals(value)) {
                    return i;
                }
            }
        }
        return -1;
    }

    public String[] getStringRescourceList(int[] resource){
        int size=resource.length;
        if(size<=0){
            return new String[0];
        }
        String[] showResource=new String[size];
        for(int i=0;i<size;i++){
            showResource[i]=mAppContext.getResources().getString(resource[i]);
        }
        return showResource;
    }

}
