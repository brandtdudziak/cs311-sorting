import java.util.Arrays;

/**
 * A list of Strings.
 *
 * @author Jim Glenn
 * @version 0.2 separated out interface; changed elements to Tasks
 * @version 0.1 ArrayList112 (of Strings) 2015-10-21
 */

public class WraparoundArrayList211<E> implements List211<E>
{
    /**
     * The array that holds the elements in this list.  The element at
     * index i on the list will be in index i of the array,
     */
    private Object[] elements;

    /**
     * The number of elements in this list.
     */
    private int count;

    /**
     * The index in the array of the element at the front of the list.
     */
    private int start;

    /**
     * Creates an empty list of tasks.
     */
    public WraparoundArrayList211()
    {
	// start with an array of 1 element
	elements = new Object[1];

	// no items on the list
	count = 0;

	// start index is index 0
	start = 0;
    }

    /**
     * Adds the given task to the end of this list.
     *
     * @param toAdd a task to add
     */
    public void add(E toAdd)
    {
	if (count == elements.length)
	    {
		embiggen();
	    }

	// next available index is equal to size of list
	elements[(start + count) % elements.length] = toAdd;

	// rememnber we have one more item than before
	count++;
    }

    /**
     * Returns the number of elements in this list.
     *
     * @return the number of elements in this list
     */
    public int size()
    {
	return count;
    }

    /**
     * Returns the element at the given position in this list.
     *
     * @param i a 0-based index in this list
     * @return the item at that position
     */
    public E get(int i)
    {
	if (i < 0 || i >= count)
	    {
		throw new IndexOutOfBoundsException("invalid index: " + i);
	    }

	// element at position i in the list is stored at index i in the array
	return (E)elements[(start + i) % elements.length];
    }

    /**
     * Replaces the element at the given position in this list with the
     * given task.
     *
     * @param i a 0-based index in this list
     * @param t a task
     */
    public void set(int i, E t)
    {
	if (i < 0 || i >= count)
	    {
		throw new IndexOutOfBoundsException("invalid index: " + i);
	    }

	// element at position i in the list is stored in index i in the array
	elements[(start + i) % elements.length] = t;
    }

    /**
     * Removes the element at the given position in this list.  Elements
     * after that position are moved back one space to fill the gap.
     * 
     * @param i a 0-based index in this list
     * @return the element that was removed
     */
    public E remove(int i)
    {
	if (i < 0 || i >= count)
	    {
		throw new IndexOutOfBoundsException("invalid index: " + i);
	    }

	// remember element we're going to remove
	E removed = (E)elements[(start + i) % elements.length];

	// null out last reference to help garbage collector
	elements[(start + i) % elements.length] = null;

	if (i != 0 && i != count - 1)
		{
		for(int k = i; k < count - 1; k++)
		{
			elements[(start+k)%elements.length] = elements[(start+k+1)%elements.length];
		}
		//throw new UnsupportedOperationException("removing from middle to do for HW4");
		}
	
	if (i == 0)
	    {
		start = (start + 1) % elements.length;
	    }

	// one less element than before
	count--;

	// return element that was removed
	return removed;
    }

    /**
     * Inserts the given element at the given position in this list.
     * Elements at or after that position are moved back one position to
     * make room.
     *
     * @param i a 0-based index in this list, or the smallest invalid index
     * @param t a task
     */
    public void add(int i, E t)
    {
	if (i < 0 || i > count)
	    {
		throw new IndexOutOfBoundsException("invalid index: " + i);
	    }

	if (count == elements.length)
		{
		embiggen();
		}
	
	if (i != 0 && i != count)
	    {
		for(int k = count; k > i; k--)
		{
			elements[(start+k)%elements.length] = elements[(start+k-1)%elements.length];
		}
		elements[(start+i)%elements.length] = t;
		count++;
		//throw new UnsupportedOperationException("adding to middle to do for HW4");
	    }

	if (i == 0)
	    {
		start = (start + (elements.length - 1)) % elements.length;
		elements[start] = t;
		count++;
	    }
	else if (i == count)
	    {
		add(t);
	    }
    }

