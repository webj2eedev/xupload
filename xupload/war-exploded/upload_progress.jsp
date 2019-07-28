<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE html><html><head>
	<meta charset="UTF-8"/>
	<script type="text/javascript" src="./jquery-1.12.4.js"></script>
	<style> *{ font-family:"Consolas"; } </style>
</head><body>
	<form>
		<div>select a file to upload.</div><br/>
		<div><input type="file" id="myfile" onchange="onFileSelected()"/></div><br/>
		<fieldset>
			<legend>FileInfo:</legend>
			fileName: <span id="fileName"></span><br/>
			fileSize: <span id="fileSize"></span><br/>
			fileType: <span id="fileType"></span><br/>
			progress: <span id="progress"></span><br/>
		</fieldset><br/>
		<input type="button" onclick="uploadFile()" value="Upload"/>
	</form>
	<script type="text/javascript">
		function onFileSelected() {
			// file的非multiple模式，FileList中只会有一个元素；
			var file = document.getElementById('myfile').files[0]; 
        
			// 展示fileName、fileSize、fileType
			$('#fileName').text(file.name);
			$('#fileSize').text(Math.round(file.size / 1024) + 'KB');
			$('#fileType').text(file.type);
			$('#progress').text("0%");
        }
      
      function uploadFile() {
    	  // 1. 获取用户选取的文件
    	  var file = document.getElementById('myfile').files[0];
    	  
    	  // 2. 用FormData组件要发送的表单数据（文件）
    	  var fd = new FormData();
    	  fd.append("myfile", file);
    	  
    	  // 3. 使用XMLHttpRequest发送请求 
    	  var xhr = new XMLHttpRequest();
    	  xhr.upload.addEventListener("progress", function(e) {
    		  let computable = e.lengthComputable; // 进度是否可计算
    		  if( computable ){
    			  let loaded = e.loaded; // 已上传量
    			  let total = e.total; // 数据总量
    			  $('#progress').text( Math.round(loaded / total * 100) + '%');
    		  }else {
    			  $('#progress').text( 'unable to compute progress!');
    		  }
    	  }, false);
    	  
    	  xhr.upload.addEventListener("load", function(e) { // 传输完成
    		  console.log("Transfer Complete!");
          }, false);
    	  
    	  xhr.addEventListener("load", function(e) { // 请求完成
    		  console.log(xhr.responseText);
          }, false);
    	  
    	  xhr.open("POST", "./apacherecvfile");
    	  xhr.send(fd);
      }
	</script>
</body></html>