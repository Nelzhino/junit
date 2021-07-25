package mockito;

public class Add {

	
	private ValidNumber validNumber;
	private Print print;

	public Add(ValidNumber validNumber) {
		this.validNumber = validNumber;
	}

	public Add(ValidNumber validNumber, Print print) {
		this.validNumber = validNumber;
		this.print = print;
	}
	
	public int add(Object a, Object b) {
		if(validNumber.isNumber(a) && validNumber.isNumber(b)) {
			return (Integer) a + (Integer) b;
		}
		
		return 0;
	}
	
	
	public int addDouble(Object a ) {
		return validNumber.doubleToInt((Double) a) + validNumber.doubleToInt((Double) a);
	}
	
	public void addPrint(Object a, Object b) {
		if(validNumber.isNumber(a) && validNumber.isNumber(b)) {
			print.showMessage( (Integer) a + (Integer) b);
			return;
		}
		print.showError();
	}
	
}
