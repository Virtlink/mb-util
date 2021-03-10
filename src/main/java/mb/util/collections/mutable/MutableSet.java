package mb.util.collections.mutable;

import mb.util.collections.ListView;
import mb.util.collections.SetView;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.*;
import java.util.stream.Stream;

/**
 * A mutable set.
 *
 * @param <E> the type of elements in the set
 */
public interface MutableSet<E> extends MutableCollection<E>, SetView<E>, Set<E> {

    /**
     * Creates an empty mutable set.
     *
     * @param <E> the type of elements in the set
     * @return the mutable set
     */
    static <E> MutableSet<E> of() {
        // This overload of of() always returns the same empty ImmutableList instance.
        throw new UnsupportedOperationException();
    }

    /**
     * Creates a singleton mutable set.
     *
     * @param element the element in the set
     * @param <E> the type of elements in the set
     * @return the mutable set
     */
    static <E> MutableSet<E> of(E element) {
        // This overload of of() returns a singleton ImmutableList instance.
        throw new UnsupportedOperationException();
    }

    /**
     * Creates an mutable set from the specified array.
     *
     * @param elements the elements in the set
     * @param <E> the type of elements in the set
     * @return the mutable set
     */
    @SafeVarargs static <E> MutableSet<E> of(E... elements) {
        throw new UnsupportedOperationException();
    }

    /**
     * Creates an mutable set from the specified set.
     *
     * @param set the set to wrap
     * @param <E> the type of elements in the set
     * @return the mutable set
     */
    static <E> MutableSet<E> from(Set<? extends E> set) {
        throw new UnsupportedOperationException();
    }

    /**
     * Creates an mutable set from a copy of the specified elements.
     *
     * @param elements the elements to copy
     * @param <E> the type of elements in the set
     * @return the mutable set
     */
    static <E> MutableSet<E> copyFrom(Iterable<? extends E> elements) {
        throw new UnsupportedOperationException();
    }

    @Override
    default boolean isEmpty() {
        return MutableCollection.super.isEmpty();
    }

    @Override
    default boolean contains(@Nullable Object element) {
        throw new UnsupportedOperationException();
    }

    @Override
    default boolean containsAll(@NotNull Collection<?> elements) {
        return MutableCollection.super.containsAll(elements);
    }

    @Override
    default Stream<E> stream() {
        return MutableCollection.super.stream();
    }

    @Override
    default Stream<E> parallelStream() {
        return MutableCollection.super.parallelStream();
    }

    @NotNull
    @Override
    default Object[] toArray() {
        return MutableCollection.super.toArray();
    }

    @NotNull
    @Override
    default <T> T[] toArray(@NotNull T[] a) {
        return MutableCollection.super.toArray(a);
    }

    @Override
    default @NotNull Iterator<E> iterator() {
        throw new UnsupportedOperationException();
    }

}
