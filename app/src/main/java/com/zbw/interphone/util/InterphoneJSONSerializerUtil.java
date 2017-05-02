package com.zbw.interphone.util;

import android.content.Context;
import android.util.Log;

import com.zbw.interphone.model.InterphoneGlobal;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;

/**
 * Created by zbw on 2017/5/2.
 */

public class InterphoneJSONSerializerUtil {
    private static final String TAG = "JSONSerializer";
    private Context mContext;
    private String mFilename;

    public InterphoneJSONSerializerUtil(Context context, String file) {
        this.mContext = context;
        this.mFilename = file;
    }

    public JSONObject loadGlobal() throws IOException, JSONException {
        BufferedReader reader = null;
        try {
            InputStream in = mContext.openFileInput(mFilename);
            reader = new BufferedReader(new InputStreamReader(in));
            StringBuilder jsonString = new StringBuilder();
            String line = null;
            while ((line = reader.readLine()) != null) {
                jsonString.append(line);
            }

            JSONObject root = new JSONObject(jsonString.toString());
            return root;

        } catch (FileNotFoundException e) {
            Log.e(TAG, "File not find: " + e);
        } finally {
            if (reader != null) {
                reader.close();
            }
        }
        return null;
    }


    public void saveInterphone(InterphoneGlobal global) throws JSONException, IOException {
        JSONObject json = global.toJSON();

        Writer writer = null;
        try {
            OutputStream outputStream = mContext.openFileOutput(mFilename, Context.MODE_PRIVATE);
            writer = new OutputStreamWriter(outputStream);
            writer.write(json.toString());
        } finally {
            if (writer != null) {
                writer.close();
            }

        }
    }
}
