package com.tianyalan.reactiveredis;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.connection.ReactiveRedisConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.ReactiveRedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.web.client.RestTemplate;

import com.tianyalan.reactiveredis.domain.Article;

@SpringBootApplication
public class SpringReactiveRedisApplication {
	
	@Bean
	public RestTemplate getRestTemplate() {
		return new RestTemplate();
	}

	@Bean
	public ReactiveRedisConnectionFactory redisConnectionFactory() {
		return new LettuceConnectionFactory();
	}

	@Bean
	ReactiveRedisTemplate<String, String> reactiveRedisTemplate(ReactiveRedisConnectionFactory factory) {
		return new ReactiveRedisTemplate<>(factory, RedisSerializationContext.string());
	}

	@Bean
	ReactiveRedisTemplate<String, Article> redisOperations(ReactiveRedisConnectionFactory factory) {
		Jackson2JsonRedisSerializer<Article> serializer = new Jackson2JsonRedisSerializer<>(Article.class);

		RedisSerializationContext.RedisSerializationContextBuilder<String, Article> builder = RedisSerializationContext
				.newSerializationContext(new StringRedisSerializer());

		RedisSerializationContext<String, Article> context = builder.value(serializer).build();

		return new ReactiveRedisTemplate<>(factory, context);
	}	

	public static void main(String[] args) {
		SpringApplication.run(SpringReactiveRedisApplication.class, args);
	}
}
