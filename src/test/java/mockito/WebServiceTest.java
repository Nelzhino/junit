package mockito;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

class WebServiceTest {

	private WebService webService;

	@Mock
	private CallBack callback;

	@BeforeEach
	void setUp() {
		this.webService = new WebService();
		MockitoAnnotations.initMocks(this);
	}

	@Test
	void loginSuccessfullTest() {
		assertTrue(this.webService.checkLogin("nacarabali", "1234"));
	}

	@Test
	void loginWithErrorTest() {
		assertFalse(this.webService.checkLogin("nacarabali", "aaaa"));
	}

	@Test
	void loginTest() {
		this.webService.login("nacarabali", "1234", callback);
		verify(callback).onSuccess("User correct!");
	}
	
	@Test
	void loginErrorTest() {
		this.webService.login("nacarabali", "2321", callback);
		verify(callback).onError("Error!");
	}

}
