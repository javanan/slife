/**
 * toast 的工具  by  122741482@qq.com
 */
$().ready(function () {


});

/**
 * 获取 toastr 配置
 * @returns {*}
 */
function get_toastr() {
    toastr.options = {
        "closeButton": true,
        "debug": false,
        "progressBar": true,
        "positionClass": "toast-top-center",
        "onclick": null,
        "showDuration": "400",
        "hideDuration": "1000",
        "timeOut": "7000",
        "extendedTimeOut": "100",
        "showEasing": "swing",
        "hideEasing": "linear",
        "showMethod": "fadeIn",
        "hideMethod": "fadeOut"
    };
    return toastr;
}
/**
 * 默认错误
 * @param title
 * @param msg
 */
function toast_error(title, msg) {
    get_toastr().error(title, msg);
}
/**
 * 提示信息
 * @param title
 * @param msg
 */
function toast_info(title, msg) {
    get_toastr().info(title, msg);
}

/**
 * 警告信息
 * @param title
 * @param msg
 */
function toast_warning(title, msg) {
    get_toastr().warning(title, msg);
}

/**
 * 成功信息
 * @param title
 * @param msg
 */
function toast_success(title, msg) {
    get_toastr().success(title, msg);
}