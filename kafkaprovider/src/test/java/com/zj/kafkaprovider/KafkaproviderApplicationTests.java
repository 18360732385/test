package com.zj.kafkaprovider;

import com.zj.kafkaprovider.provider.KafkaProvider;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.util.UUID;

@RunWith(SpringRunner.class)
@SpringBootTest
public class KafkaproviderApplicationTests {
    @Autowired
    private KafkaProvider kafkaProvider;

    @Test
    public void contextLoads() throws InterruptedException {
        // 发送 1000 个消息
        for (int i = 0; i < 100; i++) {
            long orderId = i+1;
            String orderNum = UUID.randomUUID().toString();
            kafkaProvider.sendMessage(orderId, orderNum, LocalDateTime.now());
            Thread.sleep(500);
        }
    }

}
