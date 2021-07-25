package mockito;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

class LoginTest {
	
	@InjectMocks
	private Login login;
	
	@Mock
	private WebService webService;
	
	@Captor
	private ArgumentCaptor<CallBack> captorCallback;
	

	@BeforeEach
	void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	void doLoginTest() {
		
		doAnswer(new Answer<Object>() {

			@Override
			public Object answer(InvocationOnMock invocation) throws Throwable {
				String user = invocation.getArgument(0);
				assertEquals("nacarabali", user);
				String password = invocation.getArgument(1);
				assertEquals("1234", password);
				CallBack callback = invocation.getArgument(2);
				callback.onSuccess("OK");
				
				
				return null;
			}
			
		}).when(webService).login(anyString(), anyString(), any(CallBack.class));
		
		
		login.doLogin();
		verify(webService, times(1)).login(anyString(), anyString(), any(CallBack.class));
		assertEquals(login.isLogin(), true);
		
	}
	
	
	@Test
	void doLoginFailTest() {
		
		doAnswer(new Answer<Object>() {
			
			@Override
			public Object answer(InvocationOnMock invocation) throws Throwable {
				String user = invocation.getArgument(0);
				assertEquals("nacarabali", user);
				String password = invocation.getArgument(1);
				assertEquals("1234", password);
				CallBack callback = invocation.getArgument(2);
				callback.onError("Error");
				
				
				return null;
			}
			
		}).when(webService).login(anyString(), anyString(), any(CallBack.class));
		
		
		login.doLogin();
		verify(webService, times(1)).login(anyString(), anyString(), any(CallBack.class));
		assertEquals(login.isLogin(), false);
		
	}
	
	
	@Test
	void doLoginCaptorTest() {
		login.doLogin();
		verify(webService, times(1)).login(anyString(), anyString(), captorCallback.capture());
		assertEquals(login.isLogin(), false);
		
		CallBack callBack = captorCallback.getValue();
		callBack.onSuccess("OK!");
		assertEquals(login.isLogin(), true);
		
		
		callBack.onError("Error!");
		assertEquals(login.isLogin(), false);
		
	}

}
