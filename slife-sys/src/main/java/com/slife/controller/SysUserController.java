package com.slife.controller;

import com.alibaba.fastjson.JSON;
import com.slife.annotation.SLog;
import com.slife.base.controller.BaseController;
import com.slife.base.entity.ReturnDTO;
import com.slife.base.vo.DataTable;
import com.slife.entity.SysUser;
import com.slife.enums.HttpCodeEnum;
import com.slife.exception.SlifeException;
import com.slife.service.ISysRoleService;
import com.slife.service.ISysUserService;
import com.slife.shiro.SlifeSysUser;
import com.slife.util.FileUtils;
import com.slife.util.PasswordUtils;
import com.slife.util.ReturnDTOUtil;
import com.slife.util.StringUtils;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.aop.support.AopUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by chen on 2017/8/1.
 * <p>
 * Email 122741482@qq.com
 * <p>
 * Describe:  系统用户
 */
@Controller
@RequestMapping(value = "/sys/user")
public class SysUserController extends BaseController {

    @Autowired
    private ISysUserService sysUserService;
    @Autowired
    private ISysRoleService sysRoleService;


    /**
     * 导出 exccel
     * * @param dt
     *
     * @throws Exception
     */
    @GetMapping(value = "/exportUserList")
    @ResponseBody
    public void exportCollectCustomerList(HttpServletResponse response) throws Exception {


        List<SysUser> users = sysUserService.selectList(null);

        //导出数据
        String excelTitle = "用户列表";
        String[] headerTitle = new String[]{"id", "登陆名", "姓名", "邮箱", "工号", "电话", "手机", "描述", "状态"};
        List<String[]> arrayList = new ArrayList<>();
        arrayList.add(headerTitle); //列头
        if (null != users && users.size() > 0) {
            for (SysUser sysUser : users) {
                arrayList.add(
                        new String[]{
                                sysUser.getId().toString(),
                                sysUser.getLoginName(),
                                sysUser.getName(),
                                sysUser.getEmail(),
                                sysUser.getNo(),
                                sysUser.getPhone(),
                                sysUser.getMobile(),
                                sysUser.getRemark(),
                                "Y".equals(sysUser.getLoginFlag()) ? "正常" : "禁用"
                        });
            }
        }
        ExceptInfo(response, excelTitle, arrayList);
    }


    @ApiOperation(value = "删除头像", notes = "删除头像")
    @PostMapping(value = "/delete/photo")
    public ReturnDTO deletePhoto(@RequestParam("name") String name) {

        String defaluePhoto = "/img/log9.png";
        if (defaluePhoto.equals(name)) {
            logger.info("默认头像不可删除！");
            return ReturnDTOUtil.custom(HttpCodeEnum.DELETE_DEFAULT_PHOTO_ERR);
        }
        Long userId = SlifeSysUser.id();
        SysUser sysUser = sysUserService.selectById(userId);
        if (ObjectUtils.isEmpty(sysUser)) {
            return ReturnDTOUtil.notFound();
        }
        sysUser.setPhoto(defaluePhoto);
        sysUserService.updateById(sysUser);

        FileUtils.deleteFile(name);//删除文件
        return ReturnDTOUtil.success();
    }

    @RequiresPermissions("sys:user:list")
    @ApiOperation(value = "进入用户列表界面", notes = "进入用户列表界面")
    @GetMapping(value = "")
    public String list(Model model, HttpServletRequest request) {
        model.addAttribute("url", request.getContextPath() + "/sys/user/");
        return "user/list";
    }

    /**
     * 对用户分页显示
     *
     * @param dt
     * @param request
     * @return
     */
    @SLog("获取用户列表数据")
    @ApiOperation(value = "获取用户列表数据", notes = "获取用户列表:使用约定的DataTable")
    @PostMapping(value = "/list")
    @ResponseBody
    public DataTable<SysUser> list(@RequestBody DataTable dt, ServletRequest request) {
        return sysUserService.pageSearch(dt);
    }





