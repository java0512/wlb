package com.hainet.his;


import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
@MapperScan(basePackages = "com.hainet.his.mapper")
public class HisCmnApp {

    public static void main(String[] args) {
        SpringApplication.run(HisCmnApp.class, args);
    }
}
