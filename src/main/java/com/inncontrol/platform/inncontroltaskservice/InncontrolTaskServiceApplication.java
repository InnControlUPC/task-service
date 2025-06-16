package com.inncontrol.platform.inncontroltaskservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class InncontrolTaskServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(InncontrolTaskServiceApplication.class, args);
    }

}
