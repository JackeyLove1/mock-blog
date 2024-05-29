package com.example.mockblog.handler;

import com.example.mockblog.mapper.ArticleMapper;
import com.example.mockblog.utils.TimeUtils;
import lombok.extern.slf4j.Slf4j;
import net.sf.jsqlparser.expression.DateTimeLiteralExpression;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
@Slf4j
@EnableScheduling
public class ViewCountHandler {
    @Autowired
    private StringRedisTemplate redisTemplate;

    @Autowired
    private ArticleMapper articleMapper;

    @Scheduled(cron = "0 0/10 * * * *")
    @Async("taskExecutor")
    public void scheduledUpdateViewCount() {
        log.info("=====>>>>> 同步浏览量开始执行  {}", TimeUtils.getCurrentTime());
        Map<Object, Object> viewCountMap = redisTemplate.opsForHash().entries("viewCount");
        viewCountMap.forEach((key, value) -> {
            Long articleId = Long.parseLong(key.toString());
            Integer viewCount = Integer.parseInt(value.toString());
            articleMapper.updateViewCountsById(articleId, viewCount);
        });
        log.info("=====>>>>> 同步浏览量结束  {}", TimeUtils.getCurrentTime());
    }

}
