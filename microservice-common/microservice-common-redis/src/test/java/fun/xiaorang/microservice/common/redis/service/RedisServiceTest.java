package fun.xiaorang.microservice.common.redis.service;

import fun.xiaorang.microservice.common.redis.config.RedisConfig;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author xiaorang
 * @description <p style = " font-weight:bold ; "><p/>
 * @github <a href="https://github.com/xihuanxiaorang/microservice-practice">microservice-practice</a>
 * @Copyright 博客：<a href="https://blog.xiaorang.fun">小让的糖果屋</a>  - show me the code
 * @date 2023/11/23 00:19
 */
@EnableAutoConfiguration
@SpringBootTest(classes = {RedisConfig.class, RedisService.class})
class RedisServiceTest {
    private final RedisService redisService;

    @Autowired
    RedisServiceTest(final RedisService redisService) {
        this.redisService = redisService;
    }

    @Test
    void set() {
        redisService.set("test", "123456", 20);
    }

    @Test
    public void sSet() {
        redisService.sSet("hello", "world", "redis");
    }
}