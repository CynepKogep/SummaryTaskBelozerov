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
			<c:choose>
			    <c:when test="${fn:length(cardsList) == 0}">No such orders</c:when>
	  	        <c:otherwise>
	  	     		<form id="settings_form" action="controller" method="get">
					    <input type="hidden" name="command" value="ClientCreatePay" />
                        <input type="hidden" name="name_button" value="button_create" />
					<div>
						<p>
							<fmt:message key="resource_jsp.cards"/> 
							<%-- <fmt:message key="settings_jsp.label.localization"/> --%>
						</p>
						<select name="card_to_set">
<%-- 							<c:choose>
								<c:when test="${not empty defaultLocale}">
									<option value="">${defaultLocale}[Default]</option>
								</c:when>
								<c:otherwise>
									<option value=""/>
								</c:otherwise>
							</c:choose> --%>
														
							<c:forEach var="bean" items="${cardsList}">
								<option value="${bean.id}"> ${bean.numbers} ${bean.name}</option>							
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
						<input name="number_pay" required pattern="^[0-9]+$">
					</div>
					<div>
						<p>
						<c:if test = "${localization_value == 'ru'}">
				            Сумма
				        </c:if>
				        <c:if test = "${localization_value == 'en'}">
				            Sum
				        </c:if>
						</p>
						<input name="sum_pay" required pattern="^[0-9]+$">
					</div>
					
					
<%-- 					<div>
						<p>
							<fmt:message key="settings_jsp.label.first_name"/>
						</p>
						<input name="firstName">
					</div>
					
					<div>
						<p>
							<fmt:message key="settings_jsp.label.last_name"/>
						</p>
						<input name="lastName">
					</div>
 --%>				
 	
					<%-- <input type="submit" value='<fmt:message key="resource_jsp.update"/>'><br/> --%>
					
				        <c:if test = "${localization_value == 'ru'}">
				            <input type="submit" value='создать'/>
				        </c:if>
				        <c:if test = "${localization_value == 'en'}">
				            <input type="submit" value='create'/>
				        </c:if>
                    
				</form> 
			 </c:otherwise>
			
			</c:choose>

		</td>
			
		</tr>
		
		
			
<%-- 		<tr>
			<td class="content">			
			CONTENT
			
			<form id="make_order" action="controller">
				<input type="hidden" name="command" value="listClientPays"/>
				<input type="hidden" name="command_number" value="01_number"/>
				<c:if test = "${localization_value == 'ru'}">
				    <input type="submit" value='по номеру'/>
				</c:if>
				<c:if test = "${localization_value == 'en'}">
				    <input type="submit" value= 'sort number'/>
				</c:if>
            </form>
            <form id="make_order" action="controller">
				<input type="hidden" name="command" value="listClientPays"/>
				<input type="hidden" name="command_number" value="02_data"/>
				    <c:if test = "${localization_value == 'ru'}">
				        <input type="submit" value='по дате'/>
				    </c:if>
				    <c:if test = "${localization_value == 'en'}">
				        <input type="submit" value= 'sort date'/>
				    </c:if>
				
            </form>
            <form id="make_order" action="controller">
				<input type="hidden" name="command" value="listClientPays"/>
				<input type="hidden" name="command_number" value="03_data_desc"/>
				    <c:if test = "${localization_value == 'ru'}">
				        <input type="submit" value='по дате (desc)'/>
				    </c:if>
				    <c:if test = "${localization_value == 'en'}">
				        <input type="submit" value= 'sort date desc'/>
				    </c:if>
            </form>
            
            				
			<c:choose>
			    <c:when test="${fn:length(paysList) == 0}">No such orders</c:when>
	
			    <c:otherwise>
			        <fmt:message key="settings_jsp.label.localization.value" var="localization_value" />
			        <table id="list_order_table">
				        <thead>
					        <tr>
 						        <td><fmt:message key="resource_jsp.card_number"/></td> 
 						        
 						        <td>
 						            <c:if test = "${localization_value == 'ru'}">
						                Дата
						            </c:if>
						            <c:if test = "${localization_value == 'en'}">
						                Date
						            </c:if>
 						        </td>
 						        <td>
 						            <c:if test = "${localization_value == 'ru'}">
						                Сумма
						            </c:if>
						            <c:if test = "${localization_value == 'en'}">
						                Sum
						            </c:if>
 						        </td>
 						        <td><fmt:message key="resource_jsp.card_name"/></td>
 						        <td><fmt:message key="resource_jsp.second_name"/></td>
 						        <td><fmt:message key="resource_jsp.first_name"/></td> 
 						        <td><fmt:message key="resource_jsp.request"/></td>
 						        <td>
 						            <c:if test = "${localization_value == 'ru'}">
						                Действие
						            </c:if>
						            <c:if test = "${localization_value == 'en'}">
						                Action
						            </c:if>
 						        </td> 
					        </tr>
				        </thead>
                        <c:forEach var="bean" items="${paysList}">
					        <tr>
						        <td>${bean.numbers}</td> 
						        <td>${bean.datas}</td>
						        <td>${bean.sums}</td>
						        <td>${bean.creditAccountName}</td>
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
                                <!-- статус -->
						        <td>
						            <c:if test = "${localization_value == 'ru'}">
						                <c:if test = "${bean.nameStatusPay == 'prepared'}">
						                     подготовленный
						                </c:if>
						                <c:if test = "${bean.nameStatusPay == 'sent'}">
						                     отправленный
						                </c:if>
						            </c:if>
						            <c:if test = "${localization_value == 'en'}">
						                ${bean.nameStatusPay}
						            </c:if>
						        </td>
		                        <td>
		                        <form id="make_order" action="controller">
				                        <!-- если состояние счета 'подготовленный'(accessesAccounts = 'unlocked' = 0), кнопка 'заблокирует'  -->
				                        <c:if test = "${bean.nameStatusPay == 'prepared'}">
				                            <input type="hidden" name="command" value="listClientPays"/>
				                            <input type="hidden" name="name_button" value='send' />
				                            <input type="hidden" name="id_pays" value='${bean.id}' />
				                            <input type="hidden" name="credit_account_id" value='${bean.creditAccountId}' />
				                            <input type="hidden" name="pay_sums" value='${bean.sums}' />
				                            <input type="hidden" name="status_pay_id" value='${bean.statusPayId}' />
				                            <fmt:message key="resource_jsp.block" var="send_button" />
				                            <div style="text-align:center">
				                                <c:if test = "${localization_value == 'ru'}">
				                                    <input type="submit" value='отправить'/>
				                                </c:if>
				                                <c:if test = "${localization_value == 'en'}">
				                                    <input type="submit" value='send'/>
				                                </c:if>
                                            </div>
				                        </c:if>
				                </form>
		                        </td>
		                        
 		                    </tr>
                        </c:forEach>			
			        </table>
			    </c:otherwise>
			</c:choose>
			
			CONTENT
			</td>
		</tr> --%>
		
		<%@ include file="/WEB-INF/jspf/footer.jspf" %>
		
	</table>
</body>
