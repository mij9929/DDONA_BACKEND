package com.potenday.anotherme.service;

import com.potenday.anotherme.model.CacheCompletionMessages;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class CacheService {

    private final CacheManager cacheManager;

    @Autowired
    public CacheService(CacheManager cacheManager) {
        this.cacheManager = cacheManager;
    }

    public void saveMessages(String userId, List<Map<String, String>> messages) {
        Cache cache = cacheManager.getCache("CompletionCacheManager");
        if (cache != null) {
            Cache.ValueWrapper existingWrapper = cache.get(userId);
            if (existingWrapper != null) {

                // 기존 데이터가 있으면 업데이트
                CacheCompletionMessages existingMessages = (CacheCompletionMessages) existingWrapper.get();
                existingMessages.setMessages(messages);
                cache.put(userId, existingMessages);
            } else {
                // 새로운 데이터 저장
                CacheCompletionMessages newMessages = new CacheCompletionMessages();
                newMessages.setMessages(messages);
                cache.put(userId, newMessages);
            }
        }
    }

    public CacheCompletionMessages getPrevMessages(String userId) {
        Cache cache = cacheManager.getCache("CompletionCacheManager");
        if (cache != null) {
            Cache.ValueWrapper valueWrapper = cache.get(userId);
            if (valueWrapper != null) {
                return (CacheCompletionMessages) valueWrapper.get();
            }
        }
        return null;
    }

}
