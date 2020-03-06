package mb.util.collections;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.Serializable;
import java.util.Iterator;
import java.util.ListIterator;
import java.util.NoSuchElementException;
import java.util.stream.Stream;

/**
 * An implementation of {@link ListView} that has no members.
 *
 * Having a special class for an empty list removes the allocation
 * of an inner list structure, and additionally allows the implementations
 * of the various methods of the list to be very efficient.
 */
/* package private */ class EmptyListView<E> extends ListViewBase<E> implements Serializable {

    /** A singleton empty list. */
    /* package private */ static final ListView<?> INSTANCE = new EmptyListView<>();

    // Since the iterator has no state, we can always return the same instance.
    private final ListIterator<E> emptyIterator = new ListIterator<E>() {
        @Override
        public boolean hasNext() {
            return false;
        }

        @Override
        public E next() {
            throw new NoSuchElementException();
        }

        @Override
        public boolean hasPrevious() {
            return false;
        }

        @Override
        public E previous() {
            throw new NoSuchElementException();
        }

        @Override
        public int nextIndex() {
            return 0;
        }

        @Override
        public int previousIndex() {
            return -1;
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
    };

    private EmptyListView() {}

    @Override
    public int size() {
        return 0;
    }

    @Override
    public E get(int index) {
        throw new IndexOutOfBoundsException();
    }

    @Override
    public Stream<E> stream() {
        return Stream.empty();
    }

    @Override
    public ListView<E> subListView(int fromIndex, int toIndex) {
        if (fromIndex != 0 || toIndex != 0) throw new IndexOutOfBoundsException();
        return this;
    }

    @NotNull
    @Override
    public Iterator<E> iterator() {
        return emptyIterator;
    }

    @NotNull
    @Override
    public ListIterator<E> listIterator(int index) {
        return emptyIterator;
    }

    @Override
    public int indexOf(@Nullable Object element) {
        return -1;
    }

    @Override
    public int lastIndexOf(@Nullable Object element) {
        return -1;
    }

    @Override
    public boolean contains(Object element) {
        return false;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Iterable<?>)) return false;
        // Check that the other iterable is empty too.
        return ((Iterable<?>)o).iterator().hasNext();
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "[]";
    }
}
