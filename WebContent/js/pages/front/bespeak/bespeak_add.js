$(function(){
	$("#imageCode").on("click",function(){
		$("#imageCode").attr("src","captcha.jpg?p=" + Math.random()) ;
	}) ;
	$("#bedate").on("click",function(){
		laydate({istime: true, format: 'YYYY-MM-DD'})
	});
	$("#myform").validate({
		debug : true, // 取消表单的提交操作
		submitHandler : function(form) {
			form.submit(); // 提交表单
		},
		errorPlacement : function(error, element) {
			$("#" + $(element).attr("id").replace(".", "\\.") + "Msg").append(error);
		},
		highlight : function(element, errorClass) {
			$(element).fadeOut(1,function() {
				$(element).fadeIn(1, function() {
					$("#" + $(element).attr("id").replace(".","\\.") + "Div").attr("class","form-group has-error");
				});

			})
		},
		unhighlight : function(element, errorClass) {
			$(element).fadeOut(1,function() {
				$(element).fadeIn(1,function() {
						$("#" + $(element).attr("id").replace(".","\\.") + "Div").attr("class","form-group has-success");
				});
			})
		},
		errorClass : "text-danger",
		rules : {
			"name" : {
				required : true 
			},
			"phone" : {
				required : true ,
				number : true
			},
			"school" : {
				required : true 
			},
			"edu" : {
				required : true 
			},
			"type" : {
				required : true 
			},
			"src" : {
				required : true 
			},
			"bedate" : {  
				required : true ,
				dateISO : true
			} ,
			"code" : {
				required : true ,
				/*remote : {
					url : "CheckCode", // 后台处理程序
					type : "post", // 数据发送方式
					dataType : "html", // 接受数据格式
					data : { // 要传递的数据
						code : function() {
							return $("#code").val();
						}
					},
					dataFilter : function(data, type) {
						if (data.trim() == "true") {
							return true;
						} else { 
							return false;
						}
					}
				}
				*/
			}
		}
	});
})