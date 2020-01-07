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
import com.zj.hutool.configClass.AliyunOSSConfig;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.security.PrivateKey;


@SpringBootTest
public class ConfigClassTests {
    @Autowired
    private AliyunOSSConfig aliyunOSSConfig;

    @Test
    void ConfigTest() {
        String bucketname = null;
        String one = aliyunOSSConfig.getOne();
        String keyid = aliyunOSSConfig.getTwo();
        System.out.println(one+"===="+keyid);
    }


}
