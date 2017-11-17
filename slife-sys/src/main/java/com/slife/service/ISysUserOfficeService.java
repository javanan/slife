package com.slife.service;

import com.slife.base.service.IBaseService;
import com.slife.base.vo.DataTable;
import com.slife.entity.SysUser;
import com.slife.entity.SysUserOffice;

/**
 *
 * @author chen
 * @date 2017/11/16
 * <p>
 * Email 122741482@qq.com
 * <p>
 * Describe:
 */
public interface ISysUserOfficeService extends IBaseService<SysUserOffice> {


    /**
     * 移除组织中的人
     * @param officeId
     * @param userIds
     */
    void removeUsers(Long officeId, Long[] userIds);

    /**
     * 向组织中添加用户
     * @param officeId
     * @param userIds
     * @param major
     */
    void addUsers(Long officeId, Long[] userIds, String major);

    /**
     * 获取组织的用户列表
     * @param dt
     * @return
     */
    DataTable<SysUser> userList(DataTable dt);
}
