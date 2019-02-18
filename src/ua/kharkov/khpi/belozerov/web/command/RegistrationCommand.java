package ua.kharkov.khpi.belozerov.web.command;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import ua.kharkov.khpi.belozerov.Path;
import ua.kharkov.khpi.belozerov.db.UserFullInfoDao;
import ua.kharkov.khpi.belozerov.db.entity.UserFull;

public class RegistrationCommand extends Command{

	private static final long serialVersionUID = 1863978254689587888L;
	private static final Logger log = Logger.getLogger(RegistrationCommand.class);

	
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {

		log.debug("Command starts");
		
		String command = request.getParameter("command");
		String login_registration = request.getParameter("login_registration");
		String password_registration = request.getParameter("password_registration");
		String first_name_registration = request.getParameter("first_name_registration");
		String last_name_registration = request.getParameter("last_name_registration");
		String first_name_ru_registration = request.getParameter("first_name_ru_registration");
		String last_name_ru_registration = request.getParameter("last_name_ru_registration");
		String name_button = request.getParameter("name_button");

		log.debug("command:" + command);
		log.debug("login_registration:" + login_registration);
		log.debug("password_registration:" + password_registration);
		log.debug("first_name_registration:" + first_name_registration);
		log.debug("last_name_registration:" + last_name_registration);
		log.debug("first_name_ru_registration:" + first_name_ru_registration);
		log.debug("last_name_ru_registration:" + last_name_ru_registration);
		log.debug("name_button:" + name_button);
		
		// пользователи, дл€ исключени€ повтор€ющихс€  
		List<UserFull> usersList = new UserFullInfoDao().getUsers();
		log.debug("Set the request attribute: usersList --> " + usersList);
		
		String is_user = null;
		if (login_registration != null && login_registration != null && password_registration != null && 
			first_name_registration != null && last_name_registration != null && first_name_ru_registration != null &&
			last_name_ru_registration != null && name_button != null)
		{
			log.debug("if  #1");
			if (name_button.equals("button_registration") && usersList != null)
			{
				log.debug("if  #2");
				is_user = "is_not";
				for (int i = 0; i < usersList.size(); i++)
				{
					String userLogin = usersList.get(i).getLogin();
					String userLastName = usersList.get(i).getLastName();
					String userFirstName = usersList.get(i).getFirstName();
					String userLastNameRu = usersList.get(i).getLastNameRu();
					String userFirstNameRu = usersList.get(i).getFirstNameRu();
					if (userLogin.equals(login_registration)|| userLastName.equals(last_name_registration)&&userFirstName.equals(first_name_registration)||
						userLastNameRu.equals(last_name_ru_registration)&&userFirstNameRu.equals(first_name_registration))
					{
						log.debug("if(is_user) == is");
						is_user = "is";
						break;
					}
				}
				if(is_user.equals("is_not"))
				{
					log.debug("if(is_user) == is_not");
					new UserFullInfoDao().insertUser(login_registration, password_registration, first_name_registration,
							                         last_name_registration, first_name_ru_registration, last_name_ru_registration);
					usersList = new UserFullInfoDao().getUsers();
				}
			}
		}
		request.setAttribute("is_user", is_user);
		request.setAttribute("usersList", usersList);

		log.debug("Command finished");
		return Path.PAGE__REGISTRATION;
	}

}
