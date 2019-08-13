package top.hting.cloud;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import top.hting.cloud.domain.SysUser;
import top.hting.cloud.feign.SysUserService;

/**
 * Hello world!
 *
 */
@EnableDiscoveryClient
@SpringBootApplication
@RestController
public class PayWebApp
{
    @Autowired
    SysUserService sysUserService;

    public static void main( String[] args )
    {
        SpringApplication.run(PayWebApp.class, args);
    }

    @GetMapping("/user/get")
    public SysUser get(){
        return sysUserService.findById(1L);
    }

}
