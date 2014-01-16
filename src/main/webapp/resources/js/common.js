/* author: Sucheng*/

function setmain(title, href) {
	$('#content-title strong').text("位置：" + title);
	// console.log($('#content-title strong').html());
	jQuery.ajax({
		url : href,
		type : 'get',
		cache : false,
		beforeSend : function(data) {
			setLiVal(1);
		},
		success : function(response) {
			console.log(response);
			$('#content-main').html(response);
			setLiVal(0);
			// $("#sidebar").prop('disabled', false);
		},
		error : function(response) {
			alert("网络异常：" + response);
			setLiVal(0);
		},
	});
}

function openTab(title, href) {
	if ($('#systabs').tabs('exists', title)) {
		$('#systabs').tabs('select', title);
	} else {
		$('#systabs').tabs('add', {
			title : title,
			href : href,
			border : false,
			fit : true,
			closable : true
		});
	}
	return true;
}
// 弹出窗口
function showWindow(options) {
	jQuery("#MyPopWindow").window(options);
}
// 关闭弹出窗口
function closeWindow() {
	// $("#MyPopWindow").window('destroy');
	$("#MyPopWindow").window('close');
}