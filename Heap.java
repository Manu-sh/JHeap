import java.util.Arrays;
import java.util.Queue;
import java.util.Collection;
import java.util.Iterator;
import java.util.NoSuchElementException;

class CollectionNotYetImplemented<T> implements Collection<T> {

	private void not_yet_implemented()
       	{ throw new UnsupportedOperationException(); }

	@Override 
	public boolean retainAll(Collection<?> c) 
	{ not_yet_implemented(); return false; }

	@Override
	public boolean removeAll(Collection<?> c)
	{ not_yet_implemented(); return false; }

	@Override 
	public boolean addAll(Collection<? extends T> c)
	{ not_yet_implemented(); return false; }

	@Override
	public void clear()
	{ not_yet_implemented(); }

	@Override
	public boolean containsAll(Collection<?> c)
	{ not_yet_implemented(); return false; }

	@Override
	public boolean remove(Object c)
	{ not_yet_implemented(); return false; }

	@Override
	public boolean add(T c)
	{ not_yet_implemented(); return false; }

	@Override
	public <T>T[] toArray(T[] a)
	{ not_yet_implemented(); return null; }

	@Override
	public Object[] toArray()
	{ not_yet_implemented(); return null; }

	@Override
	public Iterator<T> iterator()
	{ not_yet_implemented(); return null; }

	@Override
	public boolean contains(Object c)
	{ not_yet_implemented(); return false; }

	@Override
	public boolean isEmpty()
	{ not_yet_implemented(); return false; }

	@Override
	public int size()
	{ not_yet_implemented(); return -1;    }
}

@SuppressWarnings({"rawtypes", "unchecked"})
public class Heap<T extends Comparable<T>> extends CollectionNotYetImplemented<T> implements Iterable<T>, Queue<T> {

	private final int order;
	private int idx; // idx to the last element
	private Comparable[] v;

	public Heap() { this(true); }
	public Heap(boolean descending) {
		this.idx   = 0;
		this.order = descending ? -1 : 1;
		this.v     = new Comparable[2];
	}


	private final void swap(int a, int b) {
		Comparable tmp = v[a];
		v[a] = v[b]; v[b] = tmp;
	}

	private final int compare(int a, int b) {
		return order * v[a].compareTo(v[b]);
	}

	private final void fixUp(int k) {
		while (k > 1 && compare(k>>>1, k) < 0) {
			swap(k>>>1, k);
			k >>>= 1;
		}
	}

	private final void fixDown(int p, int to) {
		for (int j; (j = p << 1) <= to; p = j) {

			// get max
			j = j < to && compare(j, j+1) < 0 ? j+1 : j;
			if (compare(p, j) < 0)
				swap(p, j);
			else break;
		}
	}

	private class HeapIterator implements Iterator<T> {

		private int i;

		@Override public boolean hasNext() {
			return i <= idx;
		}

		@Override public T next() {
			if (!hasNext()) throw new NoSuchElementException();
			return (T)v[i++];
		}
	}


	@Override
	public boolean add(T e) {

		// doubling-halving: growUp
		if (idx >= v.length-1) {
			Comparable[] nv = Arrays.copyOf(v, v.length*2);
			if (nv == null)
				throw new IllegalStateException();
			v = nv;
		}

		v[++idx] = e;
		fixUp(idx);
		return true;
	}

	@Override
	public T remove() {

		if (isEmpty()) throw new NoSuchElementException();

		swap(1, idx);
		fixDown(1, idx-1);
		T ret = (T)v[idx--];

		// doubling-halving: growDown
		if (v.length >>> 2 > idx) {
			Comparable[] nv = Arrays.copyOf(v, v.length/4);
			if (nv == null)
				throw new IllegalStateException();
			v = nv;
		}

		return ret;
	}


	@Override public int size()
	{ return idx; }

	@Override public boolean offer(T e) 
	{ return add(e); }

	@Override public boolean isEmpty()
	{ return idx == 0; }

	@Override public Iterator<T> iterator()
	{ return new HeapIterator(); }

	@Override public void clear()
	{ v = new Comparable[2]; idx = 0; }

	@Override public T peek()
	{ return isEmpty() ? null : (T)v[1]; }

	@Override public T element() 
	{ if (isEmpty()) throw new NoSuchElementException(); return poll(); }

	@Override public T poll()
	{ try { return remove(); } catch (NoSuchElementException e) { return null; } }

}
