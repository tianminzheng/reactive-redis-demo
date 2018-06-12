package com.tianyalan.reactiveredis.service;

import org.springframework.stereotype.Service;

import com.tianyalan.reactiveredis.domain.Article;
import com.tianyalan.reactiveredis.repository.ArticleReactiveRedisRepository;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class ArticleService {
    private final ArticleReactiveRedisRepository articleRepository;

    ArticleService(ArticleReactiveRedisRepository articleRepository) {
        this.articleRepository = articleRepository;
    }

    public Mono<Boolean> save(Article article) {
        return articleRepository.saveArticle(article);
    }

    public Mono<Boolean> delete(String id) {
    	return articleRepository.deleteArticle(id);
    }   

    public Mono<Article> findArticleById(String id) {
        return articleRepository.findArticleById(id).log("findOneArticle");
    }
    
    public Flux<Article> findAllArticles() {
    	return articleRepository.findAllArticles().log("findAllArticles");
    }
}
