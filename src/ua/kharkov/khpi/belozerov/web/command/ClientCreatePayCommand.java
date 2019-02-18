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
import ua.kharkov.khpi.belozerov.db.PaysBeansDao;
import ua.kharkov.khpi.belozerov.db.UserFullInfoDao;
import ua.kharkov.khpi.belozerov.db.entity.CardBeans;
import ua.kharkov.khpi.belozerov.db.entity.UserFull;

public class ClientCreatePayCommand extends Command {

	private static final long serialVersionUID = 1863978254689587788L;
	private static final Logger log = Logger.getLogger(ClientCreatePayCommand.class);

	
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		
		HttpSession session = request.getSession();
		String login = (String)session.getAttribute("login");
		UserFull user = new UserFullInfoDao().findUserByLogin(login);

		String card_to_set = request.getParameter("card_to_set");
		String number_pay = request.getParameter("number_pay");
		String sum_pay = request.getParameter("sum_pay");
		
		String name_button = request.getParameter("name_button");
		
		log.debug("card_to_set:" + card_to_set);
		log.debug("number_pay:" + number_pay);
		log.debug("sum_pay:" + sum_pay);
		log.debug("name_button:" + name_button);
		
		List<CardBeans> cardsList = null;
		cardsList = new CardBeansDao().getCardsClientForCreate(user.getId());
		request.setAttribute("cardsList", cardsList);
		
		String user_id = String.valueOf(user.getId());
		
		if (card_to_set != null && number_pay != null && sum_pay!= null && user_id != null && name_button != null)
		{
			if(name_button.equals("button_create"))
			{
				new PaysBeansDao().insertPay(number_pay, sum_pay, user_id, card_to_set);
			}
		}
		return Path.PAGE__CLIENT_THREE;
	}

}
