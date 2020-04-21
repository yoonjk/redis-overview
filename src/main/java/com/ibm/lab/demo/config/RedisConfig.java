package com.ibm.lab.demo.config;

import java.time.Duration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration
@EnableCaching
public class RedisConfig {
	
	@Value("${redis.channel}")
	private String redisChannel;
	
	@Value("${spring.redis.password}")
	private String redisPassword;
	
	@Value("${spring.redis.host}")
	private String hostName;

    @Bean
    public RedisConnectionFactory redisConnectionFactory() {
		
		  RedisStandaloneConfiguration redisStandaloneConfiguration = new
		  RedisStandaloneConfiguration();
		  redisStandaloneConfiguration.setHostName(hostName);
//		  redisStandaloneConfiguration.setPassword(redisPassword); //redis에 비밀번호가 설정 되어 있는 경우 설정해주면 됩니다.
		         
        LettuceConnectionFactory lettuceConnectionFactory = new LettuceConnectionFactory(redisStandaloneConfiguration);
        return lettuceConnectionFactory;
    }
    
    @Bean
    public RedisTemplate<String, Object> redisTemplate() {
        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(redisConnectionFactory());
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setValueSerializer(new GenericJackson2JsonRedisSerializer());
        
        return redisTemplate;
    }
    
	@Bean
	public RedisTemplate redisObjectTemplate() {
		RedisTemplate redisTemplate = new RedisTemplate();
		redisTemplate.setKeySerializer(new StringRedisSerializer());
		redisTemplate.setConnectionFactory(redisConnectionFactory());
		
		return redisTemplate;
	}
	
	@Bean
	public StringRedisTemplate strRedisTemplate() {
		StringRedisTemplate redisTemplate = new StringRedisTemplate();
		redisTemplate.setConnectionFactory(redisConnectionFactory());
		
		return redisTemplate;
	}
     

    
	@Bean(name = "cacheManager")
    public CacheManager cacheManager() {
        RedisCacheManager.RedisCacheManagerBuilder builder = RedisCacheManager.RedisCacheManagerBuilder.fromConnectionFactory(redisConnectionFactory());     

        // 값은 json 문자열로 넣는다. @class 필드로 클래스 정보가 들어간다.
        RedisCacheConfiguration defaultConfig =
                RedisCacheConfiguration.defaultCacheConfig()
                                       .serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(new GenericJackson2JsonRedisSerializer()))
                                       .entryTtl(Duration.ofSeconds(CacheKey.USER_EXPIRE_SEC));

        builder.cacheDefaults(defaultConfig);

        return builder.build();
    }
}
