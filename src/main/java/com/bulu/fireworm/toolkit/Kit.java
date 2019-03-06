package com.bulu.fireworm.toolkit;

import org.apache.shiro.codec.Base64;

import java.util.Random;

public class Kit {

    /**
     * 生成base64 String
     */
    public byte[] creatCipherKey(){
        byte[] cipherKey = null;
        String chars = "asdfghjklqwertyuiopzxcvbnm";
        StringBuilder randomString = new StringBuilder();
        int randomNum = new Random().nextInt(99999);
        int count = 0;
        Base64 base64 = new Base64();
        try{
            while (true){
                randomString = randomString.append(String.valueOf(randomNum)).append(chars.substring(25 - count)).append(randomString);
                cipherKey = Base64.decode(base64.encodeToString(randomString.toString().getBytes()));
                if (cipherKey.length % 8 == 0 && cipherKey.length <= 512){
                    System.out.println("字符串：" + randomString);
                    System.out.println("base64字符串：" + base64.encodeToString(randomString.toString().getBytes()));
                    System.out.println("秘钥：" + cipherKey);
                    System.out.println("长度：" + cipherKey.length);
                    break;
                }else if (cipherKey.length > 512){
                    randomString.setLength(0);
                }else if(count == 25){
                    count = 0;
                }else count++;
            }
        }catch (Exception e){
               e.printStackTrace();
        }
        return cipherKey;
    }
}
