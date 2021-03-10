package mb.util.collections;

import mb.util.collections.immutable.ImmutableSet;
import mb.util.collections.immutable.PersistentList;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

/**
 * An unmodifiable view of a set.
 *
 * This interface is covariant.
 *
 * Changes to the underlying set are visible through this view.
 * To get an immutable set, use one of the immutable interfaces.
 *
 * The implementation may not be thread-safe.
 * To get a thread-safe implementation, use one of the immutable interfaces.
 *
 * @param <E> the type of elements in the collection
 */
public interface SetView<E> extends CollectionView<E>, Serializable {

    /**
     * Creates an empty unmodifiable set.
     *
     * @param <E> the type of elements in the set
     * @return the unmodifiable set
     */
    static <E> SetView<E> of() {
        // We can return a special empty implementation.
        return ImmutableSet.of();
    }

    /**
     * Creates a singleton unmodifiable set.
     *
     * @param element the element in the set
     * @param <E> the type of elements in the set
     * @return the unmodifiable set
     */
    static <E> SetView<E> of(E element) {
        // We can return a special singleton implementation.
        return ImmutableSet.of(element);
    }

    /**
     * Creates an unmodifiable set from the specified array.
     *
     * Changes to the input array are reflected in this set.
     *
     * @param elements the elements in the set
     * @param <E> the type of elements in the set
     * @return the unmodifiable set
     */
    @SafeVarargs static <E> SetView<E> of(E... elements) {
        if (elements.length == 0) {
            // When the array is empty, we can just return the empty set,
            // because the input array cannot be modified and the fact that it
            // isn't part of the returned SetView is unobservable.
            return of();
        } else {
            // Otherwise, we wrap the array in a SetView.
            // TODO:
            throw new UnsupportedOperationException();
        }
    }

    /**
     * Creates an unmodifiable set by wrapping the specified iterable.
     *
     * Changes to the input iterable are reflected in this set.
     *
     * @param elements the elements to wrap
     * @param <E> the type of elements in the set
     * @return the unmodifiable set
     */
    static <E> SetView<E> from(Iterable<? extends E> elements) {
        if (elements instanceof SetView<?>) {
            // When the iterable is an unmodifiable set (and implements SetView) we can just return it.
            //noinspection unchecked
            return (SetView<E>)elements;
        } else if (elements instanceof Set<?>) {
            // When the iterable is a set, we call the other overload.
            //noinspection unchecked
            return from((Set<E>)elements);
        } else {
            // Otherwise, we wrap the iterable in an unmodifiable set.
            // TODO:
            throw new UnsupportedOperationException();
        }
    }

    /**
     * Creates an unmodifiable set by wrapping the specified set.
     *
     * Changes to the input set are reflected in this set.
     *
     * @param set the set to wrap
     * @param <E> the type of elements in the set
     * @return the unmodifiable set
     */
    static <E> SetView<E> from(Set<? extends E> set) {
        if (set instanceof SetView<?>) {
            // When the set is an unmodifiable set (and implements SetView) we can just return it.
            //noinspection unchecked
            return (SetView<E>)set;
        } else {
            // Otherwise, we wrap the set in an unmodifiable set.
            // TODO:
            throw new UnsupportedOperationException();
        }
    }

}
