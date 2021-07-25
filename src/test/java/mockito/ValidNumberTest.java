package mockito;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ValidNumberTest {
	
	private ValidNumber validNumber;

	@BeforeEach
	void setUp() {
		this.validNumber = new ValidNumber();
	}

	@AfterEach
	void tearDown() {
		this.validNumber = null;
	}

	@Test
	void isNumberTest() {
		assertTrue(validNumber.isNumber(5));
	}

	@Test
	void isNegativeNumberTest() {
		assertFalse(validNumber.isNumber(-5));
	}
	@Test
	void isStringNumberTest() {
		assertFalse(validNumber.isNumber("5"));
	}

	
	@Test
	void isZeroNegativeFormTest() {
		assertTrue(validNumber.isZero(-57));
	}
	@Test
	void isZeroStringFormTest() {
		assertFalse(validNumber.isZero("5"));
	}
	@Test
	void isZeroPositiveFormTest() {
		assertTrue(validNumber.isZero(50));
	}
	@Test
	void isZeroTest() {
		assertThrows(ArithmeticException.class, () -> validNumber.isZero(0));
	}
	
	@Test
	void doubleToInt() {
		assertEquals(3, validNumber.doubleToInt(3.9999));
	}
	
	@Test
	void doubleToIntError() {
		assertEquals(0, validNumber.doubleToInt("3.9999"));
	}

}
