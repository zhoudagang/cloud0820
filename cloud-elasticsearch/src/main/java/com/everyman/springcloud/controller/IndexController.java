package com.everyman.springcloud.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zhougang
 * @date 2020/08/26
 */
@RestController
public class IndexController
{

    @GetMapping({"/", "/index"})
    public String index()
    {
        return "index";
    }

}
