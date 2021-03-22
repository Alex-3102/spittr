package spittr.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.ehcache.EhCacheCacheManager;
import org.springframework.cache.ehcache.EhCacheManagerFactoryBean;
import org.springframework.cache.support.CompositeCacheManager;
import org.springframework.cache.support.SimpleCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import spittr.Spitter;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @author arc3102
 * @date 2021/2/22 13:00
 */
@Configuration
@EnableCaching
public class CachingConfig {

    //使用CompositeCacheManager配置多个CacheManager
    @Bean(name = "cacheManager")
    public CacheManager cacheManager(RedisTemplate redisTemplate, net.sf.ehcache.CacheManager cm) {
        CompositeCacheManager cacheManager = new CompositeCacheManager();
        List<org.springframework.cache.CacheManager> managers = new ArrayList<org.springframework.cache.CacheManager>();
        RedisCacheManager redisCacheManager = new RedisCacheManager(redisTemplate);
        Collection<String> names = new ArrayList<String>();
        names.add("redis");
        redisCacheManager.setCacheNames(names);
        managers.add(redisCacheManager);
        managers.add(new EhCacheCacheManager(cm));
        cacheManager.setCacheManagers(managers);
        System.out.println(cacheManager.getCache("redis"));
        return cacheManager;
    }

    //为Ehcache缓存配置CacheManager
    /*
    注意该bean使用Ehcache的CacheManager注入，而不是Spring的CacheManager
    EhCacheCacheManager实现了Spring CacheManager
     */
//    @Bean
//    public EhCacheCacheManager cacheManager(CacheManager cm) {
//        return new EhCacheCacheManager(cm);
//    }
//
    @Bean
    public EhCacheManagerFactoryBean ehcache() {
        EhCacheManagerFactoryBean ehCacheFactoryBean = new EhCacheManagerFactoryBean();
        ehCacheFactoryBean.setConfigLocation(new ClassPathResource("cache/ehcache.xml"));
        return ehCacheFactoryBean;
    }

    //为Redis配置缓存管理器
//    @Bean
    public RedisCacheManager redisCacheManager(RedisTemplate redisTemplate) {
        RedisCacheManager redisCacheManager = new RedisCacheManager(redisTemplate);
        Collection<String> names = new ArrayList<String>();
        names.add("redis");
        redisCacheManager.setCacheNames(names);
        return redisCacheManager;
    }

    @Bean(name = "redisTemplate")
    public RedisTemplate<String, Spitter> redisTemplate(RedisConnectionFactory redisCF) {
        RedisTemplate<String, Spitter> redisTemplate = new RedisTemplate<String, Spitter>();
        redisTemplate.setConnectionFactory(redisCF);
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setValueSerializer(new Jackson2JsonRedisSerializer<Spitter>(Spitter.class));
        return redisTemplate;
    }
//
    @Bean
    public JedisConnectionFactory redisConnectionFactory() {
        JedisConnectionFactory jedisConnectionFactory = new JedisConnectionFactory();
        jedisConnectionFactory.setPassword("cjbcjbcjb");
        return jedisConnectionFactory;
    }
}
