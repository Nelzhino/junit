package mockito;

public class Login {

	private WebService werService;

	private boolean isLogin;

	public Login(WebService werService) {
		super();
		this.werService = werService;
		this.isLogin = false;
	}

	public void doLogin() {
		werService.login("nacarabali", "1234", new CallBack() {

			@Override
			public void onSuccess(String message) {
				isLogin = true;
				System.out.println(message);
			}

			@Override
			public void onError(String error) {
				isLogin = false;
				System.out.println(error);
			}
		});
	}

	public WebService getWerService() {
		return werService;
	}

	public void setWerService(WebService werService) {
		this.werService = werService;
	}

	public boolean isLogin() {
		return isLogin;
	}

	public void setLogin(boolean isLogin) {
		this.isLogin = isLogin;
	}

}
