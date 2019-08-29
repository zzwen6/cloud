package top.hting.cloud.config;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import feign.RequestInterceptor;
import feign.RequestTemplate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;
import java.util.Set;

/**
 * 解决服务间调用请求头未携带的问题
 */
// @Configuration
@Slf4j
// @EnableFeignClients(basePackages = "top.hting.cloud.feign")
public class FeignConfig implements RequestInterceptor {


    @Override
    public void apply(RequestTemplate requestTemplate) {


        log.info("FeignConfig:{}",Thread.currentThread().getName());
        RequestAttributes attributes = RequestContextHolder.getRequestAttributes();
        javax.servlet.http.HttpServletRequest request = ((ServletRequestAttributes) attributes).getRequest();

        Enumeration<String> headerNames = request.getHeaderNames();

        while (headerNames.hasMoreElements()) {
            String key = headerNames.nextElement();

            requestTemplate.header(key, request.getHeader(key));
        }


    }
}
