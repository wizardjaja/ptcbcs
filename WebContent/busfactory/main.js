/**
 * 车辆厂家管理主JS程序
 */
/**
 * 车辆厂家管理主控制JS
 */
$(function(){
	var factoryNo=null;
	
	//显示车辆厂家表格
	$("#busfactoryGrid").jqGrid({
		url: 'busfactory/list/page.mvc',
		datatype: "json",
		mtype:"GET",		
		colModel: [
			{ label: '车辆厂家', name: 'factoryName', width: 150 },
			{ label: '厂家描述', name: 'factoryDesc', width: 900 },
			{ label: '厂家地址', name: 'factoryLocation', width: 300 }
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
			id:"factoryNo"
		},
		pager: "#busfactoryGridPager",
		multiselect:false,
		onSelectRow:function(id){
			factoryNo=id;
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
	$("a#busfactoryAddLink").on("click",function(){
		$("#modelbody").load("busfactory/add.html",function(){
			$("#ModalLabel").html("增加车辆厂家");
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
			$("form#busfactoryAddForm").validate({
				rules:{
					factoryName:{
						required:true,
						remote:"busfactory/checkNameExist.mvc"
					}
				},
				messages:{
					factoryName:{
						required:"车辆厂家不能为空",
						remote:"此车辆厂家已经存在"
					}
				}
				
			});
			//拦截用户增加
			$("form#busfactoryAddForm").ajaxForm(function(data){
				if(data.result=="Y"){
					$("#busfactoryGrid").trigger("reloadGrid");
				}
				BootstrapDialog.alert({title:"提示",message:data.message});
				$('#busfactoryModal').modal("hide");
			});
			//点击取消按钮处理
			$("input[type='button'][value='取消']").on("click",function(){
				$('#busfactoryModal').modal("hide");
			});
		});
		$('#busfactoryModal').modal("show");
		
	});
	//点击修改处理
	$("a#busfactoryModifyLink").on("click",function(){
		if(factoryNo==null){
			BootstrapDialog.alert({title:"提示",message:"请选择要修改的车辆厂家"});
		}
		else{
			$("#modelbody").load("busfactory/modify.html",function(){
				$("#ModalLabel").html("修改车辆厂家");
				
				/*//取得系统功能列表
				$.getJSON("function/list/all.mvc",function(funtionList){
					if(funtionList!=null){
						for(var i=0;i<funtionList.length;i++){
							$("div#userfunctions").append("<label class='checkbox-inline'><input type='checkbox' name='functionNos' value='"+funtionList[i].no+"'>"+funtionList[i].funName+"</label>");
						}
					}*/
					//取得指定的用户
					$.getJSON("busfactory/get.mvc",{factoryNo:factoryNo},function(data1){
						$("input[name='factoryNo']").val(data1.factoryNo);
						$("input[name='factoryName']").val(data1.factoryName);
						$("textarea[name='factoryDesc']").val(data1.factoryDesc);
						$("textarea[name='factoryLocation']").val(data1.factoryLocation);
					});
					
				
				
				//验证
				$("form#busfactoryModifyForm").validate({
					rules:{
						factoryName:{
							required:true,
							remote:"busfactory/checkNameExist.mvc"
						}
					},
				messages:{
					factoryName:{
						required:"车辆厂家 不能为空",
						remote:"此车辆厂家已经存在"
					}
				}
				});
				//拦截用户修改表单提交
				$("form#busfactoryModifyForm").ajaxForm(function(data){
					if(data.result=="Y"){
						$("#busfactoryGrid").trigger("reloadGrid");
					}
					BootstrapDialog.alert({title:"提示",message:data.message});
					$('#busfactoryModal').modal("hide");
				});
				$("input[type='button'][value='取消']").on("click",function(){
					$('#busfactoryModal').modal("hide");
				});
			});
			$("div.modal-dialog").css("width","1400px");
			$('#busfactoryModal').modal("show");
		}
	});
	
	//点击删除处理
	$("a#busfactoryRemoveLink").on("click",function(){
		if(factoryNo==null){
			BootstrapDialog.alert({title:"提示",message:"请选择要删除的车辆厂家"});
		}
		else{
			$.getJSON("busfactory/checkcandelete.mvc",{factoryNo:factoryNo},function(data){
				if(data.result=="Y"){
					BootstrapDialog.confirm({
						title:"删除确认",
						message:"您确认要删除此车辆厂家么?",
						callback:function(result){
							if(result){
								$.post("busfactory/delete.mvc",{factoryNo:factoryNo},function(data){
									if(data.result=="Y"){
										factoryNo=null;
										 $("#busfactoryGrid").trigger("reloadGrid");
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
	$("a#busfactoryViewLink").on("click",function(){
		if(factoryNo==null){
			BootstrapDialog.alert({title:"提示",message:"请选择要查看的车辆厂家"});
		}
		else{
			$("#ModalLabel").html("查看车辆厂家");
			$("#modelbody").load("busfactory/view.html",function(){
				
				$.getJSON("busfactory/get.mvc",{factoryNo:factoryNo},function(data){
					if(data!=null){
						$("input[name='factoryName']").val(data.factoryName);
						$("textarea[name='factoryDesc']").html(data.factoryDesc);
						$("textarea[name='factoryLocation']").html(data.factoryLocation);
					}
					if(data.photoFileName!=null){
						if(data.photoContentType.indexOf("image")==0){
							$("div#busfactoryPhoto").html("<img src='busfactory/downphoto.mvc?factoryNo="+data.factoryNo+"'  width='1000'/>");	
						}
						else{
							$("div#busfactoryPhoto").html("<a href='busfactory/downphoto.mvc?factoryNo="+data.factoryNo+"'>下载</a>");
						}
					}
					else{
						$("div#busfactoryPhoto").html("无附件");
					}
				});
				
				$("input[type='button'][value='返回']").on("click",function(){
					$('#busfactoryModal').modal("hide");
				});
				
			});
			$("div.modal-dialog").css("width","1400px");
			$('#busfactoryModal').modal("show");
		}
	});
	
	//点击导入处理
	$("a#busfactoryImportLink").on("click",function(){
		$("#ModalLabel").html("导入车辆厂家");
		$("#modelbody").load("busfactory/import.html",function(){
			$("input[type='button'][value='取消']").on("click",function(){
				 $('#busfactoryModal').modal("hide");
			});
			
			$("form#busfactoryImportForm").ajaxForm(function(data){
				if(data.result=="Y"){
					 $("#busfactoryGrid").trigger("reloadGrid");
				}
				BootstrapDialog.alert({title:"提示",message:data.message});
				$('#busfactoryModal').modal("hide");
			});
		});
		$("div.modal-dialog").css("width","600px");
		$('#busfactoryModal').modal("show");
	});
	
	//点击导出处理
	$("a#busfactoryExportLink").on("click",function(){
		$("#ModalLabel").html("导出车辆厂家");
		$("#modelbody").load("busfactory/export.html",function(){
			$("input[type='button'][value='关闭']").on("click",function(){
				 $('#busfactoryModal').modal("hide");
			});
		});
		$("div.modal-dialog").css("width","600px");
		$('#busfactoryModal').modal("show");
	});
});


