package net;
import org.jdom.Document;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;

import command.CommandFactory;
import command.ICommand;

import java.io.*;

public class Parser {
	private InputStream in;
	
	public Parser(InputStream in){
		this.in = in;
	}
	
	public ICommand nextCommand(){
		BufferedInputStream reader = new BufferedInputStream(in);
		int tmp = 0;
		StringBuilder stringBuilder = new StringBuilder();
		SAXBuilder saxBuilder = new SAXBuilder();
		Document doc = null;
		
		try {
			while((tmp = reader.read()) != -1){
				if(tmp != '\0'){
					stringBuilder.append((char) tmp);
				} else {
					String result = stringBuilder.toString();
					System.err.println("received:" + result);
					
					doc = saxBuilder.build(new StringReader(result));
					String name = doc.getRootElement().getName();
					ICommand c = CommandFactory.createCommand(name);
					if(c != null){
						c.init(doc);
					}
					
					return c;
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JDOMException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
