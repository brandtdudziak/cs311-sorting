/**
 * A list.
 *
 * @author Jim Glenn
 * @version 0.3 genericized
 * @version 0.2 separated out interface; changed elements to Tasks
 * @version 0.1 ArrayList112 (of Strings) 2015-10-21
 */

public interface List211<E>
{
    /**
     * Adds the given task to the end of this list.
     *
     * @param toAdd a task to add
     */
    public void add(E toAdd);

    /**
     * Returns the number of elements in this list.
     *
     * @return the number of elements in this list
     */
    public int size();

    /**
     * Returns the element at the given position in this list.
     *
     * @param i a 0-based index in this list
     * @return the item at that position
     */
    public E get(int i);

    /**
     * Replaces the element at the given position in this list with the
     * given task.
     *
     * @param i a 0-based index in this list
     * @param t a task
     */
    public void set(int i, E t);

    /**
     * Removes the element at the given position in this list.  Elements
     * after that position are moved back one space to fill the gap.
     * 
     * @param i a 0-based index in this list
     * @return the element that was removed
     */
    public E remove(int i);

    /**
     * Inserts the given element at the given position in this list.
     * Elements at or after that position are moved back one position to
     * make room.
     *
     * @param i a 0-based index in this list, or the smallest invalid index
     * @param t a task
     */
    public void add(int i, E t);
    
    /**
     * Removes all items in a list between given indices.
     *
     * @param from a 0-based index in this list that indicates the start of the range (inclusive)
     * @param to a 0-based index in this list that indicates the end of the range (exclusive)
     */
    public void removeRange(int from, int to);
    
    /**
     * Inserts all the items of list l at index i.
     *
     * @param i a 0-based index in which to insert the elements of the other list
     * @param l a generic List211 which contains the elements to insert
     */
    public void insert(int i, List211<E> l);
}