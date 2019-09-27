package com.zj.redis;

import com.zj.redis.util.RedisUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RedisApplicationTests {
    @Autowired
    private RedisUtil redisUtil;

    @Test
    public void contextLoads() {
        Map<String, Object> map = new HashMap<>();
        map.put("name","小明");
        map.put("age",20);
        map.put("action","阅读");

        System.out.println("====="+redisUtil.hkeys("good"));
//        for (Map.Entry<Object, Object> entry:entries) {
//            System.out.println(entry.getKey().toString()+"======="+entry.getValue().toString() );
//        }

    }

}
