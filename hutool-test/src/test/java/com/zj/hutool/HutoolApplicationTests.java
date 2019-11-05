package com.zj.hutool;

import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.img.Img;
import cn.hutool.core.img.ImgUtil;
import cn.hutool.core.io.FileUtil;
import cn.hutool.core.lang.Console;
import cn.hutool.core.lang.ObjectId;
import cn.hutool.core.lang.Snowflake;
import cn.hutool.core.util.IdUtil;
import cn.hutool.system.SystemUtil;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.awt.*;
import java.util.Calendar;
import java.util.Date;

@SpringBootTest
class HutoolApplicationTests {

    /**
     * 唯一id
     */
    @Test
    void IdUtilTest() {
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

    /**
     * 时间工具类
     */
    @Test
    void timeTest() {
        //当前时间
        Date date = DateUtil.date();
        System.out.println(date.toString());

//        //当前时间
//        Date date2 = DateUtil.date(Calendar.getInstance());
//        System.out.println(date2.toString());
//
//        //当前时间
//        Date date3 = DateUtil.date(System.currentTimeMillis());
//        System.out.println(date3.toString());

        //当前时间字符串，格式：yyyy-MM-dd HH:mm:ss
        String now = DateUtil.now();
        System.out.println(now);

        //当前日期字符串，格式：yyyy-MM-dd
        String today= DateUtil.today();
        System.out.println(today);

        //获得月份，从0开始计数
        int month = DateUtil.month(date);
        System.out.println(month);

        //上周
        DateTime dateTime = DateUtil.lastWeek();
        System.out.println(dateTime);

        SystemUtil.getRuntimeInfo();


    }

    /**
     * 图片工具类
     */
    @Test
    void imgTest() {
        //缩放
        ImgUtil.scale(
                FileUtil.file("d:/face.jpg"),
                FileUtil.file("d:/face_result.jpg"),
                0.1f//缩放比例
        );

        //裁剪的矩形区域
        ImgUtil.cut(
                FileUtil.file("d:/face.jpg"),
                FileUtil.file("d:/face_result.jpg"),
                new Rectangle(200, 200, 100, 100)
        );


        ImgUtil.convert(FileUtil.file("d:/face.jpg"), FileUtil.file("d:/test2Convert.png"));


        ImgUtil.pressText(//
                FileUtil.file("d:/face.jpg"), //
                FileUtil.file("d:/test2_result.png"), //
                "版权所有", Color.WHITE, //文字
                new Font("黑体", Font.BOLD, 20), //字体
                500, //x坐标修正值。 默认在中间，偏移量相对于中间偏移
                500, //y坐标修正值。 默认在中间，偏移量相对于中间偏移
                0.8f//透明度：alpha 必须是范围 [0.0, 1.0] 之内（包含边界值）的一个浮点数字
        );

        ImgUtil.pressImage(
                FileUtil.file("d:/face.jpg"),
                FileUtil.file("d:/dest.jpg"),
                ImgUtil.read(FileUtil.file("D:/Downloads/截图/登录页.png")), //水印图片
                0, //x坐标修正值。 默认在中间，偏移量相对于中间偏移
                0, //y坐标修正值。 默认在中间，偏移量相对于中间偏移
                0.5f
        );

        // 将face.jpg切割为原型保存为face_radis.png
        Img.from(FileUtil.file("d:/face.jpg"))
                .cut(0, 0, 200)//
                .write(FileUtil.file("d:/face_radis.png"));

        Img.from(FileUtil.file("d:/1111_target.jpg"))
                .setQuality(0.2)//压缩比率
                .write(FileUtil.file("d:/1.jpg"));

    }

}
