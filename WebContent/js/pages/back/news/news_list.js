$(function(){
	$(selall).on("click",function(){
		checkboxSelectAll("nid",this.checked) ;
	}) ;
	$(removeBtn).on("click",function(){
		operateChecked("nid","/pages/back/emp") ;
	}) ;
})