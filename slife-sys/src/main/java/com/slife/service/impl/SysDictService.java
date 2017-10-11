package com.slife.service.impl;

import com.baomidou.mybatisplus.enums.SqlLike;
import com.baomidou.mybatisplus.mapper.Condition;
import com.google.common.base.Strings;
import com.google.common.collect.Lists;
import com.slife.base.service.impl.BaseService;
import com.slife.base.vo.JsTree;
import com.slife.base.vo.PCAjaxVO;
import com.slife.dao.SysDictDao;
import com.slife.entity.SysDict;
import com.slife.service.ISysDictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by chen on 2017/8/9.
 * <p>
 * Email 122741482@qq.com
 * <p>
 * Describe:
 */
@Service
@Transactional(readOnly = true)
public class SysDictService extends BaseService<SysDictDao, SysDict> implements ISysDictService {





    /**
     * 更新节点
     *
     * @param id
     * @param dicKey
     * @param dicValue
     * @param type
     * @param desc
     * @param sort
     * @param invalid
     */
    @Override
    @Transactional(readOnly = false)
    public boolean update(Long id, String dicKey, String dicValue, String type, String desc, String sort, String invalid) {
        SysDict sysDict = selectById(id);
        if (null == sysDict) {
            return false;
        }
        sysDict.setJkey(dicKey);
        sysDict.setJvalue(dicValue);
        if (!Strings.isNullOrEmpty(sort)) {
            sysDict.setSort(Integer.parseInt(sort));
        }
        if (!Strings.isNullOrEmpty(type)) {
            sysDict.setType(type);
        }
        sysDict.setRemark(desc);
        sysDict.setInvalid(invalid);
        this.baseMapper.updateById(sysDict);
        return true;
    }

    @Override
    public List<JsTree> getDictTree() {
        logger.info("查找字段树");
        List<SysDict> sysDicts = this.baseMapper.selectList(Condition.create().orderBy("sort", true));
        List<JsTree> jts = Lists.newArrayList();
        for (SysDict sysDict : sysDicts) {
            JsTree jt = new JsTree();
            jt.setId(sysDict.getId().toString());
            jt.setParent(sysDict.getParentId().compareTo(0L) > 0 ? sysDict.getParentId().toString() : "#");
            jt.setText(sysDict.getJvalue());
            if (sysDict.getType().equals("C")) {
                jt.setIcon("fa fa-home");
            } else {
                jt.setIcon("glyphicon glyphicon-tint");
            }
            jts.add(jt);
        }
        return jts;

    }

    /**
     * @param dicKey
     * @param dicValue
     * @param dicPid
     * @param type
     * @param desc
     * @param sort
     */
    @Override
    @Transactional(readOnly = false)
    public void insert(String dicKey, String dicValue, Long dicPid, String type, String desc, String
            sort, String invalid, String path) {
        SysDict sysDict = new SysDict();

        if (null != dicPid) {
            sysDict.setParentId(dicPid);
        } else {
            sysDict.setParentId(0L);
        }
        sysDict.setJkey(dicKey);
        sysDict.setJvalue(dicValue);
        if (!Strings.isNullOrEmpty(sort)) {
            sysDict.setSort(Integer.parseInt(sort));
        }
        if (!Strings.isNullOrEmpty(type)) {
            sysDict.setType(type);
        }
        sysDict.setRemark(desc);
        sysDict.setInvalid(invalid);
        this.baseMapper.insert(sysDict);

        if (sysDict.getParentId().equals(0L)) {
            sysDict.setPath(sysDict.getId() + ".");
        } else {

           /* SysDict supSysDict = sysDictDao.selectById(sysDict.getParentId());
            sysDict.setPath(supSysDict.getPath() + sysDict.getId() + ".");*/
            sysDict.setPath(path + sysDict.getId() + ".");
        }

        this.baseMapper.updateById(sysDict);

    }



    /**
     * 根据id 查询
     *
     * @param id
     * @return
     */
    @Override
    public SysDict selectById(Long id) {
        return this.baseMapper.selectById(id);
    }

    /**
     * 删除节点和子节点
     *
     * @param id
     * @return
     */
    @Override
    @Transactional(readOnly = false)
    public PCAjaxVO delete(Long id) {
        PCAjaxVO status = new PCAjaxVO(true);
        //是否为类，以及类下是否有引用
        SysDict sysDict = this.baseMapper.selectById(id);

        if (sysDict != null) {
            //删除
            this.baseMapper.delete(Condition.create().like("path", sysDict.getPath(), SqlLike.RIGHT));

        } else {
            status.setSuccess(false);
            status.setMessage("该数据不存在");
        }
        status.setMessage("删除成功");
        return status;
    }

}
