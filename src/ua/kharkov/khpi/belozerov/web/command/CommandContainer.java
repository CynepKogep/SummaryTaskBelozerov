package ua.kharkov.khpi.belozerov.web.command;

import java.util.Map;
import java.util.TreeMap;

import org.apache.log4j.Logger;

/**
 * Holder for all commands.<br/>
 * (Контейнер для всех команд.)
 * @author D.Kolesnikov
 * 
 */
public class CommandContainer {
	
	private static final Logger log = Logger.getLogger(CommandContainer.class);
	private static Map<String, Command> commands = new TreeMap<String, Command>();
	
	static {
		// common commands
		commands.put("login", new LoginCommand());
		commands.put("loginA", new LoginCommandA());
		commands.put("logout", new LogoutCommand());
		commands.put("noCommand", new NoCommand());
		commands.put("viewSettings", new ViewSettingsCommand());
		commands.put("updateSettings", new UpdateSettingsCommand());
		commands.put("registration", new RegistrationCommand());
		// client commands
		commands.put("listMenu", new ListMenuCommand());
		commands.put("listClientCards", new CardsClientCommand());
		commands.put("listClientPays", new PaysClientCommand());
		commands.put("ClientCreatePay", new ClientCreatePayCommand());
		// admin commands
		commands.put("listOrders", new ListOrdersCommand());
		commands.put("listOrdersA", new ListOrdersCommandA());
		commands.put("cardsAdminCommand", new CardsAdminCommand());
		commands.put("AdminCreateCard", new AdminCreateCardCommand());
		
		log.debug("Command container was successfully initialized");
		log.trace("Number of commands --> " + commands.size());
	}

	/**
	 * Returns command object with the given name.
	 * (Возвращает объект команды с заданным именем.)
	 * @param commandName
	 *            Name of the command. (имя команды)
	 * @return Command object. 
	 */
	public static Command get(String commandName) {
		if (commandName == null || !commands.containsKey(commandName)) {
			log.trace("Command not found, name --> " + commandName);
			return commands.get("noCommand"); 
		}
		
		log.debug("commandName: " + commandName);
		return commands.get(commandName);
	}
	
}