/**
 * 系统的主页面控制JS文件
 */
$(document).ready(function(){
	$.jgrid.defaults.styleUI = 'Bootstrap';
	//$("div#maincontent").css("background-color", "#EEE");
	$("div#maincontent").css("height", "500px");
	
	$("ul.nav-second-level li a").on("click",function(event){
		var href = $(this).attr("href");
		if(href != "#"){
			$("div#maincontent").load(href);
		}
		event.preventDefault();
	});
});