package mb.util.collections.immutable;

import mb.util.collections.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * An immutable list.
 *
 * Implementations of this interface must be immutable and thread-safe.
 *
 * @param <E> the type of elements in the list
 */
public interface ImmutableList<E> extends ListView<E> {

    /**
     * Creates an empty immutable list.
     *
     * @param <E> the type of elements in the list
     * @return the immutable list
     */
    static <E> ImmutableList<E> of() {
        // This overload of of() always returns the same empty ImmutableList instance.
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
        // This overload of of() returns a singleton ImmutableList instance.
        throw new UnsupportedOperationException();
    }

    /**
     * Creates an immutable list from the specified array.
     *
     * @param elements the elements in the list
     * @param <E> the type of elements in the list
     * @return the immutable list
     */
    @SafeVarargs static <E> ImmutableList<E> of(E... elements) {
        throw new UnsupportedOperationException();
    }

    /**
     * Creates an immutable list wrapping the specified list.
     *
     * @param list the list to wrap
     * @param <E> the type of elements in the list
     * @return the immutable list
     */
    static <E> ImmutableList<E> from(List<? extends E> list) {
        throw new UnsupportedOperationException();
    }

    /**
     * Creates an immutable list wrapping a copy of the specified elements.
     *
     * @param elements the elements to copy
     * @param <E> the type of elements in the list
     * @return the immutable list
     */
    static <E> ImmutableList<E> copyFrom(Iterable<? extends E> elements) {
        throw new UnsupportedOperationException();
    }

    /**
     * Creates an immutable list wrapping a copy of the specified list.
     *
     * @param list the list to copy
     * @param <E> the type of elements in the list
     * @return the immutable list
     */
    static <E> ImmutableList<E> copyFrom(List<? extends E> list) {
        throw new UnsupportedOperationException();
    }

    @Override ImmutableList<E> subListView(int fromIndex, int toIndex);

}
