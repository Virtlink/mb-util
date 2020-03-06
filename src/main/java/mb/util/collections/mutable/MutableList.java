package mb.util.collections.mutable;

import mb.util.collections.ListView;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.stream.Stream;

/**
 * A mutable list.
 *
 * @param <E> the type of elements in the list
 */
public interface MutableList<E> extends MutableCollection<E>, ListView<E>, List<E> {

    /**
     * Creates an empty mutable list.
     *
     * @param <E> the type of elements in the list
     * @return the mutable list
     */
    static <E> MutableList<E> of() {
        // This overload of of() always returns the same empty ImmutableList instance.
        throw new UnsupportedOperationException();
    }

    /**
     * Creates a singleton mutable list.
     *
     * @param element the element in the list
     * @param <E> the type of elements in the list
     * @return the mutable list
     */
    static <E> MutableList<E> of(E element) {
        // This overload of of() returns a singleton ImmutableList instance.
        throw new UnsupportedOperationException();
    }

    /**
     * Creates an mutable list from the specified array.
     *
     * @param elements the elements in the list
     * @param <E> the type of elements in the list
     * @return the mutable list
     */
    @SafeVarargs static <E> MutableList<E> of(E... elements) {
        throw new UnsupportedOperationException();
    }

    /**
     * Creates an mutable list wrapping the specified list.
     *
     * @param list the list to wrap
     * @param <E> the type of elements in the list
     * @return the mutable list
     */
    static <E> MutableList<E> from(List<? extends E> list) {
        throw new UnsupportedOperationException();
    }

    /**
     * Creates an mutable list wrapping a copy of the specified elements.
     *
     * @param elements the elements to copy
     * @param <E> the type of elements in the list
     * @return the mutable list
     */
    static <E> MutableList<E> copyFrom(Iterable<? extends E> elements) {
        throw new UnsupportedOperationException();
    }

    /**
     * Creates an mutable list wrapping a copy of the specified list.
     *
     * @param list the list to copy
     * @param <E> the type of elements in the list
     * @return the mutable list
     */
    static <E> MutableList<E> copyFrom(List<? extends E> list) {
        throw new UnsupportedOperationException();
    }

    @Override
    default boolean isEmpty() {
        return MutableCollection.super.isEmpty();
    }

    @Override
    default boolean contains(@Nullable Object element) {
        return ListView.super.contains(element);
    }

    @Override
    default boolean containsAll(@NotNull Collection<?> elements) {
        return MutableCollection.super.containsAll(elements);
    }

    @Override
    default int indexOf(@Nullable Object element) {
        return ListView.super.indexOf(element);
    }

    @Override
    default int lastIndexOf(@Nullable Object element) {
        return ListView.super.lastIndexOf(element);
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
        return ListView.super.iterator();
    }

    @NotNull
    @Override
    default ListIterator<E> listIterator() {
        return ListView.super.listIterator();
    }

    @NotNull
    @Override
    default ListIterator<E> listIterator(int index) {
        return ListView.super.listIterator(index);
    }

    @Override MutableList<E> subListView(int fromIndex, int toIndex);
}
