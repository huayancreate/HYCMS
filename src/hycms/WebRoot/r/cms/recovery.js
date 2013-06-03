$(document).ready(function() {

	/* 自定义按钮上的文字 */
	$.extend($.messager.defaults, {
				ok : "确定",
				cancel : "取消"
			});

	// $('#cc').onSelect(function(){
	// alert("dddddddddd");
	// });

	/* dialog initialized */
	$('#dlg').dialog('close');
	/* insert loading image */
	$('#dlg').find("p").eq(1)
			.html("<img class=\"loading\" src=\"r/cms/p.gif\" />");

	/* recovery data button */
	$("#set").click(function() {
		// alert("dddd");
		var val = $('#cc').combotree('getValue');
		// alert(val);
		if (val == "") {
			$.messager.alert('杯具啊', '请选择备份文件哦！！！！', 'warning');
			return;
		}

		/* delete blank space at the beginning and end */
		val = $.trim(val);

		if (val == "1" || val == "2") {
			$.messager.alert('杯具啊', '请选择备份文件，而不是文件夹哦！！！！', 'warning');
			return;
		}

		if (val.endsWith('.dmp')) {
			// alert("dddddddddddddd");

			/* display dialog */
			$('#dlg').find("p").eq(0).text("正在恢复数据库，请稍后。。。。。。");
			$('#dlg').dialog({
						modal : true
					});

			$.post("ServletOracleRecovery", {
						filePath : /* unicode string */decToHex(val)
					}, function(result) {

						/* close dialog */
						$('#dlg').dialog('close');

						result = $.trim(result);
						if (result == '2') {
							$.messager.alert('杯具啊', '该数据库备份文件不存在哦！！！！',
									'warning');
						} else if (result == '1') {
							$.messager.alert('洗具啊', '数据库恢复成功了', 'info');
						} else if (result == '3') {
							$.messager.alert('杯具啊', '数据库恢复失败了！！！！', 'warning');
						}
					});
			return;
		}

		/* if file is zip type file */
		if (val.endsWith('.zip')) {
			// alert("xxxxxxxxxx");

			/* display dialog */
			$('#dlg').find("p").eq(0).text("正在恢复附件，请稍后。。。。。。");
			$('#dlg').dialog({
						modal : true
					});

			$.post("ServletAttachmentRecovery", {
						name : /* parameter name unicode string */""
								+ decToHex(val)
					}, function(result) {

						/* close dialog */
						$('#dlg').dialog('close');

						result = $.trim(result);

						if (result == "1") {
							$.messager.alert('杯具啊', '参数错误！！！！', 'warning');
						} else if (result == "2") {
							$.messager.alert('杯具啊', '文件不存在！！！！', 'warning');
						} else if (result == "3") {
							$.messager.alert('杯具啊', 'winrar程序不存在，请联系系统管理员！！！！',
									'warning');
						} else if (result == "4") {
							$.messager.alert('洗具啊', '附件恢复成功了！！！！', 'info');
						} else if (result == "5") {
							$.messager.alert('杯具啊', '附件恢复失败了！！！！', 'warning');
						}
					});

			return;
		}

		$.messager.alert('杯具啊', '请选择合适的备份文件哦！！！！', 'warning');

	});/* recovery data button end */

	/* refresh backup files list begin */
	$("#refresh").click(function() {
				// alert("ddddddddddd");

				$('#cc')
						.combotree('reload', 'ServletJsonDisplayAllBackupFiles');

				$.messager.alert('洗具啊', '刷新成功了！！！！', 'info');

			});/* refresh backup files list end */

	/* 设置只能选择子节点 */
	$('#cc').combotree({
				// 获取数据URL url:
				// '@Url.Action("GetGoodsTypeTree",
				// "GoodsType")', //选择树节点触发事件
				onSelect : function(node) {
					// 返回树对象
					var tree = $(this).tree;
					// 选中的节点是否为叶子节点,如果不是叶子节点,清除选中
					var isLeaf = tree('isLeaf', node.target);
					if (!isLeaf) {
						$('#cc').combotree('clear');
//						$.messager.alert('杯具啊', '请选择文件！！！！', 'warning');
						// return false;
					}
				}
			});

});

/* js字符串转换成unicode字符串 */
var decToHex = function(str) {
	var res = [];
	for (var i = 0; i < str.length; i++)
		res[i] = ("00" + str.charCodeAt(i).toString(16)).slice(-4);
	return "\\u" + res.join("\\u");
}