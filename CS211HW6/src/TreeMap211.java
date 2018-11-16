/**
 * A binary search tree implementation of the map interface.
 *
 * @author Jim Glenn
 * @version 0.1 2017-02-27 based on DSA2's BinarySearchTree
 */

public class TreeMap211<K extends Comparable<? super K>, V> implements Map211<K, V>
{
    private Node<K, V> root;
    private int size;

    /**
     * Creates an empty map.
     */
    public TreeMap211()
	{
	    root = null;
	    size = 0;
	}

    /**
     * Returns the number of (key, value) pairs in this map.
     *
     * @return the size of this map
     */
    public int size()
    {
	return size;
    }

    /**
     * Counts the number of tree in this tree.
     *
     * @return the number if items in this tree
     */
    public int countNodes()
    {
	return count(root);
    }
    
    private int count(Node<K, V> curr)
    {
    	if(curr.left == null && curr.right == null)
    	{
    		return 1;
    	}
    	else if(curr.right == null)
    	{
    		return 1 + count(curr.left);
    	}
    	else if(curr.left == null)
    	{
    		return 1 + count(curr.right);
    	}
    	else{return 1 + count(curr.left) + count(curr.right);}
    }
    
    public static void main(String[] args)
    {
    	TreeMap211<String, Integer> m = new TreeMap211<String, Integer>();
    	m.put("F", 1);
    	m.put("C", 1);
    	m.put("I", 1);
    	m.put("B", 1);
    	m.put("E", 1);
    	m.put("H", 1);
    	m.put("K", 1);
    	m.put("A", 1);
    	m.put("D", 1);
    	m.put("G", 1);
    	m.put("J", 1);
    	m.put("L", 1);
    	System.out.println(m.countNodes()+"\n"+m.toString());
    }

    /**
     * Returns the node containing the given key, or, if there is no
     * such node, the parent of where it would go if it were to be
     * added, or null if this tree is empty.
     *
     * @param k a key; not null
     * @return the node containing k, or what would be its parent if it were added,
     * or null if this tree is empty
     */
    private SearchResult<K, V> find(K key)
    {
	// start at root
	Node<K, V> curr = root;

	// keep track of parent of current and last direction travelled
	Node<K, V> parCurr = null;

	int dir = 0;
	// traverse tree to insertion location or node containing key
	while (curr != null && (dir = key.compareTo(curr.key)) != 0)
	     {
	 	parCurr = curr;
	 	if (dir < 0)
	 	    {
	 		curr = curr.left;
	 	    }
	 	else
	 	    {
	 		curr = curr.right;
	 	    }
	     }

	return new SearchResult<K, V>(curr, parCurr);
    }

    /**
     * Associates the given value with the given key in this map.  If the key
     * is already present then the old value is overwritten.
     *
     * @param key a key; not null
     * @param value a value
     */
    public void put(K key, V value)
    {
	SearchResult<K, V> result = find(key);
	if (result.curr != null)
	    {
		// found it; replace value
		result.curr.value = value;
	    }
	else
	    {
		// didn't find but got parent; add
		Node<K, V> newNode = new Node<K, V>(key, value);
		newNode.parent = result.parent;
		if (result.parent == null)
		    {
			root = newNode;
		    }
		else if (result.parent.key.compareTo(key) > 0)
		    {
			result.parent.left = newNode;
		    }
		else
		    {
			result.parent.right = newNode;
		    }

		size++;
	    }
    }

    /**
     * Determines if this map contains the given key.
     *
     * @param key the key to search for; not null
     * @return true if and only if this map contains that key
     */
    public boolean containsKey(K key)
    {
	return find(key).curr != null;
    }

    /**
     * Retrieves the value associated with the given key.  Returns null if the
     * key is not present
     *
     * @param key the key to search for; not null
     * @return the value associated with that key, or null<
     */
    public V get(K key)
    {
	SearchResult<K, V> result = find(key);

	if (result.curr != null)
	    {
		return result.curr.value;
	    }
	else
	    {
		return null;
	    }
    }

