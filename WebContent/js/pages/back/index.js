$(function() {
	$(createNewsBut).on("click",function() {
		$.post("admin/news/create.action",{},function(data){
			operateAlert(true , "公告静态数据创建成功！" ,"") ;
		},"text") ;
	}) ;
	
	$(createBespeakBut).on("click",function() {
		$.post("admin/dict/create.action",{},function(data){
			operateAlert(true , "报名数据创建成功！" ,"报名数据创建失败！") ;
		},"text") ;
	}) ;
})