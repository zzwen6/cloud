package top.hting.cloud.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import top.hting.cloud.entity.SysUser;
import top.hting.cloud.dto.JwtUserDto;
import top.hting.cloud.dto.UserDto;
import top.hting.cloud.service.SysUserService;

@RestController
@RequestMapping("/sysUser")
public class SysUserController {

    @Autowired
    SysUserService sysUserService;

    @GetMapping("get")
    public SysUser get(Long userId){
        return sysUserService.findById(userId);
    }


    @RequestMapping(value = "login", method = RequestMethod.POST)
    public UserDto login(@RequestBody JwtUserDto userDto){
        UserDto user = sysUserService.findJwtUserByAccountThenValid(userDto);
        return user;
    }

    // @PreAuthorize("hasAuthority('haha')")
    @RequestMapping(value = "/getUserPermissionById",method = RequestMethod.GET)
    public UserDto getUserPermissionById(@RequestParam("userId") Long userId){

        UserDto userDto = sysUserService.findUserPermission(userId);
        return userDto;
    }


}
