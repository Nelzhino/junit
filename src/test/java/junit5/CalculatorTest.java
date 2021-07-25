package junit5;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTimeout;

import java.time.Duration;
import java.util.stream.Stream;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class CalculatorTest {

	private Calculator calculator;
	private Calculator calculatorNull;

	@BeforeEach
	public void setUp() {
		this.calculator = new Calculator();
	}

	@AfterEach
	public void tearDown() {
		// Execute after each test.
	}

	@Test
	public void testCalculatorNotNullTest() {
		assertNotNull(calculator, "Calculator must not be null");
	}

	@Test
	public void testCalculatorNullTest() {
		assertNull(calculatorNull, "Calculator must be null");
	}

	@Test
	void testAdd() {
		assertEquals(30, calculator.add(10, 20), "The result was not expected.");
	}

	@Test
	void testSubtractNegativeForm() {
		assertEquals(-20, calculator.subtract(10, 30), "The result was not expected.");
	}

	@Test
	void testSubtractPositiveForm() {
		assertEquals(20, calculator.subtract(30, 10), "The result was not expected.");
	}

	@Test
	void testDividePostiveForm() {
		assertEquals(3, calculator.divide(30, 10), "The result was not expected.");
	}

	@Test
	void testDivideNegativeForm() {
		assertEquals(-2, calculator.divide(-10, 5), "The result was not expected.");
	}

	@Test
	@DisplayName("Method throw exception ")
	void testDivideByZero() {
		assertThrows(ArithmeticException.class, () -> calculator.divide(10, 0), "Don't divide by zero");
	}

	@Test
	@DisplayName("Assert all ")
	void testAddByAssertAll() {
		assertAll(() -> assertEquals(30, calculator.add(20, 10)),
				() -> assertFalse(calculator.add(20, 11) % 2 == 0 ? true : false),
				() -> assertNotEquals(11, calculator.subtract(11, 23)));
	}

	@ParameterizedTest(name = "{index} => a={0}, b={1}, c={2}")
	@MethodSource("addProviderData")
	public void addParameterizedTest(int a, int b, int sum) {
		assertEquals(sum, calculator.add(a, b));
	}

	private static Stream<Arguments> addProviderData() {
		return Stream.of(Arguments.of(6, 2, 8), Arguments.of(-6, -2, -8), Arguments.of(6, -2, 4),
				Arguments.of(-6, 2, -4), Arguments.of(6, 0, 6));

	}

	@Test
	public void timeOutTest() {
		assertTimeout(Duration.ofMillis(1200), () -> {
					calculator.longTimeTaskOperation();
					});
	}

}
