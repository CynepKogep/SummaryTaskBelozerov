package ua.kharkov.khpi.belozerov.web.command;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import ua.kharkov.khpi.belozerov.Path;
import ua.kharkov.khpi.belozerov.db.CardBeansDao;
import ua.kharkov.khpi.belozerov.db.UserFullInfoDao;
import ua.kharkov.khpi.belozerov.db.entity.CardBeans;
import ua.kharkov.khpi.belozerov.db.entity.UserFull;

public class CardsClientCommand extends Command{
	
	private static final long serialVersionUID = 1863978254689587778L;
	private static final Logger log = Logger.getLogger(CardsClientCommand.class);
	
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		
		HttpSession session = request.getSession();
		String login = (String)session.getAttribute("login");
		UserFull user = new UserFullInfoDao().findUserByLogin(login);
		
		String id_card = request.getParameter("id_card");
		String accesses_card = request.getParameter("accesses_card");
		String unlock_request_card = request.getParameter("unlock_request_card");
		String name_button = request.getParameter("name_button");
		log.debug("id_card:" + id_card);
		log.debug("accesses_card:" + accesses_card);
		log.debug("unlock_request_card:" + unlock_request_card);
		
		// кнопка 'заблокировать' (вторая)
		if (id_card != null && accesses_card != null && name_button != null)
		{
			// card разблокиравана
			if (accesses_card.equals("unlocked")&& name_button.equals("lock")) // 0 
			{
	            accesses_card = "1";
	            new CardBeansDao().updateCardsAccesses(id_card, accesses_card);
			}
		}
	    // кнопка 'запрос на разблокирование' (первая)
		if (id_card != null && accesses_card != null && unlock_request_card !=null && name_button != null)
		{
			if (unlock_request_card.equals("doesnotexist")&& name_button.equals("unlock_request"))
			{
				unlock_request_card = "1";
				new CardBeansDao().updateCardsUnlockRequest(id_card, unlock_request_card);
			}
		}
		
		List<CardBeans> cardsList = null;
		
		String command_number = request.getParameter("command_number");
		log.debug("command_number:" + command_number); 
		
		// Кнопки сортировки
		if(command_number != null)
		{
			if (command_number.equals("02_name"))
			{
				cardsList = new CardBeansDao().getCardsClientSortingName(user.getId());
			}
			else if (command_number.equals("03_date_balance"))
			{
				cardsList = new CardBeansDao().getCardsClientSortingBalance(user.getId());
			}
			else if(command_number.equals("01_number") )
			{
				cardsList = new CardBeansDao().getCardsClientSortingNumber(user.getId());
			}
		}
		else
		{
			cardsList = new CardBeansDao().getCardsClientSortingNumber(user.getId());
		}
		
		// List<CardBeans> cardsList = new CardBeansDao().getCardsClientSortingNumber(user.getId());
		
		request.setAttribute("cardsList", cardsList);
		
		log.trace("Found in DB: cardsList --> " + cardsList);
		log.debug("CardsAdminCommand finished");
		return Path.PAGE__CLIENT_ONE;
	}

	

}
