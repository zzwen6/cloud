package top.hting.cloud.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import top.hting.cloud.dto.JwtUserDto;
import top.hting.cloud.dto.UserDto;

@FeignClient(value = "cloud-sys-web")
public interface UserServiceFeign {

    @RequestMapping(value = "/sysUser/login", method = RequestMethod.POST)
    UserDto login(@RequestBody JwtUserDto userDto);
}