/**
 * 添加请求动画
 */
function start_request_load() {

$("body").prepend('<div class="spiner-example slife_load" id="slife_load_div"> <div class="sk-spinner' +
    ' sk-spinner-wave"><div' +
    ' class="sk-rect1"></div><div class="sk-rect2"></div> <div class="sk-rect3"></div><div class="sk-rect4"></div><div class="sk-rect5"></div></div></div>');
}

/**
 * 删除请求动画
 */
function stop_request_load() {
    $("#slife_load_div").remove();
}



/**
 * axaj 请求返回
 * @param r
 */
function rep_message(r) {
    if (r.code == 200) {
        layer.msg(r.message);
    } else {
        layer.msg(r.error);
    }
}





