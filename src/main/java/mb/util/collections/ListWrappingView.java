package mb.util.collections;

import org.jetbrains.annotations.NotNull;

import org.jetbrains.annotations.Nullable;
import java.io.Serializable;
import java.util.*;
import java.util.function.Consumer;
import java.util.stream.Stream;


/**
 * A wrapper around a {@link List list} with read-only operations. Only {@link Serializable serializable} when the
 * wrapped list is.
 *
 * @param <E> The type of elements in this list.
 */
@SuppressWarnings("unused")
/* package private */ class ListWrappingView<E> extends ListViewBase<E> implements Serializable {

    private List<? extends E> list;

    /* package private */ ListWrappingView(List<? extends E> list) {
        this.list = list;
    }

    @Override
    public void addAllTo(Collection<? super E> collection) {
        collection.addAll(this.list);
    }

    @Override
    public ListView<E> subListView(int fromIndex, int toIndex) {
        return ListView.from(list.subList(fromIndex, toIndex));
    }

    // List<T>

    @Override
    public E get(int index) {
        return this.list.get(index);
    }

    @Override
    public int indexOf(@Nullable Object element) {
        return this.list.indexOf(element);
    }

    @Override
    public int lastIndexOf(@Nullable Object element) {
        return this.list.lastIndexOf(element);
    }

    @NotNull
    @Override
    public ListIterator<E> listIterator() {
        //noinspection unchecked
        return new WrappingListIterator<>((ListIterator<E>)this.list.listIterator());
    }

    @NotNull
    @Override
    public ListIterator<E> listIterator(int index) {
        //noinspection unchecked
        return new WrappingListIterator<>((ListIterator<E>)this.list.listIterator(index));
    }

    @NotNull
    @Override
    public List<E> subList(int fromIndex, int toIndex) {
        //noinspection unchecked
        return (List<E>)this.list.subList(fromIndex, toIndex);
    }

    // Collection<T>

    @Override
    public int size() {
        return this.list.size();
    }

    @Override
    public boolean isEmpty() {
        return this.list.isEmpty();
    }

    @Override
    public boolean contains(Object o) {
        return this.list.contains(o);
    }

    @Override
    public boolean containsAll(@NotNull Collection<?> elements) {
        return this.list.containsAll(elements);
    }

    @Override
    public Stream<E> stream() {
        //noinspection unchecked
        return (Stream<E>)this.list.stream();
    }

    @Override
    public Stream<E> parallelStream() {
        //noinspection unchecked
        return (Stream<E>)this.list.parallelStream();
    }

    @NotNull
    @Override
    public Object[] toArray() {
        return this.list.toArray();
    }

    @NotNull
    @Override
    public <T> T[] toArray(@NotNull T[] a) {
        //noinspection SuspiciousToArrayCall
        return this.list.toArray(a);
    }

    // Iterable<T>

    @NotNull
    @Override
    public Iterator<E> iterator() {
        //noinspection unchecked
        return new CollectionWrappingView.WrappingIterator<>((Iterator<E>)this.list.iterator());
    }

    @Override
    public void forEach(Consumer<? super E> action) {
        this.list.forEach(action);
    }

    @Override
    public Spliterator<E> spliterator() {
        //noinspection unchecked
        return (Spliterator<E>)this.list.spliterator();
    }

    // Object

    // TODO: Equals

    @Override
    public int hashCode() {
        return this.list.hashCode();
    }

    @Override
    public String toString() {
        return this.list.toString();
    }

    static class WrappingListIterator<E> extends CollectionWrappingView.WrappingIterator<E> implements ListIterator<E> {

        private final ListIterator<E> wrappedIterator;

        WrappingListIterator(ListIterator<E> wrappedIterator) {
            super(wrappedIterator);
            this.wrappedIterator = wrappedIterator;
        }

        @Override
        public boolean hasPrevious() {
            return this.wrappedIterator.hasPrevious();
        }

        @Override
        public E previous() {
            return this.wrappedIterator.previous();
        }

        @Override
        public int nextIndex() {
            return this.wrappedIterator.nextIndex();
        }

        @Override
        public int previousIndex() {
            return this.wrappedIterator.previousIndex();
        }

        @Override
        public void set(E e) {
            throw new UnsupportedOperationException();
        }

        @Override
        public void add(E e) {
            throw new UnsupportedOperationException();
        }
    }
}

