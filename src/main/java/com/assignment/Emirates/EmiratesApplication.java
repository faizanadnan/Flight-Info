package com.assignment.Emirates;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;

@SpringBootApplication
@EnableCaching
@AllArgsConstructor
public class EmiratesApplication {
	private final CacheManager cacheManager;

	public static void main(String[] args) {
		SpringApplication.run(EmiratesApplication.class, args);
	}

	@Bean
	public Executor taskExecutor() {
		ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
		executor.setCorePoolSize(20);
		executor.setMaxPoolSize(20);
		executor.setQueueCapacity(50);
		executor.setThreadNamePrefix("flightLookup-");
		executor.initialize();
		return executor;
	}

	@Scheduled(fixedRate = 86400) // cache will be evicted after 24 hours.
	public void evictAllcachesAtIntervals() {
		evictAllCaches();
	}

	public void evictAllCaches() {
		cacheManager.getCacheNames().stream()
				.forEach(cacheName -> cacheManager.getCache(cacheName).clear());
	}

}
