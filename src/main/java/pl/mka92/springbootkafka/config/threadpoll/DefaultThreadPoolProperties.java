package pl.mka92.springbootkafka.config.threadpoll;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Setter
@Getter
@Configuration
@ConfigurationProperties(prefix = "thread-pool-configuration.default-thread-pool")
public class DefaultThreadPoolProperties {
    private int corePoolSize;
    private int maxPoolSize;
    private String threadNamePrefix;
}
