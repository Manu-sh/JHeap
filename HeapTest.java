import java.util.Random;
import java.util.PriorityQueue;
import java.util.Queue;

class HeapTest {

	// code duplication, just for test, im lazy
	public static double pqueue(int[] set) {

		double start = System.currentTimeMillis();
		Queue<Integer> hp = new PriorityQueue<>();

		for (Integer i : set)
			hp.add(i);

		while (!hp.isEmpty())
			hp.remove();

		return System.currentTimeMillis() - start;
	}

	public static double hqueue(int[] set) {

		double start = System.currentTimeMillis();
		Queue<Integer> hp = new Heap<>();

		for (Integer i : set)
			hp.add(i);

		while (!hp.isEmpty())
			hp.remove();

		return System.currentTimeMillis() - start;
	}

	public static void main(String[] args) {

		final boolean MIN_HEAP = true, MAX_HEAP = false;
		int[] v = new int[250000];
		Random pool = new Random();

		for (int i = 0; i < v.length; i++)
			v[i] = pool.nextInt(1000);

		System.out.println("off: "+pqueue(v));
		System.out.println("unoff: "+hqueue(v));
	}

}
