<!DOCTYPE html>
<html>
<head>
    <title>用户列表</title>
    <link href="${rc.contextPath}/css/font-awesome.css?v=4.4.0" rel="stylesheet">
    <link href="${rc.contextPath}/css/plugins/bootstrap-table/bootstrap-table.min.css" rel="stylesheet">
    <link href="${rc.contextPath}/css/animate.css" rel="stylesheet">
    <script>
        var url = "${url}";
    </script>
</head>


<body class="gray-bg">
<div class="wrapper wrapper-content animated fadeInRight">
    <div class="col-sm-12">
        <div class="ibox">
            <div class="ibox-body">

                <div id="exampleToolbar" role="group">

                    <label style="margin-left: 10px;">
                        登录名：
                        <input type="text" class="form-filter input-sm _search" name="search_eq_login_name">
                    </label>
                    <label style="margin-left: 10px;">
                        工号：
                        <input type="text" class="form-filter input-sm _search" name="search_eq_no">
                    </label>
                    <label style="margin-left: 10px;">
                        姓名：
                        <input type="text" class="form-filter input-sm _search" name="search_like_name">
                    </label>

                    <label style="margin-left: 10px;">
                        <button class="btn btn-success" onclick="re_load()">
                            <i class="fa fa-search" aria-hidden="true"></i>查询
                        </button>
                        <button type="button" class="btn  btn-primary" onclick="reset()">
                            <i class="fa fa-circle-thin" aria-hidden="true"></i>重置
                        </button>
                        <button type="button" class="btn  btn-danger" onclick="batch_remove()">
                            <i class="fa fa-trash" aria-hidden="true"></i>删除
                        </button>
                        <button  type="button" class="btn  btn-info" onclick="dt_insert()">
                            <i class="fa fa-plus-square" aria-hidden="true"></i>添加
                        </button>
                        <button  type="button" class="btn  btn-info" onclick="dt_explort_buttont()">
                            <i class="fa fa-plus-square" aria-hidden="true"></i>导出
                        </button>
                    </label>

                </div>

                <table id="exampleTable" data-mobile-responsive="true">
                </table>

            </div>
        </div>
    </div>
</div>



<!-- Bootstrap table -->
<script src="${rc.contextPath}/js/plugins/bootstrap-table/bootstrap-table.min.js"></script>
<script src="${rc.contextPath}/js/plugins/bootstrap-table/bootstrap-table-mobile.min.js"></script>
<script src="${rc.contextPath}/js/plugins/bootstrap-table/locale/bootstrap-table-zh-CN.min.js"></script>

<script src="${rc.contextPath}/js/slife/datatable.js"></script>
<script type="text/javascript">

    function getcolumns() {
        var c = [
            {
                checkbox: true
            },
            {
                title: '头像',
                field: 'photo',
                align: 'center',
                formatter: function (value, row, index) {
                    return '<img style="width:30px" src="${rc.contextPath}'+value+'"/>';
                }
            },
            {
                field: 'id', // 列字段名
                title: 'id' // 列标题
            },
            {
                field: 'loginName',
                title: '登陆名'
            },
            {
                field: 'name',
                title: '姓名'
            },
            {
                field: 'email',
                title: '邮箱'
            },
            {
                field: 'no',
                title: '工号'
            },
            {
                field: 'phone',
                title: '电话'
            },
            {
                field: 'mobile',
                title: '手机'
            },
            {
                field: 'remark',
                title: '描述'
            },

            {
                field : 'loginFlag',
                title : '状态',
                align : 'center',
                formatter : function(value, row, index) {
                    if (value == 'Y') {
                        return '<span class="label label-primary">正常</span>';
                    } else if (value == 'N') {
                        return '<span class="label label-danger">禁用</span>';
                    }
                }
            },
            {
                title: '操作',
                field: 'id',
                align: 'center',
                formatter: function (value, row, index) {

                    return dt_edit_button(row)+dt_detail_button(row)+dt_delete_button(row);
                }
            }];

        return c;
    }

    load_data( getcolumns(), {"createDate": "desc"});


    function dt_explort_buttont() {

        location.href=url + "exportUserList";
    }
</script>
</body>
</html>