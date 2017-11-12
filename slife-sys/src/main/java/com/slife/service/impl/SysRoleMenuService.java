package com.slife.service.impl;

import com.slife.base.service.impl.BaseService;
import com.slife.dao.SysRoleMenuDao;
import com.slife.entity.SysRoleMenu;
import com.slife.service.ISysRoleMenuService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author chen
 * @date 2017/10/26
 * <p>
 * Email 122741482@qq.com
 * <p>
 * Describe:
 */
@Service
@Transactional(readOnly = true, rollbackFor = Exception.class)
public class SysRoleMenuService  extends BaseService<SysRoleMenuDao, SysRoleMenu> implements ISysRoleMenuService {
}
