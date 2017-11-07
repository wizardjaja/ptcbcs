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
		rowList:[1,2,3],
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
						$("input[name='bustypeName']").val(data1.typeName);
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
			$.getJSON("user/checkcandelete.mvc",{typeNo:typeNo},function(data){
				if(data.result=="Y"){
					
					BootstrapDialog.confirm({
						title:"删除确认",
						message:"您确认要删除此车辆类型么?",
						callback:function(result){
							if(result){
								$.post("bustype/delete.mvc",{userid:userid},function(data){
									if(data.result=="Y"){
										userid=null;
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
					alert(data1);
					if(data1!=null){
						$("div#typeNo").html(data1.typeNo);
					}
				});
				
				$("button[type='reset']").on("click",function(){
					$('#bustypeModal').modal("hide");
				});
				
			});
			$('#bustypeModal').modal("show");
		}
	});
});
























//$(document).ready(function(){
//	var count = 0;
//	var pageCount = 0;
//	var rows=3;
//	var page=1;
//	var userid = 0;
//	
//	function getUserInfoList(){
//		$.get("UserInfo/list/page.mvc", {rows:rows,page:page}, function(data){
//			count = data.count;
//			pageCount = data.pageCount;
//			$("div#count").html("个数："+count);  //分页
//			$("div#pagecount").html("页数:"+page+"/"+pageCount);  //分页
//			var list = data.list;
//			if(list != null && list.length>0){
//				$("table#userinfotable tbody").html("");
//				for(var i=0; i<list.length; i++){
//					$("table#userinfotable tbody").append("<tr><td>"+list[i].userid+"</td><td>"+list[i].username+"</td><td>"+list[i].password+"</td><td>"+list[i].status+"</td></tr>")
//				}
//				$("table#userinfotable tbody tr").on("click", function(){
//					userid = parseInt($(this).find("td:first").html());
//					$("table#userinfotable tbody tr").css("background-color", "#EEE");
//					$(this).css("background-color", "orange");
//				});
//			}
//		});
//	}
//	
//	//分页
//	$("ul#pagenav li a").on("click", function(event){
//		var href = $(this).attr("href");
//		if(href == "first"){
//			page = 1;
//		}else if(href == "previous"){
//			if(page > 1){
//				page=page-1;
//			}else{
//				page = pageCount;
//			}
//		}else if(href == "next"){
//			if(page < pageCount){
//				page=page+1;
//			}else{
//				page = pageCount;
//			}
//		}else{
//			page = pageCount;
//		}
//		getUserInfoList();
//		event.preventDefault();
//	});
//	
//	//增加系统管理员
//	$("a#userInfoAddLink").on("click", function(){
//		$("#ModalLabel").html("增加系统管理员");
//		$("#modelbody").load("UserInfo/add.html", function(){
//			$("button[type='reset']").on("click", function(){
//				$('#userInfoModal').modal("hide");
//			})
//			$("button#userInfoAddSubmit").on("click", function(){
//				var userid = $("input[name='userid']").val();
//				var username = $("input[name='username']").val();
//				var password = $("input[name='password']").val();
//				var status = $("input[name='status']").val();
//				if(userid == null || userid == ''){
//					$("p#useridmessage").html("帐号不能为空");
//					$("p#useridmessage").css("color","red");
//				}
//				if(username == null || username == ''){
//					$("p#usernamemessage").html("用户名不能为空");
//					$("p#usernamemessage").css("color","red");
//				}
//				if(password == null || password == ''){
//					$("p#passwordmessage").html("密码不能为空");
//					$("p#passwordmessage").css("color","red");
//				}
//				if(status == null || status == ''){
//					$("p#statusmessage").html("用户状态不能为空");
//					$("p#statusmessage").css("color","red");
//				}else{
//					$("p#useridmessage").html("");
//					$("p#usernamemessage").html("");
//					$("p#passwordmessage").html("");
//					$("p#statusmessage").html("");
//					$.post("UserInfo/add.mvc", {userid:userid, username:username, password:password, status:status}, function(data){
//						alert(data.message);
//						getUserInfoList(); //刷新显示列表
//						$('#userInfoModal').modal("hide");
//					});
//				}
//			});
//			
//		});
//		$("#userInfoModal").modal("show");
//	});
//	
//	//点击修改处理
//	$("a#userInfoModifyLink").on("click", function(){
//		if(userid == 0){
//			BootstrapDialog.alert({title:"提示", message:"请选择要修改的系统管理员"});
//			//alert("请选择要修改的系统管理员帐号");
//		}else{
//			$("#ModalLabel").html("修改系统管理员");
//			$("#modelbody").load("UserInfo/modify.html", function(){
//				$.getJSON("UserInfo/get.mvc", {userid:userid}, function(data){
//					$("input[name='userid']").val(data.userid);
//					$("input[name='username']").val(data.username);
//					$("input[name='password']").val(data.password);
//					$("input[name='status']").val(data.status);
//				});
//				
//				$("button[type='reset']").on("click", function(){
//					$('#userInfoModal').modal("hide");
//				});
//				
//				$("button#userInfoModifySubmit").on("click", function(){
//					var userid = $("input[name='userid']").val();
//					var username = $("input[name='username']").val();
//					var password = $("input[name='password']").val();
//					var status = $("input[name='status']").val();
//					if(userid == null || userid == ''){
//						$("p#useridmessage").html("帐号不能为空");
//						$("p#useridmessage").css("color","red");
//					}
//					if(username == null || username == ''){
//						$("p#usernamemessage").html("用户名不能为空");
//						$("p#usernamemessage").css("color","red");
//					}
//					if(password == null || password == ''){
//						$("p#passwordmessage").html("密码不能为空");
//						$("p#passwordmessage").css("color","red");
//					}
//					if(status == null || status == ''){
//						$("p#statusmessage").html("用户状态不能为空");
//						$("p#statusmessage").css("color","red");
//					}else{
//						$("p#useridmessage").html("");
//						$("p#usernamemessage").html("");
//						$("p#passwordmessage").html("");
//						$("p#statusmessage").html("");
//						$.post("UserInfo/modify.mvc", {userid:userid, username:username, password:password, status:status}, function(data){
//							alert(data.message);
//							getUserInfoList(); //刷新显示列表
//							$('#userInfoModal').modal("hide");
//						});
//					}
//				});
//				
//			});
//			$("#userInfoModal").modal("show");
//		}
//	});
//	//点击查看处理
//	$("a#userInfoViewLink").on("click", function(){
//		if(userid == 0){
//			BootstrapDialog.alert({title:"提示", message:"请选择要查看的系统管理员"});
//			//alert("请选择要查看的系统管理员帐号");
//		}else{
//			$("#ModalLabel").html("查看系统管理员");
//			$("#modelbody").load("UserInfo/view.html", function(){
//				$.getJSON("UserInfo/get.mvc", {userid:userid}, function(data){
//					$("input[name='userid']").val(data.userid);
//					$("input[name='username']").val(data.username);
//					$("input[name='password']").val(data.password);
//					$("input[name='status']").val(data.status);
//				});
//				$("button[type='reset']").on("click", function(){
//					$('#userInfoModal').modal("hide");
//				});
//			});
//			$("#userInfoModal").modal("show");
//		}
//	});
//	
//	//点击删除处理
//	$("a#userInfoRemoveLink").on("click", function(){
//		if(userid == 0){
//			BootstrapDialog.alert({title:"提示", message:"请选择你要删除的系统管理员"});
//			//alert("请选择你要删除的系统管理员");
//		}else{
//			BootstrapDialog.confirm({
//				title:"删除确认", 
//				message:"您确认要删除此建筑类型么?", 
//				callback:function(result){
//					if(result){
//						$.post("UserInfo/delete.mvc", {userid:userid}, function(data){
//							if(data.result == "Y"){
//								userid = 0;
//								getUserInfoList(); //刷新显示列表
//							}else{
//								BootstrapDialog.alert({title:"提示", message:data.message});
//							}
//						});
//					}
//				}
//			});
//		}
//	});
//	getUserInfoList();
//	
//	
//});

