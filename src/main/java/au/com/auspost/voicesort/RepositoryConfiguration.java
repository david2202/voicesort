package au.com.auspost.voicesort;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableAutoConfiguration
@EntityScan(basePackages = {"au.com.auspost.voicesort.domain"})
@EnableJpaRepositories(basePackages = {"au.com.auspost.voicesort.dao"})
public class RepositoryConfiguration {
}
