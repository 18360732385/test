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

import com.zj.stream.constants.MyKey;
import com.zj.stream.util.RequestWrapper;
import com.zj.stream.util.ResponseWrapper;
import com.zj.stream.util.RsaUtil;
import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

//@Component
@Configuration
//@EnableConfigurationProperties({MyKey.class})
public class MyFilter implements Filter {
    //秘钥对可以在linux服务器上生成
    String transformation = "RSA";
    String privateKeyBase64 = "MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBAI3NEl9g6ibmCS1ORpNmyEg9Kbk37NEZJWvp5Z0tpO54W7BIds7/l6BPod2q0hdNPEPgnVClSyLs6ryPdYLsJqPGtmfId2YTWHwex6Uy6Ivk3UUPeTVVL+/GNg+1glbsVrKmCOGCKMXBaX6zPIG2+WLfC9LWsFgVG57095MvtGxJAgMBAAECgYBdGUr6vBJ/v4A+8ql7lXvhkeaW6JTfI/dhxosuiw1CVFs+fhUjCsRuSFopw0F0cw0iM5KVpDCUmZ/0dOveLVWgLemebb5sMPTEwvVTEsamY4ku/1FcwOVw3wxjXguRcbgLzsri6iHlbiOblNoZaazw64obEZ6jX+zB9oWomssCUQJBANRjDxbNqSoEeqaB1MzcvQQPPybwpDh1PCX3cEG/l6VG2gNTO44DwqMle13zpVc7lGxQikzpHBLzHNKZw70TuvMCQQCq62SMpsBp2594KkRkRlhrk3zThGzs1XDMBrrOUfgl8wqAERh3PtYLMmps4fd1PcFbLUlHD5+W+B1mUDPsEtLTAkEAwm4c9ic4YfrPvXbFtPWvI/RBQAi0jerlMWygG9ClpuyB0OF1d8EBghFiKtRN3NnyOmZQ9a/Bv6dID5QsmP9i+QJAdE+XrzdSvTbdgHKS9AIC7cICMhZt4YUmK1FxEjIpwflwbdI0agFyu0/lqI7lTP1ndVqOATOakKvrpdJyYvY0TQJAGjp9n8rpaB0nN8e3MMq5I2msJhKvkQR5fzw+MvARXJxxpdA6Ms/d+IGLu2B6JjEYXhXnbtiQHHdd3HkTN8p/1w==";
    String publicKeyBase64 = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCNzRJfYOom5gktTkaTZshIPSm5N+zRGSVr6eWdLaTueFuwSHbO/5egT6HdqtIXTTxD4J1QpUsi7Oq8j3WC7CajxrZnyHdmE1h8HselMuiL5N1FD3k1VS/vxjYPtYJW7FaypgjhgijFwWl+szyBtvli3wvS1rBYFRue9PeTL7RsSQIDAQAB";

    @Autowired
    MyKey myKey;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("my filter 初始化");
//        if (null==myKey){
//            myKey= (MyKey) SpringUtils.getBean("MyKey");
//        }
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        System.out.println("my filter 启动");
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
            System.out.println("加密错误：" + e);
        }


        System.out.println("my filter 耗时:" + (new Date().getTime() - start));
        System.out.println("my filter 结束");
    }

    @Override
    public void destroy() {
        System.out.println("my filter 销毁");
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
                privateDecryptResult = RsaUtil.decryptByPrivateKey(parameter, Base64.decodeBase64(privateKeyBase64), transformation);
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
                ciphertext = RsaUtil.encryptByPublicKey(str, Base64.decodeBase64(publicKeyBase64), transformation);
                System.out.println("响应报文加密:" + ciphertext);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return ciphertext;
    }
}

