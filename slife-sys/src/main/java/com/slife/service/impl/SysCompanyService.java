package com.slife.service.impl;

import com.slife.base.service.impl.BaseService;
import com.slife.dao.SysCompanyDao;
import com.slife.entity.SysCompany;
import com.slife.service.ISysCompanyService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author chen
 * @date 2017/7/31
 * <p>
 * Email 122741482@qq.com
 * <p>
 * Describe: 公司 service
 */
@Service
@Transactional(readOnly = true,rollbackFor = Exception.class)
public class SysCompanyService extends BaseService<SysCompanyDao, SysCompany> implements ISysCompanyService {

}
