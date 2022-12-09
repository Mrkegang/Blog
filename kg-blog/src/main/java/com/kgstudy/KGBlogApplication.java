package com.kgstudy;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author kg
 * @version 1.0
 * @description
 * @date 2022/12/5 20:21
 */
@SpringBootApplication
@MapperScan("com/kgstudy/mapper")
public class KGBlogApplication {
    public static void main(String[] args) {
        SpringApplication.run(KGBlogApplication.class, args);
    }
}
