/**
 * An implementation of the List interface using a doubly-linked list with
 * dummy head and tail nodes.
 *
 * @author Jim Glenn
 * @version 0.1 2017-02-14  J &hearts; K
 */
public class LinkedList211<E> implements List211<E>
{
    /**
     * The dummy head node for this list.
     */
    private Node<E> head;

    /**
     * The dummy tail node for this list.
     */
    private Node<E> tail;

    /**
     * The number of non-dummy nodes in this list.
     */
    private int count;

    /**
     * Creates an empty list.
     */
    public LinkedList211()
	{
	    // create dummy nodes
	    head = new Node<E>(null);
	    tail = new Node<E>(null);

	    // link dummy nodes together
	    head.next = tail;
	    tail.prev = head;

	    count = 0;
	}

    /**
     * Adds the given task to the end of this list.
     *
     * @param toAdd a task to add
     */
    public void add(E toAdd)
    {
	// get reference to next-to-last node
	Node<E> nextToLast = tail.prev;

	// make new node, linked to next-to-last and dummy tail
	Node<E> newNode = new Node<E>(toAdd, tail, nextToLast);

	// link next-to-last to new node
	nextToLast.next = newNode;

	// link tail to new node
	tail.prev = newNode;

	// increment count
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

	// start at 1st data node
	Node<E> curr = head.next;

	// step through j next links to get to correct data node
	for (int j = 0; j < i; j++)
	    {
		curr = curr.next;
	    }

	// return the data in the node we ended up at
	return curr.data;
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

	// start at 1st data node
	Node<E> curr = head.next;

	// step through j next links to get to correct data node
	for (int j = 0; j < i; j++)
	    {
		curr = curr.next;
	    }

	// set the data in the node we ended up at
	curr.data = t;
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

	E removed;
	if (i == 0)
	    {
		// remember removed item
		removed = head.next.data;

		// get node after one to remove
		Node<E> after = head.next.next;
		
		// link around node to remove
		head.next = after;
		after.prev = head;
	    }
	else if (i == count - 1)
	    {
		// remember removed item
		removed = tail.prev.data;
		
		// get node before one to remove
		Node<E> before = tail.prev.prev;

		// link around node to remove
		before.next = tail;
		tail.prev = before;
	    }
	else
	    {
		// start at 1st data node
		Node<E> curr = head.next;
		
		// step through j next links to get to correct data node
		for (int j = 0; j < i; j++)
		    {
			curr = curr.next;
		    }

		// remember data in node to delete
		removed = curr.data;
		
		// set references to nodes before and after one to delete
		Node<E> before = curr.prev;
		Node<E> after = curr.next;

		// link around node to delete
		before.next = after;
		after.prev = before;
	    }
	
	count--;
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
    public void add(int i, E toAdd)
    {
	if (i < 0 || i > count)
	    {
		throw new IndexOutOfBoundsException("invalid index: " + i);
	    }

	if (i == 0)
	    {
		// remember node after one to add
		Node<E> after = head.next;

		// make new node
		Node<E> newNode = new Node<E>(toAdd, after, head);

		// link new node into list
		head.next = newNode;
		after.prev = newNode;

		// update count
		count++;
	    }
	else if (i == count)
	    {
		add(toAdd);
	    }
	else
	    {
		Node<E> curr = head.next;
		for (int j = 0; j < i; j++)
		    {
			curr = curr.next;
		    }
		Node<E> before = curr.prev;

		Node<E> newNode = new Node<E>(toAdd, curr, before);
		before.next = newNode;
		curr.prev = newNode;

		count++;
	    }
    }
    /**
     * Moves the items from the given list into the given position in this
     * list.  The other list is emptied as a result of this operation.
     *
     * @param i an index between 0 and the length of this list, inclusive
     * @param other a list; not null
     */
    public void move(int i, LinkedList211<E> other)
    {
    	if (i < 0 || i > count)
	    {
		throw new IndexOutOfBoundsException("invalid index: " + i);
	    }
    	
    	Node<E> before = this.head;
    	Node<E> start = other.head.next;
    	Node<E> end = other.tail.prev;
    	for(int j = 0; j < i; j++)
    	{
    		before = before.next;
    	}
    	Node<E> after = before.next;
    	
    	before.next = start;
    	start.prev = before;
    	
    	after.prev = end;
    	end.next = after;
    	
    	this.count += other.count;
    	other.head.next = null;
    	other.tail.prev = null;
    	other.count = 0;
    }

    /**
     * Returns a printable representation of this list.
     *
     * @return a printable representation of this list
     */
    public String toString()
    {
	// YAK!  This is O(n^2)! (can you see why?)
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
     * Nodes to use in doubly linked lists.
     */
    private static class Node<E>
    {
	private E data;
	private Node<E> prev;
	private Node<E> next;

	/**
	 * Creates a new node with the given data and null next and previous.
	 */
	public Node(E d)
	    {
		data = d;
		next = null;
		prev = null;
	    }
	
	/**
	 * Creates a new node with the given next and previous nodes.
	 *
	 * @param d a data item
	 * @param n a node, or null
	 * @param p a node, or null
	 */
	public Node(E d, Node<E> n, Node<E> p)
	    {
		data = d;
		prev = p;
		next = n;
	    }
	
	public String toString()
	{
	    return (data != null ? data.toString() : "null") + super.toString();
	}
    }

    /**
     * Test driver for LinkedList211<E>.
     */
    public static void main(String[] args)
    {
	LinkedList211<String> outbound = new LinkedList211<String>();
	outbound.add("EWR");
	outbound.add("DFW");
	outbound.add("LAX");
	outbound.add("SEA");

	LinkedList211<String> side = new LinkedList211<String>();
	side.add("ORD");
	side.add("MSP");

	outbound.move(2, side);
	System.out.println(outbound); // expect [EWR, DFW, ORD, MSP, LAX, SEA]
	System.out.println(side); // expect []
    }
}
