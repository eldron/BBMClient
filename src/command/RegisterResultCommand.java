package command;

import model.StateManager;
import org.jdom.Document;
import com.bbm.HandlerConstants;

public class RegisterResultCommand extends ICommand {
	private int userID;
	@Override
	public void init(Document doc) {
		// TODO Auto-generated method stub
		String s = doc.getRootElement().getAttributeValue(CommandConstants.ATTR_USER_ID);
		userID = Integer.parseInt(s);
	}

	@Override
	public void act() {
		// TODO Auto-generated method stub
		// returns user id if successful, returns -1 if not
		StateManager.getManager().getCurrantHandler().obtainMessage(HandlerConstants.REGISTER_RESULT, userID).sendToTarget();
	}

}
