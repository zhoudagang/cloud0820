package com.everyman.cloud.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

/**
 * 微信小程序 登陆相关接口  仅供测试
 *
 * @author zhougang
 * @date 2020/08/28
 */
@RestController
public class WechatController
{

    @Resource
    private RestTemplate restTemplate;

    /**
     * 根据code获取到openid
     *
     * @param code wx.login 获取的code
     * @return {"session_key":"Ema8uZ4UEMkFQG5SyLF8pA==","openid":"oqPkh5ZB2s6dP8vXXXNd0x7hezMI"}
     */

    @RequestMapping("/user/login")
    private String getWechatLoginInfo(String code, String avatarUrl, String city, String country, String gender, String nickName)
    {
        String requestUrl = "https://api.weixin.qq.com/sns/jscode2session?" +
                "appid=wx8e82433ac2b3b8d5&" +
                "secret=233dca4f678b5dfe24beb38f7c8e731c&" +
                "js_code=" + code + "&" +
                "grant_type=authorization_code";
        System.err.println("code = " + code);
        String info = restTemplate.getForObject(requestUrl, String.class);
        System.err.println("info = " + info);
        return info;
    }

    /**
     * 需要项目启动时 就获取 之后每间隔一个小时获取一次
     * access_token 的有效期目前为 2 个小时，需定时刷新，重复获取将导致上次获取的 access_token 失效；
     *
     * @return {"access_token":"36_DyMDTh9RwaBJrs_poWlweakBGeAZQI40p8lVim1QnOXSDX8HNoN6s0SjtErgsBcVlQdpyBSQ2rLgcDm1fw0D32Q8KlRusmmolirHqp22qXmTcKGvKrdneul8F0XNx23PLd8fy-pGizJZZp8XPOIfABAKJD","expires_in":7200}
     */
    @RequestMapping("/access_token")
    private String getAccessToken()
    {
        String requestUrl = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&" +
                "appid=wx8e82433ac2b3b8d5&" +
                "secret=233dca4f678b5dfe24beb38f7c8e731c";
        String token = restTemplate.getForObject(requestUrl, String.class);
        System.out.println("token = " + token);
        return token;

    }

    @RequestMapping("/sendMsg")
    private String send(String openid)
    {
        String requestUrl = "https://api.weixin.qq.com/c gi-bin/message/wxopen/template/uniform_send?" +
                "access_token=36_DyMDTh9RwaBJrs_poWlweakBGeAZQI40p8lVim1QnOXSDX8HNoN6s0SjtErgsBcVlQdpyBSQ2rLgcDm1fw0D32Q8KlRusmmolirHqp22qXmTcKGvKrdneul8F0XNx23PLd8fy&" +
                "touser=" + openid +"&"+
                "weapp_template_msg=";
        return "";
    }


}
