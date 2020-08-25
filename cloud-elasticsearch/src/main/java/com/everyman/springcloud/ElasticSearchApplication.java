package com.everyman.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author zhougang
 * @date 2020/08/25
 */
@SpringBootApplication
@EnableDiscoveryClient
public class ElasticSearchApplication
{
    public static void main(String[] args)
    {
        SpringApplication.run(ElasticSearchApplication.class, args);
    }
}
