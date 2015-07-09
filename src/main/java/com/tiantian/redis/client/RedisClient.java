package com.tiantian.redis.client;

public interface RedisClient {

	public <T> T get(String key, Class<T> cls);
}
