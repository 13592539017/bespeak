$(function(){
	$("input[id*=statusBut-]").each(function(){
		var mid = this.id.split("-") [1] ;
		var status = this.id.split("-") [2] ;
		// console.log("mid = " + mid + "、status = " + status) ;
		$(this).on("click",function(){
			if (window.confirm("您确定要修改该用户状态吗？")) {
				var statusValue = status == "lock" ? 0 : 1 ;
				// console.log(statusValue) ;
				operateAlert(true , "用户状态修改成功！","用户状态修改失败！") ;
			}
		}) ;
	}) ;
	$("input[id*=changeBut-]").each(function(){
		var mid = this.id.split("-") [1] ;
		// console.log("mid = " + mid) ;
		$(this).on("click",function(){
			$(midTitleSpan).text(mid) ;
			$(cpmid).val(mid) ;
			$(memberPasswordInfo).modal("toggle") ;
		}) ;
	}) ;
	$("#myform").validate({
		debug : true, // 取消表单的提交操作
		submitHandler : function(form) {
			console.log("mid = " + $(cpmid).val() + "，password = " + $(password).val()) ;
			$(memberPasswordInfo).modal("toggle") ;
			operateAlert(true , "用户登录密码修改成功！","用户登录密码修改失败！") ;
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
			"password" : { 
				required : true
			} 
		}
	});
})