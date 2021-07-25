package mockito;

public class ValidNumber {

	public ValidNumber() {
		super();
	}
	
	
	public boolean isNumber(Object obj) {
		
		if(obj instanceof Integer 
				&& (Integer) obj >= 0 && (Integer) obj < 10) {
			return true;
		} else {
			return false;
		}
		
		
	}
	
	public boolean isZero(Object obj) {
		if(obj instanceof Integer ) {
			if( (Integer) obj == 0 ) 
				throw new ArithmeticException("The numbers must not be zero.");
			else return true;
		} else {
			return false;
		}
		
	}
	
	
	public  Integer doubleToInt(Object obj) {
		if(obj instanceof Double ) {
			return ((Double) obj).intValue();
		} else {
			return 0;
		}
	}

	
	
}
