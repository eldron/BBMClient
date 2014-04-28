package net;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.util.concurrent.LinkedBlockingQueue;

import org.jdom.Document;
import org.jdom.output.XMLOutputter;

/*
 * DataSender handles sending data from client to server
 */
public class DataSender implements Runnable {
	private Socket sock;
	private LinkedBlockingQueue<Document> toSend;
	private XMLOutputter xmlOutputter;
	private boolean stopped = false;
	
	public synchronized boolean isStopped(){
		return stopped;
	}
	
	public synchronized void close(){
		stopped = true;
		try {
			sock.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void add(Document doc){
		try {
			toSend.put(doc);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public DataSender(Socket sock){
		this.sock = sock;
		toSend =  new LinkedBlockingQueue<Document>();
		xmlOutputter = new XMLOutputter();
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		while(isStopped() == false){
			try {
				Document doc = toSend.take();
				String data = xmlOutputter.outputString(doc) + "\0";
				System.err.println("send to server" + data);
				
				try {
					OutputStream out = sock.getOutputStream();
					out.write(data.getBytes());
					out.flush();
					Thread.currentThread().sleep(300);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}