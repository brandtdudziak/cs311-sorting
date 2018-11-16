import java.util.ArrayList;

/**
 * A to-do list tracking to-do and completed tasks.
 *
 * @author Jim Glenn
 * @version 0.1 2017-01-26
 */

public class ToDoList2AL implements ToDoList
{
	
	public int totalTimeRemaining(){
		int time = 0;
		for(int i = 0; i < toDo.size(); i+=1){
			time+=toDo.get(i).getEstimatedTime();
		}
		return time;
	}
	
    /**
     * The list of completed tasks, in the order completed.
     */
    private ArrayList<Task> completed;

    /**
     * The list of tasks to do, in the order planned.
     */
    private ArrayList<Task> toDo;

    /**
     * Creates an empty to-do list.
     */
    public ToDoList2AL()
    {
	completed = new ArrayList<Task>();
	toDo = new ArrayList<Task>();
    }

    /**
     * Adds the given task to the end of the to-do list.
     *
     * @param t a task
     */
    public void add(Task t)
    {
	toDo.add(t);
    }

    /**
     * Determines if there are uncompleted tasks on this list.
     *
     * @return true if and only if there are uncompleted tasks
     */
    public boolean isIncomplete()
    {
	return toDo.size() > 0;
    }

    /**
     * Returns the next task on the to-do list.
     *
     * @return the next task on the to-do list
     */
    public Task getNext()
    {
	return new Task(toDo.get(0));
    }

    /**
     * Marks the next task on the to-do list as complete and moves it to the
     * end of the completed tasks list.
     */
    public void completeNext()
    {
	Task t = toDo.get(0);
	t.complete();
	toDo.remove(0);
	completed.add(t);
    }

    /**
     * Exchanges the next two items on the to-do list.  No effect if there
     * are fewer than two items.
     */
    public void delayNext()
    {
	if (toDo.size() > 1)
	    {
		Task t = toDo.get(0);
		toDo.set(0, toDo.get(1));
		toDo.set(1, t);
	    }
    }

    /**
     * Moves the next task on the to-do list to the end of the to-do list.
     * No effect if the to-do list is empty.
     */
    public void postponeNext()
    {
	if (toDo.size() > 0)
	    {
		Task t = toDo.get(0);
		toDo.remove(0);
		toDo.add(t);
	    }
    }

    /**
     * Returns a printable representation of this list.
     *
     * @return a printable representation of this list
     */
    public String toString()
    {
	return "Completed: " + completed + "\n" + "To Do: " + toDo;
    }
}