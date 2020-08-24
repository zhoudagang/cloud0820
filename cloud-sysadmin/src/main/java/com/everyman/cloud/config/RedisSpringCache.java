package com.everyman.cloud.config;

import org.springframework.cache.CacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;

import javax.annotation.Resource;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

/**
 * @author zhougang
 */

@Configuration
public class RedisSpringCache
{
    @Resource
    private  RedisConnectionFactory redisConnectionFactory;

    @Bean
    public CacheManager cacheManager()
    {
        // 设置特有的Redis配置
        Map<String, RedisCacheConfiguration> cacheConfigurations = new HashMap<>(1);
        // user信息缓存配置
        RedisCacheConfiguration userCacheConfiguration = RedisCacheConfiguration.defaultCacheConfig()
                .entryTtl(Duration.ofMinutes(30000))
                .disableCachingNullValues()
                .prefixKeysWith("user");


        // 定制化的Cache为30000
        cacheConfigurations.put("user", userCacheConfiguration);

        // 默认超时时间60s
        return RedisCacheManager.builder(redisConnectionFactory)
                // Cache的事务支持
                .transactionAware()
                // 设置个性化的Cache配置
                .withInitialCacheConfigurations(cacheConfigurations)
                .build();
    }


}
