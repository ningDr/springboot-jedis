package com.ningdr.redis.jedis.demo;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.util.HashSet;
import java.util.Set;

@SpringBootTest
class DemoApplicationTests {

    private static final Logger LOGGER = LoggerFactory.getLogger(DemoApplicationTests.class);
    private static final String SET1 = "set1";
    private static final String SET2 = "set2";

    @Autowired
    JedisPool jedisPool;

    @Test
    void setAdd() {
        try(Jedis jedis = jedisPool.getResource()) {
            jedis.flushDB();

            Set<String> s = new HashSet<>();
            s.add("aaa");
            s.add("bbb");
            s.add("ddd");
            String[] strs = s.toArray(new String[0]);
            jedis.sadd(SET1, strs);
            jedis.sadd(SET2, "aaa", "ccc", "ggg");

            Set<String> diff = jedis.sdiff(SET1, SET2);
            for (String str : diff) {
                LOGGER.info("sdiff--结果str=[{}]", str);
            }

            jedis.sdiffstore(SET1, SET1, SET2);
            jedis.sunionstore(SET2, SET1, SET2);

            Set<String> obj = jedis.smembers(SET1);
            for (String str : obj) {
                LOGGER.info("sdiffstore--结果str=[{}]", str);
            }

//            jedis.del(SET1);
//            jedis.srem(SET2, "aaa");

        } catch (Exception e) {
            LOGGER.error("redis连接出错：error=[{}]", e.getMessage());
        }
    }

}
