package top.hting.cloud.feign;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import top.hting.cloud.domain.SysUser;

@FeignClient(value = "cloud-sys-web",path = "/sysUser")
public interface SysUserService {

    @GetMapping("/get")
    SysUser findById(Long userId);

}