    /**
     * Removes the given key from this map.  There is no effect if the key
     * is not present.
     */
    public void remove(K key)
    {
	SearchResult<K, V> result = find(key);

	Node<K, V> curr = result.curr;
	Node<K, V> parCurr = result.parent;
	
	if (curr == null)
	    {
		return;
	    }
	else
	    {
		size--; 

		if (curr.left == null && curr.right == null)
		    {
			Node<K, V> parent = curr.parent;
			if (curr.isLeftChild())
			    {
				parent.left = null;
			    }
			else if (curr.isRightChild())
			    {
				parent.right = null;
			    }
			else
			    {
				// deleting the root
				root = null;
			    }
		    }
		else if (curr.left == null)
		    {
			// node top delete only has right child
			Node<K, V> parent = curr.parent;
			
			if (curr.isLeftChild())
			    {
				parent.left = curr.right;
				curr.right.parent = parent;
			    }
			else if (curr.isRightChild())
			    {
				parent.right = curr.right;
				curr.right.parent = parent;
			    }
			else
			    {
				root = curr.right;
				root.parent = null;
			    }
		    }
		else if (curr.right == null)
		    {
			// node to delete only has left child
			Node<K, V> parent = curr.parent;
			
			if (curr.isLeftChild())
			    {
				parent.left = curr.left;
				curr.left.parent = parent;
			    }
			else if (curr.isRightChild())
			    {
				parent.right = curr.left;
				curr.left.parent = parent;
			    }
			else
			    {
				root = curr.left;
				root.parent = null;
			    }
		    }
		else
		    {
			// node to delete has two children
			
			// find inorder successor (leftmost in right subtree)
			Node<K, V> replacement = curr.right;
			if(replacement.left == null) // node to delete has a right subtree with a root with no left children
			{
				if (curr.isLeftChild())
					{
					curr.parent.left = replacement;
			    	}
				else if (curr.isRightChild())
					{
					curr.parent.right = replacement;
			    	}
				else
			    	{
					root = replacement;
			    	}
				replacement.parent = curr.parent;
				replacement.left = curr.left;
				curr.left.parent = replacement;
			}
			else{
			while (replacement.left != null)
			    {
				replacement = replacement.left;
			    }
			
			Node<K, V> replacementChild = replacement.right;
			Node<K, V> replacementParent = replacement.parent;
			
			// stitch up
			if (curr.isLeftChild())
			    {
				curr.parent.left = replacement;
			    }
			else if (curr.isRightChild())
			    {
				curr.parent.right = replacement;
			    }
			else
			    {
				root = replacement;
			    }
			

			replacement.parent.left = replacementChild;
			if (replacementChild != null)
			    {
				replacementChild.parent = replacement.parent;
			    }
			
			replacement.right = curr.right;
			curr.right.parent = replacement;

			
			
			replacement.left = curr.left;
			curr.left.parent = replacement;
			
			replacement.parent = curr.parent;
			}
		    }
	    }
    }

    /**
     * Returns a printable representation of this tree.
     *
     * @return a printable representation of this tree
     */
    public String toString()
    {
	StringBuilder s = new StringBuilder();
	buildOutput(root, s, 0);
	return s.toString();
    }

    /**
     * Appends a printable representation of the subtree rooted at the
     * given node to the given string builder.
     *
     * @param curr a node in this tree
     * @param s a string builder
     * @param depth the depth of curr
     */
    private void buildOutput(Node<K, V> curr, StringBuilder s, int depth)
    {
	if (curr != null)
	    {
		buildOutput(curr.left, s, depth + 1);
		s.append(depth + " " + curr.key + "=" + curr.value + "\n");
		buildOutput(curr.right, s, depth + 1);
	    }
    }

    /**
     * A node in this tree.
     */
    public static class Node<K extends Comparable<? super K>, V>
    {
	private Node<K, V> parent;
	private Node<K, V> left;
	private Node<K, V> right;

	private K key;
	private V value;

	private Node(K k, V val)
	    {
		key = k;
		value = val;
	    }

	private boolean isLeftChild()
	{
	    return (parent != null && parent.left == this);
	}

	private boolean isRightChild()
	{
	    return (parent != null && parent.right == this);
	}
    }

    public static class SearchResult<K extends Comparable<? super K>, V>
    {
	private Node<K, V> curr;
	private Node<K, V> parent;

	public SearchResult(Node<K, V> c, Node<K, V> p)
	    {
		curr = c;
		parent = p;
	    }
    }
}