import java.util.Arrays;

public class HybridArraySet211 <E extends Comparable<? super E>> implements Set211<E> {

	private Comparable[] sorted;
	private Comparable[] unsorted;
	private int itemsS;
	private int itemsUs;
	private long counter;
	private int merges;
	
	public HybridArraySet211()
	{
		sorted = new Comparable[0];
		unsorted = new Comparable[0];
		itemsS = 0;
		itemsUs = 0;
		counter = 0;
		merges = 0;
	}
	
	public void add(E toAdd)
	{
		if(!contains(toAdd))
		{
			itemsUs+=1;
			if(itemsUs > unsorted.length){unsorted = resize(unsorted);}
			unsorted[itemsUs-1] = toAdd;
			merge();
			counter+=3;
		}
	}
	
	private Comparable[] resize(Comparable[] a)
	{
		if(a.length == 0){return new Comparable[1];}
		Comparable[] large = new Comparable[a.length*2];
		for(int i = 0; i < a.length; i+=1)
		{
			large[i] = a[i];
			counter+=2;
		}
		counter+=3+(a.length*2);
		return large;
	}
	
	public boolean contains(E item)
	{		
		if(itemsS != 0){counter+=Math.log(itemsS);}
		if(Arrays.binarySearch(sorted, 0, itemsS, item) >= 0)
		{
			return true;
		}
		for(int i = 0; i < itemsUs; i+=1)
		{
			counter+=2;
			if(unsorted[i].equals(item))
			{
				return true;
			}
		}
		counter+=1;
		return false;
	}
	
	public void remove(E item)
	{
		int remove = Arrays.binarySearch(sorted, 0, itemsS, item);
		if(remove > -1)
		{
			itemsS-=1;
			for(int i = remove; i < itemsS; i+=1)
			{
				sorted[i] = sorted[i+1];
			}
			sorted[itemsS] = null;
		} else {
			for(int i = 0; i < itemsUs; i+=1)
			{
				if(unsorted[i].equals(item))
				{
					remove = i;
				}
			}
			if(remove > -1)
			{
				itemsUs-=1;
				for(int i = remove; i < itemsUs; i+=1)
				{
					unsorted[i] = unsorted[i+1];
				}
				unsorted[itemsUs] = null;
			}
		}
	}
	
	public int size()
	{
		return itemsS + itemsUs;
	}
	
	public long counter()
	{
		return counter;
	}
	
	public int merges()
	{
		return merges;
	}
	
	public String toString()
	{
		String re = "Sorted: [";
		for(int i = 0; i < itemsS-1; i+=1)
		{
			if(itemsS != 0){re+=sorted[i].toString()+", ";}
		}
		if(itemsS != 0){re+=sorted[itemsS-1].toString();}
		re+= "]";
		
		re+= "  Unsorted: [";
		for(int i = 0; i < itemsUs-1; i+=1)
		{
			if(itemsUs != 0){re+=unsorted[i].toString()+", ";}
		}
		if(itemsUs != 0){re+=unsorted[itemsUs-1].toString();}
		re+= "]";
		
		return re;
	}
	
	private void merge()
	{
		if(Math.sqrt(itemsS) < itemsUs)
		{
			merges+=1;
			int items = itemsS + itemsUs;
			counter+=1;
			Arrays.sort(unsorted, 0, itemsUs);
			counter+=itemsUs*Math.log(itemsUs);
			Comparable[] large = new Comparable[items];
			counter+=items;
			for(int i = items-1; i >= 0; i-=1)
			{
				if(itemsS != 0 && itemsUs != 0){
					if(unsorted[itemsUs-1].compareTo(sorted[itemsS-1]) > 0)
					{
						large[i] = unsorted[itemsUs-1];
						unsorted[itemsUs-1]=null;
						itemsUs-=1;
					} else {
						large[i] = sorted[itemsS-1];
						itemsS-=1;
					}
					counter-=1;
				} else if (itemsS == 0)
				{
					large[i] = unsorted[itemsUs-1];
					unsorted[itemsUs-1]=null;
					itemsUs-=1;
				} else {
					large[i] = sorted[itemsS-1];
					sorted[itemsS-1]=null;
					itemsS-=1;
				}
				counter+=2;
			}
			sorted = large;
			itemsS = items;
			counter+=3;
		}
		counter+=1;
	}
	
	public static void main(String[] args)
	{
		Integer i = new Integer(5);
		HybridArraySet211<Integer> h = new HybridArraySet211<Integer>();
		System.out.println(h);
		h.add(new Integer(6));
		h.add(new Integer(6));
		System.out.println(h);
		h.add(new Integer(5));
		System.out.println(h);
		h.add(new Integer(7));
		System.out.println(h);
		h.add(new Integer(8));
		System.out.println(h);
		h.add(new Integer(9));
		System.out.println(h);
		h.add(new Integer(2));
		h.remove(new Integer(100));
		h.add(new Integer(1));
		System.out.println(h);
		h.add(new Integer(15));
		h.add(new Integer(7));
		System.out.println(h);
		
		h.remove(i);
		h.remove(new Integer(9));
		h.remove(new Integer(7));
		h.remove(new Integer(1));
		h.remove(new Integer(6));
		h.remove(new Integer(8));
		h.add(new Integer(6));
		h.remove(new Integer(6));
		h.remove(new Integer(2));
		h.remove(new Integer(15));
		System.out.println(h);
		System.out.println(h.size());
	}
}
