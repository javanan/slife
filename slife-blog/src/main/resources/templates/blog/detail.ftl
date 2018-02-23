<html>
<head>
    <title>博客编辑</title>
    <link href="${rc.contextPath}/css/font-awesome.css?v=4.4.0" rel="stylesheet">
    <link href="${rc.contextPath}/css/plugins/bootstrap-table/bootstrap-table.min.css" rel="stylesheet">
    <link href="${rc.contextPath}/css/animate.css" rel="stylesheet">
    <!--summernote css -->
    <link href="${rc.contextPath}/css/plugins/summernote/summernote-0.8.8.css" rel="stylesheet">
    <script>
        var url = "${url}",  action = "${action}";
    </script>

</head>

<body class="gray-bg">
<!--渲染 -->
<div class="wrapper wrapper-content  animated fadeInRight">
    <div class="row">
        <div class="col-sm-12">
            <div class="ibox float-e-margins">
                <div class="ibox-content">
                    <div class="col-sm-12">
                        <h4>发布文章</h4>
                    </div>
                    <form class="form-horizontal form-bordered" id="slifeForm">
                        <input id="id" name="id" type="hidden">
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

                            <input id="status" name="status" type="hidden">
                        </div>
                        <div class="text-right form-group">
                            <a class="btn btn-default waves-effect waves-light" href="${rc.contextPath}/blog/content">返回列表</a>
                            <button type="button"
                                    class="btn btn-primary waves-effect waves-light" type="submit">保存文章</button>
                            <button type="button"
                                    class="btn btn-warning waves-effect waves-light" onclick="save(0)">存为草稿
                            </button>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>
<!--summernote-->
<script src="${rc.contextPath}/js/plugins/summernote/summernote.js"></script>
<script src="${rc.contextPath}/js/plugins/summernote/summernote-zh-CN.min.js"></script>
<script src="${rc.contextPath}/js/plugins/validate/jquery.validate.min.js"></script>
<script src="${rc.contextPath}/js/plugins/validate/messages_zh.min.js"></script>

<script type="text/javascript">
    $().ready(function() {

        $('.summernote').summernote({
            height : '220px',
            lang : 'zh-CN',
            callbacks: {
                onImageUpload: function(files, editor, $editable) {
                    sendFile(files);
                }
            }
        });
        validateRule();
    });


    function validateRule() {
        var icon = "<i class='fa fa-times-circle'></i> ";
        $("#slifeForm").validate({
            rules : {
                title : "required",
                author : "required",
                content : "required"
            },
            messages : {
                title : "请填写文章标题",
                author : "请填写文章作者",
                content : "请填写文章内容"
            }
        });
    }



    //编辑器新增的ajax上传图片函数
    function sendFile(files, editor, $editable) {
        var size = files[0].size;
        if((size / 1024 / 1024) > 2) {
            alert("图片大小不能超过2M...");
            return false;
        }
        console.log("size="+size);
        var formData = new FormData();
        formData.append("file", files[0]);
        $.ajax({
            data : formData,
            type : "POST",
            url : "/file/upload/blog",    // 图片上传出来的url，返回的是图片上传后的路径，http格式
            cache : false,
            contentType : false,
            processData : false,
            dataType : "json",
            success: function(data) {//data是返回的hash,key之类的值，key是定义的文件名
                console.info(data.message[0].url);
                $('.summernote').summernote('insertImage',data.message[0].url);
            },
            error:function(){
                alert("上传失败");
            }
        });
    }


$.validator.setDefaults({
    submitHandler : function() {
        saveForm(1);
    }
});


    /**
     * 提交表单
     */
    function saveForm() {
        cusFunction(); //回调一个自定义方法，比如修改提交参数。每个form表单都必须定义
        $.ajax({
            cache: true,
            type: "POST",
            url: url+action,
            data: $('#slifeForm').serialize(),// 你的formid
            async: false,
            error: function (XMLHttpRequest, textStatus, errorThrown) {
                parent.layer.alert(XMLHttpRequest.responseJSON.error);
            },
            beforeSend: function () {
                start_request_load();
            }, complete: function () {
                stop_request_load();
            },
            success: function (data) {
                if (data.code == 200) {
                    parent.layer.msg("操作成功");
                    var index = parent.layer.getFrameIndex(window.name); // 获取窗口索引
                    parent.layer.close(index);
                } else {
                    parent.layer.alert(data.error)
                }

            }
        });

    }

    function save(status) {
        $("#status").val(status);
        var content_sn = $("#content_sn").summernote('code');
        $("#content").val(content_sn);
        saveForm();
    }
    
    function cusFunction() {
        
    }

</script>
</body>
</html>