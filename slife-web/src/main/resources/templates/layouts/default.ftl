<#assign base=rc.contextPath />
<#assign url=rc.requestUri />
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8"/>
    <meta http-equiv="Content-Type" content="text/html;charset=utf-8"/>
    <title>呵呵 | S Life 企业级管理平台 |
        <sitemesh:write property='title'/>
    </title>
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta content="width=device-width, initial-scale=1.0" name="viewport"/>
    <meta name="renderer" content="webkit">
    <meta name="keywords" content="slife">
    <meta name="description" content="slife，....">

    <!--[if lt IE 9]>
    <meta http-equiv="refresh" content="0;ie.html"/>
    <![endif]-->
    <sitemesh:write property='head'/>
    <link rel="shortcut icon" href="${rc.contextPath}/favicon.ico">
    <link href="${rc.contextPath}/css/bootstrap.min.css?v=3.3.6" rel="stylesheet">
    <link href="${rc.contextPath}/css/font-awesome.min.css?v=4.4.0" rel="stylesheet"/>
    <link href="${rc.contextPath}/css/animate.css" rel="stylesheet"/>
    <link href="${rc.contextPath}/css/style.css?v=4.1.0" rel="stylesheet"/>
    <link href="${rc.contextPath}/css/slife.css" rel="stylesheet"/>

    <script src="${rc.contextPath}/js/jquery.min.js?v=2.1.4"></script>
    <script src="${rc.contextPath}/js/bootstrap.min.js?v=3.3.6"></script>

    <script src="${rc.contextPath}/js/plugins/metisMenu/jquery.metisMenu.js"></script>
    <script src="${rc.contextPath}/js/plugins/slimscroll/jquery.slimscroll.min.js"></script>
    <script src="${rc.contextPath}/js/plugins/layer/layer.min.js"></script>

    <script src="${rc.contextPath}/js/hplus.js?v=4.1.0"></script>
    <script type="text/javascript" src="${rc.contextPath}/js/contabs.js"></script>

    <script src="${rc.contextPath}/js/websocket/sockjs.min.js" type="text/javascript"></script>
    <script src="${rc.contextPath}/js/websocket/stomp.min.js" type="text/javascript"></script>
    <script src="${rc.contextPath}/js/websocket/sliescoket.js" type="text/javascript"></script>

<script>
    var slifebase="${rc.contextPath}";
</script>
    <script src="${rc.contextPath}/js/plugins/pace/pace.min.js"></script>
    <script src="${rc.contextPath}/js/slife/slife.js"></script>
</head>

<body class="fixed-sidebar full-height-layout gray-bg" style="overflow: hidden">

