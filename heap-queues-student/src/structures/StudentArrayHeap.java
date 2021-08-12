package structures;

import java.util.Comparator;
import java.util.Iterator;

public class StudentArrayHeap<P, V> extends AbstractArrayHeap<P, V> {

	
	protected StudentArrayHeap(Comparator<P> comparator) {
		super(comparator);
	}

	@Override
	protected int getLeftChildOf(int index) throws IndexOutOfBoundsException{
		if(index < 0) {
			throw new IndexOutOfBoundsException();
		}
		return 2*index+1;
	}

	@Override
	protected int getRightChildOf(int index) throws IndexOutOfBoundsException{
		if(index < 0) {
			throw new IndexOutOfBoundsException();
		}
		return 2*index+2;
	}

	@Override
	protected int getParentOf(int index) throws IndexOutOfBoundsException{
		if(index < 1) {
			throw new IndexOutOfBoundsException();
		}
		return (index-1)/2;
	}

	@Override
	protected void bubbleUp(int index){
		int parent = (index-1)/2;
		Entry<P,V> parentInfo = heap.get(parent);
		Entry<P,V> temp = heap.get(index);
		while((index > 0) && 
				(comparator.compare(parentInfo.getPriority(), temp.getPriority()) < 0)){
		heap.set(index, parentInfo);
		index = parent;
		parent = (index-1)/2;
		parentInfo = heap.get(parent);
		}
		heap.set(index, temp);
	}

	@Override
	protected void bubbleDown(int index) throws IndexOutOfBoundsException{
		if(index < 0) {
			throw new IndexOutOfBoundsException();
		}
		int largerChild;
		Entry<P,V> temp = heap.get(index);
		while(index < (heap.size()/2)) {
			int left = getLeftChildOf(index);
			int right = getRightChildOf(index);
			if(right < heap.size() && 
					(comparator.compare(heap.get(left).getPriority(),heap.get(right).getPriority())<0)) {
				largerChild = right;
			}
			else {
				largerChild = left;
			}
			
			if(comparator.compare(temp.getPriority(), heap.get(largerChild).getPriority()) >=0 ) {
				break;
			}
			heap.set(index, heap.get(largerChild));
			index = largerChild;
	}
		heap.set(index,temp);
}
}

