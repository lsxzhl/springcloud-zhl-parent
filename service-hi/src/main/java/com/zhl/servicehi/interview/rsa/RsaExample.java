package com.zhl.servicehi.interview.rsa;

import org.apache.commons.codec.binary.Base64;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.security.*;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.sql.Timestamp;
import java.time.LocalDateTime;


/**
 * @Author: zhanghailong@bitnei.cn
 * @Date: 2020/2/5 13:12
 */
public class RsaExample {

    /**
     * RSA最大加密明文大小
     */
    private static final int MAX_ENCRYPT_BLOCK = 117;

    /**
     * RSA最大解密密文大小
     */
    private static final int MAX_DECRYPT_BLOCK = 128;

    /**
     * 获取私钥
     *
     * @param privateKey 私钥字符串
     * @return
     */
    public static PrivateKey getPrivateKey(String privateKey) throws Exception {
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        byte[] decodedKey = Base64.decodeBase64(privateKey.getBytes());
        PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(decodedKey);
        return keyFactory.generatePrivate(keySpec);
    }

    /**
     * 获取公钥
     *
     * @param publicKey 公钥字符串
     * @return
     */
    public static PublicKey getPublicKey(String publicKey) throws Exception {
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        byte[] decodedKey = Base64.decodeBase64(publicKey.getBytes());
        X509EncodedKeySpec keySpec = new X509EncodedKeySpec(decodedKey);
        return keyFactory.generatePublic(keySpec);
    }

