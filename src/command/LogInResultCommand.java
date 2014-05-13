package command;

import model.StateManager;

import org.jdom.Document;

import com.bbm.HandlerConstants;

public class LogInResultCommand extends ICommand {
	private int id;
	@Override
	public void init(Document doc) {
		// TODO Auto-generated method stub
		String s = doc.getRootElement().getAttributeValue(CommandConstants.ATTR_USER_ID);
		id = Integer.parseInt(s);
	}

	@Override
	public void act() {
		// TODO Auto-generated method stub
		if(id > -1){
			StateManager.getManager().getCurrantHandler().obtainMessage(HandlerConstants.LOGIN_SUCCESS).sendToTarget();
		} else {
			StateManager.getManager().getCurrantHandler().obtainMessage(HandlerConstants.LOGIN_FAILED).sendToTarget();
		}
	}
	
}
