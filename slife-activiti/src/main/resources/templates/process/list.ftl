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
                    <label style="margin-left: 10px;">
                        <button type="button" class="btn  btn-danger" onclick="batch_remove()">
                            <i class="fa fa-trash" aria-hidden="true"></i>删除
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
                field: 'key',
                title: '模型标识'
            },
            {
                field: 'name',
                title: '流程名称'
            },
            {
                field: 'revision',
                title: '版本号'
            },
            {
                field: 'xmlName',
                title: '流程XML',
                align: 'center',
                formatter: function (value, row, index) {
                    var procDefId = row.processonDefinitionId;
                    return '<a href=${url}resource?procDefId=' + procDefId + '&resType=xml>' + value + '</a>';
                }
            },
            {
                field: 'picName',
                title: '流程图片',
                align: 'center',
                formatter: function (value, row, index) {
                    var procDefId = row.processonDefinitionId;
                    return '<a href=${url}resource?procDefId=' + procDefId + '&resType=image>' + value + '</a>';
                }
            },
            {
                field: 'deploymentTime',
                title: '部署时间'
            },
            {
                title: '操作',
                field: 'id',
                align: 'center',
                formatter: function (value, row, index) {
                    return dt_delete_button(row)+dt_active_button(row);
                }
            }];
        return c;
    }
    load_data( getcolumns(), {"createDate": "desc"});

    function dt_delete_button(row) {
        var deleteO = '<a class="btn btn-warning btn-sm" href="#" title="删除"  mce_href="#" ' +
                'onclick="remove(\'' + row.id + '\')">删除</a> ';

        return deleteO;

    }

    function dt_active_button(row) {
        var value = '';
        var status = '';
        if (row.suspend === false) {
            value = '挂起';
            status = 'suspend';
        } else {
            value = '激活'
            status = 'active';
        }
        var procDefId = row.processonDefinitionId;
        var deleteO = '<a class="btn btn-warning btn-sm" href="#" ' +
                'title="挂起/激活"  mce_href="#" onclick="active(\'' + procDefId + '\', \'' + status + '\')"> ' + value + ' </a> ';
        return deleteO;
    }

    function active(procDefId, state) {
        $.ajax({
            url: url + "status",
            type: "POST",
            data: {
                'procDefId': procDefId,
                'status': state
            },
            success: function (r) {
                dataTable_rep_message(r)
            }
        });
    }
</script>
</body>
</html>