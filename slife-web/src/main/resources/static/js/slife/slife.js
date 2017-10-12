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
 *
 *docName   file组件的name
 *viewId  img的id
 *localId  div的id
 * imgWidth  img的宽度
 * imgHeight img的高度
 * divWidth div的宽度
 * divHeight div的高度
 */
function setImagePreview(docName,viewId,localId,imgWidth,imgHeight,divWidth,divHeight,tip){
    // return;
    $('#uploadFiletIcon').text('');
    var docObj=document.getElementById(docName); //"doc");
    var imgObjPreview=document.getElementById(viewId); //"preview");
    var fileObj=$('#'+docName).val();
    if(tip==1){
        $('#tip').val(1);
        $('#uploadFileIconx').val('');
        $('#uploadFileIcon').text('');
    }else{
        $('#iconTip').val(1);
    }
    if(fileObj){
        if(docObj.files&&docObj.files[0]){
            //火狐下，直接设img属性
            imgObjPreview.style.display='inline-block';
            imgObjPreview.style.width=imgWidth; //'60px';
            imgObjPreview.style.height=imgHeight; //'60px';
            //imgObjPreview.src = docObj.files[0].getAsDataURL();
            //火狐7以上版本不能用上面的getAsDataURL()方式获取，需要一下方式
            imgObjPreview.src=window.URL.createObjectURL(docObj.files[0]);
        }else{
            //IE下，使用滤镜
            docObj.select();
            var imgSrc=document.selection.createRange().text;
            var localImagId=document.getElementById(localId); //"localImag");
            //必须设置初始大小
            localImagId.style.width=divWidth; //"300px";
            localImagId.style.height=divHeight; //"120px";
            //图片异常的捕捉，防止用户修改后缀来伪造图片
            try{
                localImagId.style.filter="progid:DXImageTransform.Microsoft.AlphaImageLoader(sizingMethod=scale)";
                localImagId.filters.item("DXImageTransform.Microsoft.AlphaImageLoader").src=imgSrc;
            }catch(e){
                alert("您上传的图片格式不正确，请重新选择!");
                return false;
            }
            imgObjPreview.style.display='none';
            document.selection.empty();
        }
    }
    return true;
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

