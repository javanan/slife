<html>
<head>
    <title>菜单管理</title>
    <link rel="stylesheet" type="text/css" href="${base}/assets/global/plugins/select2/select2.css"/>
    <link rel="stylesheet" type="text/css" href="${base}/assets/global/plugins/jstree/dist/themes/default/style.min.css"/>
    <link rel="stylesheet" href="${base}/assets/global/plugins/jstree/dist/themes/default/style.min.css" type="text/css"/>
</head>
<body>



<div class="row">

    <div class="col-md-4">
        <div class="portlet box green-haze">
            <div class="portlet-title">
                <div class="caption"><i class="fa fa-sitemap"></i>资源结构树</div>
            </div>
            <div class="portlet-body" id="resTree"></div>
        </div>
    </div>

    <div class="col-md-8">
        <div class="row">
            <div class="col-md-12">

                <div class="portlet box green-haze">
                    <div class="portlet-title">
                        <div class="caption">
                            <i class="fa fa-square-o"></i>资源详情
                        </div>
                        <div class="actions">
                            <div class="btn-group">
                                <a class="btn green btn-parent">
                                    <i class="fa fa-plus"></i>
                                    <span class="hidden-480">新增父菜单</span>
                                </a>
                                <button type="button" class="btn green btn-children " disabled="disabled">
                                    <i class="fa fa-plus"></i>
                                    <span class="hidden-480">新增子菜单</span>
                                </button>
                                <button type="button" class="btn blue btn-edit" disabled="disabled">
                                    <i class="fa fa-edit"></i>
                                    <span class="hidden-480">编辑此菜单</span>
                                </button>
                                <button type="button" class="btn red btn-delete" disabled="disabled">
                                    <i class="fa fa-trash-o"></i>
                                    <span class="hidden-480">禁用此菜单</span>
                                </button>
                            </div>
                        </div>
                    </div>
                    <div class="portlet-body form">
                        <form id="resourceForm" action="${base}/sys/menu/save" class="form-horizontal form-bordered" method="POST">
                            <input type="hidden" name="id"/>
                            <input type="hidden" name="supId" value="0"/>

                            <div class="form-body">
                                <div class="alert alert-danger display-hide">
                                    <button class="close" data-close="alert"></button>
                                    请检查后再提交
                                </div>
                                <div class="row">
                                    <div class="col-md-12">
                                        <div class="form-group">
                                            <label class="col-md-2 control-label">菜单名称<span class="required">*</span></label>

                                            <div class="col-md-10">
                                                <div class="input-icon right">
                                                    <i class="fa"></i>
                                                    <input type="text" class="form-control" name="name" readonly="true"/>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="row">
                                    <div class="col-md-12">
                                        <div class="form-group">
                                            <label class="col-md-2 control-label">菜单简称<span class="required">*</span></label>

                                            <div class="col-md-10">
                                                <div class="input-icon right">
                                                    <i class="fa"></i>
                                                    <input type="text" class="form-control" name="code" readonly="true"/>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="row">
                                    <div class="col-md-12">
                                        <div class="form-group" style="text-align:left">
                                            <label class="col-md-2 control-label">图标&nbsp;<span class="required">*</span></label>
                                            <div class="col-md-8">
                                                <input type="text" id="icon" name="icon" class="form-control mess_text" placeholder="请选择系统图标">
                                            </div>
                                            <span class="input-group-btn">
                                                <button id="icon_add" class="btn green" style="margin-top:10px;"  onclick="showIconModul()" type="button"> 选择</button>
                                            </span>
                                        </div>
                                    </div>
                                </div>
                                <div class="row">
                                    <div class="col-md-12">
                                        <div class="form-group">
                                            <label class="col-md-2 control-label">类型&nbsp;<span class="required">*</span></label>

                                            <div class="col-md-10">
                                                <div class="input-icon right">
                                                    <select name="type" class="form-control" disabled="disabled">
                                                    <#list resourceTypes as rt>
                                                        <option value="${rt}">${rt}</option>
                                                    </#list>
                                                    </select>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="row">
                                    <div class="col-md-12">
                                        <div class="form-group">
                                            <label class="col-md-2 control-label">属性&nbsp;<span class="required">*</span></label>

                                            <div class="col-md-10">
                                                <div class="input-icon right">
                                                    <i class="fa"></i>
                                                    <input type="text" class="form-control" name="url" readonly="true"/>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="row">
                                    <div class="col-md-12">
                                        <div class="form-group">
                                            <label class="col-md-2 control-label">排序&nbsp;<span class="required">*</span></label>

                                            <div class="col-md-10">
                                                <div class="input-icon right">
                                                    <i class="fa"></i>
                                                    <input type="text" class="form-control" name="seq" readonly="true"/>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="row">
                                    <div class="col-md-12">
                                        <div class="form-group">
                                            <label class="col-md-2 control-label">状态&nbsp;<span class="required">*</span></label>

                                            <div class="col-md-10">
                                                <div class="input-icon right">
                                                    <select name="active" class="form-control" disabled="disabled">
                                                        <option value="ENABLE">启用</option>
                                                        <option value="DISABLE">不启用</option>
                                                    </select>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="form-actions fluid">
                                <div class="col-md-offset-6 col-md-12">
                                    <button type="submit" class="btn green" disabled="disabled">保存</button>
                                    <button type="button" class="btn default btn-cancel" disabled="disabled">取消</button>
                                </div>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<div id="icon_add_div" class="modal fade" tabindex="-1" aria-hidden="true">
    <div class="modal-dialog" style="width:900px;">
        <div class="modal-content">
            <div class="modal-header" style="border-bottom:none;">
                <button type="button" class="close"  data-dismiss="modal" aria-hidden="true"></button>
            </div>
            <div class="modal-body">
                <div class="row" id="roletableDatas">
                    <div class="col-md-12">
                        <div class="portlet">
                            <div class="portlet-title">
                                <div class="caption"><i class="fa fa-cogs"></i>图标列表</div>
                                <div class="actions">
                                    <div class="btn-group">
                                        <select  class="form-control  input-small select2me" id="_dlgCheckIcon" onchange="checkIcon()">
                                            <option value="glyphiconsIcons">Glyphicons Icons</option>
                                            <option value="newIcons">10 New Icons in 4.0</option>
                                            <option value="webappIcons">Web Application Icons</option>
                                            <option value="formIcons">Form Control Icons</option>
                                            <option value="currencyIcons">Currency Icons</option>
                                            <option value="textIcons">Text Editor Icons</option>
                                            <option value="directIcons">Directional Icons</option>
                                            <option value="videoIcons">Video Player Icons</option>
                                            <option value="brandIcons">Brand Icons</option>
                                            <option value="medicalIcons">Medical Icons</option>
                                            <option value="simpleLineIcons">Simple Line Icons</option>
                                        </select>
                                    </div>
                                </div>
                            </div>
                            <div class="portlet-body">
                                <!-- 必须加上row样式,不然高度为1px,不能显示边框 -->
                                <div id="tab_1_2" class="tab-pane glyphicons-demo active">
                                    <ul id="iconTable" class="bs-glyphicons bs-glyphicons-list">
                                    </ul>
                                </div>
                                <div class="row">
                                    <table id="iconPageBar" class="col-md-8"
                                           style="text-align: center; width: 100%;">
                                        <tr>
                                            <td class="form-inline">
                                                <div class="pagination" id="iconPager"
                                                     style="font-size: 18px; text-align: center; vertical-align: middle;"></div>
                                                <span style="margin-top: 0px; size: 12px; color: #8a8a8a">跳转到</span>
                                                <input type="text" id="toMPage"
                                                       style="font-size: 18px; width: 50px; height: 30px;"
                                                       class="input-inline page_input"
                                                       onkeyup="if(isNaN(value))execCommand('undo');if(event.keyCode==32)execCommand('undo');"
                                                       onafterpaste="if(isNaN(value))execCommand('undo'));if(event.keyCode==32)execCommand('undo');">
                                                <button style="width: 40px; height: 30px;" id="gotoMPage"
                                                        class="btn">
                                                    GO
                                                </button>
                                            </td>
                                        </tr>
                                    </table>
                                    <div class="col-md-2"></div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

