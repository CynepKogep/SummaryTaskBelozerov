<%@ page pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jspf/directive/page.jspf" %>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf" %>

<html>

<head>
    <title></title>
    <script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.8.3/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jspdf/1.3.2/jspdf.min.js"></script>
    <script type="text/javascript" src="jspdf.js"></script> 
    <script type="text/javascript" src="jspdf.plugin.standard_fonts_metrics.js"></script>
    <script type="text/javascript" src="jspdf.plugin.split_text_to_size.js"></script>
    <script type="text/javascript" src="jspdf.plugin.from_html.js"></script>
    <script type="text/javascript" src="jspdf.debug.js"></script>
    <script type="text/javascript">
    /*function demoFromHTML() 
    {
    var doc = new jsPDF('p', 'pt');

    var elem = document.getElementById('list_order_table');
    var imgElements = document.querySelectorAll('#table tbody img');
    var data = doc.autoTableHtmlToJson(elem);
    var images = [];
    var i = 0;
    doc.autoTable(data.columns, data.rows, {
      bodyStyles: {rowHeight: 30},
      drawCell: function(cell, opts) {
        if (opts.column.dataKey === 5) {
          images.push({
            url: imgElements[i].src,
            x: cell.textPos.x,
            y: cell.textPos.y
          });
          i++;
        }
      },
      addPageContent: function() {
        for (var i = 0; i < images.length; i++) {
          doc.addImage(images[i].url, images[i].x, images[i].y, 20, 20);
        }
      }
    });

    doc.save("table.pdf");
  }
} */
    
    function demoFromHTML() {
    	
/*      	var doc = new jsPDF('p', 'pt');
    	
    	var elem = document.getElementById("list_order_table");
    	var res = doc.autoTableHtmlToJson(elem);
    	doc.autoTable(res.columns, res.data);
    	doc.save("table.pdf");
 */    	
          //var pdf = new jsPDF('p', 'pt', 'letter');// , 'letter'
          var pdf = new jsPDF('landscape', 'pt', 'a4');
        // source can be HTML-formatted string, or a reference
        // to an actual DOM element from which the text will be scraped.
        //pdf77777777
        source = $('#pdf77777777')[0];

 /*        // we support special element handlers. Register them with jQuery-style 
        // ID selector for either ID or node name. ("#iAmID", "div", "span" etc.)
        // There is no support for any other type of selectors 
        // (class, of compound) at this time.
        specialElementHandlers = {
            // element with id of "bypass" - jQuery style selector
            '#bypassme': function (element, renderer) {
                // true = "handled elsewhere, bypass text extraction"
                return true
            }
        };
        margins = {
            top: 80,
            bottom: 60,
            left: 40,
            width: 522
        };
        // all coords and widths are in jsPDF instance's declared units
        // 'inches' in this case
        pdf.fromHTML(source, // HTML string or DOM elem ref.
        		     margins.left, // x coord
                     margins.top, { 
                                  }, function (dispose) {
            // dispose: object with X, Y of the last line add to the PDF 
            //          this allow the insertion of new lines after html
            pdf.save('Test.pdf');
        }, margins); */
        
        var specialElementHandlers = {
                '#bypassme': function(element, renderer) {
                    return true
                }
            };
            var margins = {
                top: 40,
                bottom: 40,
                left: 40,
                width: 750
            };
            pdf.fromHTML(
                    source, // HTML string or DOM elem ref.
                    margins.left, // x coord
                    margins.top, {
                    },
            function(dispose) {
                  	pdf.save('Test.pdf');
            }
            , margins);
          }
    
    </script>
</head>


<c:set var="title" value="Menu" scope="page" />
<%@ include file="/WEB-INF/jspf/head.jspf" %>

