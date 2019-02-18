<%@ page pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jspf/directive/page.jspf" %>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf" %>

<html>

<c:set var="title" value="Menu" scope="page" />
<%@ include file="/WEB-INF/jspf/head.jspf" %>

<body>
<fmt:message key="settings_jsp.label.localization.value" var="localization_value" />
<div id="pdf">
<table id="main-container">
		
		<%@ include file="/WEB-INF/jspf/header.jspf" %>
		    
			<tr>
			<td class="content">			
			<%-- CONTENT --%>
			<c:choose>
			    <c:when test="${fn:length(usersList) == 0}">No such orders</c:when>
	  	        <c:otherwise>
	  	     		<form id="form_create_card" action="controller" method="post">
					    <input type="hidden" name="command" value="AdminCreateCard" />
                        <input type="hidden" name="name_button" value="button_create_card" />
					    <div>
						    <p>
							    <fmt:message key="resource_jsp.users"/>: 
						    </p>
						    <select name="users_to_set">
							    <c:forEach var="bean" items="${usersList}">
							        <c:if test = "${bean.id != '0'}">
							   	        <c:if test = "${localization_value == 'en'}">
				                           <option value="${bean.id}"> ${bean.firstName} ${bean.lastName}</option>
				                        </c:if>
				                        <c:if test = "${localization_value == 'ru'}">
                                            <option value="${bean.id}"> ${bean.firstNameRu} ${bean.lastNameRu}</option> 
				                        </c:if>
							        </c:if>						
							    </c:forEach>
						    </select>
					   </div>
					   <div>
						   <p>
						       <c:if test = "${localization_value == 'ru'}">
				                   Номер
				               </c:if>
				               <c:if test = "${localization_value == 'en'}">
				                   Number
				               </c:if>
						   </p>
						   <input name="number_card" required pattern="^[0-9]+$">
					   </div>
					   <div>
					      <p>
						       <c:if test = "${localization_value == 'ru'}">
				                   Имя карты (eng)
				               </c:if>
				               <c:if test = "${localization_value == 'en'}">
				                   Name of Card (eng)
				               </c:if>
						  </p>
						  <input name="name_card" required pattern="^[0-9a-zA-Z\s]+$">
					   </div>
					   <div>
						    <p>
						        <c:if test = "${localization_value == 'ru'}">
				                    Баланс
				                </c:if>
				                <c:if test = "${localization_value == 'en'}">
				                    Balance
				                </c:if>
						    </p>
						    <input name="balance_card" required pattern="^[0-9]+$">
					   </div>
                       <p>
				       <c:if test = "${localization_value == 'ru'}">
				            <input type="submit" value='создать'/>
                       </c:if>
				       <c:if test = "${localization_value == 'en'}">
				            <input type="submit" value='create'/>
				       </c:if>
				       </p>
				    </form> 
			    </c:otherwise>
			</c:choose>
		</td>
			
		</tr>
		
		<%@ include file="/WEB-INF/jspf/footer.jspf" %>
		
	</table>
</div>
</body>
