/**
 * 维修类型管理主JS程序
 */
/**
 * 维修类型管理主控制JS
 */
$(function(){
	var typeNo=null;
	
	//显示维修类型表格
	$("#servicetypeGrid").jqGrid({
		url: 'servicetype/list/page.mvc',
		datatype: "json",
		mtype:"GET",		
		colModel: [
			{ label: '维修类型', name: 'typeName', width: 150 },
			{ label: '维修描述', name: 'typeDesc', width: 600 }
		],
		viewrecords: true, // show the current page, data rang and total records on the toolbar
		autowidth:true,
		height: 370,
		rowNum: 10,
		rowList:[5,10,15],
		jsonReader:{
			root:"list",
			page:"page",
			total:"pageCount",
			records:"count",
			id:"typeNo"
		},
		pager: "#servicetypeGridPager",
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
	$("a#servicetypeAddLink").on("click",function(){
		$("#modelbody").load("servicetype/add.html",function(){
			$("#ModalLabel").html("增加维修类型");
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
			$("form#servicetypeAddForm").validate({
				rules:{
					typeName:{
						required:true,
						remote:"servicetype/checkNameExist.mvc"
					}
				},
				messages:{
					typeName:{
						required:"维修类型不能为空",
						remote:"此维修类型已经存在"
					}
				}
				
			});
			//拦截用户增加
			$("form#servicetypeAddForm").ajaxForm(function(data){
				if(data.result=="Y"){
					$("#servicetypeGrid").trigger("reloadGrid");
				}
				BootstrapDialog.alert({title:"提示",message:data.message});
				$('#servicetypeModal').modal("hide");
			});
			//点击取消按钮处理
			$("input[type='button'][value='取消']").on("click",function(){
				$('#servicetypeModal').modal("hide");
			});
		});
		$("div.modal-dialog").css("width","1400px");
		$('#servicetypeModal').modal("show");
		
	});
	//点击修改处理
	$("a#servicetypeModifyLink").on("click",function(){
		if(typeNo==null){
			BootstrapDialog.alert({title:"提示",message:"请选择要修改的维修类型"});
		}
		else{
			$("#modelbody").load("servicetype/modify.html",function(){
				$("#ModalLabel").html("修改维修类型");
				
				/*//取得系统功能列表
				$.getJSON("function/list/all.mvc",function(funtionList){
					if(funtionList!=null){
						for(var i=0;i<funtionList.length;i++){
							$("div#userfunctions").append("<label class='checkbox-inline'><input type='checkbox' name='functionNos' value='"+funtionList[i].no+"'>"+funtionList[i].funName+"</label>");
						}
					}*/
					//取得指定的用户
					$.getJSON("servicetype/get.mvc",{typeNo:typeNo},function(data1){
						$("input[name='typeNo']").val(data1.typeNo);
						$("input[name='typeName']").val(data1.typeName);
						$("textarea[name='typeDesc']").val(data1.typeDesc);
					});
					
				
				
				//验证
				$("form#servicetypeModifyForm").validate({
					rules:{
						typeName:{
							required:true,
							remote:"servicetype/checkNameExist.mvc"
						},
						typeDesc:{
							required:false
						}
					},
				messages:{
					typeName:{
						required:"维修类型 不能为空",
						remote:"此维修类型已经存在"
					}
				}
				});
				//拦截用户修改表单提交
				$("form#servicetypeModifyForm").ajaxForm(function(data){
					if(data.result=="Y"){
						$("#servicetypeGrid").trigger("reloadGrid");
					}
					BootstrapDialog.alert({title:"提示",message:data.message});
					$('#servicetypeModal').modal("hide");
				});
				$("input[type='button'][value='取消']").on("click",function(){
					$('#servicetypeModal').modal("hide");
				});
			});
			$("div.modal-dialog").css("width","1400px");
			$('#servicetypeModal').modal("show");
		}
	});
	
	//点击删除处理
	$("a#servicetypeRemoveLink").on("click",function(){
		if(typeNo==null){
			BootstrapDialog.alert({title:"提示",message:"请选择要删除的维修类型"});
		}
		else{
			$.getJSON("servicetype/checkcandelete.mvc",{typeNo:typeNo},function(data){
				if(data.result=="Y"){
					BootstrapDialog.confirm({
						title:"删除确认",
						message:"您确认要删除此维修类型么?",
						callback:function(result){
							if(result){
								$.post("servicetype/delete.mvc",{typeNo:typeNo},function(data){
									if(data.result=="Y"){
										typeNo=null;
										 $("#servicetypeGrid").trigger("reloadGrid");
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
	$("a#servicetypeViewLink").on("click",function(){
		if(typeNo==null){
			BootstrapDialog.alert({title:"提示",message:"请选择要查看的维修类型"});
		}
		else{
			$("#ModalLabel").html("查看维修类型");
			$("#modelbody").load("servicetype/view.html",function(){
				
				$.getJSON("servicetype/get.mvc",{typeNo:typeNo},function(data){
					if(data!=null){
						$("input[name='typeName']").val(data.typeName);
						$("textarea[name='typeDesc']").html(data.typeDesc);
					}
					if(data.photoFileName!=null){
						if(data.photoContentType.indexOf("image")==0){
							$("div#servicetypePhoto").html("<img src='servicetype/downphoto.mvc?typeNo="+data.typeNo+"'  width='1000'/>");	
						}
						else{
							$("div#servicetypePhoto").html("<a href='servicetype/downphoto.mvc?typeNo="+data.typeNo+"'>下载</a>");
						}
					}
					else{
						$("div#servicetypePhoto").html("无附件");
					}
				});
				
				$("input[type='button'][value='返回']").on("click",function(){
					$('#servicetypeModal').modal("hide");
				});
				
			});
			$("div.modal-dialog").css("width","1400px");
			$('#servicetypeModal').modal("show");
		}
	});
	
	//点击导入处理
	$("a#servicetypeImportLink").on("click",function(){
		$("#ModalLabel").html("导入维修类型");
		$("#modelbody").load("servicetype/import.html",function(){
			$("input[type='button'][value='取消']").on("click",function(){
				 $('#servicetypeModal').modal("hide");
			});
			
			$("form#servicetypeImportForm").ajaxForm(function(data){
				if(data.result=="Y"){
					 $("#servicetypeGrid").trigger("reloadGrid");
				}
				BootstrapDialog.alert({title:"提示",message:data.message});
				$('#servicetypeModal').modal("hide");
			});
		});
		$("div.modal-dialog").css("width","600px");
		$('#servicetypeModal').modal("show");
	});
	
	//点击导出处理
	$("a#servicetypeExportLink").on("click",function(){
		$("#ModalLabel").html("导出维修类型");
		$("#modelbody").load("servicetype/export.html",function(){
			$("input[type='button'][value='关闭']").on("click",function(){
				 $('#servicetypeModal').modal("hide");
			});
		});
		$("div.modal-dialog").css("width","600px");
		$('#servicetypeModal').modal("show");
	});
});


