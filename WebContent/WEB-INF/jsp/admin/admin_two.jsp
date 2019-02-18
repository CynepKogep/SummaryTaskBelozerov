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
			    <c:when test="${fn:length(cardsList) == 0}">No such orders</c:when>
	
			    <c:otherwise>
			        <fmt:message key="settings_jsp.label.localization.value" var="localization_value" />
			        <table id="list_order_table">
				        <thead>
					        <tr>
 						        <td><fmt:message key="resource_jsp.card_number"/></td> 
 						        <td><fmt:message key="resource_jsp.card_name"/></td>
 						        <td><fmt:message key="resource_jsp.card_balance"/></td>
 						        <td><fmt:message key="resource_jsp.second_name"/></td>
 						        <td><fmt:message key="resource_jsp.first_name"/></td>
 						        <td><fmt:message key="resource_jsp.access"/></td> 
 						        <td><fmt:message key="resource_jsp.request"/></td>
 						        <td></td> 
					        </tr>
				        </thead>
                        <c:forEach var="bean" items="${cardsList}">
					        <tr>
						        <td>${bean.numbers}</td> 
						        <td>${bean.name}</td> 
						        <td>${bean.balance}</td>
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
						            <c:if test = "${bean.accessesAccounts == 'unlocked'}">
						                <fmt:message key="resource_jsp.unlocked"/>
						            </c:if>
						            <c:if test = "${bean.accessesAccounts == 'blocked'}">
						                <fmt:message key="resource_jsp.blocked"/>
						            </c:if>
						        </td>
 						        <td>
						            <c:if test = "${bean.nameUnlockRequest == 'exists'}">
						                <fmt:message key="resource_jsp.unlock_request"/>
						            </c:if>
						            <c:if test = "${bean.nameUnlockRequest == 'doesnotexist'}">
						                <div style="text-align:center">
						                -
						                </div>
						            </c:if>
						        </td>
						        <td> 
						            <form id="make_order" action="controller">
				                        <c:if test = "${bean.accessesAccounts == 'unlocked'}">
				                            <input type="hidden" name="command" value="cardsAdminCommand"/>
				                            <input type="hidden" name="id_card" value='${bean.id}' />
				                            <input type="hidden" name="accesses_card" value='${bean.accessesAccounts}' />
				                            <input type="hidden" name="unlock_request_card" value='${bean.nameUnlockRequest}' />
				                            <fmt:message key="resource_jsp.block" var="block_button" />
				                            <div style="text-align:center">
				                                <input type="submit" value='${block_button}'/>
				                            </div>
				                        </c:if>
				                        <!-- если состояние карты 'заблокированно'(accessesAccounts = 'blocked' = 1), кнопка 'разблокирует'  -->
				                        <c:if test = "${bean.accessesAccounts == 'blocked'}">
				                            <input type="hidden" name="command" value="cardsAdminCommand"/>
				                            <input type="hidden" name="id_card" value='${bean.id}' />
				                            <input type="hidden" name="accesses_card" value='${bean.accessesAccounts}' /> <!-- accessesAccounts = 1 -->
				                            <input type="hidden" name="unlock_request_card" value='${bean.nameUnlockRequest}' />
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