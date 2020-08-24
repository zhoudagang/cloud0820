package com.everyman.cloud.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.everyman.cloud.entity.SysUser;

/**
 * @author zhougang
 */
public interface ISysUserService extends IService<SysUser>
{

    /**
     * 查询分页数据
     *
     * @param page      页码
     * @param pageCount 每页条数
     * @return IPage<SysUser>
     */
    IPage<SysUser> selectListByPage(Integer page, Integer pageCount);

    /**
     * id查询数据
     *
     * @param id id
     * @return SysUser
     */
    SysUser selectSysUserById(Long id);

    /**
     * 添加
     *
     * @param sysUser
     * @return int
     */
    int insertSysUser(SysUser sysUser);

    /**
     * 删除
     *
     * @param id 主键
     * @return int
     */
    int deleteSysUserById(Long id);

    /**
     * 修改
     *
     * @param sysUser
     * @return int
     */
    int updateSysUser(SysUser sysUser);


}