/**
 * 司机信息管理主JS程序
 */
/**
 * 司机信息管理主控制JS
 */
$(function(){
	var driverID=null;
	
	//显示司机信息表格
	$("#busdriverGrid").jqGrid({
		url: 'busdriver/list/page.mvc',
		datatype: "json",
		mtype:"GET",		
		colModel: [
			{ label: '姓名', name: 'dname', width: 150 },
			{ label: '性别', name: 'sex', width: 50 },
			{ label: '年龄', name: 'age', width: 50 },
			{ label: '生日', name: 'birthday', width: 150 },
			{ label: '身份证号', name: 'dcard', width: 150 },
			{ label: '驾照编号', name: 'dcore', width: 150 },
			{ label: '入职时间', name: 'joinDate', width: 150 },
			{ label: '离职时间', name: 'leaveDate', width: 150 }
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
			id:"driverID"
		},
		pager: "#busdriverGridPager",
		multiselect:false,
		onSelectRow:function(id){
			driverID=id;
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
	$("a#busdriverAddLink").on("click",function(){
		$("#modelbody").load("busdriver/add.html",function(){
			$("#ModalLabel").html("增加司机信息");
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
			$("form#busdriverAddForm").validate({
				rules:{
					dname:{
						required:true
					}
				},
				messages:{
					dname:{
						required:"司机信息不能为空"
					}
				}
				
			});
			//拦截用户增加
			$("form#busdriverAddForm").ajaxForm(function(data){
				if(data.result=="Y"){
					$("#busdriverGrid").trigger("reloadGrid");
				}
				BootstrapDialog.alert({title:"提示",message:data.message});
				$('#busdriverModal').modal("hide");
			});
			//点击取消按钮处理
			$("input[type='button'][value='取消']").on("click",function(){
				$('#busdriverModal').modal("hide");
			});
		});
		$('#busdriverModal').modal("show");
		
	});
	//点击修改处理
	$("a#busdriverModifyLink").on("click",function(){
		if(driverID==null){
			BootstrapDialog.alert({title:"提示",message:"请选择要修改的司机信息"});
		}
		else{
			$("#modelbody").load("busdriver/modify.html",function(){
				$("#ModalLabel").html("修改司机信息");
				
				/*//取得系统功能列表
				$.getJSON("function/list/all.mvc",function(funtionList){
					if(funtionList!=null){
						for(var i=0;i<funtionList.length;i++){
							$("div#userfunctions").append("<label class='checkbox-inline'><input type='checkbox' name='functionNos' value='"+funtionList[i].no+"'>"+funtionList[i].funName+"</label>");
						}
					}*/
					//取得指定的用户
					$.getJSON("busdriver/get.mvc",{driverID:driverID},function(data1){
						$("input[name='dname']").val(data1.dname);
						$("input[name='sex']").val(data1.sex);
						$("input[name='age']").val(data1.age);
						$("input[name='birthday']").val(data1.birthday);
						$("input[name='dcard']").val(data1.dcard);
						$("input[name='dcore']").val(data1.dcore);
						$("input[name='joinDate']").val(data1.joinDate);
						$("input[name='leaveDate']").val(data1.leaveDate);
					});
					
				
				
				/*//验证
				$("form#busdriverModifyForm").validate({
					rules:{
						factoryName:{
							required:true,
							remote:"busdriver/checkNameExist.mvc"
						}
					},
				messages:{
					factoryName:{
						required:"司机信息 不能为空",
						remote:"此司机信息已经存在"
					}
				}
				});*/
				//拦截用户修改表单提交
				$("form#busdriverModifyForm").ajaxForm(function(data){
					if(data.result=="Y"){
						$("#busdriverGrid").trigger("reloadGrid");
					}
					BootstrapDialog.alert({title:"提示",message:data.message});
					$('#busdriverModal').modal("hide");
				});
				$("input[type='button'][value='取消']").on("click",function(){
					$('#busdriverModal').modal("hide");
				});
			});
			$('#busdriverModal').modal("show");
		}
	});
	
	//点击删除处理
	$("a#busdriverRemoveLink").on("click",function(){
		if(driverID==null){
			BootstrapDialog.alert({title:"提示",message:"请选择要删除的司机信息"});
		}
		else{
			$.getJSON("busdriver/checkcandelete.mvc",{driverID:driverID},function(data){
				if(data.result=="Y"){
					BootstrapDialog.confirm({
						title:"删除确认",
						message:"您确认要删除此司机信息么?",
						callback:function(result){
							if(result){
								$.post("busdriver/delete.mvc",{driverID:driverID},function(data){
									if(data.result=="Y"){
										driverID=null;
										 $("#busdriverGrid").trigger("reloadGrid");
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
	$("a#busdriverViewLink").on("click",function(){
		if(driverID==null){
			BootstrapDialog.alert({title:"提示",message:"请选择要查看的司机信息"});
		}
		else{
			$("#ModalLabel").html("查看司机信息");
			$("#modelbody").load("busdriver/view.html",function(){
				
				$.getJSON("busdriver/get.mvc",{driverID:driverID},function(data1){
					if(data1!=null){
						$("div#name=dname]").html(data1.dname);
						$("div#name=sex").html(data1.sex);
						$("div#name=age").html(data1.age);
						$("div#name=birthday").html(data1.birthday);
						$("div#name=dcard").html(data1.dcard);
						$("div#name=dcore").html(data1.dcore);
						$("div#name=joinDate").html(data1.joinDate);
						$("div#name=leaveDate").html(data1.leaveDate);
					}
				});
				
				$("input[type='button'][value='返回']").on("click",function(){
					$('#busdriverModal').modal("hide");
				});
				
			});
			$('#busdriverModal').modal("show");
		}
	});
	
	//点击导入处理
	$("a#busdriverImportLink").on("click",function(){
		$("#ModalLabel").html("导入司机信息");
		$("#modelbody").load("busdriver/import.html",function(){
			$("input[type='button'][value='取消']").on("click",function(){
				 $('#busdriverModal').modal("hide");
			});
			
			$("form#busdriverImportForm").ajaxForm(function(data){
				if(data.result=="Y"){
					 $("#busdriverGrid").trigger("reloadGrid");
				}
				BootstrapDialog.alert({title:"提示",message:data.message});
				$('#busdriverModal').modal("hide");
			});
		});
		$("div.modal-dialog").css("width","600px");
		$('#busdriverModal').modal("show");
	});
	
	//点击导出处理
	$("a#busdriverExportLink").on("click",function(){
		$("#ModalLabel").html("导出司机信息");
		$("#modelbody").load("busdriver/export.html",function(){
			$("input[type='button'][value='关闭']").on("click",function(){
				 $('#busdriverModal').modal("hide");
			});
		});
		$("div.modal-dialog").css("width","600px");
		$('#busdriverModal').modal("show");
	});
});


