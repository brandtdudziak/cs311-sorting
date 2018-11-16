
public class QuickSelect {
	
	public static int quickSelect(int[] a, int lo, int hi, int k){
		if(hi <= lo) return lo;
		int partition = partition(a, lo, hi);
		//if(partition == k) return a[k];
		if(partition > k) {System.out.println("greater than k"); quickSelect(a, lo, partition-1, k);}
		if(partition < k) {System.out.println("LESS THAN K"); quickSelect(a, partition+1, hi, k);}
		return a[k];
	}
	
	private static int partition(int[] a, int lo, int hi){
    	int x = a[hi];
    	int i = lo - 1;
    	for(int j = lo; j < hi; j++){
    		if(a[j] <= x){
    			swap(a, j, i+1);
    			i++;
    		}
    	}
    	swap(a, hi, i+1);
    	return i+1;
    }
	
	private static void swap(int[] arr, int i, int min){
    	int temp = arr[i];
    	arr[i] = arr[min];
    	arr[min] = temp;
    }

	public static void main(String[] args) {
		int k = 12;
		int[] a = {10, 100, 1, 0, 4, 5, 3, 11, 111, 45, 345, 33, 75, 97, 90};
		int[] b = {10, 9, 8, 7, 6};
		
		System.out.println(quickSelect(a, 0, a.length-1, k));

	}

}
