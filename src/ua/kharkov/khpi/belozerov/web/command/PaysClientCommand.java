package ua.kharkov.khpi.belozerov.web.command;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import ua.kharkov.khpi.belozerov.Path;
import ua.kharkov.khpi.belozerov.db.CardBeansDao;
import ua.kharkov.khpi.belozerov.db.PaysBeansDao;
import ua.kharkov.khpi.belozerov.db.UserFullInfoDao;
import ua.kharkov.khpi.belozerov.db.entity.CardBeans;
import ua.kharkov.khpi.belozerov.db.entity.PaysBeans;
import ua.kharkov.khpi.belozerov.db.entity.UserFull;

public class PaysClientCommand extends Command {
	
	private static final long serialVersionUID = 1863978254689587780L;
	private static final Logger log = Logger.getLogger(PaysClientCommand.class);

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {

		HttpSession session = request.getSession();
		String login = (String)session.getAttribute("login");
		UserFull user = new UserFullInfoDao().findUserByLogin(login);
	
		String id_pays = request.getParameter("id_pays");
		String status_pay_id = request.getParameter("status_pay_id");
		String credit_account_id = request.getParameter("credit_account_id");
		String pay_sums = request.getParameter("pay_sums");
		String name_button = request.getParameter("name_button");
		String path = request.getParameter("path");
		log.debug("id_pays:" + id_pays);
		log.debug("status_pay_id:" + status_pay_id);
		log.debug("credit_account_id:" + credit_account_id);
		log.debug("pay_sums:" + pay_sums);
		log.debug("name_button:" + name_button);
		log.debug("path:" + path);
		log.debug("user.getAccessesUsers():" + user.getAccessesUsers()); 
		
		
	    // кнопка 'запрос на отправленный' (первая)
		if (id_pays != null && status_pay_id != null && credit_account_id!= null && name_button != null)
		{
			log.debug("if #1"); 
			if (name_button.equals("send") && user.getAccessesUsers().equals("unlocked"))
			{
				log.debug("if #2");
				// меняем значение переменной статуса (для платежа)
				status_pay_id = "1";
				// получаем карту (действие для карты)
				List<CardBeans> cardsList = null;
				cardsList = new CardBeansDao().getCardsClientSortingNumber(user.getId(), credit_account_id);
				if ((long)cardsList.get(0).getAccessesAccountsId() == 0)
				{
				    // получаем баланс на конкретной карте (действие для карты)
				    Long balanse = cardsList.get(0).getBalance(); 
				    // получаем сумму с платежа на отправку (действие для карты)
				    Long sums = Long.valueOf(pay_sums);
				    // новый баланс (действие для карты)
				    balanse = balanse - sums;
				    log.debug("sums:" + sums);
				    log.debug("balanse:" + balanse);
				    // изменение баланса на карте 
				    new CardBeansDao().updateCardsBalance(cardsList.get(0).getId(), balanse);
				    // изменение платежа на send							
				    new PaysBeansDao().updatePaysStatusPay(Long.valueOf(id_pays), status_pay_id);
				}
			}
		}
		
		
		String command_number = request.getParameter("command_number");
		log.debug("command_number:" + command_number); 
		
		
		List<PaysBeans> paysList = null;
		
		if(command_number != null)
		{
			if (command_number.equals("02_data"))
			{
				paysList = new PaysBeansDao().getPaysSortDate(user.getId());
			}
			else if (command_number.equals("03_data_desc"))
			{
				paysList = new PaysBeansDao().getPaysSortDateDesc(user.getId());
			}
			else if(command_number.equals("01_number") )
			{
				paysList = new PaysBeansDao().getPays(user.getId());
			}
		}
		else
		{
			paysList = new PaysBeansDao().getPays(user.getId());
		}
		
		if (name_button!=null && name_button.equals("pdg_button") && path!=null)
		{
			paysList = new PaysBeansDao().getPays(user.getId());
			createPdf(paysList,path);
		}

		if (name_button!=null && name_button.equals("pdf_2"))
		{
			log.debug("name_button!=null && name_button.equals(\"pdf_2\")"); 
			paysList = new PaysBeansDao().getPays(user.getId());
			OutputStream file = createPdf2(paysList,path);
			if(file!= null)
			{
				request.setAttribute("file", file);
				return "/WEB-INF/jsp/pdf_page.jsp";
			}
			
		}
		
		request.setAttribute("paysList", paysList);
		
		return Path.PAGE__CLIENT_TWO;
	}

