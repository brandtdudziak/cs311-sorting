/**
 * A to-do list tracking to-do and completed tasks.
 *
 * @author Jim Glenn
 * @version 0.1 2017-01-26
 */

public interface ToDoList
{
	
	public int totalTimeRemaining();
	
    /**
     * Adds the given task to the end of the to-do list.
     *
     * @param t a task
     */
    public void add(Task t);

    /**
     * Determines if there are uncompleted tasks on this list.
     *
     * @return true if and only if there are uncompleted tasks
     */
    public boolean isIncomplete();

    /**
     * Returns the next task on the to-do list.
     *
     * @return the next task on the to-do list
     */
    public Task getNext();

    /**
     * Marks the next task on the to-do list as complete and moves it to the
     * end of the completed tasks list.
     */
    public void completeNext();

    /**
     * Exchanges the next two items on the to-do list.  No effect if there
     * are fewer than two items.
     */
    public void delayNext();

    /**
     * Moves the next task on the to-do list to the end of the to-do list.
     * No effect if the to-do list is empty.
     */
    public void postponeNext();
}