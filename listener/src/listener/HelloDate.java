
package listener;

import listener.Number;



public class HelloDate {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Number n1 = new Number();
		Number n2 = new Number();
		n1.i = 9;
		n2.i = 47;
		System.out.println(n1.i + " " + n2.i);
		n1.i = n2.i;
		System.out.println(n1.i + " " + n2.i);
		n1.i = 27;
		System.out.println(n1.i + " " + n2.i);
		
	
	}

}
