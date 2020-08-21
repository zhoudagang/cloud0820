package com.everyman.cloud.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.everyman.cloud.entity.SysUser;
import com.everyman.cloud.service.ISysUserService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author zhougang
 */
@RestController
@RequestMapping("/sys-user")
public class SysUserController
{

    @Resource
    private ISysUserService sysUserService;

    @GetMapping("/list")
    public IPage<SysUser> list(@RequestParam Integer page, @RequestParam Integer pageCount)
    {
        return sysUserService.selectListByPage(page, pageCount);
    }


}
