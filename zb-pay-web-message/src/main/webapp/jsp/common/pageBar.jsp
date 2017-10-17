<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!-- ==================================================================== -->
<!-- 【个人网站】：http://www.2b2b92b.com -->
<!-- 【网站源码】：http://git.oschina.net/zhoubang85/zb -->
<!-- 【技术论坛】：http://www.2b2b92b.cn -->
<!-- 【开源中国】：https://gitee.com/zhoubang85 -->

<!-- 【支付-微信_支付宝_银联】技术QQ群：470414533 -->
<!-- 【联系QQ】：842324724 -->
<!-- 【联系Email】：842324724@qq.com -->
<!-- ==================================================================== -->
<div class="panelBar">
	<div class="pages">
		<span>显示</span>
		<select theme="simple" cssClass="combox" name="numPerPage"
			value="${pageBean.numPerPage}" onchange="navTabPageBreak({numPerPage:this.value})">
			<c:forEach begin="15" end="100" step="5" varStatus="s">
				<option value="${s.index}" ${pageBean.numPerPage eq s.index ? 'selected="selected"' : ''}>${s.index}</option>
			</c:forEach>
		</select>
		<span> 条,共<a style="color: red">${pageBean.totalCount}</a>条, 共${pageBean.totalPage}页, 当前第${pageBean.currentPage}页 </span>
	</div>
	<div class="pagination" targetType="navTab"
		totalCount="${pageBean.totalCount}" numPerPage="${pageBean.numPerPage}"
		pageNumShown="10"
		currentPage="${pageBean.currentPage}"></div>
</div>