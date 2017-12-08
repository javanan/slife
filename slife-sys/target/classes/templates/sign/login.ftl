<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="utf-8">
    <meta name="viewport"
          content="width=device-width, initial-scale=1.0, maximum-scale=1.0">

    <title>登陆页</title>
    <meta name="keywords" content="slife管理系统">
    <meta name="description" content="slife管理系统">
    <link href="${base}/css/bootstrap.min.css" rel="stylesheet">
    <link href="${base}/css/font-awesome.css?v=4.4.0" rel="stylesheet">
    <link href="${base}/css/animate.css" rel="stylesheet">
    <link href="${base}/css/style.css" rel="stylesheet">
    <link href="${base}/css/login.css" rel="stylesheet">
    <link href="${base}/css/plugins/toastr/toastr.min.css" rel="stylesheet">
    <!--[if lt IE 9]>
    <meta http-equiv="refresh" content="0;ie.html"/>
    <![endif]-->
    <script>
        if (window.top !== window.self) {
            window.top.location = window.location;
        }
    </script>

</head>

<body class="signin">
<div class="signinpanel">
    <div class="row">
        <div class="col-sm-5" style="margin: auto;float: none!important;">

            <form class="login-form" action="${base}/login" method="post">

                <p class="m-t-md">欢迎登录slife后台管理系统</p>
                <div class="form-group">
                    <input type="text" id="username" name="username" class="form-control uname" placeholder="用户名" value="admin"/>
                </div>
                <div class="form-group">
                    <input type="password" id="password" name="password" class="form-control pword m-b" placeholder="密码"
                           value="123456"/>
                </div>


                <div class="form-actions">
                    <label class="checkbox"><input type="checkbox" name="rememberMe" id="rememberMe" value="true"/>
                        下次自动登录 </label>
                    <button type="submit" class="btn btn-success btn-block">登录 <i
                            class="m-icon-swapright m-icon-white"></i></button>
                </div>

            <#--<button class="btn btn-success btn-block">登录</button>-->
            </form>
        </div>
    </div>
    <div class="signup-footer">
        <div style="text-align: center;">&copy; 2017 All Rights Reserved. slife
        </div>
    </div>
</div>
<!-- 全局js -->
<script src="${base}/js/jquery.min.js?v=2.1.4"></script>
<script src="${base}/js/bootstrap.min.js?v=3.3.6"></script>

<!-- 自定义js -->
<script src="${base}/js/content.js?v=1.0.0"></script>

<!-- jQuery Validation plugin javascript-->
<script src="${base}/js/plugins/validate/jquery.validate.min.js"></script>
<script src="${base}/js/plugins/validate/messages_zh.min.js"></script>
<script src="${base}/js/plugins/layer/layer.min.js"></script>
<script src="${base}/js/plugins/toastr/toastr.min.js"></script>
<script src="${base}/js/slife/toast.js"></script>

<script>


    $(document).ready(function () {

    <#if error??>

         var msg="";
         console.info('${error}');
        <#if error?index_of("DisabledAccountException")!=-1>
            msg="用户已被屏蔽,请登录其他用户.";
        <#elseif error?index_of("UnknownAccountException")!=-1>
            msg="用户不存在,请检查后重试!";
        <#elseif error?index_of("IncorrectCredentialsException")!=-1>
            msg="密码错误,请检查密码!";
        <#else>
            msg="登录失败，请重试.";
        </#if>
        toast_error("",msg);


    </#if>

    });
    $('.login-form').validate({
        errorElement: 'span',
        errorClass: 'help-block',
        focusInvalid: false,
        rules: {
            username: {
                required: true
            },
            password: {
                required: true
            },
            remember: {
                required: false
            }
        },
        messages: {
            username: {
                required: "用户名不能为空."
            },
            password: {
                required: "密码不能为空."
            }
        },
        invalidHandler: function (event, validator) {

            toast_error("","用户名或密码不能为空");

           /* $('.form-group', $('.login-form')).show();*/
        },
        highlight: function (element) {
            $(element).closest('.form-group').addClass('has-error');
        },
        success: function (label) {
            label.closest('.form-group').removeClass('has-error');
            label.remove();
        },
        errorPlacement: function (error, element) {
            error.insertAfter(element.closest('.input-icon'));
        },
        submitHandler: function (form) {
            form.submit();
        }
    });
    $('.login-form input').keypress(function (e) {
        if (e.which == 13) {
            if ($('.login-form').validate().form()) {
                $('.login-form').submit();
            }
            return false;
        }
    });

</script>


</body>
</html>
