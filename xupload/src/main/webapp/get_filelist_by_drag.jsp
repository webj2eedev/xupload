<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8"/>
	<title>WebJ2EE FileAPI</title>
	<script type="text/javascript" src="./jquery-1.12.4.js"></script>
</head>
<body>
	<textarea id="myFileDrop" rows="10"></textarea>

	<script type="text/javascript">
		$("#myFileDrop").bind("drop", function(e) {
			// 阻止冒泡、阻止浏览器默认行为(浏览器默认会自动打开拖拽的文件)
			e.stopPropagation();
			e.preventDefault();

			// 通过 e.originalEvent.dataTransfer.files 获取拖拽进来的 FileList
			// (注: 经jquery包装的事件对象e中不包含dataTransfer对象，
			//      所以需要通过e.originalEvent访问浏览器的原始事件对象)
			var fileMsg = "";
			var fileList = e.originalEvent.dataTransfer.files;
			for(var i=0; i<fileList.length; i++){
				var file = fileList[i];
				fileMsg += file.name + ": " + file.size/1024 + "KB;\n";
			}

			// 将组装后的文件信息展示在textarea组件中
			$(this).val(fileMsg);
		});
	</script>
</body>
</html>