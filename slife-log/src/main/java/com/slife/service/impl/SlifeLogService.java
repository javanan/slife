package com.slife.service.impl;

import com.baomidou.mybatisplus.mapper.Condition;
import com.slife.base.service.impl.BaseService;
import com.slife.base.vo.DataTable;
import com.slife.dao.SlifeLogDao;
import com.slife.entity.SlifeLog;
import com.slife.service.ISlifeLogService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by chen on 2017/9/19.
 * <p>
 * Email 122741482@qq.com
 * <p>
 * Describe: slife 日志 service
 */
@Service
@Transactional(readOnly = true)
public class SlifeLogService extends BaseService<SlifeLogDao, SlifeLog> implements ISlifeLogService {


}
