import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;

public class Autocorrect
{
    public static void main(String[] args)
    {
	List<List<String>> corrections = new ArrayList<List<String>>();
	// replace "teh" with "the"
	corrections.add(Arrays.asList(new String[] {"the", "teh"}));
	// replace "brid" and "berd" with "bird"
	corrections.add(Arrays.asList(new String[] {"bird", "brid", "berd"}));
	// replace "fleu", "glew", and "flww" with "flew"
	corrections.add(Arrays.asList(new String[] {"flew", "fleu", "glew", "flww"}));

	// set up the test to autocorrect
	List<String> text = Arrays.asList(new String[] {"teh", "berd", "glew", "high"});

	// expect [the, bird, flew, high]
	System.out.println(autocorrect(corrections, text));

    }
    /**
     * Autocorrects the given list of words using the given list of
     * substitutions.  The substitutions are given as a list of lists,
     * dict, so that the list at index i is a list of words each of
     * which should be replaced by the word at the beginning of the
     * list at index i.
     *
     * @param a non-empty list of non-empty lists such that no two lists
     * contain the same value; not null
     * @param text a list of words; not null
     * @return the autocorrected list
     */
    public static List<String> autocorrect(List<List<String>> dict, List<String> text)
    {
    	ArrayList<String> returnedList = new ArrayList<String>();
    	//Turn into map where keys are misspellings (and correct one) and values are correct spelling
    	Map<String, String> m = new TreeMap<String, String>();
    	
    	for(int i = 0; i < dict.size(); i++)
    	{
    		for(int j = 0; j < dict.get(i).size(); j++)
    		{
    			m.put(dict.get(i).get(j), dict.get(i).get(0));
    		}
    	}
    	
    	for(int i = 0; i < text.size(); i++)
    	{
    		if(m.containsKey(text.get(i)))
    		{
    			returnedList.add(m.get(text.get(i)));
    		} else {
    			returnedList.add(text.get(i));
    		}
    	}
    	
	return returnedList;
    }
}
