//显示笔记本列表
function getbooklist(){
	$.ajax({
		url : "http://localhost:8888/cloud_note/noteBook/noteBooks.do",
		type : "post",
		data : {"user_id" : user_id},
		dataType : "json",
		success : function(result) {
			if (result.status == 0) {
				var noteBooks = result.data;
				for ( var i = 0; i < noteBooks.length; i++) {
					var noteBook = noteBooks[i];
					var bookName = noteBook.cn_notebook_name;
					var bookId = noteBook.cn_notebook_id;
					var li_str = "<li class='online'>"
							+ "<a>"
							+ "<i class='fa fa-book' title='online' rel='tooltip-bottom'></i>"
							+ bookName 
							+ "<button type='button' class='close' data-dismiss='modal' style='display:none'>×</button>"
							+"</a></li>";
					//将bookId绑定到li元素上
					var $li = $(li_str);//将li_str字符串转成jQuery对象
					$li.attr('notebook_id',bookId);
					$("#contacts-list1").append($li);

				}//for循环结束							

			}//if(result.status==0)判断结束

		}//回调函数结束

	});//ajax请求结束
}

//弹出添加笔记本对话框
function addBookWindow(){
	//显示背景色
	$(".opacity_bg").show();
	//$(".opacity_bg").get(0).style.display="block";
	$("#can").load("alert/alert_notebook.html");
}

//关闭添加笔记本对话框
function closeWindow(){
	//取消背景色
	$(".opacity_bg").hide();
	//清空对话框div
	$("#can").empty();
}

function addBook(){
	var book_name=$("#input_notebook").val();
	console.log(book_name);
	if(book_name==""){
		alert("请填写笔记本名称");
	}else{
		$.ajax({
			url:"http://localhost:8888/cloud_note/noteBook/addBook.do",
			type:"post",
			data:{"user_id":user_id,"notebook_name":book_name},
			dataType:"json",
			success:function(result){
				if(result.status==0){//添加成功
				var notebook_id=result.data;
				var li_str = "<li class='online'>"
					+ "<a>"
					+ "<i class='fa fa-book' title='online' rel='tooltip-bottom'></i>"
					+ book_name + "</a>" + "</li>";
				//将bookId绑定到li元素上
				var $li = $(li_str);//将li_str字符串转成jQuery对象
				$li.attr('notebook_id',notebook_id);
				$("#contacts-list1").prepend($li);
					
				closeWindow();//关闭弹窗
				}else{
					alert(result.msg);
				}
			}
			
		});//请求结束
	}

	
}
//弹出重命名笔记本
function renamenotebook(){
	//显示背景色
	$(".opacity_bg").show();
	//$(".opacity_bg").get(0).style.display="block";
	$("#can").load("alert/alert_rename.html");
	
}
//重命名笔记本
function rename(){
	var book_name=$("#input_notebook_rename").val();
	var $li=$("#contacts-list1 li a.checked").parent();
	var book_id=$li.attr("notebook_id");
	console.log(book_name);
	$.ajax({
		url:"http://localhost:8888/cloud_note/noteBook/renamebook.do",
		type:"post",
		data:{"user_id":user_id,"book_id":book_id,"book_name":book_name},
		dataType:"json",
		success:function(result){
			if(result.status==0){
				//修改li的标题
				var str = "<i class='fa fa-book' title='online' rel='tooltip-bottom'></i>"+book_name;
				$("#contacts-list1 li a.checked").html(str);
				
				closeWindow();
					
			}
		}
	
	});//请求结束

	
}



