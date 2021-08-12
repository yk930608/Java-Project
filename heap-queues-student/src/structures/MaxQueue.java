package structures;

import comparators.IntegerComparator;

import java.util.Comparator;
import java.util.Iterator;

public class MaxQueue<V> implements PriorityQueue<Integer, V> {

	private StudentArrayHeap<Integer,V> heap;
	private IntegerComparator forCompare = new IntegerComparator();
	
	public MaxQueue() {
		
		heap = new StudentArrayHeap<Integer,V>(forCompare);
	}
	@Override
	public PriorityQueue<Integer, V> enqueue(Integer priority, V value) {
		heap.add(priority, value);
		return this;
	}

	@Override
	public V dequeue() {
		// TODO Auto-generated method stub
		return heap.remove();
	}

	@Override
	public V peek() {
		// TODO Auto-generated method stub
		return heap.peek();
	}

	@Override
	public Iterator<Entry<Integer, V>> iterator() {
		// TODO Auto-generated method stub
		return heap.asList().iterator();
	}

	@Override
	public Comparator<Integer> getComparator() {
		// TODO Auto-generated method stub
		return heap.getComparator();
	}

	@Override
	public int size() {
		// TODO Auto-generated method stub
		return heap.size();
	}

	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return heap.isEmpty();
	}
}
