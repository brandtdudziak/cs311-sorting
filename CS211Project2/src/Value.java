
public class Value {

	public boolean included;
	public String keyword;
	//Number of other words is listSize - 1
	public int listSize;
	public double[] cooccurrances;
	public double occurrances;
	public static int positionId = 0;
	public int position;
	
	public Value(String word, int size)
	{
		included = false;
		keyword = word;
		listSize = size;
		cooccurrances = new double[listSize];
		position = positionId;
		positionId += 1;
	}
	
	public void updateArray(int pos)
	{
		cooccurrances[pos] += 1;
	}
	
	public double[] getVector()
	{
		double[] vector = new double[listSize];
		for(int i = 0; i < listSize; i+=1)
		{
			if(occurrances == 0){vector[i] = 0;}
			else{vector[i] = cooccurrances[i]/occurrances;}
		}
		return vector;
	}
	
}
