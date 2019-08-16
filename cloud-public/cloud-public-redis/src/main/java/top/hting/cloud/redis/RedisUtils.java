package top.hting.cloud.redis;

import org.springframework.data.redis.core.RedisTemplate;

/**
 * 构造器注入
 */
public class RedisUtils {

    private RedisTemplate<String, Object> redisTemplate;

    public RedisUtils(RedisTemplate<String, Object> redisTemplate){
        this.redisTemplate = redisTemplate;
    }


    public Object get(String key) {
        return null == key ? null : redisTemplate.opsForValue().get(key);
    }


    public void set(String key, Object value){
        redisTemplate.opsForValue().set(key, value);
    }

}
