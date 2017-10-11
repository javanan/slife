package com.slife.service.impl;

import com.slife.base.service.impl.BaseService;
import com.slife.dao.SysCompanyDao;
import com.slife.entity.SysCompany;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by chen on 2017/7/31.
 * <p>
 * Email 122741482@qq.com
 * <p>
 * Describe: 公司 service
 */
@Service
@Transactional(readOnly = true)
public class SysCompanyService extends BaseService<SysCompanyDao, SysCompany> {


    /**
     * 根据id 查询 公司信息
     *
     * @param id
     * @return
     */
    public SysCompany selectById(Long id) {
        return this.baseMapper.selectById(id);
    }
}
