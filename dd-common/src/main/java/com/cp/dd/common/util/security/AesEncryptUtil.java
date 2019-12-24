package com.cp.dd.common.util.security;




import org.apache.commons.codec.binary.Base64;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

/**
 * AES 128bit 加密解密工具类
 *
 * @author chengp
 * @date 2019/10/17
 */
public class AesEncryptUtil {

    /**
     * 使用AES-128-CBC加密模式，key需要为16位,key和iv可以相同！
     */
    private static String KEY = "zswh!@#$$#@!hwsz";

    private static String IV = "zswh!@#$$#@!hwsz";


    /**
     * 加密方法
     *
     * @param data 要加密的数据
     * @param key  加密key
     * @param iv   加密iv
     * @return 加密的结果
     */
    public static String encrypt(String data, String key, String iv) {
        try {

            //"算法/模式/补码方式"NoPadding PkcsPadding
            Cipher cipher = Cipher.getInstance("AES/CBC/NoPadding");
            int blockSize = cipher.getBlockSize();

            byte[] dataBytes = data.getBytes();
            int plaintextLength = dataBytes.length;
            if (plaintextLength % blockSize != 0) {
                plaintextLength = plaintextLength + (blockSize - (plaintextLength % blockSize));
            }

            byte[] plaintext = new byte[plaintextLength];
            System.arraycopy(dataBytes, 0, plaintext, 0, dataBytes.length);

            SecretKeySpec keyspec = new SecretKeySpec(key.getBytes(), "AES");
            IvParameterSpec ivspec = new IvParameterSpec(iv.getBytes());

            cipher.init(Cipher.ENCRYPT_MODE, keyspec, ivspec);
            byte[] encrypted = cipher.doFinal(plaintext);

            return new Base64().encodeToString(encrypted);
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    /**
     * 解密方法
     *
     * @param data 要解密的数据
     * @param key  解密key
     * @param iv   解密iv
     * @return 解密的结果
     */
    public static String decrypt(String data, String key, String iv) {
        try {
            byte[] encrypted1 = new Base64().decode(data);

            Cipher cipher = Cipher.getInstance("AES/CBC/NoPadding");
            SecretKeySpec keySpec = new SecretKeySpec(key.getBytes(), "AES");
            IvParameterSpec ivSpec = new IvParameterSpec(iv.getBytes());

            cipher.init(Cipher.DECRYPT_MODE, keySpec, ivSpec);

            byte[] original = cipher.doFinal(encrypted1);
            return new String(original).trim();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 使用默认的key和iv加密
     *
     * @param data 要解密的数据
     * @return 加密结果
     */
    public static String encrypt(String data) {
        return encrypt(data, KEY, IV).replaceAll("[\\r\\n]", "");
    }

    /**
     * 使用默认的key和iv解密
     *
     * @param data 要解密的数据
     * @return 解密数据
     */
    public static String decrypt(String data) {
        return decrypt(data, KEY, IV);
    }

    public static void main(String[] args) {
        System.out.println(encrypt("123456"));
        System.out.println(decrypt("JRVnU/joe7j5pe1JbCtx1g=="));
        System.out.println(decrypt("5pxNwwrMC9BOA16F6Fmq1MoKz9by5zUrlwFmBrPjecTku3LBsjnYpw0QrIA3egQPcJ1e6lappEFFIDIHq7pqoqg5rVrRX7awtNfuUlLZo18AzjzSpbOG0M3bpC+gOkzx0+pEl3ntz6nLdIugBbamyTyZ4EcOmUZ8oOcp5ZkLl1Acy78maSbKNcZ5tzifVIFFzrF416dWabeTQ5xvjCiFGsy/JyejZjPQAHZMEsl0pN5oxtG59IOnhFJr7MZiot/WONvXjmm0de5oWSAC5XahptUNgFWHoe1EauN1unIbcgmRMmXQ35RXzdOr9M/tlc6F1bTCeWkt6CvEekAhzsfu7lfSzSgjkb6LUNNWjyFmnxpZ+Xo2o+rweoCm4KqaUitlytlvab7URwmGe5z6GtS/U+j+MHbEyHk+mdDp/xw7OYLwfcTWWVFvhrHpT/qmcU7jOeIt+fDNU3q8lGWYWH6dZry3ERTKVgMs5l9OIafUo9dyz0Xe7Mxhp9WwyA+yi2KSg4RtUxNiDef1dklVC3bupOUtUFn/mEtFsXnN6A/9dUg68iJcsdVDvOtzBkq6tS+NsKhfhvuFK1Km53HuSpIkyRtP9BQQOwO2k5iQr2sY9E9Qgz7Q4P5wfS96Y6KeHFYtlTHdea6zOqvN6nT0sfICtCHlivSFHExqHeeF7FkRn51Ogr6qJChCETX4y7Eao3x6"));
        System.out.println(decrypt("5pxNwwrMC9BOA16F6Fmq1KRzty+V+YJ2z3u7wtKyFQGnXTdnHL9+JR31Chlo62v8owyLgWo4mTgRhfHZci8do+yBElBL9kvu6UTMtWSx5Dg"));
    }
}
