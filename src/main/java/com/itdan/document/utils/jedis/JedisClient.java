package com.itdan.document.utils.jedis;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * jedis操作接口
 */
public interface JedisClient {

	String set(String key, String value);
	String get(String key);
	Boolean exists(String key);
	Long expire(String key, int seconds);
	Long ttl(String key);
	Long incr(String key);
	Long hset(String key, String field, String value);
	Set smembers(String key);
	String hget(String key, String field);
	Long hdel(String key, String... field);
	Boolean hexists(String key, String field);
	List<String> hvals(String key);
	Long del(String key);
	Map<String,String> hgetAll(String key);

}
