package action;

public class TestLoadDirAction extends BaseAction {
	
	public String execute(){
		
		String s = request.getParameter("oldStr");
		System.out.println("in testLoadDirAction, "+s);
		
		return "success";
	}

	
	
}
