package mockito;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

class AddMockitoTest {
	
	private Add add;
	private ValidNumber validNumber;
	
	
	@BeforeEach
	public void setUp() {
		validNumber = Mockito.mock(ValidNumber.class);
		add = new Add(validNumber);
	}

	@Test
	void test() {
		add.add(3, 2);
		Mockito.verify(validNumber).isNumber(3);
	}

}
