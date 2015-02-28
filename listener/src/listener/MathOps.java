package listener;
import java.util.*;

public class MathOps {
	static void printInt(String s, int i) {
		System.out.println(s + " = " + i);
	}
	static void printFloat(String s, float f){
		System.out.println(s + " = "  + f);
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Random rand = new Random();
		int i, j, k;
		j = rand.nextInt(100) + 1;
		k = rand.nextInt(100) + 1;
		printInt("j",j); printInt("k", k);
		i = j + k; printInt("j + k", i);
		i = j - k; printInt("j - k", i);
		i = j * k; printInt("j * k", i);
		i = j / k; printInt("j / k", i);
		j %= k; printInt("j %= k", j);
		float u, v, w;
		v = rand.nextFloat();
		w = rand.nextFloat();
		printFloat("v", v); printFloat("w", w);
		u = v + w; printFloat("v + w", u);
		u = v - w; printFloat("v - w", u);
		u = v * w; printFloat("v * w", u);
		u = v / w; printFloat("v / w", u);
		u += v; printFloat("u += v", u);
		u -= v; printFloat("u -= v", u);
		u *= v; printFloat("u *= v", u);
		u /= v; printFloat("u /= v", u);

	}

}
