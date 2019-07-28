<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8"/>
	<title>WebJ2EE FileAPI</title>
	<script type="text/javascript" src="./jquery-1.12.4.js"></script>
</head>
<body>
	
	<input id="myfile" type="file" accept="image/*" multiple></input>

	<div id="previewArea"></div>

	<script type="text/javascript">
		$("#myfile").bind("change", function(e){
			
			let fileList = this.files; // 获取用户选择的文件列表；
			
			for(let file of fileList ){ // 为每个文件，生成一张预览图；
				
				let reader = new FileReader(); // 构建一个FileReader实例；

				// FileReader是异步读取数据，注册onload事件，监听文件读取进度；
				reader.addEventListener("load", function () {

					// FileReader完成读取后，onload事件被触发，
					// 属性 result 中包含换后的 Data URL 对象；
					let imageDataUrl = reader.result; 

					// 构造一个Image结构
				    let image = new Image();
			        image.src = imageDataUrl;
			      	
			        $("#previewArea").append( image );
				}, false);

		        reader.readAsDataURL(file); // 将图片转换为基于Base64的Data URL对象；
			}
		});	
	</script>
</body>
</html>