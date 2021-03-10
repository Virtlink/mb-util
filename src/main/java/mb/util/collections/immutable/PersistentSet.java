package mb.util.collections.immutable;

import mb.util.collections.mutable.MutableList;

import java.util.Iterator;
import java.util.List;
import java.util.Set;

/**
 * A persistent list.
 *
 * @param <E> the type of elements in the list
 */
public interface PersistentSet<E> extends PersistentCollection<E>, ImmutableList<E> {

    /**
     * Creates an empty persistent set.
     *
     * @param <E> the type of elements in the set
     * @return the persistent set
     */
    static <E> PersistentSet<E> of() {
        // We can return a special empty implementation.
        throw new UnsupportedOperationException();
    }

    /**
     * Creates a singleton persistent set.
     *
     * @param element the element in the set
     * @param <E> the type of elements in the set
     * @return the persistent set
     */
    static <E> PersistentSet<E> of(E element) {
        // We can return a special singleton implementation.
        throw new UnsupportedOperationException();
    }

    /**
     * Creates a persistent set from the specified array.
     *
     * @param elements the elements in the set
     * @param <E> the type of elements in the set
     * @return the persistent set
     */
    @SafeVarargs static <E> PersistentSet<E> of(E... elements) {
        if (elements.length == 0) {
            // When the array is empty, we can just return the empty set,
            // because the input array cannot be modified and the fact that it
            // isn't part of the returned SetView is unobservable.
            return of();
        } else {
            // Otherwise, we copy the elements into a persistent set.
            // TODO:
            throw new UnsupportedOperationException();
        }
    }

    /**
     * Creates a persistent set by copying the elements from the specified iterable.
     *
     * Changes to the input iterable are not reflected in this set.
     *
     * @param elements the elements to include
     * @param <E> the type of elements in the set
     * @return the persistent set
     */
    static <E> PersistentSet<E> from(Iterable<? extends E> elements) {
        if (elements instanceof PersistentSet<?>) {
            // When the iterable is a persistent set (and implements SetView) we can just return it.
            //noinspection unchecked
            return (PersistentSet<E>)elements;
        } else if (elements instanceof Set<?>) {
            // When the iterable is a set, we can call the other overload.
            //noinspection unchecked
            return from((Set<E>)elements);
        } else {
            // Otherwise, we copy the elements into a persistent set.
            // TODO:
            throw new UnsupportedOperationException();
        }
    }

    /**
     * Creates a persistent set from the specified set.
     *
     * Changes to the input set are not reflected in this set.
     *
     * @param set the set whose elements to include
     * @param <E> the type of elements in the set
     * @return the persistent set
     */
    static <E> PersistentSet<E> from(Set<? extends E> set) {
        if (set instanceof PersistentSet<?>) {
            // When the set is a persistent set (and implements SetView) we can just return it.
            //noinspection unchecked
            return (PersistentSet<E>)set;
        } else {
            // Otherwise, we copy the elements into a persistent set.
            // TODO:
            throw new UnsupportedOperationException();
        }
    }

    PersistentSet<E> set(int index, E element);
    PersistentSet<E> insertAt(int index, E element);
    PersistentSet<E> insertAllAt(int index, Iterator<? extends E> elements);
    PersistentSet<E> removeAt(int index);

    interface Builder<E> extends MutableList<E>, PersistentCollection.Builder<E> {
        @Override
        PersistentSet<E> build();
    }

}
