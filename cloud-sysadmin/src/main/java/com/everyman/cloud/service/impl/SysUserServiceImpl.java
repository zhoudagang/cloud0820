package com.everyman.cloud.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.everyman.cloud.entity.SysUser;
import com.everyman.cloud.mapper.SysUserMapper;
import com.everyman.cloud.service.ISysUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;

/**
 * @author zhougang
 */
@Service
@Slf4j
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements ISysUserService
{

    @Override
    @Cacheable(value = "userCache")
    public IPage<SysUser> selectListByPage(Integer page, Integer pageCount)
    {
        IPage<SysUser> wherePage = new Page<>(page, pageCount);
        SysUser where = new SysUser();

        return baseMapper.selectPage(wherePage, Wrappers.query(where));
    }

    @Override
    @Cacheable(value = "userCache", key = "#id")
    public SysUser selectSysUserById(Long id)
    {
        return baseMapper.selectById(id);
    }

    @Override
    @CacheEvict(value = "userCache", allEntries = true, beforeInvocation = true)
    public int insertSysUser(SysUser sysUser)
    {
        return baseMapper.insert(sysUser);
    }

    @Override
    @CacheEvict(value = "userCache", allEntries = true, beforeInvocation = true)
    @Caching(evict = {
            @CacheEvict(value = "userCache", allEntries = true, beforeInvocation = true),
            @CacheEvict(value = "user", key = "#id", allEntries = true, beforeInvocation = true)})
    public int deleteSysUserById(Long id)
    {
        return baseMapper.deleteById(id);
    }

    @Override
    @Caching(evict = {
            @CacheEvict(value = "userCache", allEntries = true, beforeInvocation = true),
            @CacheEvict(value = "userCache", key = "#sysUser.id", allEntries = true, beforeInvocation = true)})
    public int updateSysUser(SysUser sysUser)
    {
        return baseMapper.updateById(sysUser);
    }


}
