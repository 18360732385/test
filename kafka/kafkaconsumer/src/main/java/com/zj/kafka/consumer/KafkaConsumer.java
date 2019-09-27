/**
 * Copyright (C), 2019
 * FileName: KafkaConsumer
 * Author:   zhangjian
 * Date:     2019/8/26 11:11
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.zj.kafka.consumer;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Slf4j
public class KafkaConsumer {
    @KafkaListener(topics = "wxxcx", groupId = "group_id")
    public void consumeA(String record) throws InterruptedException {

            System.out.println("kafka消费："+record);
            Thread.sleep(5000);
    }

    @KafkaListener(topics = "wxxcx", groupId = "group_id")
    public void consumeB(String record) throws InterruptedException {

        System.out.println("kafka消费："+record);
        Thread.sleep(5000);
    }
}
