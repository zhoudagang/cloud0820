package com.everyman.cloud.pojo;

import lombok.Data;

import java.util.Map;

/**
 * @author zhougang
 * @date 2020/08/28
 */
@Data
public class WxMssVO
{
    //用户openid
    private String touser;
    //模版id
    private String template_id;

    //默认跳到小程序首页
    private String page = "index";

    //收集到的用户formid
    private String form_id;
    //放大那个推送字段
    private String emphasis_keyword = "thing3.DATA";
    //推送文字
    private Map<String, TemplateDataVO> data;
}
