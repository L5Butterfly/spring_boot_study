var recordTextWithExport = "<span class='glyphicon glyphicon-refresh refresh pointer btn-refresh mt10'></span> &nbsp; {0}-{1}, 共{2}条   &nbsp;<a class='btn-jqgrid-toggle-column' href='javascript:void(0)'>显示/隐藏列&nbsp;</a><a class='btn-export' href='javascript:void(0)'>导出</a>";
var recordText = "<span class='glyphicon glyphicon-refresh refresh pointer btn-refresh mt10'></span> &nbsp; {0}-{1}, 共{2}条 &nbsp;<a class='btn-jqgrid-toggle-column' href='javascript:void(0)'>调整列&nbsp;</a>";
$.jgrid.ajaxOptions.contentType = "json;charset=utf-8";

$.jgrid.defaults = {
    recordtext: isPC() ? recordTextWithExport : recordText,
    emptyrecords: "没有数据",
    loadtext: "加载中...",
    pgtext : "{0}/{1}",
    mtype : 'POST',
    shrinkToFit: isPC(),
    autowidth: true,
    autoScroll: false,
    search:true,
    datatype : 'json',
    ajaxGridOptions: {contentType: "application/x-www-form-urlencoded"},
    resizeStop :function(newWidth,columnIndex){
    	var $grid = $("#"+this.id);
		var allColumns = $grid.jqGrid('getGridParam','colModel');
		var newWidth = [];
    	for(var i = 0 ; i < allColumns.length;i++){
    		newWidth.push(allColumns[i].width||0);
    	}
    	storage.local('columns-width-'+this.id, newWidth.join(','));
    },
    jsonReader : {
	    root: "res",  //数据模型
	    page: "page",//数据页码
	    total: "total",//数据总页码
	    records: "records",//数据总记录数
	    repeatitems: true,//如果设为false，则jqGrid在解析json时，会根据name(colmodel 指定的name)来搜索对应的数据元素（即可以json中元素可以不按顺序）
	    cell: "cell",//root 中row 行
	    id: "id",//唯一标识
	    userdata: "userdata",
	    subgrid: {root:"res"}
    },
    viewrecords : true,
    width:'100%',
    height:'auto',
    sortable : true,
    pgbuttons : true,
    rowNum : 15,
    rowList:[10,15,20,30,50,100]
}

//显示与隐藏列
$(document).on("click",".btn-jqgrid-toggle-column",function(e){
    var gridId = $(e.target).parents('.ui-widget-content').attr('id').trim('gbox_');
    var grid = $("#"+gridId);
    var originalWith = grid.data('orignial-width');
    if(!originalWith){
    	window.grid = grid;
    	originalWith = grid.jqGrid("getGridParam",'width')
    	grid.data('orignial-width',originalWith);
    }
    var op = {modal:true, updateAfterCheck:true, caption:"显示/隐藏列",bSubmit:"确定", bCancel:"取消",closeOnEscape:true,colnameview:false, ShrinkToFit:isPC()};
    grid.setGridWidth(originalWith);
    grid.jqGrid('setColumns',op);
    return false;
});

