/**
 * Copyright (C), 2020
 * FileName: AliyunOSSConfig
 * Author:   zhangjian
 * Date:     2020/1/7 14:40
 * Description: 阿里云OSS配置类
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.zj.hutool.configClass;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@PropertySource(value="classpath:application-oss.properties")
@ConfigurationProperties(prefix = "aliyun.oss")
public class AliyunOSSConfig {
    private String bucketname;
    private String endpoint;
    private String keyid;
    private String keysecret;
    private String filehost;
    //必须要有ge/set方法
    //properties中有的属性，即使class设置了默认值也没有用（覆盖）;properties中没有的属性，则默认值生效，不设置默认设置则为null
    private String one = "one";
    private String two = "two";

    public String getTwo() {
        return two;
    }

    public void setTwo(String two) {
        this.two = two;
    }

    public String getOne() {
        return one;
    }

    public void setOne(String one) {
        this.one = one;
    }

    public String getBucketname() {
        return bucketname;
    }

    public void setBucketname(String bucketname) {
        this.bucketname = bucketname;
    }

    public String getEndpoint() {
        return endpoint;
    }

    public void setEndpoint(String endpoint) {
        this.endpoint = endpoint;
    }

    public String getKeyid() {
        return keyid;
    }

    public void setKeyid(String keyid) {
        this.keyid = keyid;
    }

    public String getKeysecret() {
        return keysecret;
    }

    public void setKeysecret(String keysecret) {
        this.keysecret = keysecret;
    }

    public String getFilehost() {
        return filehost;
    }

    public void setFilehost(String filehost) {
        this.filehost = filehost;
    }
}
