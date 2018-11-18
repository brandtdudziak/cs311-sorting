public class Heap {

    int[] my_heap;
    int count = 0;

    public boolean isEmpty() {
	return (count == 0);
    }

    public void insert(int value) {
	count++;
	my_heap[count] = value;
	siftUp(count);
    }

    public void siftUp(int pos) {
	int temp = my_heap[pos];
	if(pos != 1 && my_heap[pos] > my_heap[pos/2]) {
	    my_heap[pos] = my_heap[pos/2];
	    my_heap[pos/2] = temp;
	    siftUp(pos/2);
	}
    }

    public int deleteMax() {
	if(count == 0) {
	    throw new EmptyHeapException("Error: trying to delete from an empty Heap");
	}

	int temp = my_heap[1];
	my_heap[1] = my_heap[count];
	count--;
	siftDown(1);
	return temp;
    }

    public void siftDown(int pos) {
	if(2 * pos > count) return;

	if(2 * pos == count) {
	    if(my_heap[2 * pos] > my_heap[pos]) {
		swap(pos, 2 * pos);
	    }
	}
	else {
	    int larger;
	    if(my_heap[2 * pos] >= my_heap[2 * pos + 1]) larger = 2 * pos;
	    else larger = 2 * pos + 1;
	    
	    if(my_heap[pos] < my_heap[larger]) {
		swap(pos, larger);
		siftDown(larger);
	    }
	}
    }

    public void swap(int i, int j) {
	int temp = my_heap[i];
	my_heap[i] = my_heap[j];
	my_heap[j] = temp;
    }


    //the heap will start at position 1 of the array
    //this makes the math slightly easier
    public Heap(int[] values) {

	count = values.length;
	my_heap = new int[count + 1];
	for(int i = 0; i < count; i++) {
	    my_heap[i + 1] = values[i];
	}


	for(int i = count/2; i > 0; i--) {
	    siftDown(i);
	}
    }


}

class EmptyHeapException extends RuntimeException {

    public EmptyHeapException() {
    }

    public EmptyHeapException(String message) {
	super(message);
    }

}