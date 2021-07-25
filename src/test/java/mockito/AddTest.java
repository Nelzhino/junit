package mockito;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;


class AddTest {

	@InjectMocks
	private Add add;

	@Mock
	private ValidNumber validNumber;
	@Mock
	private Print print;
	
	@Mock 
	private List<String> mockList = new ArrayList<String>();

	@Spy 
	private List<String> spyList = new ArrayList<String>();
	
	
	@Captor
	private ArgumentCaptor<Integer> argumentCaptor;

	@BeforeEach
	public void setUp() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void addtest() {
		when(validNumber.isNumber(3)).thenReturn(true);
		assertTrue(validNumber.isNumber(3));

	}

	@Test
	public void addExceptionTest() {
		when(validNumber.isZero(0)).thenThrow(new ArithmeticException("The number must not be zero."));
		Exception exception = null;
		
		try {
			validNumber.isZero(0);
		} catch (ArithmeticException e) {
			exception = e;
		}
		
		assertNotNull(exception);
	}
	
	
	
	
	@Test
	public void addCallRealMethodTest() {
		when(validNumber.isZero(3)).thenCallRealMethod();
		assertTrue(validNumber.isZero(3));
	}

	@Test
	public void addDoubleToIntAnswerThen() {
		when(validNumber.doubleToInt(7.7)).thenAnswer(new Answer<Integer>() {

			@Override
			public Integer answer(InvocationOnMock invocation) throws Throwable {
				return 7;
			}
		});
		
		assertEquals(14, this.add.addDouble(7.7));
	}
	
	@Test
	public void addPrintTest() {
		when(validNumber.isNumber(4)).thenReturn(true);
		when(validNumber.isNumber(4)).thenReturn(true);
		
		this.add.addPrint(4, 4);
		
//		verify(validNumber, times(2)).isNumber(4);
		verify(validNumber, atLeast(1)).isNumber(4);
		
		verify(print).showMessage(8);
		verify(print, never()).showError();
	}
	
	@Test
	public void captorTest() {
		when(validNumber.isNumber(4)).thenReturn(true);
		when(validNumber.isNumber(4)).thenReturn(true);
		
		this.add.addPrint(4, 4);
		
		verify(print).showMessage(argumentCaptor.capture());
		
		assertEquals(argumentCaptor.getValue().intValue(), 8);
		
	}
	
	
	@Test
	public void spyListTest() {
		spyList.add("2");
		spyList.add("3");
		
		verify(spyList).add("2");
		verify(spyList).add("3");
		
		assertEquals(2, spyList.size());
	}
	
	
	@Test
	public void mockListTest() {
		mockList.add("1");
		mockList.add("2");
		
		verify(mockList).add("1");
		verify(mockList).add("2");
		
		when(mockList.size()).thenReturn(2);
		
		assertEquals(2, mockList.size());
	}

	
	
}
