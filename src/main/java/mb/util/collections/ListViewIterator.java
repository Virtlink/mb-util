package mb.util.collections;

import java.util.ListIterator;
import java.util.function.Consumer;

/**
 * A list view iterator that does not allow modification.
 *
 * @param <E> the type of elements
 */
/* package private */ final class ListViewIterator<E> implements ListIterator<E> {

    private final ListIterator<E> iterator;

    /**
     * Initializes a new instance of the {@link ListViewIterator} class.
     *
     * @param iterator the iterator
     */
    public ListViewIterator(ListIterator<E> iterator) {
        this.iterator = iterator;
    }

    @Override
    public boolean hasNext() {
        return iterator.hasNext();
    }

    @Override
    public E next() {
        return iterator.next();
    }

    @Override
    public boolean hasPrevious() {
        return iterator.hasPrevious();
    }

    @Override
    public E previous() {
        return iterator.previous();
    }

    @Override
    public int nextIndex() {
        return iterator.nextIndex();
    }

    @Override
    public int previousIndex() {
        return iterator.previousIndex();
    }

    @Override
    public void forEachRemaining(Consumer<? super E> action) {
        iterator.forEachRemaining(action);
    }

    @Override
    public void remove() {
        throw new UnsupportedOperationException();
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
