package com.everyman.cloud;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author zhougang
 * @date 2020/08/21
 */
@SpringBootApplication
@EnableDiscoveryClient
@MapperScan("com.everyman.cloud.mapper")
@EnableCaching
public class SysadminApplication
{
    public static void main(String[] args)
    {
        SpringApplication.run(SysadminApplication.class, args);
    }
}
