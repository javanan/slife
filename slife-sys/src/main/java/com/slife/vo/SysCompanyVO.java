package com.slife.vo;

import com.slife.base.vo.BaseVO;
import com.slife.entity.SysArea;

/**
 * Created by chen on 2017/7/31.
 * <p>
 * Email 122741482@qq.com
 * <p>
 * Describe: 系统公司vo
 */
public class SysCompanyVO extends BaseVO{

    private static final long serialVersionUID = 5989416787892290738L;
    /**
     * varchar(500) NULL公司地址
     */
    private String address;
    /**
     * varchar(64) NULL区域id
     */
    private SysArea sysArea;
    /**
     * varchar(10) NULL邮政编码
     */
    private String zipCode;
    /**
     * varchar(20) NULL法人
     */
    private String master;
    /**
     * varchar(20) NULL联系电话
     */
    private String phone;
    /**
     * varchar(20) NULL传真
     */
    private String fax;
    /**
     * varchar(100) NULL邮箱
     */
    private String email;
    /**
     * varchar(100) NULL版权信息
     */
    private String appCopyright;
    /**
     * varchar(100) NULL公司名称
     */
    private String name;


}
