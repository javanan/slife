package com.slife.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.slife.dao.SysUserRoleDao;
import com.slife.entity.SysUserRole;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by chen on 2017/9/1.
 * <p>
 * Email 122741482@qq.com
 * <p>
 * Describe: 系统用户角色中间表
 */
@Service
@Transactional(readOnly = true)
public class SysUserRoleService extends ServiceImpl<SysUserRoleDao, SysUserRole> {
}
