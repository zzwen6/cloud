package top.hting.cloud.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.hting.cloud.constant.UserRedisConstant;
import top.hting.cloud.dto.JwtUserDto;
import top.hting.cloud.dto.UserDto;
import top.hting.cloud.feign.UserServiceFeign;
import top.hting.cloud.jwt.JWTUserInfo;
import top.hting.cloud.jwt.JWTUtils;
import top.hting.cloud.redis.RedisUtils;
import top.hting.cloud.service.AuthTokenService;

@Service
@Slf4j
public class AuthTokenServiceImpl implements AuthTokenService {

    @Autowired
    UserServiceFeign userServiceFeign;
    @Autowired
    RedisUtils redisUtils;
    @Override
    public String login(JwtUserDto userDto) {
        // 去请求feign接口
        UserDto userDto1 = userServiceFeign.login(userDto);

        // 验证成功，生成token返回
        String token = JWTUtils.generateToken(new JWTUserInfo(userDto1.getUser().getAccount(), userDto1.getUser().getUserName(), userDto1.getUser().getUserId().toString()));
        redisUtils.hset(UserRedisConstant.USER_TOKEN_HASH, userDto1.getUser().getAccount(), token, 30 * 60);

        log.info("token:{}", token);
        return token;
    }
}
