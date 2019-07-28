<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html><head>
	<script type="text/javascript" src="./jquery-1.12.4.js"></script>
</head><body>
	<form>
		<input id="myfile" type="file" multiple></input>
	</form>

	<script type="text/javascript">
		$("#myfile").bind("change", function(e){
			var fileList = this.files;
			console.dir(fileList);

			for(var i=0; i<fileList.length; i++){
				var file = fileList[i];
				console.log(file.name + ": " + file.size/1024 + "KB;");
			}
		});	  
	</script>
</body></html>