package com.zbw.interphone.serialport;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.widget.Toast;

import com.zbw.interphone.model.DeviceSetting;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;

/**
 * Created by zbw on 2017/4/25.
 */

public class SerialPortHelper {
    public static final String TAG = "SerialPortHelper";

    public static final int SUCCESS = 0x0110;
    public static final int IO_ERROR = 0X0111;
    public static final int SECURITY_ERROR = 0x0112;
    public static final int ERROR = 0x1113;


    private static final int NO_DEVICE = 0X1110;
    private static final int LISTEN_START = 0x1111;
    private static final int LISTEN_END = 0x1112;
    private static final int HANDSHAKE_FALSE = 0x1113;
    private static final int HANDSHAKE_SUCCESS = 0x1114;

    private FileOutputStream mOutputStream;
    private FileInputStream mInputStream;
    private SerialPort sp;
    private ListenThread mThread;
    private Handler mToastHandler;

    private DeviceSetting mSoftwareSetting;
    private Context mAppActivity;

    public SerialPortHelper(final Context context) {
        mAppActivity = context;
        mSoftwareSetting = DeviceSetting.get(context);
        mToastHandler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                String content = "";
                switch (msg.what) {
                    case NO_DEVICE:
                        content = "没有设置串口";
                        break;
                    case LISTEN_START:
                        content = "监听开始";
                        break;
                    case LISTEN_END:
                        content = "监听结束";
                        break;
                    case HANDSHAKE_FALSE:
                        content = "握手协议错误";
                        break;
                    case HANDSHAKE_SUCCESS:
                        content = "握手协议成功";
                        break;
                }
                Toast.makeText(context, content, Toast.LENGTH_SHORT).show();
            }
        };
    }

    public int setupPort() {
        try {
            sp = new SerialPort(new File(mSoftwareSetting.getDevice()), 9600);
        } catch (IOException e) {
            e.printStackTrace();
            mOutputStream = null;
            mInputStream = null;
            return IO_ERROR;
        } catch (SecurityException e) {
            e.printStackTrace();
            mOutputStream = null;
            mInputStream = null;
            return SECURITY_ERROR;
        }


        mOutputStream = (FileOutputStream) sp.getOutputStream();
        mInputStream = (FileInputStream) sp.getInputStream();
        if (mOutputStream != null && mInputStream != null) {
            return SUCCESS;
        }
        return ERROR;
    }

    public void listenPort() {
        if (mThread != null) {
            mThread = null;
        }
        mThread = new ListenThread();
        mThread.start();
    }

    public void stopListen() {
        if (mThread == null) {
            return;
        }
        mThread.exit = true;
    }


    private int handShake(int state, byte[] buffer, int size) throws IOException {
        if (mOutputStream == null) {
            mToastHandler.sendEmptyMessage(NO_DEVICE);
            return -1;
        }
        byte[] temp = new byte[size];
        System.arraycopy(buffer, 0, temp, 0, size);
        Log.d(TAG, "第" + state + "次握手协议接收,  array: " + Arrays.toString(temp));

        switch (state) {
            case 0:
                if (Arrays.equals(temp, ProtocolConstant.firstHandshakeReceive)) {
                    mOutputStream = (FileOutputStream) sp.getOutputStream();
                    mOutputStream.write(ProtocolConstant.firstHandshakeLaunch);
                    mOutputStream.write('\n');
                    Log.d(TAG, "第" + state + "次握手协议发送,  array: " + Arrays.toString(ProtocolConstant.firstHandshakeLaunch));
                    return 1;
                }
                return state;
            case 1:
                if (Arrays.equals(temp, ProtocolConstant.secondHandshakeReceive)) {
                    mOutputStream = (FileOutputStream) sp.getOutputStream();
                    mOutputStream.write(ProtocolConstant.secondHandshakeLaunch);
                    mOutputStream.write('\n');
                    Log.d(TAG, "第" + state + "次握手协议发送,  array: " + Arrays.toString(ProtocolConstant.secondHandshakeLaunch));
                    return 2;
                }
                return state;
            case 2:
                if (Arrays.equals(temp, ProtocolConstant.thirdHandshakeReceive)) {
                    mOutputStream = (FileOutputStream) sp.getOutputStream();
                    mOutputStream.write(ProtocolConstant.thirdHandshakeLaunch);
                    mOutputStream.write('\n');
                    Log.d(TAG, "第" + state + "次握手协议发送,  array: " + Arrays.toString(ProtocolConstant.thirdHandshakeLaunch));
                    return 3;
                }
                return state;
        }

        return -1;

    }

    private int writeData(byte[] buffer, int size) throws IOException {
        return 1;
    }

    private int receiveData(byte[] buffer, int size) throws IOException {
        if (mOutputStream == null) {
            mToastHandler.sendEmptyMessage(NO_DEVICE);
            return 1;
        }
        byte[] temp = new byte[size];
        System.arraycopy(buffer, 0, temp, 0, size);
        Log.d(TAG, "receiveData  array: " + Arrays.toString(temp));

        mOutputStream = (FileOutputStream) sp.getOutputStream();
        mOutputStream.write(ProtocolConstant.ACK);
        mOutputStream.write('\n');
        return 1;
    }

    private class ListenThread extends Thread {
        public volatile boolean exit = false;

        public volatile int listenTime = mSoftwareSetting.getListenTime();

        @Override
        public void run() {
            super.run();
            if (mInputStream == null) {
                mToastHandler.sendEmptyMessage(NO_DEVICE);
                return;
            }
            mToastHandler.sendEmptyMessage(LISTEN_START);
            int state = 0;
            for (int i = 0; i < listenTime && !exit; i++) {
                int size;
                try {
                    byte[] buffer = new byte[64];
                    size = mInputStream.read(buffer);

                    if (size > 0) {
                        state = handShake(state, buffer, size);
                        if (state == -1) {
                            mToastHandler.sendEmptyMessage(HANDSHAKE_FALSE);
                            break;
                        }
                        if (state == 3) {
                            mToastHandler.sendEmptyMessage(HANDSHAKE_SUCCESS);
                            receiveData(buffer, size);
                            break;
                        }
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                    return;
                }
            }
            mToastHandler.sendEmptyMessage(LISTEN_END);
        }
    }
}
