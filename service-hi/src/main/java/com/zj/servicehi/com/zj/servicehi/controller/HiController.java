/**
 * Copyright (C), 2019
 * FileName: HiController
 * Author:   zhangjian
 * Date:     2019/5/10 19:33
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.zj.servicehi.com.zj.servicehi.controller;

import com.zj.servicehi.servicehi.service.AsynHiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

@RestController
public class HiController {
    @Autowired
    private AsynHiService asynHiService;

    @RequestMapping("/hi")
    public String home(@RequestParam(defaultValue = "zj1") String name1, @RequestParam(defaultValue = "zj2")String name2) throws InterruptedException {
        String str1="初始内容";
        String str2=null;

        Future future=asynHiService.AsynHi(name1,name2);

        SimpleDateFormat df = new SimpleDateFormat("HH:mm:ss");//设置日期格式
        System.out.println("str2赋值前时间==="+df.format(new Date()));

        try {
            //FutureTask.get()会阻塞主线程最多X秒来获取‘将来’结果.如果到时间还没有结果,则报错
            // 当不调用此方法时，主线程不会阻塞！
            future.get(5000, TimeUnit.MILLISECONDS);
            str2=future.get().toString();
        } catch (Exception e) {
            //e.printStackTrace();
            str2="future两秒get失败!";
        }

        System.out.println("str2赋值后时间==="+df.format(new Date()));


        return str1+"====="+str2;
    }

}
