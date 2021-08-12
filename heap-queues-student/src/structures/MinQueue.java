package structures;

import comparators.IntegerComparator;
import comparators.ReverseIntegerComparator;

import java.util.Comparator;
import java.util.Iterator;

public class MinQueue<V> implements PriorityQueue<Integer, V> {

	private StudentArrayHeap<Integer,V> heap;
	private ReverseIntegerComparator forCompare = new ReverseIntegerComparator();
	
	public MinQueue() {
		
		heap = new StudentArrayHeap<Integer,V>(forCompare);
	}
	@Override
	public PriorityQueue<Integer, V> enqueue(Integer priority, V value) {
		heap.add(priority, value);
		return this;
	}

	@Override
	public V dequeue() {
		return heap.remove();
	}

	@Override
	public V peek() {
		return heap.peek();
	}

	@Override
	public Iterator<Entry<Integer, V>> iterator() {
		return heap.asList().iterator();
	}

	@Override
	public Comparator<Integer> getComparator() {
		return heap.getComparator();
	}

	@Override
	public int size() {
		return heap.size();
	}

	@Override
	public boolean isEmpty() {
		return heap.isEmpty();
	}
}

