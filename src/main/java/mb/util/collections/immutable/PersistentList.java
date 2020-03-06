package mb.util.collections.immutable;

import mb.util.collections.mutable.MutableList;

import java.util.Iterator;
import java.util.List;

/**
 * A persistent list.
 *
 * @param <E> the type of elements in the list
 */
public interface PersistentList<E> extends PersistentCollection<E>, ImmutableList<E> {

    /**
     * Creates an empty persistent list.
     *
     * @param <E> the type of elements in the list
     * @return the persistent list
     */
    static <E> PersistentCollection<E> of() {
        // This overload of of() always returns the same empty PersistentCollection instance.
        throw new UnsupportedOperationException();
    }

    /**
     * Creates a singleton persistent list.
     *
     * @param element the element in the list
     * @param <E> the type of elements in the list
     * @return the persistent list
     */
    static <E> PersistentCollection<E> of(E element) {
        // This overload of of() returns a singleton PersistentCollection instance.
        throw new UnsupportedOperationException();
    }

    /**
     * Creates an persistent list from the specified array.
     *
     * @param elements the elements in the list
     * @param <E> the type of elements in the list
     * @return the persistent list
     */
    @SafeVarargs static <E> PersistentCollection<E> of(E... elements) {
        throw new UnsupportedOperationException();
    }

    /**
     * Creates an persistent list wrapping the specified list.
     *
     * @param list the list to wrap
     * @param <E> the type of elements in the list
     * @return the persistent list
     */
    static <E> PersistentCollection<E> from(List<? extends E> list) {
        throw new UnsupportedOperationException();
    }

    /**
     * Creates an persistent list wrapping a copy of the specified elements.
     *
     * @param elements the elements to copy
     * @param <E> the type of elements in the list
     * @return the persistent list
     */
    static <E> PersistentCollection<E> copyFrom(Iterable<? extends E> elements) {
        throw new UnsupportedOperationException();
    }

    /**
     * Creates an persistent list wrapping a copy of the specified list.
     *
     * @param list the list to copy
     * @param <E> the type of elements in the list
     * @return the persistent list
     */
    static <E> PersistentCollection<E> copyFrom(List<? extends E> list) {
        throw new UnsupportedOperationException();
    }
    
    PersistentList<E> set(int index, E element);
    PersistentList<E> insertAt(int index, E element);
    PersistentList<E> insertAllAt(int index, Iterator<? extends E> elements);
    PersistentList<E> removeAt(int index);

    interface Builder<E> extends MutableList<E>, PersistentCollection.Builder<E> {
        @Override PersistentList<E> build();
    }

}
