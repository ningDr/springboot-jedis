package com.ningdr.redis.jedis.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
/**
 * jedis测试启动
 * @author ning.dr@foxmail.com
 * @date 2021/1/5 15:05
 */
@SpringBootApplication(scanBasePackages = "com.ningdr.redis.jedis.demo")
public class DemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

}
