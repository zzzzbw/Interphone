package com.zbw.interphone.serialport;

/**
 * Created by zbw on 2017/4/23.
 */

public class ProtocolConstant {
    public static final byte STX = 0x02;//STX
    public static final byte ACK = 0x06;//ACK


    public static final byte[] firstHandshakeReceive = {STX, 72, 73, 89, 85, 78, 84, 79, 78};//02H HIYUNTON
    public static final byte[] firstHandshakeLaunch = {ACK};
    public static final byte[] secondHandshakeReceive = {STX};
    public static final byte[] secondHandshakeLaunch = {0x00, 0x00, -16, 0x30, 0x38, 0x32, 0x33, 0x50};//0H 0H xxH 30H 38H 32H 33H 50H
    public static final byte[] thirdHandshakeReceive = {ACK};
    public static final byte[] thirdHandshakeLaunch = {ACK};


}
