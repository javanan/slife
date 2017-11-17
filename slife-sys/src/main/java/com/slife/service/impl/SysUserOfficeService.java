package com.slife.service.impl;

import com.baomidou.mybatisplus.mapper.Condition;
import com.baomidou.mybatisplus.toolkit.CollectionUtils;
import com.slife.base.service.impl.BaseService;
import com.slife.base.vo.DataTable;
import com.slife.dao.SysUserOfficeDao;
import com.slife.entity.SysUser;
import com.slife.entity.SysUserOffice;
import com.slife.service.ISysUserOfficeService;
import com.slife.service.ISysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author chen
 * @date 2017/11/16
 * <p>
 * Email 122741482@qq.com
 * <p>
 * Describe:
 */
@Service
@Transactional(readOnly = true, rollbackFor = Exception.class)
public class SysUserOfficeService extends BaseService<SysUserOfficeDao, SysUserOffice> implements ISysUserOfficeService {

    @Autowired
    private ISysUserOfficeService sysUserOfficeService;

    @Autowired
    private ISysUserService sysUserService;

    /**
     * 获取组织的用户列表
     *
     * @param dt
     * @return
     */
    @Override
    public DataTable<SysUser> userList(DataTable dt) {

        List<SysUser> sysUsers = new ArrayList<>();
        DataTable<SysUser> sysUserDataTable=new DataTable<SysUser>();
        DataTable<SysUserOffice> sysOfficeDataTable = sysUserOfficeService.pageSearch(dt);

        if (CollectionUtils.isNotEmpty(sysOfficeDataTable.getRows())) {
            List<Long> userIds = sysOfficeDataTable.getRows().stream().parallel().map(sysUserOffice -> sysUserOffice
                    .getSysUserId())
                    .collect(Collectors.toList());

            sysUsers = sysUserService.selectList(Condition.create().in("id", userIds));
        }

        sysUserDataTable.setRows(sysUsers);
        sysUserDataTable.setTotal(sysOfficeDataTable.getTotal());
        sysUserDataTable.setPageNumber(sysOfficeDataTable.getPageNumber());
        sysUserDataTable.setPageSize(sysOfficeDataTable.getPageSize());

        return sysUserDataTable;
    }


    /**
     * 移除组织中的人
     *
     * @param officeId
     * @param userIds
     */
    @Transactional(readOnly = false, rollbackFor = Exception.class)
    @Override
    public void removeUsers(Long officeId, Long[] userIds) {

        delete(Condition.create().eq("sys_user_id", officeId).in("sys_user_id", userIds));
    }

    /**
     * 向组织中添加用户
     *
     * @param officeId
     * @param userIds
     * @param major
     */
    @Transactional(readOnly = false, rollbackFor = Exception.class)
    @Override
    public void addUsers(Long officeId, Long[] userIds, String major) {
        List<SysUserOffice> sysUserOffices = Arrays.stream(userIds).parallel().map(userId -> {
                    SysUserOffice sys = new SysUserOffice();
                    sys.setMajor(major);
                    sys.setSysUserId(userId);
                    sys.setSysOfficeId(officeId);
                    return sys;
                }
        ).collect(Collectors.toList());

        insertBatch(sysUserOffices);
    }


}
