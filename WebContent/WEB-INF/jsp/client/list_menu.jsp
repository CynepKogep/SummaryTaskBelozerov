<%@ page pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jspf/directive/page.jspf" %>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf" %>

<html>

<c:set var="title" value="Menu" scope="page" />
<%@ include file="/WEB-INF/jspf/head.jspf" %>

<body>
	<table id="main-container">
			
		<%@ include file="/WEB-INF/jspf/header.jspf" %>
			
		<tr>
			<td class="content">			
			<%-- CONTENT --%>
			
			<form id="make_order" action="controller">
				<input type="hidden" name="command" value="makeOrder"/>
				<input type="submit" value='<fmt:message key="list_menu_jsp.button.make_an_order"/>'/>
				
				<table id="list_menu_table">
					<thead>
						<tr>
							<td>№</td>
							<td><fmt:message key="list_menu_jsp.table.header.name"/></td>
							<td><fmt:message key="list_menu_jsp.table.header.price"/></td>
							<td><fmt:message key="list_menu_jsp.table.header.order"/></td>
						</tr>
					</thead>
	
					<c:set var="k" value="0"/>
					<c:forEach var="item" items="${menuItems}">
						<c:set var="k" value="${k+1}"/> 
						<tr>
							<td><c:out value="${k}"/></td>
							<td>${item.name}</td>
							<td>${item.price}</td>
							<td><input type="checkbox" name="itemId" value="${item.id}"/></td>
						</tr>
					</c:forEach>
				</table>
			
			</form>
			
			<%-- CONTENT --%>
			</td>
		</tr>
		
		<%@ include file="/WEB-INF/jspf/footer.jspf" %>
		
	</table>
</body>
