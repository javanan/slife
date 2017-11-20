<html>
<head>
    <title>博客编辑</title>

    <link href="${base}/css/bootstrap.min.css?v=3.3.6" rel="stylesheet">
    <link href="${base}/css/font-awesome.min.css?v=4.4.0" rel="stylesheet">
    <link href="${base}/css/animate.css" rel="stylesheet">
    <link href="${base}/css/style.css?v=4.1.0" rel="stylesheet">
    <link href="${base}/css/slife.css" rel="stylesheet">


    <link rel="stylesheet" type="text/css" href="${base}/css/plugins/jsTree/style.min.css"/>
    <!-- 全局js -->
    <script src="${base}/js/jquery.min.js?v=2.1.4"></script>
    <script src="${base}/js/bootstrap.min.js?v=3.3.6"></script>
    <script src="${base}/js/plugins/validate/jquery.validate.min.js"></script>
    <script src="${base}/js/plugins/validate/messages_zh.min.js"></script>
    <script src="${base}/js/plugins/layer/layer.min.js"></script>
    <script src="${base}/js/jquery.form.js"></script>


    <script>
        var url = "${url}", action = "${action}";

    </script>
    <script src="${base}/js/slife/slife.js"></script>
    <script src="${base}/js/slife/slifeform.js"></script>
</head>

<body class="gray-bg">

<div class="wrapper wrapper-content  animated fadeInRight">
    <div class="row">
        <div class="col-sm-12">
            <div class="ibox float-e-margins">
                <div class="ibox-content">
                    <div class="col-sm-12">
                        <h4>发布文章</h4>
                    </div>
                    <form class="form-horizontal m-t" id="signupForm">
                        <input id="cid" name="cid" type="hidden">
                        <div class="form-group">
                            <label class="col-sm-1 control-label">标题：</label>
                            <div class="col-sm-4">
                                <input id="title" name="title" class="form-control" type="text">
                            </div>
                            <label class="col-sm-1 control-label">作者：</label>
                            <div class="col-sm-4">
                                <input id="author" name="author" class="form-control"
                                       type="text">
                            </div>
                        </div>
                        <div class="form-group">
                            <input id="content" name="content" type="hidden"> <label
                                class="col-sm-1 control-label">内容：</label>
                            <div class="col-sm-11">
                                <div class="ibox-content no-padding">
                                    <div id="content_sn" class="summernote"></div>
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-1 control-label">类型：</label>
                            <div class="col-sm-11">
                                <input id="categories" name="categories" class="form-control" type="text">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-2 control-label">开启评论：</label>
                            <div class="switch onoffswitch col-sm-1">
                                <div class="onoffswitch">
                                    <input id="allowComment" name="allowComment" checked=""
                                           type="checkbox" value="1" class="onoffswitch-checkbox"
                                           id="example1"> <label class="onoffswitch-label"
                                                                 for="example1"> <span class="onoffswitch-inner"></span>
                                    <span class="onoffswitch-switch"></span>
                                </label>
                                </div>
                            </div>

                            <label class="col-sm-2 control-label">允许订阅：</label>
                            <div class="switch onoffswitch col-sm-2">
                                <div class="onoffswitch">
                                    <input id="allowFeed" name="allowFeed" type="checkbox"
                                           value="1" class="onoffswitch-checkbox" id="example3">
                                    <label class="onoffswitch-label" for="example3"> <span
                                            class="onoffswitch-inner"></span> <span
                                            class="onoffswitch-switch"></span>
                                    </label>
                                </div>
                            </div>
                            <input id="status" name="status" type="hidden">
                        </div>
                        <div class="text-right form-group">
                            <a class="btn btn-default waves-effect waves-light"
                               onclick="returnList()">返回列表</a>
                            <button type="button"
                                    class="btn btn-primary waves-effect waves-light" type="submit">保存文章
                            </button>
                            <button type="button"
                                    class="btn btn-warning waves-effect waves-light"
                                    onclick="save(0)">存为草稿
                            </button>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>


<script type="text/javascript">




</script>
</body>
</html>