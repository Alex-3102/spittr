package spittr.data;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import spittr.Spitter;

/**
 * @author arc3102
 * @date 2021/1/28 18:31
 */
public interface SpitterRepository {
    //自定缓存key，使用SpEL
    @CachePut(value = "redis", key = "#result.username")
    Spitter save(Spitter spitter);

    /*  value是指定缓存的name，通过CacheManager相关的实现类的setCacheNames方法可以设置缓存名称
        unless计算结果为true时，缓存方法返回的数据不会被放进缓存中，但是调用方法前还是会查询缓存中的数据，如果存在直接返回缓存结果
        例子：返回值的username字段中包含arc的不会被放入缓存中
        condition计算结果为false时，缓存会直接被禁用掉，也就是直接调用方法不会查询缓存，方法返回值也不会被放入缓存中
        例子：当参数username等于1801030092时，禁用缓存，不会查询缓存中的数据
     */
    @Cacheable(value = "redis", unless = "#result.username.contains('arc')", condition = "!#username.equals('1801030092')")
    Spitter findByUsername(String username);

}
