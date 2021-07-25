package mockito;

public class WebService {

	public WebService() {
		super();
	}
	
	
	public void login (String user, String password, CallBack callBack) {
		
		if(checkLogin(user, password)) {
			callBack.onSuccess("User correct!");
		} else {
			callBack.onError("Error!");
		}
		
	}
	
	public boolean checkLogin(String user, String password) {
		
		if(user.equals("nacarabali") && password.equals("1234")) {
			return true;
		}
		
		return false;
		
	}

}
