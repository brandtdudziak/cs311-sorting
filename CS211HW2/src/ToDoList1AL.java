import java.util.ArrayList;

/**
 * A to-do list tracking to-do and completed tasks.
 *
 * @author Jim Glenn
 * @version 0.1 2017-01-26
 */

public class ToDoList1AL implements ToDoList
{
	
		public int totalTimeRemaining(){
			int time = 0;
			for(int i = numComplete; i < tasks.size(); i+=1){
				time+=tasks.get(i).getEstimatedTime();
			}
			return time;
		}
	
	
    /**
     * The list of tasks containing both completed and uncompleted
     * tasks.  The list contains the completed tasks in order of completion
     * followed by the uncompleted tasks in planned order.
     */
    private ArrayList<Task> tasks;

    /**
     * The nunmber of completed tasks (which equals the index of the
     * first uncompleted taskm if there is one).
     *
     */
     private int numComplete;

    /**
     * Creates an empty to-do list.
     */
    public ToDoList1AL()
    {
	tasks = new ArrayList<Task>();
	numComplete = 0;
    }

    /**
     * Adds the given task to the end of the to-do list.
     *
     * @param t a task
     */
    public void add(Task t)
    {
	tasks.add(t);
    }

    /**
     * Determines if there are uncompleted tasks on this list.
     *
     * @return true if and only if there are uncompleted tasks
     */
    public boolean isIncomplete()
    {
	return numComplete < tasks.size();
    }

    /**
     * Returns the next task on the to-do list.
     *
     * @return the next task on the to-do list
     */
    public Task getNext()
    {
	return new Task(tasks.get(numComplete));
    }

    /**
     * Marks the next task on the to-do list as complete and moves it to the
     * end of the completed tasks list.
     */
    public void completeNext()
    {
	tasks.get(numComplete).complete();
	numComplete++;
    }

    /**
     * Exchanges the next two items on the to-do list.  No effect if there
     * are fewer than two items.
     */
    public void delayNext()
    {
	if (tasks.size() - numComplete > 1)
	    {
		Task t = tasks.get(numComplete);
		tasks.set(numComplete, tasks.get(numComplete + 1));
		tasks.set(numComplete + 1, t);
	    }
    }

    /**
     * Moves the next task on the to-do list to the end of the to-do list.
     * No effect if the to-do list is empty.
     */
    public void postponeNext()
    {
	if (tasks.size() - numComplete > 1)
	    {
		Task t = tasks.get(numComplete);
		tasks.remove(numComplete);
		tasks.add(t);
	    }
    }

    /**
     * Returns a printable representation of this list.
     *
     * @return a printable representation of this list
     */
    public String toString()
    {
	return tasks.toString();
    }
}