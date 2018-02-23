<!DOCTYPE html>
<html>
<head>
    <title>博客列表</title>
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
                        标题：
                        <input type="text" class="form-filter input-sm _search" name="search_like_title">
                    </label>
                    <label style="margin-left: 10px;">
                        内容：
                        <input type="text" class="form-filter input-sm _search" name="search_like_content">
                    </label>
                    <label style="margin-left: 10px;">
                        类型：
                        <input type="text" class="form-filter input-sm _search" name="search_eq_type">
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

                       <a href="${url}detail/create">
                           <button type="button" class="btn btn-info">
                               <i class="fa fa-trash" aria-hidden="true"></i>添加
                           </button>
                       </a>
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
                field: 'id', // 列字段名
                title: '序号' // 列标题
            },
            {
                field: 'updateDate',
                title: '修改时间'
            },
            {
                field: 'title',
                title: '标题'
            },
            {
                field: 'tags',
                title: '标签'
            },
            {
                field: 'type',
                title: '类型'
            },
            {
                field: 'categories',
                title: '分类'
            },
            {
                field: 'allowComment',
                title: '可评论'
            },
            {
                field: 'status',
                title: '状态'
            },
            {
                field: 'author',
                title: '作者'
            },
            {
                title: '操作',
                field: 'id',
                align: 'center',
                formatter: function (value, row, index) {
                    return dt_delete_button(row);
                }
            }];

        return c;
    }

    load_data( getcolumns(), {"update_date": "desc"});


</script>
</body>
</html>