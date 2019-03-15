package com.ants.theantsgo.aes;

import android.util.Base64;

import java.io.UnsupportedEncodingException;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

/**
 * ===============Txunda===============<br>
 * 作者：DUKE_HwangZj<br>
 * 日期：2017/5/17 0017<br>
 * 时间：09:09<br>
 * 描述：Aes加密解密工具类<br>
 * <p>本类提供了将
 * <font color="#ff0000">byte[]</font>转成
 * <font color="#ff0000">16进制的字符串</font>的
 * <font color="#00ff00">加密方式</font><br></p>
 * <p>也提供了将
 * <font color="#ff0000">byte[]</font>转成
 * <font color="#ff0000">Base64</font>的
 * <font color="#00ff00">加密方式</font><br></p>
 * ===============Txunda===============
 */

public class AesEncryptionUtil {

    /**
     * 算法/模式/填充
     */
    private static final String CipherMode = "AES/CBC/PKCS7Padding";

    /**
     * 创建密钥
     *
     * @param key key
     * @return SecretKeySpec
     */
    private static SecretKeySpec createKey(String key) {
        byte[] data = null;
        if (key == null) {
            key = "";
        }
        StringBuilder sb = new StringBuilder(16);
        sb.append(key);
        while (sb.length() < 16) {
            sb.append("0");
        }
        if (sb.length() > 16) {
            sb.setLength(16);
        }
        try {
            data = sb.toString().getBytes("UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return new SecretKeySpec(data, "AES");
    }


    /**
     * @param password 密码
     * @return IvParameterSpec
     */
    private static IvParameterSpec createIV(String password) {
        byte[] data = null;
        if (password == null) {
            password = "";
        }
        StringBuilder sb = new StringBuilder(16);
        sb.append(password);
        while (sb.length() < 16) {
            sb.append("0");
        }
        if (sb.length() > 16) {
            sb.setLength(16);
        }
        try {
            data = sb.toString().getBytes("UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return new IvParameterSpec(data);
    }


    /**
     * 加密字节数据
     *
     * @param content  字节数组
     * @param password 密码
     * @param iv       iv
     * @return btte[]
     */
    private static byte[] encrypt(byte[] content, String password, String iv) {
        try {
            SecretKeySpec key = createKey(password);
            Cipher cipher = Cipher.getInstance(CipherMode);
            cipher.init(Cipher.ENCRYPT_MODE, key, createIV(iv));
            return cipher.doFinal(content);// 返回一个字节数组
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    /**
     * 加密
     *
     * @param content  数据
     * @param password 密码
     * @param iv       iv
     * @return String
     */
    public static String encrypt(String content, String password, String iv) {
        byte[] data = null;
        try {
            data = content.getBytes("UTF-8");
        } catch (Exception e) {
            e.printStackTrace();
        }
        data = encrypt(data, password, iv);
        return byte2Base64(data);// 返回一个字符串(结果为base64字符串)
//        return byte2hex(data);// 返回一个字符串(结果为16进制字符串)

    }

    /**
     * 解密字节数组
     *
     * @param content  字节数组
     * @param password 密码
     * @param iv       iv
     * @return byte
     */
    private static byte[] decrypt(byte[] content, String password, String iv) {
        try {
            SecretKeySpec key = createKey(password);
            Cipher cipher = Cipher.getInstance(CipherMode);
            cipher.init(Cipher.DECRYPT_MODE, key, createIV(iv));
            return cipher.doFinal(content);// 返回一个字节数组
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    /**
     * 解密(输出结果为字符串)
     *
     * @param content  String
     * @param password 密码
     * @param iv       iv
     * @return String
     */
    public static String decrypt(String content, String password, String iv) {
        byte[] data = null;
        try {
//            data = hex2byte(content);// 16进制转byte[]
            data = Base642byte(content);// Base64转byte[]
        } catch (Exception e) {
            e.printStackTrace();
        }
        data = decrypt(data, password, iv);
        if (data == null)
            return null;
        String result = null;
        try {
            result = new String(data, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return result;
    }


    /**
     * 字节数组转成16进制字符串(使用此方法加密成16进制的字符串，可能会和iOS的有差异)
     *
     * @param b 字节数组byte[]
     * @return String
     */
    private static String byte2hex(byte[] b) { // 一个字节的数，
        // 1.如果要操作少量的数据用 = String
        // 2.单线程操作字符串缓冲区下操作大量数据 = StringBuilder
        // 3.多线程操作字符串缓冲区下操作大量数据 = StringBuffer
        StringBuilder sb = new StringBuilder(b.length * 2);
        String tmp;
        for (byte n : b) {
            // 整数转成十六进制表示
            tmp = (java.lang.Integer.toHexString(n & 0XFF));
            if (tmp.length() == 1) {
                sb.append("0");
            }
            sb.append(tmp);
        }
        return sb.toString().toUpperCase(); // 转成大写
    }

    /**
     * Base64编码byte数组
     *
     * @param b 数组
     * @return String
     */
    private static String byte2Base64(byte[] b) {
        return Base64.encodeToString(b, Base64.NO_WRAP);
    }

    private static byte[] Base642byte(String str) {
        return Base64.decode(str, Base64.NO_WRAP);
    }

    /**
     * 将hex字符串转换成字节数组(此方式是将16进制的字符串变成byte[],使用此方法时可能会和iOS存在差异)
     *
     * @param inputString string
     * @return byte
     */
    private static byte[] hex2byte(String inputString) {
        if (inputString == null || inputString.length() < 2) {
            return new byte[0];
        }
        inputString = inputString.toLowerCase();
        int l = inputString.length() / 2;
        byte[] result = new byte[l];
        for (int i = 0; i < l; ++i) {
            String tmp = inputString.substring(2 * i, 2 * i + 2);
            result[i] = (byte) (Integer.parseInt(tmp, 16) & 0xFF);
        }
        return result;
    }

}
