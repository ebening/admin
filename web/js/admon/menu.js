var menuList = [];

$(function(){
	var url = $(location).attr('pathname');
	var menu = $("#menu").find("a[href='"+url+"']").each(function(index, o){
		activateMenu(this);
	});
	if(menuList.length>0){
		var breadcrumb = "";
		for(var i=menuList.length-1; i>=0; i--){
			var o = menuList[i];
			if(breadcrumb.length>0){
				breadcrumb += "&nbsp;&nbsp;&nbsp;>&nbsp;&nbsp;&nbsp;";
			}
			breadcrumb += o.name;
		}
		console.info(breadcrumb);
		if(breadcrumb.length>0){
			$("header.page-header > h2").html(breadcrumb);
		}
	}
});

function activateMenu(o){
	var menuObj = $(o);
	if(o.tagName!="A"){
		menuObj.addClass("active");
		var achild = menuObj.find("a:first");
		var href = achild.attr("href");
		var name = achild.find("span:first").text();
		menuList.push({
			name: name,
			url: typeof href != 'undefined'?href:''
		});
	}
	var classValue = menuObj.attr("class");
	if(typeof classValue != 'undefined' && classValue.indexOf('nav-parent')>-1){
		menuObj.addClass("nav-expanded");
	}
	var mParent = menuObj.parents("li");
	if(mParent.length>0){
		activateMenu(mParent[0]);
	}
}