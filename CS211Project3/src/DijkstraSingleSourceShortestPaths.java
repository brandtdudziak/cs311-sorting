import java.util.List;
import java.util.LinkedList;
import java.util.Map;
import java.util.HashMap;

public class DijkstraSingleSourceShortestPaths<T> implements SingleSourceShortestPaths<T> {

	private Map<T, T> pred;
	private Map<T, Double> dist;
	
	public DijkstraSingleSourceShortestPaths(Map<T, Map<T, Double>> edges, T source)
	{
		//Create empty priority queue
		PriorityQueue<T> pq = new BinaryHeapPriorityQueue<>();
		//Create empty map of cities to predecessors
		pred = new HashMap<>();
		//Create empty map of cities to distances
		dist = new HashMap<>();
		//Add source to queue with priority 0
		pq.add(source, 0);
		//while q not empty
		while(pq.size() > 0)
		{
			//Get min priority
			double q = pq.peekMin();
			T min = pq.extractMin();
			//Record distance
			dist.put(min, q);
			//for each next city
			for(T next : edges.get(min).keySet())
			{
				//Add up weights
				double p = q + edges.get(min).get(next);
				//if finished, do nothing
				if(dist.containsKey(next)){}
				else if(!pq.contains(next)) //else if not in queue, add
				{
					pq.add(next, p);
					pred.put(next, min);
				}
				else if(p < pq.getPriority(next)) //else if new distance < current distance
				{
					//Update priority
					pq.decreasePriority(next, p);
					//Record new predecessor
					pred.put(next, min);
				}
			}
		}
	}
	
	public List<T> getPath(T dest)
	{
		List<T> list = new LinkedList<T>();
		T prev = dest;
		while(prev != null)
		{
			list.add(0, prev);
			prev = pred.get(prev);
		}
		return list;
	}

	public double getDistance(T dest)
	{
		return dist.get(dest);
	}

}
