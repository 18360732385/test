/**
 * Copyright (C), 2019
 * FileName: key
 * Author:   zhangjian
 * Date:     2019/9/17 13:58
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.zj.stream.constants;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix="key")
public class MyKey {
    private String privateKeyBase64;
    private String publicKeyBase64;

    public String getPrivateKeyBase64() {
        return privateKeyBase64;
    }

    public String getPublicKeyBase64() {
        return publicKeyBase64;
    }

    public void setPrivateKeyBase64(String privateKeyBase64) {
        this.privateKeyBase64 = privateKeyBase64;
    }

    public void setPublicKeyBase64(String publicKeyBase64) {
        this.publicKeyBase64 = publicKeyBase64;
    }
}
