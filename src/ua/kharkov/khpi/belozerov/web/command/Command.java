package ua.kharkov.khpi.belozerov.web.command;

import java.io.IOException;
import java.io.Serializable;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Main interface for the Command pattern implementation.
 * (Основной интерфейс для реализации шаблона Command.)
 * @author D.Kolesnikov
 * 
 */
public abstract class Command implements Serializable {	
	private static final long serialVersionUID = 8879403039606311780L;

	/**
	 * Execution method for command.
	 * (Способ выполнения команды.)
	 * @return Address to go once the command is executed.
	 *         (Адрес отправляется после выполнения команды.)
	 */
	public abstract String execute(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException;
	
	// ----------------------------------------------------------------------------------------- 
	@Override
	public final String toString() {
		return getClass().getSimpleName();
	}
}