<div id="wrapper">

    <!--左侧导航开始-->
    <nav class="navbar-default navbar-static-side" role="navigation">
        <div class="nav-close">
            <i class="fa fa-times-circle"></i>
        </div>
        <div class="sidebar-collapse">
            <ul class="nav" id="side-menu">
                <li class="nav-header">
                    <div class="dropdown profile-element">
							<span>
                                <img alt="image" class="img-circle" style="max-width: 50px" src="${rc.contextPath}${slife.photo}"/>
                            </span>
                        <a data-toggle="dropdown" class="dropdown-toggle" href="#">
                            <span class="clear">
                                <span class="block m-t-xs">
                                    <strong class="font-bold">${slife.name}</strong>
                                </span>

							</span>
                        </a>
                        <ul class="dropdown-menu animated fadeInRight m-t-xs">
                            <li><a class="J_menuItem" href="form_avatar.html">修改头像</a>
                            </li>
                            <li><a class="J_menuItem" href="profile.html">个人资料</a></li>
                            <li><a class="J_menuItem" href="contacts.html">联系我们</a></li>
                            <li><a class="J_menuItem" href="mailbox.html" id="connect">信箱</a></li>
                            <li class="divider"></li>
                            <li><a href="${rc.contextPath}/logout">安全退出</a></li>
                        </ul>
                    </div>
                    <div class="logo-element">Slife</div>
                </li>


            <#if menus??>
            <#else>
                <script>location.href = '${rc.contextPath}/login'</script>
            </#if>


            <#list menus as menu>
                <#if "/index"?contains(menu.href)>
                    <li class="${"${rc.requestUri}"?contains(menu.href)?string('active','')}">
                        <a href="${rc.contextPath}${menu.href}"><i class="${menu.icon}"></i><span
                                class="title nav-label">${menu.name}</span><span class="selected"></span></a>
                    </li>
                <#else>
                    <li class="${"${rc.requestUri}"?contains(menu.href)?string('active open','')}">
                        <a href="javascript:void(0);"><i class="${menu.icon}"></i><span
                                class="title">${menu.name}</span>
                            <#if menu.href?ends_with("${rc.requestUri}")>
                                <span class='selected'></span>
                            </#if>
                            <span class="arrow ${menu.href?ends_with("${rc.requestUri}")?string('open','')}"></span> <span class="fa arrow"></span></a>
                        <ul class='nav nav-second-level'>
                            <#list menu.children as ch >
                                <li class="${"${rc.requestUri}"?contains(ch.href)?string('active','')}">
                                    <a href="${rc.contextPath}${ch.href}"><i class="${ch.icon}"></i>${ch.name}</a>
                                </li>
                            </#list>
                        </ul>
                    </li>
                </#if>
            </#list>

            </ul>
        </div>
    </nav>
    <!--左侧导航结束-->
    <!--右侧部分开始-->
    <div id="page-wrapper" class="gray-bg dashbard-1">
        <div class="row border-bottom">
            <nav class="navbar navbar-static-top" role="navigation"
                 style="margin-bottom: 0">
                <div class="navbar-header">
                    <a class="navbar-minimalize minimalize-styl-2 btn btn-primary "
                       href="#"><i class="fa fa-bars"></i> </a>
                    <form role="search" class="navbar-form-custom" method="post"
                          action="search_results.html">
                        <div class="form-group">
                            <input type="text" placeholder="请输入您需要查找的内容 …"
                                   class="form-control" name="top-search" id="top-search">
                        </div>
                    </form>
                </div>
                <ul class="nav navbar-top-links navbar-right">
                    <li class="dropdown"><a class="dropdown-toggle count-info"
                                            data-toggle="dropdown" href="#"> <i class="fa fa-envelope"></i>
                        <span class="label label-warning">5</span>
                    </a>
                        <ul class="dropdown-menu dropdown-messages">
                            <li class="m-t-xs">
                                <div class="dropdown-messages-box">
                                    <a href="profile.html" class="pull-left"> <img alt="image"
                                                                                   class="img-circle" src="">
                                    </a>
                                    <div class="media-body">
                                        <small class="pull-right">1小时前</small>
                                        <strong>张三</strong>
                                        这个需求半小时内完成 <br>
                                        <small class="text-muted">xxx</small>
                                    </div>
                                </div>
                            </li>
                            <li class="divider"></li>
                            <li>
                                <div class="dropdown-messages-box">
                                    <a href="profile.html" class="pull-left"> <img alt="image"
                                                                                   class="img-circle" src="">
                                    </a>
                                    <div class="media-body ">
                                        <small class="pull-right text-navy">2小时前</small>
                                        <strong>李四</strong>
                                        有客户要见你<br>
                                        <small class="text-muted">昨天</small>
                                    </div>
                                </div>
                            </li>
                            <li class="divider"></li>
                            <li>
                                <div class="text-center link-block">
                                    <a class="J_menuItem" onclick="devPeing()"> <i
                                            class="fa fa-envelope"></i> <strong> 查看所有消息</strong>
                                    </a>
                                </div>
                            </li>
                        </ul>
                    </li>
                    <li class="dropdown"><a class="dropdown-toggle count-info"
                                            data-toggle="dropdown" href="#"> <i class="fa fa-bell"></i> <span
                            class="label label-primary">8</span>
                    </a>
                        <ul class="dropdown-menu dropdown-alerts">
                            <li><a href="">
                                <div>
                                    <i class="fa fa-envelope fa-fw"></i> 您有16条未读消息 <span
                                        class="pull-right text-muted small">4分钟前</span>
                                </div>
                            </a></li>
                            <li class="divider"></li>
                            <li><a href="">
                                <div>
                                    <i class="fa fa-qq fa-fw"></i> 3条新回复 <span
                                        class="pull-right text-muted small">12分钟钱</span>
                                </div>
                            </a></li>
                            <li class="divider"></li>
                            <li>
                                <div class="text-center link-block">
                                    <a class="J_menuItem" onclick="devPeing()"> <strong>查看所有
                                    </strong> <i class="fa fa-angle-right"></i>
                                    </a>
                                </div>
                            </li>
                        </ul>
                    </li>
                    <li class="hidden-xs"><a href="" class="J_menuItem"
                                             data-index="0"><i class="fa fa-qq"></i> 联系我</a></li>
                    <li class="dropdown hidden-xs"><a
                            class="right-sidebar-toggle" aria-expanded="false"> <i
                            class="fa fa-tasks"></i> 主题
                    </a></li>
                    <li class="dropdown hidden-xs">
                        <a href="${rc.contextPath}/logout" class="right-sidebar-toggle" aria-expanded="false"> <i
                                class="fa fa-tasks"></i> 退出</a>
                    </li>
                </ul>
            </nav>
        </div>

        <div class="row content-tabs" style="height: 0!important;">


        </div>

        <div class="row" id="content-main" style="overflow: scroll">
            <sitemesh:write property='body'/>
        </div>


        <div class="footer">
            <div class="pull-right">呵呵 @ 版权</div>
        </div>
    </div>
    <!--右侧部分结束-->
    <!--右侧边栏开始-->
    <div id="right-sidebar">
        <div class="sidebar-container">

            <ul class="nav nav-tabs navs-3">

                <li class="active"><a data-toggle="tab" href="#tab-1"> <i
                        class="fa fa-gear"></i> 主题
                </a></li>
                <li class=""><a data-toggle="tab" href="#tab-2"> 通知 </a></li>
                <li><a data-toggle="tab" href="#tab-3"> 项目进度 </a></li>
            </ul>

            <div class="tab-content">
                <div id="tab-1" class="tab-pane active">
                    <div class="sidebar-title">
                        <h3>
                            <i class="fa fa-comments-o"></i> 主题设置
                        </h3>
                        <small><i class="fa fa-tim"></i>
                            你可以从这里选择和预览主题的布局和样式，这些设置会被保存在本地，下次打开的时候会直接应用这些设置。
                        </small>
                    </div>
                    <div class="skin-setttings">
                        <div class="title">主题设置</div>
                        <div class="setings-item">
                            <span>收起左侧菜单</span>
                            <div class="switch">
                                <div class="onoffswitch">
                                    <input type="checkbox" name="collapsemenu"
                                           class="onoffswitch-checkbox" id="collapsemenu"> <label
                                        class="onoffswitch-label" for="collapsemenu"> <span
                                        class="onoffswitch-inner"></span> <span
                                        class="onoffswitch-switch"></span>
                                </label>
                                </div>
                            </div>
                        </div>
                        <div class="setings-item">
                            <span>固定顶部</span>

                            <div class="switch">
                                <div class="onoffswitch">
                                    <input type="checkbox" name="fixednavbar"
                                           class="onoffswitch-checkbox" id="fixednavbar"> <label
                                        class="onoffswitch-label" for="fixednavbar"> <span
                                        class="onoffswitch-inner"></span> <span
                                        class="onoffswitch-switch"></span>
                                </label>
                                </div>
                            </div>
                        </div>
                        <div class="setings-item">
                            <span> 固定宽度 </span>

                            <div class="switch">
                                <div class="onoffswitch">
                                    <input type="checkbox" name="boxedlayout"
                                           class="onoffswitch-checkbox" id="boxedlayout"> <label
                                        class="onoffswitch-label" for="boxedlayout"> <span
                                        class="onoffswitch-inner"></span> <span
                                        class="onoffswitch-switch"></span>
                                </label>
                                </div>
                            </div>
                        </div>
                        <div class="title">皮肤选择</div>
                        <div class="setings-item default-skin nb">
								<span class="skin-name "> <a href="#" class="s-skin-0">
										默认皮肤 </a>
								</span>
                        </div>
                        <div class="setings-item blue-skin nb">
								<span class="skin-name "> <a href="#" class="s-skin-1">
										蓝色主题 </a>
								</span>
                        </div>
                        <div class="setings-item yellow-skin nb">
								<span class="skin-name "> <a href="#" class="s-skin-3">
										黄色/紫色主题 </a>
								</span>
                        </div>
                    </div>
                </div>
                <div id="tab-2" class="tab-pane">

                    <div class="sidebar-title">
                        <h3>
                            <i class="fa fa-comments-o"></i> 最新通知
                        </h3>
                        <small><i class="fa fa-tim"></i> 您当前有10条未读信息</small>
                    </div>


                </div>
                <div id="tab-3" class="tab-pane">

                    <div class="sidebar-title">
                        <h3>
                            <i class="fa fa-cube"></i> 最新任务
                        </h3>
                        <small><i class="fa fa-tim"></i> 您当前有14个任务，10个已完成</small>
                    </div>
                </div>
            </div>

        </div>
    </div>

</div>


<!--业务js  -->
<script type="text/javascript">
    $(document).ready(function () {
        //	$("#index001").click();
    });
    function devPeing() {
        layer.msg("待开发。。。");
    }
</script>

<script type="text/javascript">
    console.log("===========================");
    console.log(window.location.href);
    if (typeof console == "object") {
        console.info("邦客 app");

    }

</script>

<sitemesh:write property='slife_js'/>

</body>

</html>
