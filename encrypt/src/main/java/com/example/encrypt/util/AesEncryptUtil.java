package com.example.encrypt.util;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.security.SecureRandom;

/**
 * AES加密工具类
 * 加密解密使用同一秘钥、加密速度快
 * 应用场景：防止数据泄露
 *
 * @author hcq
 * @date 2020/4/20 19:27
 */
public class AesEncryptUtil {

    private static final String CHAR_SET = "UTF-8";
    private static final String ENCRYPT_TYPE = "AES";

    /**
     * @param data        数据
     * @param securityKey 秘钥
     * @return 密文
     */
    public static String encrypt(String data, String securityKey) throws Exception {
        Cipher cipher = getCipher(Cipher.ENCRYPT_MODE, securityKey);
        byte[] bytes = cipher.doFinal(data.getBytes(CHAR_SET));
        return new BASE64Encoder().encode(bytes);
    }

    /**
     * 解密
     *
     * @param data        密文
     * @param securityKey 秘钥
     */
    public static String decode(String data, String securityKey) throws Exception {
        Cipher cipher = getCipher(Cipher.DECRYPT_MODE, securityKey);
        //8.将加密并编码后的内容解码成字节数组
        byte[] decodeBuffer = new BASE64Decoder().decodeBuffer(data);
        byte[] bytes = cipher.doFinal(decodeBuffer);
        return new String(bytes, CHAR_SET);
    }

    /**
     * @param type        模式
     * @param securityKey 秘钥
     * @return Cipher
     */
    private static Cipher getCipher(int type, String securityKey) throws Exception {
        KeyGenerator keygen = KeyGenerator.getInstance(ENCRYPT_TYPE);
        keygen.init(128, new SecureRandom(securityKey.getBytes(CHAR_SET)));
        SecretKey secretKey = keygen.generateKey();
        byte[] raw = secretKey.getEncoded();
        SecretKey key = new SecretKeySpec(raw, ENCRYPT_TYPE);
        Cipher cipher = Cipher.getInstance(ENCRYPT_TYPE);
        cipher.init(type, key);
        return cipher;
    }

    public static void main(String[] args) throws Exception {
        String data="hello world";
        String key="000d9b262280432e96c2c1b5ca3936e4000d9b262280432e96c2c1b5ca3936e4000d9b262280432e96c2c1b5ca3936e4";
        String dataEncode = encrypt(data, key);
        System.out.println("密文："+dataEncode);
        System.out.println("明文"+decode(dataEncode, key));
    }
}
