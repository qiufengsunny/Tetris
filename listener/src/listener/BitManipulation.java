package listener;
import java.util.*;

public class BitManipulation {
	static void printBinaryInt(String s, int i){
		System.out.println(
				s + " ,int: " + i + ", binary: ");
		System.out.println("   ");
		for(int j = 31; j >= 0; j--)
			if(((1 << j) & i) != 0)
				System.out.print("1");
			else
				System.out.print("0");
		System.out.println();
	}
	static void printBinaryLong(String s, long l){
		System.out.println(s + ", long:" + l + ", binary: ");
		System.out.println("    ");
		for(int j = 63; j >=0; j--)
			if(((1 << j) & j )!= 0)
				System.out.print("1");
			else
				System.out.println("0");
		System.out.println();
				
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Random rand = new Random();
		int i = rand.nextInt();
		int j = rand.nextInt();
		printBinaryInt("-1", -1);
		printBinaryInt("+1", +1);
		int maxpos = 2147483647;
		printBinaryInt("maxpos", maxpos);
		int maxneg = -214748148;
		printBinaryInt("maxneg", maxneg);
		printBinaryInt("i", i);
		printBinaryInt("~i", i);
		printBinaryInt("-i", -i);
		printBinaryInt("j", j);
		printBinaryInt("i & j", i & j);
		printBinaryInt("i | j", i | j);
		printBinaryInt("i ^ j", i ^ j);
		printBinaryInt("i << 5", i << 5);
		printBinaryInt("i >> 5", i >> 5);
		printBinaryInt("(~i) >> 5",(~i) >> 5);
		printBinaryInt("(-i) >>> 5", (-i)>>> 5);
		
		long l = rand.nextLong();
		long m = rand.nextLong();
		printBinaryLong("-1L", -1L);
		printBinaryLong("+1L", +1L);
		long ll = 9223372036854775807L;
		printBinaryLong("maxpos", ll);
		long lln = -9223372036854775808L;
		printBinaryLong("maxneg", lln);
		printBinaryLong("l", l);
		printBinaryLong("~l", ~l);
		printBinaryLong("-l",-l);
		printBinaryLong("m", m);
		printBinaryLong("l & m", l & m);
		printBinaryLong("l | m", l | m);
		printBinaryLong("l ^ m", l ^ m );
		printBinaryLong("l << 5", l << 5);
		printBinaryLong("l >> 5", l >> 5);
		printBinaryLong("(~l) >>> 5", (~l) >>> 5);
		

	}

}