<body>
<fmt:message key="settings_jsp.label.localization.value" var="localization_value" />


	<table id="main-container">
		<%@ include file="/WEB-INF/jspf/header.jspf" %>
			
		<tr>
			<td class="content">			
			<%-- CONTENT --%>
			<div>
			    <div style="display:inline-block">
			        <c:if test = "${localization_value == 'ru'}">
			        Сортировка
			        </c:if>
			    </div>
			<form style="display:inline-block" id="make_order" action="controller">
				<input type="hidden" name="command" value="listClientPays"/>
				<input type="hidden" name="command_number" value="01_number"/>
				<c:if test = "${localization_value == 'ru'}">
				    <input type="submit" value='по номеру'/>
				</c:if>
				<c:if test = "${localization_value == 'en'}">
				    <input type="submit" value= 'sort number'/>
				</c:if>
            </form>
            
            
            <form style="display:inline-block" id="make_order" action="controller">
				<input type="hidden" name="command" value="listClientPays"/>
				<input type="hidden" name="command_number" value="02_data"/>
				    <c:if test = "${localization_value == 'ru'}">
				        <input type="submit" value='по дате'/>
				    </c:if>
				    <c:if test = "${localization_value == 'en'}">
				        <input type="submit" value= 'sort date'/>
				    </c:if>
				
            </form>
                       
            <form style="display:inline-block" id="make_order" action="controller">
				<input type="hidden" name="command" value="listClientPays"/>
				<input type="hidden" name="command_number" value="03_data_desc"/>
				    <c:if test = "${localization_value == 'ru'}">
				        <input type="submit" value='по дате (desc)'/>
				    </c:if>
				    <c:if test = "${localization_value == 'en'}">
				        <input type="submit" value= 'sort date desc'/>
				    </c:if>
            </form>
            
            <form style="display:inline-block" id="make_order" action="controller">
				<input type="hidden" name="command" value="listClientPays"/>
				<input type="hidden" name="command_number" value="pdg_page"/>
				<input type="hidden" name="name_button" value='pdg_button' />
				Path:
				<input name="path" type="text" value="C:\" required pattern="^[\\0-9A-Fa-f:.]+$">
				<input type="submit" value='pdf'/>
<%-- 				    <c:if test = "${localization_value == 'ru'}">
				        <input type="submit" value='по дате (desc)'/>
				    </c:if>
				    <c:if test = "${localization_value == 'en'}">
				        <input type="submit" value= 'sort date desc'/>
				    </c:if>
 --%>            </form>
            <button style="display:inline-block" onclick="javascript:demoFromHTML();">PDF(js)</button>
            
            <form style="display:inline-block" id="make_order" action="controller">
				<input type="hidden" name="command" value="listClientPays"/>
				<input type="hidden" name="name_button" value="pdf_2"/>
				<input type="submit" value='pdf(jsp)'/>
            </form>
            
<!--            <form style="display:inline-block" id="make_order" action="PdfServlet" method="post">
				<input type="hidden" name="command" value="listClientPays"/>
				<input type="hidden" name="command_number" value="pdg_page"/>
				<input type="hidden" name="name_button" value='pdg_button' />
				<input type="submit" value='pdf (2)'/>
           </form>            
 -->            
            </div>
            				
			<c:choose>
			    <c:when test="${fn:length(paysList) == 0}">No such orders</c:when>
	
			    <c:otherwise>
<%-- 			        <fmt:message key="settings_jsp.label.localization.value" var="localization_value" /> --%>
			        <div id="pdf77777777">
			        
			        <table id="list_order_table" class="table table-striped">
<!-- 			        <colgroup>
                              <col width="10%">
                              <col width="15%">
                              <col width="15%">
                              <col width="15%">
                              <col width="15%">
                              <col width="15%">
                              <col width="15%">
                    </colgroup> -->
				        <thead>
					        <tr class='warning'>
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
						        <td> ${bean.sums}</td>
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
			        
			        </div>
			    </c:otherwise>
			</c:choose>
			
			<%-- CONTENT --%>
			</td>
		</tr>
		
		<%@ include file="/WEB-INF/jspf/footer.jspf" %>
		
	</table>

</body>
