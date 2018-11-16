/**
 * A list of Strings.
 *
 * @author Jim Glenn
 * @version 0.2 separated out interface; changed elements to Tasks
 * @version 0.1 ArrayList112 (of Strings) 2015-10-21
 */

public class ArrayList211<E> implements List211<E>
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
     * Creates an empty list of tasks.
     */
    public ArrayList211()
    {
	// start with an array of 1 element
	elements = new Object[1];

	// no items on the list
	count = 0;
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
		resize();
	    }

	// next available index is equal to size of list
	elements[count] = toAdd;

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
	return (E)elements[i];
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
	elements[i] = t;
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
	E removed = (E)elements[i];

	// copy back to fill the hole
	for (int copyTo = i; copyTo < count - 1; copyTo++)
	    {
		elements[copyTo] = elements[copyTo + 1];
	    }

	// null out last reference to help garbage collector
	elements[count - 1] = null;

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
		resize();
	    }

	// copy everything out of the way
	for (int copyTo = count; copyTo >= i + 1; copyTo--)
	    {
		elements[copyTo] = elements[copyTo - 1];
	    }

	// add the new element at index i
	elements[i] = t;
	count++;
    }

    private void resize()
    {
	// make new, bigger array
	Object[] bigger = new Object[count * 2];

	// copy from old, too small array to new, bigger array
	for (int i = 0; i < count; i++)
	    {
		bigger[i] = elements[i];
	    }

	// rememer new, bigger array
	elements = bigger;
    }

    // --- BEGIN SOLUTION ---

    /**
     * Modifies this list to contain each element that it currently
     * has twice in succession.  So if the list is [1, 2, 3, 3] then
     * after invoking this method the list will be [1, 1, 2, 2, 3, 3, 3, 3].
     */
    public void doubleUp()
    {
    	Object[] arr = new Object[count*2];
    	for(int i = 0; i < count; i++)
    	{
    		arr[i*2] = elements[i];
    		arr[(i*2)+1] = elements[i];
    	}
    	elements = arr;
    	count *= 2;
    }

    // --- END SOLUTION ---

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
		buf.append(elements[i].toString());
	    }
	buf.append("]");
	return buf.toString();
    }

    /**
     * Very basic test for doubleUp.
     */
    public static void main(String[] args)
    {
	List211<Integer> l = new ArrayList211<Integer>();
	l.add(1);
	l.add(2);
	l.add(3);
	l.add(4);
	l.doubleUp();
	System.out.println(l); // expect [1, 1, 2, 2, 3, 3, 4, 4]
    }
	
}
