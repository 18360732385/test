/**
 * Copyright (C), 2019
 * FileName: WebLogAspect
 * Author:   zhangjian
 * Date:     2019/8/31 22:31
 * Description: web请求切面
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.zj.stream.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.*;


@Aspect
@Component
class WebLogAspect {

    private Logger logger = LoggerFactory.getLogger(WebLogAspect.class);

    @Pointcut("execution(public * com.zj.stream.controller..*.*(..))")
    public void webLog() {
    }

    @Before("webLog()")
    public void doBefore(JoinPoint joinPoint) throws Throwable {
        // 接收到请求，记录请求内容
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();

//        Map<String, String[]> parameterMap = request.getParameterMap();
//        Set<String> paramNames = parameterMap.keySet();
//        //方法参数名
          MethodSignature methodSignature = (MethodSignature)joinPoint.getSignature();
//        String[] parameterName = methodSignature.getParameterNames();

        HashMap<String, Object> args = new HashMap<>();
        Enumeration<String> parameterNames = request.getParameterNames();
        while(parameterNames.hasMoreElements()){
            String name = parameterNames.nextElement();
            String parameter = request.getParameter(name);
            args.put(name,parameter);
        }

        //头参数(实际开发中应该取token)
        HashMap<String, Object> headArgs = new HashMap<>();
        Enumeration<String> headerNames = request.getHeaderNames();
        while(headerNames.hasMoreElements()){
            String name = headerNames.nextElement();
            String header = request.getHeader(name);
            headArgs.put(name,header);
        }

        // 记录下请求内容
        logger.info("URL : " + request.getRequestURL().toString());
        logger.info("HTTP_METHOD : " + request.getMethod());
        logger.info("IP : " + request.getRemoteAddr());
        logger.info("CLASS_METHOD : " + methodSignature.getDeclaringTypeName() + "." + joinPoint.getSignature().getName());
        logger.info("HEADARGS : "+headArgs+"】");
        logger.info("ARGS : "+args);
        //logger.info("PARAME :"+Arrays.toString(parameterNames));
        //logger.info("ARGS : " + Arrays.toString(joinPoint.getArgs()));
    }

    @AfterReturning(returning = "ret", pointcut = "webLog()")
    public void doAfterReturning(Object ret) throws Throwable {
        // 处理完请求，返回内容
        logger.info("RESPONSE : " + ret);
    }


}

