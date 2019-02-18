package ua.kharkov.khpi.belozerov.web.command;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.jsp.jstl.core.Config;

import org.apache.log4j.Logger;

import ua.kharkov.khpi.belozerov.Path;
import ua.kharkov.khpi.belozerov.db.CardBeansDao;
import ua.kharkov.khpi.belozerov.db.UserFullInfoDao;
import ua.kharkov.khpi.belozerov.db.entity.CardBeans;


public class CardsAdminCommand extends Command{

	private static final long serialVersionUID = 1863978254689587777L;
	private static final Logger log = Logger.getLogger(CardsAdminCommand.class);

	
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		
		
		log.debug("CardsAdminCommand starts");
		
		String id_card = request.getParameter("id_card");
		String accesses_card = request.getParameter("accesses_card");
		String unlock_request_card = request.getParameter("unlock_request_card");
		log.debug("id_card:" + id_card);
		log.debug("accesses_card:" + accesses_card);
		log.debug("unlock_request_card:" + unlock_request_card);
//		String localeToSet = request.getParameter("localeToSet");
//		log.debug("localeToSet:" + localeToSet);

		
//		if ((id_card != null && unlock_request_card != null))
//		{
//			if (unlock_request_card.equals("exists"))
//			{
//				unlock_request_card = "0";
//				new CardBeansDao().updateCardsLockRequest(id_card, unlock_request_card);
//			}
////			else if (lock_request_card.equals("blocked"))
////			{
////				accesses_card = "0";
////			}
////			new CardBeansDao().updateCardsLockRequest(id_card, lock_request_card);
//		}
		
		if ((id_card != null && accesses_card != null))
		{
			// card разблокиравана
			if (accesses_card.equals("unlocked")) // 0 
			{
	            accesses_card = "1";
	            new CardBeansDao().updateCardsAccesses(id_card, accesses_card);
			}
			else if (accesses_card.equals("blocked"))  // 1
			{
				// card заблокиравана, запроса нет
				if(unlock_request_card != null && unlock_request_card.equals("doesnotexist"))
				{
				}
				// card заблокиравана, запроса есть
				else if(unlock_request_card != null && unlock_request_card.equals("exists"))
				{
					unlock_request_card = "0";
					new CardBeansDao().updateCardsUnlockRequest(id_card, unlock_request_card);
				}
				accesses_card = "0";
				new CardBeansDao().updateCardsAccesses(id_card, accesses_card);
			}
			
		}
		// вывод всех cards
		List<CardBeans> cardsList = new CardBeansDao().getCards();
		request.setAttribute("cardsList", cardsList);
		log.trace("Found in DB: cardsList --> " + cardsList);
		log.debug("CardsAdminCommand finished");
		return Path.PAGE__ADMIN_TWO;
	}

}
