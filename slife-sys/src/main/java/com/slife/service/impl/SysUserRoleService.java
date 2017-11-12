package com.slife.service.impl;

import com.slife.base.service.impl.BaseService;
import com.slife.dao.SysUserRoleDao;
import com.slife.entity.SysUserRole;
import com.slife.service.ISysUserRoleService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author chen
 * @date 2017/9/1
 * <p>
 * Email 122741482@qq.com
 * <p>
 * Describe: 系统用户角色中间表
 */
@Service
@Transactional(readOnly = true, rollbackFor = Exception.class)
public class SysUserRoleService extends BaseService<SysUserRoleDao, SysUserRole> implements ISysUserRoleService {
}
