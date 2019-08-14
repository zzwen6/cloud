package top.hting.cloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * Hello world!
 *
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableEurekaClient
public class GateWayServerApp
{
    public static void main( String[] args )
    {
        SpringApplication.run(GateWayServerApp.class, args);
    }
}
