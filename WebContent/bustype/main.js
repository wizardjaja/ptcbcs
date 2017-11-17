/**
 * 系统操作员管理主JS程序
 */
/**
 * 系统操作员管理主控制JS
 */
$(function(){
	var typeNo=null;
	
	//显示系统操作员表格
	$("#bustypeGrid").jqGrid({
		url: 'bustype/list/page.mvc',
		datatype: "json",
		mtype:"GET",		
		colModel: [
			{ label: '车辆类型', name: 'typeName', width: 150 }
		],
		viewrecords: true, // show the current page, data rang and total records on the toolbar
		autowidth:true,
		height: 370,
		rowNum: 10,
		rowList:[5,10],
		jsonReader:{
			root:"list",
			page:"page",
			total:"pageCount",
			records:"count",
			id:"typeNo"
		},
		pager: "#bustypeGridPager",
		multiselect:false,
		onSelectRow:function(id){
			typeNo=id;
		},
		loadComplete:function(data){
			//js只要不是false就都是true
			if(data.message){
				BootstrapDialog.alert({title:"提示",message:data.message});
			}
			
		},
		loadError:function(xhr,status,error){
			BootstrapDialog.alert({title:"提示",message:error});
			
		}
	});
	//点击增加处理
	$("a#bustypeAddLink").on("click",function(){
		$("#modelbody").load("bustype/add.html",function(){
			$("#ModalLabel").html("增加车辆类型");
			//取得系统功能列表
			/*$.getJSON("function/list/all.mvc",function(funtionList){
				if(funtionList!=null){
					if(funtionList.message){
						BootstrapDialog.alert({title:"提示",message:data.message});
					}
					else{
						for(var i=0;i<funtionList.length;i++){
							$("div#userfunctions").append("<label class='checkbox-inline'><input type='checkbox' name='functionNos' value='"+funtionList[i].no+"'>"+funtionList[i].name+"</label>");
						}
					}
				}
			});*/
			//验证
			$("form#bustypeAddForm").validate({
				rules:{
					typeName:{
						required:true,
						remote:"bustype/checkNameExist.mvc"
					}
				},
				messages:{
					typeName:{
						required:"车辆类型不能为空",
						remote:"此车辆类型已经存在"
					}
				}
				
			});
			//拦截用户增加
			$("form#bustypeAddForm").ajaxForm(function(data){
				if(data.result=="Y"){
					$("#bustypeGrid").trigger("reloadGrid");
				}
				BootstrapDialog.alert({title:"提示",message:data.message});
				$('#bustypeModal').modal("hide");
			});
			//点击取消按钮处理
			$("button[type='reset']").on("click",function(){
				$('#bustypeModal').modal("hide");
			});
		});
		$('#bustypeModal').modal("show");
		
	});
	//点击修改处理
	$("a#bustypeModifyLink").on("click",function(){
		if(typeNo==null){
			BootstrapDialog.alert({title:"提示",message:"请选择要修改的车辆类型"});
		}
		else{
			$("#modelbody").load("bustype/modify.html",function(){
				$("#ModalLabel").html("修改车辆类型");
				
				/*//取得系统功能列表
				$.getJSON("function/list/all.mvc",function(funtionList){
					if(funtionList!=null){
						for(var i=0;i<funtionList.length;i++){
							$("div#userfunctions").append("<label class='checkbox-inline'><input type='checkbox' name='functionNos' value='"+funtionList[i].no+"'>"+funtionList[i].funName+"</label>");
						}
					}*/
					//取得指定的用户
					$.getJSON("bustype/get.mvc",{typeNo:typeNo},function(data1){
						$("input[name='typeNo']").val(data1.typeNo);
						$("input[name='typeName']").val(data1.typeName);
					});
					
				
				
				//验证
				$("form#bustypeModifyForm").validate({
					rules:{
						typeName:{
							required:true,
							remote:"bustype/checkNameExist.mvc"
						}
					},
				messages:{
					typeName:{
						required:"车辆类型不能为空",
						remote:"此车辆类型已经存在"
					}
				}
				});
				//拦截用户修改表单提交
				$("form#bustypeModifyForm").ajaxForm(function(data){
					if(data.result=="Y"){
						$("#bustypeGrid").trigger("reloadGrid");
					}
					BootstrapDialog.alert({title:"提示",message:data.message});
					$('#bustypeModal').modal("hide");
				});
				$("button[type='reset']").on("click",function(){
					$('#bustypeModal').modal("hide");
				});
			});
			$('#bustypeModal').modal("show");
		}
	});
	
	//点击删除处理
	$("a#bustypeRemoveLink").on("click",function(){
		if(typeNo==null){
			BootstrapDialog.alert({title:"提示",message:"请选择要删除的车辆类型"});
		}
		else{
			$.getJSON("bustype/checkcandelete.mvc",{typeNo:typeNo},function(data){
				if(data.result=="Y"){
					BootstrapDialog.confirm({
						title:"删除确认",
						message:"您确认要删除此车辆类型么?",
						callback:function(result){
							if(result){
								$.post("bustype/delete.mvc",{typeNo:typeNo},function(data){
									if(data.result=="Y"){
										typeNo=null;
										 $("#bustypeGrid").trigger("reloadGrid");
									}
									BootstrapDialog.alert({title:"提示",message:data.message});
									
								});
							}
						}
					});
					
				}
				else{
					BootstrapDialog.alert({title:"警告",message:data.message});
				}
			});
		}
	});
	
	//点击查看处理
	$("a#bustypeViewLink").on("click",function(){
		if(typeNo==null){
			BootstrapDialog.alert({title:"提示",message:"请选择要查看的车辆类型"});
		}
		else{
			$("#ModalLabel").html("查看车辆类型");
			$("#modelbody").load("bustype/view.html",function(){
				
				$.getJSON("bustype/get.mvc",{typeNo:typeNo},function(data1){
					if(data1!=null){
						$("div#typeName").html(data1.typeName);
					}
				});
				
				$("button[type='reset']").on("click",function(){
					$('#bustypeModal').modal("hide");
				});
				
			});
			$('#bustypeModal').modal("show");
		}
	});
	
	//点击导入处理
	$("a#bustypeImportLink").on("click",function(){
		$("#ModalLabel").html("导入车辆类型");
		$("#modelbody").load("bustype/import.html",function(){
			$("input[type='button'][value='取消']").on("click",function(){
				 $('#bustypeModal').modal("hide");
			});
			
			$("form#bustypeImportForm").ajaxForm(function(data){
				if(data.result=="Y"){
					 $("#bustypeGrid").trigger("reloadGrid");
				}
				BootstrapDialog.alert({title:"提示",message:data.message});
				$('#bustypeModal').modal("hide");
			});
		});
		$("div.modal-dialog").css("width","600px");
		$('#bustypeModal').modal("show");
	});
	
	//点击导出处理
	$("a#bustypeExportLink").on("click",function(){
		$("#ModalLabel").html("导出车辆类型");
		$("#modelbody").load("bustype/export.html",function(){
			$("input[type='button'][value='关闭']").on("click",function(){
				 $('#bustypeModal').modal("hide");
			});
		});
		$("div.modal-dialog").css("width","600px");
		$('#bustypeModal').modal("show");
	});
});


