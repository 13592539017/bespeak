$(function(){
	$("a[id*=showBespeakInfo]").each(function() {
		var bid = this.id.split("-") [1] ;
		var name = $(this).text() ;
		$(this).on("click",function(){
			$(currBid).val(bid) ;
			$(nameTitleSpan).text(name) ;
			$(bespeakInfo).modal("toggle") ;
		}) ;
	}) ;
	$("#myform").validate({
		debug : true, // 取消表单的提交操作
		submitHandler : function(form) {
			console.log("bid = " + $(currBid).val() + "、newNote = " + $(newNote).val()) ;
			$(bespeakInfo).modal("toggle") ;
			operateAlert(true , "报名备注追加成功！","报名备注追加失败！") ;
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
			"newNote" : {
				required : true 
			}
		}
	});
})