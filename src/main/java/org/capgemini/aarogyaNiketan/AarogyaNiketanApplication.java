package org.capgemini.aarogyaNiketan;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.envers.repository.support.EnversRevisionRepositoryFactoryBean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import springfox.documentation.swagger2.annotations.EnableSwagger2;


@EnableAutoConfiguration
@SpringBootApplication
@EnableSwagger2
@EnableJpaRepositories(repositoryFactoryBeanClass = EnversRevisionRepositoryFactoryBean.class)
public class AarogyaNiketanApplication {
	public static void main(String[] args) {
		SpringApplication.run(AarogyaNiketanApplication.class, args);
	}
}
