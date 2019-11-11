package com.zj.redissondemo;

import com.zj.redissondemo.mapper.UserMapper;
import org.junit.jupiter.api.Test;
import org.redisson.Redisson;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.redisson.config.SingleServerConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * 单机redission分布式锁测试
 */
@SpringBootTest
class RedissondemoApplicationTests {
    @Autowired
    private UserMapper userMapper;


    @Test
    void lock() {
        Config config = new Config();
        SingleServerConfig serverConfig = config.useSingleServer()
                .setAddress("redis://127.0.0.1:6379");

        RedissonClient redissonClient = Redisson.create(config);

        RLock lock = redissonClient.getLock("anyLock");

        lock.lock();
        System.out.println("加锁成功："+lock.getName());

        try {
            userMapper.addUser("张三",18);
            Thread.sleep(20000);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    @Test
    void otherLock() {
        Config config = new Config();
        SingleServerConfig serverConfig = config.useSingleServer()
                .setAddress("redis://127.0.0.1:6379");

        RedissonClient redissonClient = Redisson.create(config);

        RLock lock = redissonClient.getLock("anyLock");

        lock.lock();
        System.out.println("加锁成功："+lock.getName());

        try {
            userMapper.addUser("李四",29);
            Thread.sleep(20000);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

}
