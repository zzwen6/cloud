package top.hting.cloud.config;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.parser.ParserConfig;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.spring.FastJsonRedisSerializer;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.cache.RedisCacheWriter;
import org.springframework.data.redis.connection.RedisConfiguration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.SerializationException;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import top.hting.cloud.redis.RedisUtils;

import java.nio.charset.Charset;

/**
 * redis 配置声明
 */
@Configuration
public class RedisConfig extends CachingConfigurerSupport {

    @Autowired
    RedisProperties redisProperties;


    @Bean
    public RedisConnectionFactory redisConnectionFactory() {
        RedisStandaloneConfiguration configuration = new RedisStandaloneConfiguration();

        configuration.setHostName(redisProperties.getHost());
        configuration.setDatabase(redisProperties.getDatabase());
        configuration.setPort(redisProperties.getPort());
        // 还可以设置其他的如连接池等 TODO

        return new JedisConnectionFactory(configuration);
    }

    @Bean
    @ConditionalOnBean(RedisConnectionFactory.class)
    public RedisTemplate redisTemplate(RedisConnectionFactory factory) {
        RedisTemplate<Object, Object> redisTemplate = new RedisTemplate<Object, Object>();
        redisTemplate.setConnectionFactory(factory);

        FastJson2RedisSerializer fastJsonRedisSerializer = new FastJson2RedisSerializer(Object.class);
        ParserConfig.getGlobalInstance().setAutoTypeSupport(true);
        redisTemplate.setValueSerializer(fastJsonRedisSerializer);
        redisTemplate.setHashValueSerializer(fastJsonRedisSerializer);

        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setHashKeySerializer(new StringRedisSerializer());
        redisTemplate.afterPropertiesSet();
        return redisTemplate;
    }

    @Bean
    @ConditionalOnBean(value = {RedisTemplate.class, RedisConnectionFactory.class})
    public RedisUtils redisUtils() {
        return new RedisUtils(redisTemplate(redisConnectionFactory()));
    }


    @Bean
    @ConditionalOnBean(value = {RedisTemplate.class, RedisConnectionFactory.class})
    public RedisCacheManager redisCacheManager(RedisTemplate redisTemplate) {


        FastJson2RedisSerializer fastJsonRedisSerializer = new FastJson2RedisSerializer(Object.class);
        ParserConfig.getGlobalInstance().setAutoTypeSupport(true);
        RedisCacheWriter redisCacheWriter = RedisCacheWriter.nonLockingRedisCacheWriter(redisTemplate.getConnectionFactory());
        RedisCacheConfiguration redisCacheConfiguration = RedisCacheConfiguration.defaultCacheConfig()
            .serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(fastJsonRedisSerializer));
        return new RedisCacheManager(redisCacheWriter, redisCacheConfiguration);
    }


    private static class FastJson2RedisSerializer<T> implements RedisSerializer<T> {
        private static final Charset DEFAULT_CHARSET = Charset.forName("UTF-8");
        private Class<T> clazz;
        public FastJson2RedisSerializer(Class<T> clazz) {
            this.clazz = clazz;
        }

        @Override
        public byte[] serialize(T t) throws SerializationException {
            if (t == null) {
                return new byte[0];
            }
            return JSON.toJSONString(t, SerializerFeature.WriteClassName).getBytes(DEFAULT_CHARSET);
        }

        @Override
        public T deserialize(byte[] bytes) throws SerializationException {
            if (bytes == null || bytes.length <= 0) {
                return null;
            }
            String str = new String(bytes, DEFAULT_CHARSET);
            return  JSON.parseObject(str, clazz);
        }
    }
}
