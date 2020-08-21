package com.everyman.cloud.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zhougang
 * @date 2020/08/21
 */
@RefreshScope
@RestController
public class IndexController
{
    @Value("${config.info}")
    private String configInfo;

    @GetMapping("/configInfo")
    public Object getConfigInfo()
    {
        return configInfo;
    }
}
