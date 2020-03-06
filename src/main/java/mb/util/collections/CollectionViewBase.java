package mb.util.collections;

import org.jetbrains.annotations.NotNull;

import java.io.Serializable;
import java.util.Collection;
import java.util.Iterator;
import java.util.Objects;
import java.util.Spliterator;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.stream.Stream;

/**
 * Base class for implementations of {@link CollectionView}.
 *
 * Most of the implementations are in the {@link CollectionView} interface,
 * so that it's easy to implement the interface without extending this class.
 * However, this class also implements {@link Collection}, such that
 * a call to {@link CollectionView#asUnmodifiable()} can return itself.
 *
 * @param <E> the type of elements in the collection
 */
/* package private */ abstract class CollectionViewBase<E> implements CollectionView<E>, Collection<E>, Serializable {

    @Override
    public abstract int size();

    @Override
    public boolean isEmpty() {
        return CollectionView.super.isEmpty();
    }

    @Override
    public abstract boolean contains(Object o);

    @Override
    public boolean containsAll(@NotNull Collection<?> elements) {
        return CollectionView.super.containsAll(elements);
    }

    @Override
    public void addAllTo(Collection<? super E> collection) {
        CollectionView.super.addAllTo(collection);
    }

    @Override
    public Collection<E> asUnmodifiable() {
        return this;
    }

    @NotNull
    @Override
    public abstract Iterator<E> iterator();

    @Override
    public Spliterator<E> spliterator() {
        return Collection.super.spliterator();
    }

    @Override
    public void forEach(Consumer<? super E> action) {
        CollectionView.super.forEach(action);
    }

    @NotNull
    @Override
    public Object[] toArray() {
        return CollectionView.super.toArray();
    }

    @NotNull
    @Override
    public <T> T[] toArray(@NotNull T[] a) {
        return CollectionView.super.toArray(a);
    }

    @Override
    public Stream<E> stream() {
        return CollectionView.super.stream();
    }

    @Override
    public Stream<E> parallelStream() {
        return CollectionView.super.parallelStream();
    }

    @Override
    public abstract boolean equals(Object obj);

    @Override
    public abstract int hashCode();

    @Override
    public abstract String toString();

    @Override
    @Deprecated
    public final boolean add(E e) {
        throw new UnsupportedOperationException();
    }

    @Override
    @Deprecated
    public final boolean remove(Object o) {
        throw new UnsupportedOperationException();
    }

    @Override
    @Deprecated
    public final boolean addAll(@NotNull Collection<? extends E> c) {
        throw new UnsupportedOperationException();
    }

    @Override
    @Deprecated
    public final boolean removeAll(@NotNull Collection<?> c) {
        throw new UnsupportedOperationException();
    }

    @Override
    @Deprecated
    public final boolean removeIf(Predicate<? super E> filter) {
        throw new UnsupportedOperationException();
    }

    @Override
    @Deprecated
    public final boolean retainAll(@NotNull Collection<?> c) {
        throw new UnsupportedOperationException();
    }

    @Override
    @Deprecated
    public final void clear() {
        throw new UnsupportedOperationException();
    }
}
