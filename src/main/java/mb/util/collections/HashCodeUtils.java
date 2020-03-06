package mb.util.collections;

/* package private */ class HashCodeUtils {
    private HashCodeUtils() {}

    // These implementations are made such that all overloads give the same hash code
    // when given the same elements, regardless of how they are implemented.

    /**
     * Calculates the hash code for an empty collection.
     *
     * @return the hash code
     */
    public static int listHashCodeOf() {
        // This implementation returns the same hash code as the AbstractList implementation.

        return 1;
    }

    /**
     * Calculates the hash code for a singleton collection.
     *
     * @param element the singleton element
     * @return the hash code
     */
    public static int listHashCodeOf(Object element) {
        // This implementation returns the same hash code as the AbstractList implementation.

        return 31 + (element != null ? element.hashCode() : 0);
    }

    /**
     * Calculates the hash code for an iterable collection.
     *
     * @param iterable the iterable collection
     * @return the hash code
     */
    public static int listHashCodeOfAll(Iterable<?> iterable) {
        // This implementation will throw a StackOverflowError when
        // the collection contains itself, directly or indirectly.

        // This implementation does not assume calls to size() report the same size as
        // the number of elements returned by the iterators, as the collection might
        // be concurrently modified between calls to size() and comparing the elements.

        // This implementation returns the same hash code as the AbstractList implementation.

        int hashCode = 1;
        for (Object e : iterable) {
            hashCode = 31 * hashCode + (e != null ? e.hashCode() : 0);
        }
        return hashCode;
    }
}
