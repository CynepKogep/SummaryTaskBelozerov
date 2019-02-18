package ua.kharkov.khpi.belozerov.web.command;

import org.apache.log4j.Logger;

import ua.kharkov.khpi.belozerov.Path;
import ua.kharkov.khpi.belozerov.db.UserDao;
import ua.kharkov.khpi.belozerov.db.entity.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.jsp.jstl.core.Config;
import java.io.IOException;

/**
 * Update settings items.
 * 
 * @author D.Kolesnikov
 * 
 */
public class UpdateSettingsCommand extends Command {

	private static final long serialVersionUID = 7732286214029478505L;

	private static final Logger log = Logger.getLogger(UpdateSettingsCommand.class);

	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {
		
		log.debug("Command starts");
		
		// UPDATE USER ////////////////////////////////////////////////////////
/*		
		User user = (User)request.getSession().getAttribute("user");
		boolean updateUser = false;
		
		// update first name
		String firstName = request.getParameter("firstName");
		if (firstName != null && !firstName.isEmpty()) {
			user.setFirstName(firstName);
			updateUser = true;
		}

		// update last name
		String lastName = request.getParameter("lastName");
		if (lastName != null && !lastName.isEmpty()) {
			user.setLastName(lastName);
			updateUser = true;
		}

*/		
		String localeToSet = request.getParameter("localeToSet");
		log.debug("localeToSet:" + localeToSet);
		log.debug("localeToSet != null -->" + (localeToSet!= null));
		log.debug("!localeToSet.isEmpty():" + (!localeToSet.isEmpty()));
		
		if (localeToSet != null && !localeToSet.isEmpty()) {
			
			HttpSession session = request.getSession();
			log.debug("session:" + session);
			Config.set(session, "javax.servlet.jsp.jstl.fmt.locale", localeToSet);
			
			// session.setAttribute("javax.servlet.jsp.jstl.fmt.locale.session", localeToSet);
			session.setAttribute("defaultLocale", localeToSet);
			log.debug("session.getAttribute(\"defaultLocale\", localeToSet);" + session.getAttribute("defaultLocale"));
			
//			user.setLocaleName(localeToSet);
//			updateUser = true;
		}
		
/*		if (updateUser == true)
			new UserDao().updateUser(user);
*/
		
		
//		HttpSession session = request.getSession();
//		// log.debug("session:" + session);
//		Config.set(session, "javax.servlet.jsp.jstl.fmt.locale", "ru");
//		// session.setAttribute("defaultLocale", localeToSet);
//
		
		
		
		log.debug("Command finished");
		return Path.PAGE__SETTINGS;
	}

}