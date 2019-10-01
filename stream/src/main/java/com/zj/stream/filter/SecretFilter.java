/**
 * Copyright (C), 2019
 * FileName: MyFilter
 * Author:   zhangjian
 * Date:     2019/9/2 18:49
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.zj.stream.filter;

import com.zj.stream.constants.SecretKey;
import com.zj.stream.util.RequestWrapper;
import com.zj.stream.util.ResponseWrapper;
import com.zj.stream.util.RsaUtil;
import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

/**
 * 报文加解密过滤器
 * 关于过滤器中无法注入bean问题，需要设置改bean的变量为staric属性
 */
//@Component
//@EnableConfigurationProperties({MyKey.class})
@Configuration
public class SecretFilter implements Filter {

    String transformation = "RSA";//加密算法

    @Autowired
    SecretKey secretKey;

    @Override
    public void init(FilterConfig filterConfig)  {
        System.out.println("SecretFilter 初始化");
//        System.out.println("init私钥: "+myKey.privateKeyBase64);
        System.out.println("init测试私钥是否为空: "+ SecretKey.privateKeyBase64);

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        System.out.println("SecretFilter 启动");

        System.out.println("doFilter测试私钥是否为空： "+ SecretKey.privateKeyBase64);

        long start = new Date().getTime();

        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        ResponseWrapper responseWrapper = new ResponseWrapper(response);

        //请求报文解密（可以判断路径是否需要解密）
        RequestWrapper requestWrapper = reuqestDecrypt(request);

        //解密报文调用服务。注意，这里要使用替代的request和response！！！
        filterChain.doFilter(requestWrapper, responseWrapper);

        //返回报文加密（可以判断路径是否需要加密）
        try {
            String responseEnCrypt = responseEnCrypt(responseWrapper);
            response.reset();//很重要，清除空行？
            response.getWriter().write(responseEnCrypt);
        } catch (Exception e) {
            System.out.println("SecretFilter加密错误：" + e);
        }


        System.out.println("SecretFilter 耗时:" + (new Date().getTime() - start));
        System.out.println("SecretFilter 结束");
    }

    @Override
    public void destroy() {
        System.out.println("SecretFilter 销毁");
    }

    /**
     * @param request
     * @return 请求报文解密(可以放到工具类util去)
     */
    public RequestWrapper reuqestDecrypt(HttpServletRequest request) {
        Map<String, Object> args = new HashMap<>();
        Enumeration<String> parameterNames = request.getParameterNames();
        while (parameterNames.hasMoreElements()) {
            String name = parameterNames.nextElement();
            String parameter = request.getParameter(name);
            System.out.println("请求原文：" + name + "===" + parameter);

            String privateDecryptResult = null;
            try {
                privateDecryptResult = RsaUtil.decryptByPrivateKey(parameter, Base64.decodeBase64(SecretKey.privateKeyBase64), transformation);
                args.put(name, privateDecryptResult);
                System.out.println("私钥解密:" + name + "===" + privateDecryptResult);
            } catch (Exception e) {
                e.printStackTrace();
            }

        }

        //利用原始的request对象创建自己扩展的request对象并添加自定义参数
        RequestWrapper requestWrapper = new RequestWrapper(request);
        requestWrapper.addParameters(args);

        return requestWrapper;
    }

    /**
     * @param responseWrapper
     * @return
     * @throws Exception
     * 响应报文加密(可以放到工具类util去)
     */
    public String responseEnCrypt(ResponseWrapper responseWrapper) throws IOException {
        byte[] content = responseWrapper.getResponseData();
        String ciphertext = null;

        if (content.length > 0) {
            String str = new String(content, "UTF-8");
            System.out.println("响应报文原文:" + str);

            try {
                ciphertext = RsaUtil.encryptByPublicKey(str, Base64.decodeBase64(SecretKey.publicKeyBase64), transformation);
                System.out.println("响应报文加密:" + ciphertext);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return ciphertext;
    }
}

