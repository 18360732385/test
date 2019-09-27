/**
 * Copyright (C), 2019
 * FileName: ExecutorUtil
 * Author:   zhangjian
 * Date:     2019/5/15 15:03
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.zj.servicehi.utils;

import java.util.concurrent.*;

/*
注:阿里巴巴不允许使用Executors来创建线程池,原因是默认的四种线程池缓存队列无限大,容易造成内存溢出
* 线程池工具类
* 线程池类型:自定义new ThreadPoolExecutor()
* 模式:单例(静态内部类)
* */
public class ExecutorUtil {
    //线程池大小
    private int poolSize=10;
    //线程池允许最大值
    private int poolMaxSize=50;
    //空闲线程存活时间
    private long keepAliveTime=30L;
    //缓存队列大小
    private int queueSize=100;


    private ExecutorService executor;

    private ExecutorUtil(){
        //推荐new ThreadPoolExecutor()自定义线程池
        executor=new ThreadPoolExecutor(poolSize,poolMaxSize,keepAliveTime,
                                        TimeUnit.MILLISECONDS,
                                        new ArrayBlockingQueue<Runnable>(queueSize),
                                        //拒绝策略:由调用线程的线程处理任务
                                        new ThreadPoolExecutor.CallerRunsPolicy());
    }

    //静态内部类
    private static class ExecutorUtilHandler{
        private final static ExecutorUtil executorUtil =new ExecutorUtil();
    }

    //公开方法调用单例
    public static ExecutorUtil getInstance(){
        return ExecutorUtilHandler.executorUtil;
    }

    //返回线程返回的结果
    public Future submit(Callable task){
        return executor.submit(task);
    }

    //返回线程执行是否完成(null为完成)
    public Future<?> submit(Runnable task){
        return executor.submit(task);
    }

    //无返回结果
    private void execute(Runnable command){
         executor.execute(command);
    }
}
