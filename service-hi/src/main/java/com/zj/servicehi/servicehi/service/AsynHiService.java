/**
 * Copyright (C), 2019
 * FileName: AsynHiService
 * Author:   zhangjian
 * Date:     2019/5/10 17:11
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.zj.servicehi.servicehi.service;

import com.zj.servicehi.utils.ExecutorUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

@Service
public class AsynHiService {

    @Value("${server.port}")
    String port;


    public Future AsynHi(String name1,String name2) throws InterruptedException {
        SimpleDateFormat df = new SimpleDateFormat("HH:mm:ss");


        //获取线程池工具类
        ExecutorUtil executorUtil = ExecutorUtil.getInstance();
        //获取异步Future对象
        Future future = executorUtil.submit(new Callable() {
            @Override
            public String call() throws Exception {
                //线程里引用外面的非线程方法
                return TimeMethod(name1, name2);
            }
        });

        System.out.println("开始休眠==="+df.format(new Date()));
        Thread.sleep(2000);
        System.out.println("结束休眠==="+df.format(new Date()));


        return future;
    }

    private String TimeMethod(String name1,String name2)  throws InterruptedException {
        SimpleDateFormat df = new SimpleDateFormat("HH:mm:ss");

        System.out.println("异步调用AsynHi服务开始==="+df.format(new Date()));
        Thread.sleep(3000);
        System.out.println("异步调用AsynHi服务结束==="+df.format(new Date()));

        return "hi " + name1+"和"+name2+ " ,i am from port:" + port;
    }


}
