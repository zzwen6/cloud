package top.hting.cloud.feign;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import top.hting.cloud.config.FeignConfig;
import top.hting.cloud.dto.UserDto;
import top.hting.cloud.entity.SysUser;

@FeignClient(value = "cloud-sys-web",path = "/sysUser")
public interface SysUserService {

    /**
     * 定义feign接口时，方法参数一定要指定@RequestParam requestbody pathvariable之类的东西，否则调用方获取不到
     * @param userId
     * @return
     */
    @GetMapping("/get")
    SysUser findById(@RequestParam("userId") Long userId);

    /**
     *
     * @param userId
     * @return
     */
    @GetMapping("getUserPermissionById")
    UserDto getUserPermissionById(@RequestParam("userId") Long userId);

}
