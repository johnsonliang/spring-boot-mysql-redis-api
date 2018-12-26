package com.test.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.embedded.EmbeddedServletContainerFactory;
import org.springframework.boot.context.embedded.jetty.JettyEmbeddedServletContainerFactory;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableCaching
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	/**
	 * 支持jetty容器
	 * @return
	 */
	@Bean
	public EmbeddedServletContainerFactory servletContainer() {
		JettyEmbeddedServletContainerFactory factory = new JettyEmbeddedServletContainerFactory();
		return factory;
	}
}
