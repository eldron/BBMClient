package command;

import net.DataSender;

import org.jdom.Document;

public abstract class ICommand {
	public abstract void init(Document doc);
	public abstract void act();
}
