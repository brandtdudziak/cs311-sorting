import java.util.NoSuchElementException;
import java.util.Arrays;
import java.util.Map;
import java.util.HashMap;

/**
 * A priority queue implemented using an unsorted array.
 *
 * @param K the type of the items in the queue
 *
 * @version 0.1 2017-04-17
 */

public class UnsortedArrayPriorityQueue<K> implements PriorityQueue<K>
{
    /**
     * The array that contains the (item, priority) pairs.
     */
    private Entry[] entries;
    // not generic b/c Java doesn't allow arrays of generics

    /**
     * The index into entries for each item in this queue.
     */
    private Map<K, Integer> handles;
    // for each parameter, K, Integer, or Double

    /**
     * The number of items in this queue.
     */
    private int size;

    /**
     * Creates an empty priority queue.
     */
    public UnsortedArrayPriorityQueue()
	{
	    entries = new Entry[2];
	    handles = new HashMap<>();
	}

    /**
     * Removes the item with the lowest priority from this queue and
     * returns its index.
     *
     * @return the item with the lowest priority
     */
    public K extractMin()
    {
	if (size == 0)
	    {
		throw new NoSuchElementException("extract from empty queue");
	    }

	// find min priority and corresponding item
	int i = findMinPriority();
	K item = (K)(entries[i].item);
	
	// swap min entry with last entry
	Entry temp = entries[i];
	entries[i] = entries[size - 1];
	entries[size - 1] = temp;
	// update handle of old last item
	handles.put((K)(entries[i].item), i);
	// remove handle to extracted item
	handles.remove(item);

	// remove last entry
	entries[size - 1] = null;
	size--;

	// we generally have not considered resizing to reduce unused space
	// when something is removed from one of our collections, but this
	// would be the place to do that

	return item;
    }

    /**
     * Returns the index into the entries array of one of the entries
     * with the minimum priority.
     *
     * @return the index of the entry with min priority
     */
    private int findMinPriority()
    {
	double min = Double.POSITIVE_INFINITY;
	int locMin = -1;
	for (int i = 0; i < size; i++)
	    {
		if (entries[i].priority < min)
		    {
			min = entries[i].priority;
			locMin = i;
		    }
	    }

	if (locMin == -1 && size > 0)
	    {
		// must have all had priority +inf; return 1st one
		return 0;
	    }
	else
	    {
		return locMin;
	    }
    }

    /**
     * Returns the lowest priority of all items in this queue.
     *
     * @return the value of the lowest priority
     */
    public double peekMin()
    {
	if (size == 0)
	    {
		throw new NoSuchElementException("extract from empty queue");
	    }

	// find min priority and return it
	int i = findMinPriority();
	return entries[i].priority;
    }

    /**
     * Adds the given item to this queue with the given priority.
     * If the item is already in this queue then the effect is the
     * same as decreasePriority.
     *
     * @param item an item, not null
     * @param pri its priority
     */
    public void add(K item, double pri)
    {
	int i = findItem(item);

	if (i != -1)
	    {
		// item already exists; do decreasePriority
		decreasePriority(item, pri);
	    }
	else
	    {
		// new item -- make sure there's space
		if (entries.length == size)
		    {
			embiggen();
		    }

		// add handle
		handles.put(item, size);

		// add the new item to the end of the array
		entries[size] = new Entry<K>(item, pri);
		size++;
	    }
    }

    /**
     * Returns the index into the entries array of the entry with for
     * the given itsm, or -1 if there is no such entry.
     *
     * @param item an item, not null
     * @return the index of that item
     */
    private int findItem(K item)
    {
	// retrieve handle
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
     * Doubles the size of the array entries refers to and copied
     * the values out of the old array into the same indices in the new
     * array.
     */
    private void embiggen()
    {
	Entry[] bigger = new Entry[entries.length * 2];
	for (int i = 0; i < entries.length; i++)
	    {
		bigger[i] = entries[i];
	    }
	entries = bigger;
    }

    /**
     * Returns the priority of the given item.
     *
     * @param item an item in this queue
     * @return the priority of that item
     */
    public double getPriority(K item)
    {
	int i = findItem(item);

	if (i == -1)
	    {
		throw new NoSuchElementException(String.valueOf(item));
	    }
	else
	    {
		return entries[i].priority;
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

	// check whether item is present and new priority is indeed lower
	if (i != -1 && entries[i].priority > pri)
	    {
		entries[i].priority = pri;
	    }
    }

    /**
     * Determines if this queue contains the item with the given index.
     *
     * @parma item an item; not null
     * @return true if and only if this queue contains the item with that index
     */
    public boolean contains(K item)
    {
	return findItem(item) != -1;
    }

    // --- BEGIN SOLUTION ---

    /**
     * Removes all items with priority greater than or equal to the given
     * value.
     *
     * @param bound a double
     */
    @SuppressWarnings("unchecked")
	public void removeEnd(double bound)
    {
    	for(int i = 0; i < size; i++)
    	{
    		if(entries[i].priority >= bound)
    		{
    			handles.remove(entries[i].item);
    			entries[i] = entries[size - 1];
    			handles.put((K)entries[i].item, i);
    			entries[size - 1] = null;
    			size--;
    			i--;
    		}
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
	return Arrays.asList(entries).subList(0, size).toString();
    }

    /**
     * An entry in this queue.  Contains an item and its priority.
     */
    private static class Entry<K>
    {
	private K item;
	private double priority;
	
	public Entry(K i, double p)
	    {
		item = i;
		priority = p;
	    }
	
	public String toString()
	{
	    return "<" + item + ":" + priority + ">";
	}
    }

    public static void main(String[] args)
    {
	PriorityQueue<String> q = new UnsortedArrayPriorityQueue<>();
	
	q.add("CAI", 9.0);
	q.add("BKK", 8.0);
	q.add("PVG", 7.0);
	q.add("MAN", 7.0);
	q.add("MHT", 6.0);
	q.add("BHM", 4.0);
	q.add("BHX", 3.0);
	q.add("BBB", 1.0);

	q.removeEnd(8.0);

	// expect [<CAI:1.0>, <BKK:2.0>, <PVG:4.0>, <MAN:6.0>]
	System.out.println(q);
    }
}
