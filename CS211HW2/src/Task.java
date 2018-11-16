/**
 * A task with completedness, estimated time to perform, and short description.
 *
 * @author Jim Glenn
 * @version 0.1 2017-01-26
 */

public class Task
{
    /**
     * The estimated time to perform this task, in minutes.
     */
    private int estimatedTime;

    /**
     * A description of this task.
     */
    private String description;

    /**
     * A flag indicating whether this task has been completed.
     */
    private boolean complete;

    /**
     * Creates an uncompleted task with the given estimated time and
     * description.
     *
     * @param t a positive integer
     * @param d a string
     */
    public Task(int t, String d)
    {
	if (t <= 0)
	    {
		throw new IllegalArgumentException("estimated time must be positive: " + t);
	    }
	
	estimatedTime = t;
	description = d;
	complete = false;
    }

    /**
     * Creates a copy of the given task.
     *
     * @param toCopy a task
     */
    public Task(Task toCopy)
    {
	// (should use clone instead, but writing/using clone is a little 112-ish
	estimatedTime = toCopy.estimatedTime;
	description = toCopy.description;
	complete = toCopy.complete;
    }

    /**
     * Returns the estimated time to complete this task, in minutes
     *
     * @return  the estimated time to complete this task
     */
    public int getEstimatedTime()
    {
	return estimatedTime;
    }

    /**
     * Returns a description of this task.
     *
     * @return a description of this task
     */
    public String getDescription()
    {
	return description;
    }

    /**
     * Returns whether this task has been completed.
     *
     * @return true if and only if this task has been completed
     */
    public boolean isComplete()
    {
	return complete;
    }

    /**
     * Marks this task as completed.
     */
    public void complete()
    {
	complete = true;
    }

    /**
     * Returns a printable representation of this task.
     *
     * @retturn a printable representation of this task
     */
    public String toString()
    {
	return (complete ? "*" : " ") + " " + estimatedTime + " " + description;
    }
}