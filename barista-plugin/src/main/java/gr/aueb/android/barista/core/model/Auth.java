package gr.aueb.android.barista.core.model;

import gr.aueb.android.barista.emulator.telnet.command.TelnetCommand;

/**
 * Cloned From  https://github.com/bzafiris/barrista.git
 */
public class Auth extends AbstractTelnetCommand {

	public static final String AUTH = "auth";
	private String authToken;
	
	public Auth(String authToken) {
		super();
		this.authToken = authToken;
	}

	@Override
	public String getCommandString() {
		StringBuffer buffer = new StringBuffer();
		buffer.append(AUTH)
				.append(" ")
				.append(authToken)
				.append("\r\n");
		return buffer.toString();
	}

}
