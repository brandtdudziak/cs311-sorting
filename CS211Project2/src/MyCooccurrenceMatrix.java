import java.util.List;
import java.util.HashMap;
import java.util.ArrayList;

public class MyCooccurrenceMatrix implements CooccurrenceMatrix {

	HashMap<String, Value> set = new HashMap<String, Value>();
	
	public MyCooccurrenceMatrix(List<String> keywords)
	{
		for(String key : keywords)
		{
			set.put(key, new Value(key, keywords.size()));
		}
	}
	
	public void update(List<String> context)
	{
		List<String> included = new ArrayList<String>();
		for(String key : context)
		{
			if(set.containsKey(key) && set.get(key).included == false)
			{
				set.get(key).included = true;
				set.get(key).occurrances += 1;
				included.add(key);
			}
		}
		for(String key : included)
		{
			for(String other : included)
			{
				set.get(key).updateArray(set.get(other).position);
			}
			set.get(key).included = false;
		}
	}
	
	public double[] getVector(String keyword)
	{
		return set.get(keyword).getVector();
	}
	
}
