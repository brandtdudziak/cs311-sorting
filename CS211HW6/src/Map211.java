/**
 * A map.
 *
 * @param K the type of the keys
 * @param V the type of the values
 * @author Jim Glenn
 * @version 0.1 2017-02-27
 */

public interface Map211<K, V>
{
    /**
     * Associates the given value with the given key in this map.  If the key
     * is already present then the old value is overwritten.
     *
     * @param key a key; not null
     * @param value a value
     */
    public void put(K key, V value);

    /**
     * Determines if this map contains the given key.
     *
     * @param key the key to search for; not null
     * @return true if and only if this map contains that key
     */
    public boolean containsKey(K key);

    /**
     * Retrieves the value associated with the given key.  Returns null if the
     * key is not present.
     *
     * @param key the key to search for; not null
     * @return the value associated with that key, or null
     */
    public V get(K key);

    /**
     * Removes the given key from this map.  There is no effect if the key
     * is not present.
     */
    public void remove(K key);
    
    /**
     * Returns the number of (key, value) pairs in this map.
     *
     * @return the size of this map
     */
    public int size();
}