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

    @PostMapping("/add")
    public int add(@RequestBody SysUser sysUser) {
        return sysUserService.insertSysUser(sysUser);
    }

    @DeleteMapping("/delete/{id}")
    public int delete(@PathVariable("id") Long id) {
        return sysUserService.deleteSysUserById(id);
    }

    @PutMapping("/edit")
    public int edit(@RequestBody SysUser sysUser) {
        return sysUserService.updateSysUser(sysUser);
    }

    @GetMapping("/list/{id}")
    public SysUser selectSysUserById(@PathVariable Long id) {
        return sysUserService.selectSysUserById(id);
    }
}
