<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<jsp:include page="../header.jsp"/>
<jsp:include page="../nav.jsp"/>

<h2>Product Detail
<c:if test="${ses.email eq pvo.writer || ses.grade gt 100 }">
<a href="/prodCtrl/modify?pno=${pvo.pno }" class="btn btn-outline-warning">MOD</a>
<a href="/prodCtrl/remove?pno=${pvo.pno }" class="btn btn-outline-danger">DEL</a>
</c:if>
</h2>
<table class="table">
	<tr>
		<th>속성</th>
		<th>속성 값</th>
	</tr>
	<tr>
		<td>PNO</td>
		<td>${pvo.pno }</td>
	</tr>
	<tr>
		<td>Product Name</td>
		<td>${pvo.pname }</td>
	</tr>
	<tr>
		<td>Price</td>
		<td>${pvo.price }</td>
	</tr>
	<tr>
		<td>Writer</td>
		<td>${pvo.writer }</td>
	</tr>
	<tr>
		<td>Made By</td>
		<td>${pvo.madeBy }</td>
	</tr>
	<tr>
		<td>Reg At</td>
		<td>${pvo.regAt }</td>
	</tr>
	<tr>
		<td>Category</td>
		<td>${pvo.category }</td>
	</tr>
	<tr>
		<td>Description</td>
		<td>${pvo.description }</td>
	</tr>
	<tr>
		<td>Mod At</td>
		<td>${pvo.modAt }</td>
	</tr>
	<tr>
		<td>Read Count</td>
		<td>${pvo.readCount }</td>
	</tr>
	<tr>
		<td>Image File</td>
		<td>${pvo.imageFile }</td>
	</tr>
</table>

<jsp:include page="../footer.jsp"/>
