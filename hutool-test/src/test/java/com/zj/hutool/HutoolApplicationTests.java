package com.zj.hutool;

import cn.hutool.core.lang.ObjectId;
import cn.hutool.core.lang.Snowflake;
import cn.hutool.core.util.IdUtil;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class HutoolApplicationTests {

    @Test
    void IdUtilTest() throws InterruptedException {
//        //生成的UUID是带-的字符串，类似于：a5c8a5e8-df2b-4706-bea4-08d0939410e3
//        String uuid = IdUtil.randomUUID();
//        System.out.println("uuid："+uuid);
//
//        //生成的是不带-的字符串，类似于：b17f24ff026d40949c85a24f4f375d42
//        String simpleUUID = IdUtil.simpleUUID();
//        System.out.println("simpleUUID："+simpleUUID);
//
//        //生成类似：5b9e306a4df4f8c54a39fb0c
//        String id = ObjectId.next();
//        System.out.println("id："+id);
//
//        //方法2：从Hutool-4.1.14开始提供
//        String id2 = IdUtil.objectId();
//        System.out.println("id2："+id2);

        //雪花算法:参数1为终端ID,参数2为数据中心ID
        Snowflake snowflake = IdUtil.createSnowflake(1, 1);
        long snowflakeId = snowflake.nextId();
        System.out.println("snowflakeId："+snowflakeId);

//        Thread.sleep(1);

        Snowflake snowflake1 = IdUtil.createSnowflake(2, 1);
        long snowflakeId1 = snowflake1.nextId();
        System.out.println("snowflakeId1："+snowflakeId1);
    }

}
