import java.util.NoSuchElementException;
import java.util.Map;
import java.util.HashMap;

public class BinaryHeapPriorityQueue<T> implements PriorityQueue<T> {

	//Handles - item, location in array
	Map<T, Integer> handles;
	private Entry[] elements;
	private int size;
	
	public BinaryHeapPriorityQueue()
	{
		handles = new HashMap<>();
		elements = new Entry[10];
		size = 0;
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
	
	@SuppressWarnings("unchecked")
	public T extractMin()
	{
		if(size == 0)
		{
			throw new NoSuchElementException();
		}
		T item = (T)elements[0].item;
		
		elements[0] = elements[size-1];
		handles.put((T)elements[size-1].item, 0);
		handles.remove(item);
		size--;
		
		trickleDown(0);
		
		return item;
	}
	
	public double peekMin()
	{
		if(size == 0)
		{
			throw new NoSuchElementException();
		}
		return elements[0].priority;
	}
	
	@SuppressWarnings("unchecked")
	public void add(T item, double pri)
	{
		if(contains(item))
		{
			decreasePriority(item, pri);
		}
		else 
		{
			if(size == elements.length)
			{
				embiggen();
			}
			elements[size] = new Entry(item, pri);
			handles.put((T)elements[size].item, size);
			size++;

			bubbleUp(size - 1);
		}
	}
	
	public double getPriority(T item)
	{
		int i = findItem(item);
		
		if(i == -1)
		{
			throw new NoSuchElementException();
		}
		return elements[i].priority;
	}
	
	public void decreasePriority(T item, double pri)
	{
		int i = findItem(item);
				
		if(i == -1)
		{
			throw new NoSuchElementException();
		}
		if(elements[i].priority > pri)
		{
			elements[i].priority = pri;
			bubbleUp(i);
		}
	}
	
	private int findItem(T item)
	{
		int i = -1;
		if(handles.containsKey(item))
		{
			i = handles.get(item);
		}
		return i;
	}
	
	public boolean contains(T item)
	{
		return findItem(item) != -1;
	}
	
	public int size()
	{
		return size;
	}
	
	private void embiggen()
	{
		Entry[] bigger = new Entry[elements.length * 2];
		for(int i = 0; i < elements.length; i++)
		{
			bigger[i] = elements[i];
		}
		elements = bigger;
	}
	
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
	
	@SuppressWarnings("unchecked")
	private void swap(int i, int j)
    {
		handles.put((T)elements[i].item, j);
		handles.put((T)elements[j].item, i);
		
		Entry temp = elements[i];
		elements[i] = elements[j];
		elements[j] = temp;
    }
	
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
	
	private static class Entry implements Comparable<Entry>
    {
		private Object item;
		private double priority;

		public Entry(Object i, double p)
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
	
}
