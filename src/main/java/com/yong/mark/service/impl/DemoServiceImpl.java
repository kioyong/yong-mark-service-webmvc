package com.yong.mark.service.impl;

import com.yong.mark.service.DemoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

/**
 * @acthor yong.a.liang
 * @date 2018/03/01
 */
@Service
@Slf4j
public class DemoServiceImpl implements DemoService{
    @Override
    public String getName(String id) {
        return id;
    }

    @Cacheable("abc")
    @Override
    public String getCacheString(String id) {
        log.info("start get cache string :{}", id);
        return "cache string " + id;
    }
}
