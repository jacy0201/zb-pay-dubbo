<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<!-- ==================================================================== -->
<!-- 【个人网站】：http://www.2b2b92b.com -->
<!-- 【网站源码】：http://git.oschina.net/zhoubang85/zb -->
<!-- 【技术论坛】：http://www.2b2b92b.cn -->
<!-- 【开源中国】：https://gitee.com/zhoubang85 -->

<!-- 【支付-微信_支付宝_银联】技术QQ群：470414533 -->
<!-- 【联系QQ】：842324724 -->
<!-- 【联系Email】：842324724@qq.com -->
<!-- ==================================================================== -->
<html xmlns="http://www.w3.org/1999/xhtml">
<jsp:include page="${baseURL }/common/taglib/taglib.jsp" />
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>支付系统管理后台</title>
<link rel="stylesheet" type="text/css" href="${baseURL }/resources/css/login.css" />
<script type="text/javascript" language="javascript" src="${baseURL }/common/js/login/login.js"></script>
</head>


<body id="loginFrame">
<div id="loginBox" style="margin-top: 200px;">
	<div id="loginBoxBody">
		<ul class="floatLeft">
			<li>
				<h4>支付系统管理后台</h4>
			</li>
			<form id=login action="${baseURL}/login/index" method="post">
				<li>
					<p>帐号:</p><input id="empNo" class="textInput" maxLength="150" size="30" type="text" name="empNo" value="admin">
				</li>
				<li>
					<p>密码:</p><input id="empPwd" class="textInput" maxLength="80" size="30" type="password" name="empPwd" value="admin">
				</li>
				<li class="highlight"><input id="loginBtn" onclick="this.blur();" value="登录" type="submit"></li>
				<li></li>
			</form>
		</ul>
		<div class="floatRight" style="text-align:center;"><br/>欢迎访问支付系统管理后台。<br/><br/><span style="font-weight: bold;">登录账号：admin <br/>登录密码：admin </span><br/></div>
		<br clear="all">
	</div>
	<div id="loginBoxFooter"></div>

</div>
</body>
</html>