    /**
     * RSA加密
     *
     * @param data      待加密数据
     * @param publicKey 公钥
     * @return
     */
    public static String encrypt(String data, PublicKey publicKey) throws Exception {
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.ENCRYPT_MODE, publicKey);
        int inputLen = data.getBytes().length;
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        int offset = 0;
        byte[] cache;
        int i = 0;
        // 对数据分段加密
        while (inputLen - offset > 0) {
            if (inputLen - offset > MAX_ENCRYPT_BLOCK) {
                cache = cipher.doFinal(data.getBytes(), offset, MAX_ENCRYPT_BLOCK);
            } else {
                cache = cipher.doFinal(data.getBytes(), offset, inputLen - offset);
            }
            out.write(cache, 0, cache.length);
            i++;
            offset = i * MAX_ENCRYPT_BLOCK;
        }
        byte[] encryptedData = out.toByteArray();
        out.close();
        // 获取加密内容使用base64进行编码,并以UTF-8为标准转化成字符串
        // 加密后的字符串
        return new String(Base64.encodeBase64String(encryptedData));
    }


    /**
     * 签名
     *
     * @param data       待签名数据
     * @param privateKey 私钥
     * @return 签名
     */
    public static String sign(String data, PrivateKey privateKey) throws Exception {
        byte[] keyBytes = privateKey.getEncoded();
        PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        PrivateKey key = keyFactory.generatePrivate(keySpec);
        Signature signature = Signature.getInstance("SHA256withRSA");
        signature.initSign(key);
        signature.update(data.getBytes());
        return new String(Base64.encodeBase64(signature.sign()));
    }

    /**
     * 验签
     *
     * @param srcData   原始字符串
     * @param publicKey 公钥
     * @param sign      签名
     * @return 是否验签通过
     */
    public static boolean verify(String srcData, PublicKey publicKey, String sign) throws Exception {
        byte[] keyBytes = publicKey.getEncoded();
        X509EncodedKeySpec keySpec = new X509EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        PublicKey key = keyFactory.generatePublic(keySpec);
        Signature signature = Signature.getInstance("SHA256withRSA");
        signature.initVerify(key);
        signature.update(srcData.getBytes());
        return signature.verify(Base64.decodeBase64(sign.getBytes()));
    }

    /**
     * RSA解密
     *
     * @param data       待解密数据
     * @param privateKey 私钥
     * @return
     */
    public static String decrypt(String data, PrivateKey privateKey) throws IOException, BadPaddingException, IllegalBlockSizeException, NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException {
            Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
            cipher.init(Cipher.DECRYPT_MODE, privateKey);
            byte[] dataBytes = Base64.decodeBase64(data);
            int inputLen = dataBytes.length;
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            int offset = 0;
            byte[] cache;
            int i = 0;
            // 对数据分段解密
            while (inputLen - offset > 0) {
                if (inputLen - offset > MAX_DECRYPT_BLOCK) {
                    cache = cipher.doFinal(dataBytes, offset, MAX_DECRYPT_BLOCK);
                } else {
                    cache = cipher.doFinal(dataBytes, offset, inputLen - offset);
                }
                out.write(cache, 0, cache.length);
                i++;
                offset = i * MAX_DECRYPT_BLOCK;
            }
            byte[] decryptedData = out.toByteArray();
            out.close();
            // 解密后的内容
            return new String(decryptedData, "UTF-8");
    }


    public static void main(String[] args) {
        try {

            String privateKey = "MIICdQIBADANBgkqhkiG9w0BAQEFAASCAl8wggJbAgEAAoGBAIKaYcaB+NUNxaDpo/sOblhuyGQuow4I8E37DsEXBUXZNINhdk4OE4wSp7K2Yw36ZlfiLmD7ih/K4mqOThMtHzhsWkxDWzjqgYwL5XUm1A6bWy/GF5725iVaCw/PIXTVEyUROg+GkJODJpQSA6Mk1eq3i+yy5vl4NzInWe9oJa+ZAgMBAAECgYBPCXi0dkGNRflcySgorfIPaHOzVRnuS3CJ8K5cIj9ZpvoFrDy5PgGMWlnMaF4HvoqjEsk4feF4HgLps1cCeeMTFwgE6OBJ7JShefGr/GtupOd4r1yXZvsnwG9/vh8VNqTNa/pWSw/L1hIGT8owMq4CueE6m6X8lSfDRtQ29E1iVQJBAMkKFEWIIwTvvRKnKlL+0TE6ZoBk+N7hjAQooJTesAi7oB6h2RGoX9IdV+TPmAGTV1bC93GtDYWL+MoMU7R49hsCQQCmTsD+Rm0JPbqp1IT/JdU6HgNq1HkU26bs3w65y0H7hOoykZakX9WEriTRdZj5aArp1od0YeTnqU3i+G7vZdxbAkAfDMl4u7/84TIokVz+GJEis0xlhDLT6a4Abm3u0D4L+z6NvJ2UFpdGEnYixtVJWQJiShaBniK6f5XbTvAWraAFAj9eMj0dyMX5df1ENBsgaU1cnaNR3wa1ybPCdpTgpz+4NpBwFAY/FAjcHca83N7IZSXJTLlsMgZ1MG/tHsm2zvsCQQDDjreVuIxNuD07tWcZO9NxumO4NVR06gWEwAgiBqNHWo4tgWUuCJYlLYTkgMkL93LXIPIMyvwMHZt1uU2V9abq";

            String rsaPublicKey =  "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCCmmHGgfjVDcWg6aP7Dm5YbshkLqMOCPBN+w7BFwVF2TSDYXZODhOMEqeytmMN+mZX4i5g+4ofyuJqjk4TLR84bFpMQ1s46oGMC+V1JtQOm1svxhee9uYlWgsPzyF01RMlEToPhpCTgyaUEgOjJNXqt4vssub5eDcyJ1nvaCWvmQIDAQAB";
            String signPrivateKey = "MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBAMldj1iFaxTlvw0v8AVJSN/RlW2EuJbF2JIl7b33tX5J8X6nTA8cUwdMy1fgTjOKHzj0gYdU3w1S1W/iRe5I+4gPhMx/T4Ela+lIUx1tErQnz2tdJAmh4gq4bMb6rYWMcGoqDspSSrZ+a5AvWyFqjkMIIRhjkwuYKp1cWb5FYWAFAgMBAAECgYB2Timx8HEBThn5PXjfIjdWiGQqfBeXPZYIB5CuU6KmF/tyVggxuIpvd4bgkkBft36wj7aqNAr4YPpVSbOcU/Sy6NTfUEshOwQLOxvobLEyOg62//mB4USxqj3ENeNehYwZ5CXLnr9wK7ni/RSnFJAW5nz5flXOCZNkPVdxdwND4QJBAOdkGYoOiP4m1NosqZCjvcxKmkBc0Ntj7vXQ2364Omn4hxYjjcdvQg6qbzuOlxbSsGGtzGsqdSVlVeXrQS0jmscCQQDex/p2mAeybl3IryAjHHkTOnOCDBbts90BAP1ie7oHN8w0a2yYZnITPpxbu7Rx5YQjlDiWYqeUUtSRo9huUsLTAkAxPTHaGQG545WD3+EtcEqhQHbWn2mqZfehw5IRwy5bApHseiBfgiNyb35AFDW+m5MBFjTb0SsgjBHdXVR/QIZdAkBObPnYC/cRslajkjrvAVQCF96X1mev8FSJyO5qYAICDnt9rJ+DGvVnnu/uPyrruY+F8uTk1AAIcAxS5OoJFGtvAkEAhD6tTAMoaBWxQf2UP1TsQoE7WpPd7Rl/T0dUCgCJum2+8AU/8h/ygWv78iRJAHoyys9wz0zweQYgPD0q1q8qvw==";

            String signPublicKey = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDJXY9YhWsU5b8NL/AFSUjf0ZVthLiWxdiSJe2997V+SfF+p0wPHFMHTMtX4E4zih849IGHVN8NUtVv4kXuSPuID4TMf0+BJWvpSFMdbRK0J89rXSQJoeIKuGzG+q2FjHBqKg7KUkq2fmuQL1shao5DCCEYY5MLmCqdXFm+RWFgBQIDAQAB";


            // RSA加密
            String data = "L6T79F4Z2JN466776";
            String encryptData = encrypt(data, getPublicKey(rsaPublicKey));
            //String encryptData = "PXoKTu0u4bmKCD82cH82lRG0aSqOTCbvVbuIMWWXLqDdxVxNHvP0mvv0tPyZcttcINgzn1YLw5iBgNerVWEbH8nFe6WRtUcX7BvhzfJ14oXGEUHapQ2xmDYG3n2Y27RHh1Na3/HxHU89mIXpEC+790sXLwWQrWlaupj+KGnWlP4=";
            System.out.println("VIN:  " + encryptData);
            System.out.println("licensePlate: " + encrypt("津AD01562",getPublicKey(rsaPublicKey)));

            String encryptParameter = "dU0WMrisMzcKfzka%2BdKZZrFfPCzZtqqIu2GPZ7HjLbSZitLTcPd%2F4GQ4pY05oCkhN7OYV9NvmsZruQDL6F51gsxD7Qv6r9MEcbG1Pd4fyRGXnIVkTgjVuOJ6cOYc8tW4Fw7e3UoLs2wAE%2F4VrGSwp%2BGWYcJiYdCANclSzYfNpdM%3D";
            URLDecoder.decode(encryptParameter);
            //解密
            String devryptData = decrypt(URLDecoder.decode(encryptParameter), getPrivateKey(privateKey));
            System.out.println("jiemi :" + devryptData);

            // SHA256withRSA签名
            long s = Timestamp.valueOf(LocalDateTime.now()).getTime();
            String sign = sign(data + "_" + s, getPrivateKey(signPrivateKey));
            System.out.println("signature:  " + sign);
            System.out.println("timestamp:  " + s);

            // RSA验签
            String userSign = "HKwhYVvYE8Pv4IZLV9/IdWlKvQhbfBS2W7f/cpA3wPDkf/bRiWJSm0ay4wmGXkc/EVaUbyoT4JhyXmzDiNTax804D6oUgRZLhd+d256/Gg2PvRVOqAUnvcfjrE0z+JbueBQavqjqC5bBSecAnBwjLXZ7JiIGzY9MKMkabz6vmEM=";
            boolean result = verify(devryptData+"_1583910214181", getPublicKey(signPublicKey), userSign);
            System.out.println("sign result:" + result);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.print("加解密异常");
        }
    }
}
