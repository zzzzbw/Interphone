package com.zbw.interphone.util;

import android.content.Context;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 * Created by zbw on 2017/4/28.
 */

public class FileUtil {

    public static ArrayList<String> getFiles(String folderPath, String key) {
        File folder = new File(folderPath);
        File[] files = folder.listFiles();

        ArrayList<String> filesName = new ArrayList<>();
        for (int i = 0; i < files.length; i++) {
            String fileName = files[i].getName();
            if (fileName.contains(key)) {
                filesName.add(fileName);
            }
        }
        Collections.sort(filesName,new SortDevice());
        return filesName;
    }

    private static class SortDevice implements Comparator {

        @Override
        public int compare(Object o1, Object o2) {
            String s1 = (String) o1;
            String s2 = (String) o2;
            if (s1.contains("S") && !s2.contains("S")) {
                return -1;
            }else if(!s1.contains("S") && s2.contains("S")){
                return 1;
            }else if(s1.length()>s2.length()){
                return 1;
            }else if(s1.length()>s2.length()){
                return -1;
            } else {
                return s1.compareTo(s2);
            }
        }
    }
}
