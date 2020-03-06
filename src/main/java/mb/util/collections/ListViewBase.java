package mb.util.collections;

import org.jetbrains.annotations.NotNull;

import org.jetbrains.annotations.Nullable;
import java.io.Serializable;
import java.util.*;
import java.util.function.UnaryOperator;

/**
 * Base class for implementations of {@link ListView}.
 *
 * Most of the implementations are in the {@link ListView} interface,
 * so that it's easy to implement the interface without extending this class.
 * However, this class also implements {@link List}, such that
 * a call to {@link ListView#asUnmodifiable()} can return itself.
 *
 * @param <E> the type of elements in the list
 */
/* package private */ abstract class ListViewBase<E> extends CollectionViewBase<E> implements ListView<E>, List<E>, Serializable {

    @Override
    public abstract int size();

    @Override
    public abstract E get(int index);

    @Override
    public boolean contains(Object element) {
        return ListView.super.contains(element);
    }

    @NotNull
    @Override
    public Iterator<E> iterator() {
        // The default implementation returns the ListIterator as the Iterator.
        // However, for some implementations it might be more efficient to return
        // just an Iterator with reduced functionality compared to returning
        // a full ListIterator, espectally when the list is not efficient at random access.
        return listIterator();
    }

    @Override
    public int indexOf(@Nullable Object element) {
        return ListView.super.indexOf(element);
    }

    @Override
    public int lastIndexOf(@Nullable Object element) {
        return ListView.super.lastIndexOf(element);
    }

    @NotNull
    @Override
    public ListIterator<E> listIterator() {
        return ListView.super.listIterator();
    }

    @NotNull
    @Override
    public abstract ListIterator<E> listIterator(int index);

    @Override
    public List<E> asUnmodifiable() {
        return this;
    }

    @Override
    public ListView<E> subListView(int fromIndex, int toIndex) {
        if (fromIndex < 0 || fromIndex > size() || toIndex < fromIndex || toIndex > size())
            throw new IndexOutOfBoundsException();

        return new SubListView<>(this, fromIndex, toIndex - fromIndex);
    }

    @NotNull
    @Override
    public List<E> subList(int fromIndex, int toIndex) {
        return subListView(fromIndex, toIndex).asUnmodifiable();
    }

    @Override
    public String toString() {
        // Calculate with allocating an Iterator

        // This implementation will throw a StackOverflowError when
        // the collection contains itself indirectly.

        // This implementation does not assume calls to size() report the same size as
        // the number of elements returned by the iterators, as the collection might
        // be concurrently modified between calls to size() and comparing the elements.

        Iterator<E> iterator = iterator();
        if (!iterator.hasNext()) return "[]";

        StringBuilder sb = new StringBuilder();
        sb.append('[');
        E e = iterator.next();
        sb.append(e == this ? "(this collection)" : e);
        while (iterator.hasNext()) {
            sb.append(',').append(' ');
            e = iterator.next();
            sb.append(e == this ? "(this collection)" : e);
        }
        sb.append(']');
        return sb.toString();
    }

    @Override
    @Deprecated
    public boolean addAll(int index, @NotNull Collection<? extends E> c) {
        throw new UnsupportedOperationException();
    }

    @Override
    @Deprecated
    public void replaceAll(UnaryOperator<E> operator) {
        throw new UnsupportedOperationException();
    }

    @Override
    @Deprecated
    public void sort(Comparator<? super E> c) {
        throw new UnsupportedOperationException();
    }

    @Override
    @Deprecated
    public E set(int index, E element) {
        throw new UnsupportedOperationException();
    }

    @Override
    @Deprecated
    public void add(int index, E element) {
        throw new UnsupportedOperationException();
    }

    @Override
    @Deprecated
    public E remove(int index) {
        throw new UnsupportedOperationException();
    }
}
