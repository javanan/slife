<html>
<head>
    <title>系统用户编辑</title>

    <link href="${base}/css/bootstrap.min.css?v=3.3.6" rel="stylesheet">
    <link href="${base}/css/font-awesome.min.css?v=4.4.0" rel="stylesheet">
    <link href="${base}/css/animate.css" rel="stylesheet">
    <link href="${base}/css/style.css?v=4.1.0" rel="stylesheet">
    <link href="${base}/css/slife.css" rel="stylesheet">
    <link href="${base}/css/plugins/select2/select2.css" rel="stylesheet">

    <!-- 全局js -->
    <script src="${base}/js/jquery.min.js?v=2.1.4"></script>
    <script src="${base}/js/bootstrap.min.js?v=3.3.6"></script>
    <script src="${base}/js/plugins/validate/jquery.validate.min.js"></script>
    <script src="${base}/js/plugins/validate/messages_zh.min.js"></script>

    <script src="${base}/js/jquery.form.js"></script>

  <script src="${base}/js/plugins/select2/select2.min.js"></script>


</head>

<body class="gray-bg">
<div class="wrapper wrapper-content animated fadeInRight">
    <div class="row">
        <div class="col-sm-12">
            <div class="ibox float-e-margins">
                <div class="ibox-content">

                    <form action="${base}/sys/user/${action}" class="form-horizontal form-bordered" method="POST"
                          id="slifeForm" enctype="multipart/form-data">
                        <input type="hidden" name="id" value="${sysUser.id}"/>
                        <input type="hidden" name="salt" value="${sysUser.salt}"/>

                        <div class="form-group">
                            <label class="col-sm-3 control-label">头像</label>
                            <div class="col-sm-2">
                                <div id="localImag" style="margin-left:15px;">
                                    <div class="img_box" id="imgBox_${image.id}">
                                        <a class="fancybox" rel="img" <#if sysUser.photo?if_exists > href="${base}${sysUser.photo}"
                                           <#else>href="${base}/img/log9.png" </#if>>
                                            <img style="width: 60px" src="${sysUser.photo}" onerror="this.src='${base}/img/log9.png'" class="img_file img-rounded"/></a>
                                        <div class="img_edit_box">
                                            <a class="img_desr" href="javascript:doDeleteImg()">删除</a>
                                        </div>
                                    </div>
                                </div>

                            </div>

                            <div class="col-sm-3">
                                <span class="btn green btn-file fileinput-button input-group-btn" style="width: 180px;">
                                	<span>上传头像</span>
                                    <input id="files" type="file" name="files" onchange="javascript:setImagePreview('files' ,'showIcon' ,'localImag' ,'74px' ,'74px' ,'74px' ,'74px')"/>
                                		<input id="photo" class="form-control" type="hidden" name="photo" value="${sysUser.photo}"/>
                               	 	</span>
                                <label class="col-sm-2 input-group" style="width:400px;" id="imgType"></label>
                                </span>
                            </div>
                        </div>


                        <div class="form-group">
                            <label class="col-sm-3 control-label">登录名<span class="required">*</span></label>
                            <div class="col-sm-8">
                                <input type="text" class="form-control" name="loginName" placeholder="请输入登录名"
                                       value="${sysUser.loginName}" required aria-required="true" />
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-sm-3 control-label">用户名<span class="required">*</span></label>
                            <div class="col-sm-8">
                                <input type="text" class="form-control" name="name" placeholder="请输入用户名"
                                       value="${sysUser.name}"/>
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-sm-3 control-label">电子邮件<span class="required">*</span></label>

                            <div class="col-sm-8">
                                <input name="email" type="email" class="form-control" value="${sysUser.email}"
                                       placeholder="电子邮件地址">
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-sm-3 control-label">密码<#if action =='insert'><span
                                    class="required">*</span></#if></label>

                            <div class="col-sm-8">
                                <div class="input-icon right">
                                    <input name="password" type="password" class="form-control"
                                           placeholder="请输入登录密码">
                                </div>
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-sm-3 control-label">工号</label>

                            <div class="col-sm-8">
                                <input name="no" type="text" class="form-control" value="${sysUser.no}"
                                       placeholder="工号">
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-sm-3 control-label">电话</label>

                            <div class="col-sm-3">
                                <div class="input-group">
                                    <input name="phone" type="text" class="form-control" value="${sysUser.phone}"
                                           placeholder="电话">
                                </div>
                            </div>

                            <label class="col-sm-2 control-label">手机</label>

                            <div class="col-sm-3">
                                <input name="mobile" type="text" class="form-control" value="${sysUser.mobile}"
                                       placeholder="手机">
                            </div>
                        </div>


                        <div class="form-group">
                            <label class="col-sm-3 control-label">备注</label>

                            <div class="col-sm-8">
                                <input name="remark" type="text" class="form-control" value="${sysUser.remark}"
                                       placeholder="备注">
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-sm-3 control-label">状态</label>

                            <div class="col-sm-8">
                                <select name="status" class="form-control">
                                    <option value="Y">启用</option>
                                    <option value="N">禁用</option>
                                </select>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-3 control-label">角色</label>

                            <div class="col-sm-8">
                                <select name="ids" multiple="multiple" class="select" style="width: 100%;">
                                <#list roles as r>
                                    <option value="${r.id}">${r.name}</option>
                                </#list>
                                </select>
                            </div>
                        </div>

                    <#if action !='detail'>
                        <div class="form-actions fluid">
                            <div class="col-md-offset-3 col-md-9">
                                <button type="submit" class="btn green">保存</button>

                            </div>
                        </div>
                    </#if>
                    </form>

                </div>
            </div>
        </div>
    </div>
