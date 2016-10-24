$(function() {
	//给登录按钮绑定单击处理
	$("#login").click(function() {
		//清空先前span的提示信息
		$("#count_msg").html("");
		$("#password_msg").html("");
		//获取用户名和密码
		var name = $("#count").val().trim();
		var password = $("#password").val().trim();
		//数据格式检查
		var ok = true;//是否通过检查
		if (name == "") {
			ok = false;
			$("#count_msg").html("用户名不为空");
		}
		if (password == "") {
			ok = false;
			$("#password_msg").html("密码不为空");
		}
	
		//发送Ajax请求
		if (ok) {
			$.ajax({
				url : "http://localhost:8888/cloud_note/user/login.do",
				type : "post",
				data : {"name" : name,"password" : password},
				dataType : "json",
				success : function(result) {
					if (result.status == 0) {
						//获取登录者的ID
						var userId=result.data;
						//放入Cookie
						addCookie("userId",userId,2);
						window.location.href = "edit.html";//成功
					} else if (result.status == 1) {
						//用户名错误
						$("#count_msg").html(result.msg);
					} else if (result.status == 2) {
						//密码错误
						$("#password_msg").html(result.msg);
					}
				},
				error : function() {
					alert("登录发生异常");
				}
			});
		}//if结束
	});

});