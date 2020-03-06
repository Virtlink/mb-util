package mb.util.collections.mutable;

import mb.util.collections.CollectionView;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Collection;
import java.util.Iterator;
import java.util.stream.Stream;

/**
 * A mutable collection.
 *
 * @param <E> the type of elements in the collection
 */
public interface MutableCollection<E> extends CollectionView<E>, Collection<E> {

    /**
     * Creates an empty mutable collection.
     *
     * @param <E> the type of elements in the collection
     * @return the mutable collection
     */
    static <E> MutableCollection<E> of() {
        // This overload of of() always returns the same empty ListView instance.
        throw new UnsupportedOperationException();
    }

    /**
     * Creates a singleton mutable collection.
     *
     * @param element the element in the collection
     * @param <E> the type of elements in the collection
     * @return the mutable collection
     */
    static <E> MutableCollection<E> of(E element) {
        // This overload of of() returns a singleton ListView instance.
        throw new UnsupportedOperationException();
    }

    /**
     * Creates an mutable collection from the specified array.
     *
     * @param elements the elements in the collection
     * @param <E> the type of elements in the collection
     * @return the mutable collection
     */
    @SafeVarargs static <E> MutableCollection<E> of(E... elements) {
        if (elements.length == 0) return of();
        throw new UnsupportedOperationException();
    }

    /**
     * Creates an mutable collection wrapping the specified collection.
     *
     * @param collection the collection to wrap
     * @param <E> the type of elements in the collection
     * @return the mutable collection
     */
    static <E> MutableCollection<E> from(Collection<? extends E> collection) {
        throw new UnsupportedOperationException();
    }

    /**
     * Creates an mutable collection wrapping a copy of the specified elements.
     *
     * @param elements the elements to copy
     * @param <E> the type of elements in the collection
     * @return the mutable collection
     */
    static <E> MutableCollection<E> copyFrom(Iterable<? extends E> elements) {
        throw new UnsupportedOperationException();
    }

    /**
     * Creates an mutable collection wrapping a copy of the specified collection.
     *
     * @param collection the collection to copy
     * @param <E> the type of elements in the collection
     * @return the mutable collection
     */
    static <E> MutableCollection<E> copyFrom(Collection<? extends E> collection) {
        throw new UnsupportedOperationException();
    }

    @Override
    default boolean isEmpty() {
        return CollectionView.super.isEmpty();
    }

    @Override
    boolean contains(@Nullable Object element);

    @Override
    default boolean containsAll(@NotNull Collection<?> elements) {
        return CollectionView.super.containsAll(elements);
    }

    @Override
    default Stream<E> stream() {
        return CollectionView.super.stream();
    }

    @Override
    default Stream<E> parallelStream() {
        return CollectionView.super.parallelStream();
    }

    @NotNull
    @Override
    default Object[] toArray() {
        return CollectionView.super.toArray();
    }

    @NotNull
    @Override
    default <T> T[] toArray(@NotNull T[] a) {
        return CollectionView.super.toArray(a);
    }

    @Override
    @NotNull Iterator<E> iterator();


}
