package com.sg.springbootmonitoring.config;

import org.cache2k.extra.spring.SpringCache2kCacheManager;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@EnableCaching
@Configuration
public class CacheConfig {

    @Bean
    public CacheManager cacheManager() {
        return new SpringCache2kCacheManager().defaultSetup(b->b.entryCapacity(1000))
                .addCache(b->b.name("employees")
                .disableMonitoring(false));
    }
}
