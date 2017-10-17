package com.slife.service.impl;


import com.baomidou.mybatisplus.mapper.Condition;
import com.google.common.base.Strings;
import com.slife.base.service.impl.BaseService;
import com.slife.base.vo.DataTable;
import com.slife.dao.SysUserDao;
import com.slife.entity.SysUser;
import com.slife.util.PasswordUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

/**
 * Created by chen on 2017/4/21.
 * <p>
 * Email 122741482@qq.com
 * <p>
 * Describe: 系统用户service
 */
@Service
@Transactional(readOnly = true)
//@CacheConfig(cacheNames = "cache:")
/*public class SysUserService  extends BaseService<SysUserDao, SysUser>*/
public class SysUserService extends BaseService<SysUserDao, SysUser> {


    @Autowired
    private SysRoleService sysRoleService;

    // @Cacheable(cacheNames = "user:", key = "#id")
    public SysUser getById(String id) {
        return this.baseMapper.selectById(id);
    }


    @Transactional(readOnly = false)
    // @CachePut(cacheNames = "user:", key = "#sysUser.id")
    public int addUser(SysUser sysUser) {
        return this.baseMapper.insert(sysUser);
    }


    //@Cacheable(cacheNames = "user:", key = "#username")
    public SysUser login(String username, String password) {
        return new SysUser();
    }

    /**
     * 登录
     *
     * @param username
     * @return
     */
    public SysUser getByLoginName(String username) {
        return this.baseMapper.selectByLoginName(username);
    }

    /**
     * 查询某个用户的所有信息
     *
     * @param id
     * @return
     */
    public SysUser selectUserAllInfoById(Long id) {

        return this.baseMapper.selectUserAllInfoById(id);
    }

    /**
     * 分页查询用户
     *
     * @param searchParams
     * @param dt
     * @return
     */
    public DataTable<SysUser> PageSysUser(Map<String, Object> searchParams, DataTable<SysUser> dt) {

        Condition cnd = Condition.create();

        if (null != searchParams && searchParams.size() > 0) {
            if (!Strings.isNullOrEmpty(searchParams.get("LIKE_loginName").toString())) {
                cnd.like("login_name", searchParams.get("LIKE_loginName").toString());
            }
            if (!Strings.isNullOrEmpty(searchParams.get("LIKE_name").toString())) {
                cnd.like("name", searchParams.get("LIKE_name").toString());
            }
            if (!Strings.isNullOrEmpty(searchParams.get("LIKE_email").toString())) {
                cnd.like("email", searchParams.get("LIKE_email").toString());
            }
            if (!Strings.isNullOrEmpty(searchParams.get("LIKE_no").toString())) {
                cnd.like("no", searchParams.get("LIKE_no").toString());
            }
            if (!Strings.isNullOrEmpty(searchParams.get("LIKE_phone").toString())) {
                cnd.like("phone", searchParams.get("LIKE_phone").toString());
            }
            if (!Strings.isNullOrEmpty(searchParams.get("LIKE_mobile").toString())) {
                cnd.like("mobile", searchParams.get("LIKE_mobile").toString());
            }
            if (!Strings.isNullOrEmpty(searchParams.get("LIKE_remark").toString())) {
                cnd.like("remark", searchParams.get("LIKE_remark").toString());
            }
            if (!Strings.isNullOrEmpty(searchParams.get("EQ_status").toString())) {
                cnd.eq("login_flag", searchParams.get("EQ_status").toString());
            }

        }

        return dt;
    }

    /**
     * 检测登录名是否重复
     *
     * @param loginName
     * @param id
     * @return
     */
    public Boolean checkLoginName(String loginName, Long id) {
        SysUser sysUser = selectOne(Condition.create().eq("login_name", loginName));
        return sysUser == null || !id.equals(0L) && sysUser.getId().equals(id);
    }

    /**
     * 创建一个用户 或者更新一个用户
     *
     * @param sysUser
     * @param ids
     */
    @Transactional(readOnly = false)
    public void insertSysUser(SysUser sysUser, Long[] ids) {

        //保存用户
        sysUser.setPassword(PasswordUtils.entryptPassword(sysUser.getPassword()));
        insert(sysUser);
        //操作角色
        sysRoleService.insertSysRole(sysUser.getId(), ids);

    }

    /**
     * 更新用户
     *
     * @param sysUser
     * @param ids
     */
    @Transactional(readOnly = false)
    public void updateSysUser(SysUser sysUser, Long[] ids) {

        updateById(sysUser);
        //操作角色
        sysRoleService.insertSysRole(sysUser.getId(), ids);

    }


}
