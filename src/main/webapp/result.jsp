<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
<title>上传视频结果</title>
</head>
<body>
	<div class="panel panel-default">
		<div class="panel-body" align="center">
			<div class="panel-heading">
				<h1 class="sub-header h3">上传结果</h1>
			</div>


			<div class="row-fluid">${result}</div>
			<hr>
			<div class="row-fluid">
				<div class="col-lg-12">
					<div class="table-responsive">


						<table class="table table-hover">
							
								<tr>
									<th>文件名称</th>
									<th>文件大小</th>
									<th>文件类型</th>
									<th>文件路径</th>
									<th>上传时间</th>
									<th>操作</th>
								</tr>
						
							<c:forEach items="${entity}" var="list">
							
								<tr>
									<td>${list.titleOrig}</td>
									<td>${list.size}</td>
									<td>${list.type}</td>
									<td>${list.path}</td>
									<td>${list.uploadTime}</td>
									<td><button onclick="play('${list.path}')">播放</button></td>
								</tr>
							</c:forEach>
						</table>
					</div>
				</div>
			</div>
			<div id="a1"></div>
		</div>
	</div>
</body>
<script type="text/javascript" src="${contextPath}/js/ckplayer/ckplayer.js" charset="utf-8"></script>
<script type="text/javascript" src="${contextPath}/js/jquery-1.12.4.min.js"></script>
<script type="text/javascript">
	function play(path) {
		var flashvars = {
			f : '${contextPath}/'+path, //项目的相对路径WebContent下
			c : 0,
			s : 0,
			p : 1,
			code:'gbk2312'
		};
		
		var video = ['${contextPath}/'+path+'->video/mp4'];
		CKobject.embed('${contextPath}/js/ckplayer/ckplayer.swf', 'a1', 'ckplayer_a1',
				'600', '400',false, flashvars, video);
	}
</script>

</html>