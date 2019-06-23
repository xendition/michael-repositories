package com.michael.demo.spring.security;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 类功能描述:
 * <pre>
 *   启动类
 * </pre>
 *
 * @author Michael
 * @version 1.0
 * @date 2019/06/21 22:35
 */
@Slf4j
@SpringBootApplication
public class SpringSecurityApplication implements CommandLineRunner {

    public static void main(String[] args) {

        SpringApplication.run(SpringSecurityApplication.class);
    }

    @Override
    public void run(String... args) {

        log.info("");
        log.info("$$$$$$$$$$$$$$$$$$$$$$$$$$$$$");
        log.info("$$$$ Lanuch Successfully $$$$");
        log.info("$$$$$$$$$$$$$$$$$$$$$$$$$$$$$");
        log.info("");
    }
}
