package top.hting.cloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import top.hting.cloud.config.RequestAttributeHystrixConcurrencyStrategy;

/**
 * Hello world!
 *
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableEurekaClient
@EnableFeignClients(basePackages = "top.hting.cloud.feign")
// @EnableFeignClients
// @EnableCircuitBreaker
public class GateWayServerApp
{
    public static void main( String[] args )
    {
        SpringApplication.run(GateWayServerApp.class, args);
    }
}
