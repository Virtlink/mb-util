package mb.util.collections.immutable;

import mb.util.collections.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

/**
 * An immutable list.
 *
 * This interface is covariant.
 *
 * Implementations of this interface are be immutable and thread-safe.
 *
 * @param <E> the type of elements in the list
 */
public interface ImmutableList<E> extends ListView<E>, ImmutableCollection<E> {

    /**
     * Creates an empty immutable list.
     *
     * @param <E> the type of elements in the list
     * @return the immutable list
     */
    static <E> ImmutableList<E> of() {
        // We can return a special empty implementation.
        throw new UnsupportedOperationException();
    }

    /**
     * Creates a singleton immutable list.
     *
     * @param element the element in the list
     * @param <E> the type of elements in the list
     * @return the immutable list
     */
    static <E> ImmutableList<E> of(E element) {
        // We can return a special singleton implementation.
        throw new UnsupportedOperationException();
    }

    /**
     * Creates an immutable list by copying the elements from the specified array.
     *
     * Changes to the input array are not reflected in this list.
     *
     * @param elements the elements in the list
     * @param <E> the type of elements in the list
     * @return the immutable list
     */
    @SafeVarargs static <E> ImmutableList<E> of(E... elements) {
        if (elements.length == 0) {
            // When the array is empty, we can just return the empty list,
            // because the input array cannot be modified and the fact that it
            // isn't part of the returned ListView is unobservable.
            return of();
        } else {
            // Otherwise, we copy the elements into an immutable list.
            // TODO:
            throw new UnsupportedOperationException();
        }
    }

    /**
     * Creates an immutable list by copying the elements from the specified iterable.
     *
     * Changes to the input iterable are not reflected in this list.
     *
     * @param elements the elements to include
     * @param <E> the type of elements in the list
     * @return the immutable list
     */
    static <E> ImmutableList<E> from(Iterable<? extends E> elements) {
        if (elements instanceof ImmutableList<?>) {
            // When the iterable is an immutable list (and implements ListView) we can just return it.
            //noinspection unchecked
            return (ImmutableList<E>)elements;
        } else if (elements instanceof List<?>) {
            // When the iterable is a list, we can call the other overload.
            //noinspection unchecked
            return from((List<E>)elements);
        } else {
            // Otherwise, we copy the elements into an immutable list.
            // TODO:
            throw new UnsupportedOperationException();
        }
    }

    /**
     * Creates an immutable list by copying the elements from the specified list.
     *
     * Changes to the input list are not reflected in this list.
     *
     * @param list the list of elements to include
     * @param <E> the type of elements in the list
     * @return the immutable list
     */
    static <E> ImmutableList<E> from(List<? extends E> list) {
        if (list instanceof ImmutableList<?>) {
            // When the list is immutable (and implements ListView) we can just return it.
            //noinspection unchecked
            return (ImmutableList<E>)list;
        } else {
            // Otherwise, we copy the elements into an immutable list.
            // TODO:
            throw new UnsupportedOperationException();
        }
    }

    @Override ImmutableList<E> subListView(int fromIndex, int toIndex);

}
