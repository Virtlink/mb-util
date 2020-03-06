package mb.util.collections;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import java.io.Serializable;
import java.util.ListIterator;
import java.util.Objects;
import java.util.stream.Stream;

/**
 * An implementation of {@link ListView} that has a singleton.
 *
 * Having a special class for singleton lists removes the allocation
 * of an inner list structure, and additionally allows the implementations
 * of the various methods of the list to be very efficient.
 */
/* package private */ class ListSingletonView<E> extends ListViewBase<E> implements Serializable {

    private final E element;

    /* package private */ ListSingletonView(E element) {
        this.element = element;
    }

    @Override
    public int size() {
        return 1;
    }

    @Override
    public E get(int index) {
        if (index != 0) throw new IndexOutOfBoundsException();
        return element;
    }

    @Override
    public Stream<E> stream() {
        return Stream.of(element);
    }

    @Override
    public ListView<E> subListView(int fromIndex, int toIndex) {
        if (fromIndex < 0 || toIndex > 1 || fromIndex > toIndex) throw new IndexOutOfBoundsException();
        // Either the sublist selected the one element, in which case we can return ourselves,
        // or it didn't, in which case it must have selected nothing and we can return an empty ListView.
        if (fromIndex == 0 && toIndex == 1) {
            return this;
        } else {
            return ListView.of();
        }
    }

    @NotNull
    @Override
    public ListIterator<E> listIterator(int index) {
        if (index != 0 && index != 1) throw new IndexOutOfBoundsException();
        return new RandomAccessListIteratorBase<E>(index) {
            @Override
            protected int getSize() {
                return 1;
            }

            @Override
            protected E getElement(int index) {
                return element;
            }
        };
    }

    @Override
    public int indexOf(@Nullable Object element) {
        if (contains(element)) return 0;
        else return -1;
    }

    @Override
    public int lastIndexOf(@Nullable Object element) {
        if (contains(element)) return 0;
        else return -1;
    }

    @Override
    public boolean contains(Object element) {
        return Objects.equals(element, this.element);
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
        return HashCodeUtils.listHashCodeOf(this.element);
    }

    @Override
    public String toString() {
        // This implementation will throw a StackOverflowError when
        // the collection contains itself indirectly.

        return "[" + (element == this ? "(this collection)" : element) + ']';
    }

}
