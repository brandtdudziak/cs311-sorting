
public class MedianOfMedians {

	public static int median(int[] a, int[] b, int m1, int m2){
		if(m1 == 0) {System.out.println("a"); return a[m1];}
		if(m2 == 0) {System.out.println("b"); return a[m2];}
		if(a[m1] > b[m2]){ System.out.println("A: Large: " + a[m1] + " Small: " + b[m2] + " " + b[(3*m2)/2]); return median(a, b, m1/2, (3*m2)/2);} //return b[m2];}
		else { System.out.println("B: Small: " + a[m1] + " Large: " + b[m2]); return median(a, b, (m1*3)/2, m2/2);} //return a[m1]; }
	}
	
	public static void main(String[] args) {
		int[] a = {2, 5, 6, 8};
		int[] b = {1, 3, 10, 12};
		int m = 1;
		
		System.out.println(median(a, b, m, m));
		
		System.out.println(1 + 2 + "hello");
		System.out.println("hello" + 1 + 2);
		System.out.println("hello" + (1 + 2));

	}

}