    /**
     * 查看用户详情界面
     *
     * @param id
     * @param model
     * @return
     */
    @GetMapping(value = "/detail/{id}")
    public String detail(@PathVariable("id") Long id, Model model, HttpServletRequest request) {
        model.addAttribute("action", "detail");
        SysUser sysUser = sysUserService.selectUserAllInfoById(id);
        model.addAttribute("sysUser", sysUser);
        model.addAttribute("roles", sysUser.getSysRoles());
        model.addAttribute("url", request.getContextPath() + "/sys/user/");
        return "user/detail";
    }


    /**
     * 进入创建用户界面
     *
     * @param model
     * @return
     */
    @GetMapping(value = "/insert")
    public String create(Model model, HttpServletRequest request) {
        model.addAttribute("action", "insert");
        SysUser sysUser = new SysUser();
        sysUser.setId(0L);
        model.addAttribute("sysUser", sysUser);
        model.addAttribute("roles", sysRoleService.ListSysRoleUseable());
        model.addAttribute("url", request.getContextPath() + "/sys/user/");
        return "user/detail";
    }

    /**
     * 更新用户界面
     *
     * @param id
     * @return
     */
    @GetMapping(value = "update/{id}")
    public String update(@PathVariable("id") Long id, Model model,HttpServletRequest request) {
        model.addAttribute("action", "update");
        model.addAttribute("url", request.getContextPath() + "/sys/user/");
        SysUser sysUser = sysUserService.selectUserAllInfoById(id);
        sysUser.setPassword(null);
        logger.info(JSON.toJSONString(sysUser));
        model.addAttribute("sysUser", sysUser);
        model.addAttribute("roles", sysRoleService.ListSysRoleUseable());
        return "user/detail";
    }

    @ApiOperation(value = "创建用户操作", notes = "创建用户操作")
    @PostMapping(value = "/insert")
    @ResponseBody
    public ReturnDTO create(@Valid SysUser sysUser, @RequestParam(value = "ids", defaultValue = "") Long[] roleIds) {
        sysUser.setId(null);
        sysUserService.insertSysUser(sysUser, roleIds);

        return ReturnDTOUtil.success();
    }


    @ApiOperation(value = "更新用户", notes = "更新用户")
    @PostMapping(value = "/update")
    @ResponseBody
    public ReturnDTO update(@Valid SysUser sysUser, @RequestParam(value = "ids", defaultValue = "") Long[] roleIds) {

        SysUser sysUserDb = sysUserService.selectById(sysUser.getId());
        if (ObjectUtils.isEmpty(sysUserDb)) {
            throw new SlifeException(HttpCodeEnum.NOT_FOUND);
        }

        sysUser.setId(sysUserDb.getId());
        sysUser.setCreateDate(sysUserDb.getCreateDate());
        sysUser.setCreateId(sysUserDb.getCreateId());
        if (StringUtils.isNotBlank(sysUser.getPassword())) {
            sysUser.setPassword(PasswordUtils.entryptPassword(sysUser.getPassword()));
        } else {
            sysUser.setPassword(sysUserDb.getPassword());
        }
        sysUserService.updateSysUser(sysUser, roleIds);
        return ReturnDTOUtil.success();
    }

    /**
     * 检查用户名是否存在
     *
     * @param loginName
     * @return
     */
    @GetMapping(value = "check/{id}")
    @ResponseBody
    public Boolean check(@PathVariable("id") Long id, @RequestParam("loginName") String loginName) {
        return sysUserService.checkLoginName(loginName, id);
    }


    /**
     * 查询系统用户 侧边栏菜单
     */
    @GetMapping(value = "/userinfoall")
    @ResponseBody
    public ReturnDTO selectUserSideMenu() {

        return ReturnDTOUtil.success(sysUserService.selectUserAllInfoById(SlifeSysUser.id()));
    }

    @SLog("批量删除用户")
    @ApiOperation(value = "批量删除用户", notes = "批量删除用户")
    @PostMapping(value = "/delete")
    @ResponseBody
    public ReturnDTO delete(@RequestParam("ids") List<Long> ids, ServletRequest request) {
        boolean success = sysUserService.deleteBatchIds(ids);
        if (success) {
            return ReturnDTOUtil.success();
        }
        return ReturnDTOUtil.fail();

    }


}
