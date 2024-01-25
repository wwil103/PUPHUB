package com.example.puphub;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@MapperScan("com.example.puphub.mapper")
public class PuphubApplication {

    public static void main(String[] args) {
        SpringApplication.run(PuphubApplication.class, args);
    }

}