	public void createPdf(List<PaysBeans> paysList, String path)
	{
	       try {

	           // количество строк
	    	   int size = paysList.size();
	    	   OutputStream file = new FileOutputStream(new File(path + "PDF_Java4s.pdf"));
		       Document document = new Document();
               PdfWriter.getInstance(document, file);
			   //Inserting Table in PDF
    		   PdfPTable table=new PdfPTable(size);
               PdfPCell cell = new PdfPCell (new Paragraph ("Table pays"));
		                  cell.setColspan (7);
					      cell.setHorizontalAlignment (Element.ALIGN_CENTER);
					      cell.setPadding (10.0f);
					      cell.setBackgroundColor (new BaseColor (140, 221, 8));					               

					      table.addCell(cell);						               

		                  
		                  for (int i = 0; i < size; i++) 
		                  {
		                	  table.addCell("" + paysList.get(i).getNumbers());
		                	  table.addCell("" + paysList.get(i).getDatas());
		                	  table.addCell("" + paysList.get(i).getSums());
		                	  table.addCell("" + paysList.get(i).getCreditAccountName());
		                	  table.addCell("" + paysList.get(i).getLastName());
		                	  table.addCell("" + paysList.get(i).getFirstName());
		                	  table.addCell("" + paysList.get(i).getNameStatusPay());
		                  }
					      
/*		                  cell.setColspan (3);
					      cell.setHorizontalAlignment (Element.ALIGN_CENTER);
					      cell.setPadding (10.0f);
					      cell.setBackgroundColor (new BaseColor (140, 221, 8));					               

					      table.addCell(cell);						               

					      table.addCell("Name");
					      table.addCell("Address");
					      table.addCell("Country");
	                      table.addCell("Java4s");
					      table.addCell("NC");
					      table.addCell("United States");
*/					      table.setSpacingBefore(30.0f);       // Space Before table starts, like margin-top in CSS
					      table.setSpacingAfter(30.0f);        // Space After table starts, like margin-Bottom in CSS								          

				 //Inserting List in PDF

//					      List list=new ArrayList(true,30);
//				          list.add(new ListItem("Java4s"));
//					      list.add(new ListItem("Php4s"));
//					      list.add(new ListItem("Some Thing..."));		

				 //Text formating in PDF
/*		                Chunk chunk=new Chunk("Welecome To Java4s Programming Blog...");

						chunk.setUnderline(+1f,-2f);//1st co-ordinate is for line width,2nd is space between
						Chunk chunk1=new Chunk("Php4s.com");
						chunk1.setUnderline(+4f,-8f);
						chunk1.setBackground(new BaseColor (17, 46, 193));      
*/
				 //Now Insert Every Thing Into PDF Document
			         document.open();//PDF document opened........			       

//						document.add(image);

						document.add(Chunk.NEWLINE);   //Something like in HTML 🙂

	                    document.add(new Paragraph("Dear " + paysList.get(0).getLastName() + " " + paysList.get(0).getFirstName()));
		                document.add(new Paragraph("Document Generated On - "+new Date().toString()));	

						document.add(table);

//						document.add(chunk);
//						document.add(chunk1);

						document.add(Chunk.NEWLINE);   //Something like in HTML 🙂							    

	       				document.newPage();            //Opened new page

						//document.add(list);            //In the new page we are going to add list

			         document.close();
				     file.close();

	            System.out.println("Pdf created successfully..");
	            

	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	    }
	
	public OutputStream createPdf2(List<PaysBeans> paysList, String path)
	{
	       try {
               String path2 = "f:\\PROGRAMME\\eclipse-workspace\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp0\\wtpwebapps\\SummaryTaskBelozerov\\WEB-INF\\jsp\\";
	           // количество строк
	    	   int size = paysList.size();
	    	   OutputStream file = new FileOutputStream(new File(path2 + "PDF_Java4s777.pdf"));
		       Document document = new Document();
               PdfWriter.getInstance(document, file);
			   //Inserting Table in PDF
    		   PdfPTable table=new PdfPTable(size);
               PdfPCell cell = new PdfPCell (new Paragraph ("Table pays"));
		                  cell.setColspan (7);
					      cell.setHorizontalAlignment (Element.ALIGN_CENTER);
					      cell.setPadding (10.0f);
					      cell.setBackgroundColor (new BaseColor (140, 221, 8));					               

					      table.addCell(cell);						               

		                  
		                  for (int i = 0; i < size; i++) 
		                  {
		                	  table.addCell("" + paysList.get(i).getNumbers());
		                	  table.addCell("" + paysList.get(i).getDatas());
		                	  table.addCell("" + paysList.get(i).getSums());
		                	  table.addCell("" + paysList.get(i).getCreditAccountName());
		                	  table.addCell("" + paysList.get(i).getLastName());
		                	  table.addCell("" + paysList.get(i).getFirstName());
		                	  table.addCell("" + paysList.get(i).getNameStatusPay());
		                  }
					      
/*		                  cell.setColspan (3);
					      cell.setHorizontalAlignment (Element.ALIGN_CENTER);
					      cell.setPadding (10.0f);
					      cell.setBackgroundColor (new BaseColor (140, 221, 8));					               

					      table.addCell(cell);						               

					      table.addCell("Name");
					      table.addCell("Address");
					      table.addCell("Country");
	                      table.addCell("Java4s");
					      table.addCell("NC");
					      table.addCell("United States");
*/					      table.setSpacingBefore(30.0f);       // Space Before table starts, like margin-top in CSS
					      table.setSpacingAfter(30.0f);        // Space After table starts, like margin-Bottom in CSS								          

				 //Inserting List in PDF

//					      List list=new ArrayList(true,30);
//				          list.add(new ListItem("Java4s"));
//					      list.add(new ListItem("Php4s"));
//					      list.add(new ListItem("Some Thing..."));		

				 //Text formating in PDF
/*		                Chunk chunk=new Chunk("Welecome To Java4s Programming Blog...");

						chunk.setUnderline(+1f,-2f);//1st co-ordinate is for line width,2nd is space between
						Chunk chunk1=new Chunk("Php4s.com");
						chunk1.setUnderline(+4f,-8f);
						chunk1.setBackground(new BaseColor (17, 46, 193));      
*/
				 //Now Insert Every Thing Into PDF Document
			         document.open();//PDF document opened........			       

//						document.add(image);

						document.add(Chunk.NEWLINE);   //Something like in HTML 🙂

	                    document.add(new Paragraph("Dear " + paysList.get(0).getLastName() + " " + paysList.get(0).getFirstName()));
		                document.add(new Paragraph("Document Generated On - "+new Date().toString()));	

						document.add(table);

//						document.add(chunk);
//						document.add(chunk1);

						document.add(Chunk.NEWLINE);   //Something like in HTML 🙂							    

	       				document.newPage();            //Opened new page

						//document.add(list);            //In the new page we are going to add list

			         document.close();
				     file.close();

	            System.out.println("Pdf created successfully..");
	            return file;

	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	       return null;
	    }
	
	
}
