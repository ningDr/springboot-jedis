package com.ningdr.redis.jedis.demo.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * jedis配置
 * @author ning.dr@foxmail.com
 * @date 2021/1/5 14:57
 */
@Configuration
public class RedisConfig {
    private static final Logger LOGGER = LoggerFactory.getLogger(RedisConfig.class);

    @Value("${spring.redis.host}")
    private String host;

    @Value("${spring.redis.port}")
    private int port;

    @Value("${spring.redis.database}")
    private int database;

    @Value("${spring.redis.timeout}")
    private int timeout;

    @Value("${spring.redis.jedis.pool.max-active}")
    private int poolMaxTotal;

    @Value("${spring.redis.jedis.pool.max-idle}")
    private int poolMaxIdle;

    @Value("${spring.redis.jedis.pool.min-idle}")
    private int poolMinIdle;

    @Value("${spring.redis.jedis.pool.max-wait}")
    private int poolMaxWait;

    @Bean
    public JedisPool jedisPoolFactory() {
        LOGGER.info("初始化jedis配置");
        JedisPoolConfig poolConfig = new JedisPoolConfig();
        poolConfig.setMaxIdle(poolMaxIdle);
        poolConfig.setMinIdle(poolMinIdle);
        poolConfig.setMaxTotal(poolMaxTotal);
        poolConfig.setMaxWaitMillis(poolMaxWait);
        return new JedisPool(poolConfig, host, port, timeout);
    }

}
