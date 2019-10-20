package com.zuri.circle.manager.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

@EnableAsync
@Configuration
public class AsyncConfig {


	@Bean(name = "mailSmsExecutor")
	public TaskExecutor taskExecutor() {
		ThreadPoolTaskExecutor taskExecutor = new ThreadPoolTaskExecutor();
		taskExecutor.setThreadNamePrefix("Async-");
		taskExecutor.setMaxPoolSize(10);
		taskExecutor.setQueueCapacity(10);
		taskExecutor.afterPropertiesSet();
		return taskExecutor;
	}
	
	

	@Bean(name = "mailExecutor")
	public TaskExecutor taskExecutor1() {
		ThreadPoolTaskExecutor taskExecutor = new ThreadPoolTaskExecutor();
		taskExecutor.setThreadNamePrefix("Async-");
		taskExecutor.setMaxPoolSize(10);
		taskExecutor.setQueueCapacity(10);
		taskExecutor.afterPropertiesSet();
		return taskExecutor;
	}
}

	