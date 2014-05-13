package genDoc;

import org.jdom.Document;
import org.jdom.Element;

import command.CommandConstants;

public class RegisterDocGen {
	public static Document genDoc(String name, String pswd){
		Document doc = new Document();
		Element root = new Element(CommandConstants.CMD_REGISTER);
		root.setAttribute(CommandConstants.ATTR_USER_NAME, name);
		root.setAttribute(CommandConstants.ATTR_PASSWORD, pswd);
		doc.setRootElement(root);
		
		return doc;
	}
}
