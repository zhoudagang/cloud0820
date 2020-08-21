package com.everyman.cloud.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.everyman.cloud.entity.SysUser;
import com.everyman.cloud.mapper.SysUserMapper;
import com.everyman.cloud.service.ISysUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author zhougang
 */
@Service
@Slf4j
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements ISysUserService
{

    @Override
    public IPage<SysUser> selectListByPage(Integer page, Integer pageCount)
    {
        IPage<SysUser> wherePage = new Page<>(page, pageCount);
        SysUser where = new SysUser();

        return baseMapper.selectPage(wherePage, Wrappers.query(where));
    }
}
