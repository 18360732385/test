/**
 * Copyright (C), 2019
 * FileName: KafkaProvider
 * Author:   zhangjian
 * Date:     2019/8/26 11:00
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.zj.kafkaprovider.provider;

import com.alibaba.fastjson.JSONObject;
import com.zj.kafkaprovider.model.Order;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

import java.time.LocalDateTime;

@SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
@Component
@Slf4j
public class KafkaProvider {
    /**
     * 消息 TOPIC
     */
    private static final String TOPIC = "wxxcx";
    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    public void sendMessage(long orderId, String orderNum, LocalDateTime createTime) {
        // 构建一个订单类
        Order order = new Order(orderId,orderNum,createTime);
        // 发送消息，订单类的 json 作为消息体

        ListenableFuture<SendResult<String, String>> future =
                kafkaTemplate.send(TOPIC,JSONObject.toJSONString(order));
        // 监听回调
        future.addCallback(new ListenableFutureCallback<SendResult<String, String>>() {
            @Override
            public void onFailure(Throwable throwable) {
                System.out.println("## kafka发送消息失败 ...");
            }
            @Override
            public void onSuccess(SendResult<String, String> result) {
                System.out.println("## kafka发送消息成功 ..."+result.getProducerRecord());
            }
        });
    }
}
