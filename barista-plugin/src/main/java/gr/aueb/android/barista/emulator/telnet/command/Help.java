package gr.aueb.android.barista.emulator.telnet.command;
/**
 * Cloned From  https://github.com/bzafiris/barrista.git
 */
@Deprecated
public class Help implements TelnetCommand {

	public static final String HELP = "help";
	
	@Override
	public String toString() {
	
		StringBuffer buffer = new StringBuffer();
		buffer.append(HELP)
		.append("\r\n");
		return buffer.toString();
	}
	
}
