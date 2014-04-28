package net;
import java.io.IOException;
import java.net.Socket;
import command.ICommand;
import model.StateManager;
/*
 * SocketHandler handles messages received from chat server
 */
public class SocketHandler implements Runnable {
	private Socket sock;
	
	public SocketHandler(Socket sock){
		this.sock = sock;
	}
	
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		try {
			DataSender sender = new DataSender(sock);
			StateManager.getManager().setDataSender(sender);
			new Thread(sender).start();
			
			Parser parser = new Parser(sock.getInputStream());
			ICommand nextCommand = null;
			while((nextCommand = parser.nextCommand()) != null){
				nextCommand.act();
			}
			
			sender.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
