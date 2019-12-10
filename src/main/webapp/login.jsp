<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form method="post" action="/video_start/upload_aa" enctype="multipart/form-data">    
		<div class="form-group" align="center">
			        文件上传:<input type="file" class="form-control" name="file"id="file">         
					上传地址:<select name="shipin">
							<option value="/video/">video视频文件</option>
						  </select>                    
			<input type="submit" value="上传">        
		</div>       
	</form>
</body>
</html>