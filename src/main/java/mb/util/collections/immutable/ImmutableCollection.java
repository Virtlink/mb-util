package mb.util.collections.immutable;

import mb.util.collections.CollectionView;

import java.util.Collection;

/**
 * An immutable collection.
 *
 * Implementations of this interface must be immutable and thread-safe.
 *
 * @param <E> the type of elements in the collection
 */
public interface ImmutableCollection<E> extends CollectionView<E> {

    /**
     * Creates an empty immutable collection.
     *
     * @param <E> the type of elements in the collection
     * @return the immutable collection
     */
    static <E> ImmutableCollection<E> of() {
        // This overload of of() always returns the same empty ListView instance.
        throw new UnsupportedOperationException();
    }

    /**
     * Creates a singleton immutable collection.
     *
     * @param element the element in the collection
     * @param <E> the type of elements in the collection
     * @return the immutable collection
     */
    static <E> ImmutableCollection<E> of(E element) {
        // This overload of of() returns a singleton ListView instance.
        throw new UnsupportedOperationException();
    }

    /**
     * Creates an immutable collection from the specified array.
     *
     * @param elements the elements in the collection
     * @param <E> the type of elements in the collection
     * @return the immutable collection
     */
    @SafeVarargs static <E> ImmutableCollection<E> of(E... elements) {
        if (elements.length == 0) return of();
        throw new UnsupportedOperationException();
    }

    /**
     * Creates an immutable collection wrapping the specified collection.
     *
     * @param collection the collection to wrap
     * @param <E> the type of elements in the collection
     * @return the immutable collection
     */
    static <E> ImmutableCollection<E> from(Collection<? extends E> collection) {
        throw new UnsupportedOperationException();
    }

    /**
     * Creates an immutable collection wrapping a copy of the specified elements.
     *
     * @param elements the elements to copy
     * @param <E> the type of elements in the collection
     * @return the immutable collection
     */
    static <E> ImmutableCollection<E> copyFrom(Iterable<? extends E> elements) {
        throw new UnsupportedOperationException();
    }

    /**
     * Creates an immutable collection wrapping a copy of the specified collection.
     *
     * @param collection the collection to copy
     * @param <E> the type of elements in the collection
     * @return the immutable collection
     */
    static <E> ImmutableCollection<E> copyFrom(Collection<? extends E> collection) {
        throw new UnsupportedOperationException();
    }

}
