package com.learning.SpringBootSelf.services;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

@SpringBootTest
public class RedisTests {

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @Test
    public void testRedis() {
        redisTemplate.opsForValue().set("hello", "world");
        System.out.println(redisTemplate.opsForValue().get("name"));
        System.out.println(redisTemplate.opsForValue().get("hello")); // should print "world"
    }
}
