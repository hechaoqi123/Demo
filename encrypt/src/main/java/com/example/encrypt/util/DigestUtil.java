package com.example.encrypt.util;

import org.apache.tomcat.util.buf.HexUtils;
import org.apache.tomcat.util.codec.binary.Base64;
import org.apache.tomcat.util.security.MD5Encoder;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * 消息摘要算法
 * 无须密钥、不可还原
 * 变长输入、定长输出
 * @author hcq
 * @date 2020/4/29 16:01
 */
public class DigestUtil {

    private static final String MD5_TYPE="MD5";


    /**
     * MD5摘要并转换为16进制字符串
     * @param data 消息内容
     * @return Base64消息摘要
     */
    public static String md5ToBase64(String data) throws NoSuchAlgorithmException {
        return Base64.encodeBase64String(md5(data));
    }

    /**
     * MD5摘要并转换为16进制字符串
     * @param data 消息内容
     * @return 16进制消息摘要
     */
    public static String md5ToHex(String data) throws NoSuchAlgorithmException {
        return MD5Encoder.encode(md5(data));
    }

    /**
     * MD5摘要
     * @param data 消息内容
     * @return 消息摘要
     */
    public static byte[] md5(String data) throws NoSuchAlgorithmException {
        MessageDigest md5 = MessageDigest.getInstance(MD5_TYPE);
        return md5.digest(data.getBytes());
    }

    public static void main(String[] args) throws NoSuchAlgorithmException {
        String data = "hello world";
        String dataHex = md5ToHex(data);
        String dataBase64 = md5ToBase64(data);
        System.out.println("Hex："+dataHex);
        System.out.println("Base64："+dataBase64);

        byte[] array = HexUtils.fromHexString(dataHex);
        for (byte b : array) {
            System.out.print(b);
        }
        System.out.println();
        array = Base64.decodeBase64(dataBase64);
        for (byte b : array) {
            System.out.print(b);
        }
    }
}
