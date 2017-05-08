package com.zbw.interphone.util;

import android.content.Context;

import com.zbw.interphone.model.ChannelList;
import com.zbw.interphone.model.InterphoneChannel;
import com.zbw.interphone.model.InterphoneGlobal;

import java.util.ArrayList;

/**
 * Created by zbw on 2017/5/3.
 */

public class InterphoneByteSerializerUtil {
    private static final int BYTE1BIT = 255;
    private static final int BYTE2BIT = 65535;

    InterphoneGlobal mGlobal;
    Context mAppContext;

    public InterphoneByteSerializerUtil(Context context) {
        mAppContext = context;
        mGlobal = InterphoneGlobal.get(context);
    }

    public byte[] toByte() throws Exception {
        ArrayList<Byte> arrayList = new ArrayList<>();
        ChannelList channelList = mGlobal.getChannelList();
        ArrayList<InterphoneChannel> frontChannels = channelList.getChannels(0, 127);
        for (int i = 0; i < frontChannels.size(); i++) {
            byte[] EEROM = getEEPROM(i);
            addToByteList(arrayList, EEROM);
            byte[] launchRate = new byte[]{0x00, 0x25, 0x01, 0x45};
            byte[] receviceRate = new byte[]{0x00, 0x25, 0x01, 0x45};
            addToByteList(arrayList, launchRate);
            addToByteList(arrayList, receviceRate);
            addToByteList(arrayList, getQTDQT());
            addToByteList(arrayList, getQTDQT());
            byte[] channelSetup = new byte[]{getChannelSetup(frontChannels.get(i))};
            addToByteList(arrayList, channelSetup);
            byte[] busyDeny = new byte[]{getBusyDeny(frontChannels.get(i))};
            addToByteList(arrayList, busyDeny);
            byte[] cover = new byte[]{-1};
            addToByteList(arrayList, cover);
            addToByteList(arrayList, cover);
        }

        byte[] data = new byte[arrayList.size()];
        for(int i=0;i<data.length;i++){
            data[i]=arrayList.get(i);
        }
        return data;
    }


    private byte[] getEEPROM(int i) {
        byte[] address = new byte[4];
        byte[] x1 = intToByteArray(i * 16);
        byte[] x2 = intToByteArray(i * 16 + 15);
        address[0] = x1[0];
        address[1] = x1[1];
        address[2] = x2[0];
        address[3] = x2[1];
        return address;
    }

    private byte[] getRate(float i) throws Exception {
        if (i > BYTE2BIT) {
            throw new Exception();
        }
        byte[] rate = new byte[4];
        int integer = (int) Math.floor(i);
        float decimal = i - integer;


        return rate;
    }

    private byte[] getQTDQT() {
        byte[] QTDQT = new byte[2];


        return QTDQT;
    }

    private byte getChannelSetup(InterphoneChannel channel) throws Exception {
        String binary = "";
        if (channel.isValid()) {
            binary += "1";
        } else {
            binary += "0";
        }
        if (channel.getChannelSpacing() == 0) {
            binary += "0";
        } else {
            binary += "1";
        }
        if (channel.isBusyDeny()) {
            binary += "1";
        } else {
            binary += "0";
        }
        if (channel.isChoiceCall()) {
            binary += "1";
        } else {
            binary += "0";
        }
        if (channel.getFirstScan() == 0) {
            binary += "0";
        } else {
            binary += "1";
        }
        if (channel.isPTTID()) {
            binary += "1";
        } else {
            binary += "0";
        }
        if (channel.getPowerLevel() == 0) {
            binary += "1";
        } else {
            binary += "0";
        }
        binary += "0";
        String str = binaryString2HexString(binary);
        return hexStringToHex(str);
    }

    private byte getBusyDeny(InterphoneChannel channel) throws Exception {
        String binary = "";
        if (channel.getScanList() == 0) {
            binary += "0000";
        } else if (channel.getScanList() == 1) {
            binary += "0001";
        } else if (channel.getScanList() == 2) {
            binary += "0010";
        } else if (channel.getScanList() == 3) {
            binary += "0100";
        } else {
            binary += "1000";
        }
        binary += "0";//接收频率步进值0 = 5 K ，1 = 6.25K
        binary += "0";//发射频率步进值 0 = 5K ，1 =6.25K
        if (channel.isBusyDeny()) {
            binary += "1";
        } else {
            binary += "0";
        }
        binary += "0";//补位

        String str = binaryString2HexString(binary);
        return hexStringToHex(str);
    }

    private int byteArrayToInt(byte[] b) {
        return b[3] & 0xFF |
                (b[2] & 0xFF) << 8 |
                (b[1] & 0xFF) << 16 |
                (b[0] & 0xFF) << 24;
    }

    private byte[] intToByteArray(int a) {
        if (a <= BYTE1BIT) {
            return new byte[]{0x00, (byte) a};
        } else if (a > BYTE1BIT && a <= BYTE2BIT) {
            return new byte[]{
                    (byte) ((a >> 8) & 0xFF),
                    (byte) (a & 0xFF)
            };
        } else {
            return new byte[]{
                    (byte) ((a >> 24) & 0xFF),
                    (byte) ((a >> 16) & 0xFF),
                    (byte) ((a >> 8) & 0xFF),
                    (byte) (a & 0xFF)
            };
        }
    }


    private String binaryString2HexString(String bString) {
        if (bString == null || bString.equals("") || bString.length() % 8 != 0)
            return null;
        StringBuffer tmp = new StringBuffer();
        int iTmp = 0;
        for (int i = 0; i < bString.length(); i += 4) {
            iTmp = 0;
            for (int j = 0; j < 4; j++) {
                iTmp += Integer.parseInt(bString.substring(i + j, i + j + 1)) << (4 - j - 1);
            }
            tmp.append(Integer.toHexString(iTmp));
        }
        return tmp.toString();
    }

    private byte hexStringToHex(String str) throws Exception {
        if (str.length() > 2) {
            throw new Exception();
        }
        String s = "0123456789abcdef";
        byte hex;
        int index = s.indexOf(str.charAt(0));
        int high = index * 16;
        index = s.indexOf(str.charAt(1));
        int low = index;
        hex = (byte) (high + low);
        return hex;
    }

    private void addToByteList(ArrayList<Byte> list, byte[] b) {
        for (int i = 0; i < b.length; i++) {
            list.add(new Byte(b[i]));
        }
    }

}
