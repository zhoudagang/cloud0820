package com.everyman.cloud;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author zhougang
 * @date 2020/08/21
 */
@SpringBootApplication
@EnableDiscoveryClient
@MapperScan("com.everyman.cloud.mapper")
public class SysadminApplication
{
    public static void main(String[] args)
    {
        SpringApplication.run(SysadminApplication.class, args);
    }
}
