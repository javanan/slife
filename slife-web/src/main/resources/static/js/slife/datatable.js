/**
 * 请求 成功 返回的 操作
 * @param r
 */
function dataTable_rep_message(r) {
    if (r.code == 200) {
        layer.msg(r.message);
        re_load();
    } else {
        layer.msg(r.error);
    }
}



/**
 * 批量删除
 */
function batch_remove() {
    var rows = $('#exampleTable').bootstrapTable('getSelections'); // 返回所有选择的行，当没有选择的记录时，返回一个空数组
    if (rows.length == 0) {
        layer.msg("请选择要删除的数据");
        return;
    }
    layer.confirm("确认要删除选中的'" + rows.length + "'条数据吗?", {
        btn: ['确定', '取消']
        // 按钮
    }, function () {
        /*var ids = new Array();*/
        var ids = '';
        //遍历所有选择的行数据，取每条数据对应的ID
        $.each(rows, function (i, row) {
            ids = ids + row['id'] + ",";
        });
        ids = ids.substr(0, ids.length - 1);
        $.ajax({
            type: 'POST',
            data: {"ids": ids},
            url: url + "delete",
            success: function (r) {
                dataTable_rep_message(r)
            }
        });
    }, function () {

    });
}

/**
 * 删除某条记录
 * @param url
 * @param id
 */
function remove(id) {


    layer.confirm('确定要删除选中的记录？', {
        btn: ['确定', '取消']
    }, function () {
        $.ajax({
            url: url + "delete",
            type: "POST",
            data: {
                'ids': id
            },
            success: function (r) {
                dataTable_rep_message(r)
            }
        });
    })
}

function reset() {
    $("#exampleToolbar ._search").each(function () {
        $(this).val("");
    });
    re_load();
}

/**
 * 重新加载表
 */
function re_load() {
    $('#exampleTable').bootstrapTable('refresh');
}


/**
 * 导出数据
 * @param columns
 * @param sorts
 */
function export_data(columns, sorts) {
    var searchParams = {};
    $("#exampleToolbar ._search").each(function () {
        searchParams[$(this).attr('name')] = $(this).val();
    });

    var  d={
        "pageNumber": 1,
        "pageSize": 99999,
        "searchParams": searchParams,
        "sorts": sorts

    }
  //  JSON.stringify(GetJsonData())
    $.ajax({
        type: "POST",  //提交方式
        url: url + "exportUserList", // 服务器数据的加载地址
        dataType: "json",
        contentType: "application/json; charset=utf-8",//(可以)
        data:JSON.stringify(d) ,//数据，这里使用的是Json格式进行传输
        success: function (result) {//返回数据根据结果进行相应的处理

        }

    });
    
    
}


/**
 * 加载表数据
 * @param url
 * @param columns
 */
function load_data(columns, sorts) {
    $('#exampleTable').bootstrapTable(
        {
            method: 'post', // 服务器数据的请求方式 get or post
            url: url + "list", // 服务器数据的加载地址
            //showRefresh : true,
            //showToggle : true,
            //showColumns : true,
            iconSize: 'outline',
            toolbar: '#exampleToolbar',
            striped: true, // 设置为true会有隔行变色效果
            dataType: "json", // 服务器返回的数据类型
            pagination: true, // 设置为true会在底部显示分页条
            // queryParamsType : "limit",
            // //设置为limit则会发送符合RESTFull格式的参数
            singleSelect: false, // 设置为true将禁止多选
            // contentType : "application/x-www-form-urlencoded",
            // //发送到服务器的数据编码类型
            pageSize: 10, // 如果设置了分页，每页数据条数
            pageNumber: 1, // 如果设置了分布，首页页码
            //search : true, // 是否显示搜索框
            //showColumns : true, // 是否显示内容下拉框（选择显示的列）
            sidePagination: "server", // 设置在哪里进行分页，可选值为"client" 或者
            // "server"
            queryParams: function (params) {

                var searchParams = {};
                $("#exampleToolbar ._search").each(function () {
                    searchParams[$(this).attr('name')] = $(this).val();
                });

                var p = {
                    "pageNumber": this.pageNumber,
                    "pageSize": this.pageSize,
                    "searchParams": searchParams,
                    "sorts": sorts

                };
                return p;

            },
            // //请求服务器数据时，你可以通过重写参数的方式添加一些额外的参数，例如 toolbar 中的参数 如果
            // queryParamsType = 'limit' ,返回参数必须包含
            // limit, offset, search, sort, order 否则, 需要包含:
            // pageSize, pageNumber, searchText, sortName,
            // sortOrder.
            // 返回false将会终止请求
            columns: columns
        });
}


/**
 * 删除 按钮
 * @returns {string}
 */
function dt_delete_button(row) {
    var deleteO = '<a class="btn btn-warning btn-sm" href="#" title="删除"  mce_href="#" ' +
        'onclick="remove(\'' + row.id + '\')"><i class="fa fa-remove"></i></a> ';

    return deleteO;

}

/**
 * 编辑 按钮
 * @returns {string}
 */
function dt_edit_button(row) {
    var editO = '<a class="btn btn-primary btn-sm" href="#" mce_href="#" title="编辑" onclick="dt_update(\''
        + row.id + '\')"><i class="fa fa-edit"></i></a> ';
    return editO;
}

/**
 * 详情按钮
 * @returns {*}
 */
function dt_detail_button(row) {
    var detailO = '<a class="btn btn-success btn-sm" href="#" title="详情"  mce_href="#" onclick="dt_detail(\'' + row.id + '\')"><i class="fa fa-info-circle"></i></a> ';
    return detailO;
}


/**
 * 打开新增框
 */
function dt_insert() {
    dt_action("新增", "insert");
}


/**
 * 打开编辑框
 * @param id
 */
function dt_update(id) {
    dt_action("编辑", "update/" + id);
}

/**
 * 打开详情框
 * @param id
 */
function dt_detail(id) {
    dt_action("详情", "detail/" + id);
}

/**
 * 打开模态框
 * @param title
 * @param action
 */
function dt_action(title, action) {
    // iframe层
    layer.open({
        type: 2,
        title: title,
        maxmin: true,
        shadeClose: false, // 点击遮罩关闭层
        area: ['800px', '520px'],
        content: url + action // iframe的url
    });
}