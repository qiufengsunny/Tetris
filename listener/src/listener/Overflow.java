package listener;

public class Overflow {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int big = 0x7fffffff;
		System.out.println("big = " + big);
		int bigger = big + 1;
		System.out.println("bigger = " + bigger);

	}

}