    private void embiggen()
    {
	// make new, bigger array
	Object[] bigger = new Object[count * 2];

	// copy from old, too small array to beginning of new, bigger array
	for (int i = 0; i < count; i++)
	    {
		bigger[i] = get(i);
	    }

	// remember new, bigger array
	elements = bigger;
	start = 0;
    }

    /**
     * Returns a printable representation of this list.
     *
     * @return a printable representation of this list
     */
    public String toString()
    {
	StringBuilder buf = new StringBuilder("[");
	for (int i = 0; i < count; i++)
	    {
		if (i > 0)
		    {
			buf.append(", ");
		    }
		buf.append(get(i).toString());
	    }
	buf.append("]");
	return buf.toString();
    }
    
    /**
     * Removes all items in a list between given indices.
     *
     * @param from a 0-based index in this list that indicates the start of the range (inclusive)
     * @param to a 0-based index in this list that indicates the end of the range (exclusive)
     */
    public void removeRange(int from, int to)
    {
    	if (from < 0 || from > count)
	    {
		throw new IndexOutOfBoundsException("invalid from index: " + from);
	    }
    	if (to < 0 || to > count || to < from)
	    {
		throw new IndexOutOfBoundsException("invalid to index: " + to);
	    }
    	for (int i = from; i < to; i++)
    	{
    		remove(from);
    	}
    }
    
    /**
     * Inserts all the items of list l at index i.
     *
     * @param i a 0-based index in which to insert the elements of the other list
     * @param l a generic List211 which contains the elements to insert
     */
    public void insert(int i, List211<E> l)
    {
    	if (i < 0 || i > count)
	    {
		throw new IndexOutOfBoundsException("invalid index: " + i);
	    }
    	count+=l.size();
    	if(count >= elements.length)
    	{
    		embiggen();
    	}
    	for(int k = count; k > i; k--)
		{
			elements[(start+k)%elements.length] = elements[(start+k-l.size()+elements.length)%elements.length];
		}
    	for(int k = 0; k < l.size(); k++)
    	{
    		elements[(start+i+k)%elements.length] = l.get(k);
    	}
    }

    /**
     * Test driver for ArrayListOfEs211.
     */
    public static void main(String[] args)
    {
	List211<String> l = new WraparoundArrayList211<String>();
	
	List211<String> m = new WraparoundArrayList211<String>();
	m.add("LAX");
	m.add("ORD");
	m.add("BDL");
	
	l.add("JFK");
	l.add("SEA");
	l.add("JNB");
	System.out.println(l); // expect JFK, SEA, JNB
	
	l.insert(1, m);
	System.out.println(l); // expect [JFK, LAX, ORD, BDL, SEA, JNB]
	l.removeRange(1, 4);
	
	System.out.println(l.get(0)); // expect JFK
	System.out.println(l.get(1)); // expect SEA
	
	l.set(0, "BWI");
	l.set(1, "PDX");
	System.out.println(l); // expect BWI, PDX, JNB
	
	l.remove(0);
	System.out.println(l); // expect PDX, JNB
	l.remove(1); 
	System.out.println(l); // expect PDX
	l.remove(0);
	System.out.println(l); // expect []
	
	l.add(0, "SFO");
	l.add(0, "HNL");
	l.add(2, "GRU");
	
	System.out.println(l); // expect HNL, SFO, GRU
	
	System.out.println(l.size()); // expect 3

	l.remove(0);
	l.remove(0);
	l.remove(0);
	System.out.println(l);  // expect []
	
	l.add("SEA");
	System.out.println(l);  // expect [SEA]

	l.add("PEK");
	System.out.println(l); // expect [SEA, PEK]

	l.set(1, "PVG");
	System.out.println(l.get(1)); // expect PVG

	l.remove(0);
	System.out.println(l);  // expect [PVG]
	System.out.println(l.get(0)); // expect PVG

	l.add(0, "MEL");
	System.out.println(l); // expect [MEL, PVG]
    }
}