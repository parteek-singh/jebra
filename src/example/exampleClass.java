package example;

public class exampleClass {
	
	public int action(String flag , int a1,int a2) {
		
		int result  = -1;
		if(flag == "sum") {
			result = sum( a1,a2);
		}
		else if(flag == "minus"){
			result = minus( a1,a2);
		}
		else {
			result = a1 * a2;
		}
		return result;
	}

	public int sum(int a1,int a2) {
		int result = a1+a2;
		return result;
	}
	
	public int minus(int a1,int a2) {
		int result = a1-a2;
		return result;
	}
	
}
