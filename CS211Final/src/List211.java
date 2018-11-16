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
     * Adds the given item to the end of this list.
     *
     * @param toAdd an item to add, not null
     */
    public void add(E toAdd);

    /**
     * Returns the number of items in this list.
     *
     * @return the number of items in this list
     */
    public int size();

    /**
     * Returns the item at the given position in this list.
     *
     * @param i a 0-based index in this list
     * @return the item at that position
     */
    public E get(int i);

    /**
     * Replaces the item at the given position in this list with the
     * given item.
     *
     * @param i a 0-based index in this list
     * @param t an item, not null
     */
    public void set(int i, E t);

    /**
     * Removes the item at the given position in this list.  Items
     * after that position are moved back one space to fill the gap.
     * 
     * @param i a 0-based index in this list
     * @return the item that was removed
     */
    public E remove(int i);

    /**
     * Inserts the given item at the given position in this list.
     * Items at or after that position are moved back one position to
     * make room.
     *
     * @param i a 0-based index in this list, or the smallest invalid index
     * @param t an item, not null
     */
    public void add(int i, E t);

    /**
     * Modifies this list to contain each element that it currently
     * has twice in succession.  So if the list is [1, 2, 3, 3] then
     * after invoking this method the list will be [1, 1, 2, 2, 3, 3, 3, 3].
     */
    public void doubleUp();
}
