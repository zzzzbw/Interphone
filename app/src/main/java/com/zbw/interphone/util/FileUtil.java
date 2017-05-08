package com.zbw.interphone.util;


import android.util.Log;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 * Created by zbw on 2017/4/28.
 */

public class FileUtil {

    private static final String TAG = "FileUtil";

    public static ArrayList<String> getFiles(String folderPath, String key) throws SecurityException {
        File folder = new File(folderPath);

        if (!folder.canRead()) {
            try {

                /*
                Process su = Runtime.getRuntime().exec("su", null, null);
                OutputStream os = su.getOutputStream();
                os.write(("chmod 666 /dev/" + "\n" + "exit\n").getBytes("ASCII"));
                os.flush();
                os.close();
                su.waitFor();
                */

                //没有读/写权限，尝试为文件授权


                Process su;
                su = Runtime.getRuntime().exec("/system/bin/su");
                String cmd = "chmod 444 " + folder.getAbsolutePath() + "/" + "\n" + "exit\n";
                Log.d(TAG, cmd);
                su.getOutputStream().write(cmd.getBytes());

                if ((su.waitFor() != 0) || !folder.canRead()) {
                    throw new SecurityException();
                }
            } catch (Exception e) {
                e.printStackTrace();
                throw new SecurityException();
            }
        }

        File[] files = folder.listFiles();

        ArrayList<String> filesName = new ArrayList<>();
        for (int i = 0; i < files.length; i++) {
            String fileName = files[i].getName();
            if (fileName.contains(key)) {
                filesName.add(fileName);
            }
        }
        Collections.sort(filesName, new SortDevice());
        return filesName;
    }

    private static class SortDevice implements Comparator {

        @Override
        public int compare(Object o1, Object o2) {
            String s1 = (String) o1;
            String s2 = (String) o2;
            if (s1.contains("S") && !s2.contains("S")) {
                return -1;
            } else if (!s1.contains("S") && s2.contains("S")) {
                return 1;
            } else if (s1.length() > s2.length()) {
                return 1;
            } else if (s1.length() > s2.length()) {
                return -1;
            } else {
                return s1.compareTo(s2);
            }
        }
    }
}
