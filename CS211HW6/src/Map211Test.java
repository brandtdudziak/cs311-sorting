/**
 * Tests for Map211 implementations.
 */

public class Map211Test
{
    public static void main(String[] args)
    {
	Map211<String, Integer> m = new TreeMap211<String, Integer>();

	test(m);
    }

    /**
     * Tests a Map211 implementation,
     *
     * @param m an empty map
     */
    public static void test(Map211<String, Integer> m)
    {
	m.put("BWI", 4);
	System.out.println(m.size()); // expect 1
	System.out.println(m.containsKey("BWI")); // expect true
	System.out.println(m.containsKey("DCA")); // expect false
	System.out.println(m.get("BWI")); // expect 4

	m.put("ADD", 10);
	System.out.println(m.size()); // expect 2
	System.out.println(m.containsKey("BWI")); // expect true
	System.out.println(m.containsKey("ADD")); // expect true
	System.out.println(m.containsKey("DCA")); // expect false
	System.out.println(m.get("BWI")); // expect 4
	System.out.println(m.get("ADD")); // expect 10
	System.out.println(m);

	m.put("DCA", 6);
	m.put("YYC", 3);

	m.remove("YYC");
	m.remove("DCA");
	m.remove("BWI");
	System.out.println(m); // expect ADD=10

	m.put("ABE", 2);
	m.put("SEA", 4);
	m.put("PDX", 9);
	m.remove("ADD");
	System.out.println(m); // expect ABE=2, PDX=9, SEA=4

	m.put("YYZ", 1);
	m.put("PWM", 3);
	m.put("PHF", 5);
	m.put("PSM", 2);
	m.remove("PDX");
	System.out.println(m); // expect ABE=2,PHF=5,PSM=2,PWM=3,SEA=4,YYZ=1

	m.put("STS", 7);
	m.remove("SEA");
	System.out.println(m); // expect ABE=2,PHF=5,PSM=2,PWM=3,STS=7,YYZ=1

	// ...
	m.remove("STS"); // error removing a node with two children whose right subtree has a root with no left children
	System.out.println(m);
    }
}