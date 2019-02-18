package ua.kharkov.khpi.belozerov.web.command;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.jsp.jstl.core.Config;

import org.apache.log4j.Logger;

import ua.kharkov.khpi.belozerov.Path;
import ua.kharkov.khpi.belozerov.db.Role;
import ua.kharkov.khpi.belozerov.db.UserDaoA;
import ua.kharkov.khpi.belozerov.db.UserFullInfoDao;
import ua.kharkov.khpi.belozerov.db.entity.UserA;
import ua.kharkov.khpi.belozerov.db.entity.UserFull;

public class LoginCommandA extends Command {

	private static final long serialVersionUID = -3071536593627692473L;
	
	private static final Logger log = Logger.getLogger(LoginCommandA.class);
	
	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException 
	{
		
		log.debug("Command starts");
		
		HttpSession session = request.getSession();
		
		// obtain login and password from the request
		String login = request.getParameter("login");
		log.trace("Request parameter: loging --> " + login);
		
		String password = request.getParameter("password");
		
		// error handler
		String errorMessage = null;		
		String forward = Path.PAGE__ERROR_PAGE;
		
		if (login == null || password == null || login.isEmpty() || password.isEmpty()) {
			errorMessage = "Login/password cannot be empty";
			request.setAttribute("errorMessage", errorMessage);
			log.error("errorMessage --> " + errorMessage);
			return forward;
		}
		
		UserFull user = new UserFullInfoDao().findUserByLogin(login);
		log.trace("Found in DB: user --> " + user);
			
		if (user == null || !password.equals(user.getPassword())) 
		{
			errorMessage = "Cannot find user with such login/password";
			request.setAttribute("errorMessage", errorMessage);
			log.error("errorMessage --> " + errorMessage);
			return forward;
		} else {
			Role userRole = Role.getRole(user);
			log.trace("userRole --> " + userRole);
				
			if (userRole == Role.ADMIN)
				forward = Path.COMMAND__LIST_ORDERS_A;
		
			if (userRole == Role.CLIENT)
				forward = Path.COMMAND__LIST_CARD_CLIENT;
			
			session.setAttribute("user", user);
			log.trace("Set the session attribute: user --> " + user);
				
			session.setAttribute("userRole", userRole);				
			log.trace("Set the session attribute: userRole --> " + userRole);
			
			session.setAttribute("login", login);
			
			log.info("User " + user + " logged as " + userRole.toString().toLowerCase());
			
			// log.debug("Command finished");

			
			
/*			// work with i18n
			String userLocaleName = user.getLocaleName();
			log.trace("userLocalName --> " + userLocaleName);
			
			if (userLocaleName != null && !userLocaleName.isEmpty()) {
				Config.set(session, "javax.servlet.jsp.jstl.fmt.locale", userLocaleName);
				
				session.setAttribute("defaultLocale", userLocaleName);
				log.trace("Set the session attribute: defaultLocaleName --> " + userLocaleName);
				
				log.info("Locale for user: defaultLocale --> " + userLocaleName);
			}
*/		}
		
		log.debug("Command finished");
		return forward;
	}

}