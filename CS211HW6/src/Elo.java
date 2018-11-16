import java.util.Scanner;
import java.util.StringTokenizer;
import java.util.*;

// import some more classes

public class Elo
{
    public static void main(String[] args)
    {
	Scanner in = new Scanner(System.in);

	// create an empty map from team names to numeric ratings
	Map<String, Double> m = new TreeMap<String, Double>();
	Double winnerRating = 0.0;
	Double loserRating = 0.0;

	while (in.hasNextLine())
	    {
		String line = in.nextLine();
		StringTokenizer tok = new StringTokenizer(line, ">");
		
		String winner = tok.nextToken();
		String loser = tok.nextToken();

		// get winner's and loser's ratings, or 1500 if no rating has
		// been recorded yet
		if(m.containsKey(winner))
		{
			winnerRating = m.get(winner);
		} else {
			winnerRating = 1500.0;
			m.put(winner, winnerRating);
		}
		if(m.containsKey(loser))
		{
			loserRating = m.get(loser);
		} else {
			loserRating = 1500.0;
			m.put(loser, loserRating);
		}

		double eWin = 1 / (1 + Math.pow(10, (loserRating - winnerRating) / 400));
		winnerRating += 32*(1-eWin);
		loserRating += -32*(1-eWin);

		// put ratings back
		m.put(winner, winnerRating);
		m.put(loser, loserRating);
	    }

	// print ratings, ordered by team name, one line at a time
	for(Map.Entry<String, Double> map : m.entrySet())
	{
		System.out.println(map.getKey()+"="+map.getValue());
	}
	
	in.close();
    }
}