//导出
$(document).on("click",".btn-export",function(e){
    var gridId = $(e.target).parents('.ui-widget-content').attr('id').trim('gbox_');
    var message='<div style="float:left;width:40%;text-align:left;"><b>导出格式：</b><br /><br />'
        +'<label><input type="radio" value="excel" checked name="filetype" />&nbsp;excel格式</label><br />'
        +'<label><input type="radio" value="csv" name="filetype" />&nbsp;csv格式</label></div>'
        +'<div style="float:left;width:40%;text-align:left;"><b>数据范围：</b><br /><br />';
    
    var pagerId=$(e.target).parents('.ui-jqgrid-pager').attr('id');
    var type=$("#"+pagerId).data("export-type") || 2;
    switch(type){
    case 0:
    	BootstrapDialog.warning("此页面不支持导出!");
    	return;
    case 2:
    	message+='<label><input type="radio" value="all" name="data" />&nbsp;全部页</label><br />';
    case 1:
    	message+='<label><input type="radio" value="page" checked name="data" />&nbsp;当前页</label><br />'
    	break;
    }
    message+='</div><div class="clear"></div><input class="hide" type="text" value="'+gridId+'" name="gridId"/>';
    BootstrapDialog.show({
        title: '导出选项',
        message: message,
        buttons: [{
            label: '导出',
            action: function(dialog) {
                var exportOptions = dialog.$modalContent.serializeObject();
                var $grid = $('#'+exportOptions.gridId), url = $grid.jqGrid('getGridParam','url');
                url = url+"?export=1&filetype="+exportOptions.filetype;
                if(url.trim() == ""){
                    console.error('无法导出'+exportOptions.gridId);
                    BootstrapDialog.warning('无法导出'+exportOptions.gridId);
                    return;
                }
                var colModel = $grid.jqGrid('getGridParam','colModel');
                var colNames = [];
                var colNamesEn = [];
                for(var i = 0;i<colModel.length;i++){
                	//不需要导出的字段
                	if(colModel[i].isExport === false)
                		continue;
                	//checkbox不需要导出
                	if(colModel[i].name == 'cb')
                		continue;
                    if(colModel[i].hidden && colModel[i].isExport != true)
                        continue;
                    colNames.push(colModel[i].label||colModel[i].name);
                    colNamesEn.push(colModel[i].name);
                }
                url += "&colnames="+encodeURIComponent(encodeURIComponent(colNames.join(",")))+"&colnamesen="+colNamesEn.join(',');
                var postData = $grid.jqGrid("getGridParam", "postData");
                postData = $.extend({},postData);
                if(exportOptions.data == 'all'){
                	postData.rows = -1;
                	postData.page = -1;
                }
                
                for(var i in postData){
                    url += "&"+i+"="+encodeURIComponent(encodeURIComponent(postData[i]));
                }
                console.log(url);
                window.open(url);
                dialog.close();
            }
        }, {
            label: '取消',
            action: function(dialog) {
                dialog.close()
            }
        }]
    });
});

//列表查询
$(document).on("change",".btn-search",searchGrid);
$(document).on("click",".btn-search",function(e){
    return e.target.nodeName !== "SELECT" && searchGrid(e);    
});

function searchGrid(e){
    var $form = $($(e.target).parents(".search-form")[0]);
    //validate data-time span
    var validated = true;
    $form.find(".datetimepickerspan").each(function(i,e){
        var $this = $(e);
        var startTime = $this.find(".startTime").val().toDate('yyyy-MM-dd').getTime();
        var endTime = $this.find(".endTime").val().toDate('yyyy-MM-dd').getTime();
        var timeRange = parseDate($this.data('range')).getTime();
        if(startTime > endTime){
            BootstrapDialog.warning('开始时间不能晚于结束时间');
            validated = false;
            return false;
        }
        if(endTime - startTime > timeRange){
            BootstrapDialog.warning('时间段不能超过'+$this.data('range').replace("d","")+'天');
            validated = false;
            return false;
        }
    });
    if(!validated)
        return false;
    var gridIds = ($form.data('for-jqgrid-id') || "").split(";");  //支持以;分割的多个jqgrid
    var queryParam = $form.serializeObject();
    for(var i in gridIds){
    	//树形结构的页面，不存在grid
    	if(gridIds[i] && gridIds[i] !=''){
	        var jqGrid = $("#"+gridIds[i]).jqGrid();
	        window.grid = jqGrid;
	        var postData = jqGrid.jqGrid("getGridParam", "postData") || {};
	        $.extend(postData,queryParam);
	        jqGrid.jqGrid("setGridParam", {search:true,datatype : 'json'}).trigger("reloadGrid", [{ page: 1}]); 
    	}
    }
}

$(document).on("keydown",".search-form input",function(e){
    if(e.keyCode != 13) return ;
    var $btn = $(e.target).parents('.search-form').find('.btn-search');//
    $btn.length != 0 && $btn.trigger("click");
    return false;
});

$(document).on("click",".btn-refresh",function(e){
    var gridId = $(e.target).parents('.ui-widget-content').attr('id').trim('gbox_');
    var jqGrid = $("#"+gridId).jqGrid();
       jqGrid.jqGrid("setGridParam", {search:true,datatype : 'json'}).trigger("reloadGrid"); 
});