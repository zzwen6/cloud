package top.hting.cloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * Hello world!
 *
 */
@SpringBootApplication
@EnableDiscoveryClient
public class ZookeeperServerApp
{
    public static void main( String[] args )
    {
        new SpringApplication(ZookeeperServerApp.class).run(args);
    }
}
