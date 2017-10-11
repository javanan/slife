package com.slife.config.sitemesh;

import org.sitemesh.builder.SiteMeshFilterBuilder;
import org.sitemesh.config.ConfigurableSiteMeshFilter;

/**
 * Created by chen on 2017/7/27.
 * <p>
 * Email 122741482@qq.com
 * <p>
 * Describe: WebSiteMeshFilter
 */
public class WebSiteMeshFilter extends ConfigurableSiteMeshFilter {

    /** 需要装饰的访问路径 */
    //@Value("${sitemesh.contentPath}")
    private String contentPath="/sys/user,/sys/menu,/sys/dict,/sys/role,/sys/,/index,/monitor/*,/404";

    /** 装饰器页面路径 */
    //@Value("${sitemesh.decoratorPath}")
  /*  private String decoratorPath="/assets/layouts/default.jsp";*/
    private String decoratorPath="/layouts";

    /** 不需要装饰的访问路径,多个之间用英文逗号分隔 */
    //@Value("${sitemesh.excludedPaths}")
/*    private String excludedPaths="/js*//**,/css*//**,/img*//**,/fonts*//**,/login,/layouts,/logout,/api,/f1*//**,/webjars*//**," +
            "/v2*//**," +
            "/swagger*//**,/swagger-ui.html,/sys*//**//*insert,/sys*//**//*detail";*/
    private String excludedPaths="";

    @Override
    protected void applyCustomConfiguration(SiteMeshFilterBuilder builder) {
        //程序写死
        //builder.addDecoratorPath("/admin/*", "/admin/index").addExcludedPath("/admin/index").addExcludedPath("/plugin/*");



        // 通过配置文件
        if (contentPath == null) {
            return;
        }
        String[] contentPaths = contentPath.split(",");
        for (String path : contentPaths) {
            builder.addDecoratorPath(path, decoratorPath);
        }

        if (excludedPaths == null) {
            return;
        }
        String[] paths = excludedPaths.split(",");
        for (String path : paths) {
            builder.addExcludedPath(path);
        }
        builder.addTagRuleBundle(new ScriptTagRuleBundle());
    }
}
