package ua.kharkov.khpi.belozerov.web.command;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import ua.kharkov.khpi.belozerov.Path;
import ua.kharkov.khpi.belozerov.db.CardBeansDao;
import ua.kharkov.khpi.belozerov.db.PaysBeansDao;
import ua.kharkov.khpi.belozerov.db.UserFullInfoDao;
import ua.kharkov.khpi.belozerov.db.entity.CardBeans;
import ua.kharkov.khpi.belozerov.db.entity.UserFull;

public class AdminCreateCardCommand extends Command{

	private static final long serialVersionUID = 1863978254689787788L;
	private static final Logger log = Logger.getLogger(AdminCreateCardCommand.class);

	
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		
		String users_to_set = request.getParameter("users_to_set");
		String number_card = request.getParameter("number_card");
		String name_card = request.getParameter("name_card");
		String balance_card = request.getParameter("balance_card");
		String name_button = request.getParameter("name_button");
		
		log.debug("users_to_set:" + users_to_set);
		log.debug("number_card:" + number_card);
		log.debug("name_card:" + name_card);
		log.debug("balance_card:" + balance_card);
		log.debug("name_button:" + name_button);
		
		List<UserFull> usersList = null;
		usersList = new UserFullInfoDao().getUsers();
		request.setAttribute("usersList", usersList);
		
		if (users_to_set != null && number_card != null && name_card!= null && balance_card != null && name_button != null)
		{
			if(name_button.equals("button_create_card"))
			{
				new CardBeansDao().insertCard(number_card, name_card, balance_card, users_to_set);
			}
		}
		
		return Path.PAGE__ADMIN_THREE;
	}

}
