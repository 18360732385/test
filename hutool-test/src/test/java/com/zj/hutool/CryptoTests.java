/**
 * Copyright (C), 2019
 * FileName: CryptoTests
 * Author:   zhangjian
 * Date:     2019/11/6 17:04
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.zj.hutool;

import cn.hutool.core.lang.Console;
import cn.hutool.core.util.CharsetUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.SecureUtil;
import cn.hutool.crypto.asymmetric.KeyType;
import cn.hutool.crypto.asymmetric.RSA;
import cn.hutool.crypto.symmetric.AES;
import cn.hutool.crypto.symmetric.SymmetricAlgorithm;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.security.PrivateKey;


@SpringBootTest
public class CryptoTests {

    @Test
    void AESTest() {
        java.lang.String content = "test中文";

        // 随机生成密钥
        byte[] key = SecureUtil.generateKey(SymmetricAlgorithm.AES.getValue()).getEncoded();
        Console.log("秘钥：{}",new String(key));

        // 构建
        AES aes = SecureUtil.aes(key);

        // 加密
        byte[] encrypt = aes.encrypt(content);
        Console.log("encrypt:{}",new String(encrypt));

        // 解密
        byte[] decrypt = aes.decrypt(encrypt);
        Console.log("decrypt:{}",decrypt);

        // 加密为16进制表示
        java.lang.String encryptHex = aes.encryptHex(content);
        Console.log("encryptHex:{}",encryptHex);

        // 解密为字符串
        String decryptStr = aes.decryptStr(encryptHex, CharsetUtil.CHARSET_UTF_8);
        Console.log("decryptStr:{}",decryptStr);


    }

    @Test
    void RSATest(){
        RSA rsa = new RSA();

        //获得私钥
        PrivateKey privateKey = rsa.getPrivateKey();

        String privateKeyBase64 = rsa.getPrivateKeyBase64();
        Console.log("私钥base64：{}",privateKeyBase64);

        //获得公钥
        rsa.getPublicKey();
        String publicKeyBase64 = rsa.getPublicKeyBase64();
        Console.log("公钥：{}"+publicKeyBase64);

        //公钥加密，私钥解密
        byte[] encrypt = rsa.encrypt(StrUtil.bytes("我是一段测试aaaa", CharsetUtil.CHARSET_UTF_8), KeyType.PublicKey);
        Console.log("公钥加密：{}",encrypt);

        byte[] decrypt = rsa.decrypt(encrypt, KeyType.PrivateKey);
        Console.log("私钥解密：{}",decrypt);


        //Junit单元测试
        //Assert.assertEquals("我是一段测试aaaa", StrUtil.str(decrypt, CharsetUtil.CHARSET_UTF_8));

        //私钥加密，公钥解密
        byte[] encrypt2 = rsa.encrypt(StrUtil.bytes("我是一段测试aaaa", CharsetUtil.CHARSET_UTF_8), KeyType.PrivateKey);
        Console.log("私钥加密：{}",encrypt2);

        byte[] decrypt2 = rsa.decrypt(encrypt2, KeyType.PublicKey);
        Console.log("公钥解密：{}",decrypt2);

        //Junit单元测试
        //Assert.assertEquals("我是一段测试aaaa", StrUtil.str(decrypt2, CharsetUtil.CHARSET_UTF_8));

    }


}
