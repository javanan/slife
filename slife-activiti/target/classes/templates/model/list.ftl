<!DOCTYPE html>
<html>
<head>
    <title>模型管理</title>
    <link href="${base}/css/font-awesome.css?v=4.4.0" rel="stylesheet">
    <link href="${base}/css/plugins/bootstrap-table/bootstrap-table.min.css" rel="stylesheet">
    <link href="${base}/css/animate.css" rel="stylesheet">
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

                    <#--<label style="margin-left: 10px;">-->
                        <#--登录名：-->
                        <#--<input type="text" class="form-filter input-sm _search" name="search_eq_login_name">-->
                    <#--</label>-->
                    <#--<label style="margin-left: 10px;">-->
                        <#--动作：-->
                        <#--<input type="text" class="form-filter input-sm _search" name="search_eq_msg">-->
                    <#--</label>-->
                    <#--<label style="margin-left: 10px;">-->
                        <#--参数：-->
                        <#--<input type="text" class="form-filter input-sm _search" name="search_like_params">-->
                    <#--</label>-->

                    <label style="margin-left: 10px;">
                        <#--<button class="btn btn-success" onclick="re_load()">-->
                            <#--<i class="fa fa-search" aria-hidden="true"></i>查询-->
                        <#--</button>-->
                        <#--<button type="button" class="btn  btn-primary" onclick="reset()">-->
                            <#--<i class="fa fa-circle-thin" aria-hidden="true"></i>重置-->
                        <#--</button>-->
                        <button type="button" class="btn  btn-danger" onclick="batch_remove()">
                            <i class="fa fa-trash" aria-hidden="true"></i>删除
                        </button>
                        <button  type="button" class="btn  btn-info" onclick="dt_insert()">
                            <i class="fa fa-plus-square" aria-hidden="true"></i>添加
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
<script src="${base}/js/plugins/bootstrap-table/bootstrap-table.min.js"></script>
<script src="${base}/js/plugins/bootstrap-table/bootstrap-table-mobile.min.js"></script>
<script src="${base}/js/plugins/bootstrap-table/locale/bootstrap-table-zh-CN.min.js"></script>

<script src="${base}/js/slife/datatable.js"></script>
<script type="text/javascript">
    function getcolumns() {
        var c = [
            {
                checkbox: true
            },
            {
                field: 'category',
                title: '流程分类'
            },
            {
                field: 'id',
                title: '模型ID'
            },
            {
                field: 'key',
                title: '模型标识'
            },
            {
                field: 'name',
                title: '模型名称'
            },
            {
                field: 'version',
                title: '版本号'
            },
            {
                field: 'createTime',
                title: '创建时间'
            },
            {
                field: 'lastUpdateTime',
                title: '最后更新时间'
            },
            {
                title: '操作',
                field: 'id',
                align: 'center',
                formatter: function (value, row, index) {
                    return dt_delete_button(row)+dt_edit_model_button(row);
                }
            }];
        return c;
    }
    load_data( getcolumns(), {"createDate": "desc"});
</script>
</body>
</html>