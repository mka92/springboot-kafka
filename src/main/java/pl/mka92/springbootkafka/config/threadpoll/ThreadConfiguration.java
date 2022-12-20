package pl.mka92.springbootkafka.config.threadpoll;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

@Configuration
@RequiredArgsConstructor
public class ThreadConfiguration {
	private final DefaultThreadPoolProperties defaultThreadPoolProperties;

	@Bean
	@DefaultThreadPool
	public ThreadPoolTaskExecutor defaultThreadPool() {
		ThreadPoolTaskExecutor threadPoolTaskExecutor = new ThreadPoolTaskExecutor();
		threadPoolTaskExecutor.setCorePoolSize(defaultThreadPoolProperties.getCorePoolSize());
		threadPoolTaskExecutor.setMaxPoolSize(defaultThreadPoolProperties.getMaxPoolSize());
		threadPoolTaskExecutor.setThreadNamePrefix(defaultThreadPoolProperties.getThreadNamePrefix());
		threadPoolTaskExecutor.afterPropertiesSet();
		return threadPoolTaskExecutor;
	}
}
