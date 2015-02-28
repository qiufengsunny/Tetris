package listener;

public class IfElse {
	
	static int test(int testval, int target){
		int result = 0;
		if(testval > target)
			result = 1;
		else if (testval < target)
			result = -1;
		else
			result = 0;
		return result;
		
	}


	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println(test(10, 5));
		System.out.println(test(5, 10));
		System.out.println(test(5, 5));
	}

}
