package command;

public class CommandFactory {
	public static ICommand createCommand(String name){
		if(name != null){
			ICommand c = null;
			try {
				c = (ICommand) Class.forName("command." + name).newInstance();
			} catch (InstantiationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return c;
		}
		return null;
	}
}
