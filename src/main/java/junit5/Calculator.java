package junit5;

public class Calculator {
	
	public int add(int n1, int n2) {
		return n1 + n2;
	}
	
	public int subtract(int n1, int n2) {
		return n1 - n2;
	}
	
	public int multiplicy(int n1, int n2) {
		return n1 * n2;
	}
	
	public int divide(int n1, int n2) {
		if(n2 == 0) throw new ArithmeticException("It can't divide by zero.");
		return n1 / n2;
	}
	
	
	public void longTimeTaskOperation() {
		try {
			Thread.sleep(1000);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

}
