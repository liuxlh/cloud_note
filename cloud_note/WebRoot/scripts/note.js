//显示笔记列表
function getnotelist(){
	$("#contacts-list1").on("click","li",function(){//给jQuery对象中现有和未来与选择器匹配的元素绑定事件fn
			//清除笔记本列表下的所有的li对象下的a元素的class属性
			$("#contacts-list1 li a").removeClass("checked");
			//设置当前li为选中状态
			$(this).find("a").addClass("checked");//给当前li对象下的a元素添加class属性
			//点击li(笔记本)之前先清除笔记列表
			$("#contacts-list2 li").remove();
			//获取点击的li(笔记本)的notebook_id值
			var notebook_id = $(this).attr("notebook_id");
			//显示笔记列表
			$.ajax({
						url : "http://localhost:8888/cloud_note/note/notes.do",
						type : "post",
						data : {"notebook_id" : notebook_id},
						dataType : "json",
						success : function(result) {//服务器返回NoteResult类型的json对象
							var notes=result.data;//元素为Map<String,String>类型的列表，同样为json对象
							for ( var i = 0; i < notes.length; i++) {//循环列表，每一个元素是Map类型的json对象
								var note_id = notes[i].cn_note_id;
								var note_title = notes[i].cn_note_title;
								var s_li = '<li class="online">';
								s_li+= '<a>';
								s_li+= '<i class="fa fa-file-text-o" title="online" rel="tooltip-bottom"></i>'+note_title+'<button type="button" class="btn btn-default btn-xs btn_position btn_slide_down"><i class="fa fa-chevron-down"></i></button>';
								s_li+= '</a>';
								s_li+= '<div class="note_menu" tabindex="-1">';
								s_li+= '	<dl>';
								s_li+= '		<dt><button type="button" class="btn btn-default btn-xs btn_move" title="移动至..."><i class="fa fa-random"></i></button></dt>';
								s_li+= '		<dt><button type="button" class="btn btn-default btn-xs btn_share" title="分享"><i class="fa fa-sitemap"></i></button></dt>';
								s_li+= '		<dt><button type="button" class="btn btn-default btn-xs btn_delete" title="删除"><i class="fa fa-times"></i></button></dt>';
								s_li+= '	</dl>';
								s_li+= '</div>';
								s_li+= '</li>';
											
									//将note_id绑定到li元素上
									var $li1 = $(s_li);//将li_str字符串转成jQuery对象
									$li1.attr('note_id',note_id);	
									$("#contacts-list2").prepend($li1);

							}//for结束

					}
			});
	});
} 

//弹出添加笔记对话框
function addNoteWindow(){
	//显示背景色
	$(".opacity_bg").show();
	//$(".opacity_bg").get(0).style.display="block";
	$("#can").load("alert/alert_note.html");
}
//添加笔记
function addNote(){
	var $li=$("#contacts-list1 li a.checked").parent();
	var notebook_id=$li.attr("notebook_id");
	var note_title=$("#input_note").val();
	$.ajax({
		url:"http://localhost:8888/cloud_note/note/addNote.do",
		type:"post",
		data:{"user_id":user_id,"notebook_id":notebook_id,"note_title":note_title},
		dataType:"json",
		success:function(result){
			if(result.status==0){
				//追加笔记列表的li
				var note_id=result.data;
				var li_str2='';
		li_str2+=	'<li class="online">'
		li_str2+=			 '<a>' 
		li_str2+=			 '<i class="fa fa-file-text-o" title="online" rel="tooltip-bottom"></i>'
		li_str2+=			 note_title
		li_str2+=			 '<button type="button" class="btn btn-default btn-xs btn_position btn_slide_down">'
		li_str2+=			 '<i class="fa fa-chevron-down"></i></button> </a>'
		li_str2+=		 '<div class="note_menu" tabindex="-1">'
		li_str2+=			 '<dl><dt>'
		li_str2+=				 '<button type="button" class="btn btn-default btn-xs btn_move" title="移动至...">'
		li_str2+=					 '<i class="fa fa-random"></i> </button></dt>'
		li_str2+=					 '<dt><button type="button" class="btn btn-default btn-xs btn_share" title="分享">'
		li_str2+=						+'<i class="fa fa-sitemap"></i>'
		li_str2+=				  '</button></dt>'
		li_str2+=				 '<dt> <button type="button" class="btn btn-default btn-xs btn_delete" title="删除">'
		li_str2+=						+'<i class="fa fa-times"></i> </button>'
		li_str2+=				 '</dt></dl>'
		li_str2+=		 '</div></li>';
				var $li=$(li_str2);
				$li.attr('note_id',note_id);
				$("#contacts-list2").prepend($li);
				closeWindow();//关闭弹窗
			}else{
				alert(result.msg);
			}
		}
		
	});//请求结束
	
}
//获取笔记内容
function noteContent(){
	//清除笔记列表下的所有的li对象下的a元素的class属性
	$("#contacts-list2 li a").removeClass("checked");
	//设置当前li为选中状态
	$(this).find("a").addClass("checked");
	//获取选中的笔记ID
	var note_id=$(this).attr("note_id");
	console.log(note_id);
	$.ajax({
		url:"http://localhost:8888/cloud_note/note/noteContent.do",
		type:"post",
		data:{"note_id":note_id},
		dataType:"json",
		success:function(result){
			var note=result.data;
			var notetitle=note.cn_note_title;
			var notebody=note.cn_note_body;
			$("#input_note_title").val(notetitle);
			if(notebody!=null){
				um.setContent(notebody);
			}else{
				um.setContent("无内容，请添加...");
			}
			
		}
	});//请求结束
	
}
//保存笔记
function updatenote(){
	var note_title=$("#input_note_title").val();
	var note_body=um.getContent();
	var $li1=$("#contacts-list1 li a.checked").parent();
	var book_id=$li1.attr("notebook_id");
	
	var $li2=$("#contacts-list2 li a.checked").parent();
	var note_id=$li2.attr("note_id");
	$.ajax({
		url:"http://localhost:8888/cloud_note/note/updatenote.do",
		type:"post",
		data:{"cn_user_id":user_id,"cn_notebook_id":book_id,
		"cn_note_id":note_id,"cn_note_title":note_title,"cn_note_body":note_body},
		dataType:"json",
		success:function(result){
			if(result.status==0){
				//如果标题改变,修改笔记列表li的标题
			    var oldTitle = $("#contacts-list2 li a.checked").text().trim();
				if(oldTitle!=note_title){
					//修改li的标题
					var str = '<i class="fa fa-file-text-o" title="online" rel="tooltip-bottom"></i>'+note_title+'<button type="button" class="btn btn-default btn-xs btn_position btn_slide_down"><i class="fa fa-chevron-down"></i></button>';
					$("#contacts-list2 li a.checked").html(str);
				}
				//弹出提示
				alert(result.msg);
			}
		
		}
	
	});//请求结束
	
}

//删除笔记对话框
function showConfirmRemoveWindow(){
	$("#can").load("alert/alert_delete_note.html");
	$(".opacity_bg").show();
}

function removeNote(){
	var $li=$("#contacts-list2 li a.checked").parent();
	var note_id=$li.attr("note_id");
	$.ajax({
		url:"http://localhost:8888/cloud_note/note/remove.do",
		type:"post",
		data:{"note_id":note_id},
		dataType:"json",
		success:function(result){
			if(result.status==0){
				closeWindow();
				//将列表中li移去
				$li.remove();
				//提示删除成功
				alert(result.msg);
				$("#contacts-list2 li:first").click();
			}
		}
	
	});//请求结束 
	
}



