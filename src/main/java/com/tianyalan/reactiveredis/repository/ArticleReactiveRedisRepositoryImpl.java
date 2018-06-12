package com.tianyalan.reactiveredis.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ReactiveRedisTemplate;
import org.springframework.stereotype.Repository;

import com.tianyalan.reactiveredis.domain.Article;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public class ArticleReactiveRedisRepositoryImpl implements ArticleReactiveRedisRepository {

	@Autowired
	private ReactiveRedisTemplate<String, Article> reactiveRedisTemplate;

	private static final String HASH_NAME = "Article:";

	@Override
	public Mono<Boolean> saveArticle(Article article) {
		return reactiveRedisTemplate.opsForValue().set(HASH_NAME + article.getId(), article);
	}

	@Override
	public Mono<Boolean> updateArticle(Article article) {
		return reactiveRedisTemplate.opsForValue().set(HASH_NAME + article.getId(), article);
	}

	@Override
	public Mono<Boolean> deleteArticle(String articleId) {
		return reactiveRedisTemplate.opsForValue().delete(HASH_NAME + articleId);
	}

	@Override
	public Mono<Article> findArticleById(String articleId) {
		return reactiveRedisTemplate.opsForValue().get(HASH_NAME + articleId);
	}

	public Flux<Article> findAllArticles() {
		return reactiveRedisTemplate.keys(HASH_NAME + "*").flatMap((String key) -> {
			Mono<Article> mono = reactiveRedisTemplate.opsForValue().get(key);
			return mono;
		});
	}

}
