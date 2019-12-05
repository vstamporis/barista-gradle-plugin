package gr.aueb.android.barista.emulator.telnet;
/**
 * Cloned From  https://github.com/bzafiris/barrista.git
 */
import gr.aueb.android.barista.core.model.Auth;
import gr.aueb.android.barista.core.model.Command;
import gr.aueb.android.barista.utilities.BaristaLogger;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.net.Socket;



public class TelnetConnection {

	public static final int SOCKET_TIMEOUT = 15000;
	private Socket socket;
	private String hostname;
	private int port;

	private String emulatorConsoleAuthToken;

	private Writer socketWriter;
	private BufferedReader socketReader;

	public TelnetConnection(String hostname, int port, String emulatorConsoleAuthToken) {
		super();
		this.hostname = hostname;
		this.port = port;
		this.emulatorConsoleAuthToken = emulatorConsoleAuthToken;
	}

	private boolean connect() {

		try {
			if (isConnected()) {
				return true;
			}
			socket = new Socket(hostname, port);
			socket.setSoTimeout(SOCKET_TIMEOUT);
			socket.setKeepAlive(true);
			OutputStream out = socket.getOutputStream();
			socketWriter = new OutputStreamWriter(out);
			socketWriter = new BufferedWriter(socketWriter);
			InputStream in = socket.getInputStream();
			socketReader = new BufferedReader(new InputStreamReader(in));

			command(new Auth(emulatorConsoleAuthToken));

			// issue auth command
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	private boolean isConnected() {
		if (socket == null || !socket.isConnected() || socket.isClosed()) {
			return false;
		}
		return true;
	}


	public boolean command(Command telnetCommand) {

		if (!connect()) {
			return false;
		}

		BaristaLogger.print(port + "> Executing: " + telnetCommand.toString());

		try {
			socketWriter.write(telnetCommand.getCommandString());
			socketWriter.flush();

			for (String line = socketReader.readLine(); line != null; line = socketReader.readLine()) {
				BaristaLogger.print("Response: " + line);
				if (line.trim().equals("OK")){
					return true;
				}
			}

		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}


		return true;
	}

}
