package com.everyman.cloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author zhougang
 * @date 2020/08/28
 */
@SpringBootApplication
@EnableDiscoveryClient
public class WechatApplication
{
    public static void main(String[] args)
    {
        SpringApplication.run(WechatApplication.class, args);
    }
}
