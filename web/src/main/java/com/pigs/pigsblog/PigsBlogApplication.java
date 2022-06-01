package com.pigs.pigsblog;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * 启动类
 */
@SpringBootApplication()
@EnableSwagger2
@MapperScan("com.pigs.pigsblog.mapper")
public class PigsBlogApplication {

    public static void main(String[] args) {
        SpringApplication.run(PigsBlogApplication.class, args);
        System.out.println(" 高山仰止,景行行止.虽不能至,心向往之...");
    }

}
