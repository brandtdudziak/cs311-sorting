//Edited in Eclipse then copied over to romulus for testing
import java.util.Random;

public class Sort {

    int N;
    int[] temp;
    Random r;

    //sorts an array of ints using heapsort - Heap is a max heap
    public void heapSort(int[] to_sort) {
    	Heap h = new Heap(to_sort);
    	for(int i = to_sort.length-1; i >= 0; i--){
    		to_sort[i] = h.deleteMax();
    	}
    }

    //sorts an array of ints using selection sort
    public void selectionSort(int[] to_sort) {
    	int min = 0;
    	for(int i = 0; i < to_sort.length; i++){
    		min = i;
    		for(int x = i+1; x < to_sort.length; x++){
    			if(to_sort[x] < to_sort[min]){
    				min = x;
    			}
    		}
    		swap(to_sort, i, min);
    	}
    }
    
    private void swap(int[] arr, int i, int min){
    	int temp = arr[i];
    	arr[i] = arr[min];
    	arr[min] = temp;
    }

    //sorts an array of ints using quicksort
    public void quickSort(int[] to_sort) {
    	quickSorter(to_sort, 0, to_sort.length-1);
    }
    
    private void quickSorter(int[] to_sort, int lo, int hi){
    	if(hi <= lo) return;
    	int partition = randomized_partition(to_sort, lo, hi);
    	quickSorter(to_sort, lo, partition-1);
    	quickSorter(to_sort, partition+1, hi);
    }
    
    private int randomized_partition(int []a, int lo, int hi){
    	int i = r.nextInt(hi-lo+1) + lo;
    	swap(a, i, hi);
    	return partition(a, lo, hi);
    }
    
    private int partition(int[] a, int lo, int hi){
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

    //sorts an array of ints using mergesort
    public void mergeSort(int[] to_sort) {
    	mergeRecursive(to_sort, 0, to_sort.length-1);
    }
    
    private void mergeRecursive(int[] to_sort, int lo, int hi){
    	if(hi <= lo) return;
    	int mid = (hi + lo)/2;
    	mergeRecursive(to_sort, lo, mid);
    	mergeRecursive(to_sort, mid+1, hi);
    	merge(to_sort, lo, mid, hi);
    }
    
    private void merge(int[] to_sort, int lo, int mid, int hi){
    	int counterOne = lo;
    	int counterTwo = mid+1;
    	for(int i = lo; i <= hi; i++){
    		if(counterOne == mid+1 || (counterTwo <= hi && to_sort[counterOne] > to_sort[counterTwo])){
    			temp[i] = to_sort[counterTwo];
    			counterTwo++;
    		} else {
    			temp[i] = to_sort[counterOne];
    			counterOne++;
    		}
    	}
    	for(int i = lo; i <= hi; i++){to_sort[i] = temp[i];}
    }
    
    private void printList(int[] to_sort){
    	for(int i = 0; i < to_sort.length; i++){System.out.print(to_sort[i] + ", ");}
    	System.out.println();
    }

    //sorts an array of ints using insertion sort
    public void insertionSort(int[] to_sort) {
	
	for(int i = 1; i < N; i++) {

	    int val = to_sort[i];
	    int j = i - 1;

	    while(j >= 0 && val < to_sort[j]) {
		to_sort[j + 1] = to_sort[j];
		j--;
	    }

	    to_sort[j+1] = val;

	}

    }

    //Checks to see if the array is sorted
    public boolean isSorted(int[] arr) {
    	for(int i = 1; i < arr.length; i++) {
    		//System.out.println(arr[i-1]);
    		if(arr[i] < arr[i-1]) { return false; }
    	}
    	//System.out.println(arr[arr.length-1]);
    	return true;
    }
    

    public void test() {

	int[] my_array = new int[N];

	long total_time = 0;
	int num_iters = 10;

	//test insertionsort
	//sort multiple times to reduce noise
	for(int i = 0; i < num_iters; i++) { 
	    Generate.randomData(my_array); //fill my_array with unsorted data

	    long start_time = System.nanoTime();
	    quickSort(my_array);
	    long end_time = System.nanoTime();
	    total_time += end_time - start_time;
	}
	System.out.println((total_time/1000000000.0)/(num_iters * 1.0) + " ");
	System.out.println("Is Sorted: " + isSorted(my_array));
    }

    //input: number of elements to sort
    public static void main(String[] args) {

	int num_items = Integer.parseInt(args[0]);

	Sort s = new Sort(num_items);
	s.test();

    }

    public Sort(int num_elts) {
	N = num_elts;
	temp = new int[N];
	r = new Random();
    }

}