package com.tianyalan.reactiveredis.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.tianyalan.reactiveredis.domain.Article;
import com.tianyalan.reactiveredis.service.ArticleService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
public class ArticleController {
	private final ArticleService articleService;

	public ArticleController(ArticleService articleService) {
		this.articleService = articleService;
	}

	@PostMapping("/article")
	public Mono<ResponseEntity<?>> create(@RequestBody Article article) {
		return articleService.save(article).map(resource -> {
			try {
				return ResponseEntity.ok().body(resource.booleanValue());
			} catch (Exception e) {
				return ResponseEntity.badRequest().body("Couldn't save article " + " => " + e.getMessage());
			}
		});
	}

	@PutMapping("/article")
	public Mono<ResponseEntity<?>> update(@RequestBody Article article) {
		return articleService.save(article).map(resource -> {
			try {
				return ResponseEntity.ok().body(resource.booleanValue());
			} catch (Exception e) {
				return ResponseEntity.badRequest().body("Couldn't update article " + " => " + e.getMessage());
			}
		});
	}

	@GetMapping("/article")
	public ResponseEntity<Flux<Article>> findAllActicle() {
		return ResponseEntity.ok().body(articleService.findAllArticles());
	}

	@GetMapping("/article/{id}")
	public Mono<ResponseEntity<Article>> findArticleById(@PathVariable String id) {
		return articleService.findArticleById(id).map(ResponseEntity::ok)
				.defaultIfEmpty(ResponseEntity.status(404).body(null));
	}

	@DeleteMapping("/article/{id}")
	public Mono<ResponseEntity<?>> deleteById(@PathVariable String id) {
		return articleService.delete(id).map(resource -> {
			try {
				return ResponseEntity.ok().body(resource.booleanValue());
			} catch (Exception e) {
				return ResponseEntity.badRequest().body("Couldn't delete article " + " => " + e.getMessage());
			}
		});
	}
}
