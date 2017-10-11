#slife
spring boot 搭建的一个企业级快速开发脚手架。
###技术栈
1. Spring Boot <p>
2. MySQL<p>
3. Freemark <p>
4. SiteMesh <p>
5. Shiro  <p>
6. Boostrapt <p>
7. mybatis、mybatisPlus <p>
8. redis <p>
9. Activiti <p>


#编码约定
系统分为controller、service、dao层。
controller主要负责转发、service主要负责业务逻辑、dao主要是数据库的操作。


###文件名称约定
在页面文件夹中，按照功能模块分别建立不同的文件夹存放页面，如用户的页面就放在user文件夹中，而角色的就放在role文件夹中。
1. 页面如果是列表类型的。页面的文件名用list.ftl命名。
2. 页面如果是详情类型的。页面的文件名用detail.ftl命名。

###controller、service、dao方法名称约定
1. 如果是增加数据操作用insert做前缀。
2. 如果是删除操作用delete做前缀
3. 如果是修改操作用update做前缀
6. 如果是查询操作用select做前缀


#数据库动静分离




#缓存ecache、redis




#新建模块
1. new Module <br>
2. GroupId --->com.slife  <br>
3. ArtifactId---> slife-模块名称   如  slife-activiti     <br>
4. Version --> 版本号   如 1.0SNAPSHOT <br>
5. Module-Name--> slife-模块名称   如  slife-activiti     <br>
6. 提交新建模块  <br>
7. pom 文件引入
```
    <name>slife-模块名称</name>

    <dependencies>
        <dependency>
            <groupId>com.slife</groupId>
            <artifactId>slife-common</artifactId>
        </dependency>

        .
        .
        .其他的依赖
        .
    </dependencies>
```


#JDK版本 1.8
```

    <build>
        <plugins>
            <plugin>
                <groupId>org.apache.maven.plugins</groupId>
                <artifactId>maven-compiler-plugin</artifactId>
                <version>3.6.1</version>
                <configuration>
                    <source>1.8</source>
                    <target>1.8</target>
                    <encoding>UTF-8</encoding>
                    <compilerArgs>
                        <arg>-parameters</arg>
                    </compilerArgs>
                    <useIncrementalCompilation>false</useIncrementalCompilation>
                </configuration>
            </plugin>
        </plugins>
    </build>

```




#新建一个功能模块
1、创建数据库

2、创建entity类

3、创建service类

4、创建controller类

5、创建list界面
```

5.1 到其他list复制代码过


5.2 修改
 <script>
        var url = "${base}/sys/user/";
 </script>

 中的 url 为你刚刚创建的 controller的类
 @Controller
 @RequestMapping(value = "/sys/user")
 public class SysUserController extends BaseController {

 的  @RequestMapping(value = "/sys/user") 值



5.3 修改搜索条件
目前的搜索条件有
    /**
     * 等于
     */
    public static final String SEARCH_EQ="search_eq_";

    /**
     * 左模糊
     */
    public static final String SEARCH_LLIKE="search_llike_";

    /**
     * 右模糊
     */
    public static final String SEARCH_RLIKE="search_rlike_";

    /***
     * 全模糊
     */
    public static final String SEARCH_LIKE="search_like_";



     <input type="text" class="form-filter input-sm _search" name="search_eq_login_name">

     只要在  input中 的 name 加入 search_eq_ 前缀 再加数据库中的字段名称即可



5.4 修改表格的字段名称

```











































