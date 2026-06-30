package org.uniplovdiv.fmi.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class NumberService {

    private final CacheManager cacheManager;

    public void putNumberInCache(int num) {
        Cache cache = cacheManager.getCache("numbers");
        cache.put("num" + num, num);
    }

    public Integer getNumberFromCache(String id) {
        Cache cache = cacheManager.getCache("numbers");
        return cache.get(id, Integer.class);
    }
}
