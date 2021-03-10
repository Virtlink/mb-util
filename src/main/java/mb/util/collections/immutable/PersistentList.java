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
    static <E> PersistentList<E> of() {
        // We can return a special empty implementation.
        throw new UnsupportedOperationException();
    }

    /**
     * Creates a singleton persistent list.
     *
     * @param element the element in the list
     * @param <E> the type of elements in the list
     * @return the persistent list
     */
    static <E> PersistentList<E> of(E element) {
        // We can return a special singleton implementation.
        throw new UnsupportedOperationException();
    }

    /**
     * Creates an persistent list from the specified array.
     *
     * Changes to the input array are not reflected in this list.
     *
     * @param elements the elements in the list
     * @param <E> the type of elements in the list
     * @return the persistent list
     */
    @SafeVarargs static <E> PersistentList<E> of(E... elements) {
        if (elements.length == 0) {
            // When the array is empty, we can just return the empty list,
            // because the input array cannot be modified and the fact that it
            // isn't part of the returned ListView is unobservable.
            return of();
        } else {
            // Otherwise, we copy the elements into a persistent list.
            // TODO:
            throw new UnsupportedOperationException();
        }
    }

    /**
     * Creates a persistent list by copying the elements from the specified iterable.
     *
     * Changes to the input iterable are not reflected in this list.
     *
     * @param elements the elements to include
     * @param <E> the type of elements in the list
     * @return the persistent list
     */
    static <E> PersistentList<E> from(Iterable<? extends E> elements) {
        if (elements instanceof PersistentList<?>) {
            // When the iterable is a persistent list (and implements ListView) we can just return it.
            //noinspection unchecked
            return (PersistentList<E>)elements;
        } else if (elements instanceof List<?>) {
            // When the iterable is a list, we can call the other overload.
            //noinspection unchecked
            return from((List<E>)elements);
        } else {
            // Otherwise, we copy the elements into a persistent list.
            // TODO:
            throw new UnsupportedOperationException();
        }
    }

    /**
     * Creates a persistent list from the specified list.
     *
     * Changes to the input list are not reflected in this list.
     *
     * @param list the list whose elements to include
     * @param <E> the type of elements in the list
     * @return the persistent list
     */
    static <E> PersistentList<E> from(List<? extends E> list) {
        if (list instanceof PersistentList<?>) {
            // When the list is a persistent list (and implements ListView) we can just return it.
            //noinspection unchecked
            return (PersistentList<E>)list;
        } else {
            // Otherwise, we copy the elements into a persistent list.
            // TODO:
            throw new UnsupportedOperationException();
        }
    }

    PersistentList<E> set(int index, E element);
    PersistentList<E> insertAt(int index, E element);
    PersistentList<E> insertAllAt(int index, Iterator<? extends E> elements);
    PersistentList<E> removeAt(int index);

    interface Builder<E> extends MutableList<E>, PersistentCollection.Builder<E> {
        @Override PersistentList<E> build();
    }

}