</div>


<script type="text/javascript">


    function doDeleteImg() {

        var name=$("#photo").val();
        layer.confirm('确定要删除头像吗？', {
            btn: ['确定', '取消']
        }, function () {
            $.ajax({
                url: url + "delete/photo",
                type: "POST",
                data: {
                    'name': name
                },
                success: function (r) {
                    if (r.code == 200) {
                        $("#photo").val("/img/log9.png");
                        $("#imgId").attr('src',"/img/log9.png");
                        $("#imgId").attr('src',"/img/log9.png");
                    } else {
                        layer.msg(r.error);
                    }
                }
            });
        })


    }
    
/*
    /!*上传头像*!/
    $('#files').fileupload({
        autoUpload: true,
        method: 'POST',
        url: '${base}/upload/file/img',
        singleFileUploads: true,
        acceptFileTypes: /(\.|\/)(gif|jpe?g|png)$/i,
        maxFileSize: 2 * 1024 * 1024,
        success: function (a) {
            $("#photo").val(a['url']);
            $("#localImag").empty();
            $("#localImag").append("<div class='img_box' id='imgBox_" + a['id'] + "'><img " +
                    "src='${base}" + a['url'] + "/type=image'" +
                    "class='img_file img-rounded'/><div class='img_edit_box'></div></div>");
        }
    });
*/

    var select = $(".select").select2();


    <#if action =='update'>
    $("select[name=status] option[value='${sysUser.status}']").attr("selected", "selected");
    var data = [];
        <#list sysUser.sysRoles as r>
        data.push({id:${r.id}, text: '${r.name}'});
        </#list>
    select.select2("data", data);
    </#if>

    var form = $('#slifeForm');
    var error = $('.alert-danger', form);
    form.validate({
        errorElement: 'span',
        errorClass: 'help-block help-block-error',
        focusInvalid: true,
        messages: {
            loginName: {remote: "登陆名已经存在"}
        },
        rules: {
            loginName: {
                minlength: 2,
                maxlength: 30,
                required: true,
                remote: '${base}/sys/user/check/${sysUser.id}'
            },
            name: {
                required: true
            },
            email: {
                required: true,
                email: true
            },
            active: {
                required: true
            },
            password: {
                maxlength: 16,
                required: true
            }
        },
        invalidHandler: function (event, validator) {
            error.show();
            Metronic.scrollTo(error, -200);
        },
        highlight: function (element) {
            $(element).closest('.form-group').addClass('has-error');
        },
        unhighlight: function (element) {
            $(element).closest('.form-group').removeClass('has-error');
        },
        success: function (label) {
            label.closest('.form-group').removeClass('has-error');
        },
        submitHandler: function (form) {
            error.hide();
            form.submit();
        }
    });


</script>


</body>

</html>