</body>
<slife_js>
    <script src="${base}/assets/global/plugins/uniform/jquery.uniform.min.js" type="text/javascript"></script>
    <script src="${base}/assets/global/plugins/jquery-validation/js/lib/jquery.form.js" type="text/javascript"></script>
    <script src="${base}/assets/global/plugins/jquery-validation/js/jquery.validate.min.js" type="text/javascript"></script>
    <script src="${base}/assets/global/plugins/jquery-validation/js/additional-methods.min.js" type="text/javascript"></script>
    <script src="${base}/assets/global/plugins/jquery-validation/js/localization/messages_zh.js" type="text/javascript"></script>
    <script src="${base}/assets/global/plugins/jstree/dist/jstree.min.js" type="text/javascript"></script>
    <script src="${base}/assets/global/plugins/bootbox/bootbox.min.js" type="text/javascript"></script>
    <script src="${base}/assets/drags/bootstrap-paginator.js"></script>
    <script type="text/javascript">
        var form=$('#resourceForm'), rid, text;
        $("#resTree").jstree({
            "core":{
                "animation":0,
                "themes":{
                    theme:"classic",
                    "dots":true,
                    "icons":true
                },
                "check_callback":true,
                'data':${resourceTree}
            },
            "types":{
                "default":{
                    "valid_children":["default","file"]
                }
            },
            "plugins":["wholerow"]
        }).on("select_node.jstree",function(node,selectd){
            rid=selectd.node.id;
            text=selectd.node.text;
            if(rid){
                $.ajax({
                    url:'${base}/sys/menu/read/'+rid,
                    type:'GET',
                    success:function(data){
                        $('input[name=id]').val(data.id);
                        $('input[name=supId]').val(data.supId);
                        $('input[name=name]').val(data.name);
                        $('input[name=code]').val(data.code);
                        $('input[name=icon]').val(data.icon);
                        $('#iconImg').attr("class",data.icon)
                        $("select[name=type] option[value='"+data.type+"']").attr("selected","selected");
                        $('input[name=url]').val(data.url);
                        $('input[name=seq]').val(data.seq);
                        $("select[name=active] option[value='"+data.active+"']").attr("selected","selected");
                        $('#resourceForm :input').each(function(a){
                            $(this).attr('disabled',"disabled");
                            $(this).attr("readonly","true");
                        });
                        $('.btn-children').enable();
                        $('.btn-edit').enable();
                        $('.btn-delete').enable();
                    }
                });
            }
        });
        var error=$('.alert-danger',form);
        form.validate({
            errorElement:'span',
            errorClass:'help-block help-block-error',
            focusInvalid:false,
            rules:{
                name:{
                    minlength:2,
                    maxlength:30,
                    required:true
                },
                code:{
                    required:true
                },
                type:{
                    required:true
                },
                url:{
                    required:true
                },
                seq:{
                    required:true,
                    number:true
                },
                active:{
                    required:true
                }
            },
            invalidHandler:function(event,validator){
                error.show();
                Metronic.scrollTo(error,-200);
            },
            errorPlacement:function(e,element){
                var icon=$(element).parent('.input-icon').children('i');
                icon.removeClass('fa-check').addClass("fa-warning");
                icon.attr("data-original-title",e.text()).tooltip({'container':'body'});
            },
            highlight:function(element){
                $(element).closest('.form-group').removeClass("has-success").addClass('has-error');
            },
            unhighlight:function(element){
            },
            success:function(label,element){
                var icon=$(element).parent('.input-icon').children('i');
                $(element).closest('.form-group').removeClass('has-error').addClass('has-success');
                icon.removeClass("fa-warning").addClass("fa-check");
            },
            submitHandler:function(form){
                error.hide();
                form.submit();
            }
        });
        $('.btn-parent').click(function(){
            form.resetForm();
            $('input[name=id]').val("");
            $('input[name=supId]').val(0);
            $('#resourceForm :input').each(function(a){
                $(this).enable();
                $(this).attr("readonly",false);
            });
            $('.btn-edit').attr('disabled',"disabled");
            $('.btn-delete').attr('disabled',"disabled");
        });
        $('.btn-children').click(function(){
            form.resetForm();
            $('input[name=id]').val("");
            $('input[name=supId]').val(rid);
            $('#resourceForm :input').each(function(a){
                $(this).enable();
                $(this).attr("readonly",false);
            });
            $('.btn-edit').attr('disabled',"disabled");
            $('.btn-delete').attr('disabled',"disabled");
        });
        $('.btn-edit').click(function(){
            $('#resourceForm :input').each(function(a){
                $(this).enable();
                $(this).attr("readonly",false);
            });
        });
        $('.btn-cancel').click(function(){
            $('#resourceForm :input').each(function(a){
                $(this).attr('disabled',"disabled");
                $(this).attr("readonly","true");
            });
            $('div.form-group ').removeClass('has-error');
            $('div.alert-danger').css('display','none');
        });
        $('.btn-delete').click(function(){
            if(rid)
                if(confirm("确认要禁用此菜单及其下级所有资源吗？"))
                    $.ajax({
                        url:'${base}/sys/menu/disable/'+rid,
                        type:'POST',
                        success:function(){
                            window.location.reload();
                        }
                    });
        });


        /**获取所有的功能****/
        var currentPage=1; //第几页
        var pageCount = 32 ; //每页显示多少条记录数据
        var totalPages=0;
        //分页查询
        var queryByPage=function(){
            Metronic.startPageLoading();
            $.ajax({
                dataType:"json",
                cache:true,
                type:"GET",
                url:"${base}/assets/global/icon.json",
                traditional:true,
                success:function(data){
                    Metronic.stopPageLoading();
                    var checkIcon = $("#_dlgCheckIcon").val();
                    data = data[checkIcon];
                    //删除所有子项
                    $("#iconTable").empty();
                    var total = 0 , str = '';
                    $.each(data, function(i, n){
                        str += '<li onclick="addIconImage(\''+n+'\');"><span class="'+n+'" style="font-size:24px;margin:5px auto 10px;display:block"></span><span>'+n+' </span></li>';
                        total++ ;
                    });
                    if(data==null||data==undefined||total==0){
                        return;
                    }
                    if(total<=pageCount){
                        $("#iconPageBar").css({ visibility: "hidden" });
                    }else{
                        $("#iconPageBar").css({ visibility: "visible" });
                        str = '' ;
                        var start = (currentPage-1)*pageCount ;
                        var k = 0 ;
                        $.each(data, function(i, n){
                            if(i>=start){
                                str += '<li onclick="addIconImage(\''+n+'\');"><span class="'+n+'" style="font-size:24px;margin:5px auto 10px;display:block"></span><span>'+n+' </span></li>';
                                k++ ;
                            }
                            if(pageCount==k){
                                return false;
                            }
                        });
                    }
                    $("#iconTable").append(str) ;
                    //总页数
                    if(total%pageCount!=0){
                        totalPages=parseInt(total/pageCount)+1;
                    }else{
                        totalPages=total/pageCount;
                    }
                    var options={
                        currentPage:currentPage,
                        totalPages:totalPages,
                        onPageClicked:function(event,originalEvent,type,page){
                            currentPage=page;
                            queryByPage(currentPage,pageCount);
                        }
                    }
                    $('#iconPager').bootstrapPaginator(options);
                },
                error:function(XMLHttpRequest,textStatus,errorThrown){
                    bootbox.alert("网络异常,数据不能成功返回");
                }
            });
        }
        //初始化列表
        queryByPage(currentPage,pageCount);
        //翻页
        $("#gotoMPage").bind("click",function(){
            if($("#toMPage").val()==null||""==$("#toMPage").val()){
                bootbox.alert("请输入跳转页码");
                return;
            }
            var thisPage=parseInt($("#toMPage").val());
            if(!( thisPage>0 && thisPage<=totalPages) ){
                bootbox.alert("请输入正确跳转页码");
                return;
            }
            $('#iconPager').bootstrapPaginator("show",thisPage);
            currentPage=thisPage;
            queryByPage(currentPage,pageCount);
        });
        function showIconModul(){
            $('#icon_add_div').modal('show');
        }
        function checkIcon(){
            this.currentPage=1; //第几页
            this.pageCount = 32 ; //每页显示多少条记录数据
            this.totalPages=0;
            $('#toMPage').val('');
            queryByPage(1,32);
        }
        function addIconImage(data){
            $('#icon').val(data);
            $('#icon_add_div').modal('hide');
        }

    </script>
</slife_js>
</html>