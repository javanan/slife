package com.slife.service.impl;

import com.slife.base.service.impl.BaseService;
import com.slife.dao.BlogContentDao;
import com.slife.entity.BlogContent;
import com.slife.service.IBlogContentService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author chen
 * @date 2017/11/20
 * <p>
 * Email 122741482@qq.com
 * <p>
 * Describe:
 */
@Service
@Transactional(readOnly = true, rollbackFor = Exception.class)
public class BlogContentService extends BaseService<BlogContentDao, BlogContent> implements IBlogContentService {
}
