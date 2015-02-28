package listener;
class Value {
	int i;
}
public class Equavalence {

	public static void main(String[] args) {
		int n1 = 47;
		int n2 = 47;
		System.out.println(n1 == n2);
		System.out.println(n1 != n2);
		Integer n3 = new Integer(47);
		Integer n4 = new Integer(47);
		System.out.println(n3 == n4);
		System.out.println(n3 != n4);
		System.out.println(n3.equals(n4));
		
		Value v1 = new Value();
		Value v2 = new Value();
		v1.i = v2.i = 100;
		System.out.println(v1.equals(v2));
		System.out.println(v1 == v2);
	}

}
