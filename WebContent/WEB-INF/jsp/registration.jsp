<%@ page pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jspf/directive/page.jspf" %>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf" %>

<html>

<c:set var="title" value="Menu" scope="page" />
<%@ include file="/WEB-INF/jspf/head.jspf" %>

<body>
    <fmt:message key="settings_jsp.label.localization.value" var="localization_value" />

    <table id="main-container">
		<%@ include file="/WEB-INF/jspf/header.jspf" %>
			<tr>
			    <td class="content">			
			    <%-- CONTENT --%>
			        <c:if test = "${is_user == 'is'}">
			           	<c:if test = "${localization_value == 'ru'}">
				            Такой пользователь уже есть! 
				        </c:if>
				        <c:if test = "${localization_value == 'en'}">
				            This user already exists!
				        </c:if>
			        </c:if>
			        <c:choose>
			            <c:when test="${fn:length(usersList) == 0}">No such users</c:when>
	  	            <c:otherwise>
	  	     		    <form id="settings_form" action="controller" method="post">
					        <input type="hidden" name="command" value="registration" />
                            <input type="hidden" name="name_button" value="button_registration" />
 					        <!-- ---------------------------------------------------------------- -->
 					        <!-- Login -->
 					        <!-- ---------------------------------------------------------------- -->
 					        <div>
						        <p>
						            <c:if test = "${localization_value == 'ru'}">
				                        Логин
				                    </c:if>
				                    <c:if test = "${localization_value == 'en'}">
				                        Login
				                    </c:if>
						        </p>
						        <input name="login_registration" required pattern="^[0-9a-zA-Z]+$">
					        </div>
					        <!-- ---------------------------------------------------------------- -->
					        <!-- Password -->
					        <!-- ---------------------------------------------------------------- -->
					        <div>
						        <p>
						            <c:if test = "${localization_value == 'ru'}">
				                        Пароль
				                    </c:if>
				                    <c:if test = "${localization_value == 'en'}">
				                        Password
				                    </c:if>
						        </p>
						        <input name="password_registration" required pattern="^[0-9a-zA-Z]+$">
					        </div>
 					        <!-- ---------------------------------------------------------------- -->
 					        <!--  First Name (eng) -->
 					        <!-- ---------------------------------------------------------------- -->
 					        <div>
						        <p>
						            <c:if test = "${localization_value == 'ru'}">
				                        Имя (eng)
				                    </c:if>
				                    <c:if test = "${localization_value == 'en'}">
				                        First Name (eng)
				                    </c:if>
						        </p>
						        <input name="first_name_registration" required pattern="^[a-zA-Z]+$">
					        </div>
					        <!-- ---------------------------------------------------------------- -->
					        <!--  Last Name (eng) -->
					        <!-- ---------------------------------------------------------------- -->
					        <div>
						        <p>
						            <c:if test = "${localization_value == 'ru'}">
				                        Фамилия (eng)
				                    </c:if>
				                    <c:if test = "${localization_value == 'en'}">
				                        Last Name (eng)
				                    </c:if>
						       </p>
						       <input name="last_name_registration" required pattern="^[a-zA-Z]+$">
					        </div>
					        <!-- ---------------------------------------------------------------- -->
 					        <!--  First Name (rus) -->
 					        <!-- ---------------------------------------------------------------- -->
 					        <div>
						        <p>
						            <c:if test = "${localization_value == 'ru'}">
				                         Имя (rus)
				                    </c:if>
				                    <c:if test = "${localization_value == 'en'}">
				                         First Name (rus)
				                    </c:if>
						        </p>
						        <input name="first_name_ru_registration" required pattern="^[а-яА-Я]+$">
					        </div>
					        <!-- ---------------------------------------------------------------- -->
					        <!-- Last Name (rus) -->
					        <!-- ---------------------------------------------------------------- -->
					        <div>
						        <p>
						             <c:if test = "${localization_value == 'ru'}">
				                          Фамилия (rus)
				                     </c:if>
				                     <c:if test = "${localization_value == 'en'}">
				                          Last Name (rus)
				                     </c:if>
						        </p>
						        <input name="last_name_ru_registration" required pattern="^[а-яА-Я]+$">
					        </div>
					        <!-- ---------------------------------------------------------------- -->
					        <!-- submit -->
					        <!-- ---------------------------------------------------------------- -->
				            <c:if test = "${localization_value == 'ru'}">
				                <input type="submit" value='Регистрация'/>
				            </c:if>
				            <c:if test = "${localization_value == 'en'}">
				                <input type="submit" value='Registration'/>
				            </c:if>
                            <!-- ---------------------------------------------------------------- -->
				</form> 
			 </c:otherwise>
			
			</c:choose>

		</td>
			
		</tr>
		<%@ include file="/WEB-INF/jspf/footer.jspf" %>
		
	</table>
</body>