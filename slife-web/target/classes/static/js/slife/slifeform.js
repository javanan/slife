$.validator.setDefaults({
    submitHandler: function () {
        saveForm();
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
                parent.re_load();
                var index = parent.layer.getFrameIndex(window.name); // 获取窗口索引
                parent.layer.close(index);

            } else {
                parent.layer.alert(data.error)
            }

        }
    });

}