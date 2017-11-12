package com.slife.service.impl;

import com.slife.base.service.impl.BaseService;
import com.slife.dao.SlifeLogDao;
import com.slife.entity.SlifeLog;
import com.slife.service.ISlifeLogService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author chen
 * @date 2017/9/19
 * <p>
 * Email 122741482@qq.com
 * <p>
 * Describe: slife 日志 service
 */
@Service
@Transactional(readOnly = true, rollbackFor = Exception.class)
public class SlifeLogService extends BaseService<SlifeLogDao, SlifeLog> implements ISlifeLogService {


}
