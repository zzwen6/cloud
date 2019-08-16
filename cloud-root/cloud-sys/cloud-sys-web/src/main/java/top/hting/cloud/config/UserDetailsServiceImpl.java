package top.hting.cloud.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import top.hting.cloud.dto.UserDto;
import top.hting.cloud.security.JwtUserDetails;
import top.hting.cloud.service.SysUserService;

@Component
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    SysUserService sysUserService;


    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {

        UserDto userDto = sysUserService.findByAccount(s);

        JwtUserDetails userDetails = new JwtUserDetails(userDto.getUser().getPassword(), s);

        return userDetails;


    }
}
