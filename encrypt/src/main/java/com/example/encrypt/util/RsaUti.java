package com.example.encrypt.util;

import org.apache.tomcat.util.codec.binary.Base64;

import javax.crypto.Cipher;
import java.security.*;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

/**
 * RSA非对称加密
 * 私钥签名、公钥验签
 * 公钥加密、私钥解密
 *
 * @author hcq
 * @date 2020/4/29 16:33
 */
public class RsaUti {

    private static final String RSA = "RSA";

    /**
     * 公钥加密
     * @param data   明文
     * @param pubKey BASE64公钥
     * @return BASE64 密文
     */
    public static String pubKeyEncryptToBase64(String data, String pubKey) throws GeneralSecurityException {
        byte[] pubByte = Base64.decodeBase64(pubKey);
        PublicKey publicKey = KeyFactory.getInstance(RSA).generatePublic(new X509EncodedKeySpec(pubByte));
        byte[] encryptData = encrypt(data, publicKey);
        return Base64.encodeBase64String(encryptData);
    }

    /**
     * 私钥解密
     * @param data Base64密文
     * @param priKey Base64私钥
     * @return 明文
     */
    public static String priKeyDecryptByBase64(String data,String priKey) throws GeneralSecurityException{
        byte[] decode = Base64.decodeBase64(priKey);
        PrivateKey privateKey = KeyFactory.getInstance(RSA).generatePrivate(new PKCS8EncodedKeySpec(decode));
        return new String(decrypt(Base64.decodeBase64(data),privateKey));
    }

    /**
     * 私钥加密
     * @param data 明文
     * @param priKey BASE64私钥
     * @return Base64密文
     */
    public static String priKeyEncryptToBase64(String data, String priKey) throws GeneralSecurityException {
        byte[] priByte = Base64.decodeBase64(priKey);
        PrivateKey privateKey = KeyFactory.getInstance(RSA).generatePrivate(new PKCS8EncodedKeySpec(priByte));
        byte[] encryptData = encrypt(data, privateKey);
        return Base64.encodeBase64String(encryptData);
    }

    /**
     * 公钥解密
     * @param data Base密文
     * @param pubKey Base64公钥
     * @return 明文
     */
    public static String pubKeyDecryptByBase64(String data, String pubKey) throws GeneralSecurityException {
        byte[] pubKeyByte = Base64.decodeBase64(pubKey);
        PublicKey publicKey = KeyFactory.getInstance(RSA).generatePublic(new X509EncodedKeySpec(pubKeyByte));
        byte[] encryptData = decrypt(Base64.decodeBase64(data), publicKey);
        return new String(encryptData);
    }

    /**
     * RSA加密
     *
     * @param data      消息内容
     * @param key rsa公钥/私钥
     * @return 密文
     */
    public static byte[] encrypt(String data, Key key) throws GeneralSecurityException{
        Cipher cipher = Cipher.getInstance(RSA);
        cipher.init(Cipher.ENCRYPT_MODE, key);
        return cipher.doFinal(data.getBytes());
    }

    /**
     * 解密
     *
     * @param data   密文
     * @param key rsa私钥/公钥
     * @return 明文
     */
    public static byte[] decrypt(byte[] data, Key key) throws GeneralSecurityException {
        Cipher cipher = Cipher.getInstance(RSA);
        cipher.init(Cipher.DECRYPT_MODE, key);
        return cipher.doFinal(data);
    }

    public static void main(String[] args) throws Exception {
        String priKey = "MIICdQIBADANBgkqhkiG9w0BAQEFAASCAl8wggJbAgEAAoGBAJ/Tf6CL/iN8kNe3" +
                "QaG+wNe/+nFU0i0WM3fg70vzbULPlPYUG00fNdj6U5BvsGzjh7HI7EmVZGMqLmfY" +
                "+ETB+0OpteFOgt0Redr7WXt5OYDu+TU4sWHuxNR+7ID0fWFR2k9B36q+umj1ROw2" +
                "71kJ1inmu4n6c7AIwjQuzH1d7ra5AgMBAAECgYBlbbymL5G2BZyKObN+KWeKxDv0" +
                "5maH1HoPTwGuSqsBZmlbjcERvYfXzm1v0WG+iNsZubAytotB34gZwXk1cYG6G1BP" +
                "BvnoVm5INi5mTAzvqttvQX/WaVitLftMQkxwLYAoQuOCHCz8HGeRZcV1oKyWdy7c" +
                "jWb7ydzwdWAhxtmrvQJBAM7zZ3zz8A6xv2DinHbx0RhFzi6Xo+cZFz71B2YDuaeM" +
                "sABJBv5wICo79ThO15r0wsPRjRgL4PpKZPzA50ZAmjMCQQDFtNcMn7DK7P99ZVUD" +
                "YV/JI+sfstDjZ20E7Fz+Kz9Ss15fyxaCnV18ArsYtRR0cxhW1kr6y5CKEM5/fgKX" +
                "BZdjAkBzjwDjigcq/V/jBsbduCvMxPXbmHtCSQVs9z+5XC0n/OwuTJjmLNAZJT/J" +
                "wGSuNywmUfXaTo/C0xXO+RxrYxl3AkABVh6aBD5SsNVtSJERi8f0+Rwuw6urzdgr" +
                "z1k1kp9D9Nhvd1T4nw2xt+cB3L99pgWFGL+7AENC26g5rmVgFfaXAkAGyHwz5JtV" +
                "lcZRqh8v9ZDAPJYPzbQS9Cy1HFhZuB748WrLcmeQPi2nVqOXFNJfNskUCck+Kc1O" +
                "iswqNHWMOdTk";
        String pubKey = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCf03+gi/4jfJDXt0GhvsDXv/px" +
                "VNItFjN34O9L821Cz5T2FBtNHzXY+lOQb7Bs44exyOxJlWRjKi5n2PhEwftDqbXh" +
                "ToLdEXna+1l7eTmA7vk1OLFh7sTUfuyA9H1hUdpPQd+qvrpo9UTsNu9ZCdYp5ruJ" +
                "+nOwCMI0Lsx9Xe62uQIDAQAB";

        String data = "hello world";


        String encrypt=pubKeyEncryptToBase64(data,pubKey);
        System.out.println("公钥加密："+encrypt);

        String decrypt=priKeyDecryptByBase64(encrypt,priKey);
        System.out.println("私钥解密："+decrypt);

        encrypt=priKeyEncryptToBase64(data,priKey);
        System.out.println("私钥加密："+encrypt);

        decrypt= pubKeyDecryptByBase64(encrypt,pubKey);
        System.out.println("公钥解密："+decrypt);
    }
}
