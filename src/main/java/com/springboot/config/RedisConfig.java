package com.springboot.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

@EnableCaching
@Configuration
public class RedisConfig extends CachingConfigurerSupport {

	private final static Logger LOGGER = LoggerFactory.getLogger(RedisConfig.class);

	@Value("${spring.redis.host}")
	private String host;

	@Value("${spring.redis.port}")
	private Integer port;

	@Value("${spring.redis.timeout}")
	private Integer timeOut;

	@Value("${spring.redis.pool.max-idle}")
	private Integer maxIdle;

	@Value("${spring.redis.pool.max-wait}")
	private Long maxWaitMillis;

	@Bean
	public JedisPool redisPoolFactory() {
		LOGGER.info("JedisPool注入成功");
		JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
		jedisPoolConfig.setMaxIdle(maxIdle);
		jedisPoolConfig.setMaxWaitMillis(maxWaitMillis);
		JedisPool jedisPool = new JedisPool(jedisPoolConfig, host, port, timeOut);
		return jedisPool;
	}
}
