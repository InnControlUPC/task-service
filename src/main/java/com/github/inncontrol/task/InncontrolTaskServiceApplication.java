package com.github.inncontrol.task;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication(scanBasePackages={"com.github.inncontrol.task", "com.github.inncontrol.shared"})
@EnableJpaAuditing
@EnableFeignClients(basePackages = "com.github.inncontrol.shared.infrastructure.feign")
public class InncontrolTaskServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(InncontrolTaskServiceApplication.class, args);
	}

}
