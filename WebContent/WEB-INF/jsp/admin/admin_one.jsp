<%@ page pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jspf/directive/page.jspf" %>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf" %>

<html>

<c:set var="title" value="List orders" scope="page" />
<%@ include file="/WEB-INF/jspf/head.jspf" %>

<body>
	<table id="main-container">
		<%@ include file="/WEB-INF/jspf/header.jspf" %>
	
		<tr>
			<td class="content">
			<%-- CONTENT --%>

			<c:choose>
			    <c:when test="${fn:length(usersList) == 0}">No such orders</c:when>
	
			    <c:otherwise>
			        <fmt:message key="settings_jsp.label.localization.value" var="localization_value" />
			        <table id="list_order_table">
				        <thead>
					        <tr>
 						        <td>№</td> 
 						        <td><fmt:message key="resource_jsp.second_name"/></td>
 						        <td><fmt:message key="resource_jsp.first_name"/></td>
 						        <td><fmt:message key="resource_jsp.access"/></td>
 						        <td></td> 
					        </tr>
				        </thead>
                        <c:forEach var="bean" items="${usersList}">
					        <tr>
						        <td>${bean.id}</td> 
						        <td>
						            <c:if test = "${localization_value == 'ru'}">
						                ${bean.lastNameRu}
						            </c:if>
						            <c:if test = "${localization_value == 'en'}">
						                ${bean.lastName}
						            </c:if>
						        </td>
						        <td>
							        <c:if test = "${localization_value == 'ru'}">
						                ${bean.firstNameRu}
						            </c:if>
						            <c:if test = "${localization_value == 'en'}">
						                ${bean.firstName}
						            </c:if>
						        </td>
						        <td>
						            <c:if test = "${bean.accessesUsers == 'unlocked'}">
						                <fmt:message key="resource_jsp.unlocked"/>
						            </c:if>
						            <c:if test = "${bean.accessesUsers == 'blocked'}">
						                <fmt:message key="resource_jsp.blocked"/>
						            </c:if>
						        </td>
		
						        <td> 
						            <form id="make_order" action="controller">
				                        <c:if test = "${bean.accessesUsers == 'unlocked' && bean.roles == 'client'}">
				                            <input type="hidden" name="command" value="listOrdersA"/>
				                            <input type="hidden" name="id_user" value='${bean.id}' />
				                            <input type="hidden" name="accesses_users" value='${bean.accessesUsers}' />
				                            <fmt:message key="resource_jsp.block" var="block_button" />
				                            <div style="text-align:center">
				                                <input type="submit" value='${block_button}'/>
				                            </div>
				                        </c:if>
				                        <c:if test = "${bean.accessesUsers == 'blocked' && bean.roles == 'client'}">
				                            <input type="hidden" name="command" value="listOrdersA"/>
				                            <input type="hidden" name="id_user" value='${bean.id}' />
				                            <input type="hidden" name="accesses_users" value='${bean.accessesUsers}' />
   				                            <fmt:message key="resource_jsp.unlock" var="unlock_button" />
				                            <div style="text-align:center">  
				                                <input type="submit" value='${unlock_button}'/>
				                            </div>      
				                        </c:if>
				                    </form>
		                        </td>  
 		                    </tr>
                        </c:forEach>			
			        </table>
			    </c:otherwise>
			</c:choose>
			<%-- CONTENT --%>
			</td>
		</tr>
		<%@ include file="/WEB-INF/jspf/footer.jspf" %>
	</table>
</body>
</html>