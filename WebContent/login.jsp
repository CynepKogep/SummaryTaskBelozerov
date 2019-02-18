<%@ include file="/WEB-INF/jspf/directive/page.jspf" %>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf" %>

<html>

<c:set var="title" value="Login" />
 <%@ include file="/WEB-INF/jspf/head.jspf" %>

<%-- <%@ include file="/WEB-INF/jspf/headerforregistration.jspf" %> --%>

	
<body>


<%--=========================================================================== 
Here we use a table layout.
Class page corresponds to the '.page' element in included CSS document.
===========================================================================--%> 
	<table id="main-container">
<%@ include file="/WEB-INF/jspf/headerforregistration.jspf" %>
<%--=========================================================================== 
This is the HEADER, containing a top menu.
header.jspf contains all necessary functionality for it.
Just included it in this JSP document.
===========================================================================--%> 

		<%-- HEADER --%>
		<%-- <%@ include file="/WEB-INF/jspf/header.jspf"%> --%>
		<%-- HEADER --%>

<%--=========================================================================== 
This is the CONTENT, containing the main part of the page.
Это CONTENT, содержащий основную часть страницы.
===========================================================================--%> 
		<tr >
			<td class="content center">
			<%-- CONTENT --%>
			
<%--=========================================================================== 
Defines the web form.
(Определяем веб-форму.)
===========================================================================--%> 
				<form id="login_form" action="controller" method="post">

<%--=========================================================================== 
Hidden field. In the query it will act as command=login.
The purpose of this to define the command name, which have to be executed 
after you submit current form. 
(Скрытое поле. В запросе он будет действовать как command = login.
Цель этого определения имени команды, которая должна быть выполнена
после отправки текущей формы.)
===========================================================================--%> 
<!-- 					<input type="hidden" name="command" value="login"/> -->
                    <input type="hidden" name="command" value="loginA"/>
					
					<fieldset >
						<legend>
						<%--
						<!-- переменная key="login_jsp.label.login"
						     находится в resources.properties ... // (текст для различных языков)  
						-->
						--%>
							<fmt:message key="resource_jsp.login"/>
						</legend>
						<input name="login"/><br/>
					</fieldset><br/>
					<fieldset>
						<legend>
							<fmt:message key="resource_jsp.password"/>
						</legend>
						<input type="password" name="password"/>
					</fieldset><br/>
					
					<input type="submit" value='<fmt:message key="resource_jsp.enter"/>'>								
				</form>
				
			<%-- CONTENT --%>

			</td>
		</tr>

		<%@ include file="/WEB-INF/jspf/footer.jspf"%>
		
	</table>
</body>
</html>