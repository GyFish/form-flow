package com.gyfish.formflow;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Set;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RedisTest {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    /**
     * 测试并发增加 hash 的某个值
     */
    @Test
    public void concurrentHashInc() {

        System.out.println("//~ start --------------------");
        long startTime = System.currentTimeMillis();
        System.out.println("answerCount = "
                + stringRedisTemplate.opsForHash().get("state", "answerCount"));

        for (int i = 0; i < 8; i++) {
            new Thread(() -> {
                for (int j = 0; j < 5000; j++) {
                    // HINCRBY key field increment
                    stringRedisTemplate.opsForHash().increment("state", "answerCount", 2);
                }
            }).run();
        }

        System.out.println("//~ end --------------------");
        long endTime = System.currentTimeMillis();
        System.out.println("answerCount = "
                + stringRedisTemplate.opsForHash().get("state", "answerCount"));

        System.out.println("cost = " + (endTime - startTime));

    }


}
