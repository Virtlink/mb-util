package mb.util.collections.immutable;

import mb.util.collections.ListView;
import mb.util.collections.SetView;

import java.util.List;
import java.util.Set;

/**
 * An immutable set.
 *
 * This interface is covariant.
 *
 * Implementations of this interface are be immutable and thread-safe.
 *
 * @param <E> the type of elements in the set
 */
public interface ImmutableSet<E> extends SetView<E>, ImmutableCollection<E> {

    /**
     * Creates an empty immutable set.
     *
     * @param <E> the type of elements in the set
     * @return the immutable set
     */
    static <E> ImmutableSet<E> of() {
        // We can return a special empty implementation.
        throw new UnsupportedOperationException();
    }

    /**
     * Creates a singleton immutable set.
     *
     * @param element the element in the set
     * @param <E> the type of elements in the set
     * @return the immutable set
     */
    static <E> ImmutableSet<E> of(E element) {
        // We can return a special singleton implementation.
        throw new UnsupportedOperationException();
    }

    /**
     * Creates an immutable set by copying the elements from the specified array.
     *
     * Changes to the input array are not reflected in this set.
     *
     * @param elements the elements in the set
     * @param <E> the type of elements in the set
     * @return the immutable set
     */
    @SafeVarargs static <E> ImmutableSet<E> of(E... elements) {
        if (elements.length == 0) {
            // When the array is empty, we can just return the empty set,
            // because the input array cannot be modified and the fact that it
            // isn't part of the returned SetView is unobservable.
            return of();
        } else {
            // Otherwise, we copy the elements into an immutable set.
            // TODO:
            throw new UnsupportedOperationException();
        }
    }

    /**
     * Creates an immutable set by copying the elements from the specified iterable.
     *
     * Changes to the input iterable are not reflected in this set.
     *
     * @param elements the elements to include
     * @param <E> the type of elements in the set
     * @return the immutable set
     */
    static <E> ImmutableSet<E> from(Iterable<? extends E> elements) {
        if (elements instanceof ImmutableSet<?>) {
            // When the iterable is an immutable set (and implements SetView) we can just return it.
            //noinspection unchecked
            return (ImmutableSet<E>)elements;
        } else if (elements instanceof Set<?>) {
            // When the iterable is a set, we can call the other overload.
            //noinspection unchecked
            return from((Set<E>)elements);
        } else {
            // Otherwise, we copy the elements into an immutable set.
            // TODO:
            throw new UnsupportedOperationException();
        }
    }

    /**
     * Creates an immutable set by copying the elements from the specified set.
     *
     * Changes to the input set are not reflected in this set.
     *
     * @param set the set of elements to include
     * @param <E> the type of elements in the set
     * @return the immutable set
     */
    static <E> ImmutableSet<E> from(Set<? extends E> set) {
        if (set instanceof ImmutableSet<?>) {
            // When the set is immutable (and implements SetView) we can just return it.
            //noinspection unchecked
            return (ImmutableSet<E>)set;
        } else {
            // Otherwise, we copy the elements into an immutable set.
            // TODO:
            throw new UnsupportedOperationException();
        }
    }

}
