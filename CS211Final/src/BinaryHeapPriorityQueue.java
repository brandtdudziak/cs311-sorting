import java.util.NoSuchElementException;
import java.util.Arrays;
import java.util.Map;
import java.util.HashMap;

public class BinaryHeapPriorityQueue<K> implements PriorityQueue<K>
{
    private Entry[] elements;
    private int size;
    private Map<K, Integer> handles;
	
    public BinaryHeapPriorityQueue()
	{
	    elements = new Entry[10];
	    size = 0;
	    handles = new HashMap<>();
	}
	
    public static int left(int i)
    {
	return 2 * i + 1;
    }
	
    public static int right(int i)
    {
	return 2 * i + 2;
    }
	
    public static int parent(int i)
    {
	return (i - 1) / 2;
    }
	
    public double peekMin()
    {
	if (size == 0)
	    {
		throw new NoSuchElementException();
	    }
	return elements[0].priority;
    }
	
    public K extractMin()
    {
	if (size == 0)
	    {
		throw new NoSuchElementException();
	    }

	// get min item from the root
	K item = (K)(elements[0].item);
		
	// swap last element to replace root
	elements[0] = elements[size - 1];
	size--;
	handles.put((K)(elements[0].item), 0);
	handles.remove(item);
		
	// fix any order violations between new root and its descendants
	trickleDown(0);
		
	return item;
    }
	
    /**
     * Restores the heap order property at the given index.
     *
     * @param i an index into elements that might be larger than
     * its two children and other descendants; this must be the
     * only violation of the heap order property anywhere in the heap
     */
    private void trickleDown(int i)
    {
	do
	    {
		int smallest = -1;
		int r = right(i);
		if (r < size && elements[r].compareTo(elements[i]) < 0)
		    {
			// two children; entry at i is out of order with right child;
			// determine which child is the smallest
			int l = left(i);
			if (elements[l].compareTo(elements[r]) < 0)
			    {
				// left child is smallest
				smallest = l;
			    }
			else
			    {
				// right child is smallest
				smallest = r;
			    }
		    }
		else
		    {
			// one or zero children; if one child determine if it is smaller
			int l = left(i);
			if (l < size && elements[l].compareTo(elements[i]) < 0)
			    {
				// left (only) child is smaller
				smallest = l;
			    }
		    }
			
		// if there was an order violation then swap
		if (smallest != -1)
		    {
			swap(i, smallest);
		    }
			
		// move down (or off heap if no order violation)
		i = smallest;
	    } while (i != -1);
    }
	
    public void add(K i, double pri)
    {
	if (contains(i))
	    {
		decreasePriority(i, pri);
	    }
	else
	    {
		// make sure there's enough space (amortized O(1) time)
		if (size == elements.length)
		    {
			embiggen();
		    }
			
		// add new item at the end
		elements[size] = new Entry<K>(i, pri);
		handles.put(i, size);
		size++;
			
		// fix any order property violations between item and its ancestors
		bubbleUp(size - 1);
	    }
    }
	
    /**
     * Restores the heap order property at the given index.
     *
     * @param i an index into elements that might contain and entry
     * with a smaller priority than its parent and ancestors; this must
     * be the only violation of the heap order property anywhere in the heap
     */
    private void bubbleUp(int i)
    {
	// get index of parent
	int p = parent(i);
		
	// repeat until at root or priority is >= parent
	while (i > 0 && elements[i].compareTo(elements[p]) < 0)
	    {
		// swap with parent
		swap(i, p);
			
		// move up
		i = p;
		p = parent(i);
	    }
    }
	
    /**
     * Swaps the given entries.
     *
     * @param i an index in the array of elements
     * @param j an index in the array of elements
     */
    private void swap(int i, int j)
    {
	Entry temp = elements[i];
	elements[i] = elements[j];
	elements[j] = temp;

	handles.put((K)(elements[i].item), i);
	handles.put((K)(elements[j].item), j);
    }
	
    public boolean contains(K item)
    {
	return findItem(item) != -1;
    }
	
    /**
     * Returns the index into elements of the entry containing the given
     * item, or -1 if there is no such item.
     *
     * @param item an item
     * @return the index of that item, or -1
     */
    private int findItem(K item)
    {
	if (handles.containsKey(item))
	    {
		return handles.get(item);
	    }
	else
	    {
		return -1;
	    }
    }

    /**
     * Decreases the priority of the given item to
     * given value.  There is no effect if the item is not present
     * or if the new priority is higher than the old.
     *
     * @param item an item in this queue
     * @param pri its new priority
     */
    public void decreasePriority(K item, double pri)
    {
	int i = findItem(item);
		
	if (i == -1)
	    {
		throw new NoSuchElementException();
	    }
	if (elements[i].priority > pri)
	    {
		elements[i].priority = pri;
		bubbleUp(i);
	    }
    }

    /**
     * Determines if this queue contains the item with the given index.
     *
     * @param item an item; not null
     * @return true if and only if this queue contains the item with that index
     */
    public double getPriority(K item)
    {
	int i = findItem(item);
		
	if (i == -1)
	    {
		throw new NoSuchElementException();
	    }
	return elements[i].priority;
    }

    /**
     * Resizes the elements array to be twice its previous size.
     */
    private void embiggen()
    {
	Entry[] bigger = new Entry[elements.length * 2];
	for (int i = 0; i < elements.length; i++)
	    {
		bigger[i] = elements[i];
	    }
	elements = bigger;
    }
	
    // --- BEGIN SOLUTION ---

    /**
     * Removes all items with priority greater than or equal to the given
     * value.
     *
     * @param bound a double
     */
    public void removeEnd(double bound)
    {
    	for(int i = 0; i < size; i++)
    	{
    		if(elements[i].priority >= bound)
    		{
    			handles.remove(elements[i].item);
    			elements[i] = elements[size - 1];
    			handles.put((K)elements[i].item, i);
    			elements[size - 1] = null;
    			size--;
    			i--;
    		}
    	}
    	for(int i = (size/2); i >= 0; i--)
    	{
    		trickleDown(i);
    	}
    }

    // --- END SOLUTION ---

    /**
     * Returns the number of items in this queue.
     *
     * @return the number of items in this queue
     */
    public int size()
    {
	return size;
    }

    /**
     * Returns a printable representation of this queue.
     *
     * @return a printable representation of this queue
     */
    public String toString()
    {
	return Arrays.asList(elements).subList(0, size).toString();
    }
	
    private static class Entry<K> implements Comparable<Entry>
    {
	private K item;
	private double priority;
		
	public Entry(K i, double p)
	    {
		item = i;
		priority = p;
	    }
		
	public int compareTo(Entry o)
	{
	    return (int)Math.signum(priority - o.priority);
	}
		
	public String toString()
	{
	    return "<" + item + ":" + priority + ">";
	}
    }

    public static void main(String[] args)
    {
	PriorityQueue<String> q = new BinaryHeapPriorityQueue<>();
	
	q.add("CAI", 9.0);
	q.add("BKK", 8.0);
	q.add("PVG", 7.0);
	q.add("MAN", 7.0);
	q.add("MHT", 6.0);
	q.add("BHM", 4.0);
	q.add("BHX", 3.0);
	q.add("BBB", 1.0);

	q.removeEnd(10.0);

	// expect [<CAI:1.0>, <BKK:2.0>, <PVG:4.0>, <MAN:6.0>]
	System.out.println(q);
    }
}
