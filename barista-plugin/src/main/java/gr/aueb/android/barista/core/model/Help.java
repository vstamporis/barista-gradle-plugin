package gr.aueb.android.barista.core.model;


/**
 * Cloned From  https://github.com/bzafiris/barrista.git
 */
public class Help extends AbstractTelnetCommand {

	public static final String HELP = "help";

	@Override
	public String getCommandString() {
		StringBuffer buffer = new StringBuffer();
		buffer.append(HELP)
				.append("\r\n");
		return buffer.toString();
	}
}
