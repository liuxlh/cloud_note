$(function() {
	var name;
	var nick;
	var pwd;
	var repwd;
	var ok = true;
	//检查数据格式
	get("regist_username").onfocus = function() {
		$("#warning_1").hide();//显示元素
	}
	get("regist_password").onfocus = function() {
		$("#warning_2").hide();//显示元素
	}
	get("final_password").onfocus = function() {
		$("#warning_3").hide();//显示元素
	}

	get("regist_username").onblur = function() {
		ok = true;
		name = $("#regist_username").val().trim();
		if (name == "") {
			$("#warning_1").show();//显示元素
			$("#warning_1 span").html("用户名不为空");
			ok = false;
		}
	}
	get("regist_password").onblur = function() {
		ok = true;
		pwd = $("#regist_password").val().trim();
		if (pwd == "") {
			$("#warning_2").show();//显示元素
			$("#warning_2 span").html("密码不能为空");
			ok = false;
		}
		if (pwd.length < 4) {
			$("#warning_2").show();//显示元素
			ok = false;
		}
	}
	get("final_password").onblur = function() {
		ok = true;
		repwd = $("#final_password").val().trim();
		console.log(pwd+","+repwd);
		if (pwd != repwd) {
			$("#warning_3").show();//显示元素
			ok = false;
		}
	}

	//给注册按钮绑定点击处理
	$("#regist_button").click(function() {
		nick = $("#nickname").val().trim();
		console.log(name+","+pwd+","+nick);
		if (ok) {
			$.ajax({
				url : "http://localhost:8888/cloud_note/user/regist.do",
				type : "post",
				data : {"name" : name,"password" : pwd,"nick" : nick},
				dataType : "json",
				success : function(result) {
					if (result.status == 1) {
						//用户名重复，显示提示信息
						$("#warning_1").show();//显示元素
						$("#warning_1 span").html(result.msg);
					} else {
						alert(result.msg);
						$("#back").click();
					}
				},
				error : function() {
					alert("注册发生异常");
				}

			});
			$("#regist_username").val("");
			$("#regist_password").val("");
			$("#nickname").val("");
			$("#final_password").val("");
	
		}//if结束

	});

});