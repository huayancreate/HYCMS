$(document).ready(function() {

	/* check whether backup is exists */
	$.post("ServletBackupStatus", {
				parameter1 : ""
			}, function(result) {
				result = $.trim(result);
				if (result == '1') {
					$('#timingStatus').text('（已设置定时备份）');
				}
			});

	/* dialog initialized */
	$('#dlg').dialog('close');
	/* insert loading image */
	$('#dlg').find("p").eq(1)
			.html("<img class=\"loading\" src=\"r/cms/p.gif\" />");

	$('#time').timespinner('setValue', '01:00');

	$('#week').combotree('setValue', 'Su');

	/* backup attachment */
	$("#backupAtt").click(function() {

				/* display dialog */
				$('#dlg').find("p").eq(0).text("正在备份附件，请稍后。。。。。。");
				$('#dlg').dialog({
							modal : true
						});

				$.post("ServletAttachmentBackup", {
							parameter1 : ""
						}, function(result) {
							result = $.trim(result);
							$('#dlg').dialog('close');
							$.messager.alert('提示', result + '', 'info');
						});
			});

	/* backup database */
	$("#backup").click(function() {

				/* display dialog */
				$('#dlg').find("p").eq(0).text("正在备份数据库，请稍后。。。。。。");
				$('#dlg').dialog({
							modal : true
						});

				$.post("ServletOracleBackup", {}, function(result) {
					result = $.trim(result);

					/* close dialog */
					$('#dlg').dialog('close');

					if (result == "1") {
						$.messager.alert('洗具啊', '备份成功了', 'info');
					} else {
						$.messager.alert('杯具', '备份失败了', 'error');
					}
						// alert(result);
					});

			});

	// $("#backupTimer").click(function() {
	// var v = $('#week').combotree('getValues');
	// if (v == "") {
	// $.messager.alert('警告', '请选择重复执行的星期!', 'warning');
	// return;
	// }
	// alert(v);
	// });

	/* set timing backup */
	$("#set").click(function() {
		// alert("dddddddddd");

		/* get week */
		var w = $('#week').combotree('getValues');
		/* get time */
		var time = $('#time').timespinner('getValue');
		if (w == '') {
			$.messager.alert('杯具', '请选择定时备份的星期', 'error');
			return;
		}
		if (time == '') {
			$.messager.alert('杯具', '请选择定时备份的时间', 'error');
			return;
		}

		var status = $.trim($('#timingStatus').text());

		/* if backup is exists already */
		if (status != '') {
			$.messager.confirm('提示', '定时备份已经存在，是否重新来过?', function(r) {
				/* if yes button is clicked */
				if (r) {
					// alert('confirmed: ' + r);

					/*
					 * post parameters to servlet ServletBackupTimer to validate
					 */
					$.post("ServletTimingBackup", {
								week : '' + w,
								time : '' + time
							}, function(result) {
								result = $.trim(result);
								if (result == '设置定时备份成功了') {
									$('#timingStatus').text('（已设置定时备份）');
									$.messager.alert('提示', '重新设置定时备份成功了', 'info')
								} else {
									$.messager.alert('提示', '' + result, 'info');
								}
							});
				}
			});
		} else {

			/*
			 * post parameters to servlet ServletBackupTimer to validate
			 */
			$.post("ServletTimingBackup", {
						week : '' + w,
						time : '' + time
					}, function(result) {
						result = $.trim(result);
						$.messager.alert('提示', '' + result, 'info');
						if (result == '设置定时备份成功了') {
							$('#timingStatus').text('（已设置定时备份）');
						}
					});

		}
			// alert(w);
			// alert(time);
	});

});

// js字符串转换成unicode字符串
var decToHex = function(str) {
	var res = [];
	for (var i = 0; i < str.length; i++)
		res[i] = ("00" + str.charCodeAt(i).toString(16)).slice(-4);
	return "\\u" + res.join("\\u");
}

// $.post("ServletOracleBackup", {
// filePath : decToHex("WEB-INF/backup/database/2013年05月12日13时05分22秒522毫秒.dmp")
// }, function(result) {
// alert(result);